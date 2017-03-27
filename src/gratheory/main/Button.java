package gratheory.main;
import processing.core.PApplet;

/*******************************************************************
 *  CLASS Button
 *
 *  OVERVIEW: A class that represents a 'label' which can act as
 *  a clickable button. Use the overButton method and pass in mouseX
 *  and mouseY to detect if a mouse is hovering over it.
 *
 *  CONSTRUCTOR PARAMETERS:
 *   x (int)           : x coordinate of button
 *   y (int)           : y coordinate of button
 *   w (int)           : width of button
 *   h (int)           : height of button
 *   buttonColor (int) : RGB/Grayscale color of button
 *   tColor (int)      : RGB/Grayscale color of text
 *   t (String)        : Text of the button
 *
 *   Note: to pass an RGB color, pass parameter as (PApplet object).color(r,g,b).
 ********************************************************************/
public class Button extends Shape{
    private String text;
    private int textColor;

    public Button(int x, int y, int w, int h, int buttonColor, int tColor, String t, PApplet parent){
        super(x,y,w,h,buttonColor,parent);
        textColor = tColor;
        text   = t;
    }

    public void display(){
        parent.fill(getColor());
        parent.rect(getxCoord(), getyCoord(), getWidth(), getHeight());
        parent.fill(textColor);
        parent.text(text,getxCoord()+getWidth()/2,getyCoord()+getHeight()/2);
    }

    // Detects if a mouse is hovering over this object
    public boolean overButton(int mouseX, int mouseY) {
        return (mouseX >= getxCoord() && mouseX <= getxCoord() + getWidth()
                && mouseY >= getyCoord() && mouseY <= getyCoord() + getHeight());
    }

    public void setText(String t){
        text = t;
    }
    public void setTextColor(int tc) {
        textColor = tc;
    }
    public int textColor() {
        return textColor;
    }
    public String getText() { return text; }

}
