package gratheory.main;


import processing.core.PApplet;

/* Class that represents a single Edge to be displayed on a Graph. */
public class Edge {
    Vertex source, dest;
    int color, weight;

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

    public void display(PApplet p){

    }

    public void setWeight(int w){
        weight = w;
    }
    public void setColor(int c) {
        color = c; }

    public void setSource(Vertex s){
        source = s;
    }
    public void setDest(Vertex d){
        dest = d;
    }
    public Vertex getSource() {
        return source;
    }
    public Vertex getDest() {
        return dest; }

    public int getColor() {
        return color;
    }
    public int getWeight() {
        return weight;
    }
}
