package gratheory.main;
import gratheory.algorithms.Algorithms;
import processing.core.PApplet;

public class Gratheory extends PApplet{

    public static void main(String[] args) { PApplet.main("gratheory.main.Gratheory"); }

    // Frequently used Processing color constants
    public static final int WHITE = 255, GRAY = 167, BLACK = 0;

    public static final int SCREEN_WIDTH   = 1200;
    public static final int SCREEN_HEIGHT  = 750;
    private final String INPUT_FILE        = "/Users/Blake/IdeaProjects/Gratheory/src/gratheory/resources/AdjancyMatrix.txt";
    private Graph graph;
    private ButtonList vertexOptions, graphOptions;
    private boolean vertexClicked, addEdgeSelected, graphOptionWindow;
    private int vertexID = 0;
    private int startVertex, endVertex;

    public void settings(){
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void setup() {

        graph           = new Graph(INPUT_FILE, this);
        vertexOptions   = new ButtonList(0,0,80,30,255, this);
        graphOptions    = new ButtonList(0,0,80,30,255, this);
        vertexOptions.addButton(GRAY, BLACK, "DFS");
        vertexOptions.addButton(GRAY, BLACK, "BFS");
        vertexOptions.addButton(GRAY, BLACK, "Add edge");
        vertexOptions.addButton(GRAY, BLACK, "Hamiltonian");
        vertexOptions.addButton(GRAY, BLACK, "Delete vertex");
        vertexOptions.addButton(GRAY, BLACK, "Delete edge");
        graphOptions.addButton(GRAY, BLACK, "New Vertex");
        graphOptions.addButton(GRAY, BLACK, "Save Graph");


        strokeWeight(2);
        stroke(WHITE);
        background(0, 0, 20);
    }

    public void draw() {
        background(0, 0, 20);
        graph.display();
        update();
    }

    public void mouseClicked() {

        // Left click on vertices to select vertex options or graph options
        if(mouseButton == LEFT){
            checkIfClickingVertex();
            checkIfClickingGraphOptions();
        }
        // Right click to create new vertices
        else if(mouseButton == RIGHT){
            if(graphOptionWindow){
                graphOptionWindow = false;
            }
            else{
                graphOptions.setCoordinates(mouseX, mouseY);
                graphOptionWindow = true;
            }
        }
    }

    // Checks if vertex is being dragged. If it is, make vertex follow user's mouse.
    public void mouseDragged(){
        for (Vertex v : graph.getVertexList())
            if (dist(v.getxCoord(), v.getyCoord(), mouseX, mouseY) < 40) {
                if (mousePressed)
                    v.setCoordinates(mouseX, mouseY);
            }
    }

    public void update(){
        if(vertexClicked){
            vertexOptions.setCoordinates(graph.getVertex(vertexID).getxCoord()
                    +50, graph.getVertex(vertexID).getyCoord()-50);
            vertexOptions.display();
            graphOptionWindow = false;
        }
        else if(graphOptionWindow){
            graphOptions.display();
        }
    }

    // Checks if user is clicking a vertex
    private void checkIfClickingVertex(){
        for (Vertex v : graph.getVertexList()) {
            if (v.overVertex(mouseX, mouseY)) {
                if (vertexClicked)
                    vertexClicked = false;
                else {
                    vertexID = v.getId();
                    vertexClicked = true;
                }
            }
        }

        // Performs a specific operation on a vertex
        if (vertexClicked) {
            // Perform DFS
            if (vertexOptions.getButton(0).overButton(mouseX, mouseY)) {
                graph.clear();
                Algorithms.DFS(graph, graph.getVertex(vertexID));
            }
            // Perform BFS
            else if (vertexOptions.getButton(1).overButton(mouseX, mouseY)) {
                graph.clear();
                Algorithms.BFS(graph, graph.getVertex(vertexID));
            }
            // Add edge
            else if (vertexOptions.getButton(2).overButton(mouseX, mouseY)) {
                startVertex = vertexID;
                addEdgeSelected = true;
                vertexClicked = false;
            }
            // Perform Hamiltonian Algorithm
            else if(vertexOptions.getButton(3).overButton(mouseX, mouseY)){
                graph.clear();
                Algorithms.HamiltonianCircuit(graph);
            }
            // Delete vertex
            else if(vertexOptions.getButton(4).overButton(mouseX, mouseY)){

            }
            // Delete edge
            else if(vertexOptions.getButton(5).overButton(mouseX, mouseY)){

            }
            else if(addEdgeSelected){
                endVertex = vertexID;
                graph.addEdge(graph.getVertex(startVertex), graph.getVertex(endVertex),WHITE,100);
                addEdgeSelected = false;
            }
        }
    }

    // Checks if user is clicking graph options menu
    private void checkIfClickingGraphOptions(){
        if(graphOptionWindow) {
            if (graphOptions.getButton(0).overButton(mouseX, mouseY)) {
                graph.addVertex(new Vertex(mouseX, mouseY, graph.VERTEX_SIZE, graph.VERTEX_SIZE, graph.VERTEX_COLOR, graph.getVertexCount(), this));
                graphOptionWindow = false;
            } else if (graphOptions.getButton(1).overButton(mouseX, mouseY)) {
                graph.saveGraph(INPUT_FILE);
                graphOptionWindow = false;
            }
        }
    }


}
