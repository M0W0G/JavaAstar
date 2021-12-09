
package astar;
import javax.swing.*;
import java.awt.*;

import java.util.*;

import java.awt.event.*;  

/**
 *
 * @author brend
 */
public class Grid extends JFrame implements MouseListener{
    
    
    ArrayList<Node> nodeList = new ArrayList<>();
    ArrayList<ArrayList<Node>> grid;
    int row;
    int col;
    int width;
    int totalRows;
    boolean start;
    boolean end;

    JPanel panel;
    
    //keeps track if beginning and ending nodes
    Node Start;
    Node End;
    
    boolean placeStart;
    boolean placeEnd;
    
    //Checks if program is running or if algorithm has started
    boolean run;
    boolean started;
    
   
    
    
    public Grid(int row, int col, int width, int totalRows){
        
        this.width = width; // width of JPanel
        this.totalRows = totalRows;
        
        this.grid = this.initGrid();
        this.grid.get(2).get(2).setBarrier();
        this.grid.get(0).get(0).setBarrier();
        
        
         
        this.nodeList.add(new Node(10,40,10,10));
        
        panel = new JPanel();
        getContentPane().add(panel);
        //setSize(800,800);
        panel.addMouseListener(this);
        
        
        
        
        
        
    }
    @Override
    //Overrides awt paint
    public void paint(Graphics g) {
        //gives graphics to parent paint method
        super.paint(g);
        super.setPreferredSize(new Dimension(800,830));
        super.pack();
        for (Node N : nodeList){
            
        }
       
        g.setColor(java.awt.Color.red);
        
        this.draw(g);
        
        
        
    }
   
    //Makes the grid of nodes in memory
    public ArrayList<ArrayList<Node>> initGrid(){
        ArrayList<ArrayList<Node>> grid = new ArrayList<ArrayList<Node>>();
        this.placeStart = false;
        this.placeEnd = false;
        
        
        //what the width of each of these cubes should be
        int gap = Math.floorDiv(super.getWidth(), this.totalRows);
        
        
        
        
        
        
        for(int i = 0; i < this.totalRows; i++){
            //2d lists are wonky in Java, this line adds another list to the 2d list "grid"
            grid.add(new ArrayList<Node>());
            for(int j = 0; j < this.totalRows; j++){
                //this accesses the 2d list, it is essentially array[i][j]
                grid.get(i).add(j,new Node(i, j, gap, this.totalRows));
                
            }
        }
        
        this.grid = grid;
        return grid;
    }
    //Calls drawspot for each node in the grid
    public void draw(Graphics g){
        System.out.print("draw()");
        g.setColor(java.awt.Color.white);
        
       
        g.setColor(java.awt.Color.BLACK);
        
        
        
        for(int i = 0; i < this.totalRows; i++){
            
            for(int j = 0; j <this.totalRows; j++){
                
                
                
                this.drawSpot(g, this.grid.get(i).get(j));
                
            }
        }
        
    }
    //Fills a rectangle with node location and colors
    public void drawSpot(Graphics g, Node N){
       
       //Fills in the color based on Node status and color
       if (N.color == Color.BLACK){
           g.setColor(java.awt.Color.BLACK);
           g.fillRect(N.getX(), N.getY(), N.getWidth(), N.getWidth());
       }
       if (N.color == Color.ORANGE){
           g.setColor(java.awt.Color.ORANGE);
           g.fillRect(N.getX(), N.getY(), N.getWidth(), N.getWidth());
       }
       if (N.color == Color.BLUE){
           g.setColor(java.awt.Color.CYAN);
           g.fillRect(N.getX(), N.getY(), N.getWidth(), N.getWidth());
       }
       if (N.color == Color.PURPLE){
           g.setColor(java.awt.Color.MAGENTA);
           g.fillRect(N.getX(), N.getY(), N.getWidth(), N.getWidth());
       }
       if (N.color == Color.WHITE){
           g.setColor(java.awt.Color.WHITE);
           g.fillRect(N.getX(), N.getY(), N.getWidth(), N.getWidth());
       }
       if (N.color == Color.RED){
           g.setColor(java.awt.Color.RED);
           g.fillRect(N.getX(), N.getY(), N.getWidth(), N.getWidth());
       }
       if (N.color == Color.GREEN){
           g.setColor(java.awt.Color.GREEN);
           g.fillRect(N.getX(), N.getY(), N.getWidth(), N.getWidth());
       }
       
       
       
       g.setColor(java.awt.Color.black);
       g.drawRect(N.getX(), N.getY(), N.getWidth(), N.getWidth());
       
       
       
    }
    //returns the x and y adjusted
    public int[] getMousePos(int x, int y){
        int gap = Math.floorDiv(super.getWidth(), this.totalRows);
        
        row = Math.floorDiv(y, gap);
        col = Math.floorDiv(x, gap);
        int[] pair = {row,col};
        return pair;
        
                
    }
    //Decides if clicking should place Start, End, Or barrier nodes
    public void colorNode(int row, int col){
            
            Node N = this.grid.get(col).get(row);
        
            if (!placeStart && N.color != Color.BLUE){
                N.setStart();
                //Saves the start node
                this.Start = N;
                this.placeStart = true;
            }
            else if(!placeEnd && N.color != Color.ORANGE){
                N.setEnd();
                //Saves the end node
                this.End = N;
                this.placeEnd = true;
            }
            else if (N.color == Color.WHITE){
                N.setBarrier();
            }
    }
    
    
    
