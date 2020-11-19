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
   * Test 1. Tests that the evaluate method returns the number passed into it when only one number
   * is entered. For this I made it parse the string into a double and return said double.
   * 
   * @throws InvalidExpression if an invalid expression is passed in by the users.
   * @throws BadTypeException if the method tries to push a symbol instead of a number.
   */
  @Test
  void testOneNum() throws InvalidExpression, BadTypeException {
    s = "5";
    d = calc.evaluate(s);
    assertEquals(d, 5, "The single number returned should be the same as the one entered.");
  }

  /**
   * Test 2. Tests that the evaluate method throws an InvalidExpression exception if passed two
   * numbers and no symbol. For this I cheated, making it throw the exception whenever a string
   * longer than 1 character is passed in.
   */
  @Test
  void testTwoNums() {
    s = "5 6";
    assertThrows(InvalidExpression.class, () -> calc.evaluate(s));
  }

  /**
   * Test 3. Tests that if two numbers and the addition symbol are passed in, the evaluate method
   * will add those two numbers together. For this I made it so that it evaluate immediately returns
   * if only one number is entered. I also made evaluate split the string, separating it by spaces,
   * pushing the numbers into a numstack and ignoring any operands.
   * 
   * @throws InvalidExpression if only two numbers are passed in.
   * @throws BadTypeException if the method tries to pusg a symbol instead of a number.
   */
  @Test
  void testJustAdd() throws InvalidExpression, BadTypeException {
    s = "5 6 +";
    d = calc.evaluate(s);
    assertEquals(d, 11, "The answer should be the sum of the two numbers.");
  }


  /**
   * Test 4. Tests that different symbol being entered make evaluate() perform the correct
   * operation. For this I made evaluate() put the symbols in a separate string, and created a
   * switch statement that performed the action relating to the symbol in the string on the two
   * numbers in the stack.
   * 
   * @throws InvalidExpression if only two numbers and no symbol are entered.
   * @throws BadTypeException if the method tries to push a symbol instead of a number.
   */
  @Test
  void testSymbols() throws InvalidExpression, BadTypeException {
    s = "8 4 /";
    d = calc.evaluate(s);
    assertEquals(d, 2, "The answer should be the first number divided by the second.");
    s = "8 4 *";
    d = calc.evaluate(s);
    assertEquals(d, 32, "The answer should be the first number mulitplied by the second.");
    s = "8 4 -";
    d = calc.evaluate(s);
    assertEquals(d, 4, "The answer should be the first number minus the second.");
  }


  /**
   * Test 5. Tests that the evaluate method can handle multiple operators and numbers. 
   * 
   * @throws InvalidExpression if the expression contains a syntax error.
   * @throws BadTypeException if the method tries to push a symbol onto the numstack.
   */
  @Test
  void testMultipleSymbols() throws InvalidExpression, BadTypeException {
    s = "4 2 + 7 2 - *";
    d = calc.evaluate(s);
    assertEquals(d, 30,
        "The answer should be returned after being corectly resolved in reverse polish notation.");
  }

}
