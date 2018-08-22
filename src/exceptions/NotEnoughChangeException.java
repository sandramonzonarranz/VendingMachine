package exceptions;

/**
 * Exception when not enough change in Vending Machine
 */
public class NotEnoughChangeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;
	public static String MESSAGE = "Not Enough Change";

    public NotEnoughChangeException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }

}