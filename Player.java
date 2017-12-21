import java.util.ArrayList;

public class Player extends Person{
	private String name; //玩家姓名
	private int chips; //玩家有的籌碼
	private int bet; //玩家此局下注的籌碼
	private ArrayList<Card> oneRoundCard; //此牌局的卡
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
	public void setOneRoundCard(ArrayList<Card> cards) {
		//設定此牌局所得到的卡 setter
		oneRoundCard = new ArrayList<Card>();
		oneRoundCard = cards;
	}
	public boolean hit_me(Table table) {
		//是否要牌，是回傳true，不再要牌則回傳false
		//基本參考條件：16點以下要牌，17點以上不要牌
		//提示：用oneRoundCard來算
		boolean temp=false;
		if(getTotalValue()<=16)
			temp =true;
		if(getTotalValue()>=17)
			temp =true;
		return temp;
	}
	public int getTotalValue() {
		//回傳此牌局所得的卡點數加總
		int points = 0;
		 for (int i = 0; i  < oneRoundCard.size(); i++) {
			 points += oneRoundCard.get(i).getRank();
		 }
		 return points;
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
