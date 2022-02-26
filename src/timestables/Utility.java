package timestables;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.*;

public class Utility {

    public void addCells(LinkedList<IntegerPair> list, int tableLength) {

        for (int rows = 0; rows < tableLength; rows++) {

            for (int cols = 0; cols < tableLength; cols++) {

                list.add(new IntegerPair(rows + 1, cols + 1));
            }
        }
    }

    public IntegerPair setPair(LinkedList<IntegerPair> list, Random rand) {
        int randomCell = rand.nextInt(list.size());
        return list.get(randomCell);
    }

    public void customizeLabel(JLabel label, String text, Color color) {
        label.setForeground(color);
        label.setText(text);
    }

    public IntegerPair nextQuestion(JLabel question, LinkedList<IntegerPair> list, Random rand) {
        IntegerPair currentPair = setPair(list, rand);
        question.setText(currentPair.toString());
        return currentPair;
    }

    public String getMinutesAndSecondsTimer(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds - (minutes * 60);

        String remainingSeconds_string = String.format("%02d", remainingSeconds);
        String minutes_string = String.format("%02d", minutes);

        return minutes_string + ":" + remainingSeconds_string;
    }

    public String getMinutesAndSecondsMessage(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds - (minutes * 60);

        if(minutes == 0) {
            if(remainingSeconds == 1) {
                return "It took you " + remainingSeconds + " second to finish.";
            }

            else {
                return "It took you " + remainingSeconds + " seconds to finish.";
            }
        }

        if(minutes == 1) {
            if(remainingSeconds == 0) {
                return "It took you " + minutes + " minute to finish.";
            }

            else if(remainingSeconds == 1) {
                return "It took you " + minutes + " minute and " + remainingSeconds + " second to finish.";
            }

            else {
                return "It took you " + minutes + " minute and " + remainingSeconds + " seconds to finish.";
            }
        }

        else {
            if(remainingSeconds == 0) {
                return "It took you " + minutes + " minutes to finish.";
            }

            else if(remainingSeconds == 1) {
                return "It took you " + minutes + " minutes and " + remainingSeconds + " second to finish.";
            }

            else {
                return "It took you " + minutes + " minutes and " + remainingSeconds + " seconds to finish.";
            }
        }
    }

    public void showEndingMessage(JLabel userMessage, int mistakes, Stopwatch watch) {
        if(mistakes == 1) {
            customizeLabel(userMessage, "You made " + mistakes + " mistake!\n" +
                    getMinutesAndSecondsMessage(watch.getSeconds()), Color.CYAN);
        }

        else {
            customizeLabel(userMessage, "You made " + mistakes + " mistakes!\n" +
                    getMinutesAndSecondsMessage(watch.getSeconds()), Color.CYAN);
        }
    }
}