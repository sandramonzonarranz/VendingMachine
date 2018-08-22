package vendingMachine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.AllSoldException;
import model.Coin;
import model.Product;


public class VendingMachineTest {

    private static VendingMachineAPI vm;

    @Before
    public void init() {
        vm = new VendingMachine();
    }

    @After
    public void tearDown() {
        vm = null;
    }
    
    @Test
    public void totalRefundTest() {
        vm.insertCoin(Coin.COIN_0_05);
        vm.insertCoin(Coin.COIN_0_1);
        vm.insertCoin(Coin.COIN_0_2);
        vm.insertCoin(Coin.COIN_0_5);
        vm.insertCoin(Coin.COIN_1);
        vm.insertCoin(Coin.COIN_2);
        assertEquals(385, Coin.getTotal(vm.refund()));
    }
    
    @Test(expected = AllSoldException.class)
    public void resetTest() {
        vm = new VendingMachine();
        vm.reset();
        vm.getProductWithPrice(Product.WATER);
    }
    
    @Test
    public void buyProduct0RefoundTest() {
        long price = vm.getProductWithPrice(Product.WATER);
        assertEquals(Product.WATER.getPrice(), price);
        vm.insertCoin(Coin.COIN_0_5);
        vm.insertCoin(Coin.COIN_0_2);
        vm.insertCoin(Coin.COIN_0_2);
        Pair<Product, List<Coin>> bucket = vm.getProductAndChange();
        Product product = bucket.getKey();
        List<Coin> change = bucket.getValue();
        assertEquals(Product.WATER, product);
        assertTrue(change.isEmpty());
    }

    @Test
    public void buyProductRefoundTest() {
        long price = vm.getProductWithPrice(Product.PEPSI);
        assertEquals(Product.PEPSI.getPrice(), price);
        vm.insertCoin(Coin.COIN_1);
        vm.insertCoin(Coin.COIN_0_5);
        Pair<Product, List<Coin>> bucket = vm.getProductAndChange();
        Product product = bucket.getKey();
        List<Coin> change = bucket.getValue();
        assertEquals(Product.PEPSI, product);
        assertTrue(!change.isEmpty());
        assertEquals(5, Coin.getTotal(change));
    }

}
