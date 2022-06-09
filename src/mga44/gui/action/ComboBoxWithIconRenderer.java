package mga44.gui.action;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.UIManager;

import logic.ndtl.ContentType;

@SuppressWarnings("serial")
public class ComboBoxWithIconRenderer extends DefaultListCellRenderer {
	private Color background = new Color(0, 100, 255, 15);
	private Color defaultBackground = (Color) UIManager.get("List.background");

	public ComboBoxWithIconRenderer() {
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		ContentType type = (ContentType) value;
		this.setText(type.toString());
		if (!isSelected)
			this.setBackground(index % 2 == 0 ? background : defaultBackground);

		return this;
	}

	@Override
	public void setOpaque(boolean isOpaque) {
		super.setOpaque(true);
	}
}
