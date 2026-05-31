

class RecursiveSolution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;        
    }

    private void postorder(TreeNode node, List<Integer> res) {
        if (node == null) return;
        postorder(node.left, res);
        postorder(node.right, res);
        res.add(node.val);
    }
}


public class StackSolution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                res.add(cur.val);
                stack.push(cur);
                cur = cur.right;
            } else {
                cur = stack.pop();
                cur = cur.left;
            }
        }

        Collections.reverse(res);
        return res;
    }
}
