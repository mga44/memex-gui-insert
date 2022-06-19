package mga44.gui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseDragAdapter extends MouseAdapter {
	private Component win;

	private static int posX, posY;

	public MouseDragAdapter(Component win) {
		this.win = win;
	}

	public void mousePressed(MouseEvent e) {
		posX = e.getX();
		posY = e.getY();
	}

	public void mouseDragged(MouseEvent evt) {
		win.setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);
	}
}
