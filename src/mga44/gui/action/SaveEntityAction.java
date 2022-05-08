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

	private String title;
	private ContentType type;
	private String note;
	private String quote;
	private Path attachment;
	private List<String> tags;

	public SaveEntityAction(String title, ContentType type, String note, String quote) {
		this.title = title;
		this.type = type;
		attachment = null;
		tags = Collections.emptyList();
		this.note = note;
		this.quote = quote;
	}

	public void run() {
		MemexEntityBuilder builder = MemexEntity.builder().withType(type).withTitle(title);
		EnvironmentManager manager = EnvironmentManager.getInstance();
		if (attachment != null) {
			String attachedFileName = attachment.getName(attachment.getNameCount() - 1).toString();
			Path destination = Path.of(manager.getMemexDirectory().toString(), "content", "media", attachedFileName);
			try {
				Files.copy(attachment, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
			entity.saveTo(manager.getDatabaseFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
