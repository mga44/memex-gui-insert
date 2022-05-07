package mga44.gui;

import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MemexInsertionWindow extends JFrame {
	private static final long serialVersionUID = -3887194207226678404L;

	public MemexInsertionWindow() throws HeadlessException {
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		buildGui();
	}

	/**
	 * Layout order: 0. title 1. type 2. file 3. tags 4. note 5. quote
	 */
	private void buildGui() {

		JButton button = new JButton();
		button.setText("Save");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO add save action
				super.mouseClicked(e);
			}
		});

		this.add(button);
	}

	public void run() {
		this.setVisible(true);
	}
}
