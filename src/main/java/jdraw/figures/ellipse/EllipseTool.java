/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures.ellipse;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler
 */
public class EllipseTool implements DrawTool {
  
	private static final String IMAGES = "/images/";

	private DrawContext context;

	private DrawView view;

	private Ellipse newEllipse = null;

	private Point anchor = null;

	public EllipseTool(DrawContext context) {
		this.context = context;
		this.view = context.getView();
	}

	@Override
	public void deactivate() {
		this.context.showStatusText("");
	}

	@Override
	public void activate() {
		this.context.showStatusText("Ellipse Mode");
	}

	@Override
	public void mouseDown(int x, int y, MouseEvent e) {
		if (newEllipse != null) {
			throw new IllegalStateException();
		}
		anchor = new Point(x, y);
		newEllipse = new Ellipse(x, y, 0, 0);
		newEllipse.addFigureListener(view);
		view.getModel().addFigure(newEllipse);
	}

	@Override
	public void mouseDrag(int x, int y, MouseEvent e) {
		newEllipse.setBounds(anchor, new Point(x, y));
		java.awt.Rectangle r = newEllipse.getBounds();
		this.context.showStatusText("w: " + r.width + ", h: " + r.height);
	}

	@Override
	public void mouseUp(int x, int y, MouseEvent e) {
		newEllipse = null;
		anchor = null;
		this.context.showStatusText("Ellipse Mode");
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}
	
	@Override
	public Icon getIcon() {
		return new ImageIcon(getClass().getResource(IMAGES + "oval.png"));
	}

	@Override
	public String getName() {
		return "Ellipse";
	}

}
