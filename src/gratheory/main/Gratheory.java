package gratheory.main;
import gratheory.algorithms.Algorithms;
import processing.core.PApplet;

// Main driver class
public class Gratheory extends PApplet{

    public static void main(String[] args) { PApplet.main("gratheory.main.Gratheory"); }
    public static final int SCREEN_WIDTH   = 1000;
    public static final int SCREEN_HEIGHT  = 650;
    public static final int GRAY           = 167;
    public static final int WHITE          = 255;
    public static final int BLACK          = 0;
    private final String INPUT_FILE        = "/Users/Blake/IdeaProjects/Gratheory/src/gratheory/resources/AdjancyMatrix.txt";
    private Graph graph;
    private ButtonList vertexOptions, graphOptions;
    private boolean vertexClicked, addEdgeSelected, graphOptionWindow;
    private int id = 0;
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


    public void update(){
        if(vertexClicked){
            vertexOptions.setCoordinates(graph.getVertex(id).getxCoord() + 10, graph.getVertex(id).getyCoord() + 10);
            vertexOptions.display();
            graphOptionWindow = false;
        }
        else if(graphOptionWindow){
            graphOptions.display();
        }
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

    // Checks if user is clicking a vertex
    private void checkIfClickingVertex(){
        for (Vertex v : graph.getVertexList()) {
            if (v.overVertex(mouseX, mouseY)) {
                if (vertexClicked)
                    vertexClicked = false;
                else {
                    id = v.getId();
                    vertexClicked = true;
                }
            }
        }

        // Performs a specific operation on a vertex
        if (vertexClicked) {
            if (vertexOptions.getButton(0).overButton(mouseX, mouseY)) {
                graph.clear();
                Algorithms.DFS(graph, graph.getVertex(id));
            }
            else if (vertexOptions.getButton(1).overButton(mouseX, mouseY)) {
                graph.clear();
                Algorithms.BFS(graph, graph.getVertex(id));
            }
            else if (vertexOptions.getButton(2).overButton(mouseX, mouseY)) {
                startVertex = id;
                addEdgeSelected = true;
                vertexClicked = false;
            }
            else if(addEdgeSelected){
                endVertex = id;
                graph.addEdge(graph.getVertex(startVertex), graph.getVertex(endVertex),WHITE,0);
                addEdgeSelected = false;
                System.out.println(graph.matrixToString());
            }
        }
    }

    // Checks if user is clicking graph options menu
    private void checkIfClickingGraphOptions(){
        if(graphOptions.getButton(0).overButton(mouseX,mouseY)){
            graph.addVertex(new Vertex(mouseX, mouseY, graph.VERTEX_SIZE, graph.VERTEX_SIZE, graph.VERTEX_COLOR, graph.getVertexCount(), this));
            System.out.println(graph.matrixToString());
        }
        else if(graphOptions.getButton(1).overButton(mouseX,mouseY)){
            graph.saveGraph(INPUT_FILE);
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

}
