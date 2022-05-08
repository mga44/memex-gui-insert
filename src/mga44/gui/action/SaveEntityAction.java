package mga44.gui.action;

import java.io.File;
import java.util.List;

import logic.ndtl.ContentType;
import mga44.io.ndtl.MemexEntity;
import mga44.io.ndtl.MemexEntity.MemexEntityBuilder;

public class SaveEntityAction {

	{
		ContentType type = null;
		String title = null;
		File attachment = null;
		List<String> tags = null;
		String note = null;
		String quote = null;

		MemexEntityBuilder builder = MemexEntity.builder().withType(type).withTitle(title);
		if (attachment != null)
			builder.withAttachment(attachment);

		if (note != null)
			builder.withNote(note);

		if (quote != null)
			builder.withQuote(quote);

		MemexEntity entity = builder.build();
	}
}
