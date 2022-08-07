package mga44.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Optional;

import javax.swing.event.MouseInputAdapter;

public class MouseDragAdapter extends MouseInputAdapter {

	Point location;
	MouseEvent pressed;

	public void mousePressed(MouseEvent me) {
		pressed = me;
	}

	public void mouseDragged(MouseEvent me) {
		Component component = getMainComponent(me.getComponent());
		location = component.getLocation(location);
		int x = location.x - pressed.getX() + me.getX();
		int y = location.y - pressed.getY() + me.getY();
		component.setLocation(x, y);
	}

	private Component getMainComponent(Component component) {
		Optional<Container> possibleParent = Optional.ofNullable(component.getParent());
		if (possibleParent.isEmpty())
			return component;

		return getMainComponent(possibleParent.get());
	}
}
