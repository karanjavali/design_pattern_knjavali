import java.util.ArrayList;

import static java.util.Spliterators.iterator;

public class OfferingList extends ArrayList<Offering> {

	public ArrayList<Offering> getOfferingList() {
		return offeringList;
	}

	public void setOfferingList(ArrayList<Offering> offeringList) {
		this.offeringList = offeringList;
	}

	private ArrayList<Offering> offeringList;

	public void addOffering(Offering o) {
		this.offeringList.add(o);
	}


	OfferingList() {
		this.offeringList = new ArrayList<Offering>();
	}

	public OfferingIterator getIterator() {
		return new OfferingIterator(this.offeringList);
	}

	public void remove(Offering o) {
		offeringList.remove(o);
	}
	private class OfferingIterator implements Iterator {
		private int index;

		private ArrayList<Offering> offeringList;

		public OfferingIterator(ArrayList<Offering> offeringList) {
			this.offeringList = offeringList;
		}
		@Override
		public boolean hasNext() {
			if(index < offeringList.size()) {
				return true;
			}
			return false;
		}

		@Override
		public Offering next() {
			Offering o = offeringList.get(index);
			index++;
			return o;
		}
		public void moveToHead() {

		}


		public void reset() {
			index = 0;
		}
	}
}
