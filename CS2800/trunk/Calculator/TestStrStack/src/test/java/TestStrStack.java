import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests the StrStack class.
 * 
 * @author zhac109
 */
class TestStrStack {

  StrStack stack = new StrStack();
  
  String string;
  
  /**
   * Test 1. Tests the push method. For this I added the push method.
   */
  
  @Test
  void testPush() {
    string = "6";
    stack.push(string);
    assertEquals(stack.size, 1, "After one entry has been pushed, the size should be 1.");
  }
  
  
  /**
   * Test 2. Tests the pop method.
   */
  
  @Test
  void testPop() {
    string = "6";
    stack.push(string);
    assertEquals(stack.pop(), "6", "The string returned should be the last string pushed.");
  }

}
