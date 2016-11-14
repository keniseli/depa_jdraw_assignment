package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import jdraw.framework.FigureHandle;

public abstract class AbstractHandle implements FigureHandle {
	protected static final int HANDLE_SIZE = 10;

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		Point location = getLocation();
		g.drawRect(location.x, location.y, HANDLE_SIZE, HANDLE_SIZE);
	}

	@Override
	public Point getLocation() {
		Point point = new Point();
		Rectangle bounds = getOwner().getBounds();
		point.x = getOriginX(bounds) - HANDLE_SIZE / 2;
		point.y = getOriginY(bounds) - HANDLE_SIZE / 2;
		return point;
	}

	@Override
	public boolean contains(int x, int y) {
		Point location = getLocation();
		Rectangle handle = new Rectangle(location.x, location.y, HANDLE_SIZE, HANDLE_SIZE);
		return handle.contains(x, y);
	}

	protected abstract int getAnchorY(Rectangle bounds);

	protected abstract int getAnchorX(Rectangle bounds);

	protected abstract int getOriginX(Rectangle bounds);

	protected abstract int getOriginY(Rectangle bounds);

}
