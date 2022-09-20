package test.mga44.memex;

import mga44.memex.MemexRepository;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MemexRepositoryTest {

    @Test
    public void initializationShouldBeSuccessful() {
        System.setProperty("memex_directory", ".");
        MemexRepository mr = MemexRepository.getInstance();

        assertEquals(Path.of("."), mr.getMemexDirectory());
        assertEquals(Path.of(".", "content", "data.ndtl"), mr.getDatabaseFile());
        assertEquals(Path.of(".", "content", "media"), mr.getAttachmentDirectory());
        assertEquals(Path.of(".", "index.html"), mr.getHtmlFile());
    }
}