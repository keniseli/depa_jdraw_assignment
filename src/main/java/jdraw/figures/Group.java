package jdraw.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public class Group extends AbstractFigure {

	private List<Figure> figureParts;
	private Rectangle bounds;
	private List<FigureListener> listeners;

	public Group(List<Figure> selection) {
		super();
		figureParts = selection;
		listeners = new CopyOnWriteArrayList<>();
		bounds = new Rectangle();
		for (Figure figure : figureParts) {
			bounds.add(figure.getBounds());
		}
	}

	@Override
	public List<Figure> getFigureParts() {
		return figureParts;
	}

	@Override
	public void draw(Graphics g) {
		for (Figure figure : figureParts) {
			figure.draw(g);
		}
	}

	@Override
	public void move(int dx, int dy) {
		for (Figure figure : figureParts) {
			figure.move(dx, dy);
		}
	}

	@Override
	public boolean contains(int x, int y) {
		boolean contains = false;
		for (int i = 0; i < figureParts.size() && !contains; i++) {
			contains = figureParts.get(i).contains(x, y);
		}
		return contains;
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		bounds.setFrameFromDiagonal(origin, corner);
		notifyListeners();
	}

	@Override
	public Rectangle getBounds() {
		return bounds;
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
		// TODO Auto-generated method stub
		return null;
	}

}
