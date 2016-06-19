package fptree;

import java.util.Map;

public class FPTreeNode {
	 /** 指向有同一标签的其它节点. */
	private FPTreeNode nextSameTag = null;
	 /** 表示该节点的标签. */
	private int nodetag = -1;
	 /** 指向该节点的父亲节点. */
	private FPTreeNode fathernode = null;
	 /** 键为节点中的标签对应的数字，可以加快查找速度. */
	private Map<Integer, FPTreeNode> childrennodes = null; 
	private int passcount = -1;
	
	public void setPassCount() {
		this.passcount = 1;
	}
	
	public void addPassCount() {
		this.passcount ++;
	}
	
	public int getPassCount() {
		return this.passcount;
	}
	
	public void setNodeTag(int tag) {
		this.nodetag = tag;
	}
	
	public void setNextSameTag(FPTreeNode nextsametag) {
		this.nextSameTag = nextsametag;
	}
	public boolean haschild(int tag) {
		if(this.childrennodes.containsKey(tag)) {
			return true;
		} else 
			return false;
	}
	public void setChilerenNodes(Map<Integer, FPTreeNode> children) {
		this.childrennodes = children;
	}
	
	public void setFatherNode(FPTreeNode fathernode) {
		this.fathernode = fathernode;
	}
	public Map<Integer, FPTreeNode> getallchild() {
		return this.childrennodes;
	}
	public int getNodeTag() {
		return this.nodetag;
	}
	
	public FPTreeNode getFathernode() {
		return this.fathernode;
	}
	
	public FPTreeNode getSameNodeNext() {
		return this.nextSameTag;
	}
	public FPTreeNode getChild(int tag) {
		return this.childrennodes.get(tag);
	}
	public boolean addChildNode(int tag, FPTreeNode node) {
		if(tag == node.getNodeTag()) {
			this.childrennodes.put(tag, node);
			return true;
		}else {
			System.out.println("提供的孩子节点信息不一致!");
			return false;
		}
	}
}
