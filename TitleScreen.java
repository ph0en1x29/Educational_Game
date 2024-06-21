import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TitleScreen extends JFrame {
    public TitleScreen() {
        this.setTitle("Title Screen");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        JLabel titleLabel = new JLabel("Welcome to the Kids Learning Quiz!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel);
        JButton shapesButton = new JButton("Start Shapes Quiz");
        shapesButton.addActionListener(e -> {
            this.dispose();
            QuizQuestion.initializeQuestions("shapes");
        });
        panel.add(shapesButton);
        JButton colorsButton = new JButton("Start Colors Quiz");
        colorsButton.addActionListener(e -> {
            this.dispose();
            QuizQuestion.initializeQuestions("colors");
        });
        panel.add(colorsButton);
        JButton numbersButton = new JButton("Start Numbers Quiz");
        numbersButton.addActionListener(e -> {
            this.dispose();
            QuizQuestion.initializeQuestions("numbers");
        });
        panel.add(numbersButton);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);
        this.add(panel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TitleScreen::new);
    }
}
