package gratheory.main;
import processing.core.PApplet;
import processing.core.PConstants;

/*******************************************************************
 *  CLASS Button
 *
 *  OVERVIEW: A class that represents a 'label' which can act as
 *  a clickable button. Use the overButton method and pass in mouseX
 *  and mouseY to detect if a mouse is hovering over it.
 *
 *  CONSTRUCTOR PARAMETERS:
 *   x <int>          : x coordinate of button
 *   y <int>          : y coordinate of button
 *   w <int>          : width of button
 *   h <int>          : height of button
 *   buttonColor<int> : RGB/Grayscale color of button
 *   tColor<int>      : RGB/Grayscale color of text
 *   t<String>        : Text of the button
 *
 *   Note: to pass an RGB color, pass parameter as color(r,g,b).
 ********************************************************************/
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
        p.text(text,xCoord+width/2,yCoord+height/2);
    }

    // Detects if a mouse is hovering over this object
    public boolean overButton(int mouseX, int mouseY) {
        return (mouseX >= xCoord && mouseX <= xCoord + width && mouseY >= yCoord && mouseY <= yCoord + height);
    }

    public void setxCoord(int x){
        xCoord = x;
    }
    public void setyCoord(int y) {
        yCoord = y;
    }
    public void setWidth(int w) {
        width = w;
    }
    public void setHeight(int h) {
        height = h;
    }
    public void setColor(int c) {
        color = c;
    }
    public void setTextColor(int tc) {
        textColor = tc;
    }
    public void setCoordinates(int x, int y) {
        xCoord = x;
        yCoord = y;
    }
    public void setSize(int w, int h){
        width = w;
        height = h;
    }
    public int xCoord() {
        return xCoord;
    }
    public int yCoord() {
        return yCoord;
    }
    public int width() {
        return width;
    }
    public int height() {
        return height;
    }
    public int color() {
        return color;
    }
    public int textColor() {
        return textColor;
    }

}
