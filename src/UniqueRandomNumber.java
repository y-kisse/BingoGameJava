import java.util.ArrayList;
import java.util.Random;

public class UniqueRandomNumber {
    /**
     *
     */

    private ArrayList<Integer> stockedNumbers;
    private int minValue;
    private int maxValue;

    public UniqueRandomNumber(int number) {
        stockedNumbers = new ArrayList<Integer>();
        for (int i = 0; i < number; i++) {
            stockedNumbers.add(i);
        }
    }

    public UniqueRandomNumber(int min, int max) {
        stockedNumbers = new ArrayList<Integer>();
        for (int i = min; i <= max; i++) {
            stockedNumbers.add(i);
        }
    }

    public int draw() {
        Random random = new Random();
        int randomIndex = random.nextInt(stockedNumbers.size());
        int retVal = stockedNumbers.get(randomIndex);
        stockedNumbers.remove(randomIndex);
        return retVal;
    }

    public boolean isDrawable() {
        return stockedNumbers.size() > 0;
    }

    public static void main(String[] args) {
        System.out.println("This is UniqueRandomNumber sample!");

        UniqueRandomNumber drawer = new UniqueRandomNumber(1,10);

        while (drawer.isDrawable()) {
            System.out.println(drawer.draw());
        }
    }
}
