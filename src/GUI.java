// Noah Park

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class GUI extends JPanel implements Runnable, KeyListener {

    // Frame size
    private static final int WIDTH = 500, HEIGHT = 500;
    private static final int CELLSIZE = 10;

    private Thread thread;

    private boolean running;
    private boolean right = false, left = false, up = false, down = false; // Initializing these to false allows the game to start when a key is pressed

    private ArrayList<Snake> snake;
    private ArrayList<Food> food;

    private Random r;

    private int ticks = 0;
    private int curSpeed = 750000; // Initial speed is fair. Will increase as length increases

    private int row = 10, col = 10, length = 1; // Length initialized to 1 so that there are no weird start cases

    public GUI(){

        // Initialize the window
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);

        // Set up empty lists for the food and snake pieces
        snake = new ArrayList<>();
        food = new ArrayList<>();

        r = new Random();

        start(); // Start the game
    }

    public void start(){
        running = true; // Game will go until this becomes false
        thread = new Thread(this);
        thread.start();
    }

    public void stop(){
        // End the game
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick(){
        // If the snake list is empty, add a new snake
        if(snake.size() == 0){
            Snake s = new Snake(row, col, CELLSIZE);
            snake.add(s);
        }

        // Increment ticks which will affect our speed of the snake
        ticks++;

        // Move the snake along in the proper direction based on the ticks against
        // the curSpeed variable.
        if(ticks > curSpeed){
            if(right){
                col++;
            }
            if(left){
                col--;
            }
            if(up){
                row--;
            }
            if(down){
                row++;
            }

            ticks = 0;

            // Add a new snake piece to the snake list.
            Snake s = new Snake(row, col, CELLSIZE);
            snake.add(s);

            // If the list becomes longer than the allowed length, remove a piece from the start
            // This will keep the length constant as long as we don't eat food.
            if(snake.size() > length){
                snake.remove(0);
            }
        }

        // If the food list is empty, pick a random row and column in the bounds and
        // place a piece of food in that cell.
        // Add that piece of food to the food array afterwards.
        if(food.size() == 0){
            int newx = r.nextInt(WIDTH / CELLSIZE);
            int newy = r.nextInt(HEIGHT / CELLSIZE);

            Food f = new Food(newy, newx, CELLSIZE);
            food.add(f);
        }

        // Iterate through the food array to check if a snake is going to "eat" that piece
        // If the indices match, increment the length, decrement the speed (increases it),
        // and remove the piece of food from the food list.
        for(int i = 0; i < food.size(); i++){
            if(col == food.get(i).getCol() && row == food.get(i).getRow()){
                length += 2;
                curSpeed -= 10000;
                food.remove(i);
                i++;
            }
        }

        // Iterate through the snake list to check if the snake will eat itself. Similarly
        // to above, if the indices match, we will end the game.
        for(int i = 0; i < snake.size(); i++){
            if(col == snake.get(i).getCol() && row == snake.get(i).getRow()){
                if(i != snake.size() - 1){
                    System.out.println("YOU ATE YOURSELF: You Lost GGs");
                    stop();
                }
            }
        }

        // Check if the snake is out of bounds. End the game if this is the case.
        if(col < 0 || col >= (WIDTH / 10) || row < 0 || row >= (HEIGHT / 10)){
            System.out.println("OUT OF BOUNDS: You Lost GGs");
            stop();
        }
    }

    // Drawing function
    public void paint(Graphics g){

        // Clear the drawing and redraw after
        g.clearRect(0,0, WIDTH, HEIGHT);

        // Make sure the background is black
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH, HEIGHT);

        // Draw the vertical lines
        for(int i = 0; i < WIDTH / 10; i++){
            g.drawLine(i * 10, 0, i * 10, HEIGHT);
        }

        // Draw the horizontal lines
        for(int i = 0; i < HEIGHT / 10; i++){
            g.drawLine(0, i * 10, WIDTH, i * 10);
        }

        // Draw the snake pieces
        for(int i = 0; i < snake.size(); i++){
            snake.get(i).draw(g);
        }

        // Draw the food piece
        for(int i = 0; i < food.size(); i++){
            food.get(i).draw(g);
        }

    }

    // Runs the game
    public void run(){
        while(running){
            tick();
            repaint();
        }
    }

    // Key Logger
    public void keyPressed(KeyEvent e){
        // Store the key pressed in 'key'
        int key = e.getKeyCode();

        // NO GOING THE OPPOSITE WAY YOU ARE CURRENTLY GOING DIRECTLY
        // Go right if not currently going left
        if(key == KeyEvent.VK_RIGHT && !left){
            right = true;
            up = false;
            down = false;
            left = false;
        }

        // Go left if not currently going right
        if(key == KeyEvent.VK_LEFT && !right){
            left = true;
            up = false;
            down = false;
            right = false;
        }

        // Go up if not currently going down
        if(key == KeyEvent.VK_UP && !down){
            up = true;
            down = false;
            left = false;
            right = false;
        }

        // Go down if not currently going up
        if(key == KeyEvent.VK_DOWN && !up){
            down = true;
            up = false;
            left = false;
            right = false;
        }
    }

    // Unused method
    public void keyReleased(KeyEvent e){

    }

    // Unused method
    public void keyTyped(KeyEvent e){

    }

}
