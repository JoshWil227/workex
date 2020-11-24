
/**
 * This enum contains the types of variable an Entry can be.
 * 
 * @author zhac109
 */

public enum Type {
  /**
   * The type for numbers, used when the user enters a number.
   */
  NUMBER("Number"),
  /**
   * The type for Symbols, used when the user enters a symbol.
   */
  SYMBOL("Symbol"),
  /**
   * The type for Strings, used when the user enters a string.
   */
  STRING("String"),
  /**
   * The type for invalid entries, used when the user enters something that is not a number, symbol,
   * or string.
   */
  INVALID("Invalid");

  private String string; // The type to be output

  /**
   * The constructor for the Type enum.
   * 
   * @param s the string containing the type.
   */
  
  private Type(String s) {
    string = s;
  }

  /**
   * Returns the type as a string.
   */
  
  @Override
  public String toString() {
    return string; // returns the type as a string
  }
}
