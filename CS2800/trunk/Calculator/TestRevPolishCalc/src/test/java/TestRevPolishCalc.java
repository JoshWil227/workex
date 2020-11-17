import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The test class for the RevPolishCalc class.
 * 
 * @author zhac109
 */
class TestRevPolishCalc {

  private RevPolishCalc calc = new RevPolishCalc();
  
  /**
   * Test 1. Tests that the evaluate method returns the number passed into it when only one number is entered.
   * For this I made it parse the string into a double and return said double.
   */
  @Test
  void testOneNum() {
    String s = "5";
    double d = calc.evaluate(s);
    assertEquals(d, 5, "The single number returned should be the same as the one entered.");
  }

}
