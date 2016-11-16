package lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;

/**
 * The Class PtPublicKey to wrap type of private key.
 */
public class DtPrivateKey implements JIntIs, Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;

	/** The value to wrap. */
	PrivateKey value;
	
	/**
	 * Instantiates new type of private key.
	 *
	 * @param value The value to be wrapped
	 */
	public DtPrivateKey(PrivateKey value){
		this.value = value;
	}
	
	/**
	 * Instantiates new type of private key.
	 */
	public DtPrivateKey(){
		value = null;
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
	
	/**
	 * Gets private key from PtString value
	 * 
	 * @param privKey PtString value of private key
	 * @throws NoSuchAlgorithmException Thrown if transformation is null, empty, in an invalid format 
	 * @throws InvalidKeySpecException Thrown  if the requested key specification is inappropriate for 
	 * the given key, or the given key cannot be processed
	 */
	public DtPrivateKey fromString(PtString privKey) 
			throws NoSuchAlgorithmException, InvalidKeySpecException{
		KeyFactory kf = KeyFactory.getInstance("RSA");
		String[] publModExp = privKey.getValue().split("//");
		RSAPrivateKeySpec new_pubks = new RSAPrivateKeySpec
			(new BigInteger(publModExp[0]), new BigInteger(publModExp[1]));
	    this.value = kf.generatePrivate(new_pubks);
	    return new DtPrivateKey(this.value);
	}
	
	/**
	 * Gets String value of private key
	 * 
	 * @return String value of private key
	 * @throws NoSuchAlgorithmException Thrown if transformation is null, empty, in an invalid format 
	 * @throws InvalidKeySpecException Thrown  if the requested key specification is inappropriate for 
	 * the given key, or the given key cannot be processed
	 */
	public PtString toStringVal()
		throws NoSuchAlgorithmException, InvalidKeySpecException{
		KeyFactory kf = KeyFactory.getInstance("RSA");
		RSAPrivateKeySpec rsaPrivKey = kf.getKeySpec(value, RSAPrivateKeySpec.class);

		BigInteger prv_m = rsaPrivKey.getModulus();
	    BigInteger prv_x = rsaPrivKey.getPrivateExponent();
	        
	    return new PtString(prv_m.toString() + "//" + prv_x.toString());
	}
	
	/**
	 * Gets private key from file
	 * 
	 * @param value Login of the user
	 * @return Private key reconstructed from file 
	 * @throws IOException Thrown if an I/O error occurs reading from the stream
	 * @throws NoSuchAlgorithmException Thrown if transformation is null, empty, in an invalid format 
	 * @throws InvalidKeySpecException Thrown  if the requested key specification is inappropriate for 
	 * the given key, or the given key cannot be processed
	 */
	public DtPrivateKey getFromFile(PtString value) 
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException{
		File file = new File("D:" + File.separator + ".icrash_rsa" + File.separator + value.getValue()+"_rsa");
		if(!file.exists()) return null;
		String privKeyString = new String(Files.readAllBytes(Paths.get(file.getPath())));
		fromString(new PtString(privKeyString));
		return new DtPrivateKey(this.value);
	}
	
	/**
	 * Deletes file with private key 
	 * 
	 * @param value Login of the user
	 */
	public void deleteFile(PtString value)
	{
		File file = new File("D:" + File.separator + ".icrash_rsa" + File.separator + value.getValue()+"_rsa");
		if(!file.exists()) return;
		file.delete();
	}
	/**
	 * Saves String value of private key to file
	 * 
	 * @param value Login of the user for file name
	 * @throws IOException Thrown if an I/O error occurs reading from the stream
	 * @throws NoSuchAlgorithmException Thrown if transformation is null, empty, in an invalid format 
	 * @throws InvalidKeySpecException Thrown  if the requested key specification is inappropriate for 
	 * the given key, or the given key cannot be processed
	 */
	public void saveToFile(PtString value) 
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		File file = new File("D:"+File.separator+".icrash_rsa");
        if(!file.exists()) file.mkdir();
        file = new File(file.getAbsolutePath() + File.separator + value.getValue()+"_rsa");
        if(!file.exists()) file.createNewFile();
        PrintWriter pwr = new PrintWriter(file.getAbsoluteFile());
        pwr.print(new DtPrivateKey(this.value).toStringVal().getValue());
        pwr.close();
	}
}
