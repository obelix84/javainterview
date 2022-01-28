package pingpong;

public class PingPong implements Runnable{
    private static String status = "ping";
    private final String word;

    public PingPong(String word) {
        this.word = word;
    }


    @Override
    public void run() {
        while(!Thread.interrupted()) {
            synchronized (PingPong.class) {
                while (word.equals(status)) {
                    try {
                        PingPong.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                System.out.println(word);
                status = word;
                try {
                    Thread.sleep(500);
                    PingPong.class.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }

    }

    public static void main(String[] args) {
        new Thread(new PingPong("ping")).start();
        new Thread(new PingPong("pong")).start();
    }
}
