package datastructure;

import java.util.Stack;
/**
 * Traverse a binary tree by preorder.Using stack data structure.
 * 
 * @author chengcheng
 * reference :http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
 *
 */
public class PreOrder {
	public static void main(String[] args) {
		
//		TreeNode root = new TreeNode(1);
//		root.left = null;
//		root.right = new TreeNode(2);
//		root.right.left = new TreeNode(3);
//		root.right.right = null;
//		PreOrder_1(root);
//		PreOrder_2(root);
		
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
		
		PreOrder_3(root);
	}
	/**
	 * using stack
	 * 
	 * @param root
	 */
	public static void PreOrder_1(TreeNode root){
		TreeNode p = root;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(p);
		
		while(!stack.isEmpty()){
			TreeNode node = stack.pop();
			int value = node.val;
			System.out.println(value);
			
			if(node.left != null)
				stack.push(node.left);
			if(node.right != null)
				stack.push(node.right);
		}
	}
	/**
	 * recursive method
	 * 
	 * @param root
	 */
	public static void PreOrder_2(TreeNode root){
		TreeNode p = root;
		if(p != null ){
			System.out.println(p.val);
			if(p.left != null){
				p = p.left;
				PreOrder_2(p);
			}
			if(p.right != null){
				p = p.right;
				PreOrder_2(p);
			}
			
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
	public static void PreOrder_3(TreeNode root){
		TreeNode cur = root,prev = null;
		while(cur != null){
			if(cur.left == null){
				System.out.println(cur.val);
				cur = cur.right;
			}
			else{
				prev = cur.left;
				while(prev.right != null && prev.right != cur)
					prev = prev.right;
				if(prev.right == null){ //2.a
					prev.right = cur;   //做线索
					cur = cur.left;
				}
				else{ // 2.b
					prev.right = null;
					System.out.println(cur.val);
					cur = cur.right;
				}
			}
		}
	}
}
