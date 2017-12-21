import java.util.ArrayList;

public class Table {
	private final int MAXPLAYER = 4; //the max number of players
	private Deck all; //all cards
	private Player[] Players; //all players
	private Dealer dealer; //a dealer 
	private int[] pos_betArray; // player's bet 
	private ArrayList<Card> playerCards=new ArrayList<Card>();	//the cards that players get in one game 
	private ArrayList<Card> dealer_card= new ArrayList<Card>(); //the cards that dealer get in one game 
	
	public Table(int nDeck) {
		pos_betArray=new int[MAXPLAYER]; 
		Players= new Player[MAXPLAYER];
		all=new Deck(nDeck);
		dealer=new Dealer();
	}
	public void set_player(int pos, Player p) {
		Players[pos]=p; 
	}
	public Player[] get_player() {
		return Players;
	}
	public void set_dealer(Dealer d) {
		dealer=d;
	}
	public Card get_faceup_card_of_dealer() {
		return dealer_card.get(1); //dealer 
	}
	private void ask_each_player_about_bets() {
		for(int i=0; i<Players.length;i++) {
			if(Players[i]==null)
				continue;
			Players[i].sayHello();
			Players[i].makeBet();
			pos_betArray[i]=Players[i].makeBet();  //***
		}
	}
	private void distribute_cards_to_dealer_and_players() {
		for(int i=0;i<Players.length;i++) {
			if(Players[i]==null)
				continue;
			playerCards.add(all.getOneCard(true)); //給牌
			playerCards.add(all.getOneCard(true)); //給牌
			Players[i].setOneRoundCard(playerCards); // put the cards to setOneRoundCard
			playerCards=new ArrayList<Card>();  //new
		}
		dealer_card.add(all.getOneCard(false));
		dealer_card.add(all.getOneCard(true));
		dealer.setOneRoundCard(dealer_card);
		System.out.print("Dealer's face up card is ");
		dealer_card.get(1).printCard();
	}
	private void ask_each_player_about_hits() {
		for(int i=0;i<Players.length;i++) {
			
			if(Players[i]==null) //可能有位置沒玩家
				continue; //跳過某位子 繼續執行
			boolean hit=false;
			do { //do while先執行一次再判斷
				hit=Players[i].hit_me(this);
				if(hit) { //if hit = true, player can get one card
					System.out.print("HIT! "); //player want card.
					Players[i].getOneRoundCard().add(all.getOneCard(true));
					System.out.println(Players[i].getName()+"'s cards now: ");
					for(Card y: Players[i].getOneRoundCard()) {
						y.printCard();
					}
					if(Players[i].getTotalValue()>21) //if the total value bigger than 21, hit=false 
						hit=false;
				}
			}while(hit);
			System.out.println(Players[i].getName()+" pass hit! ");
			System.out.println(Players[i].getName()+" final card: ");
			for(Card z: Players[i].getOneRoundCard())
				z.printCard();
			System.out.println(Players[i].getName()+"'s hit is over");
		}
	}
	private void ask_dealer_about_hits() {
		boolean hit=false;
		do{ //do while先執行一次再判斷
			hit=dealer.hit_me(this);
			if(hit) {
				System.out.print("HIT! ");
				dealer.getOneRoundCard().add(all.getOneCard(true));
				System.out.println("dealer's cards now: ");
				for(Card c: dealer.getOneRoundCard())
					c.printCard();
				if(dealer.getTotalValue()>21)
					hit=false; //if the total value bigger than 21, hit=false 
			}
		}while(hit);
		System.out.println("Dealer's hit is over! ");
		System.out.println("dealer, Final Card: ");
		for(Card x: dealer.getOneRoundCard())
			x.printCard();
	}
	private void calculate_chips(){
		System.out.print("Dealer's card  value is "+dealer.getTotalValue()+", Cards: ");
		dealer.printAllCard();
		for(int i=0;i<Players.length;i++){
			if(Players[i]==null)
				continue;
			System.out.println(Players[i].getName()+" card value is "+Players[i].getTotalValue());
			if(Players[i].getTotalValue()<=21&&dealer.getTotalValue()>21){
				Players[i].increaseChips(pos_betArray[i]);
				System.out.println(Players[i].getName()+", Get"+Players[i].makeBet()+" chips, the chips now is: "+Players[i].getCurrentChips());
			}
			if(Players[i].getTotalValue()<=21&&dealer.getTotalValue()<Players[i].getTotalValue()){
				Players[i].increaseChips(pos_betArray[i]);
				System.out.println(Players[i].getName()+", Get"+Players[i].makeBet()+" chips, the chips now is: "+Players[i].getCurrentChips());
			}
			if(dealer.getTotalValue()==Players[i].getTotalValue()){
				System.out.println(Players[i].getName()+", chips have no change! The chips now is: "+ Players[i].getCurrentChips());
			}
			if(dealer.getTotalValue()>21&&Players[i].getTotalValue()>21){
				System.out.println(Players[i].getName()+", chips have no change! The chips now is: "+ Players[i].getCurrentChips());
			}
			if(dealer.getTotalValue()<=21&&Players[i].getTotalValue()>21){
				Players[i].increaseChips(-pos_betArray[i]);
				System.out.println(Players[i].getName()+", Loss"+Players[i].makeBet()+" chips, the chips now is: "+Players[i].getCurrentChips());
			}
			if(Players[i].getTotalValue()<=21&&dealer.getTotalValue()>Players[i].getTotalValue()){
				Players[i].increaseChips(-pos_betArray[i]);
				System.out.println(Players[i].getName()+", Loss"+Players[i].makeBet()+" chips, the chips now is: "+Players[i].getCurrentChips());
			}
		}
		for(int i=0;i<pos_betArray.length;i++)
			pos_betArray[i]=0;
		dealer_card.clear();
	}
	public int[] get_palyers_bet() {
		return pos_betArray;
	}
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
	
}
