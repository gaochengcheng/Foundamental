package fptree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import struct.Element;

public class FPTree {
	/** 含有标签的表，里面的元素按照频繁度降序排序. */
	private FPTreeNodeTable nodetable = null;
	/** fp树的根，里面的标签为负值. */
	private FPTreeNode fptreeroot = null;
	
	
	/**
	   * 初始化树根节点，初始化节点表
	   * @param 排序后的节点
	   */
	public FPTree(ArrayList<Element> sortedelement, int choice,TagToInteger tagtointmap) {
		this.fptreeroot = new FPTreeNode();
		this.fptreeroot.setNodeTag(-1);
		this.fptreeroot.setChilerenNodes(new HashMap<Integer, FPTreeNode>());
		this.fptreeroot.setNextSameTag(null);
		this.fptreeroot.setFatherNode(null);
		nodetable = new FPTreeNodeTable(sortedelement, choice, tagtointmap);
	}
	
	/**
	   * 初始化树根节点，初始化节点表
	   * @param 排序后的节点
	   */
	public FPTree(ArrayList<NodeRecorder> sortedelement) {
		this.fptreeroot = new FPTreeNode();
		this.fptreeroot.setNodeTag(-1);
		this.fptreeroot.setChilerenNodes(new HashMap<Integer, FPTreeNode>());
		this.fptreeroot.setNextSameTag(null);
		this.fptreeroot.setFatherNode(null);
		nodetable = new FPTreeNodeTable(sortedelement);
	}
	
	/**
	   * 创建fptree
	   * @param 经过简化的事务的集合
	   *@author jh 
	   * @return 插入树中集合的大小
	   */
	public int creatFPTree(ArrayList<ArrayList<Integer>> tasks) {
		if(this.fptreeroot != null) {
			for(ArrayList<Integer> onetask : tasks) {
				//插入任务中所有的开始父结点为根结点
				FPTreeNode fathernode = this.fptreeroot;
				//执行插入操作
				for(int i = 0; i != onetask.size(); i++) {
					//分为两种情况，第一种是有孩子节点，另一种是没有
					
					if(!fathernode.haschild(onetask.get(i))) {
						FPTreeNode tempnode = new FPTreeNode();
						tempnode.setPassCount();
						tempnode.setNodeTag(onetask.get(i));
						tempnode.setNextSameTag(null);
						tempnode.setFatherNode(fathernode);
						
						tempnode.setChilerenNodes(new HashMap<Integer, FPTreeNode>());
						
						//加入到孩子节点之中
						fathernode.addChildNode(onetask.get(i), tempnode);
						
						//建立节点表项中的相同标签的链接
						FPTreeTagTableNode nodetofind = null;
						 for(FPTreeTagTableNode tablenode : this.nodetable.getNodeTable()) {
							if(tablenode.getTag() == onetask.get(i)) {
								nodetofind = tablenode;
								break;
							} 
						 }
						 if(nodetofind == null) {
							 System.out.println("致命错误 ！节点信息表不全！");
							 return -1;
						 } else {
							 FPTreeNode tempsamenode = nodetofind.getSameListHeader();
							 if(tempsamenode == null) {
								 nodetofind.setSameNodeListHeader(tempnode);
							 } else {
								 while (tempsamenode.getSameNodeNext() != null) {
									 tempsamenode = tempsamenode.getSameNodeNext();
								 }
								 tempsamenode.setNextSameTag(tempnode);
							 }
						}
							 
						//将父亲节点设为本节点
						fathernode = tempnode;
					} else {
						FPTreeNode tempnode = fathernode.getChild(onetask.get(i));
						//路径数加一
						tempnode.addPassCount();
						//将父亲节点设为本节点
						fathernode = tempnode;
					} //end if ... else
					
				}
			}//end insert tasks
			return tasks.size();
		} else {
			return -1;
		}
	}
	
	public ArrayList<Integer> testSinglePath(FPTreeNode root) {
		ArrayList<Integer> onepath = new ArrayList<Integer>();
		FPTreeNode currentnode = root;
		while(currentnode.getallchild().size() > 0 && currentnode.getallchild().size() == 1) {
			Iterator it = currentnode.getallchild().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry)it.next();
				currentnode = (FPTreeNode) entry.getValue();
				onepath.add(currentnode.getNodeTag());
			}
		}
		if(currentnode.getallchild().size() == 0) {
			return onepath;
		}
		if(currentnode.getallchild().size() > 1) {
			onepath = null;
			return null;
		} else 
			return null;
	}
	
	public FPTreeNode getFPTree() {
		if(this.fptreeroot != null) {
			return this.fptreeroot;
		} else 
			return null;
	}
	
	public FPTreeNodeTable getNodeTable() {
		return this.nodetable;
	}
}
