import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorsSingleThreadRunnable {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();
//            executor.execute(new Tarefa());
//            executor.execute(new Tarefa());
//            executor.execute(new Tarefa());
            Future<?> future = executor.submit(new ExecutorsSingleThreadCallable.Tarefa());
            System.out.println(future.isDone());
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw e;
        } finally {
            if (executor != null) {
//                executor.shutdown(); // Espera a tarefa terminar e mata o executor
                executor.shutdownNow(); // Mata o executor sem esperar a tarfa terminar
            }
        }

    }

    public static class Tarefa implements Runnable {

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + ": " + "Pão de quejo");
        }
    }
}
