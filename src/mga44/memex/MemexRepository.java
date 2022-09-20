package mga44.memex;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MemexRepository {
    private static volatile MemexRepository current;
    private Path memexDirectory;
    private Path databaseFile;
    private Path htmlFile;
    private Path attachmentDirectory;

    private MemexRepository() {
    }

    private MemexRepository(Path repositoryDirectory) {
        current = new MemexRepository();
        memexDirectory = repositoryDirectory;
        databaseFile = Path.of(memexDirectory.toString(), "content", "data.ndtl");
        htmlFile = Path.of(memexDirectory.toString(), "index.html");
        attachmentDirectory = Path.of(memexDirectory.toString(), "content", "media");
    }

    public static MemexRepository getInstance() {
        if (current == null) {
            synchronized (MemexRepository.class) {
                if (current == null) {
                    String property = System.getProperty("memex_directory");
                    current = new MemexRepository(Paths.get(property));
                }
            }
        }

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
