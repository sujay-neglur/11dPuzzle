import java.util.*;


public class DepthFirstSearch {

    public boolean isPresentInStack(Stack<Node> stack, Node node) {
        Stack<Node> nodeStack = (Stack<Node>) stack.clone();
        while (!nodeStack.isEmpty()) {
            Node top = nodeStack.peek();
            nodeStack.pop();
            if (Arrays.equals(top.board, node.board)) return true;
        }
        return false;
    }

    public void depthFirstSearch(int[] board) {
        Node init= new Node(board,null, Movement.Moves.ROOT,0);
        ArrayList<Node> visited= new ArrayList<>();
        Stack<Node> stack= new Stack<>();
        stack.push(init);
        while(!stack.isEmpty()){
            Node temp= stack.pop();
            visited.add(temp);
            if(Utility.checkGoalState(temp)){
                Utility.print(temp);
                break;
            }

            ArrayList<Node> children= Utility.generateChildren(temp,visited);
            Collections.reverse(children);
            for(Node child: children){
                if(child==null) continue;

                if(!isPresentInStack(stack,child) && !Utility.isVisited(visited,child.board)){
                        stack.push(child);
                }
            }
        }


    }


}