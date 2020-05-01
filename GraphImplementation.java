import java.util.ArrayList;
import java.util.List;

/**
 * This class
 * @author Dan Tat
 *
 */

public class GraphImplementation implements Graph {

	protected ArrayList<ArrayList<Integer>> adjacencyList;
	protected List<Integer> sortedList;
	
	// Constructor - initializes the adjacency list
	public GraphImplementation(int vertices) {
		
		sortedList = new ArrayList<Integer>(vertices);
		adjacencyList = new ArrayList<ArrayList<Integer>>(vertices);
		for(int i=0; i<vertices; i++)
			adjacencyList.add(new ArrayList<Integer>());
	}
	
	@Override
	public void addEdge(int src, int target) throws Exception {
		
		if(target >= adjacencyList.size())
			throw new Exception("Target/source is not within the graph index bounds.");
		adjacencyList.get(src).add(target);
	}

	@Override
	public List<Integer> topologicalSort() throws Exception {
		// TODO Auto-generated method stub
		List<Integer> firstVertices = new ArrayList<Integer>();
		boolean visited[] = new boolean[adjacencyList.size()];
		
		for(int i=0; i<visited.length; i++)
			visited[i] = false;
		firstVertices = findFirstVertices();
		if(firstVertices.isEmpty() == true)
			throw new Exception("Topological sort is not possible. Graph contains cycle.");
		
		// Adds the vertices with no incoming edges to the sorted list
		for(int i=0; i<firstVertices.size(); i++) 
			sortedList.add(firstVertices.get(i));
		
		traverse(firstVertices);
		
		return sortedList;
	}
	
	@Override
	public List<Integer> neighbors(int vertex) throws Exception {
		
		return adjacencyList.get(vertex);
	} // neighbors
	
	// Searches for vertices with no incoming edges and returns them as a List.
	protected List<Integer> findFirstVertices() {
			
		List<Integer> firstVertices = new ArrayList<Integer>();
		boolean[] hasIncomingEdges = new boolean[adjacencyList.size()];
			
		for(int i=0; i<hasIncomingEdges.length; i++) {
				
			for(int j=0; j<adjacencyList.get(i).size(); j++)
				hasIncomingEdges[adjacencyList.get(i).get(j)] = true;
		}
			
		for(int i=0; i<hasIncomingEdges.length; i++)
			if(hasIncomingEdges[i] == false)
				firstVertices.add(i);
			
		return firstVertices;
	} // findFirstVertices

	
	// Traverses through all elements within 
	protected void traverse(List<Integer> vertices) throws Exception {
		
		for(int i=0; i<vertices.size(); i++) {
			
			if(!sortedList.contains(vertices.get(i)))
				sortedList.add(vertices.get(i));
			traverse(neighbors(vertices.get(i)));
		}
	} // traverse

}
