package jdraw.constrainer;

import java.awt.Point;

import jdraw.framework.PointConstrainer;

public class Grid20Constrainer implements PointConstrainer {
	private static final int STEP_SIZE = 20;

	@Override
	public Point constrainPoint(Point p) {
		int x = ((p.x / 2) / STEP_SIZE) * STEP_SIZE;
		int y = ((p.y / 2) / STEP_SIZE) * STEP_SIZE;
		return new Point(x, y);
	}

	@Override
	public int getStepX(boolean right) {
		return getStep(right);
	}

	@Override
	public int getStepY(boolean down) {
		return getStep(!down);
	}

	private int getStep(boolean highering) {
		return STEP_SIZE;
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseUp() {
		// TODO Auto-generated method stub

	}

}
