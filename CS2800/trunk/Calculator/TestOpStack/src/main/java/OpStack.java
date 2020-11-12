
/**
 * The stack handling all of the symbols entered by the user.
 * 
 * @author zhac109
 */
public class OpStack {

  private Stack opStack = new Stack();
  
  Entry entry;
  
  int size = 0;
  
  /**
   * Pushes a symbol onto the stack.
   * 
   * @param symbol the symbol entered by the user.
   */
  
  public void push(Symbol symbol) {
    entry = new Entry(symbol);
    opStack.push(entry);
    size = opStack.size;
  }

}
