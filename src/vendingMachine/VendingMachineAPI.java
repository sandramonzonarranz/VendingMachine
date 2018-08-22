package vendingMachine;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.Coin;
import model.Product;

/**
 * API for Vending Machine
 */
public interface VendingMachineAPI {

    public void reset();

    public void insertCoin(Coin coin);
    
    public long getProductWithPrice(Product item);

    public Pair<Product, List<Coin>> getProductAndChange();

    public List<Coin> refund();

}
