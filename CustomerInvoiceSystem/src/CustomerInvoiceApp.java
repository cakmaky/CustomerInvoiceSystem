import java.util.*;
import java.sql.*;
public class CustomerInvoiceApp {

	private static Connection connection = null;
	
	public static void main(String[] args) {
	
		System.out.println("Welcome to the Customer Invoices Report\n");
		System.out.println("Email Adress    \tInvoice Number  Invoice Date \tInvoice Total");
		
		connection = connect();
		printResult();
	}
	private static Connection connect()
    {
        try
        {
        	 String dbDirectory = "/Users/yc/Documents/workspace/JavaCompHw_7";
        	 System.setProperty("derby.system.home", dbDirectory);
        	 
        	 String dbUrl = "jdbc:derby:BineetDB";
        	 
        	 String username = "";
             String password = "";
             Connection connection = DriverManager.getConnection(dbUrl, username, password);
             
             return connection;
        	
        }
        catch(SQLException e)
        {
            e.printStackTrace();

            return null;
        }
    }
	 public static void printResult()
	    {
	        try
	        {
	        	Statement statement = connection.createStatement();
	        	ResultSet rs = statement.executeQuery("select emailaddress,invoicenumber, invoicedate, invoicetotal "
	        			+ "from customers, invoices "
	        			+ "order by emailaddress asc");

	        	while(rs.next())
	            {
	        		String email = rs.getString("EmailAddress");
	        		String invoiceNumber = rs.getString("InvoiceNumber");
	        		java.sql.Date date = rs.getDate("InvoiceDate");      //InvoiceDate DATE, 
	        		double invoiceTotal = rs.getDouble("InvoiceTotal");   //InvoiceTotal DOUBLE
	        		
	        		System.out.println(email + "\t" + invoiceNumber + "\t\t" + date + "\t" + invoiceTotal);
	            }
	        	rs.close();
	            statement.close();   
	      }
	        catch(SQLException e)
	        {
	            e.printStackTrace();  // for debugging
	        }
	    }
        
}

	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        