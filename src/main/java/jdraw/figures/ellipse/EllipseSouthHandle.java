package jdraw.figures.ellipse;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;

public class EllipseSouthHandle extends EllipseHandle {

	public EllipseSouthHandle(Ellipse ellipse) {
		super(ellipse);
	}

	@Override
	public Cursor getCursor() {
		return new Cursor(Cursor.N_RESIZE_CURSOR);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		resize(0, y);
	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		resize(0, y);
	}

	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		resize(0, y);
	}

	@Override
	protected int getAnchorY(Rectangle bounds) {
		return (int) bounds.getMinY();
	}

	@Override
	protected int getAnchorX(Rectangle bounds) {
		return (int) (bounds.getMaxX() - (bounds.getWidth() / 2));
	}

	@Override
	protected int getOriginX(Rectangle bounds) {
		return (int) (bounds.getMaxX() - (bounds.getWidth() / 2));
	}

	@Override
	protected int getOriginY(Rectangle bounds) {
		return (int) bounds.getMaxY();
	}
}
