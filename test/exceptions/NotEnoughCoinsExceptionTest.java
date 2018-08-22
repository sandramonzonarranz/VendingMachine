package exceptions;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Coin;
import model.Product;
import vendingMachine.VendingMachine;
import vendingMachine.VendingMachineAPI;

public class NotEnoughCoinsExceptionTest {
	
    private static VendingMachineAPI vm;

    @Before
    public void init() {
        vm = new VendingMachine();
    }

    @After
    public void tearDown() {
        vm = null;
    }

    @Test(expected = NotEnoughCoinsException.class)
    public void buyProductRefoundTest() {
        long price = vm.getProductWithPrice(Product.WATER);
        assertEquals(Product.WATER.getPrice(), price);
        vm.insertCoin(Coin.COIN_0_5);
        vm.getProductAndChange();
    }
}
