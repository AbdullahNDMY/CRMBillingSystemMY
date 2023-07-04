package nttdm.bsys.common.util.pdf;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public class PDFInvoicePG1 extends PDF{
	public PDFInvoicePG1(String iconPath,Object obj) throws DocumentException, IOException {
		super(iconPath,obj);
		this.data = obj;
	}
	/** 
	  * Add header to PDF file
	  * <p> 
	  * 
	  * </p> 
	  * @param Document docPDF
	  * @return None
	  * @exception DocumentException
	  */
	public void addHeader(Document docPDF){
		
	}
	
	/** 
	  * Binding data to PDF file
	  * <p> 
	  * 
	  * </p> 
	  * @param Document docPDF
	  * @return None
	  * @exception DocumentException
	  */
	public void bindingData(Document docPDF){
		
	}
	
}
