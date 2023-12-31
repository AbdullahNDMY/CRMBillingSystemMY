/**
 * @(#)CSVWriter.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_cst.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import nttdm.bsys.common.util.CommonUtils;

/**
 * class for writing to character streams
 * 
 * @author p-minzh
 */
public class CSVWriter {

	private Writer rawWriter;

	private PrintWriter pw;

	private char separator;

	private char quotechar;

	private char escapechar;

	private String lineEnd;

	/**
	 * The character used for escaping quotes.
	 */
	public static final char DEFAULT_ESCAPE_CHARACTER = '"';

	/**
	 * The default separator to use if none is supplied to the constructor.
	 */
	public static final char DEFAULT_SEPARATOR = ',';

	/**
	 * The default quote character to use if none is supplied to the
	 * constructor.
	 */
	public static final char DEFAULT_QUOTE_CHARACTER = '"';

	/**
	 * The quote constant to use when you wish to suppress all quoting.
	 */
	public static final char NO_QUOTE_CHARACTER = '\u0000';

	/**
	 * The escape constant to use when you wish to suppress all escaping.
	 */
	public static final char NO_ESCAPE_CHARACTER = '\u0000';

	/**
	 * Default line terminator uses platform encoding.
	 */
	public static final String DEFAULT_LINE_END = "\n";

	/**
	 * Constructs CSVWriter using a comma for the separator.
	 * 
	 * @param writer
	 *            the writer to an underlying CSV source.
	 */
	public CSVWriter(Writer writer) {
		this(writer, DEFAULT_SEPARATOR);
	}

	/**
	 * Constructs CSVWriter with supplied separator.
	 * 
	 * @param writer
	 *            the writer to an underlying CSV source.
	 * @param separator
	 *            the delimiter to use for separating entries.
	 */
	public CSVWriter(Writer writer, char separator) {
		this(writer, separator, NO_QUOTE_CHARACTER);
	}

	/**
	 * Constructs CSVWriter with supplied separator and quote char.
	 * 
	 * @param writer
	 *            the writer to an underlying CSV source.
	 * @param separator
	 *            the delimiter to use for separating entries
	 * @param quotechar
	 *            the character to use for quoted elements
	 */
	public CSVWriter(Writer writer, char separator, char quotechar) {
		this(writer, separator, quotechar, DEFAULT_ESCAPE_CHARACTER);
	}

	/**
	 * Constructs CSVWriter with supplied separator and quote char.
	 * 
	 * @param writer
	 *            the writer to an underlying CSV source.
	 * @param separator
	 *            the delimiter to use for separating entries
	 * @param quotechar
	 *            the character to use for quoted elements
	 * @param escapechar
	 *            the character to use for escaping quotechars or escapechars
	 */
	public CSVWriter(Writer writer, char separator, char quotechar,
			char escapechar) {
		this(writer, separator, quotechar, escapechar, DEFAULT_LINE_END);
	}

	/**
	 * Constructs CSVWriter with supplied separator and quote char.
	 * 
	 * @param writer
	 *            the writer to an underlying CSV source.
	 * @param separator
	 *            the delimiter to use for separating entries
	 * @param quotechar
	 *            the character to use for quoted elements
	 * @param lineEnd
	 *            the line feed terminator to use
	 */
	public CSVWriter(Writer writer, char separator, char quotechar,
			String lineEnd) {
		this(writer, separator, quotechar, DEFAULT_ESCAPE_CHARACTER, lineEnd);
	}

	/**
	 * Constructs CSVWriter with supplied separator, quote char, escape char and
	 * line ending.
	 * 
	 * @param writer
	 *            the writer to an underlying CSV source.
	 * @param separator
	 *            the delimiter to use for separating entries
	 * @param quotechar
	 *            the character to use for quoted elements
	 * @param escapechar
	 *            the character to use for escaping quotechars or escapechars
	 * @param lineEnd
	 *            the line feed terminator to use
	 */
	public CSVWriter(Writer writer, char separator, char quotechar,
			char escapechar, String lineEnd) {
		this.rawWriter = writer;
		this.pw = new PrintWriter(writer);
		this.separator = separator;
		this.quotechar = quotechar;
		this.escapechar = escapechar;
		this.lineEnd = lineEnd;
	}

	/**
	 * Writes the entire ResultSet to a CSV file.
	 * 
	 * The caller is responsible for closing the ResultSet.
	 * 
	 * @param rs
	 *            the recordset to write
	 * @param includeColumnNames
	 *            true if you want column names in the output, false otherwise
	 * 
	 */
	public void writeAll(List<String[]> list) {
		for (int i = 0; i < list.size(); i++) {
			String[] nextLine = list.get(i);
			writeNext(nextLine);
		}
	}

	/**
	 * Writes the next line to the file.
	 * 
	 * @param nextLine
	 *            a string array with each comma-separated element as a separate
	 *            entry.
	 */
	public void writeNext(String... nextLine) {

		if (nextLine == null)
			return;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < nextLine.length; i++) {

			if (i != 0) {
				sb.append(separator);
			}

			String nextElement = nextLine[i];
			if (nextElement == null) {
				continue;
			}
			if (quotechar != NO_QUOTE_CHARACTER) {
				sb.append(quotechar);
			}
			for (int j = 0; j < nextElement.length(); j++) {
				char nextChar = nextElement.charAt(j);
				if (escapechar != NO_ESCAPE_CHARACTER && nextChar == quotechar) {
					sb.append(escapechar).append(nextChar);
				} else if (escapechar != NO_ESCAPE_CHARACTER
						&& nextChar == escapechar) {
					sb.append(escapechar).append(nextChar);
				} else {
					sb.append(nextChar);
				}
			}
			if (quotechar != NO_QUOTE_CHARACTER) {
				sb.append(quotechar);
			}
		}

		sb.append(lineEnd);
		pw.write(sb.toString());

	}

	/**
	 * Writes the line to the file.
	 * 
	 * @param elements
	 *            a string array with each comma-separated element as a separate
	 *            entry.
	 */
	public void writeLine(Object... elements) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < elements.length; i++) {
			if (i != 0) {
				sb.append(separator);
			}
			String nextElement = CommonUtils.toString(elements[i]);
			if (nextElement == null) {
				continue;
			}
			if (quotechar != NO_QUOTE_CHARACTER) {
				sb.append(quotechar);
			}
			for (int j = 0; j < nextElement.length(); j++) {
				char nextChar = nextElement.charAt(j);
				if (escapechar != NO_ESCAPE_CHARACTER && nextChar == quotechar) {
					sb.append(escapechar).append(nextChar);
				} else if (escapechar != NO_ESCAPE_CHARACTER
						&& nextChar == escapechar) {
					sb.append(escapechar).append(nextChar);
				} else {
					sb.append(nextChar);
				}
			}
			if (quotechar != NO_QUOTE_CHARACTER) {
				sb.append(quotechar);
			}
		}
		sb.append(lineEnd);
		pw.write(sb.toString());
	}

	/**
	 * Close the underlying stream writer flushing any buffered content.
	 * 
	 * @throws IOException
	 *             if bad things happen
	 * 
	 */
	public void close() throws IOException {
		pw.flush();
		pw.close();
		rawWriter.close();
	}
}
