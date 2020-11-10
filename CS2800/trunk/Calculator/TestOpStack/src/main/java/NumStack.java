
/**
 * The Stack handling all of the numbers entered by the user.
 * 
 * @author zhac109
 */
public class NumStack {
  private Stack stack = new Stack();
  
  Entry entry;
  
  /**
   * Returns the size of the stack.
   * 
   * @return the size of the stack
   */
  public int size() {
    return stack.size();
  }
  
}
