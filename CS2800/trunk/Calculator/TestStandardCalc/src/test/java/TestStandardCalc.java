import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests the StandardCalc class.
 * 
 * @author zhac109
 */
class TestStandardCalc {

  /**
   * Test 1. Tests that the calculator returns single-number inputs as they were entered.
   * For this I created the evaluate method and had it return the number entered.
   */
  @Test
  void testOneNum() {
    StandardCalc calc = new StandardCalc();
    String d = "5";
    calc.evaluate(d);
    assertEquals(d, "5", "The value returned should be the value entered.");
  }

}
