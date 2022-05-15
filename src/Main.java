
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import logic.ndtl.ContentType;
import mga44.gui.action.SaveEntityAction;
import mga44.io.FileChooseButton;

public class Main extends JFrame {
	private static final long serialVersionUID = 5373416828900017783L;

	private JPanel contentPane;
	private JTextField title;
	private JTextField file;

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
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			String rootPath = "./resources/";
			String appConfigPath = rootPath + "app.properties";
			Properties appProps = new Properties();
			appProps.load(new FileInputStream(appConfigPath));
			System.setProperty("memex_directory", appProps.getProperty("memex_directory"));
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = "Failed program initialisation:" + System.lineSeparator() + e.getLocalizedMessage();
			JOptionPane.showInternalMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Memex");
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		title = new JTextField();
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridwidth = 5;
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.fill = GridBagConstraints.HORIZONTAL;
		gbc_title.gridx = 1;
		gbc_title.gridy = 0;
		contentPane.add(title, gbc_title);
		title.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Type");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JComboBox<ContentType> type = new JComboBox<>();
		type.setModel(new DefaultComboBoxModel<>(ContentType.values()));
		type.setFont(new Font("Arial", Font.PLAIN, 14));
		GridBagConstraints gbc_type = new GridBagConstraints();
		gbc_type.gridwidth = 5;
		gbc_type.insets = new Insets(0, 0, 5, 0);
		gbc_type.fill = GridBagConstraints.HORIZONTAL;
		gbc_type.gridx = 1;
		gbc_type.gridy = 1;
		contentPane.add(type, gbc_type);

		JLabel lblNewLabel_4 = new JLabel("File");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 2;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

		file = new JTextField();
		GridBagConstraints gbc_file = new GridBagConstraints();
		gbc_file.gridwidth = 4;
		gbc_file.insets = new Insets(0, 0, 5, 5);
		gbc_file.fill = GridBagConstraints.HORIZONTAL;
		gbc_file.gridx = 1;
		gbc_file.gridy = 2;
		contentPane.add(file, gbc_file);
		file.setColumns(10);

		JButton btnNewButton_1 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 5;
		gbc_btnNewButton_1.gridy = 2;
		btnNewButton_1.addMouseListener(new FileChooseButton(file));
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);

		JLabel lblNewLabel_2 = new JLabel("Note");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JEditorPane note = new JEditorPane();
		note.setToolTipText("");
		GridBagConstraints gbc_note = new GridBagConstraints();
		gbc_note.gridwidth = 5;
		gbc_note.insets = new Insets(0, 0, 5, 0);
		gbc_note.fill = GridBagConstraints.BOTH;
		gbc_note.gridx = 1;
		gbc_note.gridy = 3;
		contentPane.add(note, gbc_note);

		JLabel lblNewLabel_3 = new JLabel("Quote");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 4;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JEditorPane quote = new JEditorPane();
		GridBagConstraints gbc_quote = new GridBagConstraints();
		gbc_quote.gridwidth = 5;
		gbc_quote.insets = new Insets(0, 0, 5, 0);
		gbc_quote.fill = GridBagConstraints.BOTH;
		gbc_quote.gridx = 1;
		gbc_quote.gridy = 4;
		contentPane.add(quote, gbc_quote);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.setIcon(new ImageIcon("resources/icon/download.jpg"));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SaveEntityAction action = new SaveEntityAction(title.getText(), ((ContentType) type.getSelectedItem()),
						note.getText(), quote.getText(), file.getText());
				action.run();

				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 5;
		contentPane.add(btnNewButton, gbc_btnNewButton);
	}
}
