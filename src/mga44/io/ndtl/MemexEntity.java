package mga44.io.ndtl;

import mga44.SimpleMessageWindow;
import mga44.memex.ContentType;
import mga44.memex.MemexRepository;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.processing.Generated;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MemexEntity {

    private final ContentType type;
    private final String title;
    private final Path attachment;
    private final List<String> tags;
    private final String note;
    private final String quote;

    private final URI link;
    private final boolean isDone;
    private final String author;
    private final String project;
    private final String term;
    private final String person;
    private final boolean isRevised;
    private final String source;

    private final ZonedDateTime created;

    @Generated("SparkTools")
    private MemexEntity(MemexEntityBuilder memexEntityBuilder) {
        this.type = memexEntityBuilder.type;
        this.title = memexEntityBuilder.title;
        this.attachment = memexEntityBuilder.attachment;
        this.tags = memexEntityBuilder.tags;
        this.note = memexEntityBuilder.note;
        this.quote = memexEntityBuilder.quote;
        this.link = memexEntityBuilder.link;
        this.isDone = memexEntityBuilder.isDone;
        this.author = memexEntityBuilder.author;
        this.project = memexEntityBuilder.project;
        this.term = memexEntityBuilder.term;
        this.person = memexEntityBuilder.person;
        this.isRevised = memexEntityBuilder.isRevised;
        this.source = memexEntityBuilder.source;
        this.created = memexEntityBuilder.created;
    }

    public URI getLink() {
        return link;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getAuthor() {
        return author;
    }

    public String getProject() {
        return project;
    }

    public String getTerm() {
        return term;
    }

    public String getPerson() {
        return person;
    }

    public boolean isRevised() {
        return isRevised;
    }

    public String getSource() {
        return source;
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

    public TemporalAccessor getCreated() {
        return created;
    }

    @Generated("SparkTools")
    public static final class MemexEntityBuilder {
        private ContentType type;
        private String title;
        private Path attachment;
        private List<String> tags = Collections.emptyList();
        private String note;
        private String quote;
        private URI link;
        private boolean isDone;
        private String author;
        private String project;
        private String term;
        private String person;
        private boolean isRevised;
        private String source;
        private ZonedDateTime created;

        public MemexEntityBuilder(String title) {
            this.title = title;
        }

        public MemexEntityBuilder withType(ContentType type) {
            this.type = type;
            return this;
        }

        public MemexEntityBuilder withAttachment(String filePath) {
            try {
                if (StringUtils.isBlank(filePath))
                    return this;

                // TODO : to fix
                String attachedFileName = Paths.get(filePath).getName(attachment.getNameCount() - 1).toString();
                Path destination = MemexRepository.getInstance().getAttachmentDirectory().resolve(attachedFileName);
                Files.copy(attachment, destination);
                this.attachment = destination;
            } catch (IOException e) {
                e.printStackTrace();
                String errorMessage = "Failed to copy file:" + System.lineSeparator() + e.getLocalizedMessage();
                new SimpleMessageWindow().showError(errorMessage);
            }
            return this;
        }

        public MemexEntityBuilder withTags(String tags) {
            this.tags = Arrays.asList(tags.split(" |,"));
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

        public MemexEntityBuilder withLink(String link) {
            if (StringUtils.isBlank(link))
                return this;

            try {
                this.link = new URI(link);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                String errorMessage = "Given value is not a proper link: " + System.lineSeparator() + link;
                new SimpleMessageWindow().showError(errorMessage);
            }
            return this;
        }

        public MemexEntityBuilder withIsDone(boolean isDone) {
            this.isDone = isDone;
            return this;
        }

        public MemexEntityBuilder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public MemexEntityBuilder withProject(String project) {
            this.project = project;
            return this;
        }

        public MemexEntityBuilder withTerm(String term) {
            this.term = term;
            return this;
        }

        public MemexEntityBuilder withPerson(String person) {
            this.person = person;
            return this;
        }

        public MemexEntityBuilder withIsRevised(boolean isRevised) {
            this.isRevised = isRevised;
            return this;
        }

        public MemexEntityBuilder withSource(String source) {
            this.source = source;
            return this;
        }

        public MemexEntityBuilder withCreated(ZonedDateTime created) {
            this.created = created;
            return this;
        }


        public MemexEntity build() {
            return new MemexEntity(this);
        }
    }

}
