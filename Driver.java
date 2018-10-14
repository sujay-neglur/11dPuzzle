import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Driver {

    public static void main(String[] args) {
//        int [] board= new int []{0,4,3,7,1,6,5,10,9,11,8,2};
//        int [] board= new int[] {0,11,10,9,8,7,6,5,4,3,2,1};
//        int [] board= new int[] {0,11,9,10,8,7,6,5,4,3,2,1};
        int [] board= new int[]{1,6,8,2,5,11,7,9,3,4,0,10};
//        int [] board1= new int[]{11,0,10,9,8,7,6,5,4,3,2,1};
//        int [] board= new int[]{1,0,3,7,2,5,6,4,9,10,11,8};
//        int [] board = new int[]{1,2,3,4,5,6,7,8,9,11,0,10};
        BoardConfig.goal=new int[]{1,2,3,4,5,6,7,8,9,10,11,0};
//        BoardConfig.goal=new int[]{1,2,3,4,5,6,7,8,0};
        BoardConfig.rows=3;
        BoardConfig.columns=4;
        BestFirstSearch bfs = new BestFirstSearch();
        DepthFirstSearch dfs= new DepthFirstSearch();
        long start=System.nanoTime();
//        dfs.depthFirstSearch(board);
        AStar as= new AStar();
        as.aStar(board,Heuristics.HeuristicToUse.GASCHNIG);
//        bfs.bestFirstSearch(board, Heuristics.HeuristicToUse.SUMINVERSION);
//        dfs.depthFirstSearch(board);
        long end= System.nanoTime();
        System.out.println("Time needed : "+((end-start)/Math.pow(10,9)));


    }

}

