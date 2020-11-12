import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestOpStack {

  public OpStack stack;
  
  Symbol symbol;
  
  @BeforeEach
  void setup() {
    stack = new OpStack();
  }
  
  /**
   * Test 1. Tests that the push method works. For this I created the Push method.
   */
  
  @Test
  void testPush() {
    symbol = Symbol.LEFT_BRACKET;
    stack.push(symbol);
    assertEquals(stack.size, 1, "After one entry has been pushed, the size should be 1.");
  }

  
  /**
   * Test 2. Tests the pop method works.
   */
  
  @Test
  void testPop() {
    symbol = Symbol.DIVIDE;
    stack.push(symbol);
    assertEquals(stack.pop(), "/", "The symbol popped should be the last symbol pushed.");
  }
}
