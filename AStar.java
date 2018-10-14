import java.util.*;

public class AStar {

    static Node getIndexOfNode(Node node, ArrayList<Node> open) {
        for (Node n : open) {
            if (Arrays.equals(n.board, node.board)) {
                return n;
            }
        }
        return null;
    }

    public void aStar(int[] board, Heuristics.HeuristicToUse heuristicToUse) {
        long start= System.nanoTime();
        ArrayList<Node> open = new ArrayList<>();
        ArrayList<Node> closed= new ArrayList<>();
        Node init= new Node(board,null, Movement.Moves.ROOT,0);
        open.add(init);
        while (!open.isEmpty()){
//            for(Node n: open){
//                if(n!=null)
//                    System.out.print(n.heuristic+n.depth+" - "+n.move+" ");
//            }

            Node temp= open.remove(0);
            closed.add(temp);
            System.out.println(open.size());
//            if(temp.depth==10) 
//                break;
//            System.out.println(temp.heuristic+" "+temp.depth);
            Utility.printMatrix(temp.board,temp.move,temp.heuristic+temp.depth,temp.parent);
            if(Utility.checkGoalState(temp)){
                Utility.print(temp);
                break;
            }
            ArrayList<Node> children= Utility.generateChildren(temp,closed);
            for(Node child:children){
                if(child==null) continue;

                if(Utility.isVisited(closed,child.board)) continue;

                if(heuristicToUse== Heuristics.HeuristicToUse.MANHATTAN)
                    child.heuristic=Heuristics.manhattanDistance(child.board);
                if(heuristicToUse== Heuristics.HeuristicToUse.SUMINVERSION)
                    child.heuristic=Heuristics.sumInversion(child.board);
                if(heuristicToUse== Heuristics.HeuristicToUse.MISPLACEDTILES)
                    child.heuristic=Heuristics.misplacedTiles(child.board);
                if(heuristicToUse== Heuristics.HeuristicToUse.FRINGEDATABASE)
                    child.heuristic=Heuristics.fringeDatabase(child.board);
                if(heuristicToUse== Heuristics.HeuristicToUse.GASCHNIG)
                    child.heuristic=Heuristics.Gaschnig(child.board);
                if(heuristicToUse== Heuristics.HeuristicToUse.EUCLIDEAN)
                    child.heuristic=Heuristics.euclideanDistance(child.board)+Heuristics.linearConflict(child.board);
                if(heuristicToUse== Heuristics.HeuristicToUse.CHEBYSHEV)
                    child.heuristic=Heuristics.chebyShev(child.board);

//                System.out.println(child.heuristic);
                Node existingNode= getIndexOfNode(child,open); //node existing in the queue
                if(existingNode!=null){
                    if(existingNode.heuristic+existingNode.depth > child.heuristic+child.depth){
                        open.remove(existingNode);
                        open.add(child);
                    }
                }
                else{
                    open.add(child);
                }
            }

            open=Utility.sortSet(open,new Utility());
        }
    }
}

