package mga44.gui.action;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.function.BiConsumer;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.UIManager;

import logic.ndtl.ContentType;

public class ComboBoxWithIconRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = -2806770278606849988L;

	private EnumMap<ContentType, Icon32> iconMap = new EnumMap<>(ContentType.class);
	private Color background = new Color(0, 100, 255, 15);
	private Color defaultBackground = (Color) UIManager.get("List.background");

	private Icon32 defaultIcon = new Icon32("./resources/icon/radiation.png");

	public ComboBoxWithIconRenderer() {
		// TODO add icons
		BiConsumer<ContentType, String> addIcon = (type, fileName) -> {
			Icon32 orginal = new Icon32("./resources/icon/" + fileName);
			iconMap.put(type, orginal);
		};
		addIcon.accept(ContentType.TOOL, "Equipment.png");
		addIcon.accept(ContentType.MUSIC, "Music.png");
		addIcon.accept(ContentType.NOTE, "New document.png");
		addIcon.accept(ContentType.TOOL, "Equipment.png");
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {

		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		ContentType type = (ContentType) value;
		this.setText(type.toString());
		this.setIcon(iconMap.getOrDefault(type, defaultIcon));
		if (!isSelected)
			this.setBackground(index % 2 == 0 ? background : defaultBackground);

		return this;
	}

	@SuppressWarnings("serial")
	public class Icon32 extends ImageIcon {
		public Icon32(String f) {
			super(f);

			BufferedImage i = new BufferedImage(14, 14, BufferedImage.TRANSLUCENT);

			Graphics2D g2d = (Graphics2D) i.getGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

			g2d.drawImage(getImage(), 0, 0, 14, 14, null);
			setImage(i);
		}

		public int getIconHeight() {
			return 14;
		}

		public int getIconWidth() {
			return 14;
		}

		public void paintIcon(Component c, Graphics g, int x, int y) {
			g.drawImage(getImage(), x, y, c);
		}
	}
}
