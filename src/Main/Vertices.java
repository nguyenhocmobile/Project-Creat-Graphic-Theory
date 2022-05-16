package Main;

import java.util.ArrayList;

public class Vertices {
	private int vertices;
	private GraphicTheoryManager graph;
	public Vertices( GraphicTheoryManager graph) {
		this.graph = graph;
		vertices = graph.getListVertices().size();
		graph.addVerticesToList(vertices);
		
	}
	public Vertices(int vertices, GraphicTheoryManager graph) {
		this.vertices = vertices;
		this.graph = graph;
	}
	public int getVertices() {
		return vertices;
	}
	public void setVertices(int vertices) {
		this.vertices = vertices;
	}
	public GraphicTheoryManager getGraph() {
		return graph;
	}
	public void setGraph(GraphicTheoryManager graph) {
		this.graph = graph;
	}
	@Override
	public String toString() {
		return  ""+vertices ;
	}
	
	
}
