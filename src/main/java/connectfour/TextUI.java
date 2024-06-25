package connectfour;

/** 
The TextUI Class:
1. Responsible for all user interaction 
2. Does all of the printing for the program 
3. The only class that takes user input
@author Jessica Nguyen (1169812)
*/
public class TextUI {
    // Declaring & initializing instances
    private static int position;
    private static boolean loadingorsaving = false;

    // Prints out the updated char[] board
    public static void printBoard(char[] board) {
        System.out.println("\n");
        System.out.println(board);
        System.out.println(" 1 2 3 4 5 6 7 \n");
    }

    // A mutator that puts user input (positionInput) into position for TextUI()'s use
    public static void setPosition(int positionInput) {
        position = positionInput;
    }
    
    // An accessor that returns the variable "position" from TextUI()
    public static int getPosition() {
        return position;
    }
    
    // After inputting an (in)correct fileName, we can turn a loop on/off
    // In TextUI because it controls what the user can see
    public static boolean inAction() {
        return loadingorsaving;
    }

    // Prints out text messages, depending on the given type, to main
    public static void printText(int type, char playerTurn, int depth) {
        if (type == 0) {
            System.out.println("Player " + playerTurn + "'s turn.");
        } else if (type == 1) {
            System.out.print("\nEnter 0 to save/load a datafile");
            System.out.print("\nor enter a position between 1 and 7: ");
        } else if (type == 2) {
            System.out.println("\nError - Out of Bounds!");
        } else if (type == 3) {
            System.err.println("\nError - Column Full!");
        } else if (type == 4) {   
            System.out.println("Rematch? (Y/N): ");
        } else if (type == 5) {   
            System.out.println("\nError - Input Mismatch!");
        } else if (type == 6) {  
            if (depth == 42) {
                System.out.println("The game is a tie!");
            } else {
                if (playerTurn == 'X') {
                    System.out.println("The winner is Player O!\n");
                } else {
                    System.out.println("The winner is Player X!\n");
                }
            }
        } else if (type == 7) {   
            loadingorsaving = false;
            if (depth == 0){
                System.out.println("\nFile successfully loaded!");
            } else {
                System.out.println("\nFile successfully saved!");
            }
        } else if (type == 8) { 
            loadingorsaving = true;
            if (depth == 0){
                System.out.println("\nType in a file name (+ its file extension) to load: ");
            } else {
                System.out.println("\nType in a file name (+ its file extension) to save: ");
            }
        }
    }

    /* Requests for a rematch:
    N/n = Exit program
    Y/y = Reset board and replays
    Other = Invalid input, no exceptions needed since it's a char*/
    public static boolean rematch(char input) {
        if (input == 'N'||input == 'n') {
            System.exit(0);
            return false;
        } else if (input == 'Y'||input == 'y') {
            return true;
        } else {
            // If the input is not y or n, print an error message
            System.err.println("\nError - Invalid input!\n");
            return false;
        }
    }
}