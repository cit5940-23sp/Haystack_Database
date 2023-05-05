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
    private int nextNode;

    
    /**
     * Empty no argument constructor
     */
    public GraphL()
    {
     // No real constructor needed
        numNodes = 0;
        numEdge = 0;
        
        nodeArray = new ArrayList<Edge>();
        nodeValues = new ArrayList<Object>();
    }


//    // Initialize the graph with n vertices
//    public void init(int n)
//    {
//        nodeArray = new Edge[n];
//        // List headers;
//        for (int i = 0; i < n; i++)
//            nodeArray[i] = new Edge(-1, -1, null, null);
//        nodeValues = new Object[n];
//        numEdge = 0;
//    }

    public void addNode() {
        
        nodeArray.add(new Edge(-1, -1, null, null));
        nodeValues.add(numNodes);
        numNodes ++;
        
        
    }

    // Return the number of vertices
    public int nodeCount()
    {
        return nodeArray.size();
    }


    // Return the current number of edges
    public int edgeCount()
    {
        return numEdge;
    }


//    // Get the value of node with index v
//    public Object getValue(int v)
//    {
//        return nodeValues[v];
//    }


//    // Set the value of node with index v
//    public void setValue(int v, Object val)
//    {
//        nodeValues[v] = val;
//    }


    // Return the link in v's neighbor list that preceeds the
    // one with w (or where it would be)
    private Edge find(int v, int w)
    {
//        System.out.println("V is " + v);
        Edge curr = nodeArray.get(v);
        while ((curr.next != null) && (curr.next.vertex < w))
            curr = curr.next;
        return curr;
    }


    // Adds a new edge from node v to node w with weight wgt
    public void addEdge(int v, int w, int wgt)
    {
        wgt += 1;
//        if (wgt == 0)
//            return; // Can't store weight of 0
        Edge curr = find(v, w);
        if ((curr.next != null) && (curr.next.vertex == w))
            curr.next.weight = wgt;
        else
        {
            curr.next = new Edge(w, wgt, curr, curr.next);
            if (curr.next.next != null)
                curr.next.next.prev = curr.next;
        }
        numEdge++;
    }


    // Get the weight value for an edge
    public int weight(int v, int w)
    {
        Edge curr = find(v, w);
        if ((curr.next == null) || (curr.next.vertex != w))
            return 0;
        else
            return curr.next.weight;
    }


    // Removes the edge from the graph.
    public void removeEdge(int v, int w)
    {
        Edge curr = find(v, w);
        if ((curr.next == null) || curr.next.vertex != w)
            return;
        else
        {
            curr.next = curr.next.next;
            if (curr.next != null)
                curr.next.prev = curr;
        }
        numEdge--;
    }


    // Returns true iff the graph has the edge
    public boolean hasEdge(int v, int w)
    {
        return weight(v, w) != 0;
    }


    // Returns an array containing the indicies of the neighbors of v
    public int[] neighbors(int v)
    {
        int cnt = 0;
        Edge curr;
        for (curr = nodeArray.get(v).next; curr != null; curr = curr.next)
            cnt++;
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

    private class Edge { // Doubly linked list node
        int  vertex, weight;
        Edge prev;
        Edge next;


        Edge(int v, int w, Edge p, Edge n)
        {
            vertex = v;
            weight = w;
            prev = p;
            next = n;
        }
    }

}
