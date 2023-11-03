package projectcamerarentalapp;

import java.util.ArrayList;
import java.util.Scanner;

//Class to represent a Camera

class Camera {
	private int id;
	private String brand;
	private String model;
	private double price;
	private boolean available;

	// Constructor to initialize a Camera
	public Camera(int id, String brand, String model, double price, boolean available) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.available = available;
	}

	// Getters for Camera attributes
	public int getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public double getPrice() {
		return price;
	}

	public boolean isAvailable() {
		return available;
	}

	// Setter to update availability of the camera
	public void setAvailable(boolean available) {
		this.available = available;
	}
}

//Class to represent an USER
class Admin {
	private String username = "admin";
	private String password = "admin123";

	public boolean authenticate(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}
}

//Class to represent an USER
class User {
	private String username;
	private String password;
	private double walletBalance;
	private ArrayList<Camera> rentedCameras = new ArrayList<>();

	// Constructor to initialize a User
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.walletBalance = 0.0;
	}

	 // Getters and setters for User attributes
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}

	public ArrayList<Camera> getRentedCameras() {
		return rentedCameras;
	}
}

//Main class for the Camera Rental App
public class CameraRentalApp {
	private static final String y = null;
	private static ArrayList<Camera> cameraList = new ArrayList<>();
	private static Admin admin = new Admin();
	private static User currentUser;
	public static void main(String[] args) {

		initializeCameras();
		welcomepage();

	}
	
	// Method for the initial welcome screen
	private static void welcomepage()
	{
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		// Display welcome screen and options
		while (!exit) {
			System.out.format(" +-----+-----------+----------------+-----------+%n");
			System.out.println(" |       WELCOME TO THE CAMERA RENTAL APP       |");
			System.out.format(" +-----+-----------+----------------+-----------+%n");
			System.out.print(" \n");      //Console space generator
			System.out.print("DEVELOPED BY : RISHU KUMAR\n ");
			System.out.print(" \n");     //Console space generator
			System.out.print("PLEASE LOGIN TO CONTINUE :\n");
			System.out.println("1. ADMIN");
			System.out.println("2. USER");
			System.out.print("ENTER CHOICE : ");
			int choice = scanner.nextInt();
			scanner.nextLine();  

			 // Handle user choice
			switch (choice) {
			case 1:
				adminLogin(scanner);
				adminMenu(scanner);
				break;
			case 2:
				userLogin(scanner);
				userMenu(scanner);
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}

			System.out.print("Do you want to exit the application? (y/n): ");
			String input = scanner.nextLine();
			exit = input.equalsIgnoreCase("y");
		}

		scanner.close();
	}

	// Displays authentication
	private static void adminLogin(Scanner scanner) {
		System.out.print("ENTER USERNAME : ");
		String username = scanner.nextLine();
		System.out.print("ENTER PASSWORD : ");
		String password = scanner.nextLine();

		if (admin.authenticate(username, password)) {
			System.out.println("Login successful!");
		} else {
			System.out.println("Invalid username or password. Login failed!");
			System.exit(0);
		}
	}

	//Displays admin menu
	private static void adminMenu(Scanner scanner) {
		boolean backToMenu = false;

		while (!backToMenu) {
			System.out.println("\nADMIN MENU");
			System.out.println("1. ADD ");
			System.out.println("2. REMOVE");
			System.out.println("3. VIEW MY CAMERA");
			System.out.println("4. GO TO PREVIOUS MENU");
			System.out.print("ENTER CHOICE : ");
			int choice = scanner.nextInt();
			scanner.nextLine();  

			//Handles Admin Choice
			switch (choice) {
			case 1:
				addCamera(scanner);
				break;
			case 2:
				removeCamera(scanner);
				break;
			case 3:
				viewAllCameras();
				break;
			case 4:
				welcomepage();
				break;
			default:
				System.out.println("INVALID CHOICE! TRY AGAIN.");
			}
		}
	}


// Methods to add camera
	private static void addCamera(Scanner scanner) {
		System.out.print("ENTER CAMERA ID : ");
		int id = scanner.nextInt();
		scanner.nextLine();  
		System.out.print("ENTER CAMERA BARAND : ");
		String brand = scanner.nextLine();
		System.out.print("ENTER CAMERA MODEL : ");
		String model = scanner.nextLine();
		System.out.print("ENTER PRICE PER DAY : ");
		double price = scanner.nextDouble();
		scanner.nextLine();  
		Camera camera = new Camera(id, brand, model, price, true);
		cameraList.add(camera);
		System.out.println("YOUR CAMERA ADDED SUCCESSFULLY TO THE LIST!.");
	}

	//Method to remove camera
	private static void removeCamera(Scanner scanner) {
		System.out.print("ENTER CAMERA ID TO REMOVE : ");
		int id = scanner.nextInt();
		scanner.nextLine();  

		Camera cameraToRemove = null;
		for (Camera camera : cameraList) {
			if (camera.getId() == id) {
				cameraToRemove = camera;
				break;
			}
		}

		if (cameraToRemove != null) {
			cameraList.remove(cameraToRemove);
			System.out.println("REMOVED CAMERA!.");
		} else {
			System.out.println("CAMERA NOT FOUND!.");
		}
	}

