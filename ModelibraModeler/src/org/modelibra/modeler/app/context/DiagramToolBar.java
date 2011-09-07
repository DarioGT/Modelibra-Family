package org.modelibra.modeler.app.context;

import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

import org.modelibra.modeler.app.pref.Para;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2001-05-07
 */
public class DiagramToolBar extends JToolBar {
	
	static final long serialVersionUID = 7168319479760000190L;

	public static final int SELECT = 1;

	public static final int BOX = 2;

	public static final int LINE = 3;

	private static final String SELECT_IMAGE = "img/selectButton.gif";

	private static final String BOX_IMAGE = "img/boxButton.gif";

	private static final String LINE_IMAGE = "img/lineButton.gif";

	private static final Border SELECTED_BORDER = new SoftBevelBorder(
			BevelBorder.LOWERED);

	private JButton selectTool = new JButton(Para.getPara().getImageIconSiblingToClassFile(
			SELECT_IMAGE));

	private JButton boxTool = new JButton(Para.getPara()
			.getImageIconSiblingToClassFile(BOX_IMAGE));

	private JButton lineTool = new JButton(Para.getPara().getImageIconSiblingToClassFile(
			LINE_IMAGE));

	private int selectedTool = SELECT;

	private boolean selectDefault = true;

	private DiagramFrame diagramFrame;

	private DiagramToolBar() {
		super();
		selectTool.addMouseListener(new SelectMouseAdapter(this));
		boxTool.addMouseListener(new BoxMouseAdapter(this));
		lineTool.addMouseListener(new LineMouseAdapter(this));

		this.add(selectTool);
		this.add(boxTool);
		this.add(lineTool);

		selectTool.setToolTipText(Para.getPara().getText("selectTip"));
		boxTool.setToolTipText(Para.getPara().getText("boxTip"));
		lineTool.setToolTipText(Para.getPara().getText("lineTip"));

		selectTool.setBorderPainted(false);
		boxTool.setBorderPainted(false);
		lineTool.setBorderPainted(false);

		this.setSelectedTool(selectedTool);
	}

	public DiagramToolBar(DiagramFrame aFrame) {
		this();
		this.setDiagramFrame(aFrame);
	}

	public JButton getSelectTool() {
		return selectTool;
	}

	public JButton getBoxTool() {
		return boxTool;
	}

	public JButton getLineTool() {
		return lineTool;
	}

	public int getSelectedTool() {
		return selectedTool;
	}

	public void setSelectedTool(int aTool) {
		if (aTool == SELECT || aTool == BOX || aTool == LINE) {
			this.setToolButton(this.getToolButton(selectedTool), false);
			selectedTool = aTool;
			this.setToolButton(this.getToolButton(selectedTool), true);
		}
	}

	private void setToolButton(JButton aButton, boolean selected) {
		if (selected) {
			aButton.setSelected(true);
			aButton.setBorder(SELECTED_BORDER);
			aButton.setBorderPainted(true);
		} else {
			aButton.setSelected(false);
			aButton.setBorderPainted(false);
		}
	}

	private JButton getToolButton(int aTool) {
		JButton toolButton = selectTool;
		switch (aTool) {
		case SELECT:
			toolButton = selectTool;
			break;
		case BOX:
			toolButton = boxTool;
			break;
		case LINE:
			toolButton = lineTool;
			break;
		}
		return toolButton;
	}

	public boolean isSelectDefault() {
		return selectDefault;
	}

	public void setSelectDefault(boolean aValue) {
		selectDefault = aValue;
	}

	public DiagramFrame getDiagramFrame() {
		return diagramFrame;
	}

	public void setDiagramFrame(DiagramFrame aFrame) {
		diagramFrame = aFrame;
	}

	void selectMousePressed(MouseEvent e) {
		this.setSelectedTool(SELECT);
	}

	void selectMouseReleased(MouseEvent e) {
		if (e.getClickCount() == 2) {
			setSelectDefault(true);
		}
	}

	void boxMousePressed(MouseEvent e) {
		this.setSelectedTool(BOX);
	}

	void boxMouseReleased(MouseEvent e) {
		if (e.getClickCount() == 2) {
			setSelectDefault(false);
		}
	}

	void lineMousePressed(MouseEvent e) {
		this.setSelectedTool(LINE);
	}

	void lineMouseReleased(MouseEvent e) {
		if (e.getClickCount() == 2) {
			setSelectDefault(false);
		}
	}

}

class SelectMouseAdapter extends java.awt.event.MouseAdapter {
	DiagramToolBar adaptee;

	SelectMouseAdapter(DiagramToolBar adaptee) {
		this.adaptee = adaptee;
	}

	public void mousePressed(MouseEvent e) {
		adaptee.selectMousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		adaptee.selectMouseReleased(e);
	}

}

class BoxMouseAdapter extends java.awt.event.MouseAdapter {
	DiagramToolBar adaptee;

	BoxMouseAdapter(DiagramToolBar adaptee) {
		this.adaptee = adaptee;
	}

	public void mousePressed(MouseEvent e) {
		adaptee.boxMousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		adaptee.boxMouseReleased(e);
	}

}

class LineMouseAdapter extends java.awt.event.MouseAdapter {
	DiagramToolBar adaptee;

	LineMouseAdapter(DiagramToolBar adaptee) {
		this.adaptee = adaptee;
	}

	public void mousePressed(MouseEvent e) {
		adaptee.lineMousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		adaptee.lineMouseReleased(e);
	}

}
