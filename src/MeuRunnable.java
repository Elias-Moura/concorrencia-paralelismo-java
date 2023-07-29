public class MeuRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Olá mundo.");
        System.out.println(Thread.currentThread().getName());
    }
}
