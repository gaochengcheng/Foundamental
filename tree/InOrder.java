package tree;

import java.util.Stack;

/**
 * 
 *             6
 *            / \
 *           2   7
 *          / \   \
 *         1   4   9
 *            / \  /
 *           3   5 8  
 * @author chengcheng
 *
 */
public class InOrder {
	public static void main(String[] args) {
		TreeNode root =  new TreeNode(6);
		TreeNode node2 = new TreeNode(2);
		root.left = node2;
		TreeNode node7 = new TreeNode(7);
		root.right = node7;
		node2.left = new TreeNode(1);
		TreeNode node4 = node2.right = new TreeNode(4);
		node4.left = new TreeNode(3);
		node4.right = new TreeNode(5);
		node7.right = new TreeNode(9);
		node7.right.left = new TreeNode(8);
		
		InOrder_3(root);
		
	}
	/**
	 * using stack
	 * 
	 * @param root
	 */
	public static void InOrder_1(TreeNode root){
		TreeNode p = root;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while(!stack.isEmpty() || p != null){
			if(p != null){
				stack.push(p);
				p = p.left;
			}
			else{
				TreeNode node = stack.pop();
				System.out.println(node.val);
				p = node.right;
			}
		}
	}
	
	public static void InOrder_2(TreeNode root){
		TreeNode p = root;
		if(p != null){
			if(p.left != null)
				InOrder_2(p.left);
			System.out.println(p.val);
			if(p.right != null)
				InOrder_2(p.right);
			
		}
	}

	/**
	 * morris method
	 * 	1. if  (当前节点的左孩子为空)
	 * 			则输出当前节点并将其右孩子作为当前节点。
		2. else (当前节点的左孩子不为空)
				在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
   			a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
   			b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
		3. 重复以上1、2直到当前节点为空。
	 * 
	 * @param root
	 */
	public static void InOrder_3(TreeNode root){
		TreeNode cur = root,prev = null;
		while(cur != null){
			if(cur.left == null){
				System.out.println("1:  "+cur.val);
				cur = cur.right;
			}
			else{
				prev = cur.left;
				while(prev.right != null && prev.right != cur)
					prev = prev.right;      //new previous node
				if(prev.right == null){ //2.a
					prev.right = cur;   //做线索
					cur = cur.left;
				}
				else{ // 2.b
					prev.right = null;   //线索已经发挥作用，取消线索，恢复树。
					System.out.println("2 :      "+cur.val);
					cur = cur.right;
				}
			}
		}
	}
}
