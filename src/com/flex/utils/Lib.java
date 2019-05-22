package com.flex.utils;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: FSS JSC @2008</p>
 * @author tritich
 * @version 1.0
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import com.flex.dbmanager.redis.IRedisProvider;
import com.flex.entities.worker.LocationEntity;
import com.flex.entities.worker.TrackingEntity;
import com.flex.utils.polygon.Line;
import com.flex.utils.polygon.Point;
import com.flex.utils.polygon.Polygon;
import com.flex.utils.polygon.Polygon.Builder;
import com.google.gson.Gson;
import java.util.Base64;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author tritich
 * @version 1.0
 */
public final class Lib {
	private static FLLogger _libLog = FLLogger.getLogger("liblog/log");
	private static char[] SPECIAL_CHARACTERS = { ' ', '!', '"', '#', '$', '%', '*', '+', ',', ':', '<', '=', '>', '?',
			'@', '[', '\\', ']', '^', '`', '|', '~', 'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ',
			'Ù', 'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă',
			'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ',
			'ẫ', 'Ậ', 'ậ', 'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế',
			'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
			'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ',
			'Ừ', 'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự', };

	private static char[] REPLACEMENTS = { '-', '\0', '\0', '\0', '\0', '\0', '\0', '_', '\0', '_', '\0', '\0', '\0',
			'\0', '\0', '\0', '_', '\0', '\0', '\0', '\0', '\0', 'A', 'A', 'A', 'A', 'E', 'E', 'E', 'I', 'I', 'O', 'O',
			'O', 'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y',
			'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
			'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e', 'E', 'e',
			'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
			'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'U', 'u', 'U', 'u',
			'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', };

	private static String[][] htmlEscape = { { "&lt;", "<" }, { "&gt;", ">" }, { "&amp;", "&" }, { "&quot;", "\"" },
			{ "&agrave;", "à" }, { "&Agrave;", "À" }, { "&acirc;", "â" }, { "&auml;", "ä" }, { "&Auml;", "Ä" },
			{ "&Acirc;", "Â" }, { "&aring;", "å" }, { "&Aring;", "Å" }, { "&aelig;", "æ" }, { "&AElig;", "Æ" },
			{ "&ccedil;", "ç" }, { "&Ccedil;", "Ç" }, { "&eacute;", "é" }, { "&Eacute;", "É" }, { "&egrave;", "è" },
			{ "&Egrave;", "È" }, { "&ecirc;", "ê" }, { "&Ecirc;", "Ê" }, { "&euml;", "ë" }, { "&Euml;", "Ë" },
			{ "&iuml;", "ï" }, { "&Iuml;", "Ï" }, { "&ocirc;", "ô" }, { "&Ocirc;", "Ô" }, { "&ouml;", "ö" },
			{ "&Ouml;", "Ö" }, { "&oslash;", "ø" }, { "&Oslash;", "Ø" }, { "&szlig;", "ß" }, { "&ugrave;", "ù" },
			{ "&Ugrave;", "Ù" }, { "&ucirc;", "û" }, { "&Ucirc;", "Û" }, { "&uuml;", "ü" }, { "&Uuml;", "Ü" },
			{ "&nbsp;", " " }, { "&copy;", "\u00a9" }, { "&reg;", "\u00ae" }, { "&euro;", "\u20a0" } };

	// public static boolean checkSpecialChar(String str) throws
	// PatternSyntaxException {
	// String regEx =
	// ".*[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\]+.*";
	// Pattern p = Pattern.compile(regEx);
	// Matcher m = p.matcher(str);
	// return m.matches();
	// }
	//
	// public static String filterString(String str) throws
	// PatternSyntaxException {
	// String regEx=
	// "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\]";
	// Pattern p = Pattern.compile(regEx);
	// Matcher m = p.matcher(str);
	// return m.replaceAll("_").trim();
	// }
	public static final String unescapeHTML(String s, int start) {
		int i, j, k;

		i = s.indexOf("&", start);
		start = i + 1;
		if (i > -1) {
			j = s.indexOf(";", i);
			/*
			 * we don't want to start from the beginning the next time, to
			 * handle the case of the & thanks to Pieter Hertogh for the bug
			 * fix!
			 */
			if (j > i) {
				// ok this is not most optimized way to
				// do it, a StringBuffer would be better,
				// this is left as an exercise to the reader!
				String temp = s.substring(i, j + 1);
				// search in htmlEscape[][] if temp is there
				k = 0;
				while (k < htmlEscape.length) {
					if (htmlEscape[k][0].equals(temp))
						break;
					else
						k++;
				}
				if (k < htmlEscape.length) {
					s = s.substring(0, i) + htmlEscape[k][1] + s.substring(j + 1);
					return unescapeHTML(s, i); // recursive call
				}
			}
		}
		return s;
	}

