import java.util.ArrayList;
import java.util.*;
import java.sql.*;

public class CustomerDB {
	
	private ArrayList<Customer> customers;
	
	private static Connection connection ;
	
	private static int count; 
	
	public CustomerDB(){
		
		customers = new ArrayList<>();
		connection = connect();
		
	}
	public static void disconnect(){
		try
        {
            connection.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
	}
	private static Connection connect(){
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
	 public void readDB(){
	    
	        try
	        {
	        	customers.clear();
	        	Statement statement = connection.createStatement();
	        	ResultSet rs = statement.executeQuery("select emailaddress, firstname, lastname "
	        			+ "from customers "
	        			+ "order by emailaddress asc");
	        	Customer c = null;

	        	while(rs.next())
	            {
	        		String email = rs.getString("EmailAddress");
	        		String firstName = rs.getString("FirstName");
	        		String lastName = rs.getString("LastName");
	        		c = new Customer(email, firstName, lastName);
	        		customers.add(c);
	        	
	        		//System.out.println(email + "\t" + invoiceNumber + "\t\t" + date + "\t" + invoiceTotal);
	            }
	        	rs.close();
	            statement.close();   
	      }
	        catch(SQLException e)
	        {
	            e.printStackTrace();  // for debugging
	        }
	 }

	public ArrayList<Customer> getAllCustomers(){
		
		readDB();
		return customers;
		
	}
	public Customer getCustomer(String email){
		
		readDB();
		Customer c = new Customer("","","");
		for(int i=0; i<customers.size(); i++){
			
			if(customers.get(i).getEmail().equals(email)){
				
				c = customers.get(i);
			}
			
		}
		return c;
	}
	public void insertCustomer(Customer c){
		
		try
		{
				PreparedStatement ps = connection.prepareStatement(
	                "SELECT * FROM Customers WHERE EmailAddress = ?");
	            ps.setString(1, c.getEmail());
	            ResultSet rs = ps.executeQuery();
	            
	            if(rs.next())
	            {
	                System.out.println("This customer email already exists: " + c.getEmail());
	            }
	            else
	            {
	            
	            	String insertCustomer = 
	                       "INSERT INTO Customers" +       //INSERT INTO Customers
	                       "VALUES (?, ?, ?, ?)";         //VALUES (3, 'John', 'Smith', 'johnsmith@hotmail.com');
	            
	            	PreparedStatement ps2 = connection.prepareStatement(insertCustomer);
	            	
	            	int id = 11;
	            
	            	 ps2.setInt(1, id); 
	            	 ps2.setString(2, c.getEmail());
	                 ps2.setString(3, c.getFirstname());
	                 ps2.setString(4, c.getLastName());
	                 ps2.executeUpdate();
	            	ps2.executeQuery();
	                 ps2.close();
			}
	            rs.close();
	            ps.close();
		}
		catch(SQLException e)
        {
            e.printStackTrace();  // for debugging
        }	
	}
	public void deleteCustomer(Customer c){
		try
		{
			PreparedStatement ps = connection.prepareStatement(
					 "SELECT * FROM Customers WHERE EmailAddress = ?");
			ps.setString(1, c.getEmail());
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
            	String deleteCustomer = "Delete from Customers where EmailAddress = ?";
            	PreparedStatement ps2 = connection.prepareStatement(deleteCustomer);
            	ps2.setString(1, c.getEmail());
                ps2.executeUpdate();
                ps2.close();
            }
            else
            {
                System.out.println("No record matches this email adress: " + c.getEmail());
            }
            rs.close();
            ps.close();
		}
		catch(SQLException e)
        {
            e.printStackTrace();  // for debugging
        }
 			
		}
		
		
	}
	
	
	














