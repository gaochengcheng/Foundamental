package fpmax;

public class MFITreeNodeTableNode {
	private int tag = -1;
	private MFITreeNode samenodeheader = null;
	
	public void setTag(int tag) {
		this.tag = tag;
	}
	
	public void setSameNodeListHeader(MFITreeNode header) {
		this.samenodeheader = header;
	}
	
	public int getTag() {
		return this.tag;
	}
	
	public MFITreeNode getSameListHeader() {
		return this.samenodeheader;
	}
}
