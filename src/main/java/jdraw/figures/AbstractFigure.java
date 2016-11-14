package jdraw.figures;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

public abstract class AbstractFigure implements Figure {
	private List<FigureListener> listeners;

	public AbstractFigure() {
		listeners = new CopyOnWriteArrayList<>();
		}
	
	@Override
	public abstract Figure clone();

	@Override
	public void addFigureListener(FigureListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		listeners.remove(listener);
	}

	protected void notifyListeners() {
		listeners.forEach(listener -> {
			FigureEvent event = new FigureEvent(this);
			listener.figureChanged(event);
		});
	}

	public List<Figure> getFigureParts() {
		LinkedList<Figure> linkedList = new LinkedList<>();
		linkedList.add(this);
		return linkedList;
	}

}
