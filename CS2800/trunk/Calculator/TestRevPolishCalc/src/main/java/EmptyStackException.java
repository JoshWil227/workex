
/**
 * This exception occurs when an empty stack has its pop method called on it.
 * 
 * @author zhac109
 */
public class EmptyStackException extends Exception{

  /**
   * The unique serial number of this Exception.
   */
  private static final long serialVersionUID = 244490188855298564L;
  
  /**
   * The message output to the terminal when this exception is thrown.
   */

  public String errorMessage;

  /**
   * Exception thrown when a method is called from the wrong type.
   * 
   * @param e the throwable needed to throw the exception.
   */
  public EmptyStackException(Throwable e) {
    super(e);
    errorMessage = "This stack is empty.";
  }

  /**
   * Returns the Error Message.
   */

  public String getMessage() {
    return errorMessage;
  }
}
