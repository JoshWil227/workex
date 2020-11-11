import static org.junit.jupiter.api.Assertions.*;
import java.util.EmptyStackException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the NumStack class.
 * 
 * @author zhac109
 */
class TestNumStack {

  public NumStack stack;

  double i = 5;

  @BeforeEach
  public void setup() {
    stack = new NumStack();
  }

  /**
   * Test 1. Tests the push method works. For this I created and wrote the push method.
   */

  @Test
  void testPush() {
    stack.push(i);
    assertEquals(stack.size, 1, "The size of the stack should be 1");
  }

  /**
   * Test 2. Tests the pop method works. For this I created and wrote the pop method.
   * 
   * @throws BadTypeException if the top entry of the stack is not a number.
   */

  @Test
  void testPop() throws BadTypeException {
    stack.push(i);
    assertEquals(stack.pop(), 5, "The value popped should be the last value pushed onto the stack");
  }

  /**
   * Test 3. Tests that the isEmpty method works. FOr this I created and wrote the IsEmpty method.
   */
  @Test
  void testIsEmpty() {
    assertTrue(stack.isEmpty());
    stack.push(i);
    assertFalse(stack.isEmpty());
  }

  /**
   * Test 4. Tests whether popping an empty stack throws an empty stack or not. Mainly to test
   * whether or not I needed to check for it in the NumStack class or if the Stack class would cover
   * it. For this I did not need to code anything new as the Stack class did cover it.
   * 
   * 
   * @throws BadTypeException if the Entry on the top of the stack is not a number.
   */

  @Test
  void testEmptyPop() throws BadTypeException {
    stack.push(i);
    stack.pop();
    assertThrows(EmptyStackException.class, () -> stack.pop(),
        "Pushing and then popping twice should throw an exception");
  }

}
