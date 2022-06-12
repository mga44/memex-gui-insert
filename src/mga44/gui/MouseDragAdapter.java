package mga44.gui;

import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseDragAdapter extends MouseAdapter {
	private Window win;

	private int posX, posY;

	public MouseDragAdapter(Window win) {
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
