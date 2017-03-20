package gratheory.main;
import processing.core.PApplet;
import java.util.Vector;

/* Main driver class */
public class Gratheory extends PApplet{

    public static void main(String[] args) { PApplet.main("gratheory.main.Gratheory"); }
    public static final int SCREEN_WIDTH  = 1000;
    public static final int SCREEN_HEIGHT = 650;
    private Graph graph;

    public void settings(){
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void setup() {
        graph = new Graph(new int[][]{{0, 1, 0, 1}, {1, 0, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 0}});
        strokeWeight(2);
        stroke(255);
        background(0, 0, 20);
    }

    // Main draw method is all cleaned up and organized now :-)
    public void draw() {
        background(0, 0, 20);
        graph.display(this);
        checkIfDragging();
    }


    /* Checks if vertex is being dragged. If it is, make vertex follow user's mouse. */
    public void checkIfDragging(){
        for (int x = 0; x < graph.getRows(); x++) {
            Vertex curr = graph.getVertex(x);
            if (dist(curr.getXCoord(), curr.getYCoord(), mouseX, mouseY) < 25 / 2) {
                if (mousePressed) {
                    curr.setXCoord(mouseX);
                    curr.setYCoord(mouseY);
                }
            }
        }
    }


}
