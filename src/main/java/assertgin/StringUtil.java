package assertgin;

public class StringUtil {

	// to not force any dependencies..

	public static String uncapitalize(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		return new StringBuffer(str.length())
				.append(Character.toLowerCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	public static String capitalize(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		return new StringBuffer(str.length())
				.append(Character.toUpperCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

}
