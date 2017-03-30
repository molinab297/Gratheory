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
    private int vertexCount;

    public Graph(String fileName, PApplet parent){
        this.parent = parent;
        readMatrix(fileName);
        initializeGraph();
    }

    public Graph(int [][] otherMatrix, PApplet parent){
        this.parent = parent;
        vertexCount = otherMatrix.length;
        matrix = new int[vertexCount][vertexCount];

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
        for (int x = 0; x < vertexCount; x++)
            vertexList.add(new Vertex(temp % (x + 15) * x + temp * (x + 15),
                    temp % (x + 5) * temp + temp * (x + 5), VERTEX_SIZE, VERTEX_SIZE, VERTEX_COLOR, x, this.parent));

        // Initializes edges
        for (int x = 0; x < vertexCount; x++) {
            for (int y = 0; y < vertexCount; y++) {
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
                    return e;
        }
        return null;
    }

    // Reset colors of graph back to normal
    public void clear(){
        for(Edge e : edgeList)
            e.setColor(EDGE_COLOR);
        for(Vertex v : vertexList)
            v.setColor(VERTEX_COLOR);
    }

    // Saves state of graph to an output file
    public void saveGraph(String filename){
        try{
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.write(String.valueOf(vertexCount)+ "\n");
            for(int i = 0; i < vertexCount; i++){
                for(int j = 0; j < vertexCount; j++)
                    writer.write(matrix[i][j] + " ");
                writer.write("\n");
            }
            writer.close();
            System.out.println("Graph saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Reads adjancy matrix from file
    private void readMatrix(String fileName){
        int i = 0, j = 0;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            String  numVertices = scanner.next();
            this.vertexCount = Integer.parseInt(numVertices);
            matrix = new int[vertexCount][vertexCount];
            while(scanner.hasNext()){
                int n = scanner.nextInt();
                matrix[i][j] = n;
                j++;

                if(j == vertexCount){
                    i++;
                    j = 0;
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Returns a visual representation of the adjancy matrix, as a String.
    public String matrixToString(){
        String result = "";
        for(int i = 0; i < vertexCount; i++){
            for(int j = 0; j < vertexCount; j++){
                result += (matrix[i][j] + " ");
            }
            result += "\n";
        }
        return result;
    }

    void addVertex(Vertex v){
        vertexList.add(v);
        resizeMatrix();
    }


    // Increases size of the matrix diagonally by 1
    private void resizeMatrix(){
        int[][] newMatrix = new int[vertexCount+1][vertexCount+1];
        for(int i = 0; i < vertexCount; i++){
            for(int j = 0; j < vertexCount; j++)
                newMatrix[i][j] = this.matrix[i][j];
        }
        this.matrix = newMatrix;
        vertexCount++;
    }

    public void addEdge(Vertex s, Vertex d, int c, int w){
        edgeList.add(new Edge(s,d,c,w));
        // Updates adjancy matrix
        matrix[s.getId()][d.getId()] = 1;
        matrix[d.getId()][s.getId()] = 1;
    }

    public Vertex getVertex(int pos){
        return vertexList.get(pos);
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public ArrayList<Vertex> getVertexList () { return vertexList; }

    // Returns entire adjancy matrix
    public int[][] getMatrix() { return matrix; }

    // Returns index of the graph's adjancy matrix
    public int getIndex(int x, int y) { return matrix[x][y]; }

    public PApplet parent() { return parent; }

}
