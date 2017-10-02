import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //  readInBuff("test.txt");
        //  combineFiles("123");
        consoleReader("big3.txt");
    }

    private static void readInBuff(String PATH) {
        try {
            byte[] buff = new byte[1024];
            FileInputStream in = new FileInputStream(PATH);
            int x = -1;
            while ((x = in.read(buff)) != -1) {
                System.out.println(new String(buff));
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void combineFiles(String source) {
        try {
            File fi = new File(source);
            ArrayList<InputStream> al = new ArrayList<>();
            for (int i = 0; i < fi.listFiles().length; i++) {
                al.add(new FileInputStream(fi.listFiles()[i]));
            }
            SequenceInputStream seq = new SequenceInputStream(Collections.enumeration(al));
            FileOutputStream out = new FileOutputStream("result.txt");
            int rb = seq.read();
            while (rb != -1) {
                out.write(rb);
                rb = seq.read();
            }
            seq.close();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private static void consoleReader(String source) {
        Scanner sc = new Scanner(System.in);
        File fi = new File(source);
        int pages = (int) fi.length() % 1800 == 0 ? (int) fi.length() / 1800 : (int) fi.length() / 1800 + 1;
        System.out.println("All pages is " + pages +
                "\nEnter page");
        try {
            RandomAccessFile raf = new RandomAccessFile(source, "r");
            while (true) {
                String page = sc.next();
                if (page.equals("end")) break;
                raf.seek((Integer.parseInt(page) - 1) * 1800);
                int x = -1;
                byte[] buff = new byte[1800];
                if ((x = raf.read(buff)) != -1) {
                    System.out.println(new String(buff));
                }
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
