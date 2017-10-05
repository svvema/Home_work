import java.io.*;

public class Main {
    static Object monitor = new Object();
    static char currentLetter = 'A';
    static int currentStream = 1;
    static PrintWriter fi = null;

    public static void main(String[] args) {
//        ABC();
//        writeInFile("1.txt");
    }

    private static void writeInFile(String file) {
        Thread[] th = new Thread[3];
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < 3; i++) {
                int finalI = i;
                th[i] = new Thread() {
                    @Override
                    public void run() {
                        try {
                            for (int i = 0; i < 10; i++) {
                                bw.write("Thread " + finalI + "\n");
                                bw.flush();
                                try {
                                    Thread.sleep(20);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                th[i].start();
                th[i].join();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void ABC() {
        Thread a = new Thread(() -> {

            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor) {
                        while (currentLetter != 'A') monitor.wait();

                        System.out.print('A');
                        currentLetter = 'B';
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        a.start();
        Thread b = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor) {
                        while (currentLetter != 'B') monitor.wait();

                        System.out.print('B');
                        currentLetter = 'C';
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        b.start();
        Thread c = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor) {
                        while (currentLetter != 'C') monitor.wait();

                        System.out.print('C');
                        currentLetter = 'A';
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        c.start();
    }

}
