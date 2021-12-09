/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;
import java.util.*;


/**
 *
 * @author brend
 */
public class Node {
    int row;
    int col;
    int x;
    int y;
    Color color;
    List<Node> neighbors = new ArrayList<>();
    int width;
    int totalRows;
    float f;
    float h;
    float g;
    
    public Node(int row, int col, int width, int totalRows){
        this.row = row;
        this.col = col;
        this.x = (row * width) + 10;
        this.y = (col * width)+ 35;
        this.color = Color.WHITE;
        this.width = width;
        this.totalRows = totalRows;
        
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        
        return this.y;
    }
    public int getWidth(){
        return this.width;
    }
    public int getRow(){
        return this.row;
    }
    public int getCol(){
        return this.col;
    }
    
    //The color ENUM relates to the state of the cell
    //Refer to Color.java for each meaning of colors
    public boolean isClosed(){
        return this.color == Color.RED;
    }
    public boolean isOpen(){
        return this.color == Color.GREEN;
    }
    public boolean isBarrier(){
        return this.color == Color.BLACK;
    }
    public boolean isStart(){
        return this.color == Color.ORANGE;
    }
    public boolean isEnd(){
        return this.color == Color.BLUE;
    }
    public void reset(){
        this.color = Color.WHITE;
    }
    
    public void setClosed(){
        this.color = Color.RED;
    }
    public void setOpen() {
        this.color = Color.GREEN;
    }
    public void setBarrier(){
        this.color = Color.BLACK;
    }
    public void setEnd(){
        this.color = Color.BLUE;
    }
    public void setStart() {
        this.color = Color.ORANGE;
    }
    public void setPath(){
        this.color = Color.PURPLE;
    }
}
