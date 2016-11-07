package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

/**
 * The Class PtPublicKey to wrap type of public key.
 */
public class DtPublicKey implements JIntIs, Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;

	/** The value to wrap. */
	public PublicKey value;
	
	/**
	 * Instantiates new type of public key.
	 *
	 * @param v The value to be wrapped
	 */
	public DtPublicKey(PublicKey value){
		this.value = value;
	}
	
	public DtPublicKey () {
		this.value = null;
	}
	/**
	 * Gets the value inside the wrapper.
	 *
	 * @return The value inside the wrapper
	 */
	public PublicKey getValue(){
		return value;
	}
	
	public DtPublicKey fromString(PtString pubKey)
		throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory kf = KeyFactory.getInstance("RSA");
		String[] publModExp = pubKey.split("//");
		RSAPublicKeySpec new_pubks = new RSAPublicKeySpec
			(new BigInteger(publModExp[0]), new BigInteger(publModExp[1]));
	    this.value = kf.generatePublic(new_pubks);
	    return new DtPublicKey(this.value);
	}
	
	public String toStringVal()
		throws NoSuchAlgorithmException, InvalidKeySpecException{
		KeyFactory kf = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec rsaPrivKey = kf.getKeySpec(value, RSAPublicKeySpec.class);

        BigInteger prv_m = rsaPrivKey.getModulus();
        BigInteger prv_x = rsaPrivKey.getPublicExponent();
        
        return prv_m.toString() + "//" + prv_x.toString();
	}

	@Override
	public PtBoolean is() {
		return new PtBoolean(this.value != null);
	}
}
