package fpmax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import fptree.FPMinning;
import fptree.FPTree;
import fptree.FPTreeTagTableNode;
import fptree.NodeRecorder;

/**
 * 
 *用fpmax算法来找到最大的无冗余的频繁项目集
 *这个算法基于fp-growth
 **/
public class FpMax {
	private Stack<Integer> headertable = new Stack<Integer>();
	private MFITree mfitree = new MFITree();
	private FPMinning fpminning = new FPMinning();
	private Stack<Integer> midstack = new Stack<Integer>();
	private ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
	private double minisupport = 0.0;
	
	public void setMiniSupport(double mini) {
		this.minisupport = mini;
	}
	
	public FPTree InitFPtree(ArrayList<ArrayList<String>> rawdata) {
		this.fpminning.preparingData(rawdata, this.minisupport);
		FPTree fptree = new FPTree(this.fpminning.getSortedNodes(), 0, this.fpminning.getTagToIntMap());
		fptree.creatFPTree(this.fpminning.getInitTask());
		this.mfitree.initMfiTree(fptree);
		return fptree;
	}
	
	public void FPMax(FPTree fptree) {
		ArrayList<Integer> onepath = fptree.testSinglePath(fptree.getFPTree());
		if(onepath != null) {
			while(!this.headertable.empty()) {
				onepath.add(this.headertable.pop());
			}
			this.mfitree.insertIntoMfiTree(onepath);
		} else{
			ArrayList<FPTreeTagTableNode> headertableoffptree = fptree.getNodeTable().getNodeTable();
			for(int i = headertableoffptree.size() - 1 ; i != -1; i--) {
				this.headertable.push(headertableoffptree.get(i).getTag());
				ArrayList<ArrayList<NodeRecorder>> condbase = this.fpminning.genCondPatBase(headertableoffptree.get(i).getSameListHeader());
				ArrayList<Integer> genlist = new ArrayList<Integer>();
				for(ArrayList<NodeRecorder> eachrec : condbase) {
					for(NodeRecorder rc : eachrec) {
						if(!genlist.contains(rc.tag)) {
							genlist.add(rc.tag);
						}
					}
				}
				Stack<Integer> tempstack = new Stack<Integer>();
				while(!this.headertable.empty()) {
					Integer integer = this.headertable.pop();
					tempstack.push(integer);
					genlist.add(integer);
				}
				while(!tempstack.empty()) {
					this.headertable.push(tempstack.pop());
				}
				if(!mfitree.subSetTest(genlist)) {
					ArrayList<NodeRecorder> newnodetable = this.fpminning.genSubNodeTable(condbase);
					ArrayList<ArrayList<Integer>> newtasks = this.fpminning.genSubFreqentOnesets(condbase, newnodetable);
					
					FPTree treemaker = new FPTree(newnodetable);
					treemaker.creatFPTree(newtasks);
					if(treemaker.getNodeTable().getNodeTable().size() == 0) {
						this.headertable.pop();
						continue;
					}
					FPMax(treemaker);
				}
				while(!this.headertable.empty()) {
					this.headertable.pop();
				}
			}//end for
			return;
		}//end else
	}//end fpmax func
	
	public MFITree getMFITree() {
		return this.mfitree;
	}
	
	private void genFPMaxResult(MFITreeNode mfiroot) {
		if(mfiroot.getallchild().size() == 0) {
			ArrayList<String> list = new ArrayList<String>();
			Stack<Integer> stack = new Stack<Integer>();
			while(!this.midstack.empty()) {
				stack.push(this.midstack.pop());
			}
			while(!stack.empty()) {
				Integer integer = stack.pop();
				this.midstack.push(integer);
				list.add(this.fpminning.getTagToIntMap().getDigitTag(integer));
			}
			this.results.add(list);
			stack = null;
			return;
		}
		
		MFITreeNode tempitr = mfiroot;
		
		while(tempitr.getallchild().size() != 0) {
			Iterator it = tempitr.getallchild().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry)it.next();
				this.midstack.push((Integer)entry.getKey());
				genFPMaxResult((MFITreeNode) entry.getValue());
				this.midstack.pop();
			}
			return;
		}
	}
	
	public ArrayList<ArrayList<String>> getFpmaxResult() {
		genFPMaxResult(this.mfitree.getMfiTreeRoot());
		return this.results;
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
	public static void main(String []args) throws IOException {
		FpMax fpmaxtest = new FpMax();
		fpmaxtest.setMiniSupport(0.2);
		fpmaxtest.FPMax(fpmaxtest.InitFPtree(fpmaxtest.gettestcase()));
		
		for(ArrayList<String> oneresult :fpmaxtest.getFpmaxResult()) {
			for(String onereselem : oneresult) {
				System.out.print(onereselem + "\t");
			}
			System.out.println();
		}
	}
}//end class fpmax
