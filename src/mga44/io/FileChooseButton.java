package mga44.io;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class FileChooseButton extends MouseAdapter {
	private JTextField correspondingField;

	public FileChooseButton(JTextField correspondingField) {
		super();
		this.correspondingField = correspondingField;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JFileChooser jfc = new JFileChooser();
		int result = jfc.showSaveDialog(correspondingField.getParent());
		if (result != JFileChooser.APPROVE_OPTION)
			return;

		Path selected = jfc.getSelectedFile().toPath();
		correspondingField.setText(selected.toString());
	}
}
