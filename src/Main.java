public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, This is Bingo Game.");

        UniqueRandomNumber drawer = new UniqueRandomNumber(1, 75);
        Card card = new Card();

        while(drawer.isDrawable() && !card.isFinished()) {
            int drawnBall = drawer.draw();
            card.update(drawnBall);
            System.out.println("Ball[" + drawer.getDrawnTimes() + "]: " + drawnBall);
            card.render();
        }
    }
}
