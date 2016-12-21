package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

public class DtCoordinatorFirstName extends DtString implements JIntIs{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;	
	
	public DtCoordinatorFirstName(PtString s) {
		super(s);
	}
	
	private int _maxLength = 24;
	
	public PtBoolean is(){
		return new PtBoolean(this.value.getValue().length() <= _maxLength);
	}

}
