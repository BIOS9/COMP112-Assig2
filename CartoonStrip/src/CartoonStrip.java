// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP112 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP112 - 2018T1, Assignment 2
 * Name: Matthew Corfiatis
 * Username: CorfiaMatt
 * ID: 300447277
 */

import ecs100.*;

/** Program to create simple animated cartoon strips using the
 *  CartoonCharacter class.
 */

public class CartoonStrip{

    /** animate creates two cartoon characters on the window.
     *  Then animates them according to a fixed script by calling a series
     *  of methods on the characters.
     */
    public void animate(){
        gameBook();
        /*
        //Core animate method, switched to run completion code
        CartoonCharacter[] chars = new CartoonCharacter[]{
                new CartoonCharacter(50, 100, "green"),
                new CartoonCharacter(-70, 100, "blue")
        };
        for(int i = 0; i < 6; i++) chars[0].walk(20);
        for(int i = 0; i < 4; i++) { chars[1].walk(20); chars[0].walk(20); }
        chars[1].speak("Hey dude.");
        chars[0].lookLeft();
        chars[0].speak("Oh hi.");
        chars[1].speak("Long time no see.");
        chars[0].speak("Yea.");
        chars[0].speak("It's been a while.");
        chars[1].speak("We should catch up.");
        chars[0].speak("Yea definitely!");
        chars[0].speak("Coffee?");
        chars[1].speak("Sounds great.");
        for(int i = 0; i < 6; i++) chars[0].walk(20);
        chars[1].lookLeft();
        for(int i = 0; i < 10; i++) { chars[1].walk(20); chars[0].walk(20); }*/
    }

    /** threeDancers creates three cartoon characters on the window.
     *  Then makes each character do the same little dance in turn,
     *  by calling the dance method.
     */
    public void threeDancers(){
        CartoonCharacter[] chars = new CartoonCharacter[]{
                new CartoonCharacter(400, 100, "yellow"),
                new CartoonCharacter(250, 100, "blue"),
                new CartoonCharacter(100, 100, "green")
        };
        for(CartoonCharacter c : chars) dance(c);
    }

    /** Makes a character do a little dance.
     * Has one parameter - a CartoonCharacter object
     */
    public void dance(CartoonCharacter face){
        face.walk(30);
        face.speak("Yea!");
        face.lookLeft();
        face.walk(30);
        face.speak("Woo!");
        face.lookRight();
        face.walk(30);
        face.speak("Haa!");
    }

    /**
     * Method to animate a very simple story and ask for user input.
     */
    public void gameBook()
    {
        CartoonCharacter[] chars = new CartoonCharacter[]{
                new CartoonCharacter(250, 100, "green"),
                new CartoonCharacter(100, 100, "blue")
        };
        chars[0].lookLeft();
        chars[1].speak("Hey dude.");
        chars[0].speak("Sup.");
        chars[1].speak("Long time no see.");
        chars[0].speak("Yea.");
        chars[0].speak("It's been a while.");
        chars[1].speak("You up for coffee?");
        UI.println("Friend asks if you want to go out for coffee:");
        UI.println("1. Lets go!");
        UI.println("2. Cant, I'm busy.");

        boolean validAnswer = false;
        while(!validAnswer)
        switch (UI.askInt("Select a response")) {
            case 1:
                validAnswer = true;
                gbOption1(chars);
                break;
            case 2:
                validAnswer = true;
                gbOption2(chars);
                break;
        }
    }

    /**
     * method to execute first selected option when the user is asked for input
     * @param chars Characters to animate
     */
    private void gbOption1(CartoonCharacter[] chars)
    {
        chars[0].speak("Yea definitely!");
        chars[0].speak("Lets go!");
        chars[1].lookLeft();
        for(int i = 0; i < 18; i++) { chars[1].walk(20); chars[0].walk(20); }
    }

    /**
     * method to execute second selected option when the user is asked for input
     * @param chars Characters to animate
     */
    private void gbOption2(CartoonCharacter[] chars)
    {
        chars[0].speak("Sorry, I can't.");
        chars[0].speak("I am busy.");
        chars[1].speak("That's ok.");
        chars[1].speak("See ya.");
        chars[0].speak("Bye.");
        chars[1].lookLeft();
        chars[0].lookRight();
        for(int i = 0; i < 20; i++) { chars[1].walk(20); chars[0].walk(20); }
    }
}

