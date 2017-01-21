package jdraw.test;

import jdraw.figures.rect.Rect;
import jdraw.framework.Figure;

public class RectangleTest extends AbstractFigureTest {

	@Override
	protected Figure createFigure() {
		return new Rect(20, 20, 5, 5);
	}

}
