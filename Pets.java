/*
Class to represent Pets
Each pet is given a name and a preference array that stores integers that
corrospond to different integer values
Functionality includes creating Pet objects using the constructor,
getting the preferences list and name of the pet object, and finding the better
match for a particular pet object
@author Rahul Pillalamarri
 */

package program;

public class Pets {
    private String name;
    private int[] petPreferences;

    //--------------------------Constructor------------------------------------
    // Purpose: To create a Pet objects
    // Preconditions: Creates objects with a string and int array attributes
    // Postconditions: Creates pet object with names and preferences set
    Pets(String newName, int [] newPetPreferences){
        name = newName;
        petPreferences = newPetPreferences;
    }

    //--------------------------getName----------------------------------------
    // Purpose: To get the name of a pet object
    // Preconditions: returns the name of a pet object
    // Postconditions: returns the name of a pet object
    public String getName(){
        return name;
    }

    //--------------------------getPreference----------------------------------
    // Purpose: To get the preferences of a pet object
    // Preconditions: returns the preference array of a pet object
    // Postconditions: returns the preference array of a pet object
    public int[] getPreferences(){
        return petPreferences;
    }

    //--------------------------betterMatch------------------------------------
    // Purpose: To determine if a person is more preffered in comparison to
    // another person
    // Preconditions: compares two integers that represent the preference nums
    // of two seperate people to determine the greater integer
    // Postconditions: returns true or false whether or not the first integer
    // passed into the function is more preffered or not
    public boolean betterMatch(int person1, int person2){
        return petPreferences[person1 - 1] < petPreferences[person2 - 1];
    }

}

