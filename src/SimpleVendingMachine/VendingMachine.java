package SimpleVendingMachine;

import java.util.ArrayList;
import java.util.List;

//This vending machine takes only quarters.
//
//It should be able to list the inventories inside, even sold out.
//
//It should tell user what can he/she buy with the inserted quarters,
//      but may not need to limit the product they buy.

// always take all the quarters at a time.
// if refund right away, only return the quarters, not money pool
// once start buying,put quarter into the money pool

public class VendingMachine {
    private List<Inventory> list;
    private int money = 0;
    private int moneyOfQuarter = 0;
    public VendingMachine(List<Inventory> invs){
        list = invs;
    }

    public int takeQuarter(){
        moneyOfQuarter+=25;
        return moneyOfQuarter;
    }

    // return all the product names in the machine,
    // even it has been sold out.
    public List<String> listInventory(){
        List<String> productList = new ArrayList<>();
        for(Inventory i: list){
            productList.add(i.getProduct());
        }
        return productList;
    }

    // return all the buyable product names based on the money
    // and inventory status.
    public List<String> listCanBuy() {
        money += moneyOfQuarter;
        moneyOfQuarter = 0;
        List<String> buyableNames = new ArrayList<>();
        for(Inventory i : list){
            boolean canBuyOne =
                    (i.canVend(money)&&
                            i.getCurrAmount()>0)?true:false;
            if(canBuyOne)buyableNames.add(i.getProduct());
        }
        return buyableNames;

    }

    // return the change, note there is no guarantee
    // that the product has not been sold out.
    public int vend(String product) {
        money += moneyOfQuarter;
        moneyOfQuarter = 0;
        for(Inventory i : list){
            if(i.getProduct().equals(product)){
                if(i.getCurrAmount()*i.getPrice() <=money){
                    money-=i.getCurrAmount()*i.getPrice();
                    i.setCurrAmount(0);
                }else{
                    int amount = money / i.getPrice();
                    money-= amount * i.getPrice();
                    i.setCurrAmount(i.getCurrAmount()-amount);
                }
            }
        }
        return money;
    }

    // return the money fed by the current customer.
    public int refund() {

        int refund = moneyOfQuarter;
        moneyOfQuarter = 0;
        return refund;

    }
}
