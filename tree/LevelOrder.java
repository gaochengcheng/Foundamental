package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
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
public class LevelOrder {
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
		
		LevelOrder_1(root);
	}
	
	public static void LevelOrder_1(TreeNode root){
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		TreeNode p = root;
		list.push(p);
		
		while(list != null && list.size() != 0){
			TreeNode node  = list.pop();
			System.out.println(node.val);
			
			if(node.left != null)
				list.add(node.left);
			if(node.right != null)
				list.add(node.right);
		}
	}
	
	
}
