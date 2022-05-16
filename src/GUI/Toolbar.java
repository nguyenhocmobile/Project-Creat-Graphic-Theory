package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.DirectoryNotEmptyException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Toolbar extends JPanel {
	private JButton btnAddVertices;
	private JButton btnConnectVertices;
	private JButton btnRemove;
	private String[] arrAlgorithms = { "Depth First Search - DFS", "Breadth First Search - BFS", "Ford - Bellman" };
	private JComboBox boxAlgorithms;
	private JCheckBox checkIsUndirected;
	private boolean isAddVertices;
	private boolean isConnectVertices;
	private boolean isRemove;
	private Color primaryText;
	private Color backgroundColor;
	private Color isChooseForeground;
	private Color isChooseBackground;
	private boolean isUndirected;

	public Toolbar() {
		isAddVertices = true;
		isConnectVertices = false;
		isRemove = false;
		primaryText = Color.black;
		backgroundColor = Color.white;
		isChooseBackground = Color.green;
		isChooseForeground = Color.white;
		isUndirected = true;
		addVerticesButton();
		addConnectVerticesButton();
		addAlgorithmsBox();
		addRemoveButton();
		addCheckUndirected();
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	private void addCheckUndirected() {
		checkIsUndirected = new JCheckBox("Undirected", isUndirected);
		checkIsUndirected.setBackground(Color.white);
		checkIsUndirected.setFocusable(false);
		checkIsUndirected.addActionListener(new ActionCheckDirection());
		this.add(checkIsUndirected);
	}

	private void addRemoveButton() {
		btnRemove = new JButton("Remove");
		btnRemove.setBackground(Color.white);
		btnRemove.setIcon(new ImageIcon(
				new ImageIcon("./src/Image/Remove.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btnRemove.setFocusable(false);
		btnRemove.addActionListener(new ActionButton());
		this.add(btnRemove);
	}

	private void addAlgorithmsBox() {
		boxAlgorithms = new JComboBox(arrAlgorithms);
		boxAlgorithms.setBackground(backgroundColor);
		boxAlgorithms.setForeground(primaryText);
		boxAlgorithms.setPreferredSize(new Dimension(200, 30));
		this.add(boxAlgorithms);
	}

	private void addConnectVerticesButton() {
		btnConnectVertices = new JButton("Connect vertices");
		btnConnectVertices.setIcon(new ImageIcon(new ImageIcon("./src/Image/ConnectVertices.png").getImage()
				.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btnConnectVertices.setFocusable(false);
		btnConnectVertices.setBackground(backgroundColor);
		btnConnectVertices.setForeground(primaryText);
		btnConnectVertices.addActionListener(new ActionButton());
		this.add(btnConnectVertices);
	}

	private void addVerticesButton() {
		btnAddVertices = new JButton("Add vertices");
		if(isAddVertices == true) {
			btnAddVertices.setBackground(isChooseBackground);
			btnAddVertices.setForeground(isChooseForeground);
		}else{
			btnAddVertices.setForeground(primaryText);
			btnAddVertices.setBackground(backgroundColor);
		}
		btnAddVertices.setIcon(new ImageIcon(
				new ImageIcon("./src/Image/AddVertices.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btnAddVertices.setFocusable(false);
		btnAddVertices.addActionListener(new ActionButton());
		this.add(btnAddVertices);
	}
	
	private class ActionCheckDirection implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(isUndirected == true) {
				isUndirected = false;
			}else {
				isUndirected = true;
			}
		}
		
	}

	private class ActionButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Add vertices":
				isAddVertices = true;
				isConnectVertices = false;
				isRemove = false;
				btnAddVertices.setBackground(isChooseBackground);
				btnAddVertices.setForeground(isChooseForeground);
				btnConnectVertices.setBackground(backgroundColor);
				btnConnectVertices.setForeground(primaryText);
				btnRemove.setBackground(backgroundColor);
				btnRemove.setForeground(primaryText);
				break;
			case "Connect vertices":
				isAddVertices = false;
				isConnectVertices = true;
				isRemove = false;
				btnConnectVertices.setBackground(isChooseBackground);
				btnConnectVertices.setForeground(isChooseForeground);
				btnAddVertices.setBackground(backgroundColor);
				btnAddVertices.setForeground(primaryText);
				btnRemove.setBackground(backgroundColor);
				btnRemove.setForeground(primaryText);
				break;
			case "Remove":
				isAddVertices = false;
				isConnectVertices = false;
				isRemove = true;
				btnRemove.setBackground(isChooseBackground);
				btnRemove.setForeground(isChooseForeground);
				btnConnectVertices.setBackground(backgroundColor);
				btnConnectVertices.setForeground(primaryText);
				btnAddVertices.setBackground(backgroundColor);
				btnAddVertices.setForeground(primaryText);
				break;
			default:
				break;
			}
		}

	}

	public boolean isAddVertices() {
		return isAddVertices;
	}

	public void setAddVertices(boolean isAddVertices) {
		this.isAddVertices = isAddVertices;
	}

	public boolean isConnectVertices() {
		return isConnectVertices;
	}

	public void setConnectVertices(boolean isConnectVertices) {
		this.isConnectVertices = isConnectVertices;
	}

	public boolean isRemove() {
		return isRemove;
	}

	public void setRemove(boolean isRemove) {
		this.isRemove = isRemove;
	}

	public boolean isUndirected() {
		return isUndirected;
	}

	public void setUndirected(boolean isUndirected) {
		this.isUndirected = isUndirected;
	}
	
}
