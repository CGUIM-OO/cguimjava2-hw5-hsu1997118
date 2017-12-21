
public class Player extends Person{
	private String name; //玩家姓名
	private int chips; //玩家有的籌碼
	private int bet; //玩家此局下注的籌碼
	//private ArrayList<Card> oneRoundCard; //此牌局的卡
	public Player(String name, int chips) { 
		//Player constructor，新增Player物件時，需要姓名、擁有的籌碼等兩個參數
		this.name=name;
		this.chips=chips;
	}
	public String getName() {
		return name;
	}
	public int makeBet() { 
		//下注，回傳預計下注的籌碼
		//注意：要檢查是否還有錢，沒錢了就不能再繼續下注
		bet=1; //基本下注：一次1元
		if(bet<1)
			return 0;
		else
			return bet;
	}
	public boolean hit_me(Table table) { //Player繼承Person
		//是否要牌，是回傳true，不再要牌則回傳false
		//基本參考條件：16點以下要牌，17點以上不要牌
		boolean temp=false;
		if(getTotalValue()<=16)
			temp =true;
		else if(hasAce()&&getTotalValue()==17) //因為1可以當1跟11 值可以變小 
			temp=true;
		if(getTotalValue()>17)
			temp =false;
		if(getTotalValue()>=21)
			temp=false;
		return temp;
	}
	public int getCurrentChips() {
		//回傳玩家現有籌碼
		return chips;
	}
	public void increaseChips (int diff) {
		//玩家籌碼變動，setter
		chips+=diff; 
	}
	public void sayHello() {
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}
}
