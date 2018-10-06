import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Driver {

    public static void main(String[] args) {
//        int [] board= new int []{0,4,3,7,1,6,5,10,9,11,8,2};
        int [] board= new int[] {0,11,10,9,8,7,6,5,4,3,2,1};
//        int [] board= new int[] {0,11,9,10,8,7,6,5,4,3,2,1};
        int [] board1= new int[]{11,0,10,9,8,7,6,5,4,3,2,1};
//        int [] board= new int[]{1,0,3,7,2,5,6,4,9,10,11,8};
        BoardConfig.goal=new int[]{1,2,3,4,5,6,7,8,9,10,11,0};
        BoardConfig.rows=3;
        BoardConfig.columns=4;
        BestFirstSearch bfs = new BestFirstSearch();
        DepthFirstSearch dfs= new DepthFirstSearch();
        long start=System.nanoTime();
//        dfs.depthFirstSearch(board);
        AStar as= new AStar();
//        as.aStar(board,Heuristics.HeuristicToUse.MANHATTAN);
//        bfs.bestFirstSearch(board, Heuristics.HeuristicToUse.MANHATTAN);
        long end= System.nanoTime();
        System.out.println("Time needed : "+((end-start)/Math.pow(10,9)));

        System.out.println(Heuristics.fringeDatabase(board));

    }

}

