package libraria.modelet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Libraria extends Object implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Libri> collection;

	public Libraria() {
		collection = new ArrayList<Libri>();
	}

	public void addLibri(Libri libri) {
		collection.add(libri);
	}

	public Libri getLibriByName(String name) {
		Libri v = null;
		Iterator<Libri> i = collection.iterator();
		while (i.hasNext()) {
			v = i.next();
			if (v.getTitulli().toLowerCase().contentEquals(name.toLowerCase())) {
				return v;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		String total = "\n";


		Iterator<Libri> i = collection.iterator();
		while (i.hasNext()) {
			Libri b = (Libri) i.next();
			total = total + b.toString();
		}
		return total;
	}

	public boolean doesStudentExist(int isbn) {
		Iterator<Libri> i = collection.iterator();
		while (i.hasNext()) {
			if (i.next().getKodiSerik() == isbn) {
				return true;
			}
		}
		return false;
	}

	public String[][] toStringVector() {
		String[][] total = new String[collection.size()][5];

		for (int i = 0; i < collection.size(); i++) {
			total[i][0] = collection.get(i).getTitulli();
			total[i][1] = collection.get(i).getAutori();
			total[i][2] = collection.get(i).getPrice();
			total[i][3] = collection.get(i).getStudenti();
		}
		
		return total;

	}

	public Libri getLibriByStudenti(String isbn) {
		for (int i = 0; i < collection.size(); i++) {
			if(collection.get(i).getStudenti().contentEquals(isbn)){
				return collection.get(i);
			}
		}
		return null;
	}

}
