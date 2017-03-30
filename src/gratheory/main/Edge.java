package gratheory.main;
import processing.core.PApplet;

/*******************************************************************
 *  CLASS Edge
 *
 *  OVERVIEW: A class that represents a single Edge object. An edge
 *  object is defined to have a source(a) and destination vertex(b),
 *  although in an undirected graph, this will not matter which vertex
 *  is which.
 *
 *  CONSTRUCTOR PARAMETERS:
 *   a (Vertex)       : a connected vertex, A
 *   b (Vertex)       : a connected vertex, B
 *   color (int)      : color of edge
 *   weight (int)     : weight of edge
 *
 ********************************************************************/
public class Edge {

    private Vertex a, b;
    private int color, weight;

    public Edge(){
        a      = null;
        b      = null;
        color  = 0;
        weight = 0;
    }

    public Edge(Vertex a, Vertex b, int color, int weight){
        this.a = a;
        this.b = b;
        this.color  = color;
        this.weight = weight;
    }

    // Draws individual edge to screen
    public void display(PApplet parent){
        parent.stroke(color);
        parent.line(a.getxCoord(),a.getyCoord(),b.getxCoord(),b.getyCoord());
        parent.stroke(255);
    }

    public void setWeight(int w){
        weight = w;
    }
    public void setColor(int c){ color = c; }
    public void connect(Vertex a, Vertex b){
        this.a = a;
        this.b = b;
    }
    public int getColor() {
        return color;
    }
    public int getWeight() {
        return weight;
    }
    public boolean connects(Vertex a, Vertex b){
        return (this.a == a && this.b == b || this.b == a && this.a == b);
    }

}
