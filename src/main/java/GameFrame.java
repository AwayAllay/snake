import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class GameFrame extends JFrame {

    private final JFrame frame;
    private final JPanel panel;

    private final JPanel actionBar;

    private final JLabel level;
    
    private final JLabel timer;

    public GameFrame() {
        frame = new JFrame();

        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());

        actionBar = new JPanel();
        actionBar.setBackground((new Color(96, 96,96)));
        actionBar.setPreferredSize(new Dimension(frame.getWidth(), 60));

        level = new JLabel("LEVEL 0");
        level.setBounds(925, 5, 125, 50);
        level.setBackground(Color.WHITE);
        level.setOpaque(true);
        level.setFont(new Font("Ariral", Font.BOLD, 50));
        
        timer = new JLabel("00:00:00");
        timer.setBounds(100, 5, 125, 50);
        timer.setBackground(Color.WHITE);
        timer.setOpaque(true);
        timer.setFont(new Font("Ariral", Font.BOLD, 50));

        actionBar.add(level);
        actionBar.add(timer);

        initializeFrame();
    }

    private void initializeFrame() {
        try {
            ImageIcon image = new ImageIcon(Objects.requireNonNull(getClass().getResource("Snake.png")));
            frame.setIconImage(image.getImage());

        } catch (NullPointerException e) {
            System.out.println("Image not found");
        }
        frame.setTitle("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setSize(2100, 1200);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.addKeyListener(new KeyListener());

        panel.add(actionBar, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void setLevel(String newLevel){
        level.setText(newLevel);
    }
    public void setTime(int time){
        
    }

}
