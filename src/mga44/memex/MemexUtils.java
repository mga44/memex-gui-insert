package mga44.memex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.stream.Collectors;

import mga44.EnvironmentManager;
import mga44.io.ndtl.MemexEntity;

public class MemexUtils {
	private static final String PROPERTY_MASK = "  %s : %s" + System.lineSeparator();

	private Path ndtlFile = EnvironmentManager.getInstance().getDatabaseFile();

	public void save(MemexEntity e) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append(e.getTitle() + System.lineSeparator());
		sb.append(String.format(PROPERTY_MASK, "TYPE", e.getType().toString()));
		if (e.getAttachment() != null)
			sb.append(String.format(PROPERTY_MASK, "FILE", e.getAttachment().getFileName().toString()));

		sb.append(String.format(PROPERTY_MASK, "QOTE", e.getQuote()));
		LinkedList<String> contents = Files.readAllLines(ndtlFile).stream()
				.collect(Collectors.toCollection(() -> new LinkedList<>()));
		contents.add(1, sb.toString());
		Files.write(ndtlFile, contents, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
	}
}
