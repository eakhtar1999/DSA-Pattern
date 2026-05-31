
class Solution {
    public TreeNode invertTree(TreeNode root) {
        //basic check to all 3 below solution
        if(root == null){
            return null;
        }

        // DFS(iterative)
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }

        //DFS(recursive)
        // TreeNode temp = root.left;
        // root.left = root.right;
        // root.right = temp;
        // invertTree(root.left);
        // invertTree(root.right);

        //BFS
        // Queue<TreeNode> q = new LinkedList<>();
        // q.offer(root);
        // while(!q.isEmpty()){
        //     TreeNode node = q.poll();
        //     TreeNode temp= node.right;
        //     node.right = node.left;
        //     node.left = temp;
        //     if (node.left != null) q.add(node.left);
        //     if (node.right != null)q.add(node.right);
        // }

        return root;
    }
}
