import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QuizQuestion {
    String question;
    String picture;
    List<Answer> answers;

    public QuizQuestion(String question, String picture, List<Answer> answers) {
        this.question = question;
        this.picture = picture;
        this.answers = answers;
    }

    static class Answer {
        String answer;
        boolean isCorrect;

        public Answer(String answer, boolean isCorrect) {
            this.answer = answer;
            this.isCorrect = isCorrect;
        }
    }

    public static void initializeQuestions(String quizType) {
        List<QuizQuestion> questions = new ArrayList<>();

        if (quizType.equals("shapes")) {
            List<Answer> shapeAnswers1 = new ArrayList<>();
            shapeAnswers1.add(new Answer("Circle", true));
            shapeAnswers1.add(new Answer("Square", false));
            shapeAnswers1.add(new Answer("Triangle", false));
            shapeAnswers1.add(new Answer("Rectangle", false));
            questions.add(new QuizQuestion("What shape is this?", "resources/circle.png", shapeAnswers1));

            List<Answer> shapeAnswers2 = new ArrayList<>();
            shapeAnswers2.add(new Answer("Star", false));
            shapeAnswers2.add(new Answer("Square", false));
            shapeAnswers2.add(new Answer("Circle", false));
            shapeAnswers2.add(new Answer("Triangle", true));
            questions.add(new QuizQuestion("What shape is this?", "resources/triangle.png", shapeAnswers2));

            List<Answer> shapeAnswers3 = new ArrayList<>();
            shapeAnswers3.add(new Answer("Diamond", false));
            shapeAnswers3.add(new Answer("Square", false));
            shapeAnswers3.add(new Answer("Star", true));
            shapeAnswers3.add(new Answer("Circle", false));
            questions.add(new QuizQuestion("What shape is this?", "resources/star.png", shapeAnswers3));

            List<Answer> shapeAnswers4 = new ArrayList<>();
            shapeAnswers4.add(new Answer("Triangle", false));
            shapeAnswers4.add(new Answer("Heart", true));
            shapeAnswers4.add(new Answer("Circle", false));
            shapeAnswers4.add(new Answer("Rectangle", false));
            questions.add(new QuizQuestion("What shape is this?", "resources/heart.png", shapeAnswers4));

        } else if (quizType.equals("colors")) {
            List<Answer> colorAnswers1 = new ArrayList<>();
            colorAnswers1.add(new Answer("Red", true));
            colorAnswers1.add(new Answer("Blue", false));
            colorAnswers1.add(new Answer("Green", false));
            colorAnswers1.add(new Answer("Yellow", false));
            questions.add(new QuizQuestion("What color is this?", "resources/red.png", colorAnswers1));

            List<Answer> colorAnswers2 = new ArrayList<>();
            colorAnswers2.add(new Answer("Blue", true));
            colorAnswers2.add(new Answer("Red", false));
            colorAnswers2.add(new Answer("Green", false));
            colorAnswers2.add(new Answer("Yellow", false));
            questions.add(new QuizQuestion("What color is this?", "resources/blue.png", colorAnswers2));

            // adding more color questions if needed
        } else if (quizType.equals("numbers")) {
            List<Answer> numberAnswers1 = new ArrayList<>();
            numberAnswers1.add(new Answer("4", false));
            numberAnswers1.add(new Answer("2", false));
            numberAnswers1.add(new Answer("3", true));
            numberAnswers1.add(new Answer("5", false));
            questions.add(new QuizQuestion("What number is this?", "resources/three.png", numberAnswers1));

            List<Answer> numberAnswers2 = new ArrayList<>();
            numberAnswers2.add(new Answer("5", true));
            numberAnswers2.add(new Answer("2", false));
            numberAnswers2.add(new Answer("4", false));
            numberAnswers2.add(new Answer("3", false));
            questions.add(new QuizQuestion("What number is this?", "resources/five.png", numberAnswers2));

            List<Answer> numberAnswers3 = new ArrayList<>();
            numberAnswers3.add(new Answer("55", false));
            numberAnswers3.add(new Answer("12", true));
            numberAnswers3.add(new Answer("14", false));
            numberAnswers3.add(new Answer("13", false));
            questions.add(new QuizQuestion("What number is this?", "resources/12.png", numberAnswers3));

            List<Answer> numberAnswers4 = new ArrayList<>();
            numberAnswers4.add(new Answer("1", false));
            numberAnswers4.add(new Answer("13", false));
            numberAnswers4.add(new Answer("4", false));
            numberAnswers4.add(new Answer("11", true));
            questions.add(new QuizQuestion("What number is this?", "resources/11.png", numberAnswers4));

        }

        createAndShowGUI(questions);
    }

    private static void createAndShowGUI(List<QuizQuestion> questions) {
        JFrame frame = new JFrame("Quiz Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel questionLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(questionLabel, BorderLayout.NORTH);

        JLabel pictureLabel = new JLabel();
        pictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(pictureLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        final int[] currentQuestionIndex = {0};
        final int[] correctAnswers = {0};

        loadQuestion(questions, frame, questionLabel, pictureLabel, buttonPanel, currentQuestionIndex, correctAnswers);
    }

    private static void loadQuestion(List<QuizQuestion> questions, JFrame frame, JLabel questionLabel, JLabel pictureLabel, JPanel buttonPanel, int[] currentQuestionIndex, int[] correctAnswers) {
        if (currentQuestionIndex[0] < questions.size()) {
            QuizQuestion currentQuestion = questions.get(currentQuestionIndex[0]);
            questionLabel.setText(currentQuestion.question);

            // load and resize the image
            ImageIcon imageIcon = new ImageIcon(currentQuestion.picture);
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            pictureLabel.setIcon(new ImageIcon(scaledImage));

            buttonPanel.removeAll();
            for (QuizQuestion.Answer answer : currentQuestion.answers) {
                JButton button = new JButton(answer.answer);
                button.addActionListener(e -> {
                    if (answer.isCorrect) {
                        JOptionPane.showMessageDialog(frame, "Correct!");
                        correctAnswers[0]++;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Incorrect");
                    }
                    currentQuestionIndex[0]++;
                    loadQuestion(questions, frame, questionLabel, pictureLabel, buttonPanel, currentQuestionIndex, correctAnswers);
                });
                buttonPanel.add(button);
            }

            buttonPanel.revalidate();
            buttonPanel.repaint();
        } else {
            int score = ScoreCalculator.calculateScore(correctAnswers[0], questions.size());
            new ScoreScreen(score);
            frame.dispose();
        }
    }
}
