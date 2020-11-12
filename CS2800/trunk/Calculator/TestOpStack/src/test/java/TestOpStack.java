import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestOpStack {

  public OpStack stack = new OpStack();
  
  Symbol symbol;
  
  /**
   * Test 1. Tests that the push method works
   */
  
  @Test
  void testPush() {
    symbol = Symbol.LEFT_BRACKET;
    stack.push(symbol);
    assertEquals(stack.size, 1, "After one entry has been pushed, the size should be 1.");
  }

}
