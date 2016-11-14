package jdraw.figures;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public class GroupTool implements DrawTool {
	private static final String IMAGES = "/images/";

	private DrawView view;
	private DrawModel model;

	public GroupTool(DrawContext context) {
		view = context.getView();
		model = context.getModel();
	}

	@Override
	public void activate() {
		System.out.println("group");
		List<Figure> selection = view.getSelection();
		Figure group = new Group(selection);
		for (Figure figure : selection) {
			model.removeFigure(figure);
		}
		model.addFigure(group);
	}

	@Override
	public void deactivate() {
	}

	@Override
	public void mouseDown(int x, int y, MouseEvent e) {
		deactivate();
	}

	@Override
	public void mouseDrag(int x, int y, MouseEvent e) {
		deactivate();
	}

	@Override
	public void mouseUp(int x, int y, MouseEvent e) {
		deactivate();
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	}

	@Override
	public Icon getIcon() {
		return new ImageIcon(getClass().getResource(IMAGES + "group.png"));
	}

	@Override
	public String getName() {
		return "Group";
	}

}