	//Method to view all camera
	private static void viewAllCameras() {
		System.out.println("\nAvailable Cameras:");
		System.out.println("===================================================================");
		System.out.println("ID\t\tBrand\t\tModel\t\tPrice\t\t");
		System.out.println("===================================================================");

		for (Camera camera : cameraList) {

			if (camera.isAvailable()) {
				System.out.println(camera.getId()+"\t\t" + camera.getBrand() +"\t\t" + camera.getModel()+"\t\t"  + camera.getPrice()+"\t\t" );
			}
		}
		System.out.println("===================================================================");


		System.out.println("\nRented Cameras:");

		System.out.println("===================================================================");
		System.out.println("ID\t\tBrand\t\tModel\t\tPrice\t\t");
		System.out.println("===================================================================");

		for (Camera camera : cameraList) {

			if (!camera.isAvailable()) {
				System.out.println(camera.getId()+"\t\t" + camera.getBrand() +"\t\t" + camera.getModel()+"\t\t"  + camera.getPrice()+"\t\t" );
			}
		}
		System.out.println("===================================================================");

	}

	//User authentication
	private static void userLogin(Scanner scanner) {
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		currentUser = new User(username, password);
		System.out.println("Login successful!");
	}

	//Displays user menu
	private static void userMenu(Scanner scanner) {
		boolean backToMenu = false;

		while (!backToMenu) {
			System.out.println("\nUser Main Menu");
			System.out.println("1. My Cameras");
			System.out.println("2. Rent a Camera");
			System.out.println("3. View All Cameras");
			System.out.println("4. My Wallet");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); 

			//Handles user choice
			switch (choice) {
			case 1:
				viewAllCameras();
				
				break;
			case 2:
				viewmyCameras();
				rentCamera(scanner);
				viewRentedCameras();
				break;
			case 3:
				viewAllCameras();
				break;
			case 4:
				myWallet(scanner);
				break;
			case 5:
				welcomepage();
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
	
	//Method to view my camera
	private static void viewmyCameras()
	{
		System.out.println("===================================================================");
		System.out.println("ID\t\tBrand\t\tModel\t\tPrice\t\t");
		System.out.println("===================================================================");

		for (Camera camera : cameraList) {

			if (camera.isAvailable()) {
				System.out.println(camera.getId()+"\t\t" + camera.getBrand() +"\t\t" + camera.getModel()+"\t\t"  + camera.getPrice()+"\t\t" );
			}
		}
		System.out.println("===================================================================");


	}
	
	// Method to view rented camera
	private static void viewRentedCameras() {
		ArrayList<Camera> rentedCameras = currentUser.getRentedCameras();
		if (rentedCameras.isEmpty()) {
			System.out.println("You haven't rented any cameras yet.");
		} else {
			System.out.println("\nRented Cameras:");
			System.out.println("===================================================================");
			System.out.println("ID\t\tBrand\t\tModel\t\tPrice\t\t");
			System.out.println("===================================================================");
			for (Camera camera : rentedCameras) {
				System.out.println(camera.getId()+"\t\t" + camera.getBrand() +"\t\t" + camera.getModel()+"\t\t"  + camera.getPrice()+"\t\t" );

			}
			System.out.println("===================================================================");

		}
	}

	//Method to rent a camera
	private static void rentCamera(Scanner scanner) {
		System.out.print("Enter camera ID to rent: ");
		int id = scanner.nextInt();
		scanner.nextLine();  

		Camera selectedCamera = null;
		for (Camera camera : cameraList) {
			if (camera.getId() == id && camera.isAvailable()) {
				selectedCamera = camera;
				break;
			}
		}

		if (selectedCamera != null) {
			double walletBalance = currentUser.getWalletBalance();
			double cameraPrice = selectedCamera.getPrice();
			if (walletBalance >= cameraPrice) {
				currentUser.setWalletBalance(walletBalance - cameraPrice);
				selectedCamera.setAvailable(false);
				currentUser.getRentedCameras().add(selectedCamera);
				System.out.println("Camera rented successfully!");
			} else {
				System.out.println("Transaction failed! Insufficient wallet balance.");
			}
		} else {
			System.out.println("Camera not available for rent.");
		}
	}

	//Method to manage wallet
	private static void myWallet(Scanner scanner) {
		System.out.println("Wallet Balance: $" + currentUser.getWalletBalance());
		System.out.print("Do you want to deposit more amount? (1. Yes / 2. No): ");
		int choice = scanner.nextInt();
		scanner.nextLine();  

		if (choice == 1) {
			System.out.print("Enter the amount to deposit: $");
			double amount = scanner.nextDouble();
			scanner.nextLine();  

			double walletBalance = currentUser.getWalletBalance();
			currentUser.setWalletBalance(walletBalance + amount);
			System.out.println("Amount deposited successfully!");
			System.out.println("Updated Wallet Balance: $" + currentUser.getWalletBalance());
		}
	}
	//Hardcoded initial camera to the list
	private static void initializeCameras() {
		cameraList.add(new Camera(1, "Canon", "EOS R5", 250.0, true));
		cameraList.add(new Camera(2, "Nikon", "Z7 II", 300.0, true));
		cameraList.add(new Camera(3, "Sony", "Alpha", 350.0, true));
	}


}


