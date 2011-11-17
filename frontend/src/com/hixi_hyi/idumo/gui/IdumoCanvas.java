package com.hixi_hyi.idumo.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import com.hixi_hyi.idumo.gui.data.IdumoContainer;
import com.hixi_hyi.idumo.gui.data.IdumoEdge;
import com.hixi_hyi.idumo.gui.data.IdumoItem;

public class IdumoCanvas extends JPanel implements MouseListener, MouseMotionListener {
	
	private static final long	serialVersionUID	= 1L;
	
	private IdumoWindow			win;
	private IdumoContainer		container;
	private IdumoItem			itemA				= null;
	private IdumoItem			itemB				= null;
	private int					selectOffsetX;
	private int					selectOffsetY;
	private final Color			NodeColor			= new Color(0xcc, 0xcc, 0xff);
	private final int			offsetX				= 5;
	private final int			offsetY				= 5;
	
	public IdumoCanvas(IdumoWindow win, IdumoContainer container) {
		this.win = win;
		this.container = container;
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void clear(Graphics g) {
		int h = win.frame.getHeight();
		int w = win.frame.getWidth();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);
	}
	
	class IdumoItemBoxFigure {
		int		x, y, w, h;
		String	name;
		String	klass;
		int		fontsize;
		
		public IdumoItemBoxFigure(IdumoItem item) {
			name = item.getName();
			klass = item.getName();
			fontsize = getFont().getSize();
			x = item.getX();
			y = item.getY();
			w = (item.getLabelLength() * fontsize) + (2 * offsetX);
			h = (fontsize * 2) + (2 * offsetY);
		}
	}
	
	public void paintIdumoItem(Graphics g, IdumoItem item) {
		IdumoItemBoxFigure fig = new IdumoItemBoxFigure(item);
		if (item == itemA) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.BLACK);
		}
		g.fillRect(fig.x - 2, fig.y - 2, fig.w + 4, fig.h + 4);
		g.setColor(NodeColor);
		g.fillRect(fig.x, fig.y, fig.w, fig.h);
		g.setColor(Color.BLACK);
		g.drawString(item.getKlass() + ":", fig.x + offsetX, fig.y + fig.fontsize + offsetY);
		g.drawString(item.getName(), fig.x + offsetX, fig.y + (2 * fig.fontsize));
		fig = null;
	}
	
	public void paintIdumoEdge(Graphics g, IdumoEdge edge) {
		IdumoItemBoxFigure src = new IdumoItemBoxFigure(edge.getSrcItem());
		IdumoItemBoxFigure dest = new IdumoItemBoxFigure(edge.getDestItem());
		g.setColor(Color.BLACK);
		g.drawLine(src.x + (src.w / 2), src.y + (src.h / 2), dest.x + (dest.w / 2), dest.y + (dest.h / 2));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		clear(g);
		for (IdumoEdge edge : container.getEdges()) {
			paintIdumoEdge(g, edge);
		}
		for (IdumoItem item : container.getItems()) {
			paintIdumoItem(g, item);
		}
	}
	
	public boolean contained(IdumoItem item, int x, int y) {
		int x0 = item.getX(), y0 = item.getY(), x1 = x0 + (item.getName().length() * getFont().getSize()) + (2 * offsetX), y1 = y0 + 40;
		return ((x >= x0) && (x1 >= x) && (y >= y0) && (y1 >= y));
	}
	
	public IdumoItem searchIdumoItem(int x, int y) {
		for (IdumoItem item : container.getItems()) {
			if (contained(item, x, y)) {
				return item;
			}
		}
		return null;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (itemA == null) {
			itemB = null;
			return;
		}
		if (itemB == null) {
			itemB = itemA;
		} else { // select another one to connect selected.
			Point p = e.getPoint();
			IdumoItem item = searchIdumoItem(p.x, p.y);
			if (item == null) {
				itemA = null;
				itemB = null;
			} else if (item != itemB) {
				container.addEdge(new IdumoEdge(itemB, item));
				win.repaint();
			}
			itemB = null;
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Point p = e.getPoint();
		itemA = searchIdumoItem(p.x, p.y);
		if (itemA != null) {
			selectOffsetX = p.x - itemA.getX();
			selectOffsetY = p.y - itemA.getY();
		}
		win.repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (itemA == null) {
			return;
		}
		Point p = arg0.getPoint();
		itemA.setPoint(p.x - selectOffsetX, p.y - selectOffsetY);
		win.repaint();
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {}
	
	public IdumoItem selectedItem() {
		return itemA;
	}
}
