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
        //TODO IMPORTANT FOR NEW SKINS
        switch (pUsedSkin) {
            case DEFAULT, BROWN, BLACK, BLUE, RED, GOLD, PURPLE, GRAY, BLACK_YELLOW, DEEP_PURPLE, BLUE_LIGHTPURPLE, BLUE_SKINCOLOR, GOD -> {
                changeSkinColor(pUsedSkin.getTailColor(), pUsedSkin.getHeadColor());
                selectedSkin = pUsedSkin;
                select.setText("Currently selected");
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
                select.setText("Currently selected");
                selectedSkin = settings.getSkin();
                new SettingsManager().save(settings);
            } else if (button.getText().equalsIgnoreCase("Back")) {
                frame.dispose();
                new SettingsFrame(settings, gameStuff);
            }

        }
    }


    /**On the NEXT Button click, this calls the skinStuff method with the next skin depending on the current one.
     * If you want to add a new skin: case >>YOUR SKIN<< -> skinStuff(The skin which is the next in the Skin row from your skin)*/
    private void nextSkin(final Skins pActual) {

        //"default","blue","brown","black","red","gold","purple","gray", "BLACK_YELLOW", "deeppurple", "bluelightpurple", blueskincolor", "god"

        //TODO IMPORTANT FOR NEW SKIN

        switch (pActual) {
            case DEFAULT -> skinStuff(Skins.BLUE);
            case BLUE -> skinStuff(Skins.BROWN);
            case BROWN -> skinStuff(Skins.BLACK);
            case BLACK -> skinStuff(Skins.RED);
            case RED -> skinStuff(Skins.GOLD);
            case GOLD -> skinStuff(Skins.PURPLE);
            case PURPLE -> skinStuff(Skins.GRAY);
            case GRAY -> skinStuff(Skins.BLACK_YELLOW);
            case BLACK_YELLOW -> skinStuff(Skins.DEEP_PURPLE);
            case DEEP_PURPLE -> skinStuff(Skins.BLUE_LIGHTPURPLE);
            case BLUE_LIGHTPURPLE -> skinStuff(Skins.BLUE_SKINCOLOR);
            case BLUE_SKINCOLOR -> skinStuff(Skins.GOD);
            case GOD -> skinStuff(Skins.DEFAULT);
        }
    }
    

    /**
     * On the LAST Button click, this calls the skinStuff method with the last skin depending on the current skin.
     * If you want to add a new skin: case >>YOUR SKIN<< -> skinStuff(The skin which is the skin behind  from your skin in the Skin row)*/
    private void lastSkin(final Skins pActual) {
        //"default","blue","brown","black","red","gold","purple","gray", "BLACK_YELLOW", "deeppurple", "bluelightpurple", blueskincolor", "god"

        //TODO IMPORTANT FOR NEW SKIN

        switch (pActual) {
            case DEFAULT -> skinStuff(Skins.GOD);
            case BLUE -> skinStuff(Skins.DEFAULT);
            case BROWN -> skinStuff(Skins.BLUE);
            case BLACK -> skinStuff(Skins.BROWN);
            case RED -> skinStuff(Skins.BLACK);
            case GOLD -> skinStuff(Skins.RED);
            case PURPLE -> skinStuff(Skins.GOLD);
            case GRAY -> skinStuff(Skins.PURPLE);
            case BLACK_YELLOW -> skinStuff(Skins.GRAY);
            case DEEP_PURPLE -> skinStuff(Skins.BLACK_YELLOW);
            case BLUE_LIGHTPURPLE -> skinStuff(Skins.DEEP_PURPLE);
            case BLUE_SKINCOLOR -> skinStuff(Skins.BLUE_LIGHTPURPLE);
            case GOD -> skinStuff(Skins.BLUE_SKINCOLOR);
        }

    }
    
    
    /**Replaced the old 280 lines of case statements. When called it will set the settings, displayed skin to the given skin, or
     * display a not unlocked skin if the skin is not unlocked yet.*/
    private void skinStuff(Skins skin) {

        settings.setSkin(skin);
        if (settings.getUnlockedLevel() > skin.getUnlockNumber() - 1) {
            changeSkinColor(skin.getTailColor(), skin.getHeadColor());
            if (skin.equals(selectedSkin)) {
                select.setText("Currently selected");
            } else {
                select.setText("Select skin");
            }
        } else {
            changeSkinColor(Skins.NOT_UNLOCKED.getTailColor(), Skins.NOT_UNLOCKED.getHeadColor());
            select.setText("Unlock at level " + skin.getUnlockNumber());
        }

    }
    

    /**Changes the color of the skin depending on what color is given*/
    private void changeSkinColor(final Color pTail, final Color pHead) {

        head.setBackground(pHead);

        for (SnakeTail tail : tails) {
            tail.setBackground(pTail);
        }

    }
}
