
/**
 * This enum contains all of the symbols that can be entered.
 * 
 * @author zhac109
 */

public enum Symbol {
  /**
   * The Left Bracket Symbol.
   */
  LEFT_BRACKET("("),
  /**
   * The Right Bracket Symbol.
   */
  RIGHT_BRACKET(")"),
  /**
   * The Multiplication Symbol.
   */
  TIMES("*"),
  /**
   * The Division Symbol.
   */
  DIVIDE("/"),
  /**
   * The Addition Symbol.
   */
  PLUS("+"),
  /**
   * The Subtraction Symbol.
   */
  MINUS("-"),
  /**
   * The Symbol for invalid types.
   */
  INVALID("Invalid Type");

  private String string; // The symbol to be output

  /**
   * The constructor for the Symbol enum.
   * 
   * @param s the String containing the symbol.
   */
  
  private Symbol(String s) {
    string = s;
  }

  /**
   *  Returns the symbol as a string.
   */
  
  @Override
  public String toString() {
    return string; // returns the symbol
  }
}
