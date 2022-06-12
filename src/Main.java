
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

import logic.ndtl.ContentType;
import mga44.SimpleMessageWindow;
import mga44.gui.action.ComboBoxWithIconRenderer;
import mga44.gui.action.SaveEntityAction;
import mga44.io.FileChooseButton;
import mga44.io.ndtl.MemexEntity.MemexEntityBuilder;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private static final Font programFont = new Font("Tahoma", Font.PLAIN, 12);

	private JPanel contentPane;
	private JTextField title;
	private JTextField file;
	private JTextField tags;
	private JTextField link;

	int posX = 0, posY = 0;

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
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				posX = e.getX();
				posY = e.getY();
			}
		});
		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent evt) {
				setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);
			}
		});
		JTabbedPane jtp = new JTabbedPane();
		GridBagConstraints gbc_jtp = new GridBagConstraints();
		gbc_jtp.gridwidth = 4;
		gbc_jtp.fill = GridBagConstraints.BOTH;
		gbc_jtp.insets = new Insets(0, 0, 5, 0);
		gbc_jtp.gridx = 0;
		gbc_jtp.gridy = 0;
		mainPane.add(jtp, gbc_jtp);
		contentPane = new JPanel();
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

		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setFont(programFont);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		title = new JTextField();
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.fill = GridBagConstraints.HORIZONTAL;
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridwidth = 2;
		gbc_title.gridx = 1;
		gbc_title.gridy = 0;
		contentPane.add(title, gbc_title);
		title.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Type");
		lblNewLabel_1.setFont(programFont);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		type.setFont(programFont);
		type.setRenderer(new ComboBoxWithIconRenderer());
		GridBagConstraints gbc_type = new GridBagConstraints();
		gbc_type.fill = GridBagConstraints.HORIZONTAL;
		gbc_type.insets = new Insets(0, 0, 5, 0);
		gbc_type.gridwidth = 2;
		gbc_type.gridx = 1;
		gbc_type.gridy = 1;
		contentPane.add(type, gbc_type);

		JButton btnNewButton_1 = new JButton("Find");

		JLabel lblNewLabel_4 = new JLabel("File");
		lblNewLabel_4.setFont(programFont);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 2;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);
		btnNewButton_1.addMouseListener(new FileChooseButton(file));

		file = new JTextField();
		GridBagConstraints gbc_file = new GridBagConstraints();
		gbc_file.fill = GridBagConstraints.HORIZONTAL;
		gbc_file.insets = new Insets(0, 0, 5, 5);
		gbc_file.gridx = 1;
		gbc_file.gridy = 2;
		contentPane.add(file, gbc_file);
		file.setColumns(10);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 2;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);

		JLabel lblNewLabel_4_1 = new JLabel("Tags");
		lblNewLabel_4_1.setFont(programFont);
		GridBagConstraints gbc_lblNewLabel_4_1 = new GridBagConstraints();
		gbc_lblNewLabel_4_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4_1.gridx = 0;
		gbc_lblNewLabel_4_1.gridy = 3;
		contentPane.add(lblNewLabel_4_1, gbc_lblNewLabel_4_1);

		tags = new JTextField();
		GridBagConstraints gbc_tags = new GridBagConstraints();
		gbc_tags.fill = GridBagConstraints.BOTH;
		gbc_tags.insets = new Insets(0, 0, 5, 0);
		gbc_tags.gridwidth = 2;
		gbc_tags.gridx = 1;
		gbc_tags.gridy = 3;
		contentPane.add(tags, gbc_tags);
		tags.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Note");
		lblNewLabel_2.setFont(programFont);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 4;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JEditorPane note = new JEditorPane();
		note.setToolTipText("");
		GridBagConstraints gbc_note = new GridBagConstraints();
		gbc_note.fill = GridBagConstraints.BOTH;
		gbc_note.insets = new Insets(0, 0, 5, 0);
		gbc_note.gridwidth = 2;
		gbc_note.gridx = 1;
		gbc_note.gridy = 4;
		contentPane.add(note, gbc_note);

		JLabel newLabel_5 = new JLabel("Link");
		newLabel_5.setFont(programFont);
		GridBagConstraints gbc_newLabel_5 = new GridBagConstraints();
		gbc_newLabel_5.anchor = GridBagConstraints.NORTH;
		gbc_newLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_newLabel_5.gridx = 0;
		gbc_newLabel_5.gridy = 5;
		contentPane.add(newLabel_5, gbc_newLabel_5);

		link = new JTextField();
		GridBagConstraints gbc_link = new GridBagConstraints();
		gbc_link.fill = GridBagConstraints.BOTH;
		gbc_link.insets = new Insets(0, 0, 5, 0);
		gbc_link.gridwidth = 2;
		gbc_link.gridx = 1;
		gbc_link.gridy = 5;
		contentPane.add(link, gbc_link);
		link.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Quote");
		lblNewLabel_3.setFont(programFont);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 6;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JEditorPane quote = new JEditorPane();
		GridBagConstraints gbc_quote = new GridBagConstraints();
		gbc_quote.fill = GridBagConstraints.BOTH;
		gbc_quote.gridwidth = 2;
		gbc_quote.gridx = 1;
		gbc_quote.gridy = 6;
		contentPane.add(quote, gbc_quote);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.setIcon(new ImageIcon("resources/icon/download.png"));
		btnNewButton.setFont(programFont);
		btnNewButton.addMouseListener(new MouseAdapter() {
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
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_3.gridx = 3;
		gbc_btnNewButton_3.gridy = 1;
		mainPane.add(btnNewButton, gbc_btnNewButton_3);

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
