import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests the StandardCalc class.
 * 
 * @author zhac109
 */
class TestStandardCalc {

  StandardCalc calc = new StandardCalc();
  String s;
  Double d;
  
  /**
   * Test 1. Tests that the calculator returns single-number inputs as they were entered.
   * For this I created the evaluate method and had it return the number entered.
   */
  @Test
  void testOneNum() {
    s = "5";
    d = calc.evaluate(s);
    assertEquals(d, 5, "The value returned should be the value entered.");
  }
  
  
  /**
   * Test 2. Tests that the calculator correctly handles single-clause infix expressions.
   */
  @Test
  void testOneOp() {
    s = "5 + 5";
    calc.evaluate(s);
    assertEquals(d, 10, "The value returned should be the result of the expression entered.");
  }

}
