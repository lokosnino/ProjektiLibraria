package libraria.modelet;

import java.io.Serializable;


public class eLibraria implements Serializable{

	private static final long ID = 1L;
	
	private String name; 
	private byte[] data;
	
	public eLibraria(){
		name = null;
		data = null;
	}
	
	public eLibraria(String name, byte[] data){
		this.name = name;
		this.data = data;
	}
	
	public String getName(){
		return name;
	}
	
	public byte[] getData(){
		return data;
	}
	
}
