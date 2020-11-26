/**
 * Evaluates expressions in reverse polish notation.
 * 
 * @author zhac109
 */

public class RevPolishCalc {

  NumStack values = new NumStack();

  double ans;

  /**
   * Evaluates the expression passed into it in reverse polish notation.
   * 
   * @param what the expression.
   * @return the answer of the expression.
   * @throws InvalidExpression if an invalid expression is passed in.
   * @throws BadTypeException if the method tries to pop a symbol instead of a number.
   */
  public double evaluate(String what) throws InvalidExpression, BadTypeException {
    double num1;
    double num2;
    boolean hasSym = false;
    String[] inputs = what.split(" ");
    if (inputs.length == 1) {
      return Double.parseDouble(inputs[0]);
    }
    for (int i = 0; i < inputs.length; i++) {
      String token = inputs[i];
      switch (token) {
        case "+":
          if (values.size >= 2) {
            num1 = values.pop();
            num2 = values.pop();
            ans = num1 + num2;
            values.push(ans);
            hasSym = true;
          } else {
            throw new InvalidExpression(null);
          }
          break;
        case "-":
          if (values.size >= 2) {
            num1 = values.pop();
            num2 = values.pop();
            ans = num2 - num1;
            values.push(ans);
            hasSym = true;
          } else {
            throw new InvalidExpression(null);
          }
          break;
        case "/":
          if (values.size >= 2) {
            num1 = values.pop();
            num2 = values.pop();
            if (num1 == 0) {
              throw new InvalidExpression(null);
            }
            ans = num2 / num1;
            values.push(ans);
            hasSym = true;
          } else {
            throw new InvalidExpression(null);
          }
          break;
        case "*":
          if (values.size >= 2) {
            num1 = values.pop();
            num2 = values.pop();
            ans = num1 * num2;
            values.push(ans);
            hasSym = true;
          } else {
            throw new InvalidExpression(null);
          }
          break;
        default:
          try {
            double d = Double.parseDouble(token);
            values.push(d);
          } catch (NumberFormatException e) {
            System.out.print("Invalid Expression.");
          }
      }
    }
    if (!hasSym) {
      throw new InvalidExpression(null);
    }
    if (ans > Double.MAX_VALUE || ans < -Double.MAX_VALUE) {
      throw new InvalidExpression(null);
    }
    ans = values.pop();
    return ans;
  }
}
