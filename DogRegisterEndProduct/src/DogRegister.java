//Elie Ishak elis3622, Jasamin Shirmand jash9828
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;

public class DogRegister {

	private Scanner input = new Scanner(System.in);
	private ArrayList<Dog> registerDog = new ArrayList<Dog>();
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Auction> auctions = new ArrayList<Auction>();

	private void initialize() {
		System.out.println("Welcome to the Dog daycare");

		System.out.println("Type register new dog to register a new dog");
		System.out.println("Type increase age to increase age");
		System.out.println("Type list dogs to list dogs");
		System.out.println("Type remove dog to remove dog");
		System.out.println("Type exit to exit");
		System.out.println("type register new user to register new user");
		System.out.println("Type list users to list users");
		System.out.println("type remove user to remove user");
		System.out.println("Type start auction to start auction");
		System.out.println("Type close auction to close auction");
		System.out.println("Type list bids to list bids");
		System.out.println("Type list auctions to list auctions");
		System.out.println("Type make bid to make bid");

	}
	
	private String readCommand() {
		System.out.println("Give command > ");
		String command = input.nextLine();
		return command;
	}

	private void loopCommand() {
		boolean done = false;
		while (!done) {
			String command = readCommand();
			done = handleCommand(command);
		}
	}

	
	private boolean handleCommand(String command) {
		switch (command.toLowerCase()) {
		case "register new dog":
			regDog();
			break;
		case "increase age":
			increaseAge();
			break;
		case "remove dog":
			removeDog();
			break;
		case "exit":
			quit();
			return true;
		case "list dogs":
			listDog();
			break;
		case "register new user":
			regUser();
			break;
		case "remove user":
			removeUser();
			break;
		case "start auction":
			startAuction();
			break;
		case "make bid":
			makeBid();
			break;
		case "close auction":
			closeAuction();
			break;
		case "list users":
			listUser();
			break;
		case "list auctions":
			listAuction();
			break;
		case "list bids":
			listBids();
			break;
		default:
			System.out.println("error, command does not exist");
		}
		return false;
	}

	private String inputToString() {
		String inputToString = input.nextLine().trim().toLowerCase();
		while (inputToString.isEmpty()) {
			System.out.println("Error: The name can't be empty");
			System.out.println("Enter the name again>");
			inputToString = input.nextLine().trim().toLowerCase();
		}
		return inputToString;
	}
	
	private void regDog() {
		System.out.println("Name of dog: ");
		String name = inputToString();
		System.out.println("Breed of dog: ");
		String breed = inputToString();
		System.out.println("Age of dog: ");
		int age = input.nextInt();
		input.nextLine();
		System.out.println("Weight of dog: ");
		int weight = input.nextInt();
		input.nextLine();
		Dog dog = new Dog(name, breed, age, weight);
		registerDog.add(dog);
		System.out.println(name + " " + "added to the register");
		System.out.println(dog);
	}

	private void startAuction() {
		System.out.println("Enter the name of the dog>");
		String dogName = inputToString();

		Dog dogsname = null;
		for (Dog dogsName : registerDog) {
			if (dogsName.getName().equalsIgnoreCase(dogName)) {
				dogsname = dogsName;
				break;
			}
		}
		if (dogsname == null) {
			System.out.println("Error: no such dog");
			return;
		}
		User usernames = dogsname.getOwner();
		if (usernames != null) {
			System.out.println("Error: this dog already has an owner");
			return;
		}
		for (Auction auctionsMany : auctions) {
			if (auctionsMany.getDog().equals(dogsname)) {
				System.out.println("Error: dog is already on market");
				return;
			}
		}
		Auction auction = new Auction(dogsname);
		auctions.add(auction);
		System.out.println(dogsname + " " + "has been put on for auction  in auction #" + auction.getAuctionNumb());
	}
	private User checkUser() {
		System.out.println("Enter the name of the user>");
		String nameUser = inputToString();

		User user = null;
		for (User userName : users) {
			if (userName.getName().equalsIgnoreCase(nameUser)) {
				user = userName;
				return user;
			}
		}
			System.out.println("Error: no such user");
			return null;
		}
	private Dog checkDog() {
		System.out.println("Enter the name of the dog");
		String nameDog = inputToString();
		Dog dogsname = null;
		for (Dog dogsName : registerDog) {
			if (dogsName.getName().equalsIgnoreCase(nameDog)) {
				dogsname = dogsName;
				return dogsname;
			}
		}
			System.out.println("Error: this dog is not up for auction");
			return null;
		
	}
	private void makeBid() {
		User user = checkUser();
		Dog dogsname = checkDog();
		Auction auction = null;
		for (Auction auctionsMany : auctions) {
			if (auctionsMany.getDog().equals(dogsname)) {
				auction = auctionsMany;
				break;
			}
		}

		if (auction == null) {
			System.out.println("Error no auction");
			return;
		}

		Bid b = auction.getHighestBid();
		int min;
		if (b == null) {
			min = 1;
		} else {

			min = b.getAmountBid() + 1;
		}

		int value = 0;
		do {
			System.out.println("Enter a bid");
			value = input.nextInt();
			input.nextLine();
			if (value < min) {
				System.out.println("Error to low bid");
			}
		} while (value < min);

		auction.makeBid(user, value);
		System.out.println("Bid made");

	}


