import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the stack class.
 * 
 * @author zhac109 This programs tests the Stack class.
 */

public class TestStack {

  /**
   * The stack of items.
   */

  public Stack stack;

  Entry entry;

  /**
   * Initialises a new stack before each test.
   */

  @BeforeEach
  public void setup() {
    stack = new Stack();
    entry = new Entry(5);
  }

  /**
   * Test 1. Tests to see if the size class works and is set to size of zero. For this I created the
   * size method and faked the size by making it return 0.
   */

  @Test
  void testSize() {
    assertEquals(stack.size(), 0, "Test to see if the stack has size zero");
  }

  /**
   * Test 2. Tests to see if the push method works. For this I created the push method and faked the
   * result by returning 1.
   */
  @Test
  void testPush() {
    stack.push(entry);
    assertEquals(stack.size(), 1, "Test to see if pushing an integer works");
  }

  /**
   * Test 3. Tests to see if popping an empty stack returns an EmptyStackException. For this I made
   * the pop method and faked the result by making it decrease size by 1. The EmptyStackException
   * was also added.
   */

  @Test
  void testEmptyPop() {
    EmptyStackException e =
        assertThrows(EmptyStackException.class, () -> stack.pop(), "Impossible to pop empty stack");
  }

  /**
   * Test 4. Tests to see if popping actually returns numbers. For this I created value variable,
   * set it to the argument of the pop function and returned it.
   */

  @Test
  void testPushPop() {
    stack.push(entry);
    assertEquals(stack.pop(), entry, "Pushing and then popping the entry should return the entry");
    assertEquals(stack.size(), 0,
        "Pushing the entry and then popping it should return an empty stack");
    assertThrows(EmptyStackException.class, () -> stack.pop(),
        "Pushing and then popping twice should throw an exception");
  }

  /**
   * Test 5. Tests to see if the stack stores values pushed onto it beyond the top one. For this I
   * created the array "values" and assigned the pushed value to the point in the array that matched
   * the size of the stack.
   */

  @Test
  void testPushTwice() {
    stack.push(entry);
    Entry entryTwo = new Entry(Symbol.RIGHT_BRACKET);
    stack.push(entryTwo);
    assertEquals(stack.size(), 2, "Pushing twice should give a stack size of 2");
    assertEquals(stack.pop(), entryTwo,
        "Pushing twice and then popping should return the most recent entry pushed");
    assertEquals(stack.pop(), entry,
        "Pushing twice and the popping a second time should return the first entry pushed");
    assertEquals(stack.size(), 0, "Pushing twice and popping twice should give an empty stack");
    assertThrows(EmptyStackException.class, () -> stack.pop(),
        "Pushing and then popping twice should throw an exception");
  }

  /**
   * Test 6. Tests to see if an unlimited number of items can be added to the stack. For this I
   * changed the values array to an array list and altered the push and pop methods to accommodate
   * this by utilising the add and remove methods.
   * 
   */

  @Test
  void testPushMany() {
    for (int i = 0; i < 500; i++) {
      Entry entry = new Entry(i);
      stack.push(entry);
    }
    Entry entryTwo = new Entry(499);
    assertEquals(stack.pop(), entryTwo,
        "There should not be a limit to the number of items in stack");
  }


  /**
   * Test 7. Tests to see if the top method returns the top of the stack without removing it. For
   * this I created the top method and had it only return the item on the top of the stack.
   */

  @Test
  void testTop() {
    stack.push(entry);
    Entry entryTwo = new Entry(Symbol.RIGHT_BRACKET);
    stack.push(entryTwo);
    assertEquals(stack.top(), entryTwo, "The top of the stack should be returned");
    assertEquals(stack.pop(), entryTwo,
        "Top of the stack should not have been changed by the top method");
  }

}
