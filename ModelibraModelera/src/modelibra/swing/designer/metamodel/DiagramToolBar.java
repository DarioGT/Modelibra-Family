package modelibra.swing.designer.metamodel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

import modelibra.swing.app.config.Para;

public class DiagramToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;

	public static final int SELECT = 1;
	public static final int ENTITY = 2;
	public static final int RSHIP = 3;

	private static final String SELECT_IMAGE = "image/select.png";
	private static final String ENTITY_IMAGE = "image/entity.png";
	private static final String RSHIP_IMAGE = "image/relationship.png";

	private static final Border SELECTED_BORDER = new SoftBevelBorder(
			BevelBorder.LOWERED);

	private JButton selectTool = new JButton(Para.getOne().getImageIcon(
			SELECT_IMAGE));
	private JButton entityTool = new JButton(Para.getOne().getImageIcon(
			ENTITY_IMAGE));
	private JButton rshipTool = new JButton(Para.getOne().getImageIcon(
			RSHIP_IMAGE));

	private int selectedTool = SELECT;
	private boolean selectDefault = true;

	private DiagramFrame diagramFrame;

	private DiagramToolBar() {
		selectTool.addMouseListener(new SelectMouseAdapter(this));
		entityTool.addMouseListener(new EntityMouseAdapter(this));
		rshipTool.addMouseListener(new RshipMouseAdapter(this));

		add(selectTool);
		add(entityTool);
		add(rshipTool);

		selectTool.setToolTipText(Para.getOne().getText("selectTip"));
		entityTool.setToolTipText(Para.getOne().getText("entityTip"));
		rshipTool.setToolTipText(Para.getOne().getText("rshipTip"));

		selectTool.setBorderPainted(false);
		entityTool.setBorderPainted(false);
		rshipTool.setBorderPainted(false);

		setSelectedTool(selectedTool);
	}

	public DiagramToolBar(DiagramFrame diagramFrame) {
		this();
		setDiagramFrame(diagramFrame);
	}

	public JButton getSelectTool() {
		return selectTool;
	}

	public JButton getEntityTool() {
		return entityTool;
	}

	public JButton getRshipTool() {
		return rshipTool;
	}

	public int getSelectedTool() {
		return selectedTool;
	}

	public void setSelectedTool(int tool) {
		if (tool == SELECT || tool == ENTITY || tool == RSHIP) {
			setToolButton(getToolButton(selectedTool), false);
			selectedTool = tool;
			setToolButton(getToolButton(selectedTool), true);
		}
	}

	private void setToolButton(JButton button, boolean selected) {
		if (selected) {
			button.setSelected(true);
			button.setBorder(SELECTED_BORDER);
			button.setBorderPainted(true);
		} else {
			button.setSelected(false);
			button.setBorderPainted(false);
		}
	}

	private JButton getToolButton(int tool) {
		JButton toolButton = selectTool;
		switch (tool) {
		case SELECT:
			toolButton = selectTool;
			break;
		case ENTITY:
			toolButton = entityTool;
			break;
		case RSHIP:
			toolButton = rshipTool;
			break;
		}
		return toolButton;
	}

	public boolean isSelectDefault() {
		return selectDefault;
	}

	public void setSelectDefault(boolean selectDefault) {
		this.selectDefault = selectDefault;
	}

	public DiagramFrame getDiagramFrame() {
		return diagramFrame;
	}

	public void setDiagramFrame(DiagramFrame diagramFrame) {
		this.diagramFrame = diagramFrame;
	}

	void selectMousePressed(MouseEvent e) {
		setSelectedTool(SELECT);
	}

	void selectMouseReleased(MouseEvent e) {
		if (e.getClickCount() == 2) {
			setSelectDefault(true);
		}
	}

	void entityMousePressed(MouseEvent e) {
		setSelectedTool(ENTITY);
	}

	void entityMouseReleased(MouseEvent e) {
		if (e.getClickCount() == 2) {
			setSelectDefault(false);
		}
	}

	void rshipMousePressed(MouseEvent e) {
		setSelectedTool(RSHIP);
	}

	void rshipMouseReleased(MouseEvent e) {
		if (e.getClickCount() == 2) {
			setSelectDefault(false);
		}
	}

}

class SelectMouseAdapter extends MouseAdapter {
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

class EntityMouseAdapter extends MouseAdapter {
	DiagramToolBar adaptee;

	EntityMouseAdapter(DiagramToolBar adaptee) {
		this.adaptee = adaptee;
	}

	public void mousePressed(MouseEvent e) {
		adaptee.entityMousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		adaptee.entityMouseReleased(e);
	}

}

class RshipMouseAdapter extends MouseAdapter {
	DiagramToolBar adaptee;

	RshipMouseAdapter(DiagramToolBar adaptee) {
		this.adaptee = adaptee;
	}

	public void mousePressed(MouseEvent e) {
		adaptee.rshipMousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		adaptee.rshipMouseReleased(e);
	}

}
