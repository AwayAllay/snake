public enum Levels {
    LEVEL1(1),
    LEVEL2(2),
    LEVEL3(3),
    LEVEL4(4),
    LEVEL5(5),
    LEVEL6(6),
    LEVEL7(7),
    LEVEL8(8),
    LEVEL9(9),
    LEVEL10(10),
    LEVEL11(11),
    LEVEL12(12),
    LEVEL13(13),
    LEVEL14(14),
    LEVEL15(15),
    LEVEL16(16),
    LEVEL17(17),
    LEVEL18(18),
    LEVEL19(19);

    private int levelNumber;

    Levels(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getLevelNumber() {
        return levelNumber;
    }
}
