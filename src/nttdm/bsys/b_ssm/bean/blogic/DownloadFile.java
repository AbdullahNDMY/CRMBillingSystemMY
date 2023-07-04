package nttdm.bsys.b_ssm.bean.blogic;

import java.io.IOException;
import java.io.PrintWriter;
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_E01
 * FUNCTION       : Download export file
 * FILE NAME      : DownloadFile.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 * BusinessLogic class.
 * @author NTT Data Vietnam	
 * Export file B_SSM_E01
 * 
 */
public class DownloadFile {
	public void exportFile(List<String[]> exportList,String fileName,HttpServletResponse response) {		
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        response.setContentType("application/msword; charset=UTF-8");
        PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int j=0;j<exportList.size();j++){
		    int colLen = exportList.get(j).length;
			for(int k=0;k<colLen;k++){
				writer.append("\""+exportList.get(j)[k].toString()+"\"");
				writer.flush();
				if(k!=(colLen-1)) {
				    writer.append(",");
				}
			}
			writer.append("\n");
		}
        try {
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
