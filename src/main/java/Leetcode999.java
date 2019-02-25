import org.junit.Assert;

/**
 * https://leetcode.com/problems/available-captures-for-rook/
 *
 * On an 8 x 8 chessboard, there is one white rook.
 * There also may be empty squares, white bishops, and black pawns.
 * These are given as characters 'R', '.', 'B', and 'p' respectively.
 * Uppercase characters represent white pieces, and lowercase characters represent black pieces.
 *
 * The rook moves as in the rules of Chess: it chooses one of four cardinal directions (north, east, west, and south),
 * then moves in that direction until it chooses to stop, reaches the edge of the board,
 * or captures an opposite colored pawn by moving to the same square it occupies.
 * Also, rooks cannot move into the same square as other friendly bishops.
 *
 * Return the number of pawns the rook can capture in one move.
 */
public class Leetcode999 {

    public int numRookCaptures(char[][] board) {
        final char ROOK = 'R';
        final char PAWN = 'p';
        final char BISHOP = 'B';

        int capturedPawns = 0;

        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (board[i][j] == ROOK) {
                    // check vertical: upper and lower
                    // check horizontal: left and right

                    // 1. go up
                    for (int k=i-1; k>=0; k--) {
                        if (board[k][j] == BISHOP) break;
                        if (board[k][j] == PAWN) {
                            capturedPawns++;
                            break;
                        }
                    }

                    // 2. go down
                    for (int k=i+1; k<8; k++) {
                        if (board[k][j] == BISHOP) break;
                        if (board[k][j] == PAWN) {
                            capturedPawns++;
                            break;
                        }
                    }

                    // 3. go left
                    for (int k=j-1; k>=0; k--) {
                        if (board[i][k] == BISHOP) break;
                        if (board[i][k] == PAWN) {
                            capturedPawns++;
                            break;
                        }
                    }

                    // 4. go right
                    for (int k=j+1; k<8; k++) {
                        if (board[i][k] == BISHOP) break;
                        if (board[i][k] == PAWN) {
                            capturedPawns++;
                            break;
                        }
                    }

                    return capturedPawns;
                }
            }
        }

        return capturedPawns;
    }

    static void test1() {
        char[][] board = {
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','p','.','.','.','.'},
                {'.','.','.','R','.','.','.','p'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','p','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'}
        };
        int res = new Leetcode999().numRookCaptures(board);
        Assert.assertEquals(3,res);
        System.out.println("test1 ok");
    }

    public static void main(String[] args) {
        test1();
    }
}
