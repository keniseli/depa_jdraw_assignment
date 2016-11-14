package jdraw.figures.ellipse;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jdraw.figures.AbstractFigure;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public class Ellipse extends AbstractFigure implements Figure {
	private Ellipse2D.Double ellipse;
	private List<FigureHandle> handles;

	public Ellipse(int x, int y, int w, int h) {
		super();
		ellipse = new Ellipse2D.Double(x, y, w, h);
		handles = new ArrayList<>();
		handles.add(new EllipseWestHandle(this));
		handles.add(new EllipseEastHandle(this));
		handles.add(new EllipseNorthHandle(this));
		handles.add(new EllipseSouthHandle(this));
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

	public void resize(int fromX, int fromY, int toX, int toY) {
		ellipse.setFrame(fromX, fromY, toX - fromX, toY - fromY);
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
		return handles;
	}

	@Override
	public Figure clone() {
		return null;
	}

}