    public void clearNode(int row, int col){
        Node N = this.grid.get(col).get(row);
        if(N.color == Color.ORANGE){
            this.placeStart = false;
        }
        else if (N.color == Color.BLUE){
            this.placeEnd = false;
        }
        N.reset();
    }
    
    //Loads all adjacent nodes but not diagonal nodes
    //Lets each node know what its neighbors are
    public void updateNeighbors(int row, int col){
        Node N = this.grid.get(col).get(row);
        ArrayList<Node> neighbors = new ArrayList();
        
        //Maybe subtract 1 from row / col
        //Checking node below
        if (row < this.totalRows - 1 && !this.grid.get(col).get(row + 1).isBarrier()){
            neighbors.add(this.grid.get(col).get(row + 1));
        }
        //Checking node above
        if (row > 0 && !this.grid.get(col).get(row - 1).isBarrier()){
            neighbors.add(this.grid.get(col).get(row - 1));
        }
        //Checking node to the right
        if (col < this.totalRows - 1 && !this.grid.get(col + 1).get(row).isBarrier()){
            neighbors.add(this.grid.get(col + 1).get(row));
        }
        //Checking node to the left
        if (col > 0 && !this.grid.get(col - 1).get(row).isBarrier()){
            neighbors.add(this.grid.get(col - 1).get(row));
        }
        N.neighbors = neighbors;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        int button = e.getButton();
        
        int[] loc = this.getMousePos(e.getX(),e.getY());
        
        //Left click
        if (button == 1){
            System.out.print("left click");
            repaint();
            
            this.colorNode(loc[0], loc[1]);
        }
        //Middle Click, generates grid
        else if(button ==2){
            
            if(!run){
               System.out.print("middle click");
               this.initGrid();
               repaint(); 
               this.run = true;
            }
            else{
                this.mainLoop(e);
            }
            
            
        }
        //Right Click, resets node to white
        else if(button == 3){
            System.out.print("right click");
            
            this.clearNode(loc[0], loc[1]);
            
            repaint();
            
        }
        
        System.out.print(this.getMousePos(e.getX(),e.getY())[0]);
        System.out.print(this.getMousePos(e.getX(),e.getY())[1]);
        
        
        
    }
    
    
    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    //Below is all Algorithm Related Methods
    
