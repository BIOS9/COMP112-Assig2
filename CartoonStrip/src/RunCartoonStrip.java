// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP112 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP112 - 2018T1, Assignment 2
 * Name: Matthew Corfiatis
 * Username: CorfiaMatt
 * ID: 300447277
 */

import ecs100.*;

/** RunCartoonStrip   */
public class RunCartoonStrip{
    public static void main(String[] arguments){
        CartoonStrip cs = new CartoonStrip();
        UI.initialise();
        UI.addButton("Clear", UI::clearGraphics );
        UI.addButton("Animate", cs::animate );
        UI.addButton("Three dancers", cs::threeDancers );
        UI.addButton("Gamebook", cs::gameBook );
        UI.addButton("Quit", UI::quit );
        UI.setDivider(0.35);       // Expand the graphics area
    }

}
