import java.util.ArrayList;
import java.util.Arrays;

public class Movement {
    enum Moves {
        UP,UPRIGHT,RIGHT,DOWNRIGHT,DOWN,DOWNLEFT,LEFT,UPLEFT,ROOT
    }

    public static Node move(Node node, ArrayList<Node> visited, Moves move) {
        int[] currentBoardPosition = Arrays.copyOf(node.board, node.board.length);
        int blankIndex = Utility.getBlankIndex(currentBoardPosition);
        int column = blankIndex % 4;
        int row = blankIndex / 4;
        if (move == Moves.UP) {
            if (row != 0) {
                int[] upMovedBoard = Utility.swapElements(currentBoardPosition, blankIndex, blankIndex - 4);
                return new Node(upMovedBoard, node, Moves.UP,node.depth+1);
            }
        }

        if (move == Moves.UPRIGHT) {
            if (row != 0 && column != BoardConfig.columns-1) {
                int upRightMovedBoard[] = Utility.swapElements(currentBoardPosition, blankIndex, blankIndex - 3);
                return new Node(upRightMovedBoard, node, Moves.UPRIGHT,node.depth+1);
            }
        }

        if (move == Moves.RIGHT) {
            if (column != BoardConfig.columns-1) {
                int[] rightMovedBoard = Utility.swapElements(currentBoardPosition, blankIndex, blankIndex + 1);
                return new Node(rightMovedBoard, node, Moves.RIGHT,node.depth+1);
            }
        }

        if (move == Moves.DOWNRIGHT) {
            if (row != BoardConfig.rows-1 && column != BoardConfig.columns-1) {
                int[] downRightMovedBoard = Utility.swapElements(currentBoardPosition, blankIndex, blankIndex + 5);
                return new Node(downRightMovedBoard, node, Moves.DOWNRIGHT,node.depth+1);
            }
        }

        if (move == Moves.DOWN) {
            if (row != BoardConfig.rows-1) {
                int[] downMovedBoard = Utility.swapElements(currentBoardPosition, blankIndex, blankIndex + 4);
                return new Node(downMovedBoard, node, Moves.DOWN,node.depth+1);
            }
        }

        if (move == Moves.DOWNLEFT) {
            if (row != BoardConfig.rows-1 && column != 0) {
                int[] downLeftMovedBoard = Utility.swapElements(currentBoardPosition, blankIndex, blankIndex + 3);
                return new Node(downLeftMovedBoard, node, Moves.DOWNLEFT,node.depth+1);
            }
        }

        if (move == Moves.LEFT) {
            if (column != 0) {
                int[] leftMovedBoard = Utility.swapElements(currentBoardPosition, blankIndex, blankIndex - 1);
                return new Node(leftMovedBoard, node, Moves.LEFT,node.depth+1);
            }
        }

        if (move == Moves.UPLEFT) {
            if (row != 0 && column != 0) {
                int[] upLeftMovedBoard = Utility.swapElements(currentBoardPosition, blankIndex, blankIndex - 5);
                return new Node(upLeftMovedBoard, node, Moves.UPLEFT,node.depth+1);
            }
        }

        return null;
    }

}
