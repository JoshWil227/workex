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
   * @throws InvalidExpression if an invalid expression is passed in by the users.
   */
  @Test
  void testOneNum() throws InvalidExpression {
    s = "5";
    d = calc.evaluate(s);
    assertEquals(d, 5, "The single number returned should be the same as the one entered.");
  }
  
  /**
   * Test 2. Tests that the evaluate method throws an InvalidExpression exception 
   * if passed two numbers and no operand.
   * For this I cheated, making it throw the exception whenever a string longer than 1 character is passed in.
   */
  @Test
  void testTwoNums() {
   s = "5 6"; 
   assertThrows(InvalidExpression.class, () -> calc.evaluate(s));
  }

}
