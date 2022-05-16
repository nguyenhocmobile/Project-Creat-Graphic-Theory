package Main;

import java.util.ArrayList;

public class Edge implements Comparable<Edge>{
	private Vertices startPoint;
	private Vertices endPoint;
	private int weight;
	private GraphicTheoryManager graph;
	private boolean isDirectionless;
	
	public Edge(Vertices startPoint, Vertices endPoint, int weight, boolean isDerectionless , GraphicTheoryManager graph) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.weight = weight;
		this.isDirectionless = isDerectionless;
		this.graph = graph;
		graph.addEdgeToList(this);
	}

	@Override
	public int compareTo(Edge o) {
		if(weight == o.weight) {
			return 0;
		}
		if(weight > o.weight) {
			return 1;
		}
		return -1;
	}

	@Override
	public String toString() {
		return "Canh [startPoint=" + startPoint + ", endPoint=" + endPoint + ", weight=" + weight + "]";
	}

	public Vertices getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Vertices startPoint) {
		this.startPoint = startPoint;
	}

	public Vertices getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Vertices endPoint) {
		this.endPoint = endPoint;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean isDirectionless() {
		return isDirectionless;
	}

	public void setDirectionless(boolean isDirectionless) {
		this.isDirectionless = isDirectionless;
	}
	
}
