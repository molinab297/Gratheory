package gratheory.main;
import processing.core.PApplet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static processing.core.PApplet.str;
/*******************************************************************
 *  CLASS Graph
 *
 *  OVERVIEW: Class which acts as both a container and drawer for
 *  all vertices and edges in a graph.
 *
 *  CONSTRUCTOR PARAMETERS:
 *   otherMatrix (int[][]) : A Graph object only needs a single piece of
 *   information. An adjancy matrix representation of a graph. This
 *   provides the information necessary to create all vertices and edges
 *   in the graph.
 *
 *   parent (PApplet) : A PApplet object that grants the class access
 *   to Processing's drawing methods.
 ********************************************************************/
public class Graph {

    public static final int VERTEX_SIZE  = 40;
    public static final int VERTEX_COLOR = 100;
    public static final int EDGE_COLOR   = 255;

    private PApplet parent;
    private ArrayList<Vertex> vertexList;
    private ArrayList<Edge> edgeList;
    private int [][]matrix;
    private int numVertices;

    public Graph(int n, String fileName, PApplet parent){
        numVertices = n;
        matrix = new int[numVertices][numVertices];
        this.parent = parent;
        readMatrix(fileName);
        initializeGraph();
    }

    public Graph(int [][] otherMatrix, PApplet parent){
        this.parent = parent;
        numVertices = otherMatrix.length;
        matrix = new int[numVertices][numVertices];

        for (int i = 0; i < otherMatrix.length; i++) {
            System.arraycopy(otherMatrix[i], 0, matrix[i], 0, otherMatrix[0].length);
        }

        initializeGraph();
    }

    // Creates all the vertices with their positions and sizes on the graph. */
    private void initializeGraph(){
        vertexList = new ArrayList<>();
        edgeList   = new ArrayList<>();
        int temp = 27;

        // Initialize Vertices *** Need to fix placement algorithm ***
        for (int x = 0; x < numVertices; x++)
            vertexList.add(new Vertex(temp % (x + 15) * x + temp * (x + 15),
                    temp % (x + 5) * temp + temp * (x + 5), VERTEX_SIZE, VERTEX_SIZE, VERTEX_COLOR, x, this.parent));

        // Initializes edges
        for (int x = 0; x < numVertices; x++) {
            for (int y = 0; y < numVertices; y++) {
                if (matrix[x][y] == 1 && !edgeExist(vertexList.get(x),vertexList.get(y))) {
                        edgeList.add(new Edge(vertexList.get(x), vertexList.get(y), EDGE_COLOR, -1));
                }
            }
        }

    }

    // Draws all vertices in the graph to the screen
    private void drawVertices(){
        int counter = 0;
        for(Vertex v: vertexList){
            v.display();
            parent.fill(255, 255, 0);
            parent.textAlign(parent.CENTER, parent.CENTER);
            parent.text(str(counter), v.getxCoord(), v.getyCoord());
            counter++;
        }
    }

    // Draws all edges in the graph to the screen.
    private void drawEdges(){
        for(Edge e: edgeList)
            e.display(parent);
    }

    // Draws the Graph. Only call within the draw() method of Gratheory.
    public void display() {
        drawEdges();
        drawVertices();
    }


    // Given two vertices, this function returns whether or a not an edge connects them.
    public boolean edgeExist(Vertex a, Vertex b){
        for(Edge e : edgeList){
            if(e.connects(a,b))
                return true;
        }
        return false;
    }

    // Returns the incident edge between vertex 'A' and vertex 'B'
    public Edge getEdge(Vertex a, Vertex b){
            for(Edge e : edgeList){
                if(e.connects(a,b))
                    return edgeList.get(edgeList.indexOf(e));
        }
        return null;
    }

    // Reset colors on edges back to white
    public void clear(){
        for(Edge e : edgeList)
            e.setColor(EDGE_COLOR);
    }

    // Saves state of graph to an output file
    public void saveGraph(String filename){
        try{
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            for(int i = 0; i < numVertices; i++){
                for(int j = 0; j < numVertices; j++)
                    writer.write(matrix[i][j] + " ");
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Reads adjancy matrix from file
    private void readMatrix(String fileName){
        int i = 0, j = 0;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while(scanner.hasNext()){
                int n = scanner.nextInt();
                matrix[i][j] = n;
                j++;

                if(j == numVertices){
                    i++;
                    j = 0;
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String matrixToString(){
        String result = "";
        for(int i = 0; i < numVertices; i++){
            for(int j = 0; j < numVertices; j++){
                result += (matrix[i][j] + " ");
            }
            result += "\n";
        }
        return result;
    }

    void addVertex(Vertex newVertex){
        vertexList.add(newVertex);
        resizeMatrix();
    }

    // Increases size of the matrix diagonally by 1
    private void resizeMatrix(){
        int[][] newMatrix = new int[numVertices+1][numVertices+1];
        for(int i = 0; i < numVertices; i++){
            for(int j = 0; j < numVertices; j++)
                newMatrix[i][j] = this.matrix[i][j];
        }
        this.matrix = newMatrix;
        numVertices++;
    }

    void addEdge(Vertex s, Vertex d, int c, int w){
        edgeList.add(new Edge(s,d,c,w));
        // Updates adjancy matrix
        matrix[s.getId()][d.getId()] = 1;
        matrix[d.getId()][s.getId()] = 1;
    }

    public Vertex getVertex(int pos){
        return vertexList.get(pos);
    }

    public final int numVertices() {
        return numVertices;
    }

    public ArrayList<Vertex> getVertexList () { return vertexList; }

    /* Returns index of the graph's adjancy matrix. */
    public int getIndex(int x, int y) { return matrix[x][y]; }

    public PApplet parent() { return parent; }

}
