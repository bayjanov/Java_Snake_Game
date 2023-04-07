import javax.swing.*;
import java.awt.event.*;

public class GameFrame extends JFrame {

    private NewGamePanel panel;

    public GameFrame() {
        NewFrame();
    }

    public void NewFrame() {
        panel = new NewGamePanel();
        this.add(panel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        // When retry button clicked
        panel.retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GameFrame();
            }
        });

        // When quit button clicked
        panel.quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

}
