public class Card{
	enum Suit {Club,Diamond,Heart,Spade};
	private Suit suit; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; //1~13
	/**
	 * @param s suit
	 * @param r rank
	 */
	public Card(Suit s,int r){
		suit=s;
		rank=r;
	}	
	//TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10 for rank)
	public void printCard(){
		//Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as Clubs Ace
		switch (suit){
		case Club: 
			System.out.print("Club ");
			break;
		case Diamond:
			System.out.print("Diamond");
			break;
		case Heart:	
			System.out.print("Heart");
			break;
		case Spade:
			System.out.print("Spade");
			break;
		}
		if(rank==1) {
			System.out.println(" Ace");
		}
		else if(rank==11) {
			System.out.println(" Jack");
		}
		else if(rank==12) {
			System.out.println(" Queen");
		}
		else if(rank==13) {
			System.out.println(" King");
		}
		else
			System.out.println(" "+rank);
		
	}
	public Suit getSuit(){
		return suit;
	}
	public int getRank(){
		return rank;
	}
	
}