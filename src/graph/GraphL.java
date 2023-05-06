package graph;

import java.util.ArrayList;

/**
 * @author 
 *
 */
public class GraphL implements Graph {

    private ArrayList<Edge>   nodeArray;
    private ArrayList<Object> nodeValues;
    private int numEdge;
    private int numNodes;

    
    /**
     * Empty no argument constructor
     */
    public GraphL() {

        numNodes = 0;
        numEdge = 0;
        
        nodeArray = new ArrayList<Edge>();
        nodeValues = new ArrayList<Object>();
    }


    public void addNode() {
        
        nodeArray.add(new Edge(-1, -1, null));
        nodeValues.add(numNodes);
        numNodes ++;
        
        
    }

    // Return the number of vertices
    public int nodeCount() {
        return nodeArray.size();
    }


    // Return the current number of edges
    public int edgeCount() {
        return numEdge;
    }


    // Return the link in v's neighbor list that preceeds the
    // one with w (or where it would be)
    private Edge find(int v, int w) {
        Edge curr = nodeArray.get(v);
        while ((curr.next != null) && (curr.next.vertex < w)) {
            curr = curr.next;
        }
        return curr;
    }


    // Adds a new edge from node v to node w with weight wgt
    public void addEdge(int v, int w, int wgt) {
        wgt += 1;
        Edge curr = find(v, w);
        if ((curr.next != null) && (curr.next.vertex == w)) {
            curr.next.weight = wgt;
        } else {
            curr.next = new Edge(w, wgt, curr.next);
        }
        
        numEdge++;
    }


    // Get the weight value for an edge
    public int weight(int v, int w) {
        Edge curr = find(v, w);
        if ((curr.next == null) || (curr.next.vertex != w)) {
            return 0;
        } else {
            return curr.next.weight;
        }
    }



    // Returns true iff the graph has the edge
    public boolean hasEdge(int v, int w) {
        return weight(v, w) != 0;
    }


    // Returns an array containing the indices of the neighbors of v
    public int[] neighbors(int v) {
        int cnt = 0;
        Edge curr;
        for (curr = nodeArray.get(v).next; curr != null; curr = curr.next) {
            cnt++;
        }
        int[] temp = new int[cnt];
        if (cnt == 0) {
            return temp;
        }

        cnt = 0;
        for (curr = nodeArray.get(v); curr != null; curr = curr.next) {
            
            if (curr.vertex == -1) {
                continue;
            }

            temp[cnt++] = curr.vertex;            
        }


        return temp;
    }

    private class Edge { // Singly linked list node
        int  vertex, weight;
        Edge next;


        Edge(int v, int w, Edge n) {
            vertex = v;
            weight = w;
            next = n;
        }
    }

}
