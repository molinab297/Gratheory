package gratheory.main;
import processing.core.PApplet;
import java.util.ArrayList;

/*******************************************************************
 *  CLASS ButtonList EXTENDS Shape
 *
 *  OVERVIEW: A class that represents a Menu object. While a Menu is
 *  a type of button, it contains a drop down list of clickable buttons.
 *  Being that the primary purpose of a Menu object is to only display
 *  the drop down menu of buttons, the functionality of the buttons will
 *  need to be implemented.
 *
 *  CONSTRUCTOR PARAMETERS:
 *   - (Refer to CLASS Button)
 ********************************************************************/
public class ButtonList extends Shape{

    private ArrayList<Button> buttonList; // holds the list of buttons

    public ButtonList(int xCoord, int yCoord, int width, int height, int color, PApplet parent){
        super(xCoord, yCoord, width, height, color, parent);
        buttonList = new ArrayList<>();
    }

    public void addButton(Button newButton){
        buttonList.add(newButton);
    }

    public void addButton(int buttonColor, int textColor, String text){
        buttonList.add(new Button(0,0,0,0,buttonColor,textColor,text, parent));
    }

    /// Displays the menu button
    public void display(){
        int x = super.getxCoord();
        int y = super.getyCoord();
        int width = super.getWidth();
        int height = super.getHeight();
        y = y + height;

        for(Button b : buttonList){
            b.setCoordinates(x,y);
            b.setSize(width,height);
            b.display();
            y = b.getyCoord() + b.getHeight();
        }
    }


    public Button getButton(int pos){
        return buttonList.get(pos);
    }

}
