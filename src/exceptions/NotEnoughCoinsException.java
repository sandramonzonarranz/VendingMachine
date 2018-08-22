package exceptions;

/**
 * Exception when not enough coins in Vending Machine
 */
public class NotEnoughCoinsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;
    private long coins;
	public static String MESSAGE = "Not enough coins in vending machine";
    public static String MESSAGE_REMAING_COINS = "Not enough coins, remaining money is ";
    
    public NotEnoughCoinsException(String message, long coins) {
        this.message = message;
        this.coins = coins;
    }

    public long getCoins() {
        return coins;
    }

    @Override
    public String getMessage() {
        return message + coins;
    }

}