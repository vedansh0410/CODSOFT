import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

public class QuizApplication {
    // Question data: each question has text, options, and the correct answer index
    static class Question {
        String questionText;
        String[] options;
        int correctAnswerIndex;

        public Question(String questionText, String[] options, int correctAnswerIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }
    }

    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static int timerSeconds = 30; // Timer for each question in seconds
    private static Timer timer;
    private static boolean answered = false;

    private static Question[] questions = {
        new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2),
        new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1),
        new Question("Which is the largest planet?", new String[]{"Earth", "Jupiter", "Mars", "Saturn"}, 1)
    };

    private static JFrame frame;
    private static JTextArea questionArea;
    private static JRadioButton[] options = new JRadioButton[4];
    private static ButtonGroup group;
    private static JButton submitButton;
    private static JLabel timerLabel;
    private static JLabel scoreLabel;

    public static void main(String[] args) {
        // Initialize the UI
        setupUI();

        // Load the first question
        loadQuestion();
    }

    private static void setupUI() {
        frame = new JFrame("Quiz Application");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Question area
        questionArea = new JTextArea();
        questionArea.setEditable(false);
        questionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        questionArea.setWrapStyleWord(true);
        questionArea.setLineWrap(true);
        frame.add(new JScrollPane(questionArea), BorderLayout.NORTH);

        // Timer label
        timerLabel = new JLabel("Time: 30", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(timerLabel, BorderLayout.SOUTH);

        // Score label
        scoreLabel = new JLabel("Score: 0", SwingConstants.LEFT);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(scoreLabel, BorderLayout.WEST);

        // Options panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));

        group = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            optionsPanel.add(options[i]);
            group.add(options[i]);
        }
        frame.add(optionsPanel, BorderLayout.CENTER);

        // Submit button
        submitButton = new JButton("Submit Answer");
        submitButton.addActionListener(e -> submitAnswer());
        frame.add(submitButton, BorderLayout.EAST);

        // Set frame size and visibility
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void loadQuestion() {
        if (currentQuestionIndex >= questions.length) {
            showResult();
            return;
        }

        // Reset the timer
        timerSeconds = 30;
        answered = false;
        timerLabel.setText("Time: " + timerSeconds);

        // Get the current question
        Question currentQuestion = questions[currentQuestionIndex];

        // Update question text
        questionArea.setText(currentQuestion.questionText);

        // Update options
        for (int i = 0; i < 4; i++) {
            options[i].setText(currentQuestion.options[i]);
            options[i].setSelected(false);
        }

        // Start the timer
        startTimer();
    }

    private static void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (timerSeconds <= 0 || answered) {
                    timer.cancel();
                    submitAnswer(); // Automatically submit if time runs out
                } else {
                    timerLabel.setText("Time: " + timerSeconds);
                    timerSeconds--;
                }
            }
        }, 1000, 1000);
    }

    private static void submitAnswer() {
        // Stop the timer
        if (timer != null) {
            timer.cancel();
        }

        // Check the selected answer
        int selectedOption = -1;
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selectedOption = i;
                break;
            }
        }

        if (selectedOption == -1) {
            JOptionPane.showMessageDialog(frame, "You must select an answer!");
            return;
        }

        // Check if the selected answer is correct
        Question currentQuestion = questions[currentQuestionIndex];
        if (selectedOption == currentQuestion.correctAnswerIndex) {
            score++;
        }

        // Update the score
        scoreLabel.setText("Score: " + score);

        // Move to the next question
        currentQuestionIndex++;

        // Load the next question after a short delay
        Timer delayTimer = new Timer();
        delayTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                loadQuestion();
            }
        }, 1000); // Wait 1 second before loading the next question
    }

    private static void showResult() {
        JOptionPane.showMessageDialog(frame, "Quiz Finished! Your score: " + score + " out of " + questions.length);
        System.exit(0);
    }
}
