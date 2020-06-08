package libraria.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class shtoLibrin extends JPanel {

	private static final long serialVersionUID = 1L;

	private Box mainBox, hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, hBox7, hBox8,
			hBox9;

	private JLabel jStudenti, jlTitle, jlAuthor, jlPrice, jlFile, jlLogDog, jKthimi;
	private JTextField jtStudenti, jtTitulli, jtAutori, jtPrice, jteLibri, jtKthimi;
	private JButton bBrowse, bAddFile, bAddBook, bSave, bSaveAndQuit, bListAllBooks,bKthimi;
	private JTextArea jtaMesazhi;
	private JScrollPane scrollPane;

	public shtoLibrin() {
		super(new FlowLayout());
		initWidgets();
		addWidgets();
		setBackground(new Color(21, 111, 248));
	}

	private void initWidgets() {
		mainBox = Box.createVerticalBox();

		hBox1 = Box.createHorizontalBox();
		hBox2 = Box.createHorizontalBox();
		hBox3 = Box.createHorizontalBox();
		hBox4 = Box.createHorizontalBox();
		hBox5 = Box.createHorizontalBox();
		hBox6 = Box.createHorizontalBox();
		hBox7 = Box.createHorizontalBox();
		hBox8 = Box.createHorizontalBox();
		hBox9 = Box.createHorizontalBox();

		jStudenti = new JLabel("Emri i Kodit serik te librit:       ");
		jlTitle = new JLabel("Sheno Emrin:        ");
		jlAuthor = new JLabel("Sheno Autorin:   ");
		jlPrice = new JLabel("Shto Cmimin:      ");
		jlFile = new JLabel("Shto nje e-Liber:   ");
		jlLogDog = new JLabel("UBT CAMP:");

		jtStudenti = new JTextField(19);
		jtTitulli = new JTextField(19);
		jtAutori = new JTextField(19);
		jtPrice = new JTextField(19);
		jteLibri = new JTextField(19);
		
		jteLibri.setText("Te gjitha librat kthenehen brenda 2 jave");

		jtStudenti.setHorizontalAlignment(JTextField.RIGHT);
		jtTitulli.setHorizontalAlignment(JTextField.RIGHT);
		jtAutori.setHorizontalAlignment(JTextField.RIGHT);
		jtPrice.setHorizontalAlignment(JTextField.RIGHT);
		jteLibri.setHorizontalAlignment(JTextField.RIGHT);

		bBrowse = new JButton("Shfleto");
		bAddFile = new JButton("Shto nje e-Libri");
		bAddBook = new JButton("Shto Libri ne Libraria");
		bSave = new JButton("Ruaj");
		bSaveAndQuit = new JButton("Ruaj&Quit");
		bListAllBooks = new JButton("Shfleto gjitha librat");

		jtaMesazhi = new JTextArea(10, 24);
		jtaMesazhi.setEditable(false);

		scrollPane = new JScrollPane(jtaMesazhi);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	}

	private void addWidgets() {
		hBox1.add(jStudenti);
		hBox1.add(jtStudenti);
		hBox2.add(jlTitle);
		hBox2.add(jtTitulli);
		hBox3.add(jlAuthor);
		hBox3.add(jtAutori);
		hBox4.add(jlPrice);
		hBox4.add(jtPrice);
		hBox5.add(jlFile);
		hBox5.add(jteLibri);
		hBox6.add(Box.createHorizontalStrut(82));
		hBox6.add(bBrowse);
		hBox6.add(Box.createHorizontalStrut(5));
		hBox6.add(bAddFile);
		hBox7.add(jlLogDog);
		hBox7.add(Box.createHorizontalStrut(99));
		hBox7.add(bAddBook);
		hBox8.add(bListAllBooks);
		hBox8.add(Box.createHorizontalStrut(20));
		hBox8.add(bSave);
		hBox8.add(Box.createHorizontalStrut(5));
		hBox8.add(bSaveAndQuit);

		mainBox.add(hBox1);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox2);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox3);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox4);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox5);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox6);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox7);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(scrollPane);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox8);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox9);


		add(mainBox);
	}

	public void addActionListener(ActionListener a) {
		bBrowse.addActionListener(a);
		bAddFile.addActionListener(a);
		bAddBook.addActionListener(a);
		bSave.addActionListener(a);
		bSaveAndQuit.addActionListener(a);
		bListAllBooks.addActionListener(a);
	}

	public JButton getButtonListAllBooks(){
		return bListAllBooks;
	}
	
	public JButton getButtonBrowse() {
		return bBrowse;
	}

	public JButton getButtonAddFile() {
		return bAddFile;
	}

	public JButton getButtonAddBook() {
		return bAddBook;
	}

	public JButton getButtonSave() {
		return bSave;
	}

	public JButton getButtonSaveAndQuit() {
		return bSaveAndQuit;
	}

	public JTextField getTextFieldIsbn() {
		return jtStudenti;
	}

	public JTextField getTextFieldTitle() {
		return jtTitulli;
	}

	public JTextField getTextFieldAuthor() {
		return jtAutori;
	}

	public JTextField getTextFieldPrice() {
		return jtPrice;
	}

	public JTextField getTextFieldFile() {
		return jteLibri;
	}
	
	public JTextArea getTextAreaLog(){
		return jtaMesazhi;
	}

}
