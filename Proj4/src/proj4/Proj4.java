package proj4;

// A Java program to print topological sorting of a DAG
import java.io.*;
import java.util.*;
 
// This class represents a directed graph using adjacency
// list representation
class Proj4
{
   
    // Driver method
    public static void main(String args[])
    {
        // Create a graph given in the above diagram
        DAG dag = new DAG(6);
        dag.addEdge(5, 2);
        dag.addEdge(5, 0);
        dag.addEdge(4, 0);
        dag.addEdge(4, 1);
        dag.addEdge(2, 3);
        dag.addEdge(3, 1);
 
        System.out.println("Following is a Topological " +
                           "sort of the given graph");
        dag.topologicalSort();
    }
}