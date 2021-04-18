public class Cell {
    public static final int FREE = -1;

    private int number;
    private boolean isPunched;

    public Cell(int number) {
        this.number = number;
        this.isPunched = false;

        if (number == FREE) {
            this.isPunched = true;
        }
    }

    public String getRenderString() {
        if (number == FREE) {
            return "FREE";
        } else if (isPunched) {
            return "(" + String.format("%2d", number) + ")";
        } else {
            return " " + String.format("%2d", number) + " ";
        }
    }

    public void punch(int number) {
        if (this.number == number) {
            isPunched = true;
        }
    }

    public boolean isPunched() {
        return isPunched;
    }
}
