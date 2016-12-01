package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import java.io.Serializable;
import java.security.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtEncodedPassword;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtPrivateKey;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtPublicKey;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
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
	/** The message that needs to be encoded */
	PtString decodedMsg;
	/** The message that needs to be decoded */
	DtEncodedPassword encodedMsg;
	
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

	public PtString getDecodedMsg(){
		return this.decodedMsg;
	}
	
	public DtEncodedPassword getEncodedMsg(){
		return this.encodedMsg;
	}
	/**
	 * Instantiates a new classtype of the key pair for encoding
	 * 
	 * @param value Public key
	 */
	public void initForEncode(DtPrivateKey privKey, PtString decodedMsg){
		this.privKey = privKey;
		this.decodedMsg = decodedMsg;
	}
	
	/**
	 * Instantiates a new classtype of the key pair for decoding
	 * 
	 * @param privKey Private key
	 * @param encodedMsg Message that needs to be decoded
	 */
	public void initForDecode(DtPublicKey pubKey, DtEncodedPassword encodedMsg){
		this.pubKey = pubKey;
		this.encodedMsg = encodedMsg;
	}
	
	/**
	 * Generates a pair of keys. Public and private
	 * 
	 * @return true if keys were successfully generated
	 * @throws NoSuchAlgorithmException Thrown if there is no such algorithm in getInstance()
	 */
	public PtBoolean getKeys()
		throws NoSuchAlgorithmException{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.pubKey = new DtPublicKey(keyPair.getPublic());
        this.privKey = new DtPrivateKey(keyPair.getPrivate());
        return new PtBoolean(this.pubKey.is().getValue() && this.privKey.is().getValue());
	}
	
	/**
	 * Encodes the message with the private key
	 * 
	 * @return 
	 * @throws NoSuchAlgorithmException Thrown if transformation is null, empty, in an invalid format
	 * @throws NoSuchPaddingException Thrown if transformation contains a padding scheme that is not available.
	 * @throws InvalidKeyException Thrown if the public key in the given certificate is inappropriate for initializing cipher
	 * @throws IllegalBlockSizeException Thrown if the total input length of the data 
	 * processed by this cipher is not a multiple of block size or 
	 * if this encryption algorithm is unable to process the input data provided
	 * @throws BadPaddingException Thrown if cipher is in decryption mode, and (un)padding has been requested, 
	 * but the decrypted data is not bounded by the appropriate padding bytes
	 */
	public PtBoolean encodeMsg()
		throws NoSuchAlgorithmException, NoSuchPaddingException, 
		InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		
		Cipher ciph = Cipher.getInstance("RSA");
        ciph.init(Cipher.ENCRYPT_MODE, this.privKey.getValue());
        this.encodedMsg = new DtEncodedPassword(ciph.doFinal(this.decodedMsg.getValue().getBytes()));
        return this.encodedMsg.is();
	}
	
	/**
	 * Decodes encoded message with the public key
	 * 
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
	public PtBoolean decodeMsg() 
			throws NoSuchAlgorithmException, NoSuchPaddingException, 
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		Cipher ciph = Cipher.getInstance("RSA");
		ciph.init(Cipher.DECRYPT_MODE, this.pubKey.getValue());
        this.decodedMsg = new PtString(new String(ciph.doFinal(this.encodedMsg.getValue())));
        return new PtBoolean(!this.decodedMsg.getValue().isEmpty());
	}
}
