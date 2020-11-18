
/**
 * Evaluates expressions in reverse polish notation.
 * 
 * @author zhac109
 */

public class RevPolishCalc {
  
  /**
   * Evaluates the expression passed into it in reverse polish notation.
   * @param what the expression.
   * @return the answer of the expression.
   * @throws InvalidExpression if an invalid expression is passed in.
   */
  public double evaluate(String what) throws InvalidExpression {
    if (what.length() >= 2) {
      throw new InvalidExpression(null);
    }
    double ans = Double.parseDouble(what);
    return ans;
  }
}
