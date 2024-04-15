import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Skins implements ActionListener {

    /**The Skin frame*/
    private final JFrame frame;

    /**The displayed Snakehead*/
    private final SnakeHead head;

    /**The list of the Snaketails used to display the skins*/
    private final List<SnakeTail> tails = new ArrayList<>();

    public Skins() {

        frame = new JFrame("Skins");
        JPanel panel = new JPanel(null);
        prepareLaunchFrame();

        JButton next = new JButton("Next");
        next.setFocusable(false);
        next.setBounds(375, 160, 60, 160);
        next.addActionListener(this);
        panel.add(next);

        JButton last = new JButton("Last");
        last.setFocusable(false);
        last.setBounds(65, 160, 60, 160);
        last.addActionListener(this);
        panel.add(last);

        JButton select = new JButton("Select skin");
        select.setFocusable(false);
        select.setBounds(125, 400, 250, 60);
        select.addActionListener(this);
        panel.add(select);

        JButton backToLaunch = new JButton("Back");
        backToLaunch.setFocusable(false);
        backToLaunch.setBounds(125, 500, 250, 60);
        backToLaunch.addActionListener(this);
        panel.add(backToLaunch);

        JLabel label = new JLabel("Choose your skin to play!");
        label.setBounds(130, 60, 250, 20);
        label.setFont(new Font("Ariral", Font.ITALIC, 20));
        panel.add(label);


        for (int i = 190; i < 281; i += 20) {
            SnakeTail tail = new SnakeTail(i, 280, 20, 20);
            tails.add(tail);
            panel.add(tail);
        }

        head = new SnakeHead(290, 280, 20, 20);
        panel.add(head);

        frame.add(panel);

        setSkinUsedSkin(Main.getSkin());

    }

    /**When frame is build, this method is called to display the lastly selected skin*/
    private void setSkinUsedSkin(String pactual) {
        //"default","blue","brown","black","red","gold","purple","gray"

        switch (pactual) {
            case "default" -> changeSkinColor(new Color(0, 204, 0), new Color(0, 102, 0));
            case "blue" -> changeSkinColor(new Color(0, 255, 255), new Color(0, 102, 102));
            case "brown" -> changeSkinColor(new Color(95, 48, 0), new Color(176, 88, 0));
            case "black" -> changeSkinColor(new Color(0, 0, 0), new Color(200, 200, 200));
            case "red" -> changeSkinColor(new Color(204, 0, 0), new Color(55, 0, 0));
            case "gold" -> changeSkinColor(new Color(255, 255, 0), new Color(153, 153, 0));
            case "purple" -> changeSkinColor(new Color(204, 0, 102), new Color(102, 0, 51));
            case "gray" -> changeSkinColor(new Color(96, 96, 96), new Color(32, 32, 32));
        }
    }

    /**
     * Sets up the frame
     */
    private void prepareLaunchFrame() {

        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            ImageIcon image = new ImageIcon(Objects.requireNonNull(getClass().getResource("Snake.png")));
            frame.setIconImage(image.getImage());
        } catch (NullPointerException e) {
            System.out.println("Image not found");
        }
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    /**The listener for the button clicks*/
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton button) {

            if (button.getText().equalsIgnoreCase("Next")) {
                nextSkin(Main.getSkin());
            } else if (button.getText().equalsIgnoreCase("Last")) {
                lastSkin(Main.getSkin());
            } else if (button.getText().equalsIgnoreCase("Select skin")) {
                new SettingsManager().save();
                System.out.println(Main.getSkin());

            } else {
                frame.dispose();
                new SettingsFrame();
            }


        }
    }

    /**On the LAST Button click. Chooses the skin before skin from the displayed skin and displays it*/
    private void lastSkin(String pactual) {
        //"default","blue","brown","black","red","gold","purple","gray"

        switch (pactual) {
            case "default" -> {
                Main.setSkin("gray");
                changeSkinColor(new Color(96, 96, 96), new Color(32, 32, 32));
            }
            case "blue" -> {
                Main.setSkin("default");
                changeSkinColor(new Color(0, 204, 0), new Color(0, 102, 0));
            }
            case "brown" -> {
                Main.setSkin("blue");
                changeSkinColor(new Color(0, 255, 255), new Color(0, 102, 102));
            }
            case "black" -> {
                Main.setSkin("brown");
                changeSkinColor(new Color(95, 48, 0), new Color(176, 88, 0));
            }
            case "red" -> {
                Main.setSkin("black");
                changeSkinColor(new Color(0, 0, 0), new Color(200, 200, 200));
            }
            case "gold" -> {
                Main.setSkin("red");
                changeSkinColor(new Color(204, 0, 0), new Color(55, 0, 0));
            }
            case "purple" -> {
                Main.setSkin("gold");
                changeSkinColor(new Color(255, 255, 0), new Color(153, 153, 0));
            }
            case "gray" -> {
                Main.setSkin("purple");
                changeSkinColor(new Color(204, 0, 102), new Color(102, 0, 51));
            }
        }

    }


    /**On the NEXT Button click. Chooses the next skin from the displayed skin and displays it*/
    private void nextSkin(String pactual) {

        //"default","blue","brown","black","red","gold","purple","gray"

        switch (pactual) {
            case "default" -> {
                Main.setSkin("blue");
                changeSkinColor(new Color(0, 255, 255), new Color(0, 102, 102));
            }
            case "blue" -> {
                Main.setSkin("brown");
                changeSkinColor(new Color(95, 48, 0), new Color(176, 88, 0));
            }
            case "brown" -> {
                Main.setSkin("black");
                changeSkinColor(new Color(0, 0, 0), new Color(200, 200, 200));
            }
            case "black" -> {
                Main.setSkin("red");
                changeSkinColor(new Color(204, 0, 0), new Color(55, 0, 0));
            }
            case "red" -> {
                Main.setSkin("gold");
                changeSkinColor(new Color(255, 255, 0), new Color(153, 153, 0));
            }
            case "gold" -> {
                Main.setSkin("purple");
                changeSkinColor(new Color(204, 0, 102), new Color(102, 0, 51));
            }
            case "purple" -> {
                Main.setSkin("gray");
                changeSkinColor(new Color(96, 96, 96), new Color(32, 32, 32));
            }
            case "gray" -> {
                Main.setSkin("default");
                changeSkinColor(new Color(0, 204, 0), new Color(0, 102, 0));
            }
        }
    }

    /**Changes the color of the skin depending on what color is given*/
    private void changeSkinColor(final Color pcolor, final Color phead){

        head.setBackground(phead);

        for (SnakeTail tail : tails) {
            tail.setBackground(pcolor);
        }

    }
}
