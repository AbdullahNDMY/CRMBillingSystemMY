package nttdm.bsys.util.word;

public class Table implements word.api.interfaces.IElement {

	public static final int BORDER_NO = 0;
	public static final int BORDER_TOP = 1;
	public static final int BORDER_LEFT = 2;
	public static final int BORDER_RIGHT = 4;
	public static final int BORDER_BOTTOM = 8;
	public static final int BORDER_VERTICAL = 16;
	public static final int BORDER_HORIZONTAL = 32;

	private int border;
	private String hexColor;
	private int indent = 0;
	private Align align = Align.LEFT;
	private int[] colsWidth = null;
	
	private StringBuilder txt = new StringBuilder("");
	private boolean hasBeenCalledBefore = false;
	
	public Table() {
		this(BORDER_TOP | BORDER_LEFT | BORDER_RIGHT | BORDER_BOTTOM | BORDER_VERTICAL | BORDER_HORIZONTAL, "000000");
	}
	
	public Table(int border) {
		this(border, "000000");
	}
	
	public Table(int border, String hexColor) {
		this.border = border;
		this.hexColor = hexColor;
	}
	
	public String getContent() {
		if("".equals(txt.toString())){
			return "";
		}
		if(hasBeenCalledBefore ){
			return txt.toString();	
		}else{
			hasBeenCalledBefore = true;
		}
		
		String top = 
			 "\n	<w:tbl> "
			+"\n            <w:tblPr> "
			+"\n                <w:tblW w:w=\"0\" w:type=\"auto\"/> "
			+"\n                <w:jc w:val=\""+ this.align.getValue() +"\"/> "
			+"\n                <w:tblBorders> ";
			if((this.border & Table.BORDER_TOP) == Table.BORDER_TOP)
				top += "\n          <w:top w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\""+this.hexColor+"\"/> ";
			if((this.border & Table.BORDER_LEFT) == Table.BORDER_LEFT)
				top += "\n          <w:left w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\""+this.hexColor+"\"/> ";
			if((this.border & Table.BORDER_BOTTOM) == Table.BORDER_BOTTOM)
				top += "\n          <w:bottom w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\""+this.hexColor+"\"/> ";
			if((this.border & Table.BORDER_RIGHT) == Table.BORDER_RIGHT)
				top += "\n          <w:right w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\""+this.hexColor+"\"/> ";
			if((this.border & Table.BORDER_HORIZONTAL) == Table.BORDER_HORIZONTAL)
				top += "\n          <w:insideH w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\""+this.hexColor+"\"/> ";
			if((this.border & Table.BORDER_VERTICAL) == Table.BORDER_VERTICAL)
				top += "\n          <w:insideV w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\""+this.hexColor+"\"/> ";
			top +="\n               <w:tblInd w:w=\""+this.indent+"\" w:type=\"dxa\" />"
			+"\n                </w:tblBorders> "
			+"\n                <w:tblLook w:val=\"00BF\"/> "
			+"\n            </w:tblPr> "
			+"\n            <w:tblGrid> "
			+"\n                <w:gridCol w:w=\"4258\"/> "
			+"\n                <w:gridCol w:w=\"4258\"/> "
			+"\n            </w:tblGrid> "
			;
		String bottom = "\n	</w:tbl>";
		
		txt.insert(0, top);
		txt.append(bottom);
		
		return txt.toString();
	}
	
	public Table add(TR tr) {
		tr.setColsWidth(colsWidth);
		txt.append(tr.getContent());
		return this;
	}
	
	public Table add(TD... td) {
		add(new TR().setColsWidth(colsWidth).add(td));
		return this;
	}
	
	public Table setIndent(int indent) {
		this.indent = indent;
		return this;
	}
	
	public static void setDefaultFont(String fontName) {
		TD.setDefaultFont(fontName);
	}
	
	public static void setDefaultFontSize(int fontSize) {
		TD.setDefaultFontSize(fontSize);
	}

	public Table setAlign(Align align) {
		this.align = align;
		return this;
	}
	
	public Table setColsWidth(int... colsWidth) {
		this.colsWidth = colsWidth;
		return this;
	}
}
