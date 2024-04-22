import java.awt.*;

public enum IngameBoost {

    /**Chance: 5%*/
    KEY_BOOST(new Color(250,250,50), 0, 0, 500),
    /**Chance: 30%*/
    REGULAR_BOOST(new Color(255, 0, 0), 2, 0, 50),
    /**Chance: 15%*/
    NICE_BOOST(new Color(50, 250, 0), 1, 0, 100),
    /**Chance: 3%*/
    MYTHICAL_BOOST(new Color(250, 50, 230), 5, 0, 300),
    /**Chance: 7%*/
    BAD_BOOST(new Color(0,0,0), 3, 0, 20),
    /**Chance: 10%*/
    HEALTH_BOOST(new Color(0, 255,255), 2,1, 200),
    /**Chance: 15%*/
    GOOD_BOOST(new Color(150, 255,150), 1, 0, 150),
    /**Chance: 1%*/
    GOD_BOOST(new Color(0, 0, 255), 6, 2, 1000),
    /**Chance: 4%*/
    LOSER_BOOST(new Color(51, 25, 0), 10, 0, 2);


    private Color boostColor;
    private int lengthBoost;
    private int healthBoost;
    private int points;

    IngameBoost(Color boostColor, int lengthBoost, int healthBoost, int points) {
        this.boostColor = boostColor;
        this.lengthBoost = lengthBoost;
        this.healthBoost = healthBoost;
        this.points = points;
    }

    public Color getBoostColor() {
        return boostColor;
    }

    public int getLengthBoost() {
        return lengthBoost;
    }

    public int getHealthBoost() {
        return healthBoost;
    }

    public int getPoints() {
        return points;
    }
}
