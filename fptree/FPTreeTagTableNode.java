package fptree;

public class FPTreeTagTableNode {
	 /** 表示该节点的标签. */
	private int tag = -1;
	 /** 表示该节点的标签出现的次数. */
	private int count = -1;
	 /** 表示该节点的标签相同的链表的头节点. */
	private FPTreeNode samenodepointer = null;
	
	public void setTag(int tag) {
		this.tag = tag;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void setSameNodeListHeader(FPTreeNode header) {
		this.samenodepointer = header;
	}
	public int getCount() {
		return this.count;
	}
	public int getTag() {
		return this.tag;
	}
	
	public FPTreeNode getSameListHeader() {
		return this.samenodepointer;
	}
}
