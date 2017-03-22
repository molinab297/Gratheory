package gratheory.main;
import processing.core.PApplet;
import processing.core.PConstants;

/* Class that represents a single button entity. A button
* represents an option in a Menu object. (Menu contains a
* list of buttons). */
public class Button {
    private String text;
    private int xCoord, yCoord, width, height, color, textColor;

    public Button(int x, int y, int w, int h, int buttonColor, int tColor, String t){
        xCoord = x;
        yCoord = y;
        width  = w;
        height = h;
        color  = buttonColor;
        textColor = tColor;
        text   = t;
    }

    void display(PApplet p){
        p.fill(color);
        p.rect(xCoord, yCoord, width, height);
        p.fill(textColor);
        p.textAlign(PConstants.CENTER, PConstants.CENTER);
        p.text(text,xCoord,yCoord);
    }

    /* Checks if mouse is hovering over button */
    boolean overButton(int x, int y, int width, int height, PApplet p){
        if (p.mouseX >= x && p.mouseX <= x+width &&
                p.mouseY >= y && p.mouseY <= y+height)
            return true;
        else
            return false;
    }

    public void setxCoord(int x){ xCoord = x; }
    public void setyCoord(int y) { yCoord = y; }
    public void setWidth(int w) { width = w; }
    public void setHeight(int h) { height = h; }
    public void setColor(int c) { color = c; }
    public void setTextColor(int tc) { textColor = tc; }
    public int xCoord() { return xCoord; }
    public int yCoord() { return yCoord; }
    public int width() { return width; }
    public int height() { return height; }
    public int color() { return color; }
    public int textColor() { return textColor; }

}
