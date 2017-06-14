package helpers;

import java.util.Base64;

public class Crypt {
	public static String encrypt(String strClearText) throws Exception{
		return Base64.getEncoder().encodeToString(strClearText.getBytes("utf-8"));
	}
	
	public static String decrypt(String strEncrypted) throws Exception{
		return new String(Base64.getDecoder().decode(strEncrypted), "utf-8");
	}
}
