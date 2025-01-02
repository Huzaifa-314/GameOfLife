package gameoflife;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell {
    private boolean isAlive;
    private final int x;
    private final int y;
    private final Rectangle rectangle;
    private final double size;
    
    public Cell(int x, int y, double size){
        this.x = x;
        this.y = y;
        this.size = size;
        this.isAlive = false;
        
        rectangle = new Rectangle(size,size);
        rectangle.setStroke(Color.LIGHTGRAY);
    }
    
    public boolean isAlive(){
        return isAlive;
    }
    
    public void setAlive(boolean alive){
        this.isAlive = alive;
        updateColor();
    }
    
    public void toggleAlive(){
        this.isAlive = !this.isAlive;
        updateColor();
    }
    
    private void updateColor(){
        if(isAlive) rectangle.setFill(Color.WHITE);
        else rectangle.setFill(Color.BLACK);
    }
    
    public Rectangle getRectangle() {
        return rectangle;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setPosition(double originX, double originY) {
        rectangle.setX(originX + x * size);
        rectangle.setY(originY + y * size);
    }
}