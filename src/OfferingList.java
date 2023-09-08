import java.util.ArrayList;


public class OfferingList extends ArrayList<Offering> {


	private ArrayList<Offering> offeringList;

	// add Offering to OfferingList
	public void addOffering(Offering o) {
		this.offeringList.add(o);
	}

	// Initialize OfferingList
	OfferingList() {
		this.offeringList = new ArrayList<Offering>();
	}

	// return Iterator
	public OfferingIterator getIterator() {
		return new OfferingIterator(this.offeringList);
	}

	// remove Offering from OfferingList
	public void remove(Offering o) {
		offeringList.remove(o);
	}

	// Iterator for OfferingList
	private class OfferingIterator implements Iterator {
		private int index;

		private ArrayList<Offering> offeringList;
		// Take Offering list as input
		public OfferingIterator(ArrayList<Offering> offeringList) {
			this.offeringList = offeringList;
		}
		// check if list has another member
		@Override
		public boolean hasNext() {
			if(index < offeringList.size()) {
				return true;
			}
			return false;
		}

		// return the member
		@Override
		public Offering next() {
			Offering o = offeringList.get(index);
			index++;
			return o;
		}


		public void reset() {
			index = 0;
		}
	}
}
