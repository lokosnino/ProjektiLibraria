package libraria.view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class modeliLibrari extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private shtoLibrin ab;
	private shfletoLibrin shB;
	private JTabbedPane jtb;
	private String filler;

	public modeliLibrari(String titulli){
		super(titulli);
		
		jtb = new JTabbedPane();
		ab = new shtoLibrin();
		shB = new shfletoLibrin();

		filler = "      "; // 6 hapsira
		jtb.addTab(filler + filler + " Ruaje " + filler + filler, ab);
		jtb.addTab(filler + " Shto Librin " + filler, shB);
		
		add(jtb);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(400, 460);
		setResizable(false);
	}
	
	public shtoLibrin getAddBookPanel(){
		return ab;
	}
	
	public shfletoLibrin getBrowseLibraryPanel(){
		return shB;
	}
	
	public JTabbedPane getTabbedPane(){
		return jtb;
	}
	
	public String getFiller(){
		return filler;
	}
	
}
