
import java.util.ArrayList;

public class User {
	private ArrayList<Dog> dogReg = new ArrayList<Dog>();

	private String name;

	public User(String userName) {
		name = userName;
	}

	public String getName() {
		return name;
	}

	public void dogOwner(Dog dog) {
		dogReg.add(dog);
	}

	public void removeDog(Dog dog) {
		dogReg.remove(dog);
	}

	public ArrayList<Dog> getDogs() {
		return dogReg;
	}

	public String toString() {
		String str = "";
		String dogFormat = name;
		for (int i = 0; i < dogReg.size(); i++) {
			str += dogReg.get(i).getName() + ", ";

		}
		if (str.length() > 2) {
			str = str.substring(0, str.length() - 2);
		}
		return dogFormat + " [" + str + "] ";
	}
}
