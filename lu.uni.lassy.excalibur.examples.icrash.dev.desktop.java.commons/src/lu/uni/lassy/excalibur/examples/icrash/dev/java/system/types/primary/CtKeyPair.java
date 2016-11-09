package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import java.io.Serializable;
import java.security.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

/**
 * The Class CtKeyPair, that hold pair of keys and allows do encode and decode a message.
 */
public class CtKeyPair implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;
	
	/** The public key. */
	DtPublicKey pubKey;
	/** The private key. */
	DtPrivateKey privKey;
	
	/**
	 * Instantiates a new classtype of the key pair
	 */
	public CtKeyPair() {
		pubKey = null;
		privKey = null;
	}
	
	/**
	 * Instantiates a new classtype of the key pair
	 * 
	 * @param privKey Private key
	 * @param pubKey Public key
	 */
	public CtKeyPair(DtPrivateKey privKey, DtPublicKey pubKey) {
		this.privKey = privKey;
		this.pubKey = pubKey;
	}
	
	/**
	 * Getter for public key
	 * 
	 * @return Public key
	 */
	public DtPublicKey getPublicKey(){
		return this.pubKey;
	}
	
	/**
	 * Getter for private key
	 * 
	 * @return Private key
	 */
	public DtPrivateKey getPrivateKey(){
		return this.privKey;
	}
	
	/**
	 * Setter for public key
	 * 
	 * @param value Public key
	 */
	public void setPublicKey(DtPublicKey value){
		pubKey = value;
	}
	
	/**
	 * Setter for private key
	 * 
	 * @param value Private key
	 */
	public void setPrivateKey(DtPrivateKey value){
		privKey = value;
	}
	
	/**
	 * Generates a pair of keys. Public and private
	 * 
	 * @throws NoSuchAlgorithmException Thrown if there is no such algorithm in getInstance()
	 */
	public void getKeys()
		throws NoSuchAlgorithmException{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.pubKey = new DtPublicKey(keyPair.getPublic());
        this.privKey = new DtPrivateKey(keyPair.getPrivate());
	}
	
	/**
	 * Encodes the message with the private key
	 * 
	 * @param msg Message which we have to encode
	 * @return Encoded message
	 * @throws NoSuchAlgorithmException Thrown if transformation is null, empty, in an invalid format
	 * @throws NoSuchPaddingException Thrown if transformation contains a padding scheme that is not available.
	 * @throws InvalidKeyException Thrown if the public key in the given certificate is inappropriate for initializing cipher
	 * @throws IllegalBlockSizeException Thrown if the total input length of the data 
	 * processed by this cipher is not a multiple of block size or 
	 * if this encryption algorithm is unable to process the input data provided
	 * @throws BadPaddingException Thrown if cipher is in decryption mode, and (un)padding has been requested, 
	 * but the decrypted data is not bounded by the appropriate padding bytes
	 */
	public byte[] encodeMsg(PtString msg)
		throws NoSuchAlgorithmException, NoSuchPaddingException, 
		InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		
		Cipher ciph = Cipher.getInstance("RSA");
        ciph.init(Cipher.ENCRYPT_MODE, this.privKey.getValue());
        return ciph.doFinal(msg.getValue().getBytes());
	}
	
	/**
	 * Decodes encoded message with the public key
	 * 
	 * @param msg Encoded message which we have to decode
	 * @return Decoded message Thrown if transformation contains a padding scheme that is not available.
	 	 * @throws NoSuchAlgorithmException Thrown if transformation is null, empty, in an invalid format
	 * @throws NoSuchPaddingException Thrown if transformation contains a padding scheme that is not available.
	 * @throws InvalidKeyException Thrown if the public key in the given certificate is inappropriate for initializing cipher
	 * @throws IllegalBlockSizeException Thrown if the total input length of the data 
	 * processed by this cipher is not a multiple of block size or 
	 * if this encryption algorithm is unable to process the input data provided
	 * @throws BadPaddingException Thrown if cipher is in decryption mode, and (un)padding has been requested, 
	 * but the decrypted data is not bounded by the appropriate padding bytes
	 */
	public PtString decodeMsg(byte[] msg) 
			throws NoSuchAlgorithmException, NoSuchPaddingException, 
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		Cipher ciph = Cipher.getInstance("RSA");
		ciph.init(Cipher.DECRYPT_MODE, this.pubKey.getValue());
        return new PtString(new String(ciph.doFinal(msg)));
	}
}
