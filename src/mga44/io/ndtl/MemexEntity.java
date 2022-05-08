package mga44.io.ndtl;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import javax.annotation.processing.Generated;

import logic.ndtl.ContentType;

public class MemexEntity {
	private static final String PROPERTY_MASK = "  %s : %s" + System.lineSeparator();

	private ContentType type;
	private String title;
	private Path attachment;
	private List<String> tags;
	private String note;
	private String quote;
	// TODO add other properties

	@Generated("SparkTools")
	private MemexEntity(MemexEntityBuilder memexEntityBuilder) {
		this.type = memexEntityBuilder.type;
		this.title = memexEntityBuilder.title;
		this.attachment = memexEntityBuilder.attachment;
		this.tags = memexEntityBuilder.tags;
		this.note = memexEntityBuilder.note;
		this.quote = memexEntityBuilder.quote;
	}

	@Generated("SparkTools")
	public static MemexEntityBuilder builder() {
		return new MemexEntityBuilder();
	}

	public ContentType getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public Path getAttachment() {
		return attachment;
	}

	public List<String> getTags() {
		return tags;
	}

	public String getNote() {
		return note;
	}

	public String getQuote() {
		return quote;
	}

	public void saveTo(Path ndtlFile) throws IOException {
		// TODO possible implementation as static method in new class
		// TODO string parametrization
		StringBuilder sb = new StringBuilder();
		sb.append(title + System.lineSeparator());
		sb.append(String.format(PROPERTY_MASK, "TYPE", type.toString()));
		if (attachment != null)
			sb.append(String.format(PROPERTY_MASK, "FILE", attachment.getFileName().toString()));
		sb.append(String.format(PROPERTY_MASK, "QOTE", quote));

		RandomAccessFile raf = new RandomAccessFile(ndtlFile.toFile(), "rwd");
		String ndtlHeader = "var DATABASE = `" + System.lineSeparator();
		raf.seek((long) ndtlHeader.getBytes().length);
		raf.write(sb.toString().getBytes());
		raf.close();
	}

	@Generated("SparkTools")
	public static final class MemexEntityBuilder {
		private ContentType type;
		private String title;
		private Path attachment;
		private List<String> tags = Collections.emptyList();
		private String note;
		private String quote;

		private MemexEntityBuilder() {
		}

		public MemexEntityBuilder withType(ContentType type) {
			this.type = type;
			return this;
		}

		public MemexEntityBuilder withTitle(String title) {
			this.title = title;
			return this;
		}

		public MemexEntityBuilder withAttachment(Path attachment) {
			this.attachment = attachment;
			return this;
		}

		public MemexEntityBuilder withTags(List<String> tags) {
			this.tags = tags;
			return this;
		}

		public MemexEntityBuilder withNote(String note) {
			this.note = note;
			return this;
		}

		public MemexEntityBuilder withQuote(String quote) {
			this.quote = quote;
			return this;
		}

		public MemexEntity build() {
			return new MemexEntity(this);
		}
	}

}
