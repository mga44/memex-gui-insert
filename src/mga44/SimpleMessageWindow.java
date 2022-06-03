package mga44;

import javax.swing.JOptionPane;

public class SimpleMessageWindow {

	public void showError(String message) {
		JOptionPane.showInternalMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
