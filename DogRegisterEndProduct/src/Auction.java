import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Auction {
	private ArrayList<Bid> bids = new ArrayList<Bid>();
	private Bid[] topBids = new Bid[3];
	private User owner = null;
	private Dog dog;
	private static int numberOfAuctions = 0;
	private int actionsNumber;
	private int highestBid = 1;

	public Auction(Dog dog) {
		this.dog = dog;
		actionsNumber = ++numberOfAuctions;
	}

	public Dog getDog() {
		return dog;

	}

	public User getOwner() {
		return owner;
	}

	public ArrayList<Bid> getBidList() {
		ArrayList<Bid> copyOfList = new ArrayList<Bid>(bids);
		return copyOfList;

	}

	public void listBids() {

		if (bids.isEmpty()) {
			System.out.println("Error:No bids registred yet for this auction");
		} else {
			sortList();
			for (Bid bid : bids) {
				System.out.println(bid.getUser().getName() + " " + bid.getAmountBid() + " " + "kr");

			}
		}
	}

	private void sortList() {
		Collections.sort(bids);
		int maxLoops = 3;
		if (bids.size() < maxLoops) {
			maxLoops = bids.size();
		}
		for (int i = 0; i < maxLoops; i++) {
			topBids[i] = bids.get(i);
		}
	}

	public void removeBid(User user) {

		for (Bid b : bids) {
			if (b.getUser().equals(user)) {
				bids.remove(b);
				break;
			}
		}
		sortList();
	}

	private void setHighestBid(int highestBid) {
		this.highestBid = highestBid;
	}

	public Bid getHighestBid() {
		if (bids.isEmpty()) {
			return null;
		}
		sortList();
		return bids.get(0);

	}

	public void makeBid(User user, int amountToBid) {
		highestBid = amountToBid;
		for (Bid bid : bids) {
			if (bid.getUser().equals(user)) {
				bid.setAmountBid(amountToBid);
				return;
			}
		}
		Bid newBid = new Bid(user, amountToBid);
		bids.add(newBid);

	}

	public int getAuctionNumb() {
		return actionsNumber;
	}

	public String topBidsToString() {
		String output = "[";
		for (int i = 0; i < topBids.length; i++) {
			if (topBids[i] != null) {
				if (i > 0) {
					output += ", ";
				}
				output += topBids[i];
			}
		}
		output += "]";
		return output;
	}
	public String toString() {
		sortList();
		return "Auction" + " " + "#" + actionsNumber + ": " + getDog().getName() + ". Top bids: " + topBidsToString();
	}
}
