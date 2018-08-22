package model;

import java.util.List;

/**
 * Coins accepted by VendingMachine.
 */
public enum Coin {
	
    COIN_0_05(5),  
    COIN_0_1(10), 
    COIN_0_2(20), 
    COIN_0_5(50), 
    COIN_1(100), 
    COIN_2(200);

    private int value;

    private Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static long getTotal(List<Coin> change) {
        long total = 0;
        for (Coin c : change) {
            total = total + c.getValue();
        }
        return total;
    }
}