import javax.swing.*;
import java.awt.*;

public class CalculatorApp extends JFrame {

    private JTextField inputField;
    private JPanel numberPanel, extrasPanel;
    private Font font;
    private Evaluator evaluator;

    private enum buttonType {INPUT, CLEAR, EVAL};

    public CalculatorApp(int width, int height, int x, int y, int fontSize) {
        super("Calculator App");
        setLayout(new BorderLayout());
        font = new Font("sans-serif", Font.PLAIN, fontSize);

        // instantiate Evaluator object
        evaluator = new CustomEvaluator();

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

        // add extra buttons (clear and operators)
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
        double result;
        try {
            result = evaluator.evaluate(inputField.getText());
            inputField.setText(Double.toString(result));
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // args: width, height, x, y, fontSize
        new CalculatorApp(300, 400, 400, 200, 20);
    }
}
