import java.sql.*;
import java.util.Scanner;

public class Main {
    static Connection connection;
    static Statement stmt;
    static PreparedStatement ps;

    public static void main(String[] args) {
        try {
            connect();
            //createTableEx();
            // clearTable();
            // batchEx();
            console();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
        ps = connection.prepareStatement("INSERT INTO products (prodid, title, cost) VALUES (?, ?, ?);");
    }

    public static void disconnect() {
        try {
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTableEx() throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS products (" +
                "    id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    prodid INTEGER,\n" +
                "    title  TEXT,\n" +
                "    cost INTEGER\n" +
                ");"
        );
    }

    public static void clearTable() throws SQLException {
        stmt.executeUpdate("DELETE FROM products;");
        stmt.executeUpdate("DELETE FROM sqlite_sequence where name='products'");
    }

    public static void batchEx() throws SQLException {
        connection.setAutoCommit(false);
        for (int i = 1; i <= 10000; i++) {
            ps.setInt(1, i);
            ps.setString(2, "product" + i);
            ps.setInt(3, 10 * i);
            ps.addBatch();
        }
        ps.executeBatch();
        connection.setAutoCommit(true);
    }

    public static void findEx(String name) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT title, cost FROM products Where title = ?;");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println(rs.getString(1) + "  cost: " + rs.getInt(2));
        } else System.out.println("Product not found");
    }

    public static void updateEx(String name, int cost) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("Update products SET cost =? Where title = ?;");
        ps.setString(2, name);
        ps.setInt(1, cost);
        ps.executeUpdate();
    }

    public static void selectEx(int from, int to) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT title, cost FROM products Where cost >= ? AND cost <=?;");
        ps.setInt(1, from);
        ps.setInt(2, to);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getInt(2));
        }
    }

    public static void console() throws SQLException {
        System.out.println("Available commands: /cost <name>, /newcost <name> <newcost>, /costfromto <from> <to>, /end");
        Scanner sc = new Scanner(System.in);
        String fail = "Command not found";
        String nCommand = "Input new command";
        String som[];
        while (sc.hasNext()) {
            som = sc.nextLine().split(" ");
            if (som[0].equals("/cost") && som.length > 1) {
                findEx(som[1]);
                System.out.println(nCommand);
            } else if (som[0].equals("/newcost") && som.length > 1) {
                updateEx(som[1], Integer.parseInt(som[2]));
                System.out.println("Cost for " + som[1] + " changed");
                System.out.println(nCommand);
            } else if (som[0].equals("/costfromto") && som.length > 2) {
                if (Integer.parseInt(som[2]) < Integer.parseInt(som[1])) System.out.println("Wrong interval");else
                selectEx(Integer.parseInt(som[1]), Integer.parseInt(som[2]));
                System.out.println(nCommand);
            } else if (som[0].equals("/end")) {
                sc.close();
                break;
            } else {
                System.out.println(fail);
                System.out.println(nCommand);
            }
        }
    }
}
