package simpleapp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mycompany.simpleapp.DijkstraSearch;
import com.mycompany.simpleapp.models.Graph;

public class SearchTest {
	
	Graph graph = new Graph();
	
	@BeforeEach
	public void setup() {
		graph.putVertex("start");
		graph.putVertex("a");
		graph.putVertex("b");
		graph.putVertex("c");
		graph.putVertex("d");
		graph.putVertex("end");

		graph.addEdge("start", "a", 5);
		graph.addEdge("start", "b", 2);
		
		graph.addEdge("b", "a", 8);
		graph.addEdge("b", "d", 7);
		
		graph.addEdge("a", "c", 4);
		graph.addEdge("a", "d", 2);
		
		graph.addEdge("c", "d", 6);
		graph.addEdge("c", "end", 3);
		
		graph.addEdge("d", "end", 1);
	}
	
	@Test
	public void dijkstraTest() {
		List<String> result = DijkstraSearch.search(graph, "start", "end");
		assertThat(result.get(0), equalTo("start -> a -> d -> end"));	
		assertThat(result.get(1), equalTo("8"));
		
	}

}
