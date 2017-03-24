package gratheory.main;
import processing.core.PApplet;

/*******************************************************************
 *  CLASS Edge
 *
 *  OVERVIEW: A class that represents a single Edge object. An edge
 *  object is defined to have a source and destination vertex, although
 *  in an undirected graph, this will not matter which vertex is which.
 *
 *  CONSTRUCTOR PARAMETERS:
 *   source <Vertex>  : source vertex
 *   dest<Vertex>     : destination vertex
 *   color<int>       : color of edge
 *   weight<int>      : weight of edge
 *
 ********************************************************************/
public class Edge {

    private Vertex source, dest;
    private int color, weight;
    PApplet parent;

    public Edge(){
        source = null;
        dest = null;
        color = 0;
        weight = 0;
    }

    public Edge(Vertex s, Vertex d, int c, int w){
        source = s;
        dest = d;
        color = c;
        weight = w;
    }

    /* Draws individual edge to screen */
    public void display(PApplet parent){
        parent.stroke(color);
        parent.line(source.xCoord(),source.yCoord(),dest.xCoord(),dest.yCoord());
    }

    public void setWeight(int w){
        weight = w;
    }
    public void setColor(int c){ color = c; }
    public void setSource(Vertex s){
        source = s;
    }
    public void setDest(Vertex d){
        dest = d;
    }
    public Vertex source() { return source; }
    public Vertex dest() { return dest; }
    public int color() {
        return color;
    }
    public int weight() {
        return weight;
    }
}
