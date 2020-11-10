
/**
 * The Stack handling all of the numbers entered by the user.
 * 
 * @author zhac109
 */
public class NumStack {
  private Stack numStack = new Stack();

  Entry entry;

  int size = 0;

  /**
   * Pushes a float onto the stack.
   * 
   * @param i The float to be pushed onto the stack.
   */
  
  public void push(float i) {
    entry = new Entry(i);
    numStack.push(entry);
    size = numStack.size;
  }


}
