import java.util.ArrayList;
import java.util.*;

public class CustomerMaintApp {
	
	public static void showMenu(){
		System.out.println("list\t-List all customers");
		System.out.println("add\t-Add a customers");
		System.out.println("del\t-Delete a customers");
		System.out.println("help\t-Show this menu");
		System.out.println("exit\t-exit this application");
		
	
	}
	
	public static void main(String args[]){
		
		
		CustomerDB db = new CustomerDB();
		ArrayList<Customer> customers = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the Customer Maintenance application");
		showMenu();
		
		System.out.print("Enter a command: ");
		String choice= sc.nextLine();
		
		while(!choice.equals("exit")){
			if(choice.equals("list")){
				customers = db.getAllCustomers();
				System.out.println("CUSTOMER LIST");
				for(Customer c: customers){
					System.out.println(c.getEmail() + "\t" + c.getFirstname() + "\t" + c.getLastName());
				}	
			}
			else if(choice.equals("add")){
				
				System.out.print("Enter customer email address: ");
				String email = sc.nextLine();
				System.out.print("Enter customer first name: ");
				String firstName = sc.nextLine();
				System.out.print("Enter customer last name: ");
				String lastName = sc.nextLine();
				Customer c = new Customer(email, firstName, lastName);
				
				db.insertCustomer(c);
				
				System.out.println(firstName + " " + lastName + " added to the database");
				
			}
			else if(choice.equals("del")){
				System.out.print("Enter customer email address to delete: ");
				String email = sc.nextLine();
				
				for(Customer c: customers){
					if(c.getEmail().equals(email)){
						db.deleteCustomer(c);
						System.out.println(c.getFirstname() + " " + c.getLastName() + " was deleted from the database.");
					}
				}
			
				
			}
			
			else if(choice.equals("help")){
				showMenu();
			}
		
			System.out.print("Enter a command: ");
			choice= sc.nextLine();
			
		}
		
		System.out.println("Program terminated!!");
		db.disconnect();
		
	}

}
