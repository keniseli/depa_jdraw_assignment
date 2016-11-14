package jdraw.figures.rect;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;

public class RectWestRectHandle extends RectHandle {

	public RectWestRectHandle(Rect rect) {
		super(rect);
	}

	@Override
	public Cursor getCursor() {
		return new Cursor(Cursor.W_RESIZE_CURSOR);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		resize(x, 0);
	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		resize(x, 0);
	}

	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		resize(x, 0);
	}

	@Override
	protected int getAnchorY(Rectangle bounds) {
		return (int) (bounds.getMaxY() - (bounds.getHeight() / 2));
	}

	@Override
	protected int getAnchorX(Rectangle bounds) {
		return (int) bounds.getMaxX();
	}

	@Override
	protected int getOriginX(Rectangle bounds) {
		return (int) bounds.getMinX();
	}

	@Override
	protected int getOriginY(Rectangle bounds) {
		return (int) (bounds.getMaxY() - (bounds.getHeight() / 2));
	}

}
