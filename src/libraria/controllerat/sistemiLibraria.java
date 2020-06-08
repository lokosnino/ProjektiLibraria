package libraria.controllerat;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import libraria.modelet.Libraria;
import libraria.modelet.Libri;
import libraria.modelet.eLibraria;
import libraria.view.shtoLibrin;
import libraria.view.shfletoLibrin;
import libraria.view.modeliLibrari;
import libraria.view.loadLibraria;
import libraria.view.tabelaModeluese;

public class sistemiLibraria implements ChangeListener, ActionListener {

	private modeliLibrari screen;
	private shtoLibrin abp;
	private shfletoLibrin blp;
	private loadLibraria ls;

	private JFileChooser chooser;
	private FileFilter filter, filter2;
	private int resultCode;
	private File vimFile, saveFile, libFile;

	private Libraria lib;
	private Libri libri;
	private List<eLibraria> vimCache;
	private FileInputStream fis;
	private FileOutputStream fos;
	private ObjectInputStream in;

	private String[][] dataBook, dataFile;

	private String fileName;

	private boolean exit;

	private String[] fajllatValide = { ".wav", ".mp3", ".avi", ".mp4", ".png",
			".jpeg" };
	private String validFileTypeReminder;

	public sistemiLibraria() {
		initEventAttributes();

		screen = new modeliLibrari("Libraria System");
		abp = screen.getAddBookPanel();
		blp = screen.getBrowseLibraryPanel();

		screen.getTabbedPane().addChangeListener(this);
		abp.addActionListener(this);
		blp.addActionListener(this);

		ls = new loadLibraria("Welcome!");
		ls.addActionListener(this);
		ls.setVisible(true);

	}

