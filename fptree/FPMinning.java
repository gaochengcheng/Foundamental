package fptree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import struct.Element;

/**
 * fpminning主类，主要功能模块为数据准备
 * 通过频繁1项集构建树，与标签表
 * 之后通过fp-growth算法来得到所有满足支持度的频繁
 * 项目集合
 **/
public class FPMinning {
	 /** 最小支持度. */
	private double minisupport = 0.0;
	 /** 从网页中提取的但不能被直接用作算法的项目集合. */
	private ArrayList<ArrayList<String>> rawtasksets = null;
	/** 处理后的项目集，可以直接用作fp-growth. */
	private ArrayList<ArrayList<Integer>> freqentonesets = new ArrayList<ArrayList<Integer>>();
	/**第一建树时可以建节点表.*/
	private ArrayList<Element> sortedelement = new ArrayList<Element>();
	/**标签到数字的映射.*/
	private TagToInteger tagtointmap = new TagToInteger();
	
	/**
	   * 根据支持度来削减标签
	   * @param void
	   * @return void
	   */
	private void GenerateFreqentOneItems() {
		int mincount = (int) (this.rawtasksets.size() * this.minisupport);
		Map<String, Integer> allitemsmap = new HashMap<String, Integer>();
		for(ArrayList<String> task : this.rawtasksets) {
			//先获取rawtasksets的一个list，然后对这个list再用for循环遍历
			for(String tag : task) {
				if(allitemsmap.containsKey(tag)) {
					int tempint = allitemsmap.get(tag).intValue();
					tempint ++;
					allitemsmap.put(tag, tempint);
				} else {
					allitemsmap.put(tag, 1);
				}
			}
		}
		//去掉不满足支持度的项
		Iterator itr = allitemsmap.entrySet().iterator();
		ArrayList<String> cutrecorder = new ArrayList<String>();
		while(itr.hasNext()) {
			Map.Entry entry = (Map.Entry)itr.next();
	        if((Integer)entry.getValue() < mincount) {
	        	cutrecorder.add((String) entry.getKey());
	        }
		}
		for(String tobecut : cutrecorder) {
			allitemsmap.remove(tobecut);
		}
		
		//初始化一个数组并按其支持度大小降序排序，也可以初始化nodetable
		ArrayList<Element> tempelement = new ArrayList<Element>();
		Iterator itrt = allitemsmap.entrySet().iterator();
		while(itrt.hasNext()) {
			Map.Entry entry = (Map.Entry)itrt.next();
	        Element elem = new Element();
	        elem.tag = (String) entry.getKey();
	        elem.frequency = (Integer) entry.getValue();
	        tempelement.add(elem);
		}
		SortElement(tempelement);
		
		//第一建树时可以建节点表
		this.sortedelement = tempelement;
		//初始化词到数字的映射,只映射频繁的项
		
		int id = 1;
		for(Element elem : tempelement) {
			this.tagtointmap.addIntegerToTagMap(id, elem.tag);
			id ++;
		}
		
		
	}
	/**
	   * 把没有处理的数据转化为削减后的排序的可以建树的事务
	   * @param void
	   * @return void
	   */
	private boolean GenerateFreqentOnesets() {
		if(this.rawtasksets != null) {
			for(ArrayList<String> rawtask : rawtasksets) {
				ArrayList<Integer> realtask = new ArrayList<Integer>();
				for(String elem : rawtask) {
					if(this.tagtointmap.contains(elem)) {
						realtask.add(this.tagtointmap.getTagDigit(elem));
					}
				}
				SortTaskList(realtask);
				this.freqentonesets.add(realtask);
			}
			return true;
		} 
		return false;
	}
	/**
	   * 对事务内部的项按支持度排序
	   * @param 排序前的标签序列
	   * @return void
	   */
	private void SortTaskList(ArrayList<Integer> task) {
		Comparator orderComparator = new Comparator(){
			public int compare(Object o1,Object o2){
				Integer u1 = (Integer)o1;
				Integer u2 = (Integer)o2;
				return (u1 - u2);
			}
		};
		Collections.sort(task,orderComparator);
	}	
	/**
	   * 按支持度从大到小排序
	   * @param 排序前的标签序列
	   * @return void
	   */
	private void SortRecord(ArrayList<NodeRecorder> beforsort){
		Comparator orderComparator = new Comparator(){
			public int compare(Object o1,Object o2){
				NodeRecorder u1 = (NodeRecorder)o1;
				NodeRecorder u2 = (NodeRecorder)o2;
				return (u2.frequency - u1.frequency);
			}
		};
		Collections.sort(beforsort,orderComparator);
	}
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
	/**
	   * 按支持度从大到小排序
	   * @param 排序前的标签序列
	   * @return void
	   */
	private void SortElement(ArrayList<Element> beforsort){
		Comparator orderComparator = new Comparator(){
			public int compare(Object o1,Object o2){
				Element u1 = (Element)o1;
				Element u2 = (Element)o2;
				return (u2.frequency - u1.frequency);
			}
		};
		Collections.sort(beforsort,orderComparator);
	}
	
