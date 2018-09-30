import java.util.*;

public class BestFirstSearch {

    enum HeuristicToUse {
        MANHATTAN, SUMINVERSION;
    }

    public void bestFirstSearch(int board [], HeuristicToUse heuristic){
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
                    if(heuristic== HeuristicToUse.MANHATTAN)
                        n.heuristic=Heuristics.manhattanDistance(n.board);
                    else
                        n.heuristic=Heuristics.sumInversion(n.board);
                    if(!Utility.isVisited(closed,n.board) && !Utility.isVisited(open, n.board)){
                        open.add(n);
                    }
                }
            }
            open=Utility.sortSet(open,new Node());
            count++;
        }
        System.out.println(count);

    }
}
