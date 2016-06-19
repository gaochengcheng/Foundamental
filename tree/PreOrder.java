package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
/**
 * Traverse a binary tree by preorder.Using stack data structure.
 *             6
 *            / \
 *           2   7
 *          / \   \
 *         1   4   9
 *            / \  /
 *           3   5 8  
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
		TreeNode node4 = new TreeNode(4);
		node2.right = node4;
		node4.left = new TreeNode(3);
		node4.right = new TreeNode(5);
		node7.right = new TreeNode(9);
		node7.right.left = new TreeNode(8);
		
		
//		ArrayList<TreeNode> result = PreOrder_1(root);
		PreOrder_3(root);
//		Iterator<TreeNode> it = result.iterator();
//		while( it.hasNext()){
//			System.out.println(it.next().val);
//		}
	}
	/**
	 * using stack
	 * 
	 * 
	 * @param root
	 */
	public static ArrayList<TreeNode> PreOrder_1(TreeNode root){
		TreeNode p = root;
		ArrayList<TreeNode> result = new ArrayList<TreeNode>(); 
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(p);                    //put the root into the stack
		
		while(!stack.isEmpty()){
			TreeNode node = stack.pop();
			int value = node.val;
			System.out.println(value);
			result.add(node);
			
			if(node.right != null)             //if right tree exist, then put the right into the stack
				stack.push(node.right);
			if(node.left != null)              //if left tree exist, then put the left into the stack.
				stack.push(node.left);
		}
		return result;
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
				PreOrder_2(p.left);
			}
			if(p.right != null){
				PreOrder_2(p.right);
			}
			
		}
	}
	
	
	public static void PreOrder_3(TreeNode root){

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
					System.out.println(cur.val);      //访问当前结点
					prev.right = cur;   //做线索
					cur = cur.left;     
				}
				else{ // 2.b
					prev.right = null;   //线索已经发挥作用，取消线索，恢复树。
					//System.out.println("2 :      "+cur.val);      //已经访问过了
					cur = cur.right;
				}
			}
		}
	
	}
	
}
  