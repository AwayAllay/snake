import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Cosmetics implements ActionListener {

    /**
     * The Skin frame
     */
    private final JFrame frame;

    /**
     * The displayed Snakehead
     */
    private final SnakeHead head;

    /**
     * The list of the Snaketails used to display the skins
     */
    private final List<SnakeTail> tails = new ArrayList<>();

    private final Settings settings;

    public Cosmetics(Settings settings) {

        this.settings = settings;

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

        setSkinUsedSkin(settings.getSkin());

    }

    /**
     * When frame is build, this method is called to display the lastly selected skin
     */
    private void setSkinUsedSkin(final Skins pUsedSkin) {
        //"default","blue","brown","black","red","gold","purple","gray"

        switch (pUsedSkin) {
            case DEFAULT, BROWN, BLACK, BLUE, RED, GOLD, PURPLE, GRAY ->
                    changeSkinColor(pUsedSkin.headColor, pUsedSkin.tailColor);
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

    /**
     * The listener for the button clicks
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton button) {

            if (button.getText().equalsIgnoreCase("Next")) {
                nextSkin(settings.getSkin());
            } else if (button.getText().equalsIgnoreCase("Last")) {
                lastSkin(settings.getSkin());
            } else if (button.getText().equalsIgnoreCase("Select skin")) {
                if (isUnlocked(settings.getSkin())) {
                    new SettingsManager().save(settings);
                }else {
                    frame.dispose();
                    JOptionPane.showMessageDialog(null, "Skin not unlocked", "Unlock skin at level " + sendUnlockMessage(settings.getSkin()), JOptionPane.WARNING_MESSAGE);
                    new Cosmetics(settings);
                }
                System.out.println(settings.getSkin());

            } else {
                frame.dispose();
                new SettingsFrame(settings);
            }

        }
    }

    private int sendUnlockMessage(final Skins pSkin) {

        int unlockAtLevel = 0;

        switch (pSkin){

            case DEFAULT -> unlockAtLevel = 1;
            case BLUE -> unlockAtLevel = 3;
            case BROWN -> unlockAtLevel = 5;
            case BLACK -> unlockAtLevel = 10;
            case RED -> unlockAtLevel = 15;
            case GOLD -> unlockAtLevel = 20;
            case PURPLE -> unlockAtLevel = 25;
            case GRAY -> unlockAtLevel = 30;

        }
        return unlockAtLevel;
    }

    private boolean isUnlocked(Skins pSkin) {

        boolean unlocked = false;
        
        switch (pSkin){

            case DEFAULT -> unlocked = settings.getUnlockedLevel() > 0;
            case BLUE -> unlocked = settings.getUnlockedLevel() > 2;
            case BROWN -> unlocked = settings.getUnlockedLevel() > 4;
            case BLACK -> unlocked = settings.getUnlockedLevel() > 9;
            case RED -> unlocked = settings.getUnlockedLevel() > 14;
            case GOLD -> unlocked = settings.getUnlockedLevel() > 19;
            case PURPLE -> unlocked = settings.getUnlockedLevel() > 24;
            case GRAY -> unlocked = settings.getUnlockedLevel() > 29;

        }
        return unlocked;
    }

    /**
     * On the LAST Button click. Chooses the skin before skin from the displayed skin and displays it
     */
    private void lastSkin(final Skins pActual) {
        //"default","blue","brown","black","red","gold","purple","gray"

        switch (pActual) {
            case DEFAULT -> {
                if (settings.getUnlockedLevel() > 29) {
                    //Ab lvl 30
                    settings.setSkin(Skins.GRAY);
                    changeSkinColor(Skins.GRAY.headColor, Skins.GRAY.tailColor);
                } else {
                    settings.setSkin(Skins.GRAY);
                    changeSkinColor(Skins.NOT_UNLOCKED.headColor, Skins.NOT_UNLOCKED.tailColor);
                }
            }
            case BLUE -> {
                if (settings.getUnlockedLevel() > 0) {
                    //Immer
                    settings.setSkin(Skins.DEFAULT);
                    changeSkinColor(Skins.DEFAULT.headColor, Skins.DEFAULT.tailColor);
                } else {
                    settings.setSkin(Skins.DEFAULT);
                    changeSkinColor(Skins.NOT_UNLOCKED.headColor, Skins.NOT_UNLOCKED.tailColor);
                }
            }
            case BROWN -> {
                if (settings.getUnlockedLevel() > 2) {
                    //Ab lvl 3
                    settings.setSkin(Skins.BLUE);
                    changeSkinColor(Skins.BLUE.headColor, Skins.BLUE.tailColor);
                } else {
                    settings.setSkin(Skins.BLUE);
                    changeSkinColor(Skins.NOT_UNLOCKED.headColor, Skins.NOT_UNLOCKED.tailColor);
                }
            }
            case BLACK -> {
                if (settings.getUnlockedLevel() > 4) {
                    //Ab lvl 5
                    settings.setSkin(Skins.BROWN);
                    changeSkinColor(Skins.BROWN.headColor, Skins.BROWN.tailColor);
                } else {
                    settings.setSkin(Skins.BROWN);
                    changeSkinColor(Skins.NOT_UNLOCKED.headColor, Skins.NOT_UNLOCKED.tailColor);
                }
            }
            case RED -> {
                if (settings.getUnlockedLevel() > 9) {
                    //Ab lvl 10
                    settings.setSkin(Skins.BLACK);
                    changeSkinColor(Skins.BLACK.headColor, Skins.BLACK.tailColor);
                } else {
                    settings.setSkin(Skins.BLACK);
                    changeSkinColor(Skins.NOT_UNLOCKED.headColor, Skins.NOT_UNLOCKED.tailColor);
                }
            }
            case GOLD -> {
                if (settings.getUnlockedLevel() > 14) {
                    //Ab lvl 15
                    settings.setSkin(Skins.RED);
                    changeSkinColor(Skins.RED.headColor, Skins.RED.tailColor);
                } else {
                    settings.setSkin(Skins.RED);
                    changeSkinColor(Skins.NOT_UNLOCKED.headColor, Skins.NOT_UNLOCKED.tailColor);
                }
            }
            case PURPLE -> {
                if (settings.getUnlockedLevel() > 19) {
                    //Ab lvl 20
                    settings.setSkin(Skins.GOLD);
                    changeSkinColor(Skins.GOLD.headColor, Skins.GOLD.tailColor);
                } else {
                    settings.setSkin(Skins.GOLD);
                    changeSkinColor(Skins.NOT_UNLOCKED.headColor, Skins.NOT_UNLOCKED.tailColor);
                }
            }
            case GRAY -> {
                if (settings.getUnlockedLevel() > 24) {
                    //Ab lvl 25
                    settings.setSkin(Skins.PURPLE);
                    changeSkinColor(Skins.PURPLE.headColor, Skins.PURPLE.tailColor);
                } else {
                    settings.setSkin(Skins.PURPLE);
                    changeSkinColor(Skins.NOT_UNLOCKED.headColor, Skins.NOT_UNLOCKED.tailColor);
                }
            }
        }

    }


    /**
     * On the NEXT Button click. Chooses the next skin from the displayed skin and displays it
     */
    private void nextSkin(final Skins pActual) {

        //"default","blue","brown","black","red","gold","purple","gray"

        switch (pActual) {
            case DEFAULT -> {
                if (settings.getUnlockedLevel() > 2) {
                    //Ab lvl 3
                    settings.setSkin(Skins.BLUE);
                    changeSkinColor(Skins.BLUE.headColor, Skins.BLUE.tailColor);
                } else {
                    settings.setSkin(Skins.BLUE);
                    changeSkinColor(Skins.NOT_UNLOCKED.headColor, Skins.NOT_UNLOCKED.tailColor);
                }
            }
            case BLUE -> {
                if (settings.getUnlockedLevel() > 4) {
                    //Ab lvl 5
                    settings.setSkin(Skins.BROWN);
                    changeSkinColor(Skins.BROWN.headColor, Skins.BROWN.tailColor);
                } else {
                    settings.setSkin(Skins.BROWN);
                    changeSkinColor(Skins.NOT_UNLOCKED.headColor, Skins.NOT_UNLOCKED.tailColor);
                }
            }
            case BROWN -> {
                if (settings.getUnlockedLevel() > 9) {
                    //Ab lvl 10
                    settings.setSkin(Skins.BLACK);
                    changeSkinColor(Skins.BLACK.headColor, Skins.BLACK.tailColor);
                } else {
                    settings.setSkin(Skins.BLACK);
                    changeSkinColor(Skins.NOT_UNLOCKED.headColor, Skins.NOT_UNLOCKED.tailColor);
                }
            }
            case BLACK -> {
                if (settings.getUnlockedLevel() > 14) {
                    //Ab lvl 15
                    settings.setSkin(Skins.RED);
                    changeSkinColor(Skins.RED.headColor, Skins.RED.tailColor);
                } else {
                    settings.setSkin(Skins.RED);
                    changeSkinColor(Skins.NOT_UNLOCKED.headColor, Skins.NOT_UNLOCKED.tailColor);
                }
            }
            case RED -> {
                if (settings.getUnlockedLevel() > 19) {
                    //Ab lvl 20
                    settings.setSkin(Skins.GOLD);
                    changeSkinColor(Skins.GOLD.headColor, Skins.GOLD.tailColor);
                } else {
                    settings.setSkin(Skins.GOLD);
                    changeSkinColor(Skins.NOT_UNLOCKED.headColor, Skins.NOT_UNLOCKED.tailColor);
                }
            }
            case GOLD -> {
                if (settings.getUnlockedLevel() > 24) {
                    //Ab lvl 25
                    settings.setSkin(Skins.PURPLE);
                    changeSkinColor(Skins.PURPLE.headColor, Skins.PURPLE.tailColor);
                } else {
                    settings.setSkin(Skins.PURPLE);
                    changeSkinColor(Skins.NOT_UNLOCKED.headColor, Skins.NOT_UNLOCKED.tailColor);
                }
            }
            case PURPLE -> {
                if (settings.getUnlockedLevel() > 29) {
                    //Ab lvl 30
                    settings.setSkin(Skins.GRAY);
                    changeSkinColor(Skins.GRAY.headColor, Skins.GRAY.tailColor);
                } else {
                    settings.setSkin(Skins.GRAY);
                    changeSkinColor(Skins.NOT_UNLOCKED.headColor, Skins.NOT_UNLOCKED.tailColor);
                }
            }
            case GRAY -> {
                if (settings.getUnlockedLevel() > 0) {
                    //Immer
                    settings.setSkin(Skins.DEFAULT);
                    changeSkinColor(Skins.DEFAULT.headColor, Skins.DEFAULT.tailColor);
                } else {
                    settings.setSkin(Skins.DEFAULT);
                    changeSkinColor(Skins.NOT_UNLOCKED.headColor, Skins.NOT_UNLOCKED.tailColor);
                }
            }
        }
    }

    /**
     * Changes the color of the skin depending on what color is given
     */
    private void changeSkinColor(final Color pColor, final Color pHead) {

        head.setBackground(pHead);

        for (SnakeTail tail : tails) {
            tail.setBackground(pColor);
        }

    }
}
