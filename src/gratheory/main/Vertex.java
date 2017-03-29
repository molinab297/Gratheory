package gratheory.main;
import processing.core.PApplet;


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

    public Vertex(int x, int y, int w, int h, int c, int id, PApplet parent){
        super(x,y,w,h,c, parent);
        this.id = id;
    }

    public void display(){
        parent.fill(getColor());
        parent.ellipse(getxCoord(), getyCoord(), getWidth(), getHeight());
    }

    // Detects if a mouse is hovering over this object
    public boolean overVertex(int mouseX, int mouseY) {
        return (mouseX >= getxCoord() && mouseX <= getxCoord() + getWidth()
                && mouseY >= getyCoord() && mouseY <= getyCoord() + getHeight());
    }

    public int  getId() { return id; }
}
