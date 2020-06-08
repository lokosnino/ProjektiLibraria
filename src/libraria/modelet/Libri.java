package libraria.modelet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Libri implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int kodiSerik;
	private String title, author;
	private double price;

	private List<eLibraria> vims;

	public Libri() {
		kodiSerik = 0;
		title = null;
		author = null;
		price = 0;
		vims = new ArrayList<eLibraria>();
	}

	public Libri(int kodiSerik, String title, String author, double price) {
		this.kodiSerik = kodiSerik;
		this.title = title;
		this.author = author;
		this.price = price;
		vims = new ArrayList<eLibraria>();
	}

	public void addVIM(eLibraria v) {
		vims.add(v);
	}

	public String getTitulli() {
		return title;
	}

	public eLibraria getVIMByName(String name) {
		eLibraria v = null;
		Iterator<eLibraria> i = vims.iterator();
		while (i.hasNext()) {
			v = i.next();
			if (v.getName().toLowerCase().contentEquals(name.toLowerCase())) {
				return v;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		String vimNames = "";
		String vimAmount = "(" + String.valueOf(vims.size()) + ")";

		Iterator<eLibraria> i = vims.iterator();
		while (i.hasNext()) {
			vimNames += i.next().getName() + ", ";
		}

		return "\nTitulli: " + title + "\nAutori: " + author + "\nKodi-serik i Librit: " + kodiSerik
				+ "\nCmimi: " + price + "\ne-Libri" + vimAmount + ": "
				+ vimNames + "\n";
	}
	
	public int getKodiSerik(){
		return kodiSerik;
	}

	public String getAutori() {
		return author;
	}

	public String getPrice() {
		return String.valueOf(price);
	}

	public String getStudenti() {
		return String.valueOf(kodiSerik);
	}

	public String[][] toStringVectorFiles() {
		String total[][] = new String[vims.size()][3];
		eLibraria v;
		
		for(int i=0; i<vims.size();i++){
			v = vims.get(i);
			if (v.getName().endsWith("wav") || v.getName().endsWith("mp3"))
				total[i][0] = v.getName();
			else if (v.getName().endsWith("png") || v.getName().endsWith("jpeg"))
				total[i][1] = v.getName();
			else
				total[i][2] = v.getName();
		}
		
		return total;
	}

}

