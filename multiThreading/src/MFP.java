
public class MFP {
    private Object scanLock = new Object();
    private Object printLock = new Object();

    public static void main(String[] args) {
        MFP mfp = new MFP();
        Doc[] docsForScan = new Doc[5];
        Doc[] docsForPrint = new Doc[5];
        for (int i = 0; i < 5; i++) {
            docsForScan[i] = new Doc("Doc for Scan " + (i + 1), (int) (Math.random() * 10 + 5));
            docsForPrint[i] = new Doc("Doc for Print " + (i + 1), (int) (Math.random() * 10 + 3));
        }
        new Thread(() -> mfp.scan(docsForScan)).start();
        new Thread(() -> mfp.print(docsForPrint)).start();
    }

    public void scan(Doc[] doc) {
        synchronized (scanLock) {
            work(doc, "scanned");
        }
    }

    public void print(Doc[] doc) {
        synchronized (printLock) {
            work(doc, "printed");
        }
    }

    private void work(Doc[] doc, String string) {
        for (int j = 0; j < doc.length; j++) {
            for (int i = 0; i < doc[j].getPages(); i++) {
                System.out.println(Character.toUpperCase(string.charAt(0)) + string.substring(1) + " " + (i + 1) + " pages of document " + doc[j].getName());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(doc[j].getName() + " was completely  " + string);
        }
    }
}

class Doc {
    String name;
    int pages;

    public Doc(String name, int pages) {
        this.name = name;
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public int getPages() {
        return pages;
    }
}
