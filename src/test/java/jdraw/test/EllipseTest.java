package jdraw.test;

import jdraw.figures.ellipse.Ellipse;
import jdraw.framework.Figure;

public class EllipseTest extends AbstractFigureTest {

	@Override
	protected Figure createFigure() {
		return new Ellipse(20, 20, 5, 5);
	}

}
