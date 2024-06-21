public class ScoreCalculator {
    public ScoreCalculator() {
    }

    public static int calculateScore(int correctAnswers, int totalQuestions) {
        if (totalQuestions == 0) {
            return 0;
        } else {
            double percentage = (double)correctAnswers / (double)totalQuestions * 100.0;
            int score = (int)percentage;
            return score;
        }
    }
}
