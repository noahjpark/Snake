// Noah Park

import java.awt.*;

public class Snake {

    // Row and column represent the row and column the piece is at
    // The size represents the scaled board
    private int row, col, size;

    public Snake(int row, int col, int size){
        this.row = row;
        this.col = col;
        this.size = size;
    }

    // Draws the snake pieces
    // Snake is traditional lime green
    public void draw(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(col * size, row * size, size, size);
    }

    // Returns the row
    public int getRow(){
        return this.row;
    }

    // Returns the column
    public int getCol(){
        return this.col;
    }

}