	/**
	   * 为第一次生成fptree准备数据
	   * @param Pages 网页集合
	   * @param mini 最小支持度
	   * @param choice 处理的是网页标题还是正文
	   * @return void
	   */
	public void preparingData(ArrayList<ArrayList<String>> rawdata, double mini) {
		this.minisupport = mini;
		this.rawtasksets = rawdata;
		this.GenerateFreqentOneItems();
		this.GenerateFreqentOnesets();
	}
	/**
	   * fp-growth挖掘算法入口
	   * @param null
	   * @return ArrayList<ArrayList<Integer>> 所有的频繁项目集合
	   */
	public ArrayList<ArrayList<Integer>> FPminning() {
		FPTree treemaker = new FPTree(this.sortedelement, 0, this.tagtointmap);
		treemaker.creatFPTree(this.freqentonesets);
		FPTreeNode root = treemaker.getFPTree();
		FPTreeNodeTable nodetable = treemaker.getNodeTable();
		return fpgrowth(root, nodetable);
	}
	/**
	   * fp-growth
	   * @param FPTreeNode fp树根
	   * @param FPTreeNodeTable 总的结点表，降序排列
	   * @return ArrayList<ArrayList<Integer>> 所有的频繁项目集合
	   */
	private ArrayList<ArrayList<Integer>> fpgrowth(FPTreeNode temproot, FPTreeNodeTable nodetable) {
		ArrayList<ArrayList<Integer>> freqentsets = new ArrayList<ArrayList<Integer>>();
		for(int j = nodetable.getNodeTable().size() - 1; j != -1; j --) {
			
			int temptag = nodetable.getNodeTable().get(j).getTag();
			FPTreeNode header = nodetable.getNodeTable().get(j).getSameListHeader();
			ArrayList<ArrayList<NodeRecorder>> condbase = genCondPatBase(header);
			
			ArrayList<NodeRecorder> newnodetable = genSubNodeTable(condbase);
			ArrayList<ArrayList<Integer>> newtasks = genSubFreqentOnesets(condbase, newnodetable);
			
			FPTree treemaker = new FPTree(newnodetable);
			treemaker.creatFPTree(newtasks);
			FPTreeNode root = treemaker.getFPTree();
			FPTreeNodeTable nextnodetable = treemaker.getNodeTable();
			
			if(currentState(root)) {
				ArrayList<Integer> subfreqentset = new ArrayList<Integer>();
				for(FPTreeTagTableNode tablenode : nextnodetable.getNodeTable()) {
					subfreqentset.add(tablenode.getTag());
				}
				subfreqentset.add(temptag);
				if(subfreqentset.size() != 0)
					freqentsets.add(subfreqentset);
				
			} else {
				ArrayList<ArrayList<Integer>> retfreqentsets = fpgrowth(root, nextnodetable);
				for(ArrayList<Integer> oneset : retfreqentsets) {
					oneset.add(temptag);
					freqentsets.add(oneset);
				}
			}
		}
		return freqentsets;
	}
	
