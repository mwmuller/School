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
        String fileName = args[0];
        DAG dag = new DAG(6);
        Graph gr = new Graph(fileName, dag);
 
        System.out.println("Following is a Topological " +
                           "sort of the given graph");
        dag.topologicalSort();
    }
}