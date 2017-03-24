package gratheory.main;
import gratheory.algorithms.BFS;
import processing.core.PApplet;

/* Main driver class */
public class Gratheory extends PApplet{

    public static void main(String[] args) { PApplet.main("gratheory.main.Gratheory"); }
    private static final int SCREEN_WIDTH  = 1000;
    private static final int SCREEN_HEIGHT = 650;
    private Graph graph;
    private Menu menu;
    private boolean menuClicked;

    public void settings(){
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void setup() {
        graph = new Graph(new int[][]{{0, 1, 0, 1},
                                      {1, 0, 1, 0},
                                      {0, 1, 0, 1},
                                      {1, 0, 1, 0}}, this);

        menu = new Menu(SCREEN_WIDTH-100,20,80,30,color(255),0,"Menu", this);
        menu.addButton(167, "New Vertex");
        menu.addButton(167, "New Edge");
        menu.addButton(167, "Save");

        BFS bfs = new BFS(graph, graph.getVertex(0));


        strokeWeight(2);
        stroke(255);
        background(0, 0, 20);
    }

    // Main draw method is all cleaned up and organized now :-)
    public void draw() {
        background(0, 0, 20);
        graph.display();
        menu.display();
        update();
        checkIfDragging();
    }

    public void update(){
        if(menuClicked)
            menu.displayOptions();
    }

    public void mouseClicked(){
        if(menu.overButton(mouseX,mouseY)) {
            if(menuClicked)
                menuClicked = false;
            else
                menuClicked = true;
        }
    }

    /* Checks if vertex is being dragged. If it is, make vertex follow user's mouse. */
    public void checkIfDragging(){
        for (int x = 0; x < graph.rows(); x++) {
            Vertex curr = graph.getVertex(x);
            if (dist(curr.xCoord(), curr.yCoord(), mouseX, mouseY) < 25 / 2) {
                if (mousePressed) {
                    curr.setXCoord(mouseX);
                    curr.setYCoord(mouseY);
                }
            }
        }
    }


}
