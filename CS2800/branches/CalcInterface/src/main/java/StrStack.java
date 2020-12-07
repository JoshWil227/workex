
/**
 * The stack containing the string of the expression entered by the user.
 * 
 * @author zhac109
 */
public class StrStack {

  Stack strStack = new Stack();

  Entry entry;

  int size = 0;

  /**
   * Pushes the given string onto the stack.
   * 
   * @param str the string entered by the user.
   */

  public void push(String str) {
    entry = new Entry(str);
    strStack.push(entry);
    size = strStack.size();
  }

  /**
   * Returns the string on the top of the stack.
   * 
   * @return the string that was last pushed onto the stack.
   * @throws BadTypeException if the entry on the top of the stack is not a string.
   */

  public String pop() throws BadTypeException {
    entry = strStack.pop();
    if (entry.getType() == "String") {
      String string = entry.getString();
      size = strStack.size();
      return string;
    } else {
      throw new BadTypeException(null);
    }
  }

  /**
   * If the stack is empty, return true. If it is populated, return false.
   * 
   * @return the boolean depending on whether or not the stack is empty.
   */

  public Boolean isEmpty() {
    if (size == 0) {
      return true;
    }
    return false;
  }


}
