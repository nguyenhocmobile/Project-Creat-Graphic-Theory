package Main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class GraphicTheoryManager {
	private ArrayList<ArrayList<Integer>> adjacencyMatrix = new ArrayList<ArrayList<Integer>>();
	private ArrayList<Boolean> listUnChecked;
	private int countVertices = 0;
	private ArrayList<ArrayList<Integer>> weightMatrix;
	private ArrayList<Edge> listEdge;
	private ArrayList<Integer> listVertices;

	public GraphicTheoryManager() {
		listVertices = new ArrayList<Integer>();
		weightMatrix = new ArrayList<ArrayList<Integer>>();
		listEdge = new ArrayList<Edge>();
		listUnChecked = new ArrayList<Boolean>();
	}
	
	public void creatListUnchecked() {
		for(int i=0; i< adjacencyMatrix.size();i++) {
			listUnChecked.add(true);
		}
	}

	public void addVerticesToList(int vertices) {
		listVertices.add(vertices);
		countVertices++;
		addVertices();
		creatListUnchecked();
	}

	public void addEdgeToList(Edge edge) {
		listEdge.add(edge);
		addEdge(edge.getStartPoint().getVertices(), edge.getEndPoint().getVertices(), edge.isDirectionless());
		creatWeightMatrix();
		System.out.println();
	}

	public ArrayList<Edge> getListEdge() {
		return listEdge;
	}

	public void setListEdge(ArrayList<Edge> listEdge) {
		this.listEdge = listEdge;
	}

	public ArrayList<Integer> getListVertices() {
		return listVertices;
	}

	public void setListVertices(ArrayList<Integer> listVertices) {
		this.listVertices = listVertices;
	}

	public void addVertices() {
		if (adjacencyMatrix.size() == 0) {
			adjacencyMatrix.add(new ArrayList<Integer>());
			adjacencyMatrix.get(0).add(0);
			return;
		}
		for (int i = 0; i < adjacencyMatrix.size(); i++) {
			adjacencyMatrix.get(i).add(0);
		}

		ArrayList<Integer> newLine = new ArrayList<Integer>();
		for (int i = 0; i < adjacencyMatrix.size() + 1; i++) {
			newLine.add(0);
		}
		adjacencyMatrix.add(newLine);
	}

	public String inArray(ArrayList<ArrayList<Integer>> list) {
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).size(); j++) {
				result += list.get(i).get(j) + "\t";
			}
			result += "\n";
		}
		return result;
	}

	public void addEdge(int diemDau1, int diemDau2, boolean laVoHuong) {
		if (diemDau1 > adjacencyMatrix.size() || diemDau2 > adjacencyMatrix.size()) {
			new RuntimeException("Du lieu khong hop le");
			return;
		}
		if (laVoHuong) {
			adjacencyMatrix.get(diemDau1).set(diemDau2, 1);
			adjacencyMatrix.get(diemDau2).set(diemDau1, 1);
		} else {
			adjacencyMatrix.get(diemDau1).set(diemDau2, 1);
		}

	}