	/**
	   * 得到给定标签节点的条件模式基用于构建条件fp-tree
	   * @param FPTreeNode 第一个相同标签的节点
	   * @return ArrayList<ArrayList<NodeRecorder>> 一个条件模式基的集合
	   */
	public ArrayList<ArrayList<NodeRecorder>> genCondPatBase(FPTreeNode header) {
		
		ArrayList<ArrayList<NodeRecorder>> condpatbase = new ArrayList<ArrayList<NodeRecorder>>();
		FPTreeNode temppattenbase = header;
		
		while(temppattenbase != null) {
			
			ArrayList<NodeRecorder> onebase = new ArrayList<NodeRecorder>();
			int passtime = temppattenbase.getPassCount();
			FPTreeNode father = temppattenbase.getFathernode();
			while(father.getNodeTag() != -1) {
				NodeRecorder rec = new NodeRecorder();
				rec.tag = father.getNodeTag();
				rec.frequency = passtime;
				onebase.add(rec);
				father = father.getFathernode();
			}//end one pattern base while
			if(onebase.size() != 0) {
				condpatbase.add(onebase);
			} else onebase = null;
			temppattenbase = temppattenbase.getSameNodeNext();
		}//end while
		
		return condpatbase;
	}

	/**
	   * 得到条件模式基产生的树的结点表
	   * @param ArrayList<ArrayList<NodeRecorder>> 条件模式基
	   * @return ArrayList<NodeRecorder>
	   */
	public ArrayList<NodeRecorder> genSubNodeTable(ArrayList<ArrayList<NodeRecorder>> condbase) {
		int mincount = (int) (this.rawtasksets.size() * this.minisupport);
		Map<Integer, Integer> allitemsmap = new HashMap<Integer, Integer>();
		for(ArrayList<NodeRecorder> task : condbase) {
			for(NodeRecorder onerecord : task) {
				if(allitemsmap.containsKey(onerecord.tag)) {
					int tempint = allitemsmap.get(onerecord.tag).intValue();
					tempint += onerecord.frequency;
					allitemsmap.put(onerecord.tag, tempint);
				} else {
					allitemsmap.put(onerecord.tag, onerecord.frequency);
				}
			}
		}
		//去掉不满足支持度的项
		Iterator itr = allitemsmap.entrySet().iterator();
		ArrayList<Integer> cutrecorder = new ArrayList<Integer>();
		while(itr.hasNext()) {
			Map.Entry entry = (Map.Entry)itr.next();
	        if((Integer)entry.getValue() < mincount) {
	        	cutrecorder.add((Integer) entry.getKey());
	        }
		}
		for(Integer tobecut : cutrecorder) {
			allitemsmap.remove(tobecut);
		}
		
		//初始化一个数组并按其支持度大小降序排序，也可以初始化nodetable
		ArrayList<NodeRecorder> tempelement = new ArrayList<NodeRecorder>();
		Iterator itrt = allitemsmap.entrySet().iterator();
		while(itrt.hasNext()) {
			Map.Entry entry = (Map.Entry)itrt.next();
			NodeRecorder elem = new NodeRecorder();
	        elem.tag = (Integer) entry.getKey();
	        elem.frequency = (Integer) entry.getValue();
	        tempelement.add(elem);
		}
		SortRecord(tempelement);
		return tempelement;
	}
	
	/**
	   * 把没有处理的数据转化为削减后的排序的可以建树的事务
	   * @param void
	   * @return void
	   */
	public ArrayList<ArrayList<Integer>> genSubFreqentOnesets(ArrayList<ArrayList<NodeRecorder>> condbase, ArrayList<NodeRecorder> nodetable) {
		ArrayList<ArrayList<NodeRecorder>> cutted = new ArrayList<ArrayList<NodeRecorder>>();
		for(ArrayList<NodeRecorder> onebase : condbase) {
			ArrayList<NodeRecorder> newtask = new ArrayList<NodeRecorder>();
			for(NodeRecorder onerecord : onebase) {
				for(int i = 0; i != nodetable.size(); i++) {
					if(onerecord.tag == nodetable.get(i).tag) {
						NodeRecorder real = new NodeRecorder();
						real.frequency = i;
						real.tag = onerecord.tag;
						newtask.add(real);
						break;
					}
				}
			}
			if(newtask.size() != 0) {
				this.SortRecordTask(newtask);
				cutted.add(newtask);
			}
		}
		
		ArrayList<ArrayList<Integer>> returnvalue = new ArrayList<ArrayList<Integer>>();
		for(ArrayList<NodeRecorder> oneset : cutted) {
			ArrayList<Integer> oneretset = new ArrayList<Integer>();
			for(NodeRecorder rc : oneset) {
				oneretset.add(rc.tag);
			}
			returnvalue.add(oneretset);
		}
		return returnvalue;
	}
	
