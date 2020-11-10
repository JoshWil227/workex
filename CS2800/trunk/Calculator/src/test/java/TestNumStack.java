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
   * Test 1. Tests that the compositing of the stack class into the numStack works correctly.
   */
  @Test
  void testComposition() {
    stack = new NumStack();
    assertEquals(stack.size(), 0, "The size of the stack should be 0");
  }

}
