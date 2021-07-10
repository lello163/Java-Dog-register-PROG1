
public class Bid implements Comparable<Bid>{
		private User name;
		private int amountToBid;
		public Bid(User user, int amountToBid) {
			name = user;
			this.amountToBid=amountToBid;
		}
		public User getUser() {
			return name;
		}
		
		public int getAmountBid() {
			return amountToBid;
		}
		public void setAmountBid(int newBid) {
			this.amountToBid = newBid;
		}
		
		public int compareTo(Bid b) {
			if (amountToBid > b.amountToBid) {
				return -1;
			}else if(b.amountToBid > amountToBid) {
				return 1;
			} else {
				return 0;
			}
		}
		public String toString() {
			return name.getName() + " " + amountToBid + " kr";
		}
	}

