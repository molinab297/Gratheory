package gratheory.main;
import processing.core.PApplet;
import java.util.ArrayList;
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

 ********************************************************************/
public class Graph {

    private PApplet parent;
    private ArrayList<Vertex> vertexList;
    private ArrayList<Edge> edgeList;
    private int [][]matrix;
    private final int ROWS, COLUMNS;
    private final int VERTEX_SIZE = 40;
    private final int EDGE_COLOR = 255;

    public Graph(int [][] otherMatrix, PApplet parent){
        this.parent = parent;
        COLUMNS = otherMatrix[0].length;
        ROWS = otherMatrix.length;
        matrix = new int[ROWS][COLUMNS];

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

        // Initialize Vertices *** Need to fix placement algo
        for (int x = 0; x < ROWS; x++)
            vertexList.add(new Vertex(temp % (x + 15) * x + temp * (x + 15),
                    temp % (x + 5) * temp + temp * (x + 5), VERTEX_SIZE, VERTEX_SIZE, 100, x, this.parent));

        // Initializes edges
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLUMNS; y++) {
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

    // Saves state of graph to an output file (created as an adjancy matrix)
    public void saveGraph(){

    }


    void addVertex(int x, int y, int w, int h, int c, int id){
        vertexList.add(new Vertex(x,y,w,h,c,id, this.parent));
    }

    void addVertex(Vertex newVertex){
        vertexList.add(newVertex);
    }

    void addEdge(Vertex s, Vertex d, int c, int w){
        edgeList.add(new Edge(s,d,c,w));
    }

    public Vertex getVertex(int pos){
        return vertexList.get(pos);
    }

    public final int rows() {
        return ROWS;
    }
    public final int columns() {
        return COLUMNS;
    }

    public ArrayList<Vertex> getVertexList () { return vertexList; }

    /* Returns index of the graph's adjancy matrix. */
    public int getIndex(int x, int y) { return matrix[x][y]; }

    public PApplet parent() { return parent; }

}
