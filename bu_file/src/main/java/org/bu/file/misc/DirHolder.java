package org.bu.file.misc;


public class DirHolder {

	public static String subDir(String baseDir, String fileid, int subDirSize) {
		StringBuilder builder = new StringBuilder(baseDir);
		builder.append(stringToAscii(fileid) % subDirSize);
		builder.append("/");
		return builder.toString();
	}

	public static int stringToAscii(String value) {
		int sbu = 0;
		for (char chr : value.toCharArray()) {
			sbu += (int) chr;
		}
		return sbu;
	}

	public static void main(String[] args) {
//		Set<String> codes = new HashSet<String>();
//		for (int i = 0; i < 65535; i++) {
//			codes.add(subDir("", UUID.randomUUID().toString(), 1000));
//		}
//		System.out.println(codes.size() + ",\n" + codes);
		
		System.out.println(subDir("..", "jiangxs_session_id_mid.jpg", 124));
	}

}
