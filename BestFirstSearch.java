import java.util.*;

public class BestFirstSearch {

    public void bestFirstSearch(int board [], Heuristics.HeuristicToUse heuristic){
        ArrayList<Node> open = new ArrayList<>();
        ArrayList<Node> closed= new ArrayList<>();

        Node init= new Node(board,null, Movement.Moves.ROOT,0);
        open.add(init);
        int count=0;
        while (!open.isEmpty()){
            for (Node n: open){
                System.out.print((n.heuristic)+" "+n.move+" ");
            }
            System.out.println();
            Node temp= open.remove(0);
            Utility.printMatrix(temp.board,temp.move,temp.heuristic,temp.parent);
            closed.add(temp);
            if(Utility.checkGoalState(temp)){
//                for(Node temp1= temp;temp1!=null;temp1=temp1.parent){
//                    Utility.printMatrix(temp1.board,temp1.move,temp1.heuristic+temp1.depth,temp1.parent);
//                    ;
//                }
                Utility.print(temp);
                break;
            }
//            if(count==10) break;
            ArrayList<Node> children=Utility.generateChildren(temp,closed);

//            System.out.println(children);
            for(Node n: children){
                if(n!=null && !Utility.isVisited(closed,n.board) && !Utility.isVisited(open,n.board)){
                    if(heuristic== Heuristics.HeuristicToUse.MANHATTAN)
                        n.heuristic=Heuristics.manhattanDistance(n.board);
                    if(heuristic== Heuristics.HeuristicToUse.SUMINVERSION)
                        n.heuristic= Heuristics.sumInversion(n.board);
                    if(heuristic== Heuristics.HeuristicToUse.MISPLACEDTILES)
                        n.heuristic=Heuristics.misplacedTiles(n.board);
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
