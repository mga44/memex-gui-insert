package mga44.gui.action;

import java.awt.Desktop;
import java.io.IOException;

import mga44.io.ndtl.MemexEntity;
import mga44.memex.MemexRepository;
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
			Desktop.getDesktop().open(MemexRepository.getInstance().getHtmlFile().toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
