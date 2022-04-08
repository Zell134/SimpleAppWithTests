package simpleapp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mycompany.simpleapp.models.Edge;
import com.mycompany.simpleapp.models.Graph;
import com.mycompany.simpleapp.models.Vertex;

public class GraphTest {

	private Graph graph;
	
	@BeforeEach
	public void setup() {
		graph = new Graph();
	}
	
	@Test
	public void putVertexTest() {
		assertThat(graph.getVertexes().size(), is(0));
		graph.putVertex("test"); 
		assertThat(graph.getVertexes().size(), is(1));
	}
	
	@Test
	public void putOneVertexWithSameNameTest() {
		
		assertThat(graph.getVertexes().size(), is(0));
		graph.putVertex("test");
		graph.putVertex("test");
		graph.putVertex("test");
		assertThat(graph.getVertexes().size(), is(1));

	}
	
	@Test
	public void getVertexByNameTest() {
		
		graph.putVertex("start");
		graph.putVertex("end");

		graph.addEdge("start", "end", 2);
		graph.addEdge("end", "start", 1);
		
		Set<Edge> vertex = graph.getVertexByName("start"); 
		for(Edge edge : vertex)
			if (edge.getTargetVertex().equals(new Vertex("end")))
				assertThat(edge.getWeight(), is(2));

		for(Edge edge : vertex)
			if (edge.getTargetVertex().equals(new Vertex("start")))
				assertThat(edge.getWeight(), is(6));
	}
	
	@Test 
	public void addEdgeTest() {
		
		graph.putVertex("start");
		graph.putVertex("end");

		graph.addEdge("start", "end", 2);
		assertThat(graph.getVertexes().size(), is(2));
		
		Set<Edge> vertex = graph.getVertexByName("start"); 
		for(Edge edge : vertex)
			if (edge.getTargetVertex().equals(new Vertex("end")))
				assertThat(edge.getWeight(), is(2));

	}
}
