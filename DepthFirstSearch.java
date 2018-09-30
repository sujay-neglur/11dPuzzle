import java.util.*;

import static jdk.nashorn.internal.objects.Global.print;


public class DepthFirstSearch {






//    public void addChildToList(Node node, HashSet<Node> visited, ArrayList<Node> children, Moves move) {
//        if (node != null) {
////            visited.add(node);
//            children.add(node);
////            System.out.println(move);
////            print(node.board);
//        }
//    }


    public void dfs(int[] board) {
        Node init = new Node(board, null, Movement.Moves.ROOT,0);
        System.out.println("Here");
        ArrayList<Node> visited = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(init);
        boolean goal = false;
        int count = 0;
        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            System.out.println(temp.move);
            print(temp.board);
            visited.add(temp);
            if (Utility.checkGoalState(temp)) {
                break;
            }

            ArrayList<Node> children = Utility.generateChildren(temp, visited);
            for (Node n : children) {
                if (Utility.checkGoalState(n)) {
                    return;
                }
            }
            for (int i = children.size() - 1; i >= 0; i--) {
                if (!Utility.isVisited(visited, children.get(i).board)) {
                    stack.push(children.get(i));
                }
            }
            System.out.println(++count);
        }
    }


}