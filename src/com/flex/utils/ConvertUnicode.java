package com.flex.utils;

public class ConvertUnicode {
	
	
	private static char[] tcvn3chars = 
	{ 'µ', '¸', '¶', '·', '¹', '¨', '»', '¾',
		'¼', '½', 'Æ', '©', 'Ç', 'Ê', 'È', 'É', 'Ë', '®', 'Ì', 'Ð', 'Î',
		'Ï', 'Ñ', 'ª', 'Ò', 'Õ', 'Ó', 'Ô', 'Ö', '×', 'Ý', 'Ø', 'Ü', 'Þ',
		'ß', 'ã', 'á', 'â', 'ä', '«', 'å', 'è', 'æ', 'ç', 'é', '¬', 'ê',
		'í', 'ë', 'ì', 'î', 'ï', 'ó', 'ñ', 'ò', 'ô', '­', 'õ', 'ø', 'ö',
		'÷', 'ù', 'ú', 'ý', 'û', 'ü', 'þ', 
		'µ', '¸', '¶', '·', '¹', '¡', '»', '¾',
		'¼', '½', 'Æ', '¢', 'Ç', 'Ê', 'È', 'É', 'Ë', '§', 'Ì', 'Ð', 'Î',
		'Ï', 'Ñ', '£', 'Ò', 'Õ', 'Ó', 'Ô', 'Ö', '×', 'Ý', 'Ø', 'Ü', 'Þ',
		'ß', 'ã', 'á', 'â', 'ä', '¤', 'å', 'è', 'æ', 'ç', 'é', '¥', 'ê',
		'í', 'ë', 'ì', 'î', 'ï', 'ó', 'ñ', 'ò', 'ô', '¦', 'õ', 'ø', 'ö',
		'÷', 'ù', 'ú', 'ý', 'û', 'ü', 'þ'};
		private static String[] vnichars ={ "aø", "aù", "aû", "aõ", "aï", "aê", "aè", "aé",
			"aú", "aü", "aë", "aâ", "aà", "aá", "aå", "aã", "aä", "ñ", "eø", "eù", "eû",
			"eõ", "eï", "eâ", "eà", "eá", "eå", "eã", "eä", "ì", "í", "æ", "ó", "ò",
			"oø", "où", "oû", "oõ", "oï", "oâ", "oà", "oá", "oå", "oã", "oä", "ô", "ôø",
			"ôù", "ôû", "ôõ", "ôï", "uø", "uù", "uû", "uõ", "uï", "ö", "öø", "öù", "öû",
			"öõ", "öï", "yø", "yù", "yû", "yõ", "î", 
			"AØ", "AÙ", "AÛ", "AÕ", "AÏ", "AÊ", "AÈ", "AÉ",
			"AÚ", "AÜ", "AË", "AÂ", "AÀ", "AÁ", "AÅ", "AÃ", "AÄ", "Ñ", "EØ", "EÙ", "EÛ",
			"EÕ", "EÏ", "EÂ", "EÀ", "EÁ", "EÅ", "EÃ", "EÄ", "Ì", "Í", "Æ", "Ó", "Ò",
			"OØ", "OÙ", "OÛ", "OÕ", "OÏ", "OÂ", "OÀ", "OÁ", "OÅ", "OÃ", "OÄ", "Ô", "ÔØ",
			"ÔÙ", "ÔÛ", "ÔÕ", "ÔÏ", "UØ", "UÙ", "UÛ", "UÕ", "UÏ", "Ö", "ÖØ", "ÖÙ", "ÖÛ",
			"ÖÕ", "ÖÏ", "YØ", "YÙ", "YÛ", "YÕ", "Î"};
		
		private static char[] unichars = { 'à', 'á', 'ả', 'ã', 'ạ', 'ă', 'ằ', 'ắ',
			'ẳ', 'ẵ', 'ặ', 'â', 'ầ', 'ấ', 'ẩ', 'ẫ', 'ậ', 'đ', 'è', 'é', 'ẻ',
			'ẽ', 'ẹ', 'ê', 'ề', 'ế', 'ể', 'ễ', 'ệ', 'ì', 'í', 'ỉ', 'ĩ', 'ị',
			'ò', 'ó', 'ỏ', 'õ', 'ọ', 'ô', 'ồ', 'ố', 'ổ', 'ỗ', 'ộ', 'ơ', 'ờ',
			'ớ', 'ở', 'ỡ', 'ợ', 'ù', 'ú', 'ủ', 'ũ', 'ụ', 'ư', 'ừ', 'ứ', 'ử',
			'ữ', 'ự', 'ỳ', 'ý', 'ỷ', 'ỹ', 'ỵ', 
			'À', 'Á', 'Ả', 'Ã', 'Ạ', 'Ă', 'Ằ', 'Ắ',
			'Ẳ', 'Ẵ', 'Ặ', 'Â', 'Ầ', 'Ấ', 'Ẩ', 'Ẫ', 'Ậ', 'Đ', 'È', 'É', 'Ẻ',
			'Ẽ', 'Ẹ', 'Ê', 'Ề', 'Ế', 'Ể', 'Ễ', 'Ệ', 'Ì', 'Í', 'Ỉ', 'Ĩ', 'Ị',
			'Ò', 'Ó', 'Ỏ', 'Õ', 'Ọ', 'Ô', 'Ồ', 'Ố', 'Ổ', 'Ỗ', 'Ộ', 'Ơ', 'Ờ',
			'Ớ', 'Ở', 'Ỡ', 'Ợ', 'Ù', 'Ú', 'Ủ', 'Ũ', 'Ụ', 'Ư', 'Ừ', 'Ứ', 'Ử',
			'Ữ', 'Ự', 'Ỳ', 'Ý', 'Ỷ', 'Ỹ', 'Ỵ'};
	

