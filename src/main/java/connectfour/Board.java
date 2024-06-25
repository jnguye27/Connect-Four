package connectfour;

/** 
The Board class:
1. Maintains the state of the board (where the X/Os are)
2. Creates the string representation of the board that's printed for the user
3. Checks for win conditions (four in a row)
@author Jessica Nguyen (1169812)
*/
public class Board {
    // Declaring & initializing instances
    private static char[] board = {
        '|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|','\n',
        '|','-','+','-','+','-','+','-','+','-','+','-','+','-','|','\n',
        '|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|','\n',
        '|','-','+','-','+','-','+','-','+','-','+','-','+','-','|','\n',
        '|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|','\n',
        '|','-','+','-','+','-','+','-','+','-','+','-','+','-','|','\n',
        '|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|','\n',
        '|','-','+','-','+','-','+','-','+','-','+','-','+','-','|','\n',
        '|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|','\n',
        '|','-','+','-','+','-','+','-','+','-','+','-','+','-','|','\n',
        '|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|','\n',
        '|','-','-','-','-','-','-','-','-','-','-','-','-','-','|','\n'
    };
    private static int[] posToIndex = {
        1,3,5,7,9,11,13,
        33,35,37,39,41,43,45,
        65,67,69,71,73,75,77,
        97,99,101,103,105,107,109,
        129,131,133,135,137,139,141,
        161,163,165,167,169,171,173
    };
    // TextUI is only used here to print line 228's message
    private static connectfour.TextUI gameUI = new connectfour.TextUI();
    
    /** 
    getBoard() will output the game board for display
    @param N/A getBoard() has no parameters
    @return will return the character array of the board
    */
    public static char[] getBoard() {
        return board;
    }

    /** 
    loadBoard() will load a saved game board to the current board
    @param boardString holds the string of the board from file
    @return N/A, loadBoard() returns nothing because it's a void method
    */
    public static void loadBoard(String boardString) {
        for (int i = 0; i < boardString.length(); i++) {
            board[i] = boardString.charAt(i);
        }
    }
    
    /**
    columnFull() checks if the chosen column on the board is full or not
    @param positionUI is the position value, from TextUI, that finds the index value to the board
    @return will return true when the column's full or false when the column's not
    */
    public static boolean columnFull(int positionUI) {
        // index position starts at 0, subtract 1
        positionUI = positionUI - 1;

        if (
        board[posToIndex[positionUI]] != ' ' 
        &&
        board[posToIndex[positionUI+7]] != ' ' 
        &&
        board[posToIndex[positionUI+14]] != ' ' 
        &&
        board[posToIndex[positionUI+21]] != ' ' 
        &&
        board[posToIndex[positionUI+28]] != ' ' 
        &&
        board[posToIndex[positionUI+35]] != ' ' 
        ) {
        return true;
        }
        return false;
    }

    /**
    setBoardPosition() is a mutator that sets the position on the board from ' ' to X or O
    @param positionUI is needed to find the position of the board's index for comparison
    @param playerTurn is the current player's turn (X or O), which is used to replace ' '
    @return N/A, setBoardPosition() returns nothing because it's a void method
    */
    public static void setBoardPosition(int positionUI, char playerTurn) {
        // index position starts at 0, subtract 1
        positionUI = positionUI - 1;
        if (board[posToIndex[positionUI+35]] == ' ') {
            board[posToIndex[positionUI+35]] = playerTurn;
        } else if (board[posToIndex[positionUI+28]] == ' ') {
            board[posToIndex[positionUI+28]] = playerTurn;
        } else if (board[posToIndex[positionUI+21]] == ' ') {
            board[posToIndex[positionUI+21]] = playerTurn;
        } else if (board[posToIndex[positionUI+14]] == ' ') {
            board[posToIndex[positionUI+14]] = playerTurn;
        } else if (board[posToIndex[positionUI+7]] == ' ') {
            board[posToIndex[positionUI+7]] = playerTurn;
        } else {
            board[posToIndex[positionUI]] = playerTurn;
        }
    }
    
