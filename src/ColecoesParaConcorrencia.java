import java.util.*;
import java.util.concurrent.*;

public class ColecoesParaConcorrencia {
    /*
    Thread safe porém não é muito performática pois cada operação de adiconar ou remover um item
    Necessita a copia do array inteiro.
    */
//    private static List<String> lista = new CopyOnWriteArrayList<>();

    /*
    Também perde desempenho pois parte do run precisa ser sincronizado o que leva a uma thread
    executar por vez.
    A vantagem é não ter que fazer isso na mão.
    */
//    private static Map<Integer, String> mapa = new ConcurrentHashMap<>() {

    private static LinkedBlockingQueue<String> fila = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
        MeuRunnable runnable = new MeuRunnable();
        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t0.start();
        t1.start();
        t2.start();

        Thread.sleep(500);
//        System.out.println(lista);
//        System.out.println(mapa);
        System.out.println(fila);
    }

    public static class MeuRunnable implements Runnable {

        @Override
        public void run() {
//            lista.add("Batata frita");
            fila.add("batata frita");
            String name = Thread.currentThread().getName();
            System.out.println(name + " inseriu na fila.");
        }
    }
}