package SimpleVendingMachine;
//The inventory is the stock of a certain product in a vending machine.
public class Inventory {
    //note: size defines the capacity
    private String product;
    private int price, capacity, currAmount=0;

    public Inventory(String product, int price, int size){
        this.product = product;
        this.price = price;
        capacity = size;
    }

    //fill to full
    public void fill(){
        currAmount = capacity;
    }

    public String getProduct() {
        return product;
    }

    public int getPrice(){
        return price;
    }

    public int getCurrAmount(){
        return currAmount;
    }

    public void setCurrAmount(int num){
        currAmount = num;
    }

    // return if the product can be vended with the given amount of money.
    public boolean canVend(int money){
        if(money>=price) return true;
        return false;
    }

    // return if the product is actually vended.
    public boolean vend(int money){
        if(canVend(money)){
            int most = money/price;
            if(most<= currAmount){
                currAmount-=most;
                return true;
            }else return false;
        }
        else return false;
    }
}
