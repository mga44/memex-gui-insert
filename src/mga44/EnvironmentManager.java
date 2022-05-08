package mga44;

import java.nio.file.Path;

public class EnvironmentManager {

	public static final Path memexDirectory = Path.of(".");
	public static final Path databaseFile = Path.of(memexDirectory.toString(), "content", "data.ndtl");
}
