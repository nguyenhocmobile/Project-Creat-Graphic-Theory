package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Main.Edge;
import Main.GraphicTheoryManager;
import Main.Point;
import Main.Vertices;

public class MainArea extends JPanel implements ActionListener{
	private GraphicTheoryManager graph;
	private ArrayList<Point> listPoint;
	private ArrayList<Integer> listVertices;
	private int widthCircle;
	private Toolbar toolbar;
	private ArrayList<Edge> listEdge;
	private int vertices1 = -1;
	private int vertices2 = -1;
	private int edgeArrow = 20;
	private int edgeBottom = 15;

	public MainArea(GraphicTheoryManager graph, Toolbar toolbar) {
		this.graph = graph;
		listPoint = new ArrayList<Point>();
		listVertices = graph.getListVertices();
		widthCircle = 40;
		this.toolbar = toolbar;
		listEdge = graph.getListEdge();
		addAction();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawn(g);
	}

	private void drawn(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		drawVertices(g2d);
		drawEdge(g2d);
		g2d.dispose();
		updateUI();
	}
	private void drawEdge(Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(3));
		for(int i=0; i< listEdge.size(); i++) {
			if(toolbar.isUndirected() == true) {
				int x = 0;
				int y =0;
				Point pointStart = listPoint.get(listEdge.get(i).getStartPoint().getVertices());
				Point pointEnd = listPoint.get(listEdge.get(i).getEndPoint().getVertices());
				int subtractX1ForX2 = Math.abs(pointStart.getxPosition() - pointEnd.getxPosition());
				int subtractY1ForY2 = Math.abs(pointStart.getyPosition() - pointEnd.getyPosition());
				double distance = Math.sqrt(Math.pow(subtractX1ForX2, 2) + Math.pow(subtractY1ForY2, 2));
				double rateDistance = (double)((widthCircle/2)/distance);
				x = Math.round((float)(subtractX1ForX2*rateDistance*1000)/1000);
				y = Math.round((float)(subtractY1ForY2*rateDistance*1000)/1000);
				if(pointStart.getxPosition() > pointEnd.getxPosition() && pointStart.getyPosition()> pointEnd.getyPosition()) {
					g2d.drawLine(pointStart.getxPosition()-x, pointStart.getyPosition()-y, pointEnd.getxPosition()+x, pointEnd.getyPosition()+y);
				}
				if(pointStart.getxPosition()< pointEnd.getxPosition() && pointStart.getyPosition() > pointEnd.getyPosition() ) {
					g2d.drawLine(pointStart.getxPosition()+x, pointStart.getyPosition()-y, pointEnd.getxPosition()-x, pointEnd.getyPosition()+y);
				}
				if(pointStart.getxPosition()> pointEnd.getxPosition() && pointStart.getyPosition() < pointEnd.getyPosition() ) {
					g2d.drawLine(pointStart.getxPosition()-x, pointStart.getyPosition()+y, pointEnd.getxPosition()+x, pointEnd.getyPosition()-y);
				}
				if(pointStart.getxPosition()< pointEnd.getxPosition() && pointStart.getyPosition() < pointEnd.getyPosition()) {
					g2d.drawLine(pointStart.getxPosition()+x, pointStart.getyPosition()+y, pointEnd.getxPosition()-x, pointEnd.getyPosition()-y);
				}
			}else {
				int x = 0;
				int y =0;
				Point pointStart = listPoint.get(listEdge.get(i).getStartPoint().getVertices());
				Point pointEnd = listPoint.get(listEdge.get(i).getEndPoint().getVertices());
				int subtractX1ForX2 = Math.abs(pointStart.getxPosition() - pointEnd.getxPosition());
				int subtractY1ForY2 = Math.abs(pointStart.getyPosition() - pointEnd.getyPosition());
				double distance = Math.sqrt(Math.pow(subtractX1ForX2, 2) + Math.pow(subtractY1ForY2, 2));
				double rateDistance = (double)((widthCircle/2)/distance);
				x = Math.round((float)(subtractX1ForX2*rateDistance*1000)/1000);
				y = Math.round((float)(subtractY1ForY2*rateDistance*1000)/1000);
				double cornerRotate = Math.asin((double)(subtractX1ForX2/ distance));
				Polygon polygon = new Polygon();
				int yDistance = Math.round((float)(Math.sqrt(Math.pow(edgeArrow, 2)-Math.pow(edgeBottom/2, 2))));
				if(pointStart.getxPosition() > pointEnd.getxPosition() && pointStart.getyPosition()> pointEnd.getyPosition()) {
					int getStartXPosition = pointStart.getxPosition()-x;
					int getStartYPosition =pointStart.getyPosition()-y;
					int getEndXPosition =pointEnd.getxPosition()+x;
					int getEndYPosition =pointEnd.getyPosition()+y;
					g2d.drawLine(getStartXPosition, getStartYPosition, getEndXPosition, getEndYPosition);
					polygon.addPoint(getEndXPosition, getEndYPosition);
					polygon.addPoint(getEndXPosition - (edgeBottom/2),getEndYPosition+ yDistance);
					polygon.addPoint(getEndXPosition + (edgeBottom/2), getEndYPosition+yDistance);
					g2d.rotate(-1*cornerRotate, getEndXPosition, getEndYPosition);
					g2d.setStroke(new BasicStroke(5));
					g2d.fillPolygon(polygon);
					g2d.rotate(1*cornerRotate, getEndXPosition, getEndYPosition);
					g2d.setStroke(new BasicStroke(3));
				}
				if(pointStart.getxPosition()< pointEnd.getxPosition() && pointStart.getyPosition() > pointEnd.getyPosition() ) {
					g2d.drawLine(pointStart.getxPosition()+x, pointStart.getyPosition()-y, pointEnd.getxPosition()-x, pointEnd.getyPosition()+y);
				}
				if(pointStart.getxPosition()> pointEnd.getxPosition() && pointStart.getyPosition() < pointEnd.getyPosition() ) {
					g2d.drawLine(pointStart.getxPosition()-x, pointStart.getyPosition()+y, pointEnd.getxPosition()+x, pointEnd.getyPosition()-y);
				}
				if(pointStart.getxPosition()< pointEnd.getxPosition() && pointStart.getyPosition() < pointEnd.getyPosition()) {
					g2d.drawLine(pointStart.getxPosition()+x, pointStart.getyPosition()+y, pointEnd.getxPosition()-x, pointEnd.getyPosition()-y);
				}
			}

		}
	}
	private void drawVertices(Graphics2D g2d) {
		for(int i=0; i< listPoint.size();i++) {
			int xPosition = listPoint.get(i).getxPosition();
			int yPosition = listPoint.get(i).getyPosition();
			if(listPoint.get(i).isChoose()) {
				g2d.setColor(Color.green);
				g2d.fillOval(xPosition-(widthCircle/2), yPosition - (widthCircle/2), widthCircle, widthCircle);
			}else {
				g2d.setColor(new Color(0, 122, 255));
				g2d.fillOval(xPosition-(widthCircle/2), yPosition - (widthCircle/2), widthCircle, widthCircle);
			}
			g2d.setColor(Color.white);
			g2d.setFont(new Font("Arial", Font.BOLD, 16));
			String vertives = listVertices.get(i).toString();
			FontMetrics font = getFontMetrics(g2d.getFont());
			g2d.drawString(vertives,(float) (xPosition- (float)(font.stringWidth(vertives)/2)),(float) (yPosition + (g2d.getFont().getSize()/2) - font.getDescent()/2));
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	private void addAction() {
		this.addMouseListener(new AddVertices());
	}
	
	private class AddVertices implements MouseListener {
		
		private Point pointSelected1;
		private Point pointSelected2;
		
		public boolean checkDependent(Point pointClick, Point pointInList) {
			double subtractX1ForX2 = Math.abs(pointClick.getxPosition() - pointInList.getxPosition());
			double subtractY1ForY2 = Math.abs(pointClick.getyPosition() - pointInList.getyPosition());
			double distance = Math.sqrt(Math.pow(subtractX1ForX2, 2) + Math.pow(subtractY1ForY2, 2));
			if(distance <= 20) {
				return true;
			}else {
				return false;
			}
		}
		public void behaviorAddVertices(MouseEvent e) {
			new Vertices(graph);
			Point pointVertices = new Point(e.getX(), e.getY());
			listPoint.add(pointVertices);
		}
		public void behaviorConnectVertices(MouseEvent e) {
			Point pointClick = new Point(e.getX(), e.getY());
			for(int i=0; i< listPoint.size();i++) {
				Point getPoint = listPoint.get(i);
				if(checkDependent(pointClick, getPoint)) {
					if(pointSelected1 == null) {
						pointSelected1 = getPoint;
						getPoint.setChoose(true);
						vertices1 = i;
						break;
					}else if(pointSelected1 != null && pointSelected2 == null) {
						pointSelected2 = getPoint;
						getPoint.setChoose(true);
						vertices2 = i;
						break;
					}

				}
			}
			if(pointSelected1 != null && pointSelected2 != null) {
				if(vertices1 != -1 && vertices2 != -1) {
					new Edge(new Vertices(listVertices.get(vertices1), graph), new Vertices(listVertices.get(vertices2), graph), 0, toolbar.isUndirected(), graph);
					listPoint.get(vertices1).setChoose(false);
					listPoint.get(vertices2).setChoose(false);
					vertices1 = -1;
					vertices2 = -1;
					pointSelected1 = null;
					pointSelected2 = null;
				}
				return;
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(toolbar.isAddVertices() == true) {
				behaviorAddVertices(e);
			}
			if(toolbar.isConnectVertices() == true) {
				behaviorConnectVertices(e);
			}
			if(toolbar.isRemove() == true) {
				Point pointClicked = new Point(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}

}
