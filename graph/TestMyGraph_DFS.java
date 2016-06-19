package graph;

public class TestMyGraph_DFS {
	public static void main(String[] args) {
		int n=8,e=9;
		String labels[] = {"1","2","3","4","5","6","7","8"};
		MyGraph_DFS graph = new MyGraph_DFS(n);
		for(String label:labels){
			graph.insertVertex(label);
		}
		
		//插入九条边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        graph.insertEdge(1, 0, 1);
        graph.insertEdge(2, 0, 1);
        graph.insertEdge(3, 1, 1);
        graph.insertEdge(4, 1, 1);
        graph.insertEdge(7, 3, 1);
        graph.insertEdge(7, 4, 1);
        graph.insertEdge(4, 2, 1);
        graph.insertEdge(5, 2, 1);
        graph.insertEdge(6, 5, 1);
        
//        graph.depthFirstSearch();
        graph.broadFirstSearch();
	}
}
