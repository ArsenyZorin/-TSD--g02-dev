package lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib;

import java.io.Serializable;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;

public class DtEncodedPassword implements Serializable, JIntIs{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;
	
	byte[] value;
	
	public DtEncodedPassword(byte[] value){
		this.value = value;
	}
	
	public byte[] getValue(){
		return this.value;
	}

	@Override
	public PtBoolean is() {
		return new PtBoolean(this.value != null);
	}
}
