package logic.ndtl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ContentItem {

	String title;
	ContentType type;
	Map<String, String> contents = new HashMap<>();

	@Override
	public String toString() {
		String contentsStr = "";
		for (Entry<String, String> item : contents.entrySet())
			contentsStr += item.getKey() + "  " + item.getValue() + System.lineSeparator();

		return title + System.lineSeparator() + contentsStr;
	}
}
