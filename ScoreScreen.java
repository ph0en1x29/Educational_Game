import javax.swing.*;
import java.awt.*;

public class ScoreScreen extends JFrame {
    public ScoreScreen(int score) {
        this.setTitle("Score Screen");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel scoreLabel = new JLabel("Your score: " + score + "%");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(scoreLabel, BorderLayout.CENTER);
        JButton returnButton = new JButton("Return to Main Menu");
        returnButton.addActionListener(e -> {
            this.dispose();
            new TitleScreen();
        });
        panel.add(returnButton, BorderLayout.SOUTH);
        this.add(panel);
        this.setVisible(true);
    }
}
