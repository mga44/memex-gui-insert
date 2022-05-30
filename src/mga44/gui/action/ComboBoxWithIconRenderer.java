package mga44.gui.action;

import java.awt.Color;
import java.awt.Component;
import java.util.EnumMap;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.UIManager;

import logic.ndtl.ContentType;

public class ComboBoxWithIconRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = -2806770278606849988L;

	private EnumMap<ContentType, ImageIcon> iconMap = new EnumMap<>(ContentType.class);
	private Color background = new Color(0, 100, 255, 15);
	private Color defaultBackground = (Color) UIManager.get("List.background");

	public ComboBoxWithIconRenderer() {
//		iconMap.put(ContentType.TOOL, new ImageIcon("./resources/icon/hammer.png"));// TODO
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {

		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		ContentType type = (ContentType) value;
		this.setText(type.toString());
		this.setIcon(iconMap.get(type));
		if (!isSelected)
			this.setBackground(index % 2 == 0 ? background : defaultBackground);

		return this;
	}
}
