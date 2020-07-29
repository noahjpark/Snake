// Noah Park

import java.awt.*;

public class Food {

    // Row and column are for the location of the food
    // Size is for the size of the food
    private int row, col, size;

    public Food(int row, int col, int size){
        this.row = row;
        this.col = col;
        this.size = size;
    }

    // Draw the food onto the frame
    // Row/Column are scaled to the size
    // Food is white
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
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
