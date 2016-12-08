package konrad.java.sha1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Util {

	private SHA1Util() {
	}

	private static byte[] calculateSHA1Hash(byte[] arrayOfBytes) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

		md.reset();
		md.update(arrayOfBytes);

		return md.digest();
	}

	public static String calculateSHA1Hash(String str) {
		byte[] result = calculateSHA1Hash(str.getBytes());

		StringBuilder hexString = new StringBuilder();
		if (result != null) {
			for (byte aResult: result) {
                String hex = Integer.toHexString(0xFF & aResult);
                if (hex.length() == 1) {
                    hexString.append("0");
					hexString.append(hex);
                } else {
                    hexString.append(hex);
                }
            }
		}

		return hexString.toString();
	}

	public static void main(String[] args) {
		System.out.println(SHA1Util.calculateSHA1Hash("861012BOTKO404"));
		System.out.println(SHA1Util.calculateSHA1Hash("1234567890CcBOTKO404"));
	}

}
