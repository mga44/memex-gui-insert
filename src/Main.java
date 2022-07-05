
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import logic.ndtl.ContentType;
import mga44.SimpleMessageWindow;
import mga44.gui.MouseDragAdapter;
import mga44.gui.action.ComboBoxWithIconRenderer;
import mga44.gui.action.SaveEntityAction;
import mga44.io.FileChooseButton;
import mga44.io.ndtl.MemexEntity.MemexEntityBuilder;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private static final Font programFont = new Font("Tahoma", Font.PLAIN, 12);

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		initialize();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void initialize() {
		try {
			// TODO add error log file
			FlatDarkLaf.setup();
			UIManager.setLookAndFeel(new FlatDarkLaf());
			String rootPath = "./resources/";
			String appConfigPath = rootPath + "app.properties";
			Properties appProps = new Properties();
			appProps.load(new FileInputStream(appConfigPath));
			System.setProperty("memex_directory", appProps.getProperty("memex_directory"));
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = "Failed program initialisation:" + System.lineSeparator() + e.getLocalizedMessage();
			new SimpleMessageWindow().showError(errorMessage);
			System.exit(1);
		}
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 625);
		JPanel mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gbl_mainPane = new GridBagLayout();
		gbl_mainPane.columnWidths = new int[] { 194, 194, 194, 194, 0 };
		gbl_mainPane.rowHeights = new int[] { 231, 0, 0 };
		gbl_mainPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_mainPane.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		mainPane.setLayout(gbl_mainPane);
		setContentPane(mainPane);
		setUndecorated(true);
		this.addMouseListener(new MouseDragAdapter(this));
		this.addMouseMotionListener(new MouseDragAdapter(this));
		JTabbedPane jtp = new JTabbedPane();
		jtp.setBorder(UIManager.getBorder("OptionPane.border"));
		jtp.addMouseListener(new MouseDragAdapter(jtp));
		jtp.addMouseMotionListener(new MouseDragAdapter(jtp));
		GridBagConstraints gbc_jtp = new GridBagConstraints();
		gbc_jtp.gridwidth = 4;
		gbc_jtp.fill = GridBagConstraints.BOTH;
		gbc_jtp.insets = new Insets(0, 0, 5, 0);
		gbc_jtp.gridx = 0;
		gbc_jtp.gridy = 0;
		mainPane.add(jtp, gbc_jtp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 0, 0, 0));
		jtp.add("Basic", contentPane);
		JPanel advancedPane = new JPanel();
		jtp.add("Advanced", advancedPane);
		jtp.setEnabledAt(1, false);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 32, 96, 51, 0 };
		gbl_contentPane.rowHeights = new int[] { 19, 23, 21, 19, 19, 19, 19, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JComboBox<ContentType> type = new JComboBox<>();
		type.setModel(new DefaultComboBoxModel<>(ContentType.values()));

		JLabel titleLabel = new JLabel("Title");
		titleLabel.setFont(programFont);
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.EAST;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		contentPane.add(titleLabel, gbc_titleLabel);

		JTextField title = new JTextField();
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.fill = GridBagConstraints.HORIZONTAL;
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridwidth = 2;
		gbc_title.gridx = 1;
		gbc_title.gridy = 0;
		contentPane.add(title, gbc_title);
		title.setColumns(10);

		JLabel typeLabel = new JLabel("Type");
		typeLabel.setFont(programFont);
		GridBagConstraints gbc_typeLabel = new GridBagConstraints();
		gbc_typeLabel.anchor = GridBagConstraints.EAST;
		gbc_typeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_typeLabel.gridx = 0;
		gbc_typeLabel.gridy = 1;
		contentPane.add(typeLabel, gbc_typeLabel);
		type.setFont(programFont);
		type.setRenderer(new ComboBoxWithIconRenderer());
		GridBagConstraints gbc_type = new GridBagConstraints();
		gbc_type.fill = GridBagConstraints.HORIZONTAL;
		gbc_type.insets = new Insets(0, 0, 5, 0);
		gbc_type.gridwidth = 2;
		gbc_type.gridx = 1;
		gbc_type.gridy = 1;
		contentPane.add(type, gbc_type);

		JLabel FileLabel = new JLabel("File");
		FileLabel.setFont(programFont);
		GridBagConstraints gbc_FileLabel = new GridBagConstraints();
		gbc_FileLabel.anchor = GridBagConstraints.EAST;
		gbc_FileLabel.fill = GridBagConstraints.VERTICAL;
		gbc_FileLabel.insets = new Insets(0, 0, 5, 5);
		gbc_FileLabel.gridx = 0;
		gbc_FileLabel.gridy = 2;
		contentPane.add(FileLabel, gbc_FileLabel);

		JTextField file = new JTextField();
		GridBagConstraints gbc_file = new GridBagConstraints();
		gbc_file.fill = GridBagConstraints.HORIZONTAL;
		gbc_file.insets = new Insets(0, 0, 5, 5);
		gbc_file.gridx = 1;
		gbc_file.gridy = 2;
		contentPane.add(file, gbc_file);
		file.setColumns(10);
		GridBagConstraints gbc_findFileButton = new GridBagConstraints();
		gbc_findFileButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_findFileButton.insets = new Insets(0, 0, 5, 0);
		gbc_findFileButton.gridx = 2;
		gbc_findFileButton.gridy = 2;

		JButton findFileButton = new JButton("Find");
		findFileButton.addMouseListener(new FileChooseButton(file));
		contentPane.add(findFileButton, gbc_findFileButton);

		JLabel tagsLabel = new JLabel("Tags");
		tagsLabel.setFont(programFont);
		GridBagConstraints gbc_tagsLabel = new GridBagConstraints();
		gbc_tagsLabel.anchor = GridBagConstraints.EAST;
		gbc_tagsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tagsLabel.gridx = 0;
		gbc_tagsLabel.gridy = 3;
		contentPane.add(tagsLabel, gbc_tagsLabel);

		JTextField tags = new JTextField();
		GridBagConstraints gbc_tags = new GridBagConstraints();
		gbc_tags.fill = GridBagConstraints.BOTH;
		gbc_tags.insets = new Insets(0, 0, 5, 0);
		gbc_tags.gridwidth = 2;
		gbc_tags.gridx = 1;
		gbc_tags.gridy = 3;
		contentPane.add(tags, gbc_tags);
		tags.setColumns(10);

		JLabel NoteLabel = new JLabel("Note");
		NoteLabel.setFont(programFont);
		GridBagConstraints gbc_NoteLabel = new GridBagConstraints();
		gbc_NoteLabel.insets = new Insets(0, 0, 5, 5);
		gbc_NoteLabel.gridx = 0;
		gbc_NoteLabel.gridy = 4;
		contentPane.add(NoteLabel, gbc_NoteLabel);

		JEditorPane note = new JEditorPane();
		GridBagConstraints gbc_note = new GridBagConstraints();
		gbc_note.fill = GridBagConstraints.BOTH;
		gbc_note.insets = new Insets(0, 0, 5, 0);
		gbc_note.gridwidth = 2;
		gbc_note.gridx = 1;
		gbc_note.gridy = 4;
		contentPane.add(note, gbc_note);

		JLabel linkLabel = new JLabel("Link");
		linkLabel.setFont(programFont);
		GridBagConstraints gbc_linkLabel = new GridBagConstraints();
		gbc_linkLabel.anchor = GridBagConstraints.NORTH;
		gbc_linkLabel.insets = new Insets(0, 0, 5, 5);
		gbc_linkLabel.gridx = 0;
		gbc_linkLabel.gridy = 5;
		contentPane.add(linkLabel, gbc_linkLabel);

		JTextField link = new JTextField();
		GridBagConstraints gbc_link = new GridBagConstraints();
		gbc_link.fill = GridBagConstraints.BOTH;
		gbc_link.insets = new Insets(0, 0, 5, 0);
		gbc_link.gridwidth = 2;
		gbc_link.gridx = 1;
		gbc_link.gridy = 5;
		contentPane.add(link, gbc_link);
		link.setColumns(10);

		JLabel quoteLabel = new JLabel("Quote");
		quoteLabel.setFont(programFont);
		GridBagConstraints gbc_quoteLabel = new GridBagConstraints();
		gbc_quoteLabel.insets = new Insets(0, 0, 0, 5);
		gbc_quoteLabel.gridx = 0;
		gbc_quoteLabel.gridy = 6;
		contentPane.add(quoteLabel, gbc_quoteLabel);

		JEditorPane quote = new JEditorPane();
		GridBagConstraints gbc_quote = new GridBagConstraints();
		gbc_quote.fill = GridBagConstraints.BOTH;
		gbc_quote.gridwidth = 2;
		gbc_quote.gridx = 1;
		gbc_quote.gridy = 6;
		contentPane.add(quote, gbc_quote);

		JButton saveButton = new JButton("Save");
		saveButton.setIcon(new ImageIcon("resources/icon/download.png"));
		saveButton.setFont(programFont);
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//@formatter:off
						MemexEntityBuilder builder = new MemexEntityBuilder(title.getText())
								.withType(((ContentType) type.getSelectedItem()))
								.withNote(note.getText())
								.withQuote(quote.getText())
								.withLink(link.getText())
								.withAttachment(file.getText())
								.withTags(tags.getText());
						//@formatter:on
				SaveEntityAction action = new SaveEntityAction(builder.build());
				action.run();

				System.exit(0);
			}
		});
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.anchor = GridBagConstraints.EAST;
		gbc_saveButton.gridx = 3;
		gbc_saveButton.gridy = 1;
		mainPane.add(saveButton, gbc_saveButton);

		JButton exitBtn = new JButton("X");
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 1;
		mainPane.add(exitBtn, gbc_btnNewButton_2);
	}
}
