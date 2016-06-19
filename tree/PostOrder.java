package tree;

import java.util.Stack;
/**
 *             6
 *            / \
 *           2   7
 *          / \   \
 *         1   4   9
 *            / \  /
 *           3   5 8  
 * 
 * @author chengcheng
 *
 */
public class PostOrder {
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
		
		PostOrder_1(root);
		System.out.println();
		PostOrder_2(root);
	}
	
	/**
	 * use stack to implement
	 * @param root
	 */
	public static void PostOrder_1(TreeNode root){
		TreeNode p = root,q;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		do{
			while(p != null){
				stack.push(p);
				p = p.left;
			}
			q = null;
			while(!stack.isEmpty()){
				p = stack.peek();
				stack.pop();
				/*右孩子不存在或已被访问，访问之*/
				if(p.right == q){
					System.out.println(p.val);
					q = p; /* 保存刚访问过的结点 */
				}
				else{
					/* 当前结点不能访问，需第二次进栈 */
					stack.push(p);
					/* 先处理右子树 */
					p = p.right;
					break;
				}
			}
		}
		while(!stack.isEmpty());
	}
	public static void PostOrder_2(TreeNode root){
		TreeNode p = root;
		if(p != null){
			if(p.left != null)
				PostOrder_2(p.left);
			if(p.right != null)
				PostOrder_2(p.right);
			System.out.println(p.val);
		}
	}
	
	public static void PostOrder_3(TreeNode root){
		
	}
}
