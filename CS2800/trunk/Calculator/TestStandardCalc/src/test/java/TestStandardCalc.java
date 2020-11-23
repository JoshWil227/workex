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
   * Test 1. Tests that the calculator returns single-number inputs as they were entered. For this I
   * created the evaluate method and had it return the number entered.
   * 
   * @throws InvalidExpression if an invalid expression is passed in by the users.
   * @throws BadTypeException if the method tries to push a symbol instead of a number.
   */
  @Test
  void testOneNum() throws InvalidExpression, BadTypeException {
    s = "5";
    d = calc.evaluate(s);
    assertEquals(d, 5, "The value returned should be the value entered.");
  }


  /**
   * Test 2. Tests that the calculator correctly handles single-clause infix expressions. For this I
   * made the evaluate method split the expression, then add the numbers to a new string and the
   * operator to a different string, which is then added on at the end. That string is then passed
   * into the RevPolishCalc's evaluate method.
   * 
   * @throws InvalidExpression if an invalid expression is passed in by the users.
   * @throws BadTypeException if the method tries to push a symbol instead of a number.
   */
  @Test
  void testOneOp() throws InvalidExpression, BadTypeException {
    s = "5 + 5";
    d = calc.evaluate(s);
    assertEquals(d, 10, "The value returned should be the result of the expression entered.");
  }
  
  
  /**
   * Test 3. Tests that the calculator correctly handles two-clause infix expressions.
   * 
   * @throws InvalidExpression if an invalid expression is passed in by the users.
   * @throws BadTypeException if the method tries to push a symbol instead of a number.
   */
  @Test
  void testTwoOp() throws InvalidExpression, BadTypeException {
    s = "5 * 5 + 5";
    d = calc.evaluate(s);
    assertEquals(d, 30, "The value returned should be the result of the expression.");
    s = "5 * 5 - 5";
    d = calc.evaluate(s);
    assertEquals(d, 20, "The value returned should be the result of the expression.");
    s = "5 * 5 / 5";
    d = calc.evaluate(s);
    assertEquals(d, 5, "The value returned should be the result of the expression.");
  }

}
