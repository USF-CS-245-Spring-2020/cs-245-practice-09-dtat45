import java.lang.Exception;
import java.util.List;

public interface Graph {
	
	// Eventually, our graphs will include more functions than this
	// ... but let's start here.
	public void addEdge(int v1, int v2) throws Exception;
	public List<Integer> topologicalSort() throws Exception;
	public List<Integer> neighbors(int vertex) throws Exception;

}
