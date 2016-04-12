package exceptions;

public class InvalidParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2572113382467414214L;

	// Constructors from superclass
	public InvalidParameterException() {
	}

	public InvalidParameterException(String arg0) {
		super(arg0);
	}

	public InvalidParameterException(Throwable arg0) {
		super(arg0);
	}

	public InvalidParameterException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidParameterException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