     //finds distance between two points using manhattan distance
    public float heuristic(Node n1, Node n2){
        int x1 = n1.x;
        int y1 = n1.y;
        
        int x2 = n2.x;
        int y2 = n2.y;
        
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    
    public void remakePath(Hashtable cameFrom, Node current){
        while(cameFrom.containsKey(current)){
            
            current = (Node) cameFrom.get(current);
            current.setPath();
            
            this.Start.setStart();
            this.End.setEnd();
            
            repaint();
        }
        System.out.print("\nREMAKING");
        
    }
    
    
    
    public void aStar(MouseEvent e){
        int count = 0;
        
        //PriorityQue, keeps track which nodes are best
        //fScoreComparator allows priorityque to understand how to rank the nodes (based on F score)
        PriorityQueue<Node> openNodes = new PriorityQueue<>(11, new fScoreComparator());
        openNodes.add(this.Start);
       
        //Keeps track of where nodes came from
        //Used to reconstruct the path visually at the end (in purple)
        Hashtable cameFrom = new Hashtable();
        
        //Keeps track of each nodes gScore
        Dictionary gScore = new Hashtable();
        
        //Sets each gScore at Positive Infinity Initially 
        for(int i = 0; i < this.totalRows; i++){
            for(int j = 0; j <this.totalRows; j++){
                gScore.put(this.grid.get(i).get(j), Float.POSITIVE_INFINITY);
            }
        }
        
     
        //Dictionarys do not allow for a node to be altered, must remove it and then readd it
        gScore.remove(this.Start);
        gScore.put(this.Start, 0f);
        
        
        //Exact same as gScore dictionary
        Dictionary fScore = new Hashtable();
        
        for(int i = 0; i < this.totalRows; i++){
            for(int j = 0; j <this.totalRows; j++){
                fScore.put(this.grid.get(i).get(j), Float.POSITIVE_INFINITY);
            }
        }
        
        //No way to alter value of key so I remove it and then add it back
        fScore.remove(this.Start);
        //Adds start
        fScore.put(this.Start, this.heuristic(this.Start, this.End));
        

     
        Node currentNode = null;
        //Keeps running until all possible nodes have been explored or end is found
        //If openNodes ever becomes empty, it means there is no possible path
        while(!openNodes.isEmpty()){
            
            
            
            //Gets current head Node from the priorityQue that it is going to be evaluating
            //.remove() removes node but throws exception if there is none to pull, consider using this instead
            currentNode = openNodes.remove();
          
            //If we are on the end, we have found the shortest path
            //Begins reconstructing the path to visualize
            if(currentNode == this.End){
                
                this.remakePath(cameFrom, currentNode);
                System.out.print(" " + cameFrom.size() + " ");
                this.run = false;
                
                
                repaint();
                
                System.out.print("\nI found the end");
                return;
                
            }
            
            for(int i = 0; i < currentNode.neighbors.size(); i++){
                
                float tempG = (float) gScore.get(currentNode) + 1f;
                
                //G score we are checking
                Node neighbor = currentNode.neighbors.get(i);
                if (neighbor == null){
                    System.out.print(" Neigbor is null ");
                }
                
                float checkG = (float) gScore.get(neighbor);
               System.out.print("\nCheck " + checkG);
               
               System.out.print("\nTemp " + tempG);
               System.out.print("\n---");
                if(tempG < checkG){
                    System.out.print("less than");
                    
                    cameFrom.put(neighbor, currentNode);
                    
                    gScore.put(neighbor, tempG);
                    fScore.put(neighbor, tempG + this.heuristic(neighbor, this.End));
                    
                    if(!openNodes.contains(neighbor)){
                        count+=1;
                        
                        
                        neighbor.f = (float) fScore.get(neighbor);
                        openNodes.add(neighbor);
                        
                        neighbor.setOpen();
                        
                    }
                    
                }
            }
            
            repaint();
            
            if(currentNode != this.Start){
                
                //currentNode.setClosed();
            }
            
        }

        
        System.out.print("Open Node is empty");
        this.run = false;
    
                
        //Keeps track of path for nodes
        ArrayList<Node> pathList = new ArrayList<Node>();
        
        
    }
    
    public void mainLoop(MouseEvent e){
        
        int[] loc = this.getMousePos(e.getX(),e.getY());
        
        while (run){
            if(!started){
                
                for(int i = 0; i < this.totalRows; i++){
                    for(int j = 0; j < this.totalRows; j++){
                        //Updates all Nodes Neighbors
                        this.updateNeighbors(i, j);
                        
                    }
                }
                //Actual A* Algorithm Call
                
                this.aStar(e);
            }
            
            
        }
        repaint();
        
    }
    
    
}
