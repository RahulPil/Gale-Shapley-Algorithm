/*
Class to represent a People object
The person is represented by a name and a preference array that stores integers
that corrospond to different preference values for different pet objects
Functionality includes creating People objects and initializing the name and
preference array, getting the name of the people object, and getting the next
preffered pet object in the preference array
@author Rahul Pillalamarri
 */

package program;

public class People {
    private String name;
    private int peoplePreferences[];
    private int nextPreference;

    //--------------------------Constructor------------------------------------
    // Purpose: To create a People object
    // Preconditions:reates objects with a string and int array attributes
    // Postconditions: Creates pet object with names and preferences set
    // also sets the nextPreferences integer to 0
    People(String newName, int [] newPref) {
        nextPreference = 0;
        name = newName;
        peoplePreferences = newPref;
    }

    //--------------------------getName----------------------------------------
    // Purpose: To get the name of a pet object
    // Preconditions: returns the name of a People object
    // Postconditions: returns the name of a People object
    public String getName(){
        return name;
    }

    //------------------------------getNextPreferences-------------------------
    // Purpose: To return the next preffered pet in the peoplePreferences array
    // Preconditions: returns the next preffered pet in the preferences array
    // Postconditions: returns the next preffered pet in the preferences array
    public int getNextPreferences(){
        return peoplePreferences[nextPreference++];
    }

}
