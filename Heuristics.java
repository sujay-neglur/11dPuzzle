import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.DeflaterInputStream;

public class Heuristics {

    enum HeuristicToUse {
        MANHATTAN, SUMINVERSION, MISPLACEDTILES;
    }

    public static int calculateTraditionalDistance(int targetRow, int targetColumn, int currentRow, int currentColumn) {
        return Math.abs(targetColumn - currentColumn) + Math.abs(targetRow - currentRow);
    }


    public static double manhattanDistance(int[] board) {

        int distance = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) continue;
            int indexInGoal = Utility.getIndex(BoardConfig.goal, board[i]);
            int targetRow = indexInGoal / BoardConfig.columns;
            int targetColumn = indexInGoal % BoardConfig.columns;
            int currentRow = i / BoardConfig.columns;
            int currentColumn = i % BoardConfig.columns;
            distance += Math.sqrt((Math.pow(targetRow - currentRow, 2) + Math.pow(targetColumn - currentColumn, 2)));
//            distance += Math.max(Math.abs(currentRow - targetRow), Math.abs(currentColumn - targetColumn));
//            distance+=calculateTraditionalDistance(targetRow,targetColumn,currentRow,currentColumn);

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

    public static int misplacedTiles(int[] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) continue;
            if (board[i] != BoardConfig.goal[i])
                count++;
        }
        return count;
    }

    public static int linearConflict(int[] board) {
        int conflict = 0;
        for (int i = 0; i < board.length - 1; i++) {
            if (board[i] == 0) continue;
            for (int j = i + 1; j < board.length; j++) {
                if (board[j] == 0) continue;

                int currentRowOfI = Utility.getIndex(board, board[i]) / BoardConfig.columns;
                int currentColumnOfI = Utility.getIndex(board, board[i]) % BoardConfig.columns;
                int currentRowOfJ = Utility.getIndex(board, board[j]) / BoardConfig.columns;
                int currentColumnOfJ = Utility.getIndex(board, board[j]) % BoardConfig.columns;
                int targetRowOfI = Utility.getIndex(BoardConfig.goal, board[i]) / BoardConfig.columns;
                int targetColumnOfI = Utility.getIndex(BoardConfig.goal, board[i]) % BoardConfig.columns;
                int targetRowOfJ = Utility.getIndex(BoardConfig.goal, board[j]) / BoardConfig.columns;
                int targetColumnOfJ = Utility.getIndex(BoardConfig.goal, board[j]) % BoardConfig.columns;

                if (currentRowOfI == currentRowOfJ && targetRowOfI == targetRowOfJ) {
                    if ((currentColumnOfI<currentColumnOfJ && targetColumnOfI>targetColumnOfJ) || (currentColumnOfI>currentColumnOfJ && targetColumnOfI<targetColumnOfJ)) {
                        conflict++;
//                    System.out.println(board[i]+" "+board[j]);
                    }
                }
                if (currentColumnOfI == currentColumnOfJ && targetColumnOfI == targetColumnOfJ) {
                    if((currentRowOfI <currentRowOfJ && targetRowOfI>targetRowOfJ) || (currentRowOfI>currentRowOfJ && targetRowOfI<targetRowOfJ)){
                        conflict++;
//                    System.out.println(board[i]+" "+board[j]);
                    }
                }
            }
        }
        return conflict;
    }

    public static int fringeDatabase(int [] board){
        int costOfTopLeft=0;
        int costOfTheRest=0;
        ArrayList<Integer> firstRow=  new ArrayList<>();
        for(int i=0;i<BoardConfig.goal.length;i++){
//            if(board[i]==0) continue;
            if(i/BoardConfig.columns==0) {
                firstRow.add(board[i]);
                continue;
            }
            if(i%BoardConfig.columns==0){
                firstRow.add(board[i]);
            }
        }
        System.out.println(firstRow);

        for(int i=0;i<firstRow.size();i++){
            int currentRow=Utility.getIndex(board,firstRow.get(i))/BoardConfig.columns;
            int currentColumn=Utility.getIndex(board,firstRow.get(i))%BoardConfig.columns;
            int targetRow=Utility.getIndex(BoardConfig.goal,firstRow.get(i))/BoardConfig.columns;
            int targetColumn=Utility.getIndex(BoardConfig.goal,firstRow.get(i))%BoardConfig.columns;
            costOfTopLeft+=Math.sqrt(Math.pow(currentRow-targetRow,2)+ Math.pow(currentColumn-targetColumn,2));
        }

        for(int i=0;i<board.length;i++){
            if(firstRow.contains(board[i])) continue;
            int currentRow=Utility.getIndex(board,board[i])/BoardConfig.columns;
            int currentColumn=Utility.getIndex(board,board[i])%BoardConfig.columns;
            int targetRow=Utility.getIndex(BoardConfig.goal,board[i])/BoardConfig.columns;
            int targetColumn=Utility.getIndex(BoardConfig.goal,board[i])%BoardConfig.columns;
        }
        return costOfTopLeft;
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