/**
 * Billing System
 * 
 * SUBSYSTEM NAME : 
 * SERVICE NAME : 
 * FUNCTION : Utility for compress files
 * FILE NAME : ZipUtil.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 */
package nttdm.bsys.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Utility for compress files<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class ZipUtil {
   // public static String SESSION_DIRECTORY = "";//init when session start
	
	/**
	 * ZIP some file to File with default file name in zip folder
	 * 
	 * @param fileNames
	 * @return
	 * @throws FileNotFoundException
	 * @throws ZipException
	 */
	public static String zip(String[] fileNames,String SESSION_DIRECTORY) throws FileNotFoundException, ZipException {
		File[] files = new File[fileNames.length];
		for(int i = 0; i < fileNames.length; i++) {
			files[i] = new File(fileNames[i]);
		}
		return zip(files, SESSION_DIRECTORY);
	}
	
	/**
	 * ZIP some file to File with default file name in zip folder
	 * 
	 * @param files
	 * @return
	 * @throws FileNotFoundException
	 * @throws ZipException
	 */
	public static String zip(File[] files,String SESSION_DIRECTORY) throws FileNotFoundException, ZipException {
		String[] zipFileNames = new String[files.length];
		for(int i = 0; i < files.length; i++) {
			zipFileNames[i] = files[i].getName();
		}
		return zip(files, zipFileNames,SESSION_DIRECTORY);
	}

	/**
	 * ZIP some file to File with special file name in zip folder
	 * 
	 * @param files
	 * @param zipFileNames
	 * @return
	 * @throws FileNotFoundException
	 * @throws ZipException
	 */
	public static String zip(File[] files, String[] zipFileNames,String SESSION_DIRECTORY) throws FileNotFoundException, ZipException {
		InputStream[] inStreams = new InputStream[files.length];
		for(int i = 0; i < files.length; i++) {
			inStreams[i] = new BufferedInputStream(new FileInputStream(files[i]));
		}
		return zip(inStreams, zipFileNames,SESSION_DIRECTORY);
	}
	
	/**
	 * ZIP some stream to File with special file name in zip folder
	 * 
	 * @param inStreams
	 * @param zipFileNames
	 * @return file after zip
	 * @throws FileNotFoundException
	 * @throws ZipException
	 */
	public static String zip(InputStream[] inStreams, String[] zipFileNames ,String SESSION_DIRECTORY) throws FileNotFoundException, ZipException {
		//generate zip file name
		String zipFileName = SESSION_DIRECTORY + File.separator + System.currentTimeMillis() + ".zip";
		ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(
				new FileOutputStream(zipFileName)));
		zipOut.setLevel(Deflater.DEFAULT_COMPRESSION);
		int len;
		byte[] buffer = new byte[2048];
		try {
			for (int i = 0; i < inStreams.length; i++) {
				zipOut.putNextEntry(new ZipEntry(zipFileNames[i]));
				while ((len = inStreams[i].read(buffer)) > 0) {
					zipOut.write(buffer, 0, len);
				}
			}
			zipOut.closeEntry();
			zipOut.close();
		} catch(IOException e) {
			throw new ZipException(e.getMessage());
		}
		return zipFileName;
	}
	
	/**
	 * ZIP some file to one zip stream with default file name in zip folder
	 * 
	 * @param fileNames
	 * @return
	 * @throws ZipException
	 * @throws FileNotFoundException 
	 */
	public static InputStream zip2(String[] fileNames) throws ZipException, FileNotFoundException {
		File[] files = new File[fileNames.length];
		for(int i = 0; i < fileNames.length; i++) {
			files[i] = new File(fileNames[i]);
		}
		return zip2(files);
	}
	
	/**
	 * ZIP some file to one zip stream with default file name in zip folder
	 * 
	 * @param files
	 * @return
	 * @throws ZipException
	 * @throws FileNotFoundException 
	 */
	public static InputStream zip2(File[] files) throws ZipException, FileNotFoundException {
		String[] zipFileNames = new String[files.length];
		for(int i = 0; i < files.length; i++) {
			zipFileNames[i] = files[i].getName();
		}
		return zip2(files, zipFileNames);
	}

	/**
	 * ZIP some file to one zip stream with special file name in zip folder
	 * 
	 * @param files
	 * @param zipFileNames
	 * @return
	 * @throws FileNotFoundException 
	 * @throws ZipException 
	 */
	public static InputStream zip2(File[] files, String[] zipFileNames) throws FileNotFoundException, ZipException {
		InputStream[] inStreams = new InputStream[files.length];
		for(int i = 0; i < files.length; i++) {
			inStreams[i] = new BufferedInputStream(new FileInputStream(files[i]));
		}
		return zip2(inStreams, zipFileNames);
	}
	
	/**
	 * ZIP some stream to one zip stream with special file name in zip folder
	 * 
	 * @param inStreams
	 * @param zipFileNames
	 * @return file after zip
	 * @throws ZipException
	 */
	public static InputStream zip2(InputStream[] inStreams, String[] zipFileNames) throws ZipException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(byteOut));
		zipOut.setLevel(Deflater.DEFAULT_COMPRESSION);
		int len;
		byte[] buffer = new byte[2048];
		try {
			for (int i = 0; i < inStreams.length; i++) {
				zipOut.putNextEntry(new ZipEntry(zipFileNames[i]));
				while ((len = inStreams[i].read(buffer)) > 0) {
					zipOut.write(buffer, 0, len);
				}
			}
			zipOut.closeEntry();
			zipOut.close();
		} catch(IOException e) {
			throw new ZipException(e.getMessage());
		}
		InputStream in = new BufferedInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
		return in;
	}
}
