# Connect Four

The purpose of this program is to allow a user to play a local game of Connect Four with a friend.

## Description

ConnectFour.java will be the one to run the main method that contains method calls from TextUI.java and Board.java. ConnectFour.java also holds the save/load methods and error checker methods. TextUI.java will be responsible for all user interaction, this includes user inputs and prints, in ConnectFour.Java. Board.java will be responsible for using encapsulation to maintain the state of the board and checks if there are any winning 4 in a row patterns. These programs work together to create a working encapsulated game of connect four. The game also has the addition of catching any exceptions, allowing rematches, and saving/loading old game files. 

## Getting Started

### Dependencies

Describe any prerequisites, libraries, OS version, etc., needed before installing and running your program:
* Make sure that the java files and text files are put within the same folders (in the same "connectfour" package) before startup
* Import java.util packages like Scanner and Exceptions
* To load any .txt or .csv files into the program, you must have them within the same folder as the running program (wherever gradle is)

### Executing program

How to build and run Connect Four:
* Make sure you are within the A2 file that build.gradle is within
* In terminal, type "gradle build" to build/compile the program
* In terminal, type "gradle run" to output the command to run the program
* Enter the given command line. (I'll put it here aswell: java -cp build/classes/java/main connectfour.ConnectFour)

Expected Output:
```
| | | | | | | |
|-+-+-+-+-+-+-|
| | | | | | | |
|-+-+-+-+-+-+-|
| | | | | | | |
|-+-+-+-+-+-+-|
| | | | | | | |
|-+-+-+-+-+-+-|
| | | | | | | |
|-+-+-+-+-+-+-|
| | | | | | | |
|-------------|

 1 2 3 4 5 6 7

Player X's turn.

Enter 0 to save/load a datafile
or enter a position between 1 and 7:
```

## Limitations

What isn't done? 
* Implementing toString()
* Save/Load data, ran out of time

What things cause errors? 
* BoardTest.java - For some reason, running testloadBoard() causes testSetBoardPosition() & testResetBoard() to fail. I created @Before and @After to clean up after each test yet the issue still occurs. I even tried using different variable names, cleaning char result, and tested each test seperately (commenting out the others) only to find out that testloadBoard() is the only one with this issue. If you know the solution, please let me know.
* ConnectFour.java - Attempting to load a non-existent fileName in loadData() causes an exception to appear. Spaces in the fileName also cause errors. No time to fix it.

## Author Information

* Name: Jessica Nguyen 
* Student ID: 1169812
* Email Address: jnguye27@uoguelph.ca

## Development History

Keep a log of what things you accomplish when.  
You can use git's tagging feature to tag the versions or you can reference commits.
* 0.6
    * Rearranged some method locations 
    * Save/Load system is somewhat complete
    * Edited BoardTest
    * Edited ReadMe.md
* 0.5
    * playerTurn is now a part of ConnectFour.java (by using constructors)
    * Edited ReadMe.md
* 0.4
    * Any strings from Board.java are now printed in TextUI.java
    * Completed JUnit testing for BoardTest.java
    * Edited JavaDoc comments
    * Edited ReadMe.md
* 0.3
    * All texts are printed by TextUI.java
    * Shorten ConnectFour.java's line length
    * Added InputMismatchException in ConnectFour.java
    * JavaDoc comments are written for Board & other classes
    * Edited ReadMe.md
* 0.2
    * Various bug fixes and optimizations
    * Created a working ConnectFour + Replay option
* 0.1
    * Initial Release

## Acknowledgments

Inspiration and code snippets:
* [How to use JUnit](https://youtu.be/vZm0lHciFsQ)
* [How to use Exception Handling](https://youtu.be/1XAfapkBQjk)
* [How to build and use simple Constructors](https://www.w3schools.com/java/java_constructors.asp)
* [How to use replace() method](https://www.javatpoint.com/java-string-replace)
* [How to use contain() method](https://www.w3schools.com/java/ref_string_contains.asp)
* [CIS2430 Workshop: how to read/write to files]
