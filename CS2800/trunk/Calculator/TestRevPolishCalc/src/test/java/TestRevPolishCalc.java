import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The test class for the RevPolishCalc class.
 * 
 * @author zhac109
 */
class TestRevPolishCalc {

  private RevPolishCalc calc = new RevPolishCalc();
  
  String s;
  
  double d;
  
  /**
   * Test 1. Tests that the evaluate method returns the number passed into it when only one number is entered.
   * For this I made it parse the string into a double and return said double.
   */
  @Test
  void testOneNum() {
    s = "5";
    d = calc.evaluate(s);
    assertEquals(d, 5, "The single number returned should be the same as the one entered.");
  }
  
  /**
   * Test 2. Tests that the evaluate method throws an InvalidExpression exception if passed two numbers and no operand.
   */
  @Test
  void testTwoNums() {
   s = "5 6"; 
   assertThrows(InvalidExpression.class, () -> calc.evaluate(s));
  }

}
