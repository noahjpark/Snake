// Noah Park

import javax.swing.*;
import java.awt.*;

public class Game {

    // Game method runs the overall game
    public Game(){
        JFrame frame = new JFrame(); // New frame object
        GUI g = new GUI();           // New GUI object

        frame.add(g);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the game upon closing the window
        frame.setBackground(Color.BLACK);         // Blackout the background
        frame.setTitle("Snake");                  // Title of the game

        frame.pack();                             // Sets the size of the frame
        frame.setVisible(true);                   // Ensure the frame is visible

    }

    public static void main(String[] args){
        new Game(); // Begin the game
    }
}
