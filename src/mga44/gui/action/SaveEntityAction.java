package mga44.gui.action;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import logic.ndtl.ContentType;
import mga44.EnvironmentManager;
import mga44.io.ndtl.MemexEntity;
import mga44.io.ndtl.MemexEntity.MemexEntityBuilder;

public class SaveEntityAction {
	public void run() {
		ContentType type = null;
		String title = "";
		Path attachment = Path.of("");
		List<String> tags = Collections.emptyList();
		String note = "";
		String quote = "";

		MemexEntityBuilder builder = MemexEntity.builder().withType(type).withTitle(title);
		if (attachment != null) {
			Path destination = Path.of(EnvironmentManager.memexDirectory.toString(), "content", "media",
					attachment.getName(attachment.getNameCount() - 1).toString());
			try {
				Files.copy(attachment, destination);
			} catch (IOException e) {
				e.printStackTrace();
			} // TODO test same file names
			builder.withAttachment(destination);
		}

		if (note != null)
			builder.withNote(note);

		if (quote != null)
			builder.withQuote(quote);

		if (!tags.isEmpty())
			builder.withTags(tags);

		MemexEntity entity = builder.build();
		try {
			entity.saveTo(EnvironmentManager.databaseFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
