import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class AStar {

    public void aStar(int[] board, BestFirstSearch.HeuristicToUse heuristicToUse) {
        ArrayList<Node> open = new ArrayList<>();
        ArrayList<Node> closed= new ArrayList<>();

        Node init= new Node(board,null, Movement.Moves.ROOT,0);
        open.add(init);

        int count=0;
        while (!open.isEmpty()){
            Node temp= open.remove(0);
//            Utility.print(temp);
            closed.add(temp);
            if(Utility.checkGoalState(temp)){
                Utility.print(temp);
                break;
            }
            ArrayList<Node> children=Utility.generateChildren(temp,closed);

//            System.out.println(children);
            for(Node n: children){
                if(n!=null && !Utility.isVisited(closed,n.board) && !Utility.isVisited(open,n.board)){
                    if(heuristicToUse== BestFirstSearch.HeuristicToUse.MANHATTAN)
                        n.heuristic=Heuristics.manhattanDistance(n.board);
                    else
                        n.heuristic=Heuristics.sumInversion(n.board);
                    if(!Utility.isVisited(closed,n.board) && !Utility.isVisited(open, n.board)){
                        open.add(n);
                    }
                }
            }
            open= Utility.sortSet(open,new Utility());
            count++;
        }
        System.out.println(count);

    }


}