    // Checks if there is a four in a row win (in a column)
    private static boolean checkColumn() {
        for (int i = 0; i < 7; i++) {
            if (board[posToIndex[i]] != ' '
                &&
                board[posToIndex[i]] == board[posToIndex[i+7]] 
                && 
                board[posToIndex[i+7]]== board[posToIndex[i+14]]
                &&
                board[posToIndex[i+14]]== board[posToIndex[i+21]]) {
                return true;
            }

            if (board[posToIndex[i+7]] != ' '
                &&
                board[posToIndex[i+7]] == board[posToIndex[i+14]] 
                && 
                board[posToIndex[i+14]]== board[posToIndex[i+21]]
                &&
                board[posToIndex[i+21]]== board[posToIndex[i+28]]) {
                return true;
            }

            if (board[posToIndex[i+14]] != ' '
                &&
                board[posToIndex[i+14]] == board[posToIndex[i+21]] 
                && 
                board[posToIndex[i+21]]== board[posToIndex[i+28]]
                &&
                board[posToIndex[i+28]]== board[posToIndex[i+35]]) {
                return true;
            }
        }
        return false;
    }

    // Checks if there is a four in a row win (in a diagonal)
    private static boolean checkDiagonal() {
        /* Top left to bottom right:
        j = goes to the next row (20 = max position to index), same column
        i = reads the possible winning diagonal rows (7 = jumps down)*/
        for (int j = 0; j <= 20; j+=7) {
            for (int i = j; i < (j+4); i++) {
                if (board[posToIndex[i]] != ' '
                    &&
                    board[posToIndex[i]] == board[posToIndex[i+8]] 
                    &&
                    board[posToIndex[i+8]] == board[posToIndex[i+16]]
                    &&
                    board[posToIndex[i+16]] == board[posToIndex[i+24]]) {
                    return true;
                }
            }
        }

        // Top right to bottom left
        for (int j = 3; j <= 20; j+=7) {
            for (int i = j; i < (j+4); i++) {
                if (board[posToIndex[i]] != ' '
                    &&
                    board[posToIndex[i]] == board[posToIndex[i+6]] 
                    &&
                    board[posToIndex[i+6]] == board[posToIndex[i+12]]
                    &&
                    board[posToIndex[i+12]] == board[posToIndex[i+18]]) {
                    return true;
                }
            }
        }
        return false;
    }

    // Checks if there is a four in a row win (in a row)
    private static boolean checkRow() {
        // j = goes to the next row, same column
        // i = reads the possible winning rows from left to right
        for (int j = 0; j <= 41; j+=7) {
            for (int i = j; i < (j+4); i++) {
                if (board[posToIndex[i]] != ' '
                    &&
                    board[posToIndex[i]] == board[posToIndex[i+1]] 
                    &&
                    board[posToIndex[i+1]] == board[posToIndex[i+2]]
                    &&
                    board[posToIndex[i+2]] == board[posToIndex[i+3]]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
    winner() will check if there are any wins or ties (aka. matching patterns found)
    @param depth is equal to the # of spaces on the board, 42 spaces is the max (max depth = tie)
    @param playerTurn is the current player's turn, if there is a win, switch the player's turn
    @return will return false if there is a winner/tie and true when the game's still ongoing
    */
    public static boolean winner(int depth, char playerTurn, int type) {
        char winner = '?';
        boolean winColumn;
        boolean winDiagonal;
        boolean winRow;

        winColumn = checkColumn();
        winDiagonal = checkDiagonal();
        winRow = checkRow();

        // If any are true, there is a winner
        if (winColumn || winDiagonal || winRow) {
            if (playerTurn == 'X') {
                winner = 'O';
            } else {
                winner = 'X';
            }
        }

        // If depth = 42, no more moves on the board (tie)
        if (depth == 42 || winner != '?') {
            if (type == 1) {
                // Message only prints if type == 1
                gameUI.printText(6, playerTurn, depth);
            }
            return true;
        } 
        return false;
    }

    /**
    resetBoard() will reset the board for rematches (6*7 = 42-1 = 41)
    @param N/A, resetBoard() has no parameters
    @return N/A, resetBoard() returns nothing because it's a void method
    */
    public static int resetBoard() {
        for (int i = 0; i < 42; i++) {
            board[posToIndex[i]] = ' ';
        }

        // Resets the depth in main as well
        return 0;
    }
}

