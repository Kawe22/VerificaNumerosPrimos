import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VerificaNumerosPrimos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a lista de números separados por espaço:");
        String inputNumbers = scanner.nextLine();
        scanner.close();

        String[] numbers = inputNumbers.split(" ");

        ExecutorService executor = Executors.newFixedThreadPool(numbers.length);

        for (String numStr : numbers) {
            int num = Integer.parseInt(numStr);
            Runnable task = new VerificaPrimoTask(num);
            executor.execute(task);
        }

        executor.shutdown();
    }

    static class VerificaPrimoTask implements Runnable {
        private final int num;

        public VerificaPrimoTask(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            if (isPrimo(num)) {
                System.out.println("O número " + num + " é primo.");
            } else {
                System.out.println("O número " + num + " não é primo.");
            }
        }

        private boolean isPrimo(int num) {
            if (num <= 1) {
                return false;
            }
            if (num <= 3) {
                return true;
            }
            if (num % 2 == 0 || num % 3 == 0) {
                return false;
            }
            for (int i = 5; i * i <= num; i += 6) {
                if (num % i == 0 || num % (i + 2) == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
