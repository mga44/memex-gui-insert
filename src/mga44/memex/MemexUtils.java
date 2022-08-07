package mga44.memex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import mga44.io.ndtl.MemexEntity;

public class MemexUtils {
	private static final String PROPERTY_MASK = "  %s : %s" + System.lineSeparator(); // TODO handling multi line
																						// entries

	public void save(MemexEntity e) throws IOException {
		StringBuilder sb = new StringBuilder();

		BiConsumer<String, Object> append = (name, value) -> {
			if (Optional.ofNullable(value).map(Object::toString).map(StringUtils::isNotBlank).orElse(Boolean.FALSE))
				sb.append(String.format(PROPERTY_MASK, name, value));
		};
		sb.append(e.getTitle() + System.lineSeparator());
		append.accept("TYPE", e.getType());
		append.accept("QOTE", e.getQuote());
		append.accept("NOTE", e.getNote());
		append.accept("TAGS", e.getTags().stream().collect(Collectors.joining(",")));
		if (e.getAttachment() != null && Files.exists(e.getAttachment()))
			append.accept("FILE", e.getAttachment().getFileName().toString());

		append.accept("LINK", e.getLink());

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")
				.withZone(ZoneId.systemDefault());
		append.accept("DATE", dateFormatter.format(Instant.now()));
		append.accept("DONE", e.isDone());
		append.accept("AUTH", e.getAuthor());
		append.accept("PROJ", e.getProject());
		append.accept("TERM", e.getTerm());
		append.accept("PERS", e.getPerson());
		append.accept("REVI", e.isRevised());
		append.accept("SRCE", e.getSource());

		Path databaseFile = MemexRepository.getInstance().getDatabaseFile();
		LinkedList<String> contents = Files.readAllLines(databaseFile).stream()
				.collect(Collectors.toCollection(() -> new LinkedList<>()));
		contents.add(1, sb.toString());
		Files.write(databaseFile, contents, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
	}
}
