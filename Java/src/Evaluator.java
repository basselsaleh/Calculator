import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import java.util.Stack;

public interface Evaluator {

    public double evaluate(String expr) throws Exception;
}

class JSEngineEvaluator implements Evaluator {

    private ScriptEngineManager manager;
    private ScriptEngine engine;
    
    public JSEngineEvaluator() {
        manager = new ScriptEngineManager();
        engine = manager.getEngineByName("JavaScript");
        System.out.println(engine);
    }

    public double evaluate(String expr) throws ScriptException {
        return (double) engine.eval(expr);
    }
}

class CustomEvaluator implements Evaluator {

    public double evaluate(String expr) {
        // parse the expression and evaluate
        Stack<Double> numbers = new Stack<Double>();
        Stack<Character> operators = new Stack<Character>();

        char[] chars = expr.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];
            if(currentChar >= '0' && currentChar <= '9') {
                String num = "";
                while(i < chars.length && (chars[i] >= '0' && chars[i] <= '9' || chars[i] == '.'))
                    num += chars[i++];
                numbers.push(Double.parseDouble(num));
                i--;
            }
            else if(currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
                while(!operators.empty() && hasPrecedence(currentChar, operators.peek())) {
                    double result = apply(operators.pop(), numbers.pop(), numbers.pop());
                    numbers.push(result);
                }
                operators.push(currentChar);
            }
        }
        // apply remaining operations
        while(!operators.empty()) {
            double result = apply(operators.pop(), numbers.pop(), numbers.pop());
            numbers.push(result);
        }

        return numbers.pop();
    }

    private boolean hasPrecedence(char op1, char op2) {
        return !((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'));
    }

    private double apply(char op, double b, double a) throws ArithmeticException {
        switch(op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if(b == 0.0) throw new ArithmeticException("Can't divide by zero");
                return a / b;
        }
        return 0.0;
    }
}