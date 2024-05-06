public enum Achievement {
    NOOB("Noob!", "Die at level 1"),
    WHATS_WINDING_THERE("What is winding there?", "Play for the first time"),
    BEGINNER("Beginner", "Play BEGINNER mode"),
    GOT_THEM_ALL("Got them all!", "Collect every boost in one round"),
    SMELLS_FAMILIAR("Smells familiar...", "Die by hitting yourself"),
    SKIN_ENTHUSIAST("Skin enthusiast", "Collect every skin"),
    YUMMY("Yummy!", "Eat a boost for the first time"),
    IAM_GROWING_UP("I´m growing up!", "Play ADULT mode"),
    PARENT("Parent..", "Reach level 20 in ADULT mode"),
    MASTER("Master", "PLay MASTER mode"),
    DEMIGOD("Demigod", "Play GOD mode"),
    SERPENT_GOD("Serpent god", "Reach level 20 in GOD mode"),
    DIVINITY("Divinity", "Reach the final level in GOD mode"),
    THE_LONGEST_OF_THEM_ALL("The longest of them all", "Contain 100 snake tails"),
    COLLECTOR("Collector", "Collect 3000 points"),
    COLLECTING_MASTER("Collecting-master", "Collect 10000 points"),
    COLLECTING_GOD("Collecting-god", "Collect 20000 points"),
    COLLECTING_ADDICT("Collecting-addict", "Collect 50000 points"),
    I_HAVE_NO_LIFE("I have no life..", "Collect 100000 points"),
    GETTING_BETTER("Getting better:)", "Reach level 20 in BEGINNER mode"),
    EXPLOIT("Exploit?", "Collect somehow 200000 points");


    private String name;
    private String description;
    private boolean isCollected;

    Achievement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
