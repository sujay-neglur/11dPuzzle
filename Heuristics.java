import java.util.ArrayList;

public class Heuristics {

    public static int calculateTraditionalDistance(int targetRow, int targetColumn, int currentRow, int currentColumn) {
        return Math.abs(targetColumn - currentColumn) + Math.abs(targetRow - currentRow);
    }

    public static int manhattanDistance(int[] board) {
//        int currentRow;
//        int currentColumn;
//        int targetRow;
//        int targetColumn;
//        int distance=0;
//        for(int i=0;i<board.length;i++){
//            if(board[i]==0) continue;
//            int indexInGoal= Utility.getIndex(BoardConfig.goal,board[i]);
//            targetRow= indexInGoal/BoardConfig.columns;
//            targetColumn=indexInGoal%BoardConfig.columns;
//            currentRow=i/BoardConfig.columns;
//            currentColumn=i%BoardConfig.columns;
//            if((currentRow+1== targetRow && currentColumn+1==targetColumn)
//                    || (currentRow+1 ==targetRow && currentColumn+1 ==targetColumn)
//                    || (currentRow-1 ==targetRow && currentColumn+1==targetColumn)
//                    || (currentRow-1 == targetRow && currentColumn-1 ==targetColumn)){
//                distance++;
//                continue;
//            }
//            distance+= Math.abs(targetColumn-currentColumn)+Math.abs(targetRow-currentRow);
//        }
//        return distance;

        int distance = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) continue;
            int indexInGoal = Utility.getIndex(BoardConfig.goal, board[i]);
            int targetRow = indexInGoal / BoardConfig.columns;
            int targetColumn = indexInGoal % BoardConfig.columns;
            int currentRow = i / BoardConfig.columns;
            int currentColumn = i % BoardConfig.columns;
            int traditionalDistance = 0;
            int diagonalDistance = 0;
            while (targetRow != currentRow && targetColumn != currentColumn) {
                if (targetRow < currentRow && targetColumn < currentColumn) {
                    currentRow--;
                    currentColumn--;
                    diagonalDistance++;
                    continue;
                }

                if (targetRow < currentRow && targetColumn > currentColumn) {
                    currentRow--;
                    currentColumn++;
                    diagonalDistance++;
                    continue;
                }

                if (targetRow > currentRow && targetColumn < currentColumn) {
                    currentRow++;
                    currentColumn--;
                    diagonalDistance++;
                    continue;
                }
                if (targetRow > currentRow && targetColumn > currentColumn) {
                    currentRow++;
                    currentColumn++;
                    diagonalDistance++;
                    continue;
                }
            }
            diagonalDistance += calculateTraditionalDistance(targetRow, targetColumn, currentRow, currentColumn);
            distance += diagonalDistance;
        }
        return distance;
    }


    public static int sumInversion(int[] board) {
        ArrayList<Integer> tilesToTheLeft = new ArrayList<>();
        int sum = 0;

        for (int i = 0; i < board.length; i++) {
            int count = 0;
            if (board[i] == 0) continue;
            int index = Utility.getIndex(BoardConfig.goal, board[i]);

            for (int j = index - 1; j >= 0; j--) {
                if (BoardConfig.goal[j] != 0) {
                    tilesToTheLeft.add(BoardConfig.goal[j]);
                }
            }

            for (int j = i + 1; j < board.length; j++) {
                if (tilesToTheLeft.contains(board[j])) count++;
            }

            sum += count;
            tilesToTheLeft.clear();
        }
        return sum;
    }

    public static int findDepth(Node node) {
        int depth = 0;
        for (Node temp = node; temp != null; temp = temp.parent) {
            depth++;
        }
        return depth;
    }
}


/*
int count=0;
            int index= Utility.getIndex(BoardConfig.goal,board[i]);

            for(int j=index;j>0;j--){
                if(BoardConfig.goal[j]==0 ) continue;
                else {
                    if(BoardConfig.goal[j]!=0){
                        tilesToTheLeft.add(BoardConfig.goal[j]);
                    }
                }
            }
            System.out.println(tilesToTheLeft);
            for(int j=i;j<board.length;j++){
                if(tilesToTheLeft.contains(board[j])) count++;
            }
            tilesToTheLeft.clear();
            sum+=count;
 */