	/**
	 * Multiple String replacement.
	 * 
	 * @param text
	 *            Text to be performed on
	 * @param pattern
	 *            Find text
	 * @param replace
	 *            Replace text
	 * @return Result text
	 */
	String replaceString(String text, final String[] pattern,
			final String[] replace) {
		int startIndex;
		int foundIndex;
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < pattern.length; i++) {
			startIndex = 0;
			// Clear the buffer
			result.setLength(0);

			// Look for a pattern to replace
			while ((foundIndex = text.indexOf(pattern[i], startIndex)) >= 0) {
				result.append(text.substring(startIndex, foundIndex));
				result.append(replace[i]);
				startIndex = foundIndex + pattern[i].length();
			}
			result.append(text.substring(startIndex));
			text = result.toString();
		}
		return text;
	}

		
	public static String UTF8ToTCVN3(String value) {
		if (Lib.isBlank(value))
			return "";
		value=value.replace("ệ", "ệ");
		value=value.replace("ĩ", "ĩ");
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char chr = chars[i];
			// if (chr < (char)256)
			{
				for (int j = 0; j < unichars.length; j++) {
					if (chr == unichars[j]) {
						chars[i] = tcvn3chars[j];
						break;
					}
				}
			}
		}
	 return new String(chars);
	 }
	// public static String TCVN3ToUnicode(String value) {
	// if(Lib.isBlank(value))
	// return "";
	// char[] chars = value.toCharArray();
	// for (int i = 0; i < chars.length; i++){
	// char chr=chars[i];
	// // if (chr < (char)256)
	// {
	// for (int j = 0; j < tcvnchars.length; j++) {
	// if(chr==tcvnchars[j])
	// {
	// chars[i]=unichars[j];
	// break;
	// }
	// }
	// }
	// }
	// return new String(chars);
	// }
	/**
	 * Converts UTF-8 to Unicode.
	 * 
	 * @param str
	 *            Source string in UTF-8 encoding
	 * @return Unicode string
	 */
	public String convertUT82Unicode(String str) {
		try {
			byte[] aBytes = str.getBytes("ISO8859_1");

			// UTF-8 byte strings are frequently corrupted during handling or
			// transmission.
			// Specifically, no-break spaces (0xA0 or 160) usually become
			// regular spaces (0x20).
			// In the Vietnamese Unicode set, there are only four characters
			// whose UTF-8
			// representations contain NBSP.

			// UTF-8 byte values Unicode name
			// ------------------------------
			// 195 160 a with grave
			// 225 186 160 A with dot below
			// 198 160 O with horn
			// 225 187 160 O with horn and tilde

			// Replace spaces with NBSP where applicable
			for (int i = 1; i < aBytes.length; i++) {
				if (aBytes[i] == 0x20) // space?
				{
					if ((aBytes[i - 1] == (byte) 0xC3)
							|| ((i > 1) && (aBytes[i - 2] == (byte) 0xE1) && (aBytes[i - 1] == (byte) 0xBA))
							|| (aBytes[i - 1] == (byte) 0xC6)
							|| ((i > 1) && (aBytes[i - 2] == (byte) 0xE1) && (aBytes[i - 1] == (byte) 0xBB))) {
						aBytes[i] = (byte) 0xA0; // NBSP
					}
				}
			}

			return new String(aBytes, "UTF-8");

		} catch (java.io.UnsupportedEncodingException exc) {
			throw new RuntimeException("Unsupported encoding.");
		}
	}

	public String convertUT82VNI(String value) {
		if (Lib.isBlank(value))
			return "";
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {

			String strchr = value.substring(i, i + 1);
			for (int j = 0; j < unichars.length; j++) {
				if (strchr.equals(String.valueOf(unichars[j]))) {
					strchr = vnichars[j];
					break;
				} else {

				}
			}
			str.append(strchr);

		}

		return str.toString();
	}

	

	
}