//	public int bac(int i) {
//		int result = 0;
//		for (int j = 0; j < adjacencyMatrix.get(i).size(); j++) {
//			if (adjacencyMatrix.get(i).get(j) == 1) {
//				result++;
//			}
//		}
//		return result;
//	}
//
//	public int soCanh() {
//		int result = 0;
//		for (int i = 0; i < adjacencyMatrix.size(); i++) {
//			for (int j = 0; j < adjacencyMatrix.get(i).size(); j++) {
//				if (adjacencyMatrix.get(i).get(j) == 1) {
//					result++;
//				}
//			}
//		}
//		return result;
//	}
//
//	public boolean ke(int x, int y) {
//		boolean result = false;
//		if (adjacencyMatrix.get(x).get(y) == 1) {
//			return true;
//		}
//		return result;
//	}
//
//	public ArrayList<Integer> danhSachKe(int i, boolean laVoHuong) {
//		ArrayList<Integer> result = new ArrayList<Integer>();
//		if (laVoHuong) {
//			for (int j = 0; j < adjacencyMatrix.size(); j++) {
//				if (adjacencyMatrix.get(i).get(j) == 1) {
//					if (result.contains(j)) {
//						continue;
//					}
//					result.add(j);
//				}
//			}
//		} else {
//			for (int j = 0; j < adjacencyMatrix.size(); j++) {
//				if (adjacencyMatrix.get(i).get(j) == 1 || adjacencyMatrix.get(j).get(i) == 1) {
//					result.add(j);
//				}
//			}
//		}
//		return result;
//	}

	public ArrayList<Integer> DFS(int s) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(s);
		upDateListUnchecked(listUnChecked, s, false);
		while (!stack.isEmpty()) {
			int element = stack.pop();
			result.add(element);
			for (int j = 0; j < adjacencyList(element).size(); j++) {
				int u = adjacencyList(element).get(j);
				if (listUnChecked.get(u) == true) {
					stack.add(u);
					upDateListUnchecked(listUnChecked, u, false);
				}
			}

		}
		return result;
	}

	public void upDateListUnchecked(ArrayList<Boolean> list, int index, boolean valueReplace) {
		list.set(index, valueReplace);
	}

	public ArrayList<Integer> adjacencyList(int i) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int j = 0; j < adjacencyMatrix.size(); j++) {
			if (adjacencyMatrix.get(i).get(j) == 1) {
				if (result.contains(j)) {
					continue;
				}
				result.add(j);
			}
		}
		return result;
	}

	public ArrayList<Integer> BFS(int s) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		List<Integer> listExisted = new ArrayList<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		listExisted.add(s);
		result.add(s);
		upDateListUnchecked(listUnChecked, s, false);
		while (!queue.isEmpty()) {
			int p = queue.poll();
			for (int i = 0; i < adjacencyList(p).size(); i++) {
				int u = adjacencyList(p).get(i);
				if (listUnChecked.get(u) == true) {
					result.add(u);
					queue.add(u);
					upDateListUnchecked(listUnChecked, u, false);
				}

			}

		}
		return result;
	}

	public void creatWeightMatrix() {
		weightMatrix = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < countVertices; i++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			for (int j = 0; j < countVertices; j++) {
				row.add(999);
			}
			weightMatrix.add(row);
		}
		for (Edge k : listEdge) {
			weightMatrix.get(k.getStartPoint().getVertices()).set(k.getEndPoint().getVertices(), k.getWeight());
			weightMatrix.get(k.getEndPoint().getVertices()).set(k.getStartPoint().getVertices(), k.getWeight());
		}
	}

	public void bellmanFord(int s) {
		ArrayList<Integer> pre = new ArrayList<Integer>();
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i < countVertices; i++) {
			pre.add(null);
			if (i == s) {
				l.add(0);
			} else {
				l.add(999);
			}
		}
		boolean stop = false;
		int k = 0;
		while (stop == false) {
			stop = true;
			k++;
			for (int i = 0; i < countVertices; i++) {
				for (int j = 0; j < countVertices; j++) {
					if (l.get(j) > l.get(i) + weightMatrix.get(i).get(j)) {
						l.set(j, l.get(i) + weightMatrix.get(i).get(j));
						pre.set(j, i);
						stop = false;
					}
				}
			}

			if (k > countVertices) {
				if (stop == false) {
					System.out.println("Do thi co chu trinh am");
					stop = true;
				}
			}

		}
		System.out.println(l);
		System.out.println(pre);
	}
	

	public static void main(String[] args) {
		GraphicTheoryManager gp = new GraphicTheoryManager();
		Vertices vt0 = new Vertices( gp);
		Vertices vt1 = new Vertices( gp);
		Vertices vt2 = new Vertices(gp);
		Vertices vt3 = new Vertices( gp);
		Vertices vt4 = new Vertices( gp);
		Vertices vt5 = new Vertices(gp);
		Vertices vt6 = new Vertices( gp);
		Vertices vt7 = new Vertices( gp);

		gp.listEdge.add(new Edge(vt0, vt1, 2,false, gp));
		gp.listEdge.add(new Edge(vt0, vt5, 1,false, gp));
		gp.listEdge.add(new Edge(vt1, vt2, 2,false, gp));
		gp.listEdge.add(new Edge(vt1, vt3, 2,false, gp));
		gp.listEdge.add(new Edge(vt1, vt4, 4,false, gp));
		gp.listEdge.add(new Edge(vt2, vt4, 3,false, gp));
		gp.listEdge.add(new Edge(vt2, vt7, 1,false, gp));
		gp.listEdge.add(new Edge(vt3, vt4, 4,false, gp));
		gp.listEdge.add(new Edge(vt3, vt5, 3,false, gp));
		gp.listEdge.add(new Edge(vt4, vt6, 1,false, gp));
		gp.listEdge.add(new Edge(vt5, vt6, 7,false, gp));
		gp.listEdge.add(new Edge(vt6, vt7, 6,false, gp));
		// System.out.println(gp.countVertices);
		//gp.bellmanFord(0);
		// System.out.println(gp.listEdge);
		//System.out.println(gp.inArray());
		System.out.println(gp.BFS(0));
	}

}
