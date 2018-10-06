import java.util.*;

public class AStar {

    static Node getIndexOfNode(Node node, ArrayList<Node> open) {
        ArrayList<Node> al = new ArrayList<>(open);
        for (Node n : al) {
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
            if(System.nanoTime()== start+40*Math.pow(10,9)) break;
            Node temp= open.remove(0);
            closed.add(temp);
            System.out.println(temp.heuristic+" "+temp.depth);
            Utility.printMatrix(temp.board,temp.move,temp.heuristic+temp.depth,temp.parent);
            if(Utility.checkGoalState(temp)){
                Utility.print(temp);
                break;
            }
            ArrayList<Node> children= Utility.generateChildren(temp,closed);
            for(Node child:children){
                if(child==null) continue;

                if(Utility.isVisited(closed,child.board)) continue;

                if(Arrays.equals(BoardConfig.goal,child.board))
                {
                    Utility.print(child);
                    System.exit(0);
                }
                if(heuristicToUse== Heuristics.HeuristicToUse.MANHATTAN)
                    child.heuristic=Heuristics.manhattanDistance(child.board)+Heuristics.linearConflict(child.board);
                if(heuristicToUse== Heuristics.HeuristicToUse.SUMINVERSION)
                    child.heuristic=Heuristics.sumInversion(child.board)+Heuristics.linearConflict(child.board);
                if(heuristicToUse== Heuristics.HeuristicToUse.MISPLACEDTILES)
                    child.heuristic=Heuristics.misplacedTiles(child.board)+Heuristics.linearConflict(child.board);
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


