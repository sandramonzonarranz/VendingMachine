package exceptions;

/**
 * Exception when all existences of Vending Machine are sold.
 */
public class AllSoldException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;
	public static String MESSAGE = "Sold all existences of ";

    public AllSoldException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }

}