import model.ELearningApp;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ELearningApp app = new ELearningApp();
            app.setVisible(true);
        });
    }
}