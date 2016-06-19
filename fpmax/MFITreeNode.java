package fpmax;

import java.util.Map;

public class MFITreeNode {
	/** 指向有同一标签的其它节点. */
	private MFITreeNode nextSameTag = null;
	 /** 表示该节点的标签. */
	private int nodetag = -1;
	 /** 指向该节点的父亲节点. */
	private MFITreeNode fathernode = null;
	 /** 键为节点中的标签对应的数字，可以加快查找速度. */
	private Map<Integer, MFITreeNode> childrennodes = null; 
	/** 到根节点的距离，主要用于子集比较时方便. */
	private int distancetoroot = 0;
	
	public void setDistance(int newdistance) {
		this.distancetoroot = newdistance;
	}
	
	public int getDistanceToRoot() {
		return this.distancetoroot;
	}
	
	public int getDistance() {
		return this.distancetoroot;
	}
	
	public void setNodeTag(int tag) {
		this.nodetag = tag;
	}
	
	public void setNextSameTag(MFITreeNode nextsametag) {
		this.nextSameTag = nextsametag;
	}
	
	public boolean haschild(int tag) {
		if(this.childrennodes.containsKey(tag)) {
			return true;
		} else 
			return false;
	}
	
	public void setChilerenNodes(Map<Integer, MFITreeNode> children) {
		this.childrennodes = children;
	}
	
	public void setFatherNode(MFITreeNode fathernode) {
		this.fathernode = fathernode;
	}
	
	public Map<Integer, MFITreeNode> getallchild() {
		return this.childrennodes;
	}
	
	public int getNodeTag() {
		return this.nodetag;
	}
	
	public MFITreeNode getFathernode() {
		return this.fathernode;
	}
	
	public MFITreeNode getSameNodeNext() {
		return this.nextSameTag;
	}
	
	public MFITreeNode getChild(int tag) {
		return this.childrennodes.get(tag);
	}
	
	public boolean addChildNode(int tag, MFITreeNode node) {
		if(tag == node.getNodeTag()) {
			this.childrennodes.put(tag, node);
			return true;
		}else {
			System.out.println("提供的孩子节点信息不一致!");
			return false;
		}
	}
	
}
