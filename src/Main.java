import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.UIManager;

import mga44.gui.MemexInsertionWindow;

public class Main {

	private static Properties props;

	public static void main(String[] args) {
		initialize();
		MemexInsertionWindow window = new MemexInsertionWindow();
		window.run();
	}

	private static void initialize() {
		try {
			props = new Properties();
			props.load(new FileInputStream("./resoures/app.properties"));
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
