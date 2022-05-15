package mga44.io.ndtl;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import javax.annotation.processing.Generated;

import logic.ndtl.ContentType;

public class MemexEntity {

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
