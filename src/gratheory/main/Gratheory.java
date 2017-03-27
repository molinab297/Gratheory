package gratheory.main;
import gratheory.algorithms.Algorithms;
import processing.core.PApplet;

// Main driver class
public class Gratheory extends PApplet{

    public static void main(String[] args) { PApplet.main("gratheory.main.Gratheory"); }
    private static final int SCREEN_WIDTH  = 1000;
    private static final int SCREEN_HEIGHT = 650;
    private static final int GRAY          = 167;
    private Graph graph;
    private Menu menu, options;
    private boolean menuClicked, vertexClicked;
    private int id = 0;

    public void settings(){
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void setup() {
        graph = new Graph(new int[][]{{0,1,1,1,0,0,1},
                                      {1,0,1,0,0,0,0},
                                      {1,1,0,1,1,0,0},
                                      {1,0,1,0,1,1,0},
                                      {0,0,1,1,0,0,0},
                                      {0,0,0,1,0,0,1},
                                      {1,0,0,0,0,1,0}},this);


        menu = new Menu(SCREEN_WIDTH-100,20,80,30,color(255),0,"Menu", this);
        menu.addButton(GRAY, "Add Vertex");
        menu.addButton(GRAY, "Add Edge");
        menu.addButton(GRAY, "Save Graph");


        options = new Menu(SCREEN_WIDTH-100,20,80,30,color(255),0,"Options", this);
        options.addButton(GRAY, "DFS");
        options.addButton(GRAY, "BFS");



        strokeWeight(2);
        stroke(255);
        background(0, 0, 20);
    }

    public void draw() {
        background(0, 0, 20);
        graph.display();
        menu.display();
        update();
    }


    public void update(){
        if(menuClicked)
            menu.displayOptions();

        if(vertexClicked){
            options.setCoordinates(graph.getVertex(id).getxCoord() + 10, graph.getVertex(id).getyCoord() + 10);
            options.display();
            options.displayOptions();
        }
    }

    public void mouseClicked() {

        // Checks if user to clicking Menu button
        if (menu.overButton(mouseX, mouseY)) {
            if (menuClicked)
                menuClicked = false;
            else
                menuClicked = true;
        }

        // Checks if user is clicking a vertex
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

        // Runs algorithm on specified vertex
        if (vertexClicked) {
            if (options.getButton(0).overButton(mouseX, mouseY)) {
                graph.clear();
                Algorithms.DFS(graph, graph.getVertex(id));
            }
            else if (options.getButton(1).overButton(mouseX, mouseY)) {
                graph.clear();
                Algorithms.BFS(graph, graph.getVertex(id));
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

}
