package timestables;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import javax.swing.*;

public class Coordinator implements ActionListener {

    private final JFrame frame;
    private final JPanel panel;
    private final JPanel centerPanel;
    private final JPanel topPanel;
    private final JPanel bottomPanel;
    private final JLabel question;
    private final JTextField answerField;
    private final JLabel userMessage;
    private final JLabel timeLabel;
    private final GridBagConstraints gbc = new GridBagConstraints();

    private final String tableLengthPrompt = "Times table length?";
    private IntegerPair currentPair;
    private LinkedList<IntegerPair> integerPairs;
    private final Random rand = new Random();
    private final Utility utility = new Utility();
    private final Timer timer = new Timer();
    private final Stopwatch watch = new Stopwatch(timer);
    private int mistakes;

    public Coordinator() {

        frame = new JFrame("Times Tables");
        frame.setBounds(0, 0, 500, 500);

        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.DARK_GRAY);

        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.DARK_GRAY);
        panel.add(centerPanel, BorderLayout.CENTER);

        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.DARK_GRAY);
        panel.add(topPanel, BorderLayout.NORTH);

        bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setBackground(Color.DARK_GRAY);
        bottomPanel.setPreferredSize(new Dimension(100, 50));
        panel.add(bottomPanel, BorderLayout.SOUTH);

        gbc.insets = new Insets(5, 5, 5, 5);

        question = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 1;
        utility.customizeLabel(question, tableLengthPrompt, Color.WHITE);
        centerPanel.add(question, gbc);

        answerField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        answerField.setHorizontalAlignment(JTextField.CENTER);
        centerPanel.add(answerField, gbc);

        userMessage = new JLabel();
        utility.customizeLabel(userMessage, "Good Luck!", Color.WHITE);
        bottomPanel.add(userMessage);

        timeLabel = new JLabel(utility.getMinutesAndSecondsTimer(0));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setPreferredSize(new Dimension(40, 25));
        topPanel.add(timeLabel, BorderLayout.EAST);

        answerField.addActionListener(this);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Coordinator();
    }

    public void actionPerformed(ActionEvent event) {

        if(event.getSource() == answerField) {

            try {
                if(question.getText().equals(tableLengthPrompt)) {

                    int tableLength = Integer.parseInt(answerField.getText());

                    if(tableLength >= 1 && tableLength <= 20) {
                        integerPairs = new LinkedList<>();
                        utility.addCells(integerPairs, tableLength);

                        currentPair = utility.nextQuestion(question, integerPairs, rand);
                        utility.customizeLabel(userMessage, "Begin!", Color.GREEN);
                        watch.start(timeLabel, utility);
                    }

                    else {
                        utility.customizeLabel(userMessage, "Invalid input. Input not in range.", Color.RED);
                    }
                }

                else {
                    int answer = Integer.parseInt(answerField.getText());

                    if(answer == currentPair.correctAnswer()) {
                        utility.customizeLabel(userMessage, "Correct!", Color.GREEN);
                        integerPairs.remove(currentPair);

                        if(integerPairs.size() == 0) {
                            question.setText("All done!");
                            utility.showEndingMessage(userMessage, mistakes, watch);
                            watch.stop();
                            answerField.removeActionListener(this);
                            answerField.setEditable(false);
                        }

                        else {
                            currentPair = utility.nextQuestion(question, integerPairs, rand);
                        }
                    }

                    else {
                        utility.customizeLabel(userMessage, "Incorrect, try again.", Color.RED);
                        mistakes++;
                    }
                }
            }

            catch(NumberFormatException exception) {
                if(!answerField.getText().equals("")) {
                    utility.customizeLabel(userMessage, "Invalid input.", Color.RED);
                }
            }

            answerField.setText(null);
        }
    }
}