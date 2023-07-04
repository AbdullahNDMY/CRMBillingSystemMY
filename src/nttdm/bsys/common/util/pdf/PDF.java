package nttdm.bsys.common.util.pdf;

import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
public abstract class PDF {
	protected Object data;
	public static final String PAGE=":";
	protected BaseColor title_bgcolor;
	protected Font title_font;
	protected BaseColor group_title_bgcolor;
	protected Font group_tile_font;
	protected BaseColor sub_title_bgcolor;
	protected Font sub_title_font;
	protected Font item_font;
	protected String iconPath;
	public PDF(String iconPath, Object obj) throws DocumentException, IOException{
		this.title_bgcolor = new BaseColor(153,204,255);
		this.title_font = new Font(BaseFont.createFont("c:\\windows\\fonts\\msgothic.ttc,1", BaseFont.IDENTITY_H,true),12);
		this.group_title_bgcolor = new BaseColor(153,204,255);
		this.group_tile_font = new Font(BaseFont.createFont("c:\\windows\\fonts\\msgothic.ttc,1", BaseFont.IDENTITY_H,true),10);
		this.sub_title_bgcolor = new BaseColor(153,204,255);
		this.sub_title_font = new Font(BaseFont.createFont("c:\\windows\\fonts\\msgothic.ttc,1", BaseFont.IDENTITY_H,true),9);
		this.item_font = new Font(BaseFont.createFont("c:\\windows\\fonts\\msgothic.ttc,1", BaseFont.IDENTITY_H,true),7);
		this.iconPath = iconPath;
	}
	
	public abstract void addHeader(Document docPDF)throws DocumentException,IOException;
	
	public abstract void bindingData(Document docPDF)throws DocumentException,IOException;
	
}
