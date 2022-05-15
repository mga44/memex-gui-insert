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
	private static final String PROPERTY_MASK = "  %s : %s" + System.lineSeparator(); // TODO handling multi line
																						// entries

	public void save(MemexEntity e) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append(e.getTitle() + System.lineSeparator());
		sb.append(String.format(PROPERTY_MASK, "TYPE", e.getType().toString()));
		sb.append(String.format(PROPERTY_MASK, "QOTE", e.getQuote()));
		sb.append(String.format(PROPERTY_MASK, "NOTE", e.getNote()));
		if (e.getAttachment() != null && Files.exists(e.getAttachment()))
			sb.append(String.format(PROPERTY_MASK, "FILE", e.getAttachment().getFileName().toString()));

		Path databaseFile = EnvironmentManager.getInstance().getDatabaseFile();
		LinkedList<String> contents = Files.readAllLines(databaseFile).stream()
				.collect(Collectors.toCollection(() -> new LinkedList<>()));
		contents.add(1, sb.toString());
		Files.write(databaseFile, contents, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
	}
}
