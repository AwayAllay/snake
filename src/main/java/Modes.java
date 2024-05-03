public enum Modes {
    NOOB(100, 1),
    BEGINNER(90, 1),
    ADULT(80, 1),
    MASTER(60, 2),
    GOD(50, 3);



    private int speed;
    private int modeMultiplier;


    Modes(int speed, int modeMultiplier) {
        this.speed = speed;
        this.modeMultiplier = modeMultiplier;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getModeMultiplier() {
        return modeMultiplier;
    }

    public void setModeMultiplier(int modeMultiplier) {
        this.modeMultiplier = modeMultiplier;
    }
}
