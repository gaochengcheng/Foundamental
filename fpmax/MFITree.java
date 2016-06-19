package fpmax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import fptree.FPTree;
import fptree.FPTreeNodeTable;
import fptree.FPTreeTagTableNode;
import fptree.NodeRecorder;

public class MFITree {
	/**与fptree相同的一个结点表的结构，用于快速做子集比较，排序为fptree中的排序，以后的
	 * 任何子集都要与此表中的顺序相一致*/
	private ArrayList<MFITreeNodeTableNode> mfitreenodetable = new ArrayList<MFITreeNodeTableNode>();
	/** mfi树的根节点. */
	private MFITreeNode mfitreeroot = null;
	/** tag到与之排名的映射，可以快速整节点顺序，不对外提供访问. */
	private Map<Integer, Integer> wordrankmap = new HashMap<Integer, Integer>();
	/** 做子集检测时的缓冲，不对外提供访问. */
	private ArrayList<Integer> setcache = null;
	
	/**
	   * 按排名从前往后排序
	   * @param 排序前的标签序列
	   * @return void
	   */
	public void initMfiTree(FPTree fptree) {
		FPTreeNodeTable fpnodetable = fptree.getNodeTable();
		ArrayList<FPTreeTagTableNode> realtable = fpnodetable.getNodeTable();
		for(FPTreeTagTableNode nodeinfpnodetable : realtable) {
			MFITreeNodeTableNode tempnode = new MFITreeNodeTableNode();
			tempnode.setTag(nodeinfpnodetable.getTag());
			tempnode.setSameNodeListHeader(null);
			this.mfitreenodetable.add(tempnode);
			this.wordrankmap.put(nodeinfpnodetable.getTag(), realtable.indexOf(nodeinfpnodetable));
		}
		this.mfitreeroot = new MFITreeNode();
		this.mfitreeroot.setChilerenNodes(new HashMap<Integer, MFITreeNode>());
	}
	
	/**
	   * 按排名从前往后排序
	   * @param 排序前的标签序列
	   * @return void
	   */
	public boolean subSetTest(ArrayList<Integer> onemayset) {
		ArrayList<Integer> sortedset = new ArrayList<Integer>();
		ArrayList<NodeRecorder> recorder = new ArrayList<NodeRecorder>();
		for(Integer befelem : onemayset) {
			NodeRecorder rcelem = new NodeRecorder();
			rcelem.frequency = this.wordrankmap.get(befelem);
			rcelem.tag = befelem;
			recorder.add(rcelem);
		}
		this.SortRecordTask(recorder);
		for(NodeRecorder recelem : recorder) {
			sortedset.add(recelem.tag);
		}
		if(this.setcache == null) {
			this.setcache = sortedset;
			return this.testInTree(sortedset);
		}
		
		if(this.setcache.size() >= sortedset.size()) {
			int i = 0;
			for(; i != sortedset.size(); i++) {
				if(!this.setcache.contains(sortedset.get(i))) {
					break;
				}
			}
			if(i == sortedset.size()) {
				return true;
			}
		}
		this.setcache = sortedset;
		return this.testInTree(sortedset);
	}
	
