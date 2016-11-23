package lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib;

import java.io.Serializable;

public class DtEncodedPassword implements Serializable{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;
	
	byte[] value;
	
	public DtEncodedPassword(){
		value = null;
	}
	
	public DtEncodedPassword(byte[] value){
		this.value = value;
	}
	
	public byte[] getValue(){
		return this.value;
	}
}
