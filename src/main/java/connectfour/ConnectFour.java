package connectfour;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.io.IOException;

/** 
The ConnectFour class:
1. Manages the player turns
2. Calls Board methods to update the state of the board
3. Asks the Board whether a win has been detected 
4. Tells the TextUI what to print
5. Contains the main method that is run to play the game
@author Jessica Nguyen (1169812)
*/
public class ConnectFour {
    // Declaring instances
    private char playerTurn;
    private int depth; 
    private connectfour.Board board;
    private connectfour.TextUI textUI;

    // Made a constructor to initialize ConnectFour's instances
    public ConnectFour() {
        playerTurn = 'X';
        depth = 0; 
        board = new connectfour.Board();
        textUI = new connectfour.TextUI();
    }

    // Main method, aka. the code that runs
    // *Note: Code is compressed and lacking comments because of method line limit
    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        while (true) {
            Scanner input = new Scanner(System.in);
            game.textUI.printBoard(game.board.getBoard());
            game.textUI.printText(0, game.playerTurn, game.depth);
            while (!game.board.winner(game.depth, game.playerTurn, 0)) {
                if (!game.textUI.inAction()) {
                    game.textUI.printText(1, game.playerTurn, game.depth);
                    // Catches any char input exceptions when asking for an integer input
                    try {
                        game.textUI.setPosition(input.nextInt());
                    } catch (InputMismatchException e) {
                        game.textUI.printText(5, game.playerTurn, game.depth);
                        break;
                    }
                    // Checks if any other errors occurred and sets the inputted position
                    if (!game.errorCheck(game)) {
                        game.playerTurn = switchTurn(game.playerTurn);
                    }
                } else {
                    // Catches any input exceptions when asking for a fileName + file extension
                    try {
                        // Load old games at the beginning or save new games inbetween games
                        String fileName = input.nextLine();
                        if (fileName.contains(".")){
                            game.textUI.printText(7, game.playerTurn, game.depth);
                            if (game.depth == 0){
                                game.board.loadBoard(loadData(fileName, ""));
                                game.depth = countDepth(game.board.getBoard());
                            } else {
                                saveData(fileName,"",new String(game.board.getBoard()));
                            }
                        }
                        game.textUI.printBoard(game.board.getBoard());
                    } catch (InputMismatchException e) {
                        game.textUI.printText(5, game.playerTurn, game.depth);
                        break;
                    }
                }
            }
            // If a request for a rematch exists, resets the depth and board
            if (game.board.winner(game.depth, game.playerTurn, 1)) {
                game.textUI.printText(4, game.playerTurn, game.depth);
                if (game.textUI.rematch(input.next().charAt(0))) {  
                    game.depth = game.board.resetBoard();
                }
            }
        }
    }

    // This method switches the Player's turn (X/O)
    public static char switchTurn(char playerTurn) {
        if (playerTurn == 'X') {
            return 'O';
        } else {
            return 'X';
        }
    }
    
    // This method counts the new depth value from loading a file
    public static int countDepth(char[] board) {
        // Resets the depth value
        int depth = 0;

        // Adds how many X & Os there are to depth
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 'X'||board[i] == 'O') {
                depth = depth + 1;
            }
        }

        return depth;
    }

    /* This method checks for the errors
    If no errors are found, applies the playerTurn to the position on the board*/
    public static boolean errorCheck(ConnectFour game) {
        // If position is out of bounds, return a message
        if (game.textUI.getPosition() > 7 || game.textUI.getPosition() < 0) {
            game.textUI.printText(2, game.playerTurn, game.depth);

        } else if (game.textUI.getPosition() == 0) {
            // If input is 0, ask for a file name
            game.textUI.printText(8, game.playerTurn, game.depth);

        } else {
            // If the column is full, prints out an error message
            if (game.board.columnFull(game.textUI.getPosition())) {
                game.textUI.printText(3, game.playerTurn, game.depth);

            } else {
                // If no errors are found: 
                // Add 1 to depth (# of spots taken on the board)
                game.depth = game.depth + 1;

                // Apply playerTurn (X/O) to the position on the board
                game.board.setBoardPosition(game.textUI.getPosition(), game.playerTurn);
                game.textUI.printBoard(game.board.getBoard());
                game.playerTurn = switchTurn(game.playerTurn);
                game.textUI.printText(0, game.playerTurn, game.depth);
                game.playerTurn = switchTurn(game.playerTurn);
                
                // If the board isn't full, switch turns
                if (game.depth != 42) {
                    return false;
                }
            }
        }
        return true;
    }

    // This method will LOAD a board from a savefile to the current board
    public static String loadData(String filename, String location) {
        // Path is where the program (gradle) is currently running in
        Path path = FileSystems.getDefault().getPath(location, filename);
        String boardString = null;

        // Attempts to read & write the file contents into the string
        try {
            boardString = Files.readString(path);
        } catch(IOException e) {
            // Catches and handles the errors properly
            System.out.println(e.getMessage());
        }

        // Replaces 1s with Xs, 2s with Os, and 0s with ' 's 
        boardString = boardString.replace('1','X');
        boardString = boardString.replace('2','O');
        boardString = boardString.replace('0',' ');

        return boardString;
    }

    // This method will SAVE the current board into a savefile
    public static void saveData(String fileName, String location, String boardString) {
        // Path is where the program (gradle) is currently running in
        Path path = FileSystems.getDefault().getPath(location, fileName);

        // Replaces Xs with 1s, Os with 2s, and ' ' with 0s 
        boardString = boardString.replace('X','1');
        boardString = boardString.replace('O','2');
        boardString = boardString.replace(' ','0');

        // Attempts to write into the file
        try {
            Files.writeString(path,boardString);
        } catch(IOException e) {
            // Catches and handles the errors properly
            System.out.println(e.getMessage());
        }
    }
}
