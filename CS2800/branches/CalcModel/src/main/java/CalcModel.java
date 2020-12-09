
/**
 * This class is the model in which the controller will process information.
 * 
 * @author zhac109
 */
public class CalcModel {
  Calculator revPolishCalc = new RevPolishCalc();
  Calculator standardCalc = new StandardCalc();

  /**
   * Evaluates the expression passed into it in the notation given.
   * 
   * @param string The expression to be evaluated.
   * @param b The boolean which decides which notation is to be used.
   * @return The answer to the expression,
   * @throws InvalidExpression if an invalid expression is passed in.
   * @throws BadTypeException if the method tries to pop a symbol instead of a number.
   */
  public double evaluate(String string, boolean b) throws InvalidExpression, BadTypeException {
    if (b == true) {
      return standardCalc.evaluate(string);
    } else {
      return revPolishCalc.evaluate(string);
    }
  }
}