	public static String escapeHtmlFull(String s) {
		StringBuilder b = new StringBuilder(s.length());
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch >= '0' && ch <= '9') {
				// safe
				b.append(ch);
			} else if (Character.isWhitespace(ch)) {
				// paranoid version: whitespaces are unsafe - escape
				// conversion of (int)ch is naive
				b.append("&#").append((int) ch).append(";");
			} else if (Character.isISOControl(ch)) {
				// paranoid version:isISOControl which are not isWhitespace
				// removed !
				// do nothing do not include in output !
			} else if (Character.isHighSurrogate(ch)) {
				int codePoint;
				if (i + 1 < s.length() && Character.isSurrogatePair(ch, s.charAt(i + 1))
						&& Character.isDefined(codePoint = (Character.toCodePoint(ch, s.charAt(i + 1))))) {
					b.append("&#").append(codePoint).append(";");
				} else {
				}
				i++; // in both ways move forward
			} else if (Character.isLowSurrogate(ch)) {
				i++; // move forward,do nothing do not include in output !
			} else {
				if (Character.isDefined(ch)) {
					// paranoid version
					// the rest is unsafe, including <127 control chars
					b.append("&#").append((int) ch).append(";");
				}
				// do nothing do not include undefined in output!
			}
		}
		return b.toString();
	}

	/**
	 * <p>
	 * Unescapes any JavaScript literals found in the <code>String</code>.
	 * </p>
	 * <p>
	 * For example, it will turn a sequence of <code>'\'</code> and
	 * <code>'n'</code> into a newline character, unless the <code>'\'</code> is
	 * preceded by another <code>'\'</code>.
	 * </p>
	 * 
	 * @param str
	 *            the <code>String</code> to unescape, may be null
	 * @return A new unescaped <code>String</code>, <code>null</code> if null
	 *         string input
	 */
	public static String unescapeJavaScript(String str) {
		if (str == null) {
			return null;
		}

		StringBuffer writer = new StringBuffer(str.length());
		int sz = str.length();
		StringBuffer unicode = new StringBuffer(4);
		boolean hadSlash = false;
		boolean inUnicode = false;

		for (int i = 0; i < sz; i++) {
			char ch = str.charAt(i);
			if (inUnicode) {
				// if in unicode, then we're reading unicode
				// values in somehow
				unicode.append(ch);
				if (unicode.length() == 4) {
					// unicode now contains the four hex digits
					// which represents our unicode character
					try {
						int value = Integer.parseInt(unicode.toString(), 16);
						writer.append((char) value);
						unicode.setLength(0);
						inUnicode = false;
						hadSlash = false;
					} catch (NumberFormatException nfe) {
						throw new IllegalArgumentException(
								"Unable to parse unicode value: " + unicode + " cause: " + nfe);
					}
				}
				continue;
			}

			if (hadSlash) {
				// handle an escaped value
				hadSlash = false;
				switch (ch) {
				case '\\':
					writer.append('\\');
					break;
				case '\'':
					writer.append('\'');
					break;
				case '\"':
					writer.append('"');
					break;
				case 'r':
					writer.append('\r');
					break;
				case 'f':
					writer.append('\f');
					break;
				case 't':
					writer.append('\t');
					break;
				case 'n':
					writer.append('\n');
					break;
				case 'b':
					writer.append('\b');
					break;
				case 'u':
					// uh-oh, we're in unicode country....
					inUnicode = true;
					break;
				default:
					writer.append(ch);
					break;
				}
				continue;
			} else if (ch == '\\') {
				hadSlash = true;
				continue;
			}
			writer.append(ch);
		}

		if (hadSlash) {
			// then we're in the weird case of a \ at the end of the
			// string, let's output it anyway.
			writer.append('\\');
		}

		return writer.toString();
	}

	/**
	 * xoa het ky dac biet, chi de lai ky tu [a-zA-Z_0-9]
	 * 
	 * @param s
	 * @return
	 */
	public static String replaceAllSpecialCharacters(String s) {
		try {
			s = s.replaceAll("\\W", "");
			return s;
		} catch (Exception e) {
			return "";
		}
	}

	public static String subString(String baseStr, int beginIndex, int length) throws Exception {
		if (length <= 0) {
			return "";
		}
		int endIndex = beginIndex + length;
		return baseStr.substring(beginIndex, endIndex);
	}

	/**
	 * null save to string conversion
	 * 
	 * @param aO
	 * @return
	 */
	public static String str(Object aO) {
		if (aO == null)
			return "";
		else
			return aO.toString();
	}

	/**
	 * opposite of str(). Converts s to null if it contains ""
	 * 
	 * @param aS
	 * @return
	 */
	public static String rst(String aS) {
		if (aS != null && aS.trim().length() == 0)
			return null;
		return aS;
	}

	/**
	 * use this function will not appear error Safe for trim
	 * 
	 * @param aS
	 * @return
	 */
	public static String safeTrim(String aS) {
		if (aS == null)
			return "";
		else
			return aS.trim();
	}

	/**
	 * use this function will not appear error Safe for trim
	 * 
	 * @param aS
	 * @return
	 */
	public static String safeTrim(Object aS) {
		try {
			if (aS == null)
				return "";
			else
				return ((String) aS).trim();
		} catch (Exception ex) {
			return "";
		}

	}

	/**
	 * Get Stack Trace
	 * 
	 * @param aT
	 * @return
	 */
	public static String getStackTrace(Throwable aT) {
		StringWriter sw = new StringWriter();
		aT.printStackTrace(new PrintWriter(sw));
		return sw.getBuffer().toString();
	}

	/**
	 * Check object is not null if object is null, the function will throw
	 * exception when program you will catch this error
	 * 
	 * @param aObj
	 * @param aObjName
	 * @throws java.lang.Exception
	 */
	public static void assertNotNull(Object aObj, String aObjName) throws Exception {
		if (aObj == null)
			throw new Exception(aObjName + " must not be null");
	}

	/**
	 * Check object is not blank if object is blank, the function will throw
	 * exception when program you will catch this error
	 * 
	 * @param aS
	 * @param varName
	 * @throws java.lang.Exception
	 */
	public static void assertNotBlank(String aS, String varName) throws Exception {
		if (isBlank(aS))
			throw new Exception(varName + " must not be blank");
	}

	/**
	 * Check codition is true
	 * 
	 * @param aCondition
	 * @param aMsg
	 * @throws java.lang.Exception
	 */
	public static void assert1(boolean aCondition, String aMsg) throws Exception {
		if (!aCondition)
			throw new Exception(aMsg);
	}

	/**
	 * check String is blank or not
	 * 
	 * @param aS
	 * @return true if String is null
	 */
	public static boolean isBlank(String aS) {
		return (aS == null || "".equals(safeTrim(aS))) ? true : aS.trim().length() == 0;
	}

	/**
	 * check String is blank or not
	 * 
	 * @param aS
	 * @return true if String is null
	 */
	public static boolean isBlank(Object aS) {
		return (aS == null || "".equals(safeTrim(aS))) ? true : safeTrim(aS).length() == 0;
	}

	/**
	 * check aS is null or not
	 * 
	 * @param aS
	 * @param aDefault
	 * @return aS if s is not null else return sDefault
	 */
	public static String nvl(String aS, String aDefault) {
		return (aS != null && !safeTrim(aS).equals("")) ? aS : aDefault;
	}

	/**
	 * Tries it's best to generically deep-copy an object. Strings and Numbers
	 * are copied by reference. For all other types an attempt is made to
	 * serialize the object. If the object is not serializable, the original
	 * object reference is returned.
	 */
	public static Object copyObject(Object aO) {
		Object retval = null;

		if (aO instanceof String || aO instanceof Number)
			retval = aO;
		else {
			try { // try to stream it out
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos);
				oos.writeObject(aO);
				ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
				retval = ois.readObject();
			} // if not serializable use pointer asignment
			catch (NotSerializableException ex) {
				retval = aO;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return retval;
	}

	/**
	 * Creates an object of the specified class by calling the default
	 * constructor.
	 * 
	 * @param aClassName
	 * @return object has just created
	 */
	public static Object createObject(String aClassName) {
		return createObject(aClassName, new Class[] {}, new Object[] {});
	}

	/**
	 * Creates an object of the specified class by calling the constructor
	 * identified by the specified parameter types.
	 * 
	 * @param aClassName
	 * @param aParamTypes
	 * @param aParameters
	 * @return object has just been created
	 */
	public static Object createObject(String aClassName, Class[] aParamTypes, Object[] aParameters) {
		try {
			Class cls = Class.forName(aClassName);
			Constructor constr = cls.getConstructor(aParamTypes);
			return constr.newInstance(aParameters);
		} catch (Exception ex) {
			// ex.printStackTrace();
			StringBuffer buf = new StringBuffer(128);
			buf.append("Couldn't create ").append(aClassName).append("(");
			if (aParamTypes != null)
				for (int i = 0; i < aParamTypes.length; i++) {
					if (i > 0)
						buf.append(", ");
					buf.append(aParamTypes[i].getName());
					buf.append(" = ");
					if (aParameters != null && i < aParameters.length)
						buf.append(str(aParameters[i]));
				}
			buf.append(")\n").append("Reason: ");
			buf.append(ex.getClass().getName()).append(": ").append(getStackTrace(ex));
			throw new RuntimeException(buf.toString());
		}
	}

	/**
	 * replace all string
	 * 
	 * @param aSource
	 * @param aFind
	 * @param aReplaceBy
	 * @return string has just been repalced
	 */
	public static String replace(String aSource, String aFind, String aReplaceBy) {
		if (aSource == null || aFind == null)
			return aSource;
		if (aSource.equals("") || aFind.equals(""))
			return aSource;
		if (aReplaceBy == null)
			aReplaceBy = "";
		StringBuffer result = new StringBuffer();
		int len = aFind.length();

		int oldPos = 0;
		int pos = aSource.indexOf(aFind);
		while (pos > -1) {
			result.append(aSource.substring(oldPos, pos));
			result.append(aReplaceBy);
			oldPos = pos + len;
			pos = aSource.indexOf(aFind, oldPos);
		}
		result.append(aSource.substring(oldPos));

		return result.toString();
	}

	/**
	 * encode string to md5
	 * 
	 * @param aClear
	 * @return string was encoded
	 * @throws java.lang.Exception
	 */
	public static String toMd5String(String aClear) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] b;
			b = md.digest(aClear.getBytes());
			StringBuffer md5;
			md5 = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				int u = b[i] & 0xFF; // unsigned conversion
				if (u < 0x10)
					md5.append("0");
				md5.append(Integer.toHexString(u));
			}
			return md5.toString();
		} catch (Exception ex) {
			return aClear;
		}
	}

	/**
	 * get now string with format yyyy/MM/dd hh:mm:ss
	 * 
	 * @return
	 */
	public static String getDateNow() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		return padLeft(String.valueOf(calendar.get(Calendar.YEAR)), 4) + "/"
				+ padLeft(String.valueOf((calendar.get(Calendar.MONTH) + 1)), 2) + "/"
				+ padLeft(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2) + " "
				+ padLeft(String.valueOf(calendar.get(Calendar.HOUR)), 2) + ":"
				+ padLeft(String.valueOf(calendar.get(Calendar.MINUTE)), 2) + ":"
				+ padLeft(String.valueOf(calendar.get(Calendar.SECOND)), 2);
	}

	public static int getYearByDate(Date date) {
		if (null == date) {
			return -1;
		}
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static int getMonthByDate(Date date) {
		if (null == date) {
			return -1;
		}
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int getMonthByDay(Date date) {
		if (null == date) {
			return -1;
		}
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * get time string yyyymmddhhmmdd
	 * 
	 * @return
	 */
	public static String getTimeString() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		return padLeft(String.valueOf(calendar.get(Calendar.YEAR)), 4)
				+ padLeft(String.valueOf((calendar.get(Calendar.MONTH) + 1)), 2)
				+ padLeft(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2)
				+ padLeft(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), 2)
				+ padLeft(String.valueOf(calendar.get(Calendar.MINUTE)), 2)
				+ padLeft(String.valueOf(calendar.get(Calendar.SECOND)), 2);

	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Calendar getCalendar(int year, int month, int day, int hour, int minute, int second) {
		try {
			Calendar cal = Calendar.getInstance(TimeZone.getDefault());
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
			cal.set(Calendar.HOUR_OF_DAY, hour);
			cal.set(Calendar.MINUTE, minute);
			cal.set(Calendar.SECOND, second);
			return cal;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * This method is to get string dd/mm/yyyy hh:mm
	 * 
	 * @author Trinh Nguyen
	 * @date 16-02-2009
	 * 
	 * @return
	 */
	public static String getCurrentDatetime() {
		try {
			Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
			return Lib.padLeft(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2) + "/"
					+ Lib.padLeft(String.valueOf((calendar.get(Calendar.MONTH) + 1)), 2) + "/"
					+ Lib.padLeft(String.valueOf(calendar.get(Calendar.YEAR)), 4) + " "
					+ Lib.padLeft(String.valueOf(calendar.get(Calendar.HOUR)), 2) + ":"
					+ Lib.padLeft(String.valueOf(calendar.get(Calendar.MINUTE)), 2);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * add time to date param
	 * 
	 * @param date
	 * @param amount:
	 *            2
	 * @param field:
	 *            Calendar.DAY_OF_MONTH
	 * @return
	 */
	public static Date addTime(Date date, int amount, int field) {
		try {
			Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
			calendar.setTime(date);
			calendar.add(field, amount);
			return calendar.getTime();
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * convert tu timezone den utc time
	 * 
	 * @param time
	 * @param from
	 * @return
	 */
	public static long toUTC(long time, TimeZone from) {
		long t = time - from.getOffset(time);
		return t;
	}

	/**
	 * ....gio...phut, ngày...tháng...năm......
	 * 
	 * @return
	 */
	public static String getCurrentDayByWord2() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		String date = Lib.padLeft(String.valueOf(calendar.get(Calendar.HOUR)), 2) + " giờ, ";
		date += Lib.padLeft(String.valueOf(calendar.get(Calendar.MINUTE)), 2) + " phút,  ngày ";
		date += Lib.padLeft(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2) + " tháng ";
		date += Lib.padLeft(String.valueOf(calendar.get(Calendar.MONTH) + 1), 2) + " năm ";
		date += Lib.padLeft(String.valueOf(calendar.get(Calendar.YEAR)), 4);
		return date;
	}

	/**
	 * This method is to get string ddmmyyyy
	 * 
	 * @author Trinh Nguyen
	 * @date 28-03-2009
	 * 
	 * @return
	 */
	public static String getCurrentDayStr() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH) + 1;
		String year = cal.get(Calendar.YEAR) + "";
		String yearS = year.substring(2, 4);
		return Lib.padLeft(String.valueOf(day), 2) + Lib.padLeft(String.valueOf(month), 2) + yearS;
	}

	/**
	 * This method is to get string ddmmyy
	 * 
	 * @return
	 */
	public static String getCurrentDayStrDDMMYY() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH) + 1;
		String year = cal.get(Calendar.YEAR) + "";
		String yearS = Lib.padLeft(year, 2);
		return Lib.padLeft(String.valueOf(day), 2) + Lib.padLeft(String.valueOf(month), 2) + yearS;
	}

	/**
	 * This method is to get current day dd/mm/yyyy
	 * 
	 * @author Trinh Nguyen
	 * @date 12-03-2009
	 * 
	 * @return
	 */
	public static String getStringCurrentDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		return Lib.padLeft(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2) + "/"
				+ Lib.padLeft(String.valueOf((calendar.get(Calendar.MONTH) + 1)), 2) + "/"
				+ Lib.padLeft(String.valueOf(calendar.get(Calendar.YEAR)), 4);
	}

	public static Date getCurrentDateZeroHour() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH), 0,
				0, 0);
		return calendar.getTime();
	}

	public static Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		return calendar.getTime();
	}

	public static String getCurrentDate(String patern) {
		SimpleDateFormat gmtDateFormat = new SimpleDateFormat(patern);
		gmtDateFormat.setTimeZone(TimeZone.getDefault());
		return gmtDateFormat.format(new Date());
	}

	/**
	 * This method is to get current day yyyy/MM/dd
	 * 
	 * @author Tich Nguyen
	 * @date 09-04-2010
	 * 
	 * @return
	 */
	public static String getCurrentJPDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		return Lib.padLeft(String.valueOf(calendar.get(Calendar.YEAR)), 4) + "/"
				+ Lib.padLeft(String.valueOf((calendar.get(Calendar.MONTH) + 1)), 2) + "/"
				+ Lib.padLeft(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2);
	}

	public static String getCurrentDayByWord() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		return "Ngày " + Lib.padLeft(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2) + " Tháng "
				+ Lib.padLeft(String.valueOf((calendar.get(Calendar.MONTH) + 1)), 2) + " Năm "
				+ Lib.padLeft(String.valueOf(calendar.get(Calendar.YEAR)), 4);
	}

	/**
	 * convert date to word dd/MM/yyyy to Ngay ... Thang ... Nam
	 * 
	 * @param strDate
	 * @return
	 */
	public static String date2Word(String strDate) {
		strDate = strDateConvertForView(strDate);
		if (isBlank(strDate))
			return "";
		String[] arrDate = strDate.split("/");
		return "Ngày " + padLeft(safeTrim(arrDate[0]), 2) + " Tháng " + padLeft(safeTrim(arrDate[1]), 2) + " Năm "
				+ arrDate[2];
	}

	/**
	 * convert date to word Date to ngay .. thang .. nam ....
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToWord(Date date) {
		if (date == null)
			return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return "Ngày " + Lib.padLeft(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)), 2) + " Tháng "
				+ Lib.padLeft(String.valueOf((cal.get(Calendar.MONTH) + 1)), 2) + " Năm "
				+ Lib.padLeft(String.valueOf(cal.get(Calendar.YEAR)), 4);
	}

	public static String dateToENWord(Date date) {
		try {
			if (date == null)
				return "";
			String strDate = dateToStringDDMMYYYY(date);
			date = stringToDate(strDate);
			String str = date.toLocaleString();
			str = str.replace(" 12:00:00 AM", "");
			return str;
		} catch (Exception ex) {
			return "";
		}
	}

	public static String strToUnicode(String aStr) {
		String r = "";

		try {
			aStr = removeBadChars(aStr);
			char[] ch = aStr.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				r += "\\u" + Integer.toHexString(ch[i]);
			}
			return r;
		} catch (Exception e) {
			return aStr;
		}
	}

	public static String removeBadChars(String s) {
		if (s == null)
			return null;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isHighSurrogate(s.charAt(i)))
				continue;
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}

	public static String removeBadUtf8Char(String str) {
		try {
			CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
			decoder.onMalformedInput(CodingErrorAction.REPLACE);
			decoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
			ByteBuffer bb = ByteBuffer.wrap(str.getBytes());
			CharBuffer decode;

			decode = decoder.decode(bb);

			return decode.toString();
		} catch (CharacterCodingException e) {
			return str;
		}
	}

	/**
	 * get Day at client
	 * 
	 * @return
	 */
	public static int getCurrentDay() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * get Month at client
	 * 
	 * @return
	 */
	public static int getCurrentMonth() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int getCurrentSeason() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int month = calendar.get(Calendar.MONTH) + 1;
		int season = 1;
		switch (month) {
		case 1:
		case 2:
		case 3:
			season = 1;
			break;
		case 4:
		case 5:
		case 6:
			season = 2;
			break;
		case 7:
		case 8:
		case 9:
			season = 3;
			break;
		case 10:
		case 11:
		case 12:
			season = 4;
			break;
		}
		return season;
	}

	/**
	 * get Month at client
	 * 
	 * @return
	 */
	public static int getCurrentHour() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * get Minute at client
	 * 
	 * @return
	 */
	public static int getCurrentMinute() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * get Second at client
	 * 
	 * @return
	 */
	public static int getCurrentSecond() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * get Year at client
	 * 
	 * @return
	 */
	public static int getCurrentYear() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 
	 * @return String
	 */
	public static String getYMDHMSMS() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		return padLeft(String.valueOf(calendar.get(Calendar.DATE)), 4) + "/"
				+ padLeft(String.valueOf((calendar.get(Calendar.MONTH) + 1)), 2) + "/"
				+ padLeft(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2) + " "
				+ padLeft(String.valueOf(calendar.get(Calendar.HOUR)), 2) + ":"
				+ padLeft(String.valueOf(calendar.get(Calendar.MINUTE)), 2) + ":"
				+ padLeft(String.valueOf(calendar.get(Calendar.SECOND)), 2) + calendar.get(Calendar.MILLISECOND)
				+ calendar.get(Calendar.AM_PM);
	}

	/**
	 * format date in "yyyy/MM/dd hh:mm:ss" type
	 * 
	 * @param aD
	 * @return
	 */
	public static String dateToString(java.util.Date aD) {

		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			return df.format(aD);
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * format date with format
	 * 
	 * @param aD
	 * @return
	 */
	public static String dateToString(java.util.Date aD, String format) {

		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			return df.format(aD);
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * format date in "yyyy-MM-dd HH:mm:ss" type
	 * 
	 * @param aD
	 * @return
	 */
	public static String dateTo_yyyyMMddHHmmss(java.util.Date aD) {

		return dateToString(aD, "yyyy-MM-dd HH:mm:ss");

	}

	/**
	 * format date in "yyyy/MM/dd" type
	 * 
	 * @param aD
	 * @return
	 */
	public static String dateToStringYYYYMMDD(java.util.Date aD) {

		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			return df.format(aD);
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * format date in "dd/MM/yyyy" stype
	 * 
	 * @param aD
	 * @return
	 */
	public static String dateToStringDDMMYYYY(java.util.Date aD) {

		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			return df.format(aD);
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * format date in "dd/MM/yyyy" stype
	 * 
	 * @param aS
	 * @return
	 */
	public static java.util.Date stringToDate(String aS) {
		if (isBlank(aS))
			return null;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date todate;
		try {
			todate = df.parse(aS);
			// System.out.println("Today = " + df.format(todate));
		} catch (Exception e) {
			return null;
		}
		return todate;

	}

	// public static java.util.Date StringToDate(String aS, String format) {
	// if (isBlank(aS))
	// return null;
	// DateFormat df = new SimpleDateFormat(format);
	// Date todate;
	// try {
	// todate = df.parse(aS);
	// // System.out.println("Today = " + df.format(todate));
	// } catch (Exception e) {
	//
	// return StringToDate(aS);
	// }
	// return todate;
	//
	// }
	/**
	 * connvert string date to date with current string format
	 * 
	 * @param dateInString
	 * @param format
	 * @return
	 */
	public static java.util.Date stringToDate(String aS, String format) {
		if (isBlank(aS))
			return null;
		DateFormat df = new SimpleDateFormat(format);
		Date todate;
		try {
			todate = df.parse(aS);
			// System.out.println("Today = " + df.format(todate));
		} catch (Exception e) {

			return stringToDate(aS);
		}
		return todate;

	}

	/**
	 * connvert string date to date by format yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateInString
	 * @param format
	 * @return
	 */
	public static java.util.Date stringToDate_yyyyMMddHHmmss(String aS) {

		return stringToDate(aS, "yyyy-MM-dd HH:mm:ss");

	}

	/**
	 * connvert string date to date by format
	 * 
	 * @param dateInString
	 * @param format
	 * @return
	 */
	public static java.util.Date StringToDateFromUTC(String dateInString, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = null;
		try {
			TimeZone utcZone = TimeZone.getTimeZone("UTC");
			formatter.setTimeZone(utcZone);
			date = formatter.parse(dateInString);
			// Date myDate =
			// formatter.parse(rawQuestion.getString("AskDateTime"));
			// System.out.println(date);
			// System.out.println(formatter.format(date));
			return date;
		} catch (ParseException e) {
			return null;// e.printStackTrace();
		}
	}

	public static java.util.Date dateAddHours(Date oldDate, int hours) {
		// Date oldDate = new Date(); // oldDate == current time
		Date newDate = new Date(oldDate.getTime() + TimeUnit.HOURS.toMillis(hours)); // Adds
																						// 2
																						// hours
		return newDate;
	}

	/**
	 * convert second to string time format
	 * 
	 * @param totalSecs
	 * @return
	 */
	public static String secToTimeFormat(int totalSecs, boolean getSec) {
		try {
			int hours = totalSecs / 3600;
			int minutes = (totalSecs % 3600) / 60;
			int seconds = totalSecs % 60;
			String h = "";
			String m = "";
			String s = "";
			if (hours > 0) {
				h = String.format("%02d:", hours);
			}
			if (minutes > 0) {
				if (getSec) {
					m = String.format("%02d:", minutes);
				} else {
					m = String.format("%02d", minutes);
				}
			}
			if (seconds > 0) {
				if (getSec) {
					s = String.format("%02d", seconds);
				}
			}
			// String timeString = String.format("%02d:%02d:%02d", hours,
			// minutes, seconds);
			String timeString = h + m + s;
			return timeString;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * convert from date dd/mm/yyyy to yyyy/mm/dd hoac nguojc lai
	 * 
	 * @param strDate
	 * @return
	 */
	public static String strDateConvert(String strDate) {
		if (isBlank(strDate))
			return null;
		try {
			strDate = strDate.replace("-", "/");
			String[] strDateInfo = strDate.split("/");
			return strDateInfo[2] + "/" + strDateInfo[1] + "/" + strDateInfo[0];
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * convert date string to dd/MM/yyyy
	 * 
	 * @param strDate
	 * @return
	 */
	public static String strDateConvertForView(String strDate) {
		if (isBlank(strDate))
			return null;
		try {
			if (strDate.length() < 10)
				return strDate;

			String[] strDateInfo;
			strDate = strDate.substring(0, 10);
			if (strDate.indexOf("-") > 0) {
				strDateInfo = strDate.split("-");
			} else if (strDate.indexOf("/") > 0) {
				strDateInfo = strDate.split("/");
			} else
				return strDate;
			if (strDateInfo[0].length() == 2)
				return strDateInfo[0] + "/" + strDateInfo[1] + "/" + strDateInfo[2];
			else
				return strDateInfo[2] + "/" + strDateInfo[1] + "/" + strDateInfo[0];
		} catch (Exception ex) {
			return "";
		}
	}

	public static String strDateConvertForDB(String strDate) {
		if (isBlank(strDate))
			return null;
		try {
			String[] strDateInfo = strDate.split("/");
			if (strDateInfo[0].length() == 4)
				return strDate;

			return strDateInfo[2] + "/" + strDateInfo[1] + "/" + strDateInfo[0];
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * convert from date dd/mm/yyyy hh:mm to yyyy/mm/dd hh:mm hoac nguoc lai
	 * 
	 * @param strDate
	 * @return
	 */
	public static String strDateTimeConvert(String strDateTime) {
		if (isBlank(strDateTime))
			return null;
		try {
			strDateTime = strDateTime.replaceAll("-", "/");
			String[] strDateInfo = strDateTime.split("/");
			String[] strYearTime = safeTrim(strDateInfo[2]).split(" ");
			String str = strYearTime[0] + "/" + strDateInfo[1] + "/" + strDateInfo[0];
			if (strYearTime.length > 1) {

				if (!Lib.isBlank(strYearTime[1])) {
					if (strYearTime[1].length() == 4)
						strYearTime[1] += "0";
					if (strYearTime[1].length() >= 5)
						str += " " + strYearTime[1].substring(0, 5);
				}

			}
			return str;
		} catch (Exception ex) {

			return null;
		}
	}

	/**
	 * format date in "yyyy/MM/dd HH:mm" stype
	 * 
	 * @param aS
	 * @return
	 */
	public static java.sql.Date stringToSQLDate(String aS) {
		if (isBlank(aS))
			return null;
		try {
			SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			java.util.Date parsedDate = formater.parse(aS);
			java.sql.Date result = new java.sql.Date(parsedDate.getTime());
			return result;
		} catch (ParseException ex) {
			try {
				SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				java.util.Date parsedDate = formater.parse(aS);
				java.sql.Date result = new java.sql.Date(parsedDate.getTime());
				return result;
			} catch (ParseException ex1) {
				try {
					SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					java.util.Date parsedDate = formater.parse(aS);
					java.sql.Date result = new java.sql.Date(parsedDate.getTime());
					return result;
				} catch (ParseException ex2) {
					try {
						SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						java.util.Date parsedDate = formater.parse(aS);
						java.sql.Date result = new java.sql.Date(parsedDate.getTime());
						return result;
					} catch (ParseException ex3) {
						return null;
					}
				}
			}

		}

	}

	/**
	 * format double to string with any pattern
	 * 
	 * @param aS
	 * @param aPattern
	 * @return
	 */
	public static String format(double aS, String aPattern) {
		try {
			Locale.setDefault(Locale.US);
			DecimalFormat df = new DecimalFormat(aPattern);
			return df.format(aS);
		} catch (Exception ex) {
			return String.valueOf(aS);
		}

	}

	/**
	 * all double can't contain comma, it is just contain dot
	 * 
	 * @param aNumber
	 *            double
	 * @param aRoundUp_Down
	 *            int, it has 3 values, 0 , 1,2 if aRoundUp_Down=0 is normal .1
	 *            Up, 2 is Down
	 * @param aNumberDigiAfterComma
	 *            int
	 * @return double
	 */
	public static double round(double aNumber, int aRoundUp_Down, int aNumberDigiAfterComma) {

		try {

			switch (aRoundUp_Down) {
			case 1: {
				return (new BigDecimal(Double.toString(aNumber)).setScale(aNumberDigiAfterComma, RoundingMode.UP))
						.doubleValue();
			}
			case 2: {
				return (new BigDecimal(Double.toString(aNumber)).setScale(aNumberDigiAfterComma, RoundingMode.DOWN))
						.doubleValue();
			}
			case 0: {
				return (new BigDecimal(Double.toString(aNumber)).setScale(aNumberDigiAfterComma, RoundingMode.HALF_UP))
						.doubleValue();
			}
			default:
				return (new BigDecimal(Double.toString(aNumber)).setScale(aNumberDigiAfterComma, RoundingMode.HALF_UP))
						.doubleValue();
			}

		} catch (NumberFormatException ex) {
			return aNumber;
		}

		// String sNumber;
		// String sNumberBeforeDot;
		// String sNumberAfterDot;
		// try {
		// int PosOfDot = 0;
		// if (aNumber == 0)
		// return 0;
		// // convert double to String to split dot
		// sNumber = String.valueOf(aNumber);
		// PosOfDot = sNumber.lastIndexOf(".");
		// sNumberBeforeDot = sNumber.substring(0, PosOfDot);
		// sNumberAfterDot = sNumber.substring(PosOfDot + 1, sNumber.length());
		// if (sNumberAfterDot.equals(""))
		// return Double.parseDouble(sNumberBeforeDot);
		// if (Double.parseDouble(sNumberAfterDot) <= 0)
		// return Double.parseDouble(sNumberBeforeDot);
		// switch (aRoundUp_Down) {
		// case 2: {
		// if (aNumberDigiAfterComma == 0)
		// return Double.parseDouble(sNumberBeforeDot);
		// if (sNumberAfterDot.equals("")
		// || sNumberAfterDot.length() <= aNumberDigiAfterComma)
		// return Double.parseDouble(sNumberBeforeDot + "."
		// + sNumberAfterDot);
		// else {
		// if (sNumberAfterDot.length() <= aNumberDigiAfterComma) {
		// sNumber = sNumberBeforeDot + "." + sNumberAfterDot;
		// return Double.parseDouble(sNumber);
		// } else {
		// sNumber = sNumberBeforeDot
		// + "."
		// + sNumberAfterDot.substring(0,
		// aNumberDigiAfterComma);
		// }
		// return Double.parseDouble(sNumber);
		// }
		// }
		// case 1: {
		// if (sNumberAfterDot.length() <= aNumberDigiAfterComma) {
		// if (!sNumberAfterDot.equals(""))
		// sNumber = sNumberBeforeDot + "." + sNumberAfterDot;
		// else
		// sNumber = sNumberBeforeDot;
		// return Double.parseDouble(sNumber);
		// }
		// String s = "";
		// s = sNumberAfterDot.substring(0, aNumberDigiAfterComma);
		// if (!s.equals("")) {
		// sNumber = sNumberBeforeDot + "." + (Long.parseLong(s) + 1);
		// return Double.parseDouble(sNumber);
		// } else
		// return (Double.parseDouble(sNumberBeforeDot) + 1);
		// }
		// case 0: {
		// if (sNumberAfterDot.length() <= aNumberDigiAfterComma) {
		// if (!sNumberAfterDot.equals(""))
		// sNumber = sNumberBeforeDot + "." + sNumberAfterDot;
		// else
		// sNumber = sNumberBeforeDot;
		// return Double.parseDouble(sNumber);
		// }
		// if (aNumberDigiAfterComma == 0)
		// return java.lang.Math.round(aNumber);
		// String s = sNumberAfterDot.substring(0, aNumberDigiAfterComma);
		// String s1 = sNumberAfterDot.substring(aNumberDigiAfterComma,
		// aNumberDigiAfterComma + 1);
		// if (Integer.parseInt(s1) < 5) {
		// sNumber = sNumberBeforeDot + "." + s;
		// } else
		// sNumber = sNumberBeforeDot + "." + (Long.parseLong(s) + 1);
		// return Double.parseDouble(sNumber);
		// }
		// }
		// } catch (Exception ex) {
		// return 0;
		// }
		// return 0;
	}

	/**
	 * add zero at left of String
	 * 
	 * @param aS
	 * @param aLen
	 * @return
	 */
	public static String padLeft(String aS, int aLen) {
		if (aS == null || aS.equals(""))
			return "";
		String sZero = "";
		try {
			for (int i = 0; i < aLen; i++)
				sZero += "0";

			aS = sZero + aS;
			aS = aS.substring(aS.length() - aLen, aS.length());
			return aS;
		} catch (Exception ex) {
			return aS;
		}
	}

	/**
	 * add zero at right of String
	 * 
	 * @param aS
	 * @param aLen
	 * @return
	 */
	public static String padRight(String aS, int aLen) {
		String sZero = "";
		try {
			for (int i = 0; i < aLen; i++)
				sZero += "0";
			aS = aS + sZero;
			aS = aS.substring(0, aLen);
			return aS;
		} catch (Exception ex) {
			return aS;
		}
	}

	/**
	 * convert String to Shift_JIS endcode
	 * 
	 * @param aString
	 * @return
	 */
	public static String strToShift_JIS(String aString) {
		if (aString == null)
			return "";
		try {
			String sString = new String(aString.getBytes("ISO-8859-1"), "Shift_JIS");
			return sString;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * convert String to Shift_JIS endcode
	 * 
	 * @param aString
	 * @return
	 */
	public static String strUTF8ToShift_JIS(String aString) {
		if (aString == null)
			return "";
		try {
			String sString = new String(aString.getBytes("UTF-8"), "Shift_JIS");
			return sString;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * convert String to Shift_JIS endcode
	 * 
	 * @param aString
	 * @return
	 */
	public static String strShift_JISToUTF8(String aString) {
		if (aString == null)
			return "";
		try {
			String sString = new String(aString.getBytes("Shift_JIS"), "UTF-8");
			return sString;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * convert String to UTF-8 endcode
	 * 
	 * @param aString
	 * @return
	 */
	public static String strToUTF_8(String aString) {
		if (aString == null)
			return "";
		try {
			String sString = new String(aString.getBytes("Cp437"), "UTF-8");

			return sString;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * convert String to UTF-8 endcode
	 * 
	 * @param aString
	 * @return
	 */
	public static String strTo8859_1(String aString) {
		if (aString == null)
			return "";
		try {
			String sString = new String(aString.getBytes("UTF-8"), "ISO-8859-1");
			return sString;
		} catch (Exception ex) {
			return "";
		}
	}

	public static String strISOToUTF8(String aString) {
		if (aString == null)
			return "";
		try {
			String sString = new String(aString.getBytes("ISO-8859-1"), "UTF-8");
			return sString;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * out html tags to webpage
	 * 
	 * @param aString
	 * @return
	 */
	public static String toHtml(String aString) {
		if (aString == null || safeTrim(aString).equals(""))
			return "";
		String sString = replace(aString, "[\\x00-\\x1f]", " ");
		sString = replace(sString, "&", "&amp;");
		sString = replace(sString, "<", "&lt;");
		sString = replace(sString, ">", "&gt;");
		sString = replace(sString, "\"", "&quot;");

		return sString;
	}

	/**
	 * out html tags to webpage
	 * 
	 * @param aString
	 * @return
	 */
	public static String htmlToTag(String aString) {
		if (aString == null || safeTrim(aString).equals(""))
			return "";
		// String sString=replace(aString," ","[\\x00-\\x1f]");
		String sString = replace(aString, "&amp;", "&");
		sString = replace(sString, "&lt;", "<");
		sString = replace(sString, "&gt;", ">");
		sString = replace(sString, "&quot;", "\"");

		return sString;
	}

	/**
	 * 
	 * 
	 * @param str
	 *            return
	 */
	public static int countBytes(String str) {
		byte[] ba = str.getBytes();

		return ba.length;
	}

	/**
	 * get total page Number
	 * 
	 * @param aNumRecPerPage
	 * @param aTotalRecNum
	 * @return
	 */
	public static int getTotalPageNum(int aNumRecPerPage, int aTotalRecNum) {
		int numPage = 1;
		if (aTotalRecNum <= 0)
			return 0;
		int delta = aTotalRecNum - aNumRecPerPage;
		if (delta <= 0)
			return 1;
		else
			numPage += getTotalPageNum(aNumRecPerPage, delta);
		return numPage;
	}

	/**
	 * split String
	 * 
	 * @param str
	 * @param spltr
	 * @return
	 */
	public static String[] split(String str, String spltr) {

		Vector v = new Vector();
		boolean ok = true;

		int indx1 = 0;
		int indx2 = 0;

		str = str.trim();

		while (ok) {
			indx2 = str.indexOf(spltr, indx1);
			if (indx2 != -1) {
				v.addElement(str.substring(indx1, indx2));
				indx1 = indx2 + 1;
			} else {
				ok = false;
			}
		}

		v.addElement(str.substring(indx1, str.length()));
		String[] sResult = new String[v.size()];
		for (int i = 0; i < v.size(); i++) {
			sResult[i] = (String) v.get(i);
		}

		return sResult;
	}

	/**
	 * create folder
	 * 
	 * @param aFolderNamePath
	 * @return
	 */
	public static boolean createFolder(String aFolderNamePath) {
		try {
			File f = new File(aFolderNamePath);
			return f.mkdirs();
		} catch (Exception iox) {
			return false;
		}

	}

	/**
	 * create folder
	 * 
	 * @param aFolderNamePath
	 * @return
	 */
	public static boolean deleteFolder(String aFolderNamePath) {
		try {
			File f = new File(aFolderNamePath);
			String files[] = f.list();

			for (int i = 0; i < files.length; i++) {
				File file = new File(aFolderNamePath + "/" + files[i]);
				file.delete();
			}
			return f.delete();
		} catch (Exception iox) {
			return false;
		}

	}

	/**
	 * create folder
	 * 
	 * @param aFileNamePath
	 * @return
	 */
	public static boolean deleteFile(String aFileNamePath) {
		try {
			File f = new File(aFileNamePath);
			return f.delete();
		} catch (Exception iox) {
			return false;
		}

	}

	public static String toScript(String str) {
		if (Lib.isBlank(str))
			return "";
		str = replace(str, "\\", "\\\\");
		str = str.replace("'", "\\\'");
		str = str.replace("\"", "\\\"");
		str = str.replace("\r", "\\r");
		str = str.replace("\n", "\\n");
		return str;

	}

	public static String strToJavascript(String str) {
		if (Lib.isBlank(str))
			return "";
		str = replace(str, "\\", "\\\\");
		str = str.replace("'", "\\\'");
		str = str.replace("\"", "\\\"");
		str = str.replace("\r", "\\r");
		str = str.replace("\n", "\\n");
		return str;
	}

	/** Prevent instantiation. */
	private Lib() {
	}

	/**
	 * thuc hien cong thuc tu dinh nghia
	 * 
	 * @throws Exception
	 */
	public static Object eval(String formular) throws Exception {
		String formular_orinal = formular;

		if (isBlank(formular)) {
			throw new Exception();
		}
		if (formular.indexOf("=") > 0) {
			formular = formular.replace("=", "==");
		}
		if (formular.indexOf("====") > 0) {
			formular = formular.replace("====", "==");
		}
		if (formular.indexOf(">==") > 0) {
			formular = formular.replace(">==", ">=");
		}
		if (formular.indexOf("<==") > 0) {
			formular = formular.replace("<==", "<=");
		}
		if (formular.indexOf("&") > 0) {
			formular = formular.replace("&", "&&");
		}
		if (formular.indexOf("&&&&") > 0) {
			formular = formular.replace("&&&&", "&&");
		}
		if (formular.indexOf("|") > 0) {
			formular = formular.replace("|", "||");
		}
		if (formular.indexOf("||||") > 0) {
			formular = formular.replace("||||", "||");
		}

		Date t = Lib.getCurrentDate();
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");

		Object result = engine.eval(formular);
		long t_result = Lib.timeDiff(Lib.getCurrentDate(), t, TimeUnit.SECONDS);
		if (t_result > 0) {
			_libLog.info("Chuoi truyen vao: " + formular_orinal + "- Chuoi chuan hoa: " + formular);
			_libLog.info(" THOI GIAN engine.eval(formular) sec: " + t_result);
			// System.out.println("Chuoi truyen vao: " + formular_orinal + "-
			// Chuoi chuan hoa: " + formular);
			// System.out.println(" THOI GIAN engine.eval(formular) sec: " +
			// t_result);
		}
		return result;
	}

	/**
	 * thuc hien cong thuc tu dinh nghia param x: array param formular: sample
	 * (x-x1-x2-x3)/20
	 * 
	 * @throws Exception
	 */
	public static Object getValFromDefineSimpleStringFunc(String formular, double... x) throws Exception {
		if (x.length <= 0) {
			return 0;
		}
		formular = formular.toLowerCase();
		for (int i = x.length - 1; i >= 0; i--) {
			String xKey = "x" + i;
			if (i == 0) {
				xKey = "x";
			}
			if (formular.indexOf("=") > 0) {
				formular = formular.replace("=", "==");
			}
			if (formular.indexOf("====") > 0) {
				formular = formular.replace("====", "==");
			}
			if (formular.indexOf(">==") > 0) {
				formular = formular.replace(">==", ">=");
			}
			if (formular.indexOf("<==") > 0) {
				formular = formular.replace("<==", "<=");
			}
			if (formular.indexOf("&") > 0) {
				formular = formular.replace("&", "&&");
			}
			if (formular.indexOf("&&&&") > 0) {
				formular = formular.replace("&&&&", "&&");
			}
			if (formular.indexOf("|") > 0) {
				formular = formular.replace("|", "||");
			}
			if (formular.indexOf("||||") > 0) {
				formular = formular.replace("||||", "||");
			}
			double xVal = x[i];
			if (formular.indexOf(xKey) >= 0) {
				formular = formular.replace(xKey, xVal + "");
			}
		}
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		Object obj = engine.eval(formular);
		return obj;
	}

	public static long strToLong(String str) {
		if (isBlank(str))
			return 0;
		try {
			str = safeTrim(str);
			str = replace(str, ",", "");
			// return Long.parseLong(str);
			NumberFormat format = NumberFormat.getInstance(Locale.US);
			Number number = 0;
			number = format.parse(str);
			return number.longValue();
		} catch (Exception e) {
			return 0;
		}
	}

	public static int strToInteger(String str) {
		if (isBlank(str))
			return 0;
		try {
			str = safeTrim(str);
			str = replace(str, ",", "");
			// return Integer.parseInt(str);
			NumberFormat format = NumberFormat.getInstance(Locale.US);
			Number number = 0;
			number = format.parse(str);
			return number.intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	public static double strToDouble(String str) {
		if (isBlank(str))
			return 0;
		try {
			String dbl = safeTrim(str);
			dbl = replace(dbl, ",", "");
			// return Double.parseDouble(dbl);
			NumberFormat format = NumberFormat.getInstance(Locale.US);
			Number number = 0;
			number = format.parse(str);
			return number.doubleValue();
		} catch (Exception e) {
			return 0;
		}
	}

	public static float strToFloat(String str) {
		if (isBlank(str))
			return 0;
		try {
			String dbl = safeTrim(str);
			dbl = replace(dbl, ",", "");
			// return Double.parseDouble(dbl);
			NumberFormat format = NumberFormat.getInstance(Locale.US);
			Number number = 0;
			number = format.parse(str);
			return number.floatValue();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * convert base number
	 * 
	 * @param base
	 * @param toBase
	 * @return
	 * @throws Exception
	 */
	public static int getIntegerFromBase(String base, int toBase) {
		try {
			return Integer.valueOf(base, toBase);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * This method to cut left spaces of string
	 * 
	 * @param str
	 * @return string
	 */

	public static String leftTrim(String str) {
		Pattern p = Pattern.compile("[ \t]*(.*)");
		Matcher m = p.matcher(str);
		m.find();
		String result = m.group(1);
		return result;
	}

	/**
	 * This method to cut right spaces of string
	 * 
	 * @param str
	 * @return string
	 */

	public static String rightTrim(String str) {
		Pattern p = Pattern.compile("(.*[^ \t])[ \t]*");
		Matcher m = p.matcher(str);
		m.find();
		String result = m.group(1);
		return result;
	}

	/**
	 * This method is to check existed file
	 * 
	 * @author Trinh Nguyen
	 * @date 19-12-2008
	 * 
	 * @param file_name
	 * @return
	 */
	public static boolean checkExistFile(String file_name) {
		File file = new File(file_name);
		boolean exists = file.exists();
		if (!exists) {
			return false;
		}
		return true;
	}

	/**
	 * This method is to copy file
	 * 
	 * @author Trinh Nguyen
	 * @date 19-12-2008
	 * 
	 * @param srFile
	 * @param dtFile
	 */
	public static void copyFile(String srFile, String dtFile) {
		try {
			File f1 = new File(srFile);
			File f2 = new File(dtFile);
			InputStream in = new FileInputStream(f1);

			// For Append the file.
			// OutputStream out = new FileOutputStream(f2,true);

			// For Overwrite the file.
			OutputStream out = new FileOutputStream(f2);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (FileNotFoundException ex) {
			System.exit(0);
		} catch (IOException e) {
		}
	}

	/**
	 * get extention of file
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExtFile(String fileName) {
		if (isBlank(fileName))
			return "";
		String ext = "";
		try {
			ext = fileName.substring(fileName.lastIndexOf("."));
		} catch (Exception ex) {
			return "";
		}
		return ext;
	}

	/**
	 * Converts an array of bytes back to its constituent object. The input
	 * array is assumed to have been created from the original object. Uses the
	 * Logging utilities in j2sdk1.4 for reporting exceptions.
	 * 
	 * @param bytes
	 *            the byte array to convert.
	 * @return the associated object.
	 */
	public static Object toObject(byte[] bytes) {
		Object object = null;
		try {
			object = new java.io.ObjectInputStream(new java.io.ByteArrayInputStream(bytes)).readObject();
		} catch (java.io.IOException ioe) {
		} catch (java.lang.ClassNotFoundException cnfe) {
		}
		return object;
	}

	/**
	 * Tis method is to get cal's month by month
	 * 
	 * @author Trinh Nguyen
	 * @date 19-08-2009
	 * 
	 * @param month
	 * @return
	 */
	public static int getMonth(int month) {
		int cal_month = 0;
		if (month == 0) {
			cal_month = Calendar.JANUARY;
		} else if (month == 1) {
			cal_month = Calendar.FEBRUARY;
		} else if (month == 2) {
			cal_month = Calendar.MARCH;
		} else if (month == 3) {
			cal_month = Calendar.APRIL;
		} else if (month == 4) {
			cal_month = Calendar.MAY;
		} else if (month == 5) {
			cal_month = Calendar.JUNE;
		} else if (month == 6) {
			cal_month = Calendar.JULY;
		} else if (month == 7) {
			cal_month = Calendar.AUGUST;
		} else if (month == 8) {
			cal_month = Calendar.SEPTEMBER;
		} else if (month == 9) {
			cal_month = Calendar.OCTOBER;
		} else if (month == 10) {
			cal_month = Calendar.NOVEMBER;
		} else if (month == 11) {
			cal_month = Calendar.DECEMBER;
		}
		return cal_month;
	}

	/**
	 * This method is to get cal's day by day
	 * 
	 * @author Trinh Nguyen
	 * @date 19-08-2009
	 * 
	 * @param day
	 * @return
	 */
	public static String getDay(int day) {
		String cal_day = "";
		if (day == 1) {
			cal_day = "SUNDAY";
		} else if (day == 2) {
			cal_day = "MONDAY";
		} else if (day == 3) {
			cal_day = "TUESDAY";
		} else if (day == 4) {
			cal_day = "WEDNESDAY";
		} else if (day == 5) {
			cal_day = "THURSDAY";
		} else if (day == 6) {
			cal_day = "FRIDAY";
		} else if (day == 7) {
			cal_day = "SATURDAY";
		}
		return cal_day;
	}

	/**
	 * lay so gio gia 2 ngay
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static int getHourBetween2Date(Date fromDate, Date toDate) {
		try {
			return (int) Math.ceil(
					Math.abs((double) ((double) (fromDate.getTime() - toDate.getTime()) / (double) (1000 * 60 * 60))));
		} catch (Exception ex) {
			return 0;

		}
	}

	/**
	 * cach tinh tien benh vien, 1 ngay chia ra làm 6 block, 1 block 6h 1 ngày
	 * sẽ có 4 mức: 0.25 nếu số giờ nhỏ hơn hoặc bằng 6h 0.5 nếu số giờ >6h và
	 * <=12h 0.75 nếu số giờ >12h và <= 18h 1 nếu số giờ >18h và <=24h
	 * 
	 * @param hour
	 * @return
	 */
	public static double getDaysOfBedNoiTru(int hour) {
		double numRealDays = 0.0;
		int numDays = (int) Math.floor((double) hour / 24.0);// tổng số ngày
		// lấy giờ lẽ
		int digit = hour - numDays * 24;
		if (digit <= 6)
			numRealDays = numDays + 0.25;
		else if (digit > 6 && digit <= 12)
			numRealDays = numDays + 0.5;
		else if (digit > 12 && digit <= 18)
			numRealDays = numDays + 0.75;
		else if (digit > 18 && digit <= 24)
			numRealDays = numDays + 1.0;
		return numRealDays;
	}

	/**
	 * This method is to check drink_time < current_time
	 * 
	 * @author Trinh Nguyen
	 * @date 19-05-2009
	 * 
	 * @param drink_hour
	 * @param drink_time
	 * @return
	 */
	public static boolean checkBeforeCurrentTime(String drink_hour, String drink_time) {
		boolean flag;
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.set(2009, 5, 19, Lib.strToInteger(drink_hour), Lib.strToInteger(drink_time), 0);
		cal2.set(2009, 5, 19, cal2.get(Calendar.HOUR_OF_DAY), cal2.get(Calendar.MINUTE), 0);
		flag = cal1.getTime().before(cal2.getTime());
		return flag;
	}

	public static boolean checkBeforeCurrentTime1(Date drink_date) {
		Date now = new Date();
		return drink_date.before(now);
	}

	/**
	 * This method is to subtract time
	 * 
	 * @author Trinh Nguyen
	 * @date 04-08-2009
	 * 
	 * @param start_time
	 * @param end_time
	 * @return
	 */
	public static double getPeriod(int start_hour, int start_minute, int end_hour, int end_minute) {
		double result = 0;
		GregorianCalendar scal = new GregorianCalendar(2009, 8, 4, start_hour, start_minute);
		GregorianCalendar escal = new GregorianCalendar(2009, 8, 4, end_hour, end_minute);
		Date sdate = scal.getTime();
		Date edate = escal.getTime();
		result = (edate.getTime() - sdate.getTime()) / 60000;
		result /= 60;
		return result;
	}

	/* Tinh khoang cach ngay giua 2 ngay */
	public static int subtractDays(Date date1, Date date2) {
		Calendar gc1 = Calendar.getInstance();
		gc1.setTime(date1);
		Calendar gc2 = Calendar.getInstance();
		gc2.setTime(date2);

		int days1 = 0;
		int days2 = 0;
		int maxYear = Math.max(gc1.get(Calendar.YEAR), gc2.get(Calendar.YEAR));

		Calendar gctmp = (Calendar) gc1.clone();
		for (int f = gctmp.get(Calendar.YEAR); f < maxYear; f++) {
			days1 += gctmp.getActualMaximum(Calendar.DAY_OF_YEAR);
			gctmp.add(Calendar.YEAR, 1);
		}

		gctmp = (Calendar) gc2.clone();
		for (int f = gctmp.get(Calendar.YEAR); f < maxYear; f++) {
			days2 += gctmp.getActualMaximum(Calendar.DAY_OF_YEAR);
			gctmp.add(Calendar.YEAR, 1);
		}

		days1 += gc1.get(Calendar.DAY_OF_YEAR) - 1;
		days2 += gc2.get(Calendar.DAY_OF_YEAR) - 1;

		return Math.abs(days1 - days2);
	}

	/**
	 * This method is to generate resident prescription id by drink_date
	 * 
	 * @author Trinh Nguyen
	 * @date 09-09-2009
	 * 
	 * @param drink_date
	 * @return
	 */
	/*
	 * public static String getResPresIdByDrinkDate(String drink_date) { String
	 * prescription_id = ""; String[] dateArr = drink_date.split("/"); String
	 * day = dateArr[0]; String month = dateArr[1]; String year = dateArr[2];
	 * String yearS = year.substring(2, 4); String drink_day =
	 * Lib.padLeft(String.valueOf(day), 2) + Lib.padLeft(String.valueOf(month),
	 * 2) + yearS; ResidentPrescriptionService service = new
	 * ResidentPrescriptionService(); int index = service.getMaxIndexOfResPres()
	 * + 1; String prefix = Setting.getValue("prescription_id"); prescription_id
	 * = prefix + drink_day + Lib.padLeft(String.valueOf(index), 5); return
	 * prescription_id; }
	 */

	/**
	 * This method is to get field_value by field_key
	 * 
	 * @author Trinh Nguyen
	 * @date 29-09-2009
	 * 
	 * @param field_key
	 * @return
	 */
	public static String getFieldValue(String field_key) {
		return !Lib.isBlank(field_key) ? field_key : "";
	}

	/**
	 * get hour and minute string
	 * 
	 * @return hh:mm
	 */
	public static String getHourMinute() {
		return padLeft(getCurrentHour() + "", 2) + ":" + padLeft(getCurrentMinute() + "", 2);
	}

	/**
	 * get last day of month
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getLastDayOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		int date = 1;
		calendar.set(year, month - 1, date);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return maxDay;
	}

	/**
	 * get date after added number day base on current day lay ngay sau khi da
	 * cong them bao nhieu ngay vd: hom nay: 09/04/2010 goi ham
	 * getDateAddedDays(10) se tra ve ngay 19/04/2010
	 * 
	 * @param days
	 * @return
	 */
	public static Date getDateAddedDays(int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());// current date
		c.add(Calendar.DATE, days); // number of days to add
		return c.getTime();
	}

	/**
	 * Ham kiem tra 2 ngay truyen vao co bang nhau khong(chi kiem tra phan ngay
	 * khong kiem tra phan gio)
	 * 
	 * @author MinhPham date 25-07-2011
	 * @return
	 */
	public static boolean compareDate(Date date1, Date date2) {
		String str_date1 = dateToString(date1, "yyyyMMdd");
		String str_date2 = dateToString(date2, "yyyyMMdd");
		if (str_date1.equals(str_date2))
			return true;
		return false;
	}

	/**
	 * So sanh 2 ngay lon hoac nho hon
	 * 
	 * @param d1
	 * @param d2
	 * @return lon hon:1, bang nhau:0, nho hon:-1,
	 * @throws Exception
	 * 
	 */
	public static int dateCompare(Date d1, Date d2) throws Exception {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			String str1 = format.format(d1);
			// System.out.println("str1: " + str1);
			String str2 = format.format(d2);
			// System.out.println("str2: " + str2);
			int result = str1.compareTo(str2);
			if (result > 0) {
				return 1;
			} else if (result == 0) {
				return 0;
			} else {
				return -1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ParseException(e.toString(), -1);
		}
	}

	/**
	 * @deprecated replaced timeDiff Lay gia tri chenh lech cua 2 date1 va date2
	 * @param date1
	 * @param date2
	 * @return seconds
	 * @author jincheng
	 */
	public static long timeDiffSecond(Date date1, Date date2) throws Exception {
		long duration = date1.getTime() - date2.getTime();

		long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
		return diffInSeconds;

	}

	/**
	 * Lay gia tri chenh lech cua 2 date1 va date2, tra ve theo dinh dang
	 * TimeUnit
	 * 
	 * @param date1
	 * @param date2
	 * @return seconds
	 * @author jincheng
	 */
	public static long timeDiff(Date date1, Date date2, TimeUnit unit) throws Exception {
		long duration = Math.abs(date1.getTime() - date2.getTime());
		long difftime = duration;
		if (unit == TimeUnit.MINUTES) {
			difftime = TimeUnit.MILLISECONDS.toMinutes(duration);
		} else if (unit == TimeUnit.HOURS) {
			difftime = TimeUnit.MILLISECONDS.toHours(duration);
		} else if (unit == TimeUnit.DAYS) {
			difftime = TimeUnit.MILLISECONDS.toDays(duration);
		} else if (unit == TimeUnit.SECONDS) {
			difftime = TimeUnit.MILLISECONDS.toSeconds(duration);
		}
		return difftime;
	}

	public static String getCurdateWorld(String patern) {
		try {
			SimpleDateFormat gmtDateFormat = new SimpleDateFormat(patern);
			gmtDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
			return gmtDateFormat.format(new Date());
		} catch (Exception ex) {
			return "";
		}

	}

	/**
	 * so sanh giua 2 ngay truyen vao
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return ceil(days)
	 */
	public static int daysBetween2Dates(Date fromDate, Date toDate) {
		try {
			long t1 = toDate.getTime();
			long t2 = fromDate.getTime();
			int days = (int) Math.ceil(Math.abs((double) ((double) (t1 - t2) / (double) (24 * 1000 * 60 * 60))));
			return days;
		} catch (Exception ex) {
			return 0;

		}
	}

	/**
	 * So sanh voi thoi gian hien tai
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static long daysBetween2Dates(int year, int month, int day) {
		long days = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance(TimeZone.getDefault());
		c1.set(year, month, day);
		days = ((c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000));
		return days;
	}

	/**
	 * This method is to get re_examine_date after 7 days
	 * 
	 * @author Trinh Nguyen
	 * @date 17-02-2009
	 * 
	 * @return
	 */
	public static String getReExamineDate(int date_number) {
		String re_examine_date = "";
		Calendar date = new GregorianCalendar();
		Calendar cal = new GregorianCalendar(date.get(Calendar.YEAR), date.get(Calendar.MONTH),
				date.get(Calendar.DATE) + date_number, date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE));
		re_examine_date = Lib.padLeft(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)), 2) + "/"
				+ Lib.padLeft(String.valueOf((cal.get(Calendar.MONTH) + 1)), 2) + "/"
				+ Lib.padLeft(String.valueOf(cal.get(Calendar.YEAR)), 4) + " "
				+ Lib.padLeft(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), 2) + ":"
				+ Lib.padLeft(String.valueOf(cal.get(Calendar.MINUTE)), 2);
		return re_examine_date;
	}

	public static String memmoryInfo() {

		List<MemoryPoolMXBean> beans = ManagementFactory.getMemoryPoolMXBeans();
		MemoryPoolMXBean permgenBean = null;
		for (MemoryPoolMXBean bean : beans) {
			if (bean.getName().toLowerCase().indexOf("perm gen") >= 0) {
				permgenBean = bean;
				break;
			}
		}
		MemoryUsage currentUsage = permgenBean.getUsage();
		int percentageUsed = (int) ((currentUsage.getUsed() * 100) / currentUsage.getMax());
		String memInfo = getDateNow() + ": Permgen " + currentUsage.getUsed() + " of " + currentUsage.getMax() + " ("
				+ percentageUsed + "%)";
		return memInfo;

	}

	public static String stringToHex(String base) {
		StringBuffer buffer = new StringBuffer();
		int intValue;
		for (int x = 0; x < base.length(); x++) {
			int cursor = 0;
			intValue = base.charAt(x);
			String binaryChar = new String(Integer.toBinaryString(base.charAt(x)));
			for (int i = 0; i < binaryChar.length(); i++) {
				if (binaryChar.charAt(i) == '1') {
					cursor += 1;
				}
			}
			if ((cursor % 2) > 0) {
				intValue += 128;
			}
			buffer.append(Integer.toHexString(intValue) + " ");
		}
		return buffer.toString();
	}

	/**
	 * convert byte array to hex string
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);

		Formatter formatter = new Formatter(sb);
		for (byte b : bytes) {
			formatter.format("%02x", b);
		}

		return sb.toString();
	}

	/**
	 * convert byte to string
	 * 
	 * @param bytes
	 * @param charset
	 * @return
	 */
	public static String bytesToString(byte[] bytes, String charset) {
		try {
			Charset cs = Charset.forName(charset);
			String strData = new String(bytes, cs);
			return strData;
		} catch (Exception e) {
			try {
				return new String(bytes, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				return "";
			}
		}
	}

	/**
	 * convert hex string ve bytes
	 * 
	 * @param s
	 * @return
	 */
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public static String hexBytes2String(byte[] receivedBytes) {
		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		String hexString = Lib.bytesToHexString(receivedBytes);
		for (int i = 0; i < hexString.length() - 1; i += 2) {

			// grab the hex in pairs
			String output = hexString.substring(i, (i + 2));
			// convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			// convert the decimal to character
			sb.append((char) decimal);

			temp.append(decimal);
		}
		// System.out.println("Decimal : " + temp.toString());

		return sb.toString();
	}

	public static String str2CapitalCase(String strObj) {
		StringBuffer res = new StringBuffer();

		String[] strArr = strObj.split(" ");
		for (String str : strArr) {
			str = str.toLowerCase();
			if (isBlank(str))
				continue;
			char[] stringArray = str.trim().toCharArray();

			stringArray[0] = Character.toUpperCase(stringArray[0]);
			stringArray[0] = Character.toUpperCase(stringArray[0]);
			str = new String(stringArray);

			res.append(str).append(" ");
		}
		return res.toString();
	}

	public static String toUrlFriendly(String s) {
		int maxLength = Math.min(s.length(), 236);
		char[] buffer = new char[maxLength];
		int n = 0;
		for (int i = 0; i < maxLength; i++) {
			char ch = s.charAt(i);
			buffer[n] = removeAccent(ch);
			// skip not printable characters
			if (buffer[n] > 31) {
				n++;
			}
		}
		// skip trailing slashes
		while (n > 0 && buffer[n - 1] == '/') {
			n--;
		}
		return String.valueOf(buffer, 0, n);
	}

	public static char removeAccent(char ch) {
		int index = Arrays.binarySearch(SPECIAL_CHARACTERS, ch);
		if (index >= 0) {
			ch = REPLACEMENTS[index];
		}
		return ch;
	}

	public static String removeAccent(String s) {
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < sb.length(); i++) {
			sb.setCharAt(i, removeAccent(sb.charAt(i)));
		}
		return sb.toString();
	}

	/**
	 * convert object to json string
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> String convertObj2JsonString(T obj) {
		try {
			Gson gson=  new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss a").create();
			String jsonStr = gson.toJson(obj);
			return jsonStr;
		} catch (Exception e) {
			_libLog.info(e);
			return "";
		}
	}

	/**
	 * convert string json to object
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static <T> T convertJson2Object(String jsonStr, Class<T> clazz) {
		try {
			Gson gson=  new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss a").create();
			return gson.fromJson(jsonStr, clazz);
		} catch (Exception e) {
			_libLog.error(e);
			_libLog.error(e.getMessage());
			return null;
		}
	}

	public static JSONObject str2Json(String json) {
		try {
			return new JSONObject(json);
		} catch (JSONException e) {
			return null;
		}

	}

	/**
	 * Get date to json
	 * 
	 * @param dateJson
	 * @return
	 */
	public static Date getDateJson(String dateJson) {
		long strToInteger = 0;
		dateJson = dateJson.substring(6);
		dateJson = replace(dateJson, ")/", "");
		strToInteger = strToLong(dateJson);
		Date date = new Date(strToInteger);
		return date;
	}

	/**
	 * get current location
	 * 
	 * @return
	 */
	public static String getCurrentLocationPath() {
		try {
			String path = new File(".").getCanonicalPath();
			return path;
		} catch (IOException e) {
			return "";
		}

	}

	/**
	 * lay path tuyet doi
	 * 
	 * @param clas
	 * @return
	 */
	public static String getAbsolutePath(Class clas) {
		try {
			String absolutePath = clas.getProtectionDomain().getCodeSource().getLocation().getPath();
			String path = new File(absolutePath).getParentFile().getAbsolutePath();
			return path;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * neu truong co dinh app dir thi lay path dir
	 * System.setProperty(appDirKey,path);
	 * 
	 * @param appDirKey
	 * @return
	 */
	public static String getCurrentLocationPath(String appDirKey) {
		try {
			String path = "";
			if (isBlank(appDirKey)) {
				path = new File(".").getCanonicalPath();
			} else {
				path = System.getProperty(appDirKey);
			}
			if (isBlank(path)) {
				path = new File(".").getCanonicalPath();
			}
			return path;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * combine paths
	 * 
	 * @param paths
	 * @return
	 */
	public static String combine(String... paths) {
		File file = new File(paths[0]);

		for (int i = 1; i < paths.length; i++) {
			file = new File(file, paths[i]);
		}

		return file.getPath();
	}

	/**
	 * gop cac mang byte thanh 1 mang byte
	 * 
	 * @param byteArrs
	 * @return
	 */
	public static byte[] byteArrayConcat(byte[]... byteArrs) {
		int length = 0;
		for (int i = 0; i < byteArrs.length; i++) {
			if (null != byteArrs[i]) {
				length += byteArrs[i].length;
			}
		}
		if (length <= 0)
			return null;

		byte[] byteArray = new byte[length];
		int pos = 0;
		for (int i = 0; i < byteArrs.length; i++) {
			if (null != byteArrs[i]) {
				System.arraycopy(byteArrs[i], 0, byteArray, pos, byteArrs[i].length);
				pos += byteArrs[i].length;
			}
		}
		return byteArray;
	}

	// / <summary>
	// / get index of byte array
	// / </summary>
	// / <param name="array"></param>
	// / <param name="pattern"></param>
	// / <param name="offset"></param>
	// / <returns></returns>
	public static int byteIndexOf(byte[] array, byte[] pattern, int offset) {
		int success = 0;
		for (int i = offset; i < array.length; i++) {
			if (array[i] == pattern[success]) {
				success++;
			} else {
				success = 0;
			}

			if (pattern.length == success) {
				return i - pattern.length + 1;
			}
		}
		return -1;
	}

	/**
	 * get lastindexof byte array in array
	 * 
	 * @param array
	 * @param pattern
	 * @return
	 */
	public static int byteLastIndexOf(byte[] array, byte[] pattern) {
		int success = 0;
		for (int i = array.length - 1; i >= 0; i--) {
			if (array[i] == pattern[pattern.length - success - 1]) {
				success++;
			} else {
				success = 0;
			}

			if (pattern.length == success) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * get byte array from index to index
	 * 
	 * @param source
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public static byte[] subByteArray(byte[] source, int startIndex, int endIndex) {
		try {
			if (endIndex - startIndex <= 0)
				return null;
			if (endIndex > source.length)
				endIndex = source.length;
			byte[] destArray = new byte[endIndex - startIndex];
			System.arraycopy(source, startIndex, destArray, 0, endIndex - startIndex);
			return destArray;
		} catch (Exception ex) {
			return null;
		}
	}

	public static void addToByteList(byte[] bytes, List<Byte> bytesList) {

		for (int i = 0; i < bytes.length; i++) {
			bytesList.add(bytes[i]);
		}
	}

	public static byte[] getArrayFromList(List<Byte> bytesList) {
		byte[] bytes = new byte[bytesList.size()];
		for (int i = 0; i < bytesList.size(); i++) {
			bytes[i] = bytesList.get(i);
		}

		return bytes;
	}

	public static int hexStr2int(String hex) {
		try {
			return Integer.parseInt(hex, 16);
		} catch (Exception e) {
			return 0;
		}
	}

	public static BigDecimal str2BigDecimal(String str) {
		try {
			return new BigDecimal(str, MathContext.UNLIMITED);
		} catch (Exception e) {
			return new BigDecimal("0", MathContext.UNLIMITED);
		}
	}

	/**
	 * Kiem tra input on/off 111111111 : nByte _________ 000000100 : bitIndex:2
	 * =>pow(2,2){base 10}= 000000100{base 2} =========== 000000100=>input 3 la
	 * on.
	 * 
	 * @param nByte
	 * @param bitIndex
	 * @return
	 */
	public static boolean checkInputSignalOnOff(int nByte, int bitIndex) {
		int result = nByte & Lib.strToInteger(Math.pow(2, bitIndex) + "");
		return result != 0 ? true : false;
	}

	/**
	 * tinh van toc tu khoang cach va thoi gian
	 * 
	 * @param nDistance
	 * @param duration
	 * @return
	 */
	public static double CalcSpeed(double nDistance, int duration) {
		double CalcSpeed = 0;
		try {
			double nTime;
			// TimeSpan difTime = Time2.Subtract(Time1);
			nTime = Math.abs((double) duration / 60); // Math.Abs(difTime.TotalMinutes);
			if (nTime > 0) {
				// CalcSpeed = Round((nDistance*60) / (nTime), 0, 1);//(km/h)
				// nTime/3600: doi ra gio
				CalcSpeed = round((nDistance * 60) / (nTime), 0, 0);// (km/h)
																	// nTime/3600:
																	// doi ra
																	// gio
			}
			if (CalcSpeed < 0)
				CalcSpeed = 0;
		} catch (Exception ex) {
			// LogFile.WriteAppendLogError( "Error CalcSpeed:" + ex.Message,
			// Global.PathError);
			CalcSpeed = 0;
		}
		return CalcSpeed;
	}

	public static boolean isArray(final Object obj) {
		return obj instanceof Object[] || obj instanceof boolean[] || obj instanceof byte[] || obj instanceof short[]
				|| obj instanceof char[] || obj instanceof int[] || obj instanceof long[] || obj instanceof float[]
				|| obj instanceof double[];
	}

	/**
	 * ho tro array list<?>, object[], string[] v.v...
	 * 
	 * @param arg
	 * @return
	 */
	public static String joinString(Object arg) {
		try {
			// if(args.length <1) return "";//throw new
			// IllegalArgumentException();
			String joined = "";
			Object[] objArr = null;
			if (arg instanceof List<?>) {
				objArr = ((List) arg).toArray();
			}
			if (!isArray(objArr)) {
				return arg.toString();
				// args = Arrays.toString((String[])arg);
				// return Arrays.toString((T[]) arg);
			}
			joined = Arrays.toString(objArr);
			String result = joined.substring(1, joined.length() - 1);
			return result;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * double array 2 string
	 * 
	 * @param degitals
	 * @return
	 */
	public static String doubleArray2JoinString(double... degitals) {
		try {
			String str_result = "";
			if (null == degitals || degitals.length <= 0) {
				return "";
			}
			for (int i = 0; i < degitals.length; i++) {
				if (!Lib.isBlank(str_result)) {
					str_result += "-";
				}
				str_result = str_result + degitals[i];
			}
			return str_result;
		} catch (Exception e) {
			return "";
		}
	}

	public static void systemOut(Boolean isDebug, String msg) {
		if (isDebug)
			System.out.println(msg);
	}

	/**
	 * build noi dung loi
	 * 
	 * @param error_id
	 * @param hex
	 * @param messErr
	 * @author tichnguyen
	 * @return
	 */
	public static String buildLogFileERRContent(String error_id, String hex, String messErr) {
		return error_id + "," + hex + "," + messErr;
	}

	/**
	 * xac dinh diem nam trong hinh tron
	 * 
	 * @param centerLat
	 * @param centerLon
	 * @param pointLat
	 * @param pointLon
	 * @param radius
	 *            dv:km
	 * @return
	 */
	public static boolean ssInsideCircle(double centerLat, double centerLon, double pointLat, double pointLon,
			double radius) {
		// tinh khoang cach tam va diem
		// double distance = findDistanceInKm(centerLat, centerLon, pointLat,
		// pointLon);
		double distance = calDistance(centerLat, centerLon, pointLat, pointLon);
		if (distance <= radius) {
			return true;
		}
		return false;

	}

	/**
	 * tinh khoang cach giua 2 diem xy
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double calDistance(double x1, double y1, double x2, double y2) {
		try {
			double distance = Double.MIN_VALUE;
			double lat1InRad = x1 * (Math.PI / 180.0);
			double long1InRad = y1 * (Math.PI / 180.0);
			double lat2InRad = x2 * (Math.PI / 180.0);
			double long2InRad = y2 * (Math.PI / 180.0);
			double latitude = lat2InRad - lat1InRad;
			double longtitud = long2InRad - long1InRad;
			double a = Math.pow(Math.sin(latitude / 2.0), 2.0)
					+ Math.cos(lat2InRad) * Math.cos(lat2InRad) * Math.pow(Math.sin(longtitud / 2.0), 2.0);
			double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));
			// double kEarthRadiusKms = 6376.5;
			double kEarthRadiusKms = 6378.137;
			distance = kEarthRadiusKms * c;
			return Math.abs(distance);
		} catch (Exception ex) {
			return 0;
		}
	}

	// convert degrees to radians
	public static double deg2Rad(double deg) {
		double rad = deg * Math.PI / 180; // radians = degrees pi/180
		return rad;
	}

	/**
	 * thuat toan hinh chu nhat
	 * 
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @param lat
	 * @param lon
	 * @return
	 */
	public static boolean checkInRec(double lat1, double lon1, double lat2, double lon2, double lat, double lon) {
		boolean result = false;
		if (lat1 < lat2) {
			if (lon1 < lon2) {
				if (lat >= lat1 && lat <= lat2 && lon >= lon1 && lon <= lon2)
					result = true;
			} else {
				if (lat >= lat1 && lat <= lat2 && lon >= lon2 && lon <= lon1)
					result = true;
			}
		} else {
			if (lon1 < lon2) {
				if (lat >= lat2 && lat <= lat1 && lon >= lon1 && lon <= lon2)
					result = true;
			} else {
				if (lat >= lat2 && lat <= lat1 && lon >= lon2 && lon <= lon1)
					result = true;
			}
		}
		return result;
	}

	/**
	 * check the point is inside polygon or not
	 * 
	 * @param poly
	 * @param pnt
	 * @return
	 */
	public static boolean isInsidePolygon(Point[] poly, Point pt) {
		Builder builder = Polygon.Builder();
		for (int i = 0; i < poly.length; i++) {
			builder.addVertex(poly[i]);
		}
		Polygon newPoly = builder.build();
		// Point is inside
		return newPoly.contains(pt);
	}

	/**
	 * Thêm một giải thuật xét điểm qua vùng.
	 * 
	 * @param test:
	 *            Điểm cần xét
	 * @return: true nếu điểm trong vung, false nếu điểm ngoài vùng
	 */
	public static boolean contains(Point[] points, Point test) {
		int i;
		int j;
		boolean result = false;
		for (i = 0, j = points.length - 1; i < points.length; j = i++) {
			if ((points[i].y > test.y) != (points[j].y > test.y)
					&& (test.x < (points[j].x - points[i].x) * (test.y - points[i].y) / (points[j].y - points[i].y)
							+ points[i].x)) {
				result = !result;
			}
		}
		return result;
	}

	/***
	 * Tìm điểm giao giữa hai đoạn thẳng thompt add ngày 04/12/2017
	 * 
	 * @param line1
	 * @param line2
	 * @return Điểm giao
	 */
	public static boolean getIntersection(Line line1, Line line2) {

		double x1, y1, x2, y2, x3, y3, x4, y4;
		x1 = line1.getStart().x;
		y1 = line1.getStart().y;
		x2 = line1.getEnd().x;
		y2 = line1.getEnd().y;
		x3 = line2.getStart().x;
		y3 = line1.getStart().y;
		x4 = line2.getEnd().x;
		y4 = line1.getEnd().y;
		double x = ((x2 - x1) * (x3 * y4 - x4 * y3) - (x4 - x3) * (x1 * y2 - x2 * y1))
				/ ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
		double y = ((y3 - y4) * (x1 * y2 - x2 * y1) - (y1 - y2) * (x3 * y4 - x4 * y3))
				/ ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));

		if (x != Double.NaN && y != Double.NaN) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Get angle in degree by 3 points in 2D vector
	 * 
	 * @param lat1
	 * @param lng1
	 * @param latCenter
	 * @param lngCenter
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static int getAngle(double lat1, double lng1, double latCenter, double lngCenter, double lat2, double lng2) {
		// giữa Center là góc O, 1 ( vecto x), 2 (vecto y)
		// Góc giữ Ox và Oy = (vOX.vOY)/(|vOX|.|vOY|)
		int result = 0;
		double vecto1X, vecto1Y, vecto2X, vecto2Y;
		vecto1X = lat1 - latCenter;
		vecto1Y = lng1 - lngCenter;
		vecto2X = lat2 - latCenter;
		vecto2Y = lng2 - lngCenter;
		double muti_non_direct = vecto1X * vecto2X + vecto1Y * vecto2Y;
		double muti_length = Math.sqrt(vecto1X * vecto1X + vecto1Y * vecto1Y)
				* Math.sqrt(vecto2X * vecto2X + vecto2Y * vecto2Y);
		if (muti_length != 0) {
			result = (int) (Math.acos(muti_non_direct / muti_length) * 180 / Math.PI);
			// Lấy góc nhỏ nhất giữa 2 vector
			// if (result > 180)
			// result = 360 - result;
		}
		return result;
	}

	public static double getAverage(Double[] data) throws Exception {
		double sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum = sum + data[i];
		}
		double average = sum / data.length;
		;
		return average;
		// System.out.println("Average value of array element is " " + average);
	}

	public static Double[] toDoubleArray(List<Double> list) {
		Double[] ret = new Double[list.size()];
		for (int i = 0; i < ret.length; i++)
			ret[i] = list.get(i);
		return ret;
	}

	/**
	 * 计算方位角pab
	 * 
	 * @param lat_a
	 * @param lng_a
	 * @param lat_b
	 * @param lng_b
	 * @return
	 */
	public static double gps2d(double lat_a, double lng_a, double lat_b, double lng_b) {
		double d = 0;
		lat_a = lat_a * Math.PI / 180;
		lng_a = lng_a * Math.PI / 180;
		lat_b = lat_b * Math.PI / 180;
		lng_b = lng_b * Math.PI / 180;

		d = Math.sin(lat_a) * Math.sin(lat_b) + Math.cos(lat_a) * Math.cos(lat_b) * Math.cos(lng_b - lng_a);
		d = Math.sqrt(1 - d * d);
		d = Math.cos(lat_b) * Math.sin(lng_b - lng_a) / d;
		d = Math.asin(d) * 180 / Math.PI;
		// d = Math.round(d*10000);
		return d;
	}

	/**
	 * lay gia tri calib
	 * 
	 * @param val:
	 *            gia tri truyen vo de tham chieu
	 * @param calib:
	 *            chuoi calib
	 * @return
	 * @throws Exception
	 *             neu chuoi calib khong hop le
	 */
	public static double getValFromCalib(double val, String calib) throws Exception {
		String reg1 = ";";
		String reg2 = ",";
		List<Double> keyArr = new ArrayList<Double>();
		List<Double> valArr = new ArrayList<Double>();
		if (isBlank(calib)) {
			return 0;
		}
		String[] strAr = calib.split(reg1);
		if (strAr.length < 2) {
			return 0;
		}
		boolean isReverseOrder = false;
		double firstKey = 0;
		boolean isFirst = true;
		for (int i = 0; i <= strAr.length - 1; i++) {
			try {
				String subStr = strAr[i];
				if (isBlank(subStr)) {
					continue;
				}
				String[] subAr = subStr.split(reg2);
				if (subAr.length < 2) {
					continue;
				}
				String strKey = subAr[0];
				String strval = subAr[1];
				double idxKey = strToDouble(strKey);
				double idxVal = strToDouble(strval);
				keyArr.add(idxKey);
				valArr.add(idxVal);
				if (isFirst) {
					firstKey = idxKey;
					isFirst = false;
				}
				if (i == strAr.length - 1) {
					if (firstKey > idxKey) {
						keyArr.clear();
						valArr.clear();
						isReverseOrder = true;
						break;
					}
				}
			} catch (Exception e) {
				continue;
			}
		}
		if (isReverseOrder) {
			for (int i = strAr.length - 1; i >= 0; i--) {
				try {
					String subStr = strAr[i];
					if (isBlank(subStr)) {
						continue;
					}
					String[] subAr = subStr.split(reg2);
					if (subAr.length < 2) {
						continue;
					}
					String strKey = subAr[0];
					String strval = subAr[1];
					double idxKey = strToDouble(strKey);
					double idxVal = strToDouble(strval);
					keyArr.add(idxKey);
					valArr.add(idxVal);

				} catch (Exception e) {
					continue;
				}
			}
		}
		if (keyArr.size() <= 1) {
			return 0;
		}
		if (keyArr.indexOf(val) >= 0) {
			return valArr.get(keyArr.indexOf(val));
		}
		int maxIndex = keyArr.size() - 1;
		if (keyArr.get(0) > keyArr.get(maxIndex)) {
			Collections.sort(keyArr);
			Collections.sort(valArr, Collections.reverseOrder());
		}
		double result = 0;
		double a = 0;
		double b = 0;
		double c = 0;
		double d = 0;
		if (val < keyArr.get(0)) {
			// truong hop < min
			a = keyArr.get(1) - keyArr.get(0);
			b = valArr.get(1) - valArr.get(0);
			c = val - keyArr.get(0);
			d = c * b / a;
			result = d + valArr.get(0);
		} else if (val > keyArr.get(maxIndex)) {
			// truong hop > max
			a = keyArr.get(maxIndex) - keyArr.get(maxIndex - 1);
			b = valArr.get(maxIndex) - valArr.get(maxIndex - 1);
			c = val - keyArr.get(maxIndex - 1);
			d = c * b / a;
			result = d + valArr.get(maxIndex - 1);
		} else {
			// truong hop khoan giua
			for (int i = 0; i < keyArr.size(); i++) {
				double key = keyArr.get(i);
				if (key > val) {
					a = keyArr.get(i) - keyArr.get(i - 1);
					b = valArr.get(i) - valArr.get(i - 1);
					c = val - keyArr.get(i - 1);
					d = c * b / a;
					result = d + valArr.get(i - 1);
					break;
				}
			}
		}
		return result;
	}

	public static BitSet convertBitSet(long value) {
		BitSet bits = new BitSet();
		int index = 0;
		while (value != 0L) {
			if (value % 2L != 0) {
				bits.set(index);
			}
			++index;
			value = value >>> 1;
		}
		return bits;
	}

	public static double convertKnots2Km(double knots) {
		double km = 0;
		try {
			double factor = 1.852;
			km = knots * factor;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return km;
	}

	/**
	 * lay huong tu 2 poits
	 * 
	 * @param lat2
	 * @param lat1
	 * @param long2
	 * @param long1
	 * @return
	 */
	public static double getHeadingBy2Poits(double lat2, double lat1, double long2, double long1) {
		if (lat2 == lat1 && long2 == long1) {
			return 0;
		}
		double phi1 = lat1 * Math.PI / 180.0;
		double phi2 = lat2 * Math.PI / 180.0;
		double lam1 = long1 * Math.PI / 180.0;
		double lam2 = long2 * Math.PI / 180.0;
		double direction = (Math.atan2(Math.sin(lam2 - lam1) * Math.cos(phi2),
				Math.cos(phi1) * Math.sin(phi2) - Math.sin(phi1) * Math.cos(phi2) * Math.cos(lam2 - lam1)) * 180
				/ Math.PI);
		// if (direction < 0) {
		// direction += 360;
		// }
		if (phi1 > phi2) { // 1为参考点坐标
			if (lam1 > lam2)
				direction += 180;
			else
				direction = 180 - direction;
		} else if (lam1 > lam2)
			direction = 360 - direction;
		return direction;
	}

	public static double getAngle(double lat1, double lng1, double lat2, double lng2) {
		double x1 = lng1;
		double y1 = lat1;
		double x2 = lng2;
		double y2 = lat2;
		double pi = Math.PI;
		double w1 = y1 / 180 * pi;
		double j1 = x1 / 180 * pi;
		double w2 = y2 / 180 * pi;
		double j2 = x2 / 180 * pi;
		double ret;
		if (j1 == j2) {
			if (w1 > w2)
				return 270; // 北半球的情况，南半球忽略
			else if (w1 < w2)
				return 90;
			else
				return -1;// 位置完全相同
		}
		ret = 4 * Math.pow(Math.sin((w1 - w2) / 2), 2)
				- Math.pow(Math.sin((j1 - j2) / 2) * (Math.cos(w1) - Math.cos(w2)), 2);
		ret = Math.sqrt(ret);
		double temp = (Math.sin(Math.abs(j1 - j2) / 2) * (Math.cos(w1) + Math.cos(w2)));
		ret = ret / temp;
		ret = Math.atan(ret) / pi * 180;
		if (j1 > j2) { // 1为参考点坐标
			if (w1 > w2)
				ret += 180;
			else
				ret = 180 - ret;
		} else if (w1 > w2)
			ret = 360 - ret;
		return ret;
	}

	/**
	 * @param lat1
	 *            纬度1
	 * @param lng1
	 *            经度1
	 * @param lat2
	 *            纬度2
	 * @param lng2
	 *            经度2
	 * @return 方向
	 */
	public static String getDirection(double lat1, double lng1, double lat2, double lng2) {
		double jiaodu = getAngle(lat1, lng1, lat2, lng2);
		if ((jiaodu <= 10) || (jiaodu > 350)) {
			return "东";
		}
		if ((jiaodu > 10) && (jiaodu <= 80)) {
			return "东北";
		}
		if ((jiaodu > 80) && (jiaodu <= 100)) {
			return "北";
		}
		if ((jiaodu > 100) && (jiaodu <= 170)) {
			return "西北";
		}
		if ((jiaodu > 170) && (jiaodu <= 190)) {
			return "西";
		}
		if ((jiaodu > 190) && (jiaodu <= 260)) {
			return "西南";
		}
		if ((jiaodu > 260) && (jiaodu <= 280)) {
			return "南";
		}
		if ((jiaodu > 280) && (jiaodu <= 350)) {
			return "东南";
		}
		return "";
	}

	/**
	 * Hàm chuyển tiếng việt có dấu thành tiếng việt không dấu
	 * 
	 * @param str
	 *            : Chuỗi tiếng việt có dấu
	 * @return str : Chuỗi tiếng việt không dấu
	 */
	public static String covertStringToURL(String str) {
		try {
			String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
			return pattern.matcher(temp).replaceAll("").replaceAll("đ", "d");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	/**
	 * Hàm phân tách chuỗi ban đầu thành các chuỗi có độ dài quy định là length
	 * bytes
	 * 
	 * @param original
	 *            : chuỗi ban đầu
	 * @param length
	 *            : Độ dài bytes cần phân tách
	 * @param separator
	 * @return
	 * @throws IOException
	 *             author: thompt
	 */
	public static String chunk_split(String original, int length, String separator) throws IOException {

		int len = original.length();
		StringBuilder str = new StringBuilder(original);
		if (len % length > 0) {
			int addlen = len % length;
			for (int i = 0; i < addlen; i++) {
				str.insert(len, "0");
			}
			// System.out.println("new original: " + str);
		}
		original = str.toString();
		ByteArrayInputStream bis = new ByteArrayInputStream(original.getBytes());
		int n = 0;
		byte[] buffer = new byte[length];
		String result = "";
		while ((n = bis.read(buffer)) > 0) {
			for (byte b : buffer) {
				result += (char) b;
			}
			Arrays.fill(buffer, (byte) 0);
			result += separator;
		}
		return result;
	}

	/**
	 * Hàm chuyển giá chuỗi nhị phân thành giá trị thập phân
	 * 
	 * @param binary
	 * @return int number author: thompt
	 */
	public static int binaryToDecimal(String binary) {
		int decimalValue = 0;
		int length = binary.length() - 1;
		for (int i = 0; i < binary.length(); i++) {
			decimalValue += Integer.parseInt(binary.charAt(i) + "") * Math.pow(2, length);
			length--;
		}
		return decimalValue;
	}

	/**
	 * Hàm chuyển một số về chuỗi nhị phân n bít
	 * 
	 * @param num
	 * @param nbyte
	 * @return string binary author: thompt
	 */
	public static String numberToBinary(int num, int nbyte) {
		StringBuilder buf1 = new StringBuilder();
		StringBuilder buf2 = new StringBuilder();
		while (num != 0) {
			int digit = num % 2;
			buf1.append(digit); // apend 0101 order
			num = num / 2;
		}
		String binary = buf1.reverse().toString();// reverse to get binary 1010
		int length = binary.length();
		if (length < nbyte) {
			while (nbyte - length > 0) {
				buf2.append("0");// add zero until length =8
				length++;
			}
		}
		String bin = buf2.toString() + binary;// binary string with leading 0's

		return bin;
	}

	/**
	 * Hàm chuyển đổi chuỗi hex sang một số thập phân
	 * 
	 * @param hex
	 * @return
	 */
	public static int hexToDec(String hex) {
		return Integer.parseInt(hex, 16);
	}

	/**
	 * Split text into n number of characters.
	 *
	 * @param text
	 *            the text to be split.
	 * @param size
	 *            the split size.
	 * @return an array of the split text.
	 */
	public static String[] splitToNChar(String text, int size) {
		List<String> parts = new ArrayList<>();

		int length = text.length();
		for (int i = 0; i < length; i += size) {
			parts.add(text.substring(i, Math.min(length, i + size)));
		}
		return parts.toArray(new String[0]);
	}

	// convert from UTF-8 -> internal Java String format
	public static String convertFromUTF8(String s) {
		String out = null;
		try {
			out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
		} catch (java.io.UnsupportedEncodingException e) {
			return null;
		}
		return out;
	}

	// convert from internal Java String format -> UTF-8
	public static String convertToUTF8(String s) {
		String out = null;
		try {
			out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
		} catch (java.io.UnsupportedEncodingException e) {
			return null;
		}
		return out;
	}

	/**
	 * Hàm giải mã một chuỗi tín hiệu điện của RS232
	 * 
	 * @param msOriginal
	 * @return Chuỗi được mã hóa author: thompt
	 */
	public static String decodeMsbodyForRS232(String msOriginal) {

		if (isBlank(msOriginal) || msOriginal.length() < 20) {
			return null;
		}

		String msbody = new String();
		// 1. Phân tách chuỗi ban đầu thành các nhóm 4byte.
		String[] msbodyArr = splitToNChar(msOriginal, 4);
		String result = "";
		// 2. Với mỗi chuỗi con có được
		for (int j = 0; j <= msbodyArr.length - 1; j++) {
			String text = msbodyArr[j];
			String finalString = new String();
			for (int i = 0; i < text.length(); i++) {
				char character = text.charAt(i);
				// 2.1 Tra mã trong base64
				int ascii = new String(Base64Dec.base64alphabet).indexOf(character);
				// 2.2 Doi sang nhi phan 8 bit
				String binary = numberToBinary(ascii, 8);

				// 2.3 Bo di 2 bit dau
				String binaryAfterCut = binary.substring(2);
				// 2.4 Gộp các chuỗi 6 bít lại với nhau
				finalString += binaryAfterCut;
			}

			// 3 Với chuỗi bít ở trên chia thành các nhóm 8 bít
			String[] array8bits = splitToNChar(finalString, 8);
			String finalHex = new String();
			for (int i = 0; i < array8bits.length; i++) {
				String element = array8bits[i];
				// 3.1 chuyen chuoi nhi phan thanh thập phân
				int hex = binaryToDecimal(element);

				// 3.2 Tra code trong bang ASCII.
				char ch = (char) hex;
				// if (Character.isLetterOrDigit(ch) || (ch >= 32 && ch <= 126))
				// {
				finalHex += ch;
				// }

			}
			// 4. Gộp các chuỗi mã ACII lại
			result += finalHex;
		}
		// Trả về kết quả
		return result;
	}

	/**
	 * Hàm so sánh thời gian thiết bị và thời gian hiện hành
	 * 
	 * @param trk_time
	 * @param daysDiff:
	 *            Số ngày chênh lệch cho phép
	 * @return false: Nếu chênh lệch ngày thiết bị và ngày hiện tại > daysDiff,
	 *         true nếu ngược lại
	 * @throws Exception
	 */
	public static boolean Compare2Dates(String trk_time, int daysDiff) throws Exception {

		if (isBlank(trk_time) || daysDiff == 0) {
			return true;
		}
		try {
			Date deviceDate = StringToDateFromUTC(trk_time, "yyMMddHHmmss");
			Date curDate = getCurrentDate();

			// long duration = Math.abs(date1.getTime() - date2.getTime());

			long days = timeDiff(deviceDate, curDate, TimeUnit.DAYS);

			if (days > daysDiff) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			_libLog.error("Co loi xay ra trong ham Compare2Dates", e);
			return true;
		}

	}

	public static void main(String[] args) throws Exception {
		// Write your code here
		// create an empty array list
		ArrayList<String> color_list = new ArrayList<String>();

		// use add() method to add values in the list
//		color_list.add("White");
//		color_list.add("Black");
//		color_list.add("Red");
//		color_list.add(null);
//		color_list.add(null);
//		color_list.add("R");
//		color_list.add("R");
//
//		if (color_list.size() > 0) {
//			System.out.println("First List :" + color_list);
//			try {
//				// Loại bỏ các cấu hình null
//				color_list.removeAll(Collections.singleton(null));
//
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			System.out.println("Second List :" + color_list);
//		}

		 String hexStr
		 = "52502c323137393430373730373a392c3139303431383039353830302c453130362e3932353838332c4e31302e3835393832352c2c33302c3134392c31322c302c30302d30302d30302d30382d30302d30302d30302d30302c2c656136302d366439342d34353230312c333e32373138352e3933332d31323338363138382c343e302e3030302d302d302d33302c393e31302d312d343039352c393e322d312d302d3139322c31373e302d32392d33312d33332d33342d33352d33372d33392d34312d34322d34322d34312d34312d34322d34332d34342d34352d34362d34372d34382d34382d34392d34392d34392d34392d34392d34392d35302d35302d35302d3530";
		
		 byte[] arrayHex = hexStringToByteArray(hexStr);
		 String strFinal = hexBytes2String(arrayHex);
		
		 System.out.println(strFinal);

//		Calendar calendar = Calendar.getInstance();
//
//        System.out.println("Year: " + calendar.get(Calendar.YEAR));
//        System.out.println("Month: " + calendar.get(Calendar.MONTH));
//        System.out.println("Date: " + calendar.get(Calendar.DATE));
//
//        System.out.println("Hour: " + calendar.get(Calendar.HOUR));
//        System.out.println("Minute: " + calendar.get(Calendar.MINUTE));
//        System.out.println("Second: " + calendar.get(Calendar.SECOND));

		
		// String hexStr1 =
		// "52502c54414e43414e473034323a312c3138313230373037343434362c453130362e3738383332382c4e31302e3735393133332c2c302e35362c33302e32362c392c302c30302d30302d30302d30302d30302d30302d30302d30302c2c";
		//
		// byte[] arrayHex1 = hexStringToByteArray(hexStr1);
		// String strFinal1 = hexBytes2String(arrayHex1);
		////
		// System.out.println(strFinal1);
		//
		// double number = 10.89;
		// System.out.println(Lib.round(number,0,0));
		// System.out.println(Lib.round(number,0,1));
		// System.out.println(Lib.round(number,1,0));

		// String coolString =
		// "6F3234382C3836393530333033323133313330352C4343452C0100000001003F001800070123050106110716140015001B0009080000093F000A08000B0000160200193F011A690929000035000006023F3F3F000357115B0604393F24230C3F0000000D3F0609001C01000000020E0C3F010100504E3F3F000000003678000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002A4339";

	}

	/*
	 * Convert bytes array to number in little endian order
	 */
	public static int byteArrayToIntLE(final byte[] b, final int offset, final int bytesNum) {
		int value = 0;
		for (int i = 0; i < bytesNum; i++) {
			if (i + offset < b.length) {
				value |= ((int) b[i + offset] & 0x000000FF) << (i * 8);
			} else {
			}
		}
		return value;
	}

	/*
	 * Convert bytes array to number in little endian order
	 */
	public static long byteArrayToIntLE2(final byte[] b, final int offset, final int bytesNum) {
		long value = 0;
		for (int i = 0; i < bytesNum; i++) {
			if (i + offset < b.length) {
				value |= ((long) b[i + offset] & 0x000000FF) << (i * 8);
			} else {
			}
		}
		return value;
	}

	/*
	 * Convert string to number in little endian order
	 */
	public static int stringToIntLE(String data) {
		// System.out.print("Debug: ");
		int numOfBytes = data.length();
		int[] arr = new int[numOfBytes];
		for (int i = 0; i < numOfBytes; i++) {
			arr[i] = (int) data.charAt(i) & 0xFF;
			// System.out.printf("%02X",arr[i]);
		}
		int value = 0;
		for (int i = 0; i < numOfBytes; i++) {
			value |= ((int) arr[i] & 0x000000FF) << (i * 8);
		}
		// System.out.printf(" ==== Debug: %08X",value);
		// System.out.println("");
		return value;
	}
}
