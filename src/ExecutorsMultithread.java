import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ExecutorsMultithread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = null;
        try {
//            executor = Executors.newFixedThreadPool(6); // Cria e usa a quantidade de Threads que você passar
            // newCachedThreadPool Cria e usa Threadas sob demanda.
            // Isso pode ser um problema com muitas tarefas pois ele criará várias threads
            executor = Executors.newCachedThreadPool();

            List<Tarefa> lista = new ArrayList<>();
            for (long i = 0; i < 100; i++) {
                lista.add(new Tarefa());
            }

            List<Future<String>> list = executor.invokeAll(lista);
//            String s = executor.invokeAny(lista); // Retorna apenas uma saída.

            for (Future<String> future : list) {
                System.out.println(future.get());
            }
//            Future<String> f1 = executor.submit(new Tarefa());
//            Future<String> f2 = executor.submit(new Tarefa());
//            Future<String> f3 = executor.submit(new Tarefa());
//            System.out.println(f1.get());
//            System.out.println(f2.get());
//            System.out.println(f3.get());
            executor.shutdown();
        } catch (Exception e) {
            throw e;
        } finally {
            if (executor != null) {
                executor.shutdownNow();
            }
        }
    }

    public static class Tarefa implements Callable<String> {

        @Override
        public String call() throws Exception {
            String name = Thread.currentThread().getName();
            int nextInt = new Random().nextInt(1000);
            return name + ": " + "Pão de quejo " + nextInt;
        }
    }
}
