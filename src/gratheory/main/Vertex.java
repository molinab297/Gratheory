package gratheory.main;
import processing.core.PApplet;

import java.util.HashSet;


/* Class that represents a single vertex to be displayed on a Graph. */
public class Vertex{
    private int xCoord, yCoord, width, height, color, id;

    public Vertex(int x, int y, int w, int h, int c, int id){
        xCoord = x;
        yCoord = y;
        width  = w;
        height = h;
        color  = c;
        this.id = id;
    }

    public void display(PApplet parent){
        parent.fill(color);
        parent.ellipse(xCoord, yCoord, width, height);
    }

    public void setXCoord(int x){ xCoord = x; }
    public void setYCoord(int y){ yCoord = y; }
    public void setColor(int c){ color = c; }
    public int  xCoord(){ return xCoord; }
    public int  yCoord(){ return yCoord; }
    public int  color(){ return color; }
    public int  width(){ return width; }
    public int  height(){ return height; }
    public int  id() { return id; }
}
