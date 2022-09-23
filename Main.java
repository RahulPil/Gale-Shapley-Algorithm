/*
Class to run all functions defined and implemented in Algorithms class
This class uses functions from the Algorithms class in order to produce an
output of stable matches for all the object defined in the input file
The main function creates an Algorithm object in order to access functions
that enable this class to produce stable matches to the console
Functionality includes, calling on functions defined in the algorithms class
@author Rahul Pillalamarri
 */

package program;

import java.io.FileNotFoundException;

public class Main {

    //--------------------------Main-------------------------------------------
    // Purpose: To run functions necessary to create stable matches between
    // pairs in the input file.
    // Preconditions: Functions defined and implemented in Algorithm, Pets, and
    // People classes work and can produce an output with all stable matches of
    // objects
    // Postconditions: Creates an Algorithm object that is then used to gain
    // call on the readData, galeShapely, and printMatches functions defined
    // in the Algorithm class in order to produce an output with all the stable
    // matches of objects in the input file
    public static void main(String [] args) throws FileNotFoundException {
        Algorithm main = new Algorithm();
        main.readData();
        main.galeShapley();
        main.printMatches();
    }
}
