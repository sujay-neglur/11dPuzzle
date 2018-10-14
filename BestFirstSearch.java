import java.util.*;

public class BestFirstSearch {

    public void bestFirstSearch(int board [], Heuristics.HeuristicToUse heuristic){
        ArrayList<Node> open = new ArrayList<>();
        ArrayList<Node> closed= new ArrayList<>();

        Node init= new Node(board,null, Movement.Moves.ROOT,0);
        open.add(init);
        int count=0;
        while (!open.isEmpty()){
            Node temp= open.remove(0);
            closed.add(temp);
            if(Utility.checkGoalState(temp)){
                Utility.print(temp);
                break;
            }
            ArrayList<Node> children=Utility.generateChildren(temp,closed);

            for(Node child: children){
                if(child!=null && !Utility.isVisited(closed,child.board) && !Utility.isVisited(open,child.board)){
                    if(heuristic== Heuristics.HeuristicToUse.MANHATTAN)
                        child.heuristic=Heuristics.manhattanDistance(child.board);
                    if(heuristic== Heuristics.HeuristicToUse.SUMINVERSION)
                        child.heuristic= Heuristics.sumInversion(child.board)+Heuristics.linearConflict(child.board);
                    if(heuristic== Heuristics.HeuristicToUse.EUCLIDEAN)
                        child.heuristic=Heuristics.euclideanDistance(child.board);
                    if(heuristic== Heuristics.HeuristicToUse.MISPLACEDTILES)
                        child.heuristic=Heuristics.misplacedTiles(child.board);
                    if(!Utility.isVisited(closed,child.board) && !Utility.isVisited(open, child.board)){
                        open.add(child);
                    }
                }
            }
            open=Utility.sortSet(open,new Node());
            count++;
        }
        System.out.println(count);

    }


}
