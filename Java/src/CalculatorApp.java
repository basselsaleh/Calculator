import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CalculatorApp extends JFrame {

    private JTextField inputField;
    private JPanel numberPanel, extrasPanel;
    private Font font;

    private enum buttonType {INPUT, CLEAR, EVAL};

    public CalculatorApp(int width, int height, int x, int y, int fontSize) {
        super("Calculator App");
        setLayout(new BorderLayout());
        font = new Font("sans-serif", Font.PLAIN, fontSize);

        inputField = new JTextField();
        inputField.setFont(font);
        inputField.setPreferredSize(new Dimension(100, 50));
        add(inputField, BorderLayout.NORTH);

        numberPanel = new JPanel(new GridLayout(4, 3));

        // add number buttons
        for(int i = 1; i <= 9; i++)
            addButtonToNumberPanel(Integer.toString(i), buttonType.INPUT);
        addButtonToNumberPanel(".", buttonType.INPUT);
        addButtonToNumberPanel("0", buttonType.INPUT);
        addButtonToNumberPanel("=", buttonType.EVAL);

        // add extra buttons to extras panel
        extrasPanel = new JPanel(new GridLayout(5, 1));

        // add extra buttons (zero and operators)
        String[] operators = {"+", "-", "*", "/"};
        for(String op: operators)
            addButtonToExtrasPanel(op, buttonType.INPUT);
        addButtonToExtrasPanel("C", buttonType.CLEAR);

        add(numberPanel, BorderLayout.WEST);
        add(extrasPanel, BorderLayout.EAST);

        setSize(width, height);
        setLocation(x, y);
        setVisible(true);
    }

    private void addButtonToNumberPanel(String text, buttonType type) {
        addButtonToPanel(text, type, numberPanel);
    }

    private void addButtonToExtrasPanel(String text, buttonType type) {
        addButtonToPanel(text, type, extrasPanel);
    }

    private void addButtonToPanel(String text, buttonType type, JPanel panel) {
        JButton button = new JButton(text);
        switch(type) {
            case INPUT:
                button.addActionListener(e -> inputField.setText(inputField.getText() + button.getText()));
                break;
            
            case CLEAR:
                button.addActionListener(e -> inputField.setText(""));
                break;
            
            case EVAL:
                button.addActionListener(e -> evaluate());
        }
        button.setFont(font);
        panel.add(button);
    }

    private void evaluate() {
        // parse the input field to get a list of tokens (integer, decimal, or operator)
        String input = inputField.getText();
        ArrayList<Token> tokens = new ArrayList<Token>();
        String operators = "+-*/";

        String currentString = "";
        boolean isDecimal = false;
        for(int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if(operators.contains(Character.toString(currentChar))) {
                if(isDecimal)
                    tokens.add(new Token<Double>(Double.parseDouble(currentString), "decimal"));
                else
                    tokens.add(new Token<Integer>(Integer.parseInt(currentString), "integer"));

                tokens.add(new Token<Character>(currentChar, "operator"));
                currentString = "";
                isDecimal = false;
            }
            else {
                if(currentChar == '.') {
                    isDecimal = true;
                }
                currentString += currentChar;

                if(i == input.length() - 1) {
                    if(isDecimal)
                        tokens.add(new Token<Double>(Double.parseDouble(currentString), "decimal"));
                    else
                        tokens.add(new Token<Integer>(Integer.parseInt(currentString), "integer"));
                }
            }
        }

        // build an operation graph from the tokens
        for(Token token: tokens) {
            System.out.println(token);
        }
    }

    private class Token<T> {
        private T value;
        private String type;

        public Token(T value, String type) {
            this.value = value;
            this.type = type;
        }

        public T getValue() {
            return value;
        }

        public String toString() {
            return "type: " + type + "\tvalue: " + value;
        }
    }

    public static void main(String[] args) {
        // args: width, height, x, y, fontSize
        new CalculatorApp(300, 400, 400, 200, 20);
    }
}
