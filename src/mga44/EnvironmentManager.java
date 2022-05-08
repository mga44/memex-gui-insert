package mga44;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EnvironmentManager {
	// TODO better name, structure
	private Path memexDirectory;
	private Path databaseFile;

	private static EnvironmentManager current;

	private EnvironmentManager() {
	}

	public static EnvironmentManager getInstance() {
		if (current != null)
			return current;

		current = new EnvironmentManager();
		String property = System.getProperty("memex_directory"); // TODO to constant
		current.memexDirectory = Paths.get(property);
		current.databaseFile = Path.of(current.memexDirectory.toString(), "content", "data.ndtl");

		return current;
	}

	public Path getMemexDirectory() {
		return memexDirectory;
	}

	public Path getDatabaseFile() {
		return databaseFile;
	}

}
