package gratheory.main;
import processing.core.PApplet;

import java.util.HashSet;


/*******************************************************************
 *  CLASS Vertex
 *
 *  OVERVIEW: A class that represents a single Vertex object.
 *
 *  CONSTRUCTOR PARAMETERS:
 *      id (int) : row position in the adjancy matrix represents the vertex's ID.
 *
 *      (Refer to Class Shape to see rest).
 *
 ********************************************************************/
public class Vertex extends Shape{
    private int id;

    public Vertex(int x, int y, int w, int h, int c, int id){
        super(x,y,w,h,c);
        this.id = id;
    }

    public void display(PApplet draw){
        draw.fill(getColor());
        draw.ellipse(getxCoord(), getyCoord(), getWidth(), getHeight());
    }

    public int  getId() { return id; }
}
