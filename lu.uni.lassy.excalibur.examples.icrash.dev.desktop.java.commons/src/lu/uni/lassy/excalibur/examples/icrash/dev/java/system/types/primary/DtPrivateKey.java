package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

/**
 * The Class PtPublicKey to wrap type of private key.
 */
public class DtPrivateKey implements JIntIs, Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;

	/** The value to wrap. */
	PrivateKey value;
	
	/**
	 * Instantiates new type of public key.
	 *
	 * @param v The value to be wrapped
	 */
	public DtPrivateKey(PrivateKey v){
		value = v;
	}
	
	/**
	 * Gets the value inside the wrapper.
	 *
	 * @return The value inside the wrapper
	 */
	public PrivateKey getValue(){
		return value;
	}
	
	@Override
	public PtBoolean is() {
		return new PtBoolean(this.value != null);
	}
	
	public void fromString(PtString privKey)
		throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory kf = KeyFactory.getInstance("RSA");
		String[] publModExp = privKey.split("//");
		RSAPrivateKeySpec new_pubks = new RSAPrivateKeySpec
			(new BigInteger(publModExp[0]), new BigInteger(publModExp[1]));
	    this.value = kf.generatePrivate(new_pubks);
	}
	
	public String toStringVal()
		throws NoSuchAlgorithmException, InvalidKeySpecException{
		KeyFactory kf = KeyFactory.getInstance("RSA");
		RSAPrivateKeySpec rsaPrivKey = kf.getKeySpec(value, RSAPrivateKeySpec.class);

		BigInteger prv_m = rsaPrivKey.getModulus();
	    BigInteger prv_x = rsaPrivKey.getPrivateExponent();
	        
	    return prv_m.toString() + "//" + prv_x.toString();
	}
	
	public void saveToFile(PtString value) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException{
		File file = new File("D:"+File.separator+".icrash_rsa");
        if(!file.exists()) file.mkdir();
        file = new File(file.getAbsolutePath() + File.separator + value.getValue()+"_rsa");
        if(!file.exists()) file.createNewFile();
        PrintWriter pwr = new PrintWriter(file.getAbsoluteFile());
        pwr.print(new DtPrivateKey(this.value).toStringVal());
        pwr.close();
	}
}
