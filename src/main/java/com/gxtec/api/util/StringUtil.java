package com.gxtec.api.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * 字符串操作工具包
 */
public class StringUtil {

	/**
	 * 字符串是否为空(空字符串或null)
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	

	/**
	 * 十进制转十六进制对应表
	 */
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * 字节转十六进制
	 * 
	 * @param bytes
	 * @return
	 */
	public static String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			sb.append(HEX_DIGITS[(bytes[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return sb.toString();
	}

	/**
	 * MD5编码，大写，使用默认charset(UTF-8)
	 */
	public static String md5(String str) {
		return md5(str.getBytes());
	}

	/**
	 * MD5编码，小写，使用默认charset(UTF-8)
	 */
	public static String md5Lcase(String str) {
		String output = md5(str.getBytes());
		return output == null ? null : output.toLowerCase(Locale.getDefault());
	}

	/**
	 * MD5编码，大写，使用特定charset
	 * 
	 * @param str
	 * @param charsetName
	 * @return
	 */
	public static String md5(String str, String charsetName) {
		try {
			return md5(str.getBytes(charsetName));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * MD5编码，小写，使用特定charset
	 * 
	 * @param str
	 * @param charsetName
	 * @return
	 */
	public static String md5Lcase(String str, String charsetName) {
		String output = md5(str, charsetName);
		return output == null ? null : output.toLowerCase(Locale.getDefault());
	}

	/**
	 * MD5编码，大写
	 * 
	 * @param bytes
	 * @return
	 */
	public static String md5(byte[] bytes) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(bytes);
			return toHexString(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * MD5编码，小写
	 * 
	 * @param bytes
	 * @return
	 */
	public static String md5Lcase(byte[] bytes) {
		String output = md5(bytes);
		return output == null ? null : output.toLowerCase(Locale.getDefault());
	}

	/**
	 * 把颜色数值转为#RRGGBB形式
	 * 
	 * @param color
	 * @return
	 */
	public static String getRGBColor(int color) {
		return "#" + Integer.toHexString(color | 0xff000000).substring(2);
	}

	/**
	 * 把颜色数值转为#AARRGGBB形式
	 * 
	 * @param color
	 * @return
	 */
	public static String getARGBColor(int color) {
		return "#" + Long.toHexString(color | 0xffffffff00000000L).substring(8);
	}

	
	/**
	 * 计算字符串的字节长度(半角符号计1，全角符号计2)
	 * 
	 * @param string
	 * @return
	 */
	public static int getByteLength(String string) {
		int count = 0;
		for (int i = 0; i < string.length(); i++) {
			count += Integer.toHexString(string.charAt(i)).length() == 4 ? 2 : 1;
		}
		return count;
	}

	/**
	 * 按指定长度，截断字符串，超长会添加指定后缀<br>
	 * 半角符号长度为1，全角符号长度为2
	 * 
	 * @param string 字符串
	 * @param length 保留字符串长度
	 * @param suffix 超长时添加的后缀
	 * @return 截断后的字符串
	 */
	public static String trimString(String string, int length, String suffix) {
		if (getByteLength(string) <= length)
			return string;
		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (suffix == null)
			suffix = "";
		int slength = getByteLength(suffix);
		for (int i = 0; i < string.length(); i++) {
			char temp = string.charAt(i);
			count += Integer.toHexString(temp).length() == 4 ? 2 : 1;
			if (count + slength <= length) {
				sb.append(temp);
			}
			if (count + slength >= length) {
				break;
			}
		}
		sb.append(suffix);
		return sb.toString();
	}

	/**
	 * 按指定长度，截断字符串，超长会添加…<br>
	 * 半角符号长度为1，全角符号长度为2
	 * 
	 * @param string 字符串
	 * @param length 保留字符串长度
	 * @return 截断后的字符串
	 */
	public static String trimString(String string, int length) {
		return trimString(string, length, "…");
	}

	

	/**
	 * javascript escape
	 * 
	 * @param s
	 * @return
	 */
	public static String escape(String s) {
		StringBuilder sb = new StringBuilder();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			int ch = s.charAt(i);
			if (isEscapePersistDigit(ch)) {
				sb.append((char) ch);
			} else if (ch <= 0x007F) {
				sb.append('%');
				sb.append(HEX_DIGITS[(ch & 0xf0) >>> 4]);
				sb.append(HEX_DIGITS[ch & 0x0f]);
			} else {
				sb.append('%');
				sb.append('u');
				sb.append(HEX_DIGITS[(ch & 0xf000) >>> 12]);
				sb.append(HEX_DIGITS[(ch & 0x0f00) >>> 8]);
				sb.append(HEX_DIGITS[(ch & 0x00f0) >>> 4]);
				sb.append(HEX_DIGITS[ch & 0x000f]);
			}
		}
		return sb.toString();
	}

	/**
	 * javascript unescape
	 * 
	 * @param s
	 * @return
	 */
	public static String unescape(String s) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		int len = s.length();
		while (i < len) {
			int ch = s.charAt(i);
			if (isEscapePersistDigit(ch)) {
				sb.append((char) ch);
			} else if (ch == '%') {
				boolean flag = false;
				int cint = 0;
				if ('u' == s.charAt(i + 1) || 'U' == s.charAt(i + 1)) {
					if (i + 5 < len) {
						char[] chs = new char[4];
						s.getChars(i + 2, i + 6, chs, 0);
						if (isHexDigits(chs)) {
							cint = Integer.parseInt(new String(chs), 16);
							i += 5;
							flag = true;
						}
					}
				} else {
					if (i + 2 < len) {
						char[] chs = new char[2];
						s.getChars(i + 1, i + 3, chs, 0);
						if (isHexDigits(chs)) {
							cint = Integer.parseInt(new String(chs), 16);
							i += 2;
							flag = true;
						}
					}
				}
				if (flag)
					sb.append((char) cint);
				else
					sb.append((char) ch);
			} else {
				sb.append((char) ch);
			}
			i++;
		}
		return sb.toString();
	}

	private static boolean isHexDigit(int ch) {
		return ('A' <= ch && ch <= 'Z') || ('a' <= ch && ch <= 'z') || ('0' <= ch && ch <= '9');
	}

	private static boolean isHexDigits(char[] chs) {
		for (int i = 0; i < chs.length; i++) {
			if (!isHexDigit(chs[i]))
				return false;
		}
		return true;
	}

	private static boolean isEscapePersistDigit(int ch) {
		return isHexDigit(ch) || ch == '*' || ch == '@' || ch == '-' || ch == '_' || ch == '+' || ch == '.' || ch == '/';
	}

}
