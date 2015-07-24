package org.bu.file.misc;

public class DirHolder {

	public static String getDir(String baseDir, String fileid, String subPath) {
		StringBuilder builder = new StringBuilder(baseDir);
		builder.append(fileid);
		builder.append(subPath);
		return builder.toString();
	}

	public static void main(String[] args) {
		// Set<String> codes = new HashSet<String>();
		// for (int i = 0; i < 65535; i++) {
		// codes.add(subDir("", UUID.randomUUID().toString(), 1000));
		// }
		// System.out.println(codes.size() + ",\n" + codes);

	}

}
