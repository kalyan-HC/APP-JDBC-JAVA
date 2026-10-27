
import java.sql.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@//localhost:1521/XEPDB1",
                "student", "Student123"
            );

            Statement st = con.createStatement();

            try {
                st.executeUpdate(
                    "CREATE TABLE students (" +
                    "id NUMBER PRIMARY KEY, " +
                    "name VARCHAR2(30), " +
                    "marks NUMBER)"
                );
                System.out.println("Table created.");
            } catch (SQLException e) {
                if (e.getErrorCode() != 955) {
                    throw e;
                }
            }

            st.close();

            System.out.print("Enter number of students (minimum 5): ");
            int n = sc.nextInt();

            if (n < 5) {
                System.out.println("Enter at least 5 students.");
                con.close();
                return;
            }

            String sql = "INSERT INTO students VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            for (int i = 1; i <= n; i++) {
                System.out.println("Enter ID, name and marks:");

                int id = sc.nextInt();
                String name = sc.next();
                int marks = sc.nextInt();

                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setInt(3, marks);

                ps.addBatch();
            }

            int[] result = ps.executeBatch();

            System.out.println("Records processed: " + result.length);

            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Insertion error: " + e.getMessage());
        }

        sc.close();
    }
}
