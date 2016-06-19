package graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class MyGraph_DFS {
	//设计一个结构，把图存起来
	private ArrayList vertexList;//存储点的链表
	private int[][] edges;//存储边
	private int numOfEdges;//边的数目
	
	public MyGraph_DFS(int n){
		edges = new int[n][n];
		vertexList = new ArrayList(n);
		numOfEdges = 0;
	}
	
	//得到节点个数
	public int getNumOfVertex(){
		return vertexList.size();
	}
	
	//得到边的数目
	public int getNumOfEdges(){
		return numOfEdges;
	}
	
	//返回节点i的数据
	public Object getValueByIndex(int i){
		return vertexList.get(i);
	}
	
	//返回v1，v2的权值
	public int getWeight(int v1,int v2){
		return edges[v1][v2];
	}
	
	//插入节点值
	public void insertVertex(Object vertex){
		vertexList.add(vertexList.size(),vertex);
	}
	
	//插入边
	public void insertEdge(int v1, int v2, int weight){
		edges[v1][v2] = weight;
		numOfEdges++;
	}
	
	//删除边，当这条边的权重为0的时候，我们就认为这条边不存在了
	public void deleteEdge(int v1, int v2){
		edges[v1][v2] = 0;
		numOfEdges--;
	}
	
	//得到第一个邻接节点的下标
	//在邻接矩阵中，沿着一行扫描，遇到第一个不为0的位置，返回该位置的列坐标。
	//这个列坐标是与当前节点相邻的那个节点的节点标号
	public int getFirstNeighbor(int index){
		for(int j = 0; j < vertexList.size(); j++){
			if(edges[index][j] > 0)
				return j;
		}
		return -1;
	}
	
	//根据当前邻接节点的下标获取下一个邻接节点
	public int getNextNeighbor(int v1, int v2){
		for(int j = v2 + 1; j < vertexList.size(); j++){
			if(edges[v1][j] > 0)
				return j;
		}
		return -1;
	}
	
	//私有函数，根据选定的点，进行深度优先遍历
	private void depthFirstSearch(boolean[] isVisited, int i){
		//首先访问该节点，在控制台打印出来
		System.out.print(vertexList.get(i)+ "  ");
		//置该节点为已访问
		isVisited[i] = true;
		
		int w = getFirstNeighbor(i);
		while(w != -1){
			if(!isVisited[w]){
				depthFirstSearch(isVisited,w);
			}
			w = getNextNeighbor(i,w);
		}
	}
	
	
	//public声明的depthFirstSearch()和broadFirstSearch()函数，
    //是为了应对当该图是非连通图的情况，如果是非连通图，那么只通过一个结点是无法完全遍历所有结点的。
    
    //对外公开函数，深度优先遍历，与其同名私有函数属于方法重载
	public void depthFirstSearch(){
		boolean[] isVisited = new boolean[getNumOfVertex()];
		for(int i = 0; i < getNumOfVertex(); i++){
			isVisited[i] = false;
		}
		for(int i = 0; i < getNumOfVertex(); i++)
			//我们计划针对每个节点都做DFS搜索，因为防止这个图不是全连通的
			//但是如果在某个阶段，isVisited全为true了，那就说明全部都遍历过了
			if(!isVisited[i]){
				depthFirstSearch(isVisited,i);
			}
	}
	//内部私有函数，广度优先搜索
	private void broadFirstSearch(boolean[] isVisited,int i){
		int u,w;
		LinkedList queue = new LinkedList();
		
		//访问节点i,并且置对应的位置为true
		System.out.print(getValueByIndex(i)+"  ");
		isVisited[i] = true;
		
		queue.addLast(i);
		while(!queue.isEmpty()){
			//从queue中取出第一个节点,并且移除
			u = ((Integer)queue.removeFirst()).intValue();
			//获取u的第一个邻接节点
			w = getFirstNeighbor(u);
			while( w != -1){
				
				if(!isVisited[w]){
					//访问该结点
                    System.out.print(getValueByIndex(w)+"  ");
                    //标记已被访问
                    isVisited[w]=true;
                    //入队列
                    queue.addLast(w);
				}
				//寻找下一个节点
				w = getNextNeighbor(u, w);
			}
			
		}
	}
	
	
	//对外公开函数，广度优先遍历
	public void broadFirstSearch(){
		boolean[] isVisited = new boolean[getNumOfVertex()];
		for(int i = 0; i < getNumOfVertex(); i++){
			isVisited[i] = false;
		}
		for(int i = 0; i < getNumOfVertex(); i++)
			//我们计划针对每个节点都做DFS搜索，因为防止这个图不是全连通的
			//但是如果在某个阶段，isVisited全为true了，那就说明全部都遍历过了
			if(!isVisited[i]){
				broadFirstSearch(isVisited,i);
			}
				
	}
	
}
