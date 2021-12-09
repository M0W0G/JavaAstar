/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;



import java.util.Comparator;

/**
 *
 * @author brend
 */

//Must have a way to compare nodes for priority que to determine which node is weighted more
//fScoreComparator allows priorityque to understand how to rank the nodes (based on F score)

public class fScoreComparator implements  Comparator<Node>{

    @Override
    public int compare(Node n1, Node n2) {
        if(n1.f == n2.f){
            return 0;
        }
        else if (n1.f > n2.f){
            return 1;
        }
        else{
            return -1;
        }
        
        
    }
}
