import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestOpStack {

  public OpStack stack;
  
  Symbol symbol;
  
  /**
   * Test 1. Tests that the push method works
   */
  
  @Test
  void testPush() {
    symbol = Symbol.LEFT_BRACKET;
    stack.push(symbol);
    assertEquals(stack.size, 1, "AFter one entry has been pushed, ")
  }

}
