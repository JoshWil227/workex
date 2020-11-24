import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * This class stores items in a stack.
 * 
 * @author zhac109
 */

public class Stack {

  /**
   * The amount of items in the stack.
   */

  int size = 0;

  /**
   * The entry most recently entered into the stack. Used in the pop method.
   */

  Entry entry;

  /**
   * The array list that holds the items in the stack.
   */

  ArrayList<Entry> entries = new ArrayList<>();



  /**
   * Returns the size of the stack.
   * 
   * @return the size of the stack.
   */
  
  public int size() {
    return size;
  }

  /**
   * Pushes the passed integer on to the stack.
   * 
   * @param entry the entry to be pushed onto the stack.
   */

  public void push(Entry entry) {
    size++; // increase the size of the arraylist
    entries.add(entry); // add the new entry to the arraylist
  }

  /**
   * Removes and returns the item on top of the stack.
   * 
   * @return the item on the top of the stack
   */

  public Entry pop() {
    if (size == 0) { // if stack is empty throw exception
      throw new EmptyStackException();
    }
    size--; // account for difference between arrays starting at 0 and size
    entry = entries.get(size); // retrieve the last entry from arraylist and store it
    entries.remove(size); // remove the last entry from the arraylist
    return entry; // return it
  }

  /**
   * Returns the item on the top of the stack.
   * 
   * @return the item on the top of the stack
   */

  public Entry top() {
    if (size == 0) {
      throw new EmptyStackException();
    }
    return entries.get(size - 1); // Returns size - 1 because the array list starts as 0 but
    // size starts at 1 when not empty. Throws EmptyStackException if the stack has size 0
    // because there is nothing to return and so it would return a negative value.
  }

}
