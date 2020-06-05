package com.dcf.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

public class StringUtil {

	public static String javaShortTypeName(String fullname) {
		int indx = fullname.lastIndexOf('.');
		return fullname.substring(indx + 1);
	}

	/**
	 * FEE_AA fee_aa -> feeAa
	 * 
	 * @return
	 */
	public static String nameFormat(String input) {
		input = input.toLowerCase();
		String[] words = input.split("_");
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			if(i==0 && words[i].equals("t")){
				continue;
			}
			String word = firstCharUpcase(words[i]);
			res.append(word);
		}
		return firstCharLowcase(res.toString());
	}

	/**
	 * FEE_AA fee_aa -> FeeAa
	 *
	 * @return
	 */
	public static String nameFormatOther(String input) {
		input = input.toLowerCase();
		String[] words = input.split("_");
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String word = firstCharUpcase(words[i]);
			res.append(word);
		}
		return res.toString();
	}

	public static String firstCharUpcase(String input) {
		byte[] items = input.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}

	public static String firstCharLowcase(String input) {
		byte[] items = input.getBytes();
		items[0] = (byte) ((char) items[0] - 'A' + 'a');
		return new String(items);
	}

	/**
	 * 替换字符串模板中的占位属性
	 * 
	 * @param values
	 * @param strTemplate
	 * @return
	 */
	public static String generateMessageWithTemplate(Map<String, Object> values, String strTemplate) {
		String resultMessage = null;

		try {
			Template template = new Template("name", new StringReader(strTemplate), new Configuration());
			StringWriter writer = new StringWriter();
			template.process(values, writer);
			resultMessage = writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

		return resultMessage;
	}



	public static void main(String args[]) {
		System.out.println(System.getProperty("user.dir"));
	}

}
