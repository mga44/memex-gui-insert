package mga44.gui.action;

import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Paths;

import mga44.EnvironmentManager;
import mga44.io.ndtl.MemexEntity;
import mga44.memex.MemexUtils;

public class SaveEntityAction {

	private MemexEntity entity;

	public SaveEntityAction(MemexEntity entity) {
		this.entity = entity;
	}

	public void run() {
		try {
			new MemexUtils().save(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// debug
		try {
			Desktop.getDesktop().open(
					Paths.get(EnvironmentManager.getInstance().getMemexDirectory().toString(), "index.html").toFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
