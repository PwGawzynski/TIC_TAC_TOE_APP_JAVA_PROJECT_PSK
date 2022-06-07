package AI;

// Java program to find the
// next optimal move for a player
public class AIUtil
{
    static class Move
    {
        int row, col;
    };

    static char player = 'o', opponent = 'x';


    static public int process(int [] gameState){
        int position;
        char board[][] = {{ '_', '_', '_' },//00 01 02
                { '_', '_', '_' },//10 11 12
                { '_', '_', '_' }};//20 21 21
        int stage = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameState[stage + j] == 0) {
                    board[i][j] = 'x';
                } else if (gameState[stage + j] == 1) {
                    board[i][j] = 'o';
                }
            }
            stage+=3;
        }
        Move bestMove = findBestMove(board);
        if (bestMove.row == 0) return bestMove.row + bestMove.col;
        else if(bestMove.row == 1) return bestMove.row + bestMove.col + 2;
        else  return bestMove.row + bestMove.col + 4;
    }
    //sprawdza czy sa dostępne ruchy
    // jeżeli sa ruchy zwraca prawde
    // jeżeli nie ma żadnej możliwości ruchu zwraca fałsz
    public static Boolean isMovesLeft(char board[][])
    {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == '_')
                    return true;
        return false;
    }

    // Funkcja odpowiadająca za obliczenie wag poszczególnych propozycji ruchów
    static int evaluate(char b[][])
    {
        for (int row = 0; row < 3; row++)
        {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2])
            {
                if (b[row][0] == player)
                    return +10;
                else if (b[row][0] == opponent)
                    return -10;
            }
        }
        for (int col = 0; col < 3; col++)
        {
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col])
            {
                if (b[0][col] == player)
                    return +10;

                else if (b[0][col] == opponent)
                    return -10;
            }
        }

        if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
        {
            if (b[0][0] == player)
                return +10;
            else if (b[0][0] == opponent)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
        {
            if (b[0][2] == player)
                return +10;
            else if (b[0][2] == opponent)
                return -10;
        }

        return 0;
    }

    // Funkcja minmax znana z algolytmu minmax czyli algorytmu AI Computer_Player
    // zwraca najlepszy ruch
    static int minimax(char board[][],
                       int depth, Boolean isMax)
    {
        int score = evaluate(board);

        if (score == 10)
            return score;

        if (score == -10)
            return score;

        if (isMovesLeft(board) == false)
            return 0;

        if (isMax)
        {
            int best = -1000;

            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (board[i][j]=='_')
                    {
                        board[i][j] = player;

                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax));

                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }

        else
        {
            int best = 1000;

            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (board[i][j] == '_')
                    {
                        board[i][j] = opponent;

                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax));

                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
    }
    // znajduje najlepszy ruch i zwraca go do głownego Drivera jako obiekt klasy move
    static Move findBestMove(char board[][])
    {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] == '_')
                {
                    board[i][j] = player;

                    int moveVal = minimax(board, 0, false);

                    board[i][j] = '_';

                    if (moveVal > bestVal)
                    {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }
}
