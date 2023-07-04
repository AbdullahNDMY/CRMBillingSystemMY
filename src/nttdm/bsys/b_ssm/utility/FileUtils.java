/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S01
 * FUNCTION       : Containing utilities for BLogic processing
 * FILE NAME      : BLogicUtils.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @author NTT Data VietNam
 * Class containing utilities for file processing
 */
public class FileUtils {

	/**
	 * Export specific file
	 * @param response
	 * @param fileName
	 * @param path
	 * @param bufferCount
	 */
	public static void exportFile(HttpServletResponse response, 
									String fileName,
									String path,
									int bufferCount) {
		 
		 response.setContentType("application/octet-stream");
	     response.setHeader("Content-Disposition","attachment; filename=" + fileName);
	 
	     try 
	     {	       
	       	FileInputStream in = new FileInputStream(new File(path));	 
	       	// Get servlet out
	        ServletOutputStream out = response.getOutputStream();
	        // Initilize out mem
	        byte[] outputByte = new byte[bufferCount > 0 ? bufferCount : 4096];
	        // Get file and write output
	        while(in.read(outputByte, 0, bufferCount) != -1) {
	        	out.write(outputByte, 0, bufferCount);
	        }
	        // Close and flush
	        in.close();
	        out.flush();
	        out.close();
	 
	     } catch(Exception e) {
	    	e.printStackTrace();
	     }	 	   
	}
	
	/**
	 * Export specific file
	 * @param response
	 * @param fileName
	 * @param path
	 */
	public static void exportFile(HttpServletResponse response,
									String fileName,
									String path) {
		 response.setContentType("application/octet-stream");
	     response.setHeader("Content-Disposition","attachment; filename=" + fileName);
	 
	     try 
	     {	       
	       	File file = new File(path);
	    	FileInputStream in = new FileInputStream(file);	 
	        // Get servlet out
	        ServletOutputStream out = response.getOutputStream();
	        // Initilize out mem
	        byte[] outputByte = new byte[(int) file.length()];
	        // Get file and write output
	        in.read(outputByte);
	        out.write(outputByte);
	        // Close and flush
	        in.close();
	        out.flush();
	        out.close();
	     } catch(Exception e) {
	    	e.printStackTrace();
	     }	 	   
	}

	/**
	 * Export file
	 * @param response
	 * @param inputStream
	 * @param fileName
	 * @param contentType
	 */
	public static void exportFile(HttpServletResponse response,
									InputStream inputStream,
									String fileName,
									String contentType) {
		 response.setContentType(contentType);
	     response.setHeader("Content-Disposition","attachment; filename=" + fileName);
	 
	     try 
	     {	       	      
	       	int bufferCount = 1;
	       	int len = -1;
	    	// Get servlet out
	        ServletOutputStream outputStream = response.getOutputStream();
	        // Initilize out mem
	        byte[] outputByte = new byte[bufferCount];
	        // Get file and write output
	        while((len = inputStream.read(outputByte)) != -1) {
	        	outputStream.write(outputByte, 0, len);
	        }
	        // Close and flush
	        inputStream.close();
	        outputStream.flush();
	        outputStream.close();
	 
	     } catch(Exception e) {
	    	e.printStackTrace();
	     }	 	   
	}
	
	/**
	 * Export zip file
	 * @param response
	 * @param inputStream
	 * @param fileName
	 */
	public static void exportZipFile(HttpServletResponse response,
									   String zipFileName,
									   InputStream inputStream,
									   String fileName) {
		 response.setContentType("application/zip");
	     response.setHeader("Content-Disposition","attachment; filename=" + zipFileName);
	 
	     try 
	     {	       	      
	       	int bufferCount = 1;
	       	int len = -1;
	    	// Get servlet out
	        ServletOutputStream outputStream = response.getOutputStream();
	        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
	        zipOutputStream.putNextEntry(new ZipEntry(fileName));
	       
	        // Initilize out mem
	        byte[] outputByte = new byte[bufferCount];
	        // Get file and write output
	        while((len = inputStream.read(outputByte)) != -1) {
	        	zipOutputStream.write(outputByte, 0, len);
	        }	        
	        zipOutputStream.closeEntry();
	        // Close and flush
	        inputStream.close();
	        zipOutputStream.finish();
	        zipOutputStream.close();
	 
	     } catch(Exception e) {
	    	e.printStackTrace();
	     }	 	   
	}
	
	/**
	 * Export zip file
	 * @param response
	 * @param inputStreams
	 * @param fileNames
	 */
	public static void exportZipFile(HttpServletResponse response,
									   String zipFileName,
									   List<InputStream> inputStreams,
									   List<String> fileNames) {
		 if (inputStreams == null || fileNames == null) {
			 return;
		 }
		
		 response.setContentType("application/zip");
	     response.setHeader("Content-Disposition","attachment; filename=" + zipFileName);
	 
	     try 
	     {	       	      
	       	int bufferCount = 1;
	       	int len = -1;
	    	// Get servlet out
	        ServletOutputStream outputStream = response.getOutputStream();
	        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
	        for (int i = 0 ; i < inputStreams.size(); i++) {
	        	String fileName = fileNames.get(i);
	        	InputStream inputStream = inputStreams.get(i);
		        zipOutputStream.putNextEntry(new ZipEntry(fileName));
		        // Initilize out mem
		        byte[] outputByte = new byte[bufferCount];
		        // Get file and write output
		        while((len = inputStream.read(outputByte)) != -1) {
		        	zipOutputStream.write(outputByte, 0, len);
		        }	        
		        zipOutputStream.closeEntry();
		        inputStream.close();
	        }
	        // Close and flush
	        zipOutputStream.finish();
	        zipOutputStream.close();
	 
	     } catch(Exception e) {
	    	e.printStackTrace();
	     }	 	   
	}

	/**
	 * Export text
	 * @param str
	 * @throws IOException io exception
	 */
	public static void exportText(HttpServletResponse response, String str) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(str);
		out.flush();
		out.close();
	}
	
}
