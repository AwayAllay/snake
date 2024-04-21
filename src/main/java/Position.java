public class Position {

    private int frameXPosition;
    private int frameYPosition;

    public Position(int fieldX, int fieldY) {
        this.frameXPosition = fieldX;
        this.frameYPosition = fieldY;
    }

    public int getFrameXPosition() {
        return frameXPosition;
    }

    public int getFrameYPosition() {
        return frameYPosition;
    }

    public int getFieldX() {
        return  frameXPosition / GameFrame.FIELD_WIDTH_PX;
    }

    public int getFieldY() {
        return frameYPosition / GameFrame.FIELD_HEIGHT_PX + 60;
    }

}
