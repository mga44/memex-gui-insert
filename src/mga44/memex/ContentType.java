package mga44.memex;

public enum ContentType
{
	TOOL("Tool"), PODCAST("Podcast"), LIST("List"), MUSIC("Music"), ARTICLE("Article"), BOOK("Book"), VIDEO("Video"),
	LECTURE("Lecture"), IMAGE("Image"), QUOTE("Quote"), NOTE("Note"), TERM("Term"), GAME("Game"),
	ENCYCLOPEDIA("Encyclopedia");

	private String name;

	ContentType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
