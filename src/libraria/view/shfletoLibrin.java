package libraria.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class shfletoLibrin extends JPanel {

	private static final long serialVersionUID = 1L;

	private Box mainBox, hBox1, hBox2, hBox3, hBox4, hBox5;

	private JLabel jlBookTable, jlFileTable;
	private JButton bOpenBook, bDeleteBook, bOpenFile, bDeleteFile, bSave,
			bSaveAndQuit;
	private JTable tLibrat, tFiles;
	private tabelaModeluese model;
	private JScrollPane spBookTable, spFileTable;

	String[] bookColumns = { "Titulli", "Autori", "Cmimi", "Kodi Serik" };
	String[] fileColumns = { "e-Librat PDF", "ePub Libra", "Sound-track Libra" };
	String[][] bookData = { { " ", " ", " ", " " } };
	String[][] fileData = { { " ", " ", " " } };

	public shfletoLibrin() {
		super(new FlowLayout());
		intWidgets();
		shtoMenu();
		setBackground(new Color(22, 111, 248));
	}

	private void intWidgets() {
		mainBox = Box.createVerticalBox();

		hBox1 = Box.createHorizontalBox();
		hBox2 = Box.createHorizontalBox();
		hBox3 = Box.createHorizontalBox();
		hBox4 = Box.createHorizontalBox();
		hBox5 = Box.createHorizontalBox();

		jlBookTable = new JLabel("Duke shfaqur te gjitha Librat ne Bibloteke");
		jlFileTable = new JLabel("shfaqur eLibrat..");

		bOpenBook = new JButton("Hap");
		bDeleteBook = new JButton("Fshij");
		bOpenFile = new JButton("Hap");
		bDeleteFile = new JButton("Fshij");
		bSave = new JButton("Ruaj");
		bSaveAndQuit = new JButton("Ruaj&Mbyll");
		
		model = new tabelaModeluese(bookData, bookColumns);
		tLibrat = new JTable(model);
		tLibrat.setPreferredScrollableViewportSize(new Dimension(328, 120));
		tLibrat.setFillsViewportHeight(true);
		tLibrat.setAutoCreateRowSorter(true);
		tLibrat.getTableHeader().setReorderingAllowed(false);
		tLibrat.getColumnModel().getColumn(0).setPreferredWidth(200);
		tLibrat.getColumnModel().getColumn(1).setPreferredWidth(150);
		
		model = new tabelaModeluese(fileData, fileColumns);
		tFiles = new JTable(model);
		tFiles.setPreferredScrollableViewportSize(new Dimension(328, 80));
		tFiles.setFillsViewportHeight(true);
		tFiles.setAutoCreateRowSorter(true);
		tFiles.getTableHeader().setReorderingAllowed(false);
		
		spBookTable = new JScrollPane(tLibrat);
		spFileTable = new JScrollPane(tFiles);
		
		spBookTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		spBookTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		spFileTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		spFileTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	}

	private void shtoMenu() {
		hBox1.add(jlBookTable);
		hBox1.add(Box.createHorizontalStrut(50));
		hBox1.add(bDeleteBook);
		hBox1.add(bOpenBook);
		hBox2.add(spBookTable);
		hBox3.add(jlFileTable);
		hBox3.add(Box.createHorizontalStrut(65));
		hBox3.add(bDeleteFile);
		hBox3.add(bOpenFile);
		hBox4.add(spFileTable);
		hBox5.add(Box.createHorizontalStrut(185));
		hBox5.add(bSave);
		hBox5.add(Box.createHorizontalStrut(5));
		hBox5.add(bSaveAndQuit);

		mainBox.add(hBox1);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox2);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox3);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox4);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox5);

		add(mainBox);
		
	}
	
	public void addActionListener(ActionListener a){
		bOpenBook.addActionListener(a);
		bDeleteBook.addActionListener(a);
		bOpenFile.addActionListener(a);
		bDeleteFile.addActionListener(a);
		bSave.addActionListener(a);
		bSaveAndQuit.addActionListener(a);
	}
	
	public JButton getButtonOpenBook(){
		return bOpenBook;
	}
	
	public JButton getButtonDeleteBook(){
		return bDeleteBook;
	}
	
	public JButton getButtonOpenFilek(){
		return bOpenFile;
	}
	
	public JButton getButtonDeleteFile(){
		return bDeleteFile;
	}
	
	public JButton getButtonSave(){
		return bSave;
	}
	
	public JButton getButtonSaveAndQuit(){
		return bSaveAndQuit;
	}
	
	public JTable getBookTable(){
		return tLibrat;
	}
	
	public JTable getFileTable(){
		return tFiles;
	}

}
