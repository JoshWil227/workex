
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
   */
  public String pop() {
    return "6";
  }
  
  
}
