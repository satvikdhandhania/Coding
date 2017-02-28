import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class SHA1 {
	/*	hello
	 * 	SHA-1 (Hex):F7FF9E8B7BB2E09B70935A5D785E0CC5D9D0ABF0
		SHA-1 (Base 64): 9/+ei3uy4Jtwk1pdeF4MxdnQq/A=
		MD5: (Hex): 8B1A9953C4611296A827ABF8C47804D7
		MD5: (Base 64): ixqZU8RhEpaoJ6v4xHgE1w==
	 * 
	 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data = "Hello";
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.update(data.getBytes());
			byte[] messageDigestSHA1 = messageDigest.digest();
			StringBuffer stringBuffer = new StringBuffer();
			for (byte bytes : messageDigestSHA1) {
				stringBuffer.append(String.format("%02x", bytes & 0xff));
			}
			System.out.println("data:" + data);
			System.out.println("digestedSHA1(hex):" + stringBuffer.toString());

			String encoded = DatatypeConverter.printBase64Binary(messageDigestSHA1);
			System.out.println("BAse64: "+encoded);

		} catch (NoSuchAlgorithmException exception) {
			exception.printStackTrace();
		}
	}

}
