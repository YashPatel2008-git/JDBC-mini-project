import java.util.Scanner;

import jdbc.bean.productbean;
import jdbc.dao.ProductDao;

public class testApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ProductDao pdao = new ProductDao();
		int choice , rowsaffected ;
		productbean pbean = null;
		
		int pid,quantity;
		String pname,category;
		double price ;
		while(true) {
			System.out.println("1) add product.");
			System.out.println("2) delete product.");
			System.out.println("3) update product.");
			System.out.println("4) show all product.");
			System.out.println("5) search product by pid.");
			System.out.println("6) search product by pname.");
			System.out.println("7) product sorting.");
			System.out.println("8) show low stock product.");
			System.out.println("9) count total product.");
			System.out.println("10) Exit");
		
			System.out.println("Enter your choice : ");
			choice = sc.nextInt();
			
			switch (choice) {
			
				case 1: System.out.println("Enter product id : ");
						pid = sc.nextInt();
						sc.nextLine();
						System.out.println("Enter product name : ");
						pname = sc.nextLine();
						System.out.println("Enter product Category : ");
						category = sc.nextLine();
						System.out.println("Enter product quantity : ");
						quantity = sc.nextInt();
						System.out.println("Enter product price : ");
						price = sc.nextInt();
						
						pbean = new productbean(pid, pname, category, quantity, price);
						
						rowsaffected = pdao.insertProduct(pbean);
						
						if(rowsaffected >0) {
							System.out.println("Product inserted successfully...");
						}else {
							System.out.println("Product not inserted...");
						}
						
					break;
				
				case 2: System.out.println("Enter product id which you want to delete : ");
						pid = sc.nextInt();
						
						rowsaffected = pdao.Deleteproduct(pid);
						if(rowsaffected >0) {
							System.out.println("Product deleted successfully...");
						}else {
							System.out.println("Product not deleted...");
						}
					break;
				
				case 3: System.out.println("Enter product id which you want to update : ");
						pid = sc.nextInt();
						sc.nextLine();
						System.out.println("Enter product name : ");
						pname = sc.nextLine();
						System.out.println("Enter product Category : ");
						category = sc.nextLine();
						System.out.println("Enter product quantity : ");
						quantity = sc.nextInt();
						System.out.println("Enter product price : ");
						price = sc.nextInt();
		
						pbean = new productbean(0, pname, category, quantity, price);
						
						rowsaffected = pdao.updateProduct(pbean , pid);
						
						if(rowsaffected >0) {
							System.out.println("Product Updated successfully...");
						}else {
							System.out.println("Product not updated...");
						}
					break;
				
				case 4: pdao.displayAllProduct();
					break;
				
				case 5: System.out.println("Enter product id which you want to search : ");
						pid = sc.nextInt();
						pdao.SearchById(pid);
					break;
				
				case 6: System.out.println("Enter product name which you want to search : ");
						sc.nextLine();
						pname = sc.nextLine();
						pdao.Searchbyname(pname);
					break;
				
				case 7: System.out.println("1) product name ascending \n2) Price ascending \n3) price descending");
						System.out.println("Enter your choise for sorting ");
						int choice1 = sc.nextInt();
						pdao.sortProducts(choice1);
						
					break;
				
				case 8: pdao.lowStockProduct();
					break;
			
				case 9: pdao.countProduct();
					break;
				
				case 10: return;
				
				default : 
					System.out.println("Incorrect choice "+ choice +". Plese enter valide choice...");
			
			}
		}
	}
}
