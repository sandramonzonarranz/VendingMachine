package exceptions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Coin;
import model.Product;
import vendingMachine.VendingMachine;
import vendingMachine.VendingMachineAPI;

public class AllSoldExceptionTest {

    private static VendingMachineAPI vm;

    @Before
    public void init() {
        vm = new VendingMachine();
    }

    @After
    public void tearDown() {
        vm = null;
    }

    @Test(expected = AllSoldException.class)
    public void allSoldTest() {
        for (int i = 0; i < 11; i++) {
            vm.getProductWithPrice(Product.WATER);
            vm.insertCoin(Coin.COIN_2);
            vm.getProductAndChange();
        }
    }
   


}
