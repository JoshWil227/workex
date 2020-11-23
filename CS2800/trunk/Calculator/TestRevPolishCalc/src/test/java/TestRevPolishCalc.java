import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.*;

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
   * @throws BadTypeException if the method tries to push a symbol instead of a number.
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
   * Test 5. Tests that the evaluate method can handle multiple operators and numbers. For this I
   * moved the switch statement and the popping of numbers out to have operations performed on them
   * inside the reading loop so that whenever a symbol is read, that function is performed on the
   * most recent two numbers in the stack.
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


  /**
   * Test 6. Tests that the evaluate method correctly handles too many symbols being entered by
   * throwing an InvalidExpression exception. For this I inserted an if statement when a symbol is
   * read, checking that there are two numbers in the stack to have the operation performed on.
   * 
   * @throws InvalidExpression if there is a syntax error in the expression entered.
   * @throws BadTypeException if the method attempts to push a symbol onto the numstack.
   */

  @Test
  void testTooManySymbols() throws InvalidExpression, BadTypeException {
    s = "4 2 + -";
    assertThrows(InvalidExpression.class, () -> calc.evaluate(s),
        "Should throw invalidexpression exception.");
  }

  /**
   * Test 7. Tests that the evaluate method correctly handles numbers with multiple digits,
   * splitting them correctly and dealing with large numbers. For this I changed the check for only
   * a single number entered to check for only one number in the array instead of checking the
   * number of characters in the string. Also changed how numbers are pushed onto the stack from the
   * first character of that array item to the whole string of that element. Changed the checking of
   * no symbols to use a boolean set true when the first symbol is read. If not true at end of
   * string
   * 
   * @throws InvalidExpression if there is a syntax error in the expression entered.
   * @throws BadTypeException if the method attempts to push a symbol onto the numstack.
   */
  @Test
  void testMultiDigitNums() throws InvalidExpression, BadTypeException {
    s = "10";
    d = calc.evaluate(s);
    assertEquals(d, 10, "The number returned should be the number entered.");
    s = "100";
    d = calc.evaluate(s);
    assertEquals(d, 100, "The number returned should be the number entered.");
    s = "10 10 *";
    d = calc.evaluate(s);
    assertEquals(d, 100, "The number returned should be the result of the operation entered.");
    s = "10 123456789 *";
    d = calc.evaluate(s);
    assertEquals(d, 1234567890,
        "The number returned should be the result of the operation entered.");
  }


  /**
   * Test 8. Tests that the evaluate method throws an InvalidExpression exception when an infix
   * expression is entered. For this I did not need to do anything, as my protection against too
   * many symbols being entered already caught it.
   * 
   * @throws InvalidExpression if there is a syntax error in the expression entered.
   * @throws BadTypeException if the method attempts to push a symbol onto the numstack.
   */

  @Test
  void testInfix() throws InvalidExpression, BadTypeException {
    s = "10 + 10";
    assertThrows(InvalidExpression.class, () -> calc.evaluate(s),
        "Passing an expression in infix notation should return an invalid expression exception.");
  }


  /**
   * Test 9. Tests that dividing by zero throws an InvalidExpression exception. Added an if
   * statement to stipulate that if the second number is zero, throw an InvalidExpression exception.
   * 
   * @throws InvalidExpression if there is a syntax error in the expression entered.
   * @throws BadTypeException if the method attempts to push a symbol onto the numstack.
   */

  @Test
  void testDivZero() throws InvalidExpression, BadTypeException {
    s = "5 0 /";
    assertThrows(InvalidExpression.class, () -> calc.evaluate(s),
        "Dividing by zero should throw an invalid expression exception.");
  }


  /**
   * Test 10. Tests that the evaluate method deals with arithmetic overflow correctly by throwing an
   * InvalidExpression.
   * 
   * @throws InvalidExpression if there is a syntax error in the expression entered.
   * @throws BadTypeException if the method attempts to push a symbol onto the numstack.
   */
  @Test
  void testOverflow() throws InvalidExpression, BadTypeException {
    s = "1000000000000000000000000000 * 100000000000000000000000000000";
    assertThrows(InvalidExpression.class, () -> calc.evaluate(s),
        "Arithmetic overflow should throw an invalid expression exception.");
  }

}
