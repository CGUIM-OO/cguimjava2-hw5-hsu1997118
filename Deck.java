import java.util.ArrayList;
import java.util.Random;

public class Deck{
	private ArrayList<Card> cards;
	private ArrayList<Card> openCard; //存放此副牌中所有打開的牌，洗牌時要重置
	ArrayList<Card> usedCard = new ArrayList<>();
	public int nUsed;	
	//TODO: Please implement the constructor (30 points)
	public Deck(int nDeck){
		usedCard=new ArrayList<Card>();
		openCard=new ArrayList<Card>();
		cards=new ArrayList<Card>();
		//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		//Hint: Use new Card(x,y) and 3 for loops to add card into deck
		//Sample code start
		//Card card=new Card(1,1); ->means new card as clubs ace
		//cards.add(card);
		//Sample code end

		for(int k=0 ; k<nDeck ; k++){
			for(Card.Suit s : Card.Suit.values()){
				for(int j=1; j<= 13; j++){
					cards.add(new Card(s,j));
				}
			}
		}

		//--1. call shuffle (你們描述規定的)
		shuffle();
					
	}	
	//TODO: Please implement the method to print all cards on screen (10 points)
	public void printDeck(){
		//Hint: print all items in ArrayList<Card> cards, 
		//TODO: please implement and reuse printCard method in Card class (5 points)
		for(Card card:cards) {
			card.printCard();
		}
	}
	public ArrayList<Card> getAllCards(){
		return cards;
	}
	
	public Card getOneCard(boolean isOpened) {
		//--檢查排發完沒
		if (nUsed == 52)
			shuffle();
		nUsed+=1;
		
		if(isOpened) {
			openCard.add(cards.get(nUsed-1));
		}
		usedCard.add(cards.get(nUsed-1));
		return cards.get(nUsed-1);
	}

	public void shuffle() {
		Random random = new Random();
		
		for (int i = 51; i >= 0; i--) {
		    int j = random.nextInt(i + 1);
		    Card card = cards.get(i);
		    cards.set(i, cards.get(j));
		    cards.set(j, card);
		}
		usedCard.clear();
		nUsed = 0;
		openCard.clear();
	}
	public ArrayList<Card> getOpenedCard(){
		//回傳此副牌中所有打開過的牌，意即openCard
		return openCard;
	}
	
	
}