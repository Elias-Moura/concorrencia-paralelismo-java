import java.util.Random;
import java.util.concurrent.*;

public class ExecutorsSingleThreadCallable {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();

            Future<String> future = executor.submit(new ExecutorsSingleThreadCallable.Tarefa());

            System.out.println(future.isDone());
//            System.out.println(future.get()); // get espera a tarefa finalizar
            System.out.println(future.get(5, TimeUnit.SECONDS)); // Podemos setar um time out
            System.out.println(future.isDone());

//            executor.shutdown();
//            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw e;
        } finally {
            if (executor != null) {
//                executor.shutdown(); // Espera a tarefa terminar e mata o executor
                executor.shutdownNow(); // Mata o executor sem esperar a tarfa terminar
            }
        }

    }

    public static class Tarefa implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(1000);
            String name = Thread.currentThread().getName();
            int nextInt = new Random().nextInt(1000);
            return name + ": " + "Pão de quejo " + nextInt;
        }
    }
}

