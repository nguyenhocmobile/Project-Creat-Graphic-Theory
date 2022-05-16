package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

import Main.GraphicTheoryManager;

public class MainFrame extends JFrame{
	private Toolbar toolbar;
	private MainArea mainArea;
	private GraphicTheoryManager graph;

	
	public MainFrame() {
		this.setAlwaysOnTop(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(60, 0);
		this.setSize(new Dimension(1400,800));
		this.setTitle("GraphicTheoryManager");
		this.setLayout(new BorderLayout(5, 0));
		addComponent();
		this.setVisible(true);
	}


	private void addComponent() {
		addToolBar();
		addMainArea();
	}

	private void addMainArea() {
		graph = new GraphicTheoryManager();
		mainArea = new MainArea(graph, toolbar);
		this.add(mainArea, BorderLayout.CENTER);
	}

	private void addToolBar() {
		toolbar = new Toolbar();
		this.add(toolbar, BorderLayout.NORTH);
	}

	


	public static void main(String[] args) {
		

	}

}
