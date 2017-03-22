package gratheory.main;
import processing.core.PApplet;

import java.util.ArrayList;

/*******************************************************************
 *  CLASS Menu EXTENDS Button
 *
 *  OVERVIEW: A class that represents a Menu object. A Menu contains a
 *  drop down list of clickable buttons. Being that the primary purpose
 *  of a Menu object is to only display the drop down menu of buttons,
 *  the functionality of the buttons will need to be implemented.
 *
 *  CONSTRUCTOR PARAMETERS:
 *   - (Refer to CLASS Button)
 ********************************************************************/
public class Menu extends Button{

    private ArrayList<Button> buttonList; // holds the list of buttons

    public Menu(int x, int y, int w, int h, int buttonColor, int textColor, String text){
        super(x,y,w,h,buttonColor,textColor, text);
        buttonList = new ArrayList<>();
    }

    public void addButton(String text){
        buttonList.add(new Button(0,0,0,0,super.color(),super.textColor(),text));
    }

    public void addButton(int buttonColor, String text){
        buttonList.add(new Button(0,0,0,0,buttonColor,super.textColor(),text));
    }

    public void addButton(int buttonColor, int textColor, String text){
        buttonList.add(new Button(0,0,0,0,buttonColor,textColor,text));
    }

    // Displays the menu button
    public void display(PApplet p){
        super.display(p);
    }

    // Displays a dropdown list of all the other buttons
    public void displayOptions(PApplet p){
        int x = super.xCoord();
        int y = super.yCoord();
        int width = super.width();
        int height = super.height();
        y = y + height;

        for(Button b : buttonList){
            b.setCoordinates(x,y);
            b.setSize(width,height);
            b.display(p);
            y = b.yCoord() + b.height();
        }
    }

    // Detects if a mouse is hovering over this object
    public boolean overButton(int mouseX, int mouseY){
        return super.overButton(mouseX,mouseY);
    }

}
