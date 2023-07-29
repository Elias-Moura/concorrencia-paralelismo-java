public class main {
    public static void main(String[] args) {

        // Thread atual
        Thread tMain = Thread.currentThread();
        System.out.println(tMain.getName());

        // Nova thread
        Thread t0 = new Thread(new MeuRunnable());
        // to.run(); // Apenas executando na mesma thread
        t0.start(); // Executando uma nova Thread


        // Runable com lambda
        Thread t1 = new Thread(
                () -> System.out.println("Batata Frita")
        );
        t1.start();
        // t1.start(); // Não posso pedir para iniciar a mesma thread pois lança exceção.

    }
}
