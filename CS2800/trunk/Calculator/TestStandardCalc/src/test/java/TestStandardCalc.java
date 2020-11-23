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
   */
  @Test
  void testOneNum() {
    StandardCalc calc = new StandardCalc();
    String d = "5";
    calc.evaluate(d);
  }

}
