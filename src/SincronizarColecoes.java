import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class SincronizarColecoes {

    private static List<String> lista = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        lista = Collections.synchronizedList(lista);
        MeuRunnable runnable = new MeuRunnable();

        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        t0.start();
        t1.start();
        t2.start();
        Thread.sleep(1);
        System.out.println(lista);
    }

    public static class MeuRunnable implements Runnable {

        @Override
        public void run() {
            lista.add("Batata");
            String name = Thread.currentThread().getName();
            System.out.println(name + " inseriu na lista!");
        }
    }
}
