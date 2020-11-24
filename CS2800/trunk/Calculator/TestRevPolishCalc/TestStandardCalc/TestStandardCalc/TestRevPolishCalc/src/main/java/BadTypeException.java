
/**
 * This Exception occurs when a method is called on an object with a Type not compatible with that
 * method.
 * 
 * @author zhac109
 */
public class BadTypeException extends Exception {

  /**
   * The unique serial number of this Exception.
   */
  private static final long serialVersionUID = -3348082585727456416L;

  /**
   * The message output to the terminal when this exception is thrown.
   */

  public String errorMessage;

  /**
   * Exception thrown when a method is called from the wrong type.
   * 
   * @param e the throwable needed to throw the exception.
   */
  public BadTypeException(Throwable e) {
    super(e);
    errorMessage = "Wrong type for the method that was called.";
  }

  /**
   * Returns the Error Message.
   */

  public String getMessage() {
    return errorMessage;
  }
}
