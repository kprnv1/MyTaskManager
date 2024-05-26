
import service.HistoryTest;
import service.Runner;

public class Main {
    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.start();

        HistoryTest historyTest = new HistoryTest();
//        historyTest.start();
    }
}