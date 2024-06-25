package connectfour;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.After;
//import org.junit.Assert;

/* Notes:
- Tests are run on build unless specifically excluded with -x test.
- The test results are reported in build -> reports -> tests -> test -> index.html 
- It can be run seperately by "gradle clean test"*/

public class BoardTest {
    
    private Board testOne;
    private Board testTwo;
    private Board testThree;
    private boolean result;
    private char[] expectedBoard = {
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
    private char[] editedBoard = {
        '|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|','\n',
        '|','-','+','-','+','-','+','-','+','-','+','-','+','-','|','\n',
        '|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|','\n',
        '|','-','+','-','+','-','+','-','+','-','+','-','+','-','|','\n',
        '|',' ','|',' ','|',' ','|','X','|',' ','|',' ','|',' ','|','\n',
        '|','-','+','-','+','-','+','-','+','-','+','-','+','-','|','\n',
        '|',' ','|',' ','|','O','|','X','|',' ','|',' ','|',' ','|','\n',
        '|','-','+','-','+','-','+','-','+','-','+','-','+','-','|','\n',
        '|',' ','|','O','|','X','|','X','|',' ','|',' ','|',' ','|','\n',
        '|','-','+','-','+','-','+','-','+','-','+','-','+','-','|','\n',
        '|','O','|','X','|','X','|','O','|',' ','|',' ','|',' ','|','\n',
        '|','-','-','-','-','-','-','-','-','-','-','-','-','-','|','\n'
    };
    
    // "Arrange"
    @Before
    public void setUp() {
        testOne = new Board();
        testTwo = new Board();
        testThree = new Board();
    }
    
    // Resets the "Arrange"
    @After
    public void tearDown() {
        testOne = null;
        testTwo = null;
        testThree = null;
    }
    
    @Test
    public void testGetBoard() {
        // Act (Arrays.equals() will see if the arrays are identical)
        char[] actualBoard = testOne.getBoard();
        result = Arrays.equals(expectedBoard, actualBoard);

        // Assert
        assertEquals(true, result);
    }
    
    /*@Test 
    public void testloadBoard() {
        // Act (Arrays.equals() will see if the arrays are identical)
        String editedString = editedBoard.toString();
        testOne.loadBoard(editedString);
        char[] actualBoard = testOne.getBoard();

        //result = Arrays.equals(editedBoard, actualBoard);

        // Assert
        assertEquals(editedBoard, actualBoard);
    }*/

    @Test
    public void testColumnFull() {
        // Test 1 (Not Full)
        result = testOne.columnFull(6);
        assertEquals(false, result);
        
        // Test 2 (Full)
        // Confirmed that setBoardPosition() works first
        testTwo.setBoardPosition(3,'X');
        testTwo.setBoardPosition(3,'O');
        testTwo.setBoardPosition(3,'X');
        testTwo.setBoardPosition(3,'O');
        testTwo.setBoardPosition(3,'X');
        testTwo.setBoardPosition(3,'O');
        result = testTwo.columnFull(3);
        assertEquals(true, result);
    }

    @Test
    public void testSetBoardPosition() {
        // Act
        testOne.setBoardPosition(4,'O');
        testOne.setBoardPosition(4,'X');
        testOne.setBoardPosition(4,'X');
        testOne.setBoardPosition(4,'X');
        testOne.setBoardPosition(3,'X');
        testOne.setBoardPosition(3,'X');
        testOne.setBoardPosition(3,'O');
        testOne.setBoardPosition(2,'X');
        testOne.setBoardPosition(2,'O');
        testOne.setBoardPosition(1,'O');
        // Confirmed that getBoard() works first
        char[] actualBoard = testOne.getBoard();
        result = Arrays.equals(editedBoard, actualBoard);

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void testWinner() {
        // Test 1 (No Wins)
        result = testOne.winner(0,'X',0);
        assertEquals(false, result);

        // Test 2 (Depth = 42, Game is a Tie)
        result = testOne.winner(42,'X',0);
        assertEquals(true, result);

        // Test 3 (Column win, checkColumn() works)
        // Confirmed that setBoardPosition() works first
        testOne.setBoardPosition(5,'X');
        testOne.setBoardPosition(5,'X');
        testOne.setBoardPosition(5,'O');
        testOne.setBoardPosition(5,'O');
        testOne.setBoardPosition(5,'O');
        testOne.setBoardPosition(5,'O');
        result = testOne.winner(6,'X',0);
        assertEquals(true, result);

        // Test 4 (Diagonal win, checkDiagonal() works)
        testTwo.setBoardPosition(4,'O');
        testTwo.setBoardPosition(4,'X');
        testTwo.setBoardPosition(4,'X');
        testTwo.setBoardPosition(4,'X');
        testTwo.setBoardPosition(3,'X');
        testTwo.setBoardPosition(3,'X');
        testTwo.setBoardPosition(3,'O');
        testTwo.setBoardPosition(2,'X');
        testTwo.setBoardPosition(2,'O');
        testTwo.setBoardPosition(1,'O');
        result = testTwo.winner(10,'X',0);
        assertEquals(true, result);

        // Test 5 (Row win, checkRow() works)
        testThree.setBoardPosition(3,'O');
        testThree.setBoardPosition(4,'O');
        testThree.setBoardPosition(5,'O');
        testThree.setBoardPosition(6,'O');
        result = testThree.winner(4,'X',0);
        assertEquals(true, result);
    }

    @Test
    public void testResetBoard() {
        // Act
        testOne.setBoardPosition(3,'X');
        testOne.resetBoard();
        char[] actualBoard = testOne.getBoard();
        result = Arrays.equals(expectedBoard, actualBoard);
        
        // Assert
        assertEquals(true, result);
    }
}