package gratheory.main;
import processing.core.PApplet;


/* Class that represents a single vertex to be displayed on a Graph. */
public class Vertex{
    private int xCoord, yCoord, width, height, color;

    public Vertex(int x, int y, int w, int h, int c){
        xCoord = x;
        yCoord = y;
        width  = w;
        height = h;
        color  = c;
    }

    void display(PApplet parent){
        parent.fill(color);
        parent.ellipse(xCoord, yCoord, width, height);
    }

    void setXCoord(int x){ xCoord = x; }
    void setYCoord(int y){ yCoord = y; }
    void setColor(int c){ color = c; }
    int  getXCoord(){ return xCoord; }
    int  getYCoord(){ return yCoord; }
    public int  getColor(){ return color; }
    public int  getWidth(){ return width; }
    public int  getHeight(){ return height; }
}
