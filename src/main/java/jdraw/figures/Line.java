package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public class Line implements Figure {
	private Line2D line;
	private List<FigureListener> listeners;

	public Line(int x, int y, int w, int h) {
		line = new Line2D.Double(x, y, w, h);
		listeners = new CopyOnWriteArrayList<>();
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		Point2D p1 = line.getP1();
		Point2D p2 = line.getP2();
		g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
		notifyListeners();
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		line.setLine(origin, corner);
		notifyListeners();
	}

	@Override
	public void move(int dx, int dy) {
		if (dx != 0 && dy != 0) {
			Point2D p1 = line.getP1();
			p1.setLocation(p1.getX() + dx, p1.getY() + dy);
			Point2D p2 = line.getP2();
			p2.setLocation(p2.getX() + dx, p2.getY() + dy);
			line.setLine(p1, p2);
			notifyListeners();
		}
	}

	@Override
	public boolean contains(int x, int y) {
		return line.contains(x, y);
	}

	@Override
	public Rectangle getBounds() {
		return line.getBounds();
	}

	@Override
	public List<FigureHandle> getHandles() {
		return null;
	}

	@Override
	public void addFigureListener(FigureListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		listeners.remove(listener);
	}

	@Override
	public Figure clone() {
		return null;
	}

	private void notifyListeners() {
		listeners.forEach(listener -> {
			FigureEvent event = new FigureEvent(this);
			listener.figureChanged(event);
		});
	}
}