	private void listBids() {
		System.out.println("Enter the name of the dog> ");
		String dogName = inputToString();
		System.out.println("Here are the bids ");

		boolean foundDog = false;
		if (!auctions.isEmpty()) {
			for (Auction auction : auctions) {
				if (auction.getDog().getName().equals(dogName)) {
					auction.listBids();
					foundDog = true;
				}

			}
		}
		if (auctions.isEmpty() || !foundDog) {
			System.out.println("Error: this dog is not up for auction");
		}
	}

	private void removeUser() {
		System.out.println("Enter the name of the user>");
		String removeName = inputToString();
		User u = null;
		for (User user : users) {

			if (user.getName().equalsIgnoreCase(removeName)) {
				u = user;
				break;
			}
		}
		if (u == null) {
			System.out.println("Error no such user");
			return;
		} else {
			for (Dog d : u.getDogs()) {
				registerDog.remove(d);
			}
			for (Auction a : auctions) {
				a.removeBid(u);

			}
		}
		users.remove(u);
		System.out.println(u.getName() + " hase been removed");

	}

	private void regUser() {
		System.out.print("Name> ");
		String nameUser = inputToString();
		User username = new User(nameUser);
		users.add(username);
		System.out.println((username));
	}

	private void increaseAge() {
		System.out.println("increase age");
		System.out.println("Type in dog name: ");
		String name = inputToString();
		for (Dog dog : registerDog) {
			if (dog.getName().equalsIgnoreCase(name)) {
				dog.getUpdatedAge();
				System.out.println(name + " " + "You have increased age");
				return;
			}
		}
		System.out.println("Error: no such dog");
	}

	private void removeDog() {
		System.out.println("You chose remove dog , what kind of dog do you want to remove: ");
		String remove = inputToString();
		for (Dog dog : registerDog) {
			if (dog.getName().equalsIgnoreCase(remove)) {
				registerDog.remove(dog);
				System.out.println("Dog with given name is removed");
				return;
			}
		}
		System.out.println("Error, dog not found");

	}

	private void listAuction() {
		if (auctions.isEmpty()) {
			System.out.println("Error: no auctions in progress");
			return;
		}
		for (Auction auction : auctions) {
			System.out.println(auction);
		}
	}

	private void listUser() {
		if (users.isEmpty()) {
			System.out.println("Error: no users in register");
			return;
		}
		for (User sortUser : users) {
			System.out.println(sortUser);
		}
	}

	private void listDog() {
		if (registerDog.isEmpty()) {
			System.out.println("Error: no dogs in register");
			return;
		}
		System.out.println("Smallest taillength to display> ");
		double tailLength = input.nextDouble();
		input.nextLine();
		selectionSortDogs(registerDog);
		for (Dog sortDog : registerDog) {
			if (sortDog.getTailLength() >= tailLength) {
				System.out.println(sortDog);
			}
		}
	}

	private void selectionSortDogs(ArrayList<Dog> hund) {
		int currentMinIndex;
		for (int i = 0; i < hund.size() - 1; i++) {
			currentMinIndex = i;
			for (int j = i + 1; j < hund.size(); j++) {
				if (hund.get(j).compareTo(hund.get(currentMinIndex)) > 0) {
					Collections.swap(hund, i, j);
				}
			}
		}
	}

	private void closeAuction() {
		System.out.println("Enter the name of the dog> ");
		String nameOfDog = inputToString();
		Dog dog = findDog(nameOfDog);
		Auction auction = findAuction(dog);
		if (auction == null) {
			System.out.println("Error this dog is not up for auction");
			return;
		} else if (auction.getBidList().isEmpty()) {
			System.out.printf("The auction is closed. no bids were made for %s\n", auction.getDog().getName());
			auctions.remove(auction);
			return;
		} else {
			Bid highest = auction.getHighestBid();
			if (highest == null) {
				System.out.printf("The auction is closed. no bids were made for %s\n", auction.getDog().getName());
				auctions.remove(auction);
				return;
			}
			User owner = highest.getUser();
			owner.dogOwner(dog);
			dog.setOwner(owner);
			int amount = highest.getAmountBid();

			System.out.printf("The auction is closed. The winning bid was %d kr and was made by %s", amount,
					owner.getName());
			auctions.remove(auction);
			return;
		}
	}

	private Dog findDog(String dogName) {
		for (Dog dog : registerDog) {
			if (dog.getName().equals(dogName)) {
				return dog;
			}
		}
		return null;
	}

	private Auction findAuction(Dog dogToFind) {

		for (Auction auction : auctions) {
			if (auction.getDog().equals(dogToFind)) {
				return auction;
			}
		}
		return null;

	}

	private void quit() {
		System.out.println("you chose to exit");
	}

	private void goodbye() {
		System.out.print("Goodbye, come back later");
	}

	public static void main(String[] args) {
		DogRegister d = new DogRegister();
		d.initialize();
		d.loopCommand();
		d.goodbye();
	}

}
