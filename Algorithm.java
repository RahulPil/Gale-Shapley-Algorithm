/*
Class to create stable matches between all people and pets mentioned in input
file.
Does this by reading through the file and using the Pet.java and People.java
classes to create people and pet objects
Functionality includes, using galeShapley algorithm to create stable matches
and using scanners to read files
@author Rahul Pillalamarri
 */

package program;

import java.io.*;
import java.util.*;

public class Algorithm {
    // arrays of people and bet objects to enable referencing the objects
    // created while reading the input file
    private static People[] listOfPeople;
    private static Pets[] listOfPets;

    // arrays of integers to hold the matched up pairs
    private static int [] peopleMatches;
    private static int [] petMatches;

    // a stack of integers to keep track of whether or not and who is
    // unnmatched within the created people objects
    private static final Stack<Integer> unmatchedPeople = new Stack<>();

    //--------------------------Constructor------------------------------------
    // Purpose: To create the Algorithm class as an instantiable object to
    // follow OOP principles
    // Preconditions: Function constructs an Algorithm object which has no data
    // members attached to it. But the object has access to galeShapley,
    // printMatches, and readData functions
    // Postconditions: Function constructs an Algorithm object which has no
    // data members attached to it. But the object has access to galeShapley,
    // printMatches, and readData functions
    Algorithm(){ }

    //--------------------------printMatches-----------------------------------
    // Purpose: To print the final matched up people and pets in the correct
    // format
    // Preconditions: uses a for loop in order to traverse through the
    // listOfPeople, listsOfPets, and peopleMatches arrays in order to
    // print out the correct matched up pairs to the console
    // Postconditions: Prints out the final matched up pairs in the correct
    // formatting to the console by accessing the listOfPeople and listOfPets
    // arrays
    public void printMatches(){
        for (int i = 0; i < listOfPeople.length; i++) {
            System.out.println(listOfPeople[i].getName() + " / "
                    + listOfPets[peopleMatches[i + 1] - 1].getName());
        }
    }

    //-------------------------galeShapley-------------------------------------
    // Purpose: To create stable matching pairs between two types of objects
    // given each object's preference lists
    // Preconditions: Method uses Gale Shapley's algorithm in order to create
    // stable matches between objects defined in input file.
    // program read the input file and created all people and pet objects
    // as well as initialized all arrays defined in as private variables
    // program added all people to the unmatchedPeople stack in order to
    // enable the function to match up pets and people
    // Postconditions: Finds the people-optimal stable matched pairs based on
    // preference lists provided in the file
    public void galeShapley() {
        while(!unmatchedPeople.isEmpty()){

            // getting the index of the first person who has to get matched
            int person_index = unmatchedPeople.pop();

            // initializes the data attributes of the People p object
            // through the listOfPeople array at index person_index
            People p = listOfPeople[person_index-1];
            int pet = p.getNextPreferences();

            // Case 1: Pet is unmatched
            // result: match person and pet
            if(petMatches[pet] == 0){
                petMatches[pet] = person_index;
                peopleMatches[person_index] = pet;
            }

            // Case 2: if pet is matched but the new unmatched person
            // is a better fit than current match
            // Result: replace matching and add the old match back
            // to unmatched people
            else if(listOfPets[pet - 1].betterMatch(person_index,
                    petMatches[pet])){
                    // unmatches old person
                    unmatchedPeople.push(petMatches[pet]);
                    peopleMatches[petMatches[pet]] = 0;
                    // matches with the new person
                    petMatches[pet] = person_index;
                    peopleMatches[person_index] = pet;

            // Case 3: pet rejects person
            // Result: person is pushed back into the unmatched stack
            }else{
                unmatchedPeople.push(person_index);
            }
        }
    }

    //--------------------------readData--------------------------------------
    // Purpose: To read the input file and create pet/people objects using the
    // pet and people classes. Also to initialize and set the arrays defined
    // in the private variables section of the file which will be used in the
    // galeShapley method
    // Preconditions: Takes in an input file and uses a scanner to read through
    // the file and create people and pet objects as well as initialize the
    // listOfPeople, listOfPets, peopleMatches, and petMatches classes.
    // Postconditions: Creates pet and people objects as well as initializes,
    // the arrays defined in the private variables section of the file by
    // reading the input file. It does this by using a scanner and multiple for
    // loops in order to add different types of data to different arrays.
    // The function first reads the number of people/pet objects there are in
    // the first line. It then initializes an array of strings that hold the
    // names of each person as it reads through the file using a for loop.
    // Once it gets to the preferences list, it uses two for loops in order to
    // add all the preference integers to an array and use that array to create
    // a people object with the name of the person by accessing the index of
    // the peopleNames array thats associated with the preference list
    // function does the same thing for the pet objects
    public void readData() throws FileNotFoundException {
        // creates a file input and a scanner that is used to read the file
        File input_file = new File("src\\program\\program1data.txt");
        Scanner fileReader = new Scanner(input_file);
        // gets the number of people and pet objects in the file
        // this is used multiple times to run for loops and initiliaze key
        // arrays that are used to hold the pet and people objects and their
        // attributes
        int n = Integer.parseInt(fileReader.nextLine());

        // initializing the private variable arrays
        listOfPeople = new People[n];
        listOfPets = new Pets[n];
        peopleMatches = new int[n+1];
        petMatches = new int[n+1];

        // arrays that are used to create people and pet objects in order to
        // make it easier to associate each preference list to a specifc name
        // to create the people and pet object
        String [] peopleNames = new String[n];
        String [] petNames = new String[n];

        // adds all the people names to the array
        for(int i = 0; i < n; i++){
            peopleNames[i] = fileReader.nextLine();
        }

        // constructs all the people objects by using a double for loop
        // that associates a preference list array with a name that is used to
        // construct the object
        for(int i = 0; i < n; i++){
            int[] peopleConstructorArray = new int[n];
            for(int j = 0; j < n; j++){
                peopleConstructorArray[j] = fileReader.nextInt();
            }
            // constructs people object
            People p = new People(peopleNames[i], peopleConstructorArray);
            listOfPeople[i] = p;
            // adds the index of the people object to the stack that keeps
            // track of all unmatchedPeople, which will be used to in the
            // stable matching algorithm
            unmatchedPeople.push(i+1);
        }
        //moves onto the next line
        fileReader.nextLine();

        // adds all pet names to the string array
        for(int i = 0; i < n; i++){
            petNames[i] = fileReader.nextLine();
        }

        // constructs all the pet objects by using a double for loop
        // that associates a preference list array with a name that is used to
        // construct the object
        for(int i = 0; i < n; i++){
            int[] petConstructorArray = new int[n];
            for(int j = 0; j < n; j++){
                int z = fileReader.nextInt()-1;
                petConstructorArray[z] = j;
            }
            // constructs pet objects
            Pets y = new Pets(petNames[i], petConstructorArray);
            listOfPets[i] = y;
        }
        // closes the file to stop reading
        fileReader.close();
    }
}
