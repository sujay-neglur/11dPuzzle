import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class Node implements Comparator<Node>, Comparable<Node> {
    int[] board;
    Node parent;
    double heuristic;
    Movement.Moves move;
    double depth;

    public Node() {

    }

    Node(int[] child, Node parent, Movement.Moves move, double depth) {
        this.board = child;
        this.parent = parent;
        this.move = move;
        this.depth=depth;
    }

    @Override
    public int compare(Node node, Node t1) {

        if (node.heuristic > t1.heuristic) return 1;

        if (node.heuristic == t1.heuristic) {
            return resolveEquality(node,t1);
        }
        if (node.heuristic < t1.heuristic)
            return -1;
        return -1;
    }

    static int resolveEquality(Node first, Node second){
//        System.out.println("Here");
        ArrayList<Movement.Moves> movesArrayList = new ArrayList<>(Arrays.asList(Movement.Moves.values()));
        if (movesArrayList.indexOf(first.move) < movesArrayList.indexOf(second.move))
            return -1;
        else
        {
            if(movesArrayList.indexOf(first.move) > movesArrayList.indexOf(second.move))
                return  1;
            else
                return 0;
        }
    }

    @Override
    public int compareTo(Node node) {
        return 0;
    }
}