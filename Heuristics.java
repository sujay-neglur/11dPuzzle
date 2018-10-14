import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Heuristics {

    static ArrayList<Integer> pattern= new ArrayList<Integer>(Arrays.asList(new Integer[] {1,2,3,4,5,9}));

    enum HeuristicToUse {
        MANHATTAN, SUMINVERSION, MISPLACEDTILES, FRINGEDATABASE, GASCHNIG,EUCLIDEAN, CHEBYSHEV
    }

    private static int [] getRowColumn(int [] board, int element){
        int row= Utility.getIndex(board,element)/BoardConfig.columns;
        int column= Utility.getIndex(board,element)%BoardConfig.columns;
        return  new int[]{row,column};
    }

    public static int calculateTraditionalDistance(int targetRow, int targetColumn, int currentRow, int currentColumn) {
        return Math.abs(targetColumn - currentColumn) + Math.abs(targetRow - currentRow);
    }


    public static double euclideanDistance(int [] board){
        double distance = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) continue;
            int rowCol []= getRowColumn(board,board[i]);
            int targetRow = rowCol[0];
            int targetColumn = rowCol[1];
            rowCol=getRowColumn(BoardConfig.goal,board[i]);
            int currentRow = rowCol[0];
            int currentColumn = rowCol[1];
            distance += Math.sqrt((Math.pow(targetRow - currentRow, 2) + Math.pow(targetColumn - currentColumn, 2)));

        }
