import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the NumStack class.
 * 
 * @author zhac109
 */
class TestNumStack {

  public NumStack stack;

  Entry entry;

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
   * @throws BadTypeException if the top entry of the stack is not a number.
   */
  
  @Test
  void testPop() throws BadTypeException {
    stack.push(i);
    assertEquals(stack.pop(), 5, "The value popped should be the last value pushed onto the stack");
  }
  
  /**
   * Test 3. Tests that the isEmpty method works.
   */
  @Test
  void testIsEmpty() {
    assertTrue(stack.isEmpty());
    stack.push(i);
    assertFalse(stack.isEmpty());
  }

}
