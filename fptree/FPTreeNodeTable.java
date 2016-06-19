package fptree;

import java.util.ArrayList;

import struct.Element;


/**
 * fptree的节点表，按出现的频繁度排序，所有的频繁项集都按此排序
 * 提供同一个标签在fptree中的线索，从而找到条件模式基用来递归
 * 挖掘
 **/
public class FPTreeNodeTable {
	/** 排序后频繁项集的表. */
	private ArrayList<FPTreeTagTableNode> fptreetagtable = new ArrayList<FPTreeTagTableNode>();
	
	
	public FPTreeNodeTable(ArrayList<NodeRecorder> sortedrecord) {
		// TODO Auto-generated constructor stub
		for(NodeRecorder elem : sortedrecord) {
			FPTreeTagTableNode tablenode = new FPTreeTagTableNode();
			tablenode.setSameNodeListHeader(null);
			tablenode.setCount(elem.frequency);
			tablenode.setTag(elem.tag);
			fptreetagtable.add(tablenode);
		}
	}
	
	
	public FPTreeNodeTable(ArrayList<Element> sortedelement, int choice, TagToInteger tagtointmap) {
		// TODO Auto-generated constructor stub
		for(Element elem : sortedelement) {
			FPTreeTagTableNode tablenode = new FPTreeTagTableNode();
			tablenode.setSameNodeListHeader(null);
			tablenode.setCount(elem.frequency);
			tablenode.setTag(tagtointmap.getTagDigit(elem.tag));
			fptreetagtable.add(tablenode);
		}
	}
	public ArrayList<FPTreeTagTableNode> getNodeTable() {
		return this.fptreetagtable;
	}
}