	/**
	   * 将最大的频繁项插入到MFI树中，其中要先把要插入的项目集排序
	   * @param ArrayList<Integer>
	   * @return boolean
	   */
	public boolean insertIntoMfiTree(ArrayList<Integer> onemayset) {
		ArrayList<Integer> sortedset = new ArrayList<Integer>();
		ArrayList<NodeRecorder> recorder = new ArrayList<NodeRecorder>();
		for(Integer befelem : onemayset) {
			NodeRecorder rcelem = new NodeRecorder();
			rcelem.frequency = this.wordrankmap.get(befelem);
			rcelem.tag = befelem;
			recorder.add(rcelem);
		}
		this.SortRecordTask(recorder);
		for(NodeRecorder recelem : recorder) {
			sortedset.add(recelem.tag);
		}
		
		//插入任务中所有的开始父结点为根结点
		MFITreeNode fathernode = this.mfitreeroot;
		//执行插入操作
		for(int i = 0; i != sortedset.size(); i++) {
			//分为两种情况，第一种是有孩子节点，另一种是没有
			
			if(!fathernode.haschild(sortedset.get(i))) {
				MFITreeNode tempnode = new MFITreeNode();
				tempnode.setDistance(fathernode.getDistance() + 1);
				tempnode.setNodeTag(sortedset.get(i));
				tempnode.setNextSameTag(null);
				tempnode.setFatherNode(fathernode);
				
				tempnode.setChilerenNodes(new HashMap<Integer, MFITreeNode>());
				
				//加入到孩子节点之中
				fathernode.addChildNode(sortedset.get(i), tempnode);
				
				//建立节点表项中的相同标签的链接
				MFITreeNodeTableNode nodetofind = null;
				 for(MFITreeNodeTableNode tablenode : this.mfitreenodetable) {
					if(tablenode.getTag() == sortedset.get(i)) {
						nodetofind = tablenode;
						break;
					} 
				 }
				 if(nodetofind == null) {
					 System.out.println("致命错误 ！节点信息表不全！");
					 return false;
				 } else {
					 MFITreeNode tempsamenode = nodetofind.getSameListHeader();
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
				MFITreeNode tempnode = fathernode.getChild(sortedset.get(i));
				//将父亲节点设为本节点
				fathernode = tempnode;
			} //end if ... else
			
		}//end for
		return true;
	}//end insert tasks	
	/**
	   * 按排名从前往后排序
	   * @param 排序前的标签序列
	   * @return void
	   */
	private void SortRecordTask(ArrayList<NodeRecorder> beforsort){
		Comparator orderComparator = new Comparator(){
			public int compare(Object o1,Object o2){
				NodeRecorder u1 = (NodeRecorder)o1;
				NodeRecorder u2 = (NodeRecorder)o2;
				return (u1.frequency - u2.frequency);
			}
		};
		Collections.sort(beforsort,orderComparator);
	}
	
	private boolean testInTree(ArrayList<Integer> sortedsets) {
		if(sortedsets.size() == 0)
			return false;
		
		boolean returnvalue = false;
		Integer tainelem = sortedsets.get(sortedsets.size() - 1);
		Integer indexoftainelem = this.wordrankmap.get(tainelem);
		MFITreeNodeTableNode pointernode = this.mfitreenodetable.get(indexoftainelem);
		MFITreeNode pointer = pointernode.getSameListHeader();
		
		while(pointer != null) {
			if(pointer.getDistance() < sortedsets.size()) {
				//肯定不是这一支的子集，进入下一个节点
				pointer = pointer.getSameNodeNext();
				continue;
			} else if(pointer.getDistance() == sortedsets.size()) {
				//这个要全匹配才可以咯，顺序还要对
				MFITreeNode currentnode = pointer;
				int i = sortedsets.size() - 1;
				while(currentnode != this.mfitreeroot && i != -1) {
					if(currentnode.getNodeTag() != sortedsets.get(i)) {
						break;
					}
					currentnode = currentnode.getFathernode();
					i --;
				}
				if(i != -1 && currentnode != this.mfitreeroot) {
					//因为没有匹配成功而退出while
					pointer = pointer.getFathernode();
					continue;
				} else {
					returnvalue = true;
					break;
				}
			} else if(pointer.getDistance() > sortedsets.size()) {
				//这个只要全在里面就可以咯
				ArrayList<Integer> pathinfo = new ArrayList<Integer>();
				MFITreeNode currentnode = pointer;
				while(currentnode != this.mfitreeroot) {
					pathinfo.add(currentnode.getNodeTag());
					currentnode = currentnode.getFathernode();
				}
				int i = 0;
				for(; i != sortedsets.size(); i++) {
					if(!pathinfo.contains(sortedsets.get(i))) {
						break;
					}
				}
				if(i == sortedsets.size()) {
					returnvalue = true;
					break;
				} else {
					pointer = pointer.getFathernode();
					continue;
				}
			}
		}//end while
		return returnvalue;
	}
	
	public MFITreeNode getMfiTreeRoot() {
		return this.mfitreeroot;
	}
}
