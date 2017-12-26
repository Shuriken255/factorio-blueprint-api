package ua.kiev.shuriken.blueprint;

/**
 * This type of exception is used to be thrown in case if blueprint can't be created.
 */
public class BlueprintException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public BlueprintException(String message) {
		super(message);
	}
}
