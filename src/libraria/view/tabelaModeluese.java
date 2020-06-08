package libraria.view;

import javax.swing.table.DefaultTableModel;

public class tabelaModeluese extends DefaultTableModel{

	private static final long ID = 1L;
	
	public tabelaModeluese(String[][] data, String[] columns){
		super(data, columns);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	

}
