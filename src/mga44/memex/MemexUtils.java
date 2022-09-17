package mga44.memex;

import mga44.io.ndtl.MemexEntity;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.BiConsumer;

public class MemexUtils {
    private static final String PROPERTY_MASK = "  %s : %s" + System.lineSeparator(); // TODO handling multi line entries

    public void save(MemexEntity e) throws IOException {
        String element = prepareEntityToSaving(e);
        Path databaseFile = MemexRepository.getInstance().getDatabaseFile();
        LinkedList<String> contents = new LinkedList<>(Files.readAllLines(databaseFile));
        contents.add(1, element);
        Files.write(databaseFile, contents, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private String prepareEntityToSaving(MemexEntity e) {
        StringBuilder sb = new StringBuilder();

        BiConsumer<String, Object> append = (name, value) -> {
            String valStr = Optional.ofNullable(value).map(Object::toString).orElse("");
            if (StringUtils.isNotBlank(valStr)) {
                if (valStr.contains(System.lineSeparator())) {
                    String newlinePrefix = System.lineSeparator() + "    > ";
                    valStr = newlinePrefix + valStr.replaceAll(System.lineSeparator(), newlinePrefix);
                }
                sb.append(String.format(PROPERTY_MASK, name, valStr));
            }
        };
        sb.append(e.getTitle()).append(System.lineSeparator());
        append.accept("TYPE", e.getType());
        append.accept("QOTE", e.getQuote());
        append.accept("NOTE", e.getNote());
        append.accept("TAGS", String.join(",", e.getTags()));
        if (e.getAttachment() != null && Files.exists(e.getAttachment()))
            append.accept("FILE", e.getAttachment().getFileName().toString());

        append.accept("LINK", e.getLink());

        if (e.getCreated() != null) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")
                    .withZone(ZoneId.systemDefault());
            append.accept("DATE", dateFormatter.format(e.getCreated()));
        }
        append.accept("DONE", e.isDone());
        append.accept("AUTH", e.getAuthor());
        append.accept("PROJ", e.getProject());
        append.accept("TERM", e.getTerm());
        append.accept("PERS", e.getPerson());
        append.accept("REVI", e.isRevised());
        append.accept("SRCE", e.getSource());

        return sb.toString();
    }
}
