package mga44.memex;

import mga44.gui.MainWindow;
import mga44.gui.action.SaveEntityAction;
import mga44.io.ndtl.MemexEntity;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.ZonedDateTime;

public class SaveButtonAction extends MouseAdapter {

    private final MainWindow window;

    public SaveButtonAction(MainWindow window) {
        this.window = window;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //@formatter:off
        MemexEntity.MemexEntityBuilder builder = new MemexEntity.MemexEntityBuilder(window.title.getText())
                .withType(((ContentType) window.type.getSelectedItem()))
                .withNote(window.note.getText())
                .withQuote(window.quote.getText())
                .withLink(window.link.getText())
                .withAttachment(window.file.getText())
                .withTags(window.tags.getText())
                .withCreated(ZonedDateTime.now())
                .withIsDone(window.isDone.isSelected())
                .withIsRevised(window.isRevised.isSelected())
                .withTerm(window.term.getText())
                .withAuthor(window.author.getText())
                .withProject(window.project.getText())
                .withPerson(window.person.getText());
        //@formatter:on
        SaveEntityAction action = new SaveEntityAction(builder.build());
        action.run();

        System.exit(0);

    }
}
