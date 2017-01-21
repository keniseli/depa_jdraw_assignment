package jdraw.test;

import jdraw.figures.Line;
import jdraw.framework.Figure;

public class LineTest extends AbstractFigureTest {

	protected Figure createFigure() {
		return new Line(5, 10);
	}
}
