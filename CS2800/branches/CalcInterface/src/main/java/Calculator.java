
/**
 * The interface for the calculator, which both RevPolishCalc and StandardCalc will implement.
 * 
 * @author zhac109
 */
public interface Calculator {
  /**
   * Resolves the expression entered by the user.
   * 
   * @return the answer to the expression.
   * @throws InvalidExpression if there is a syntax error in the passed expression.
   * @throws BadTypeException if the method tries to pop a symbol instead of a number.
   */
  public double evaluate(String what) throws InvalidExpression, BadTypeException;
}
