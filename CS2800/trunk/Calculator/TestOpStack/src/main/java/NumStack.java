
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

  public void push(double i) {
    entry = new Entry(i);
    numStack.push(entry);
    size = numStack.size;
  }

  /**
   * Returns the float on the top of the stack.
   * 
   * @return the last floated pushed to the stack.
   * @throws BadTypeException if the last entry pushed to the stack was not a number.
   */

  public double pop() throws BadTypeException {
    entry = numStack.pop();
    if (entry.getType() == "Number") {
      double val = entry.getValue();
      size = numStack.size();
      return val;
    } else {
      throw new BadTypeException(null);
    }
  }

  /**
   * If the stack is empty, returns true. Else returns false.
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
