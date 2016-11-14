package jdraw.figures.ellipse;

import java.awt.Cursor;
import java.awt.Rectangle;

import jdraw.figures.AbstractHandle;
import jdraw.framework.Figure;

public abstract class EllipseHandle extends AbstractHandle {
	public Ellipse ellipse;

	public EllipseHandle(Ellipse ellipse) {
		this.ellipse = ellipse;
	}

	@Override
	public Figure getOwner() {
		return ellipse;
	}

	protected void resize(int x, int y) {
		Rectangle bounds = getOwner().getBounds();
		int fromY = y;
		int toAnchorX = getAnchorX(bounds);
		int toY = getAnchorY(bounds);
		int fromX = x;
		if (x == 0) {
			fromX = (int) bounds.getMinX();
			toAnchorX = (int) bounds.getMaxX();
		} else if (fromX > toAnchorX) {
			fromX = toAnchorX;
			toAnchorX = x;
		}
		if (y == 0) {
			fromY = (int) bounds.getMinY();
			toY = (int) bounds.getMaxY();
		} else if (fromY > toY) {
			fromY = toY;
			toY = y;
		}
		ellipse.resize(fromX, fromY, toAnchorX, toY);
	}

}
