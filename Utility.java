import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Utility implements Comparator<Node> {
    public static void print(Node node) {

        char characters [] []= new char[BoardConfig.rows][BoardConfig.columns];
        String printString="";
        int first=97;

        for(int i=0;i<BoardConfig.rows;i++){
            for(int j=0;j<BoardConfig.columns;j++){
                characters[i][j]= (char)first;
                first++;
            }
        }
//
//        int blankIndex= getBlankIndex(node.board);
//        int blankRow= blankIndex/BoardConfig.columns;
//        int blankColumn= blankIndex%BoardConfig.columns;
////        System.out.println(node.move);
//        if(node.move== Movement.Moves.ROOT) printString+="0 [";
//        else printString+=characters[blankRow][blankColumn]+" [";
//        for(int i=0;i<node.board.length;i++){
//            printString+=node.board[i];
//            if(i!=node.board.length-1){
//                printString+=",";
//            }
//        }
//        System.out.println(printString+"] "+node.move);
        ArrayList<String> stringToPrint= new ArrayList<>();

        for(Node temp= node; temp!=null; temp= temp.parent){
            printString="";
            int blankIndex= getBlankIndex(temp.board);
            int blankRow= blankIndex/BoardConfig.columns;
            int blankColumn= blankIndex%BoardConfig.columns;
            if(temp.move== Movement.Moves.ROOT) printString+="0 [";
            else printString+=characters[blankRow][blankColumn]+" [";
            for(int i=0;i<temp.board.length;i++){
                printString+=temp.board[i];
                if(i!=temp.board.length-1){
                    printString+=",";
                }
            }
            stringToPrint.add(printString+"] "+temp.move+" "+temp.depth+" "+temp.heuristic);
        }

        for(int i=stringToPrint.size()-1;i>=0;i--){
            System.out.println(stringToPrint.get(i));
        }
    }

    public static boolean checkGoalState(Node node) {
        if (Arrays.equals(node.board, BoardConfig.goal)) {
            return true;
        }
        return false;
    }

    public static int[] swapElements(int[] board, int blankIndex, int targetIndex) {
//        System.out.println("Blank index "+blankIndex+" Target index "+targetIndex);
        int temp = board[blankIndex];
        board[blankIndex] = board[targetIndex];
        board[targetIndex] = temp;
        return board;
    }

    public static int getBlankIndex(int[] board) {
        for (int i : board) {
            if (board[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isVisited(ArrayList<Node> visited, int[] currentBoardPosition) {
        for (int i = 0; i < visited.size(); i++) {
            if (Arrays.equals(visited.get(i).board, currentBoardPosition)) {
                return true;
            }
        }
        return false;
    }

    public static int getIndex(int[] board, int number) {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == number) {
                return i;
            }
        }
        return -1;
    }

    public static ArrayList<Node> generateChildren(Node node, ArrayList<Node> visited) {
        ArrayList<Node> children = new ArrayList<>();

        Node upChild = Movement.move(node, visited, Movement.Moves.UP);
        children.add(upChild);

        Node upRightChild = Movement.move(node, visited, Movement.Moves.UPRIGHT);
        children.add(upRightChild);

        Node rightChild = Movement.move(node, visited, Movement.Moves.RIGHT);
        children.add(rightChild);

        Node downRightChild = Movement.move(node, visited, Movement.Moves.DOWNRIGHT);
        children.add(downRightChild);

        Node downChild = Movement.move(node, visited, Movement.Moves.DOWN);
        children.add(downChild);

        Node downLeftChild = Movement.move(node, visited, Movement.Moves.DOWNLEFT);
        children.add(downLeftChild);

        Node leftChild = Movement.move(node, visited, Movement.Moves.LEFT);
        children.add(leftChild);

        Node upLeftChild = Movement.move(node, visited, Movement.Moves.UPLEFT);
        children.add(upLeftChild);

        return children;
    }

    public static ArrayList<Node> sortSet(ArrayList<Node> open, Comparator<Node> comparator) {
//        System.out.println(comparator.equals(new Utility()));
        Collections.sort(open, comparator);
        return open;
    }

    @Override
    public int compare(Node node, Node t1) {
//        System.out.println("here");
        if(node.depth+node.heuristic> t1.depth+t1.heuristic)
            return 1;

        if(node.depth+node.heuristic<t1.depth+t1.heuristic)
            return -1;

        if(node.depth+node.heuristic==t1.depth+t1.heuristic)
            return Node.resolveEquality(node, t1);

        return -1;
    }
}
