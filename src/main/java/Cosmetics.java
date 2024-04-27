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
    private final JButton select;

    /**
     * The list of the Snaketails used to display the skins
     */
    private final List<SnakeTail> tails = new ArrayList<>();
    private final GameStuff gameStuff;
    private Skins selectedSkin;
    private final Settings settings;

    public Cosmetics(final Settings settings, final GameStuff gameStuff) {

        this.settings = settings;
        this.gameStuff = gameStuff;

        frame = new JFrame("Skins");
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(139, 90, 43));
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

        select = new JButton("Select skin");
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
            case DEFAULT, BROWN, BLACK, BLUE, RED, GOLD, PURPLE, GRAY -> {
                changeSkinColor(pUsedSkin.getTailColor(), pUsedSkin.getHeadColor());
                selectedSkin = pUsedSkin;
            }

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
                new SettingsManager().save(settings);
                selectedSkin = settings.getSkin();
            } else if (button.getText().equalsIgnoreCase("Back")) {
                frame.dispose();
                new SettingsFrame(settings, gameStuff);
            }

        }
    }

    private int sendUnlockMessage(final Skins pSkin) {

        int unlockAtLevel = 0;

        switch (pSkin) {

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

    /**
     * On the LAST Button click. Chooses the skin before skin from the displayed skin and displays it
     */
    private void lastSkin(final Skins pActual) {
        //"default","blue","brown","black","red","gold","purple","gray"
        //TODO ADD SELECTED TEXT TO BUTTON VIA SELECTEDSKIN VARIABLE!!!

        switch (pActual) {
            case DEFAULT -> {
                settings.setSkin(Skins.GRAY);
                if (settings.getUnlockedLevel() > 29) {
                    //Ab lvl 30
                    changeSkinColor(Skins.GRAY.getTailColor(), Skins.GRAY.getHeadColor());
                    select.setText("Select skin");
                } else {
                    changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
                    select.setText("Unlock at level " + sendUnlockMessage(Skins.GRAY));
                }
            }
            case BLUE -> {
                settings.setSkin(Skins.DEFAULT);
                if (settings.getUnlockedLevel() > 0) {
                    //Immer
                    changeSkinColor(Skins.DEFAULT.getTailColor(), Skins.DEFAULT.getHeadColor());
                    select.setText("Select skin");
                } else {
                    changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
                    select.setText("Unlock at level " + sendUnlockMessage(Skins.DEFAULT));
                }
            }
            case BROWN -> {
                settings.setSkin(Skins.BLUE);
                if (settings.getUnlockedLevel() > 2) {
                    //Ab lvl 3
                    changeSkinColor(Skins.BLUE.getTailColor(), Skins.BLUE.getHeadColor());
                    select.setText("Select skin");
                } else {
                    changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
                    select.setText("Unlock at level " + sendUnlockMessage(Skins.BLUE));
                }
            }
            case BLACK -> {
                settings.setSkin(Skins.BROWN);
                if (settings.getUnlockedLevel() > 4) {
                    //Ab lvl 5
                    changeSkinColor(Skins.BROWN.getTailColor(), Skins.BROWN.getHeadColor());
                    select.setText("Select skin");
                } else {
                    changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
                    select.setText("Unlock at level " + sendUnlockMessage(Skins.BROWN));
                }
            }
            case RED -> {
                settings.setSkin(Skins.BLACK);
                if (settings.getUnlockedLevel() > 9) {
                    //Ab lvl 10
                    changeSkinColor(Skins.BLACK.getTailColor(), Skins.BLACK.getHeadColor());
                    select.setText("Select skin");
                } else {
                    changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
                    select.setText("Unlock at level " + sendUnlockMessage(Skins.BLACK));
                }
            }
            case GOLD -> {
                settings.setSkin(Skins.RED);
                if (settings.getUnlockedLevel() > 14) {
                    //Ab lvl 15
                    changeSkinColor(Skins.RED.getTailColor(), Skins.RED.getHeadColor());
                    select.setText("Select skin");
                } else {
                    changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
                    select.setText("Unlock at level " + sendUnlockMessage(Skins.RED));
                }
            }
            case PURPLE -> {
                settings.setSkin(Skins.GOLD);
                if (settings.getUnlockedLevel() > 19) {
                    //Ab lvl 20
                    changeSkinColor(Skins.GOLD.getTailColor(), Skins.GOLD.getHeadColor());
                    select.setText("Select skin");
                } else {
                    changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
                    select.setText("Unlock at level " + sendUnlockMessage(Skins.GOLD));
                }
            }
            case GRAY -> {
                settings.setSkin(Skins.PURPLE);
                if (settings.getUnlockedLevel() > 24) {
                    //Ab lvl 25
                    changeSkinColor(Skins.PURPLE.getTailColor(), Skins.PURPLE.getHeadColor());
                    select.setText("Select skin");
                } else {
                    changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
                    select.setText("Unlock at level " + sendUnlockMessage(Skins.PURPLE));
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
                settings.setSkin(Skins.BLUE);
                if (settings.getUnlockedLevel() > 2) {
                    //Ab lvl 3
                    changeSkinColor(Skins.BLUE.getTailColor(), Skins.BLUE.getHeadColor());
                    select.setText("Select skin");
                } else {
                    changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
                    select.setText("Unlock at level " + sendUnlockMessage(Skins.BLUE));
                }
            }
            case BLUE -> {
                settings.setSkin(Skins.BROWN);
                if (settings.getUnlockedLevel() > 4) {
                    //Ab lvl 5
                    changeSkinColor(Skins.BROWN.getTailColor(), Skins.BROWN.getHeadColor());
                    select.setText("Select skin");
                } else {
                    changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
                    select.setText("Unlock at level " + sendUnlockMessage(Skins.BROWN));
                }
            }
            case BROWN -> {
                settings.setSkin(Skins.BLACK);
                if (settings.getUnlockedLevel() > 9) {
                    //Ab lvl 10
                    changeSkinColor(Skins.BLACK.getTailColor(), Skins.BLACK.getHeadColor());
                    select.setText("Select skin");
                } else {
                    changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
                    select.setText("Unlock at level " + sendUnlockMessage(Skins.BLACK));
                }
            }
            case BLACK -> {
                settings.setSkin(Skins.RED);
                if (settings.getUnlockedLevel() > 14) {
                    //Ab lvl 15
                    changeSkinColor(Skins.RED.getTailColor(), Skins.RED.getHeadColor());
                    select.setText("Select skin");
                } else {
                    changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
                    select.setText("Unlock at level " + sendUnlockMessage(Skins.RED));
                }
            }
            case RED -> {
                settings.setSkin(Skins.GOLD);
                if (settings.getUnlockedLevel() > 19) {
                    //Ab lvl 20
                    changeSkinColor(Skins.GOLD.getTailColor(), Skins.GOLD.getHeadColor());
                    select.setText("Select skin");
                } else {
                    changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
                    select.setText("Unlock at level " + sendUnlockMessage(Skins.GOLD));
                }
            }
            case GOLD -> {
                settings.setSkin(Skins.PURPLE);
                if (settings.getUnlockedLevel() > 24) {
                    //Ab lvl 25
                    changeSkinColor(Skins.PURPLE.getTailColor(), Skins.PURPLE.getHeadColor());
                    select.setText("Select skin");
                } else {
                    changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
                    select.setText("Unlock at level " + sendUnlockMessage(Skins.PURPLE));
                }
            }
            case PURPLE -> {
                settings.setSkin(Skins.GRAY);
                if (settings.getUnlockedLevel() > 29) {
                    //Ab lvl 30
                    changeSkinColor(Skins.GRAY.getTailColor(), Skins.GRAY.getHeadColor());
                    select.setText("Select skin");
                } else {
                    changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
                    select.setText("Unlock at level " + sendUnlockMessage(Skins.GRAY));
                }
            }
            case GRAY -> {
                settings.setSkin(Skins.DEFAULT);
                if (settings.getUnlockedLevel() > 0) {
                    //Immer
                    changeSkinColor(Skins.DEFAULT.getTailColor(), Skins.DEFAULT.getHeadColor());
                    select.setText("Select skin");
                } else {
                    changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
                    select.setText("Unlock at level " + sendUnlockMessage(Skins.DEFAULT));
                }
            }
        }
    }

    /**
     * Changes the color of the skin depending on what color is given
     */
    private void changeSkinColor(final Color pTail, final Color pHead) {

        head.setBackground(pHead);

        for (SnakeTail tail : tails) {
            tail.setBackground(pTail);
        }

    }
}
