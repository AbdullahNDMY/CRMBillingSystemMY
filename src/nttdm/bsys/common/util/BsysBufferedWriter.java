/**
 * @(#)BsysBufferedWriter.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.BufferedWriter;

import java.io.IOException;

import java.io.Writer;

/**
 * class for writing to character streams.
 * 
 * @author i-jankw
 * 
 */
public class BsysBufferedWriter extends BufferedWriter {

	/**
	 * Constructs a BsysBufferedWriter object given a Writer Object .
	 * 
	 * @param out
	 */
	public BsysBufferedWriter(Writer out) {
		super(out);
	}

	/**
	 * Writes a string.
	 * 
	 * @param str
	 * @return BsysBufferedWriter
	 * @throws IOException
	 */
	public BsysBufferedWriter print(String str) throws IOException {
		this.write(str);
		return this;
	}

	/**
	 * Writes a portion of a string.
	 * 
	 * @param str
	 * @param length
	 * @return BsysBufferedWriter
	 * @throws IOException
	 */
	public BsysBufferedWriter print(String str, int length) throws IOException {
		String spaces = "";
		str = str.trim();//
		if (length > str.length())
			spaces = BsysBufferedWriter.str(" ", length - str.length());
		if (length < str.length())
			str = str.substring(0, length);

		this.append(str).append(spaces);
		return this;
	}

	/**
	 * Writes a portion of a int
	 * 
	 * @param i
	 * @param length
	 * @return BsysBufferedWriter
	 * @throws IOException
	 */
	public BsysBufferedWriter print(int i, int length) throws IOException {
		return this.print(Integer.toString(i), length);
	}

	/**
	 * Writes a int.
	 * 
	 * @param i
	 * @return BsysBufferedWriter
	 * @throws IOException
	 */
	public BsysBufferedWriter print(int i) throws IOException {
		this.write(Integer.toString(i));
		return this;
	}

	/**
	 * Writes a portion of a object.
	 * 
	 * @param o
	 * @param length
	 * @return BsysBufferedWriter
	 * @throws IOException
	 */
	public BsysBufferedWriter print(Object o, int length) throws IOException {
		return this.print(toString(o), length);
	}

	/**
	 * Writes a object.
	 * 
	 * @param o
	 * @return BsysBufferedWriter
	 * @throws IOException
	 */
	public BsysBufferedWriter print(Object o) throws IOException {
		return this.print(toString(o));
	}

	/* static businesses */
	public static String str(String str, int times) {
		if ("".equalsIgnoreCase(str))
			return str;

		String result = "";
		for (int i = 0; i < times; i++)
			result += str;
		return result;
	}

	/* overriding toString */
	protected static String toString(Object o) {
		if (o == null)
			return "";
		return String.valueOf(o);
	}
}
