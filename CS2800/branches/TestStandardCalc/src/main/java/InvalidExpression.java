
/**
 * This Exception is thrown when an invalid expression (no operand, too many numbers, etc.) is
 * entered into the calculator to the user.
 * 
 * @author zhac109
 */
public class InvalidExpression extends Exception {

  /**
   * The unique serial number of this Exception.
   */
  private static final long serialVersionUID = 7621102332928715513L;

  /**
   * The message output to the terminal when this exception is thrown.
   */

  public String errorMessage;

  /**
   * Exception thrown when a method is called from the wrong type.
   * 
   * @param e the throwable needed to throw the exception.
   */
  public InvalidExpression(Throwable e) {
    super(e);
    errorMessage = "This expression is invalid.";
  }

  /**
   * Returns the Error Message.
   */

  public String getMessage() {
    return errorMessage;
  }
}
