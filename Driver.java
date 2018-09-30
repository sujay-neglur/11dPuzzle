import java.util.ArrayList;
import java.util.Collections;

public class Driver {

    public static void main(String[] args) {
//        int [] board= new int []{1,2,6,4,5,9,7,3,0,10,11,8};
        int [] board= new int[] {0,11,10,9,8,7,6,5,4,3,2,1};
        int [] board1= new int[]{11,0,10,9,8,7,6,5,4,3,2,1};
//        int [] board= new int[]{1,0,3,7,5,2,6,4,9,10,11,8};
        BoardConfig.goal=new int[]{1,2,3,4,5,6,7,8,9,10,11,0};
        BoardConfig.rows=3;
        BoardConfig.columns=4;
        BestFirstSearch bfs = new BestFirstSearch();
//        bfs.bestFirstSearch(board, BestFirstSearch.HeuristicToUse.SUMINVERSION);
        AStar as= new AStar();
        as.aStar(board, BestFirstSearch.HeuristicToUse.MANHATTAN);

//        ArrayList<Node> open = new ArrayList<>();
//        Node n1= new Node(board,null, Movement.Moves.UPLEFT,0);
//        Node n2= new Node(board1,n1, Movement.Moves.RIGHT,0);
//        open.add(n1);
//        open.add(n2);
//        int h= Heuristics.manhattanDistance(n1.board);
//        n1.heuristic=h;
//        h=Heuristics.manhattanDistance(n2.board);
//        n2.heuristic=h;
//        System.out.println((n1.heuristic+n1.depth)+" "+(n2.heuristic+n2.depth));
//        System.out.println(n1.move+" "+n2.move);
//        open=Utility.sortSet(open,new Utility());
    }
}
