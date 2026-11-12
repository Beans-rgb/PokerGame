public class card {
    private String suit;
    private int value;

    public card(String suit, int value){
        this.suit = suit;
        this.value = value;
    }
    public String getSuit(){
        return suit;
    }
    public int getValue(){
        return value;
    }
}
