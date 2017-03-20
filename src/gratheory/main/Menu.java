package gratheory.main;
import processing.core.PApplet;

import java.util.ArrayList;

/* A container that holds a list of buttons for performing
 * different actions. */
public class Menu {

    Button menuButton;
    private ArrayList<Button> buttonList; // holds the list of buttons

    public Menu(int x, int y, int w, int h, int buttonColor, int textColor, String text){
        menuButton = new Button(x,y,w,h,buttonColor,textColor, text);
        buttonList = new ArrayList<>();
    }


    void addButton(Button newButton){
        buttonList.add(newButton);
    }

    void removeButton(){

    }
    
    // displays the menu button
    void display(PApplet p){
        menuButton.display(p);
    }

    // displays a dropdown list of all the other buttons
    void displayOptions(PApplet p){

    }

    boolean overButton(int x, int y, int width, int height, PApplet p){
        return menuButton.overButton(x, y, width, height, p);
    }

}
