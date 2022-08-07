package mga44.memex;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MemexRepository {
	private Path memexDirectory;
	private Path databaseFile;
	private Path htmlFile;
	private Path attachmentDirectory;

	private static MemexRepository current;

	private MemexRepository() {
	}

	private MemexRepository(Path repositoryDirectory) {
		memexDirectory = repositoryDirectory;
		databaseFile = Path.of(current.memexDirectory.toString(), "content", "data.ndtl");
		htmlFile = Path.of(current.memexDirectory.toString(), "index.html");
		attachmentDirectory = Path.of(current.memexDirectory.toString(), "content", "media");
	}

	public static MemexRepository getInstance() {
		if (current != null)
			return current;

		String property = System.getProperty("memex_directory");
		current = new MemexRepository(Paths.get(property));

		return current;
	}

	public Path getMemexDirectory() {
		return memexDirectory;
	}

	public Path getDatabaseFile() {
		return databaseFile;
	}

	public Path getHtmlFile() {
		return htmlFile;
	}

	public Path getAttachmentDirectory() {
		return attachmentDirectory;
	}
}
