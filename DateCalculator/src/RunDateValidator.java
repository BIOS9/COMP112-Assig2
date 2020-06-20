package src;

// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP112 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP112 - 2018T1, Assignment 2
 * Name: Matthew Corfiatis
 * Username: CorfiaMatt
 * ID: 300447277
 */

import ecs100.*;

/** Run DateValidator methods */

public class RunDateValidator {

    public static void main(String[] args){
        DateValidator dv = new DateValidator();
        UI.initialise();
        UI.addButton("Clear", UI::clearText );
        UI.addButton("Core: Check date is valid", dv::doCore );
        UI.addButton("Completion: Find day of the week", dv::doCompletion );
        UI.addButton("Challenge: Display monthly calendar", dv::doChallenge );
        UI.addButton("Quit", UI::quit );
        UI.setDivider(0.5);       // Expand the text area
    }
}
