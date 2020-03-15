package org.kakarrot.util;

import java.net.URLConnection;

public final class CheckFileType {

	public static int checkingImg(String fname) {
		String result = URLConnection.guessContentTypeFromName(fname);
		return result == null ? 0 : (result.startsWith("image") ? 1 : 0);
	}
	
}
