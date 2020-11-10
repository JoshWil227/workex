import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests the NumStack class.
 * 
 * @author zhac109
 */
class TestNumStack {

  public NumStack stack;

  Entry entry;

  /**
   * Test 1. Tests the push method works.
   */
  
  @Test
  void testPush() {
    stack = new NumStack();
    float i = 5;
    stack.push(i);
    assertEquals(stack.size, 1, "The size of the stack should be 1");
  }

}
