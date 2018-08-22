package exceptions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Coin;
import model.Product;
import vendingMachine.VendingMachineAPI;
import vendingMachine.VendingMachine;

public class NotEnoughChangeExceptionTest {

    private static VendingMachineAPI vm;

    @Before
    public void init() {
        vm = new VendingMachine();
    }

    @After
    public void tearDown() {
        vm = null;
    }

    @Test(expected = NotEnoughChangeException.class)
    public void notEnoughChangeExceptionTest() {
        for (int i = 0; i < 10; i++) {
            vm.getProductWithPrice(Product.WATER);
            vm.insertCoin(Coin.COIN_0_5);
            vm.insertCoin(Coin.COIN_0_5);
            vm.getProductAndChange();
        }
    }
}
