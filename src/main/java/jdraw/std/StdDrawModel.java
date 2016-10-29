/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jdraw.framework.DrawCommandHandler;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawModelEvent;
import jdraw.framework.DrawModelListener;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

/**
 * Provide a standard behavior for the drawing model. This class initially does
 * not implement the methods in a proper way. It is part of the course
 * assignments to do so.
 * 
 * @author ken iseli
 *
 */
public class StdDrawModel implements DrawModel, FigureListener {
	private List<Figure> figures;
	private List<DrawModelListener> listeners;
	/**
	 * The draw command handler. Initialized here with a dummy implementation.
	 */
	// TODO initialize with your implementation of the undo/redo-assignment.
	private DrawCommandHandler handler = new EmptyDrawCommandHandler();

	public StdDrawModel() {
		figures = new ArrayList<>();
		listeners = new CopyOnWriteArrayList<>();
	}

	@Override
	public void addFigure(Figure f) {
		if (!figures.contains(f) && figures.add(f)) {
			f.addFigureListener(this);
			notifyDrawModelListeners(f, DrawModelEvent.Type.FIGURE_ADDED);
		}
	}

	@Override
	public Iterable<Figure> getFigures() {
		return figures;
	}

	@Override
	public void removeFigure(Figure f) {
		if (figures.remove(f)) {
			f.removeFigureListener(this);
			notifyDrawModelListeners(f, DrawModelEvent.Type.FIGURE_REMOVED);
		}
	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Retrieve the draw command handler in use.
	 * 
	 * @return the draw command handler.
	 */
	@Override
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}

	@Override
	public void setFigureIndex(Figure f, int index) {
		if (!figures.contains(f)) {
			throw new IllegalArgumentException();
		} else if (index >= figures.size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		figures.remove(f);
		figures.add(index, f);
		notifyDrawModelListeners(f, DrawModelEvent.Type.DRAWING_CHANGED);
	}

	@Override
	public void removeAllFigures() {
		for (Figure figure : figures) {
			figure.removeFigureListener(this);
		}
		notifyDrawModelListeners(null, DrawModelEvent.Type.DRAWING_CLEARED);
	}

	private void notifyDrawModelListeners(Figure figure, DrawModelEvent.Type eventType) {
		DrawModelEvent event = new DrawModelEvent(this, figure, eventType);
		listeners.forEach(listener -> listener.modelChanged(event));
	}

	@Override
	public void figureChanged(FigureEvent e) {
		DrawModelEvent event = new DrawModelEvent(this, e.getFigure(), null);
		listeners.forEach(listener -> listener.modelChanged(event));
	}

}