	private void initEventAttributes() {
		chooser = new JFileChooser();
		filter = new FileNameExtensionFilter("Video/Image/Music Files", "wav",
				"mp3", "avi", "mp4", "png", "jpeg");
		filter2 = new FileNameExtensionFilter("Libraria Files", "ser");
		chooser.addChoosableFileFilter(filter);
		chooser.addChoosableFileFilter(filter2);

		lib = new Libraria();

		exit = false;

		vimCache = new ArrayList<eLibraria>();
		vimFile = null;
		saveFile = null;
		libFile = null;
		validFileTypeReminder = "Valid file types end with .wav, .mp3, .avi, .mp4, .png, .jpeg!";
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == abp.getButtonBrowse()) {
			openChooserAndSetTheVIMFile();
		} else if (event.getSource() == abp.getButtonAddFile()) {
			addVimFileTovimCache();
		} else if (event.getSource() == abp.getButtonAddBook()) {
			addVimFilesInvimCacheToBookAndAddBookToLibrary();
			reloadDataBook();
			reloadDataFile();
		} else if (event.getSource() == abp.getButtonListAllBooks()) {
			listAllBooksInLibrary();
		} else if (event.getSource() == abp.getButtonSave()) {
			save();
		} else if (event.getSource() == abp.getButtonSaveAndQuit()) {
			saveAndQuit();
		} else if (event.getSource() == blp.getButtonOpenBook()) {
			openBook();
		} else if (event.getSource() == blp.getButtonDeleteBook()) {

		} else if (event.getSource() == blp.getButtonOpenFilek()) {
			openFile();
		} else if (event.getSource() == blp.getButtonDeleteFile()) {

		} else if (event.getSource() == blp.getButtonSave()) {
			save();
		} else if (event.getSource() == blp.getButtonSaveAndQuit()) {
			saveAndQuit();
		} else if (event.getSource() == ls.getButtonLoad()) {
			// Clear he data inside the tables if any
			reloadDataBook();
			reloadDataFile();
			loadLibrary();
			chooser.setFileFilter(filter);
		} else if (event.getSource() == ls.getButtonNew()) {
			ls.dispose();
			screen.setVisible(true);
		} else if (event.getSource() == ls.getButtonExit()) {
			System.exit(0);
		}
	}

	private void openFile() {
		int row, column;
		eLibraria v;
		String fileName;
		File file;

		row = ((JTable) blp.getFileTable()).getSelectedRow();
		column = ((JTable) blp.getFileTable()).getSelectedColumn();
		fileName = ((JTable) blp.getFileTable()).getValueAt(row, column)
				.toString();

		v = libri.getVIMByName(fileName);
		try {
			file = new File(v.getName());
			fos = new FileOutputStream(file);
			fos.write(v.getData());// write this file
			fos.close();
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void openBook() {
		int row, column;
		String isbn;

		row = ((JTable) blp.getBookTable()).getSelectedRow();
		column = 3; // ISBN Column
		isbn = ((JTable) blp.getBookTable()).getValueAt(row, column).toString();
		libri = lib.getLibriByStudenti(isbn);

		dataFile = libri.toStringVectorFiles();
		reloadDataFile();
	}

	private void loadLibrary() {
		chooser.setFileFilter(filter2);
		resultCode = chooser.showOpenDialog(screen);
		if (resultCode == JFileChooser.APPROVE_OPTION) {
			libFile = chooser.getSelectedFile();
			try {
				fis = new FileInputStream(libFile);
				in = new ObjectInputStream(fis);
				lib = (Libraria) in.readObject();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			ls.dispose();

			// LOAD THE BOOK
			reloadDataBook();

			screen.setVisible(true);
		}
	}

	private void reloadDataBook() {
		while (((tabelaModeluese) blp.getBookTable().getModel()).getRowCount() > 0) {
			((tabelaModeluese) blp.getBookTable().getModel()).removeRow(0);
		}
		dataBook = lib.toStringVector();
		for (int i = 0; i < dataBook.length; i++) {
			((tabelaModeluese) blp.getBookTable().getModel()).addRow(dataBook[i]);
		}
	}

	private void reloadDataFile() {
		while (((tabelaModeluese) blp.getFileTable().getModel()).getRowCount() > 0) {
			((tabelaModeluese) blp.getFileTable().getModel()).removeRow(0);
		}
		if (dataFile != null)
			for (int i = 0; i < dataFile.length; i++) {
				((tabelaModeluese) blp.getFileTable().getModel()).insertRow(i,
						dataFile[i]);
			}
	}

	private void saveAndQuit() {
		save();
		if (exit)
			System.exit(0);
	}

	private void save() {
		fileName = JOptionPane.showInputDialog(screen,
				"Enter file name to save as...", "Save",
				JOptionPane.INFORMATION_MESSAGE);
		if (fileName != null) {
			if (!fileName.trim().contentEquals("")) {
				FileOutputStream fos = null;
				ObjectOutputStream out = null;
				try {
					saveFile = new File(fileName.trim() + ".ser");
					if (!saveFile.exists()) {
						fos = new FileOutputStream(saveFile);
						out = new ObjectOutputStream(fos);
						out.writeObject(lib);
						fos.close();
						out.close();
						exit = true;
					} else {
						int resultCode = JOptionPane.showConfirmDialog(screen,
								"File name already exist.\nOverwrite it?",
								"Warning", JOptionPane.YES_NO_OPTION,
								JOptionPane.WARNING_MESSAGE);
						if (resultCode == JOptionPane.YES_OPTION) {
							fos = new FileOutputStream(saveFile);
							out = new ObjectOutputStream(fos);
							out.writeObject(lib);
							fos.close();
							out.close();
							exit = true;
						} else {
							abp.getTextAreaLog().append("\n> Save cancelled.");
							exit = false;
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				abp.getTextAreaLog().append("\n> Save cancelled.");
				exit = false;
			}
		} else {
			abp.getTextAreaLog().append("\n> Save cancelled.");
			exit = false;
		}
	}

	private void listAllBooksInLibrary() {
		abp.getTextAreaLog().setText("> Listing all books in libraria...");
		abp.getTextAreaLog().append(" " + lib.toString());
	}

	private void addVimFilesInvimCacheToBookAndAddBookToLibrary() {
		boolean ISBNAlreadyExist = false;
		boolean AllFieldsAreFilled = false;
		int isbn = 0;
		double price = 0.0;
		Libri b;

		if (!abp.getTextFieldIsbn().getText().trim().contentEquals("")
				&& !abp.getTextFieldTitle().getText().trim().contentEquals("")
				&& !abp.getTextFieldAuthor().getText().trim().contentEquals("")
				&& !abp.getTextFieldPrice().getText().trim().contentEquals("")) {
			AllFieldsAreFilled = true;
		}

		if (AllFieldsAreFilled) {
			try {
				isbn = Integer
						.parseInt(abp.getTextFieldIsbn().getText().trim());
				price = Double.parseDouble(abp.getTextFieldPrice().getText()
						.trim());

				ISBNAlreadyExist = lib.doesStudentExist(isbn);
				if (ISBNAlreadyExist) {
					JOptionPane.showMessageDialog(screen, isbn
							+ " already exist!\nPlease use another isbn!");
				} else {
					b = new Libri(isbn,
							abp.getTextFieldTitle().getText().trim(), abp
									.getTextFieldAuthor().getText().trim(),
							price);

					for (int i = 0; i < vimCache.size(); i++) {
						b.addVIM(vimCache.get(i));
					}

					lib.addLibri(b);
					abp.getTextFieldIsbn().setText("");
					abp.getTextFieldTitle().setText("");
					abp.getTextFieldAuthor().setText("");
					abp.getTextFieldPrice().setText("");
					abp.getTextAreaLog().append(
							"\n> " + b.getTitulli()
									+ " has been added to the libraria!");

					vimCache = new ArrayList<eLibraria>();
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(screen,
						"Isbn or Price is not a number!");
			}
		} else {
			JOptionPane.showMessageDialog(screen,
					"Please fill out all non-optional fields");
		}
	}

	private void addVimFileTovimCache() {
		if (vimFile != null) {
			for (int i = 0; i < fajllatValide.length; i++) {
				if (abp.getTextFieldFile().getText().trim()
						.endsWith(fajllatValide[i])) {
					byte[] data = new byte[(int) vimFile.length()];
					try {
						fis = new FileInputStream(vimFile);
						fis.read(data);
						fis.close();
					} catch (FileNotFoundException e) {
						JOptionPane
								.showMessageDialog(screen, "File not found!");
					} catch (IOException e) {
						JOptionPane.showMessageDialog(screen,
								"Error reading file!");
					}
					eLibraria v = new eLibraria(abp.getTextFieldFile().getText().trim(),
							data);
					vimCache.add(v);
					vimFile = null;
					abp.getTextAreaLog().append(
							"\n> " + abp.getTextFieldFile().getText().trim()
									+ " is ready to be added to libri.");
					abp.getTextFieldFile().setText("optional");
					return;
				}
			}
			JOptionPane
					.showMessageDialog(screen,
							"Something went wrong!\nPlease click browse again and choose your file.");

		} else
			JOptionPane.showMessageDialog(screen, abp.getTextFieldFile()
					.getText()
					+ " is not a valid file type!\n"
					+ validFileTypeReminder);
	}

	private void openChooserAndSetTheVIMFile() {
		resultCode = chooser.showOpenDialog(screen);
		if (resultCode == JFileChooser.APPROVE_OPTION) {
			vimFile = chooser.getSelectedFile();
			abp.getTextFieldFile().setText(vimFile.getName());
		}
	}

	@Override
	// Called when tab changes
	public void stateChanged(ChangeEvent arg0) {
		// from add libri tab browse libraria tab
		if (screen.getTabbedPane().getSelectedIndex() == 1)
			screen.setSize(440, 452);
		else
			// from browse libraria tab to add libri tab
			screen.setSize(400, 460);

	}

} // sistemiLibraria End here

