
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
   * @param i the symbol entered by the user.
   */
  
  public void push(Symbol i) {
    entry = new Entry(i);
    opStack.push(entry);
    size = opStack.size;
  }

  /**
   * Returns the symbol on the top of the stack.
   * 
   * @return the symbol on the top of the stack.
   * @throws BadTypeException if the entry on the top of the stack is not a symbol.
   */
  
  public Symbol pop() throws BadTypeException {
    entry = opStack.pop();
    Symbol symbol = entry.getSymbol();
    return symbol;
  }

}
