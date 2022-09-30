import mga44.SimpleMessageWindow;
import mga44.gui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.util.Properties;

public class Main extends JFrame {

    public static void main(String[] args) {
        initialize();
        EventQueue.invokeLater(() -> {
            try {
                MainWindow mainWindow = new MainWindow();
                mainWindow.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            String rootPath = "./resources/";
            String appConfigPath = rootPath + "app.properties";
            Properties appProps = new Properties(System.getProperties());
            appProps.load(new FileInputStream(appConfigPath));
            System.setProperties(appProps);
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = "Failed program initialisation:" + System.lineSeparator() + e.getLocalizedMessage();
            new SimpleMessageWindow().showError(errorMessage);
            System.exit(1);
        }
    }
}
