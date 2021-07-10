
public class Dog  implements Comparable <Dog>{

		private String name;
		private String breed;
		private int age;
		private int weight;
		private User owner;
		private double tailLength;	
		
		public Dog(String newName, String newBreed, int newAge, int newWeight) {

			name = newName;
			breed = newBreed;
			age = newAge;
			weight = newWeight;
		}
		public int compareTo(Dog dogToCompare) {
			if (tailLength > dogToCompare.tailLength)
				return -1;
			else if(dogToCompare.tailLength>tailLength)
				return 1;
			else 
				return dogToCompare.getName().compareToIgnoreCase(this.name);
		}
		public User getOwner() {
			return owner;
		}
		
		public boolean isOwned() {
			if(getOwner() == null) {
				return false;
			}
			return true;
		}
		
		
		public String getName() {
			return name;
		}
		public void setOwner(User setUSer) {
			owner = setUSer;
		}

		public String getBreed() {
			return breed;
		}

		public int getAge() {
			return age;

		}

		public int getWeight() {
			return weight;
		}

		public double getTailLength() {
			if (breed.equalsIgnoreCase("tax") || breed.equalsIgnoreCase("Dachshund")) {
				tailLength = 3.7;
			} else {
				return tailLength = (age * weight) / 10.0;

			}
			return tailLength;
		}

		public int getUpdatedAge() {
			return age++;

		}

		public String toString() {
			if(getOwner() == null) {
				return getName() + "(" + getBreed() + " " + getAge() + " years" + " " + getWeight() + " kilo" + " " + getTailLength() + " cm tail)";
		} else {
			return getName() + "(" + getBreed() + " " + getAge() + " years" + " " + getWeight() + " kilo" + " " + getTailLength() + " cm tail)" + getOwner().getName();
		}
		}	
	
}
