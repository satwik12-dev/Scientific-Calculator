import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private final DecimalFormat df = new DecimalFormat("0.######");

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 18));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/", "sin",
            "4", "5", "6", "*", "cos",
            "1", "2", "3", "-", "tan",
            "0", ".", "=", "+", "√",
            "log", "x²", "1/x", "C", "!"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            switch (command) {
                case "C" -> display.setText("");
                case "=" -> display.setText(evaluate(display.getText()));
                case "√" -> display.setText(df.format(Math.sqrt(Double.parseDouble(display.getText()))));
                case "sin" -> display.setText(df.format(Math.sin(Math.toRadians(Double.parseDouble(display.getText())))));
                case "cos" -> display.setText(df.format(Math.cos(Math.toRadians(Double.parseDouble(display.getText())))));
                case "tan" -> display.setText(df.format(Math.tan(Math.toRadians(Double.parseDouble(display.getText())))));
                case "log" -> display.setText(df.format(Math.log10(Double.parseDouble(display.getText()))));
                case "x²" -> display.setText(df.format(Math.pow(Double.parseDouble(display.getText()), 2)));
                case "1/x" -> display.setText(df.format(1 / Double.parseDouble(display.getText())));
                case "!" -> display.setText(df.format(factorial(Integer.parseInt(display.getText()))));
                default -> display.setText(display.getText() + command);
            }
        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    private String evaluate(String expression) {
        try {
            return df.format(new javax.script.ScriptEngineManager().getEngineByName("JavaScript").eval(expression));
        } catch (Exception e) {
            return "Error";
        }
    }

    private int factorial(int n) {
        return (n == 0 || n == 1) ? 1 : n * factorial(n - 1);
    }

    public static void main(String[] args) {
        new ScientificCalculator();
    }
}
