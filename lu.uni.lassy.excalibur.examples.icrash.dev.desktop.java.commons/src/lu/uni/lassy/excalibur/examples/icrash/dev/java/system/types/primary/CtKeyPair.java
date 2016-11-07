package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import java.io.Serializable;
import java.security.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

public class CtKeyPair implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;
	
	DtPublicKey pubKey;
	DtPrivateKey privKey;
	
	public CtKeyPair() {
		pubKey = null;
		privKey = null;
	}
	
	public CtKeyPair(DtPrivateKey privKey, DtPublicKey pubKey) {
		this.privKey = privKey;
		this.pubKey = pubKey;
	}
	
	public DtPublicKey getPublicKey(){
		return this.pubKey;
	}
	
	public DtPrivateKey getPrivateKey(){
		return this.privKey;
	}
	
	public void setPublicKey(DtPublicKey value){
		pubKey = value;
	}
	
	public void setPrivateKey(DtPrivateKey value){
		privKey = value;
	}
	
	public void getKeys()
		throws NoSuchAlgorithmException{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.pubKey = new DtPublicKey(keyPair.getPublic());
        this.privKey = new DtPrivateKey(keyPair.getPrivate());
	}
	
	public byte[] encodeMsg(PtString msg)
		throws InvalidKeyException, NoSuchPaddingException, 
		NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException{
		
		Cipher ciph = Cipher.getInstance("RSA");
        ciph.init(Cipher.ENCRYPT_MODE, this.privKey.getValue());
        return ciph.doFinal(msg.getValue().getBytes());
	}
	
	public String decodeMsg(byte[] msg)
		throws NoSuchPaddingException, NoSuchAlgorithmException, 
		InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		
		Cipher ciph = Cipher.getInstance("RSA");
		ciph.init(Cipher.DECRYPT_MODE, this.pubKey.getValue());
        return ciph.doFinal(msg).toString();
	}
}