	/**
	   * 看是否到了结束递归的条件
	   * @param FPTreeNode 树根结点
	   * @return boolean
	   */
	private boolean currentState(FPTreeNode root) {
//		if(root.getallchild().size() == 0)
//			return true;
		
//		boolean currentstate = true;
		FPTreeNode currentnode = root;
		while(currentnode.getallchild().size() > 0 && currentnode.getallchild().size() == 1) {
			Iterator it = currentnode.getallchild().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry)it.next();
				currentnode = (FPTreeNode) entry.getValue();
			}
		}
		
		if(currentnode.getallchild().size() == 0) {
			return true;
		} else if(currentnode.getallchild().size() > 1) {
			return false;
		} 
		return true;
	}
	
	public TagToInteger getTagToIntMap() {
		return this.tagtointmap;
	}
	
	public ArrayList<ArrayList<String>> gettestcase() {
		ArrayList<ArrayList<String>> retval = new ArrayList<ArrayList<String>>();
		ArrayList<String> onetask = new ArrayList<String>();
		onetask.add("a");
		onetask.add("b");
		onetask.add("c");
		onetask.add("e");
		onetask.add("f");
		onetask.add("o");
		retval.add(onetask);
		
		onetask = new ArrayList<String>();
		onetask.add("a");
		onetask.add("c");
		onetask.add("g");
		retval.add(onetask);
		
		onetask = new ArrayList<String>();
		onetask.add("e");
		onetask.add("i");
		retval.add(onetask);
		
		onetask = new ArrayList<String>();
		onetask.add("a");
		onetask.add("c");
		onetask.add("d");
		onetask.add("e");
		onetask.add("g");
		retval.add(onetask);
		
		onetask = new ArrayList<String>();
		onetask.add("a");
		onetask.add("c");
		onetask.add("e");
		onetask.add("g");
		onetask.add("l");
		retval.add(onetask);
		
		onetask = new ArrayList<String>();
		onetask.add("e");
		onetask.add("j");
		retval.add(onetask);
		
		onetask = new ArrayList<String>();
		onetask.add("a");
		onetask.add("b");
		onetask.add("c");
		onetask.add("e");
		onetask.add("f");
		onetask.add("p");
		retval.add(onetask);
		
		onetask = new ArrayList<String>();
		onetask.add("a");
		onetask.add("c");
		onetask.add("d");
		retval.add(onetask);

		onetask = new ArrayList<String>();
		onetask.add("a");
		onetask.add("c");
		onetask.add("e");
		onetask.add("g");
		onetask.add("m");
		retval.add(onetask);
		
		onetask = new ArrayList<String>();
		onetask.add("a");
		onetask.add("c");
		onetask.add("e");
		onetask.add("g");
		onetask.add("n");
		retval.add(onetask);
		return retval;
	}
	
	public ArrayList<Element> getSortedNodes() {
		return this.sortedelement;
	}
	
	public ArrayList<ArrayList<Integer>> getInitTask() {
		return this.freqentonesets;
	}
	
	public static void main(String []args) throws IOException {
		FPMinning fptest = new FPMinning();
		fptest.preparingData(fptest.gettestcase(), 0.2);
		
		for(ArrayList<Integer> oneresult : fptest.FPminning()) {
			for(Integer onereselem : oneresult) {
				System.out.print(fptest.getTagToIntMap().getDigitTag(onereselem) + "\t");
			}
			System.out.println();
		}
	}
}
