public enum Achievement {
    NOOB("Noob!", "Die at level 1", false),
    WHATS_WINDING_THERE("What is winding there?", "Play for the first time", false),
    BEGINNER("Beginner", "Play BEGINNER mode", false),
    GOT_THEM_ALL("Got them all!", "Collect every boost in one round", false),
    SMELLS_FAMILIAR("Smells familiar...", "Die by hitting yourself", false),
    SKIN_ENTHUSIAST("Skin enthusiast", "Collect every skin", false),
    YUMMY("Yummy!", "Eat a boost for the first time", false),
    IAM_GROWING_UP("I´m growing up!", "Play ADULT mode", false),
    PARENT("Parent..", "Reach level 20 in ADULT mode", false),
    MASTER("Master", "PLay MASTER mode", false),
    DEMIGOD("Demigod", "Play GOD mode", false),
    SERPENT_GOD("Serpent god", "Reach level 15 in GOD mode", false),
    DIVINITY("Divinity", "Reach the final level in GOD mode", false),
    THE_LONGEST_OF_THEM_ALL("The longest of them all", "Contain 100 snake tails", false),
    COLLECTOR("Collector", "Collect 3000 points", false),
    COLLECTING_MASTER("Collecting-master", "Collect 10000 points", false),
    COLLECTING_GOD("Collecting-god", "Collect 20000 points", false),
    COLLECTING_ADDICT("Collecting-addict", "Collect 50000 points", false),
    I_HAVE_NO_LIFE("I have no life..", "Collect 100000 points", false);


    private String name;
    private String description;
    private boolean isCollected;

    Achievement(String name, String description, boolean isCollected) {
        this.name = name;
        this.description = description;
        this.isCollected = isCollected;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
