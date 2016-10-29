package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public class Ellipse implements Figure {
	private Ellipse2D.Double ellipse;
	private List<FigureListener> listeners;

	public Ellipse(int x, int y, int w, int h) {
		ellipse = new Ellipse2D.Double(x, y, w, h);
		listeners = new CopyOnWriteArrayList<>();
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		int height = (int) ellipse.getHeight();
		int width = (int) ellipse.getWidth();
		int y = (int) ellipse.getY();
		int x = (int) ellipse.getX();
		g.fillOval(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawOval(x, y, width, height);
		notifyListeners();
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		ellipse.setFrameFromDiagonal(origin, corner);
		notifyListeners();
	}

	@Override
	public void move(int dx, int dy) {
		if (dx != 0 && dy != 0) {
			int x = (int) ellipse.getX() + dx;
			int y = (int) ellipse.getY() + dy;
			Rectangle2D bounds = ellipse.getBounds2D();
			bounds.setFrame(new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight()));
			ellipse.setFrame(bounds);
			notifyListeners();
		}
	}

	@Override
	public boolean contains(int x, int y) {
		return ellipse.contains(x, y);
	}

	@Override
	public Rectangle getBounds() {
		return ellipse.getBounds();
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
