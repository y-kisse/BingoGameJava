import java.util.ArrayList;

public class Card {
    private final ArrayList<ArrayList<Cell>> cells;
    private int bingoLines;
    private int oneLeftLines;
    private static final int CARDSIZE = 5;
    private static final int CARDCENTER = (CARDSIZE - 1) / 2;

    public Card() {
        cells = new ArrayList<ArrayList<Cell>>();

        // とりあえず全部 FREE セルで初期化
        for (int i = 0; i < CARDSIZE; i++) {
            ArrayList<Cell> c = new ArrayList<>();
            for (int j = 0; j < CARDSIZE; j++) {
                c.add(new Cell(Cell.FREE));
            }
            cells.add(c);
        }

        // ランダムな数で update
        // 列ごとに入る値の制約を掛ける
        for (int colIndex = 0; colIndex < CARDSIZE; colIndex++) {
            UniqueRandomNumber drawer = new UniqueRandomNumber(colIndex * 15 + 1, (colIndex + 1) * 15);
            for (int rowIndex = 0; rowIndex < CARDSIZE; rowIndex++) {
                cells.get(rowIndex).set(colIndex, new Cell(drawer.draw()));
            }
        }

        cells.get(CARDCENTER).set(CARDCENTER, new Cell(Cell.FREE));

        bingoLines = 0;
        oneLeftLines = 0;
    }

    public void update(int number) {
        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            for (int colIndex = 0; colIndex < 5; colIndex++) {
                cells.get(rowIndex).get(colIndex).punch(number);
            }
        }

        searchLines();
    }

    public void searchLines() {
        bingoLines = 0;
        oneLeftLines = 0;

        for (int rowIndex = 0; rowIndex < CARDSIZE; rowIndex++) {
            int count = 0;
            for (int colIndex = 0; colIndex < CARDSIZE; colIndex++) {
                if (cells.get(rowIndex).get(colIndex).isPunched()) {
                    count++;
                }
            }
            if (count == 4) {
                oneLeftLines++;
            } else if (count == 5) {
                bingoLines++;
            }
        }

        for (int colIndex = 0; colIndex < CARDSIZE; colIndex++) {
            int count = 0;
            for (int rowIndex = 0; rowIndex < CARDSIZE; rowIndex++) {
                if (cells.get(rowIndex).get(colIndex).isPunched()) {
                    count++;
                }
            }

            if (count == 4) {
                oneLeftLines++;
            } else if (count == 5) {
                bingoLines++;
            }
        }

        int count = 0;
        for (int index = 0; index < CARDSIZE; index++) {
            if (cells.get(index).get(index).isPunched()) {
                count++;
            }
        }

        if (count == 4) {
            oneLeftLines++;
        } else if (count == 5) {
            bingoLines++;
        }

        count = 0;
        for (int index = 0; index < CARDSIZE; index++) {
            if (cells.get(index).get(CARDSIZE - 1 - index).isPunched()) {
                count++;
            }
        }
        if (count == 4) {
            oneLeftLines++;
        } else if (count == 5) {
            bingoLines++;
        }
    }

    public void render() {
        for (int rowIndex = 0; rowIndex < CARDSIZE; rowIndex++) {
            for (int colIndex = 0; colIndex < CARDSIZE; colIndex++) {
                System.out.print(cells.get(rowIndex).get(colIndex).getRenderString());
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("OneLeft:" + oneLeftLines);
        System.out.println("BINGO! :" + bingoLines);
        System.out.println("--------------------");
    }

    public boolean isFinished() {
        if (bingoLines == 12) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("This is Card class sample!");
        Card card = new Card();
        card.render();

        for (int i = 0; i < 10; i++) {
            card.update(i);
            card.render();
        }
    }
}