//        System.out.println(distance);
        return distance;
    }

    public static double manhattanDistance(int[] board) {

        int distance = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) continue;
            int rowCol []= getRowColumn(board,board[i]);
            int targetRow = rowCol[0];
            int targetColumn = rowCol[1];
            rowCol=getRowColumn(BoardConfig.goal,board[i]);
            int currentRow = rowCol[0];
            int currentColumn = rowCol[1];
            distance+=calculateTraditionalDistance(targetRow,targetColumn,currentRow,currentColumn);

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

                int rowCol []= getRowColumn(board,board[i]);
                int currentRowOfI = rowCol[0];
                int currentColumnOfI = rowCol[1];

                rowCol= getRowColumn(board,board[j]);
                int currentRowOfJ = rowCol[0];
                int currentColumnOfJ = rowCol[1];

                rowCol= getRowColumn(BoardConfig.goal,board[i]);
                int targetRowOfI = rowCol[0];
                int targetColumnOfI = rowCol[1];

                rowCol=getRowColumn(BoardConfig.goal,board[j]);
                int targetRowOfJ = rowCol[0];
                int targetColumnOfJ = rowCol[1];

                if (currentRowOfI == currentRowOfJ && targetRowOfI == targetRowOfJ) {
                    if ((currentColumnOfI < currentColumnOfJ && targetColumnOfI > targetColumnOfJ) || (currentColumnOfI > currentColumnOfJ && targetColumnOfI < targetColumnOfJ)) {
                        conflict++;
//                    System.out.println(board[i]+" "+board[j]);
                    }
                }
                if (currentColumnOfI == currentColumnOfJ && targetColumnOfI == targetColumnOfJ) {
                    if ((currentRowOfI < currentRowOfJ && targetRowOfI > targetRowOfJ) || (currentRowOfI > currentRowOfJ && targetRowOfI < targetRowOfJ)) {
                        conflict++;
//                    System.out.println(board[i]+" "+board[j]);
                    }
                }
            }
        }
        return conflict;
    }

    public static double fringeDatabase(int[] board) {
//        int costOfTopLeft = 0;
//        int costOfTheRest = 0;
//        ArrayList<Integer> firstRow = new ArrayList<>();
//        for (int i = 0; i < BoardConfig.goal.length; i++) {
////            if(board[i]==0) continue;
//            if (i / BoardConfig.columns == 0) {
//                firstRow.add(BoardConfig.goal[i]);
//                continue;
//            }
//            if (i % BoardConfig.columns == 0) {
//                firstRow.add(BoardConfig.goal[i]);
//            }
//        }
////        System.out.println(firstRow);
//
//        for (int i = 0; i < board.length; i++) {
//            int currentRow = Utility.getIndex(board, board[i]) / BoardConfig.columns;
//            int currentColumn = Utility.getIndex(board, board[i]) % BoardConfig.columns;
//            int targetRow = Utility.getIndex(BoardConfig.goal, board[i]) / BoardConfig.columns;
//            int targetColumn = Utility.getIndex(BoardConfig.goal, board[i]) % BoardConfig.columns;
//            if (firstRow.contains(board[i])) {
//                costOfTopLeft += Math.sqrt(Math.pow(currentRow - targetRow, 2) + Math.pow(currentColumn - targetColumn, 2));
//            } else {
//                costOfTheRest += Math.sqrt(Math.pow(currentRow - targetRow, 2) + Math.pow(currentColumn - targetColumn, 2));
//            }
//        }
//        return Math.max(costOfTheRest, costOfTopLeft);
        double manhattanDistanceOfPattern=0;
        double manhattanDistanceOfNonPattern=0;
        for(int i=0;i<board.length;i++){
            if(board[i]==0) continue;

            int [] rowCol = getRowColumn(board,board[i]);
            int currentRow= rowCol[0];
            int currentCol= rowCol[1];
            rowCol=getRowColumn(BoardConfig.goal,board[i]);
            int targetRow= rowCol[0];
            int targetColumn= rowCol[1];
            if(pattern.contains(board[i])){
//                manhattanDistanceOfPattern+=calculateTraditionalDistance(targetRow,targetColumn,currentRow,currentCol);
                manhattanDistanceOfPattern+= Math.sqrt(Math.pow(currentRow-targetRow,2)+Math.pow(currentCol-targetColumn,2));
            }

            else{
//                manhattanDistanceOfNonPattern+=calculateTraditionalDistance(targetRow,targetColumn,currentRow,currentCol);
                manhattanDistanceOfNonPattern+= Math.sqrt(Math.pow(currentRow-targetRow,2)+Math.pow(currentCol-targetColumn,2));
            }

        }
        return Math.max(manhattanDistanceOfNonPattern,manhattanDistanceOfPattern);
    }

    public static int chebyShev(int board []){
        int distance=0;
        for(int i=0;i<board.length;i++){
            if(board[i]==0) continue;
            int rowCol []= getRowColumn(board,board[i]);
            int currentRow=rowCol[0];
            int currentCol=rowCol[1];
            rowCol=getRowColumn(BoardConfig.goal,board[i]);
            int targetRow= rowCol[0];
            int targetCol= rowCol[1];
            distance+=Math.max(Math.abs(currentRow-targetRow), Math.abs(currentCol-targetCol));
        }
        return distance;
    }

    public static int Gaschnig(int[] board) {
        int[] boardCopy = Arrays.copyOf(board, board.length);
        int swaps = 0;
        int min = 0, max = board.length;
        while (!Arrays.equals(boardCopy, BoardConfig.goal)) {
            int blankIndex = Utility.getBlankIndex(boardCopy);
            int elementInGoal = BoardConfig.goal[blankIndex];
            if (elementInGoal != 0) {
//                System.out.println(elementInGoal);
                int currentIndexOfElement = Utility.getIndex(boardCopy, elementInGoal);
                boardCopy = Utility.swapElements(boardCopy, blankIndex, currentIndexOfElement);
            } else {
//                System.out.println(elementInGoal);
                int index = 0;
                for (int j = 0; j < boardCopy.length; j++) {
                    if (boardCopy[j] != BoardConfig.goal[j]) {
                        index = j;
                        break;
                    }
                }
                boardCopy = Utility.swapElements(boardCopy, blankIndex, index);
            }
//            for(int i=0;i<boardCopy.length;i++)
//                System.out.print(boardCopy[i]+" ");
//            System.out.println();
            swaps++;
        }
        return swaps;

    }
}
