package jdraw.figures.rect;

import java.awt.Rectangle;

import jdraw.figures.AbstractHandle;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

public abstract class RectHandle extends AbstractHandle implements FigureHandle {
	protected static final int HANDLE_SIZE = 10;
	protected Rect rect;

	public RectHandle(Rect rect) {
		this.rect = rect;
	}
	
	@Override
	public Figure getOwner() {
		return rect;
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
		rect.resize(fromX, fromY, toAnchorX, toY);
	}
}
