import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The StateCapitalDatabase class contains the main() method, which interfaces with a mySQL database,
 * requests the name of a state from the user, and outputs the associated state capital if the search
 * results in a successful match.
 * @author Robert Dobson
 */
public class StateCapitalDatabase {

    /**
     * The main() method
     * @param args not used
     */
    public static void main(String[] args) {

        String state = null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n*** State/Capital Database Search ***\n");
        System.out.print("Please enter the name of a state to retrieve its associated capital: ");
        try {
            state = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/US_States", "Enter username here", "Enter password here");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select state_capital from unitedStates where state_name = '" + state + "'");
            System.out.print("\nThe capital of " + state + " is: ");
            while(rs.next())
                System.out.println(rs.getString(1));
            con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
