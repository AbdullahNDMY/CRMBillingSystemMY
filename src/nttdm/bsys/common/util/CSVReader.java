/**
 * @(#)CSVReader.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * class for reading to character streams
 * 
 * @author i-jankw
 */
public class CSVReader {

	private BufferedReader br;

	private boolean hasNext = true;

	/** 
	 * The separator is supplied to the constructor. 
	 */
	private char separator;

	/** 
	 * The quote character is supplied to the constructor. 
	 */
	private char quotechar;

	/** 
	 * The line to start reading. 
	 */
	private int skipLines;

	private boolean linesSkiped;

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
	 * The default line to start reading.
	 */
	public static final int DEFAULT_SKIP_LINES = 0;

	/**
	 * Constructs CSVReader using a comma for the separator.
	 * 
	 * @param reader
	 *            the reader to an underlying CSV source.
	 */
	public CSVReader(Reader reader) {
		this(reader, DEFAULT_SEPARATOR);
	}

	/**
	 * Constructs CSVReader with supplied separator.
	 * 
	 * @param reader
	 *            the reader to an underlying CSV source.
	 * @param separator
	 *            the delimiter to use for separating entries.
	 */
	public CSVReader(Reader reader, char separator) {
		this(reader, separator, DEFAULT_QUOTE_CHARACTER);
	}

	/**
	 * Constructs CSVReader with supplied separator and quote char.
	 * 
	 * @param reader
	 *            the reader to an underlying CSV source.
	 * @param separator
	 *            the delimiter to use for separating entries
	 * @param quotechar
	 *            the character to use for quoted elements
	 */
	public CSVReader(Reader reader, char separator, char quotechar) {
		this(reader, separator, quotechar, DEFAULT_SKIP_LINES);
	}

	/**
	 * Constructs CSVReader with supplied separator and quote char.
	 * 
	 * @param reader
	 *            the reader to an underlying CSV source.
	 * @param separator
	 *            the delimiter to use for separating entries
	 * @param quotechar
	 *            the character to use for quoted elements
	 * @param line
	 *            the line number to skip for start reading
	 */
	public CSVReader(Reader reader, char separator, char quotechar, int line) {
		this.br = new BufferedReader(reader);
		this.separator = separator;
		this.quotechar = quotechar;
		this.skipLines = line;
	}

	/**
	 * Reads the entire file into a List with each element being a String[] of
	 * tokens.
	 * 
	 * @return a List of String[], with each String[] representing a line of the
	 *         file.
	 * 
	 * @throws IOException
	 *             if bad things happen during the read
	 */
	public List<String[]> readAll() throws IOException {

		List<String[]> allElements = new ArrayList<String[]>();
		while (hasNext) {
			String[] nextLineAsTokens = readNext();
			if (nextLineAsTokens != null) {
				allElements.add(nextLineAsTokens);
			}
		}
		return allElements;
	}

	/**
	 * Reads the next line from the buffer and converts to a string array.
	 * 
	 * @return a string array with each comma-separated element as a separate
	 *         entry.
	 * 
	 * @throws IOException
	 *             if bad things happen during the read
	 */
	public String[] readNext() throws IOException {
		String nextLine = getNextLine();
		return hasNext ? parseLine(nextLine) : null;
	}

	/**
	 * Reads the next line from the file.
	 * 
	 * @return the next line from the file without trailing newline
	 * @throws IOException
	 *             if bad things happen during the read
	 */
	private String getNextLine() throws IOException {
		if (!this.linesSkiped) {
			for (int i = 0; i < skipLines; i++) {
				br.readLine();
			}
			this.linesSkiped = true;
		}
		String nextLine = br.readLine();
		if (nextLine == null) {
			hasNext = false;
		}
		return hasNext ? nextLine : null;
	}

	/**
	 * Parses an incoming String and returns an array of elements.
	 * 
	 * @param nextLine
	 *            the string to parse
	 * @return the comma-tokenized list of elements, or null if nextLine is null
	 * @throws IOException
	 *             if bad things happen during the read
	 */
	private String[] parseLine(String nextLine) throws IOException {

		if (nextLine == null) {
			return null;
		}

		List<String> tokensOnThisLine = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		boolean inQuotes = false;
		do {
			if (inQuotes) {
				// continuing a quoted section, reappend newline
				sb.append("\n");
				nextLine = getNextLine();
				if (nextLine == null) {
					break;
				}
			}
			for (int i = 0; i < nextLine.length(); i++) {
				char c = nextLine.charAt(i);
				// this gets complex... the quote may end a quoted block, or
				// escape another quote. do a 1-char lookahead:
				if (c == quotechar) {
					// we are in quotes, therefore there can be escaped quotes
					// in here.
					if (inQuotes
					// there is indeed another character to check...and that
					// char. is a quote also.
					&& nextLine.length() > (i + 1)
							&& nextLine.charAt(i + 1) == quotechar) {
						// we have two quote chars in a row == one quote char,
						// so consume them both and put one on the token. we do
						// *not* exit the quoted text.
						sb.append(nextLine.charAt(i + 1));
						i++;
					} else if (inQuotes && i > 2 
							// in quotes
							&& nextLine.charAt(i - 1) != this.separator
							// not at the begining of an escape sequence
							&& nextLine.length() > (i + 1)
							// not at the end of an escape sequence
							&& nextLine.charAt(i + 1) != this.separator) {
						// the tricky case of an embedded quote in the
						// middle:a,bc"d"ef,g
						sb.append(c);
					} else {
						// end quote
						inQuotes = !inQuotes;
					}
				} else if (c == separator && !inQuotes) {
					tokensOnThisLine.add(sb.toString());
					
					// start work on next token
					sb = new StringBuffer();
				} else {
					sb.append(c);
				}
			}
		} while (inQuotes);
		tokensOnThisLine.add(sb.toString());
		return (String[]) tokensOnThisLine.toArray(new String[0]);
	}

	/**
	 * Closes the underlying reader.
	 * 
	 * @throws IOException
	 *             if the close fails
	 */
	public void close() throws IOException {
		br.close();
	}
}
