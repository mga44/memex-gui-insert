package mga44.gui;

import mga44.io.FileChooseButton;
import mga44.memex.ContentType;
import mga44.memex.SaveButtonAction;

import javax.swing.*;

public class MainWindow {
    public JTabbedPane tabbedPane1;
    public JButton saveButton;
    public JButton closeButton;
    public JTextField title;
    public JComboBox<ContentType> type;
    public JTextField file;
    public JTextArea note;
    public JTextField link;
    public JTextArea quote;
    public JTextField tags;
    public JPanel panel;
    public JCheckBox isDone;
    public JCheckBox isRevised;
    public JTextField term;
    public JTextField author;
    public JTextField project;
    public JTextField person;
    private JButton fileButton;

    public void show() {
        this.createUIComponents();
        JFrame frame = new JFrame("Memex");
        frame.setContentPane(new MainWindow().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        type = new JComboBox<>();
        type.setModel(new DefaultComboBoxModel<>(ContentType.values()));

        saveButton = new JButton();
        saveButton.addMouseListener(new SaveButtonAction(this));

        closeButton = new JButton();
        closeButton.addActionListener(l -> JFrame.getFrames()[0].dispose());

        fileButton = new JButton();
        fileButton.addActionListener(l -> new FileChooseButton(file));
    }
}






