package nttdm.bsys.util.word;

public class TD {
	private static String DEFAULT_FONT_NAME = "Cambria";
	private static int DEFAULT_FONT_SIZE = 11;
	
	private String value;
	private String fontName = DEFAULT_FONT_NAME;
	private int fontSize = DEFAULT_FONT_SIZE;
	private int width = 4258;
	private int col = 1;
	private String background = null;
	private Align align = Align.LEFT;
	private String color = null;
	
	public TD() {
		this(" ");
	}
	
	public TD(String value) {
		if(value == null || "".equals(value))
			this.value = "";
		else
			this.value = value;
	}
	
	public String getContent() {
		String td =
		"                <w:tc> "
		+"\n                    <w:tcPr> "
		+"\n                        <w:tcW w:w=\"" + this.width + "\" w:type=\"dxa\"/> "
		+"\n                        <w:gridSpan w:val=\""+ this.col +"\" /> "
		;
		if(this.background != null) {
		td += 
		 "\n                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\""+ this.background +"\"/> ";
		}
		td +=
		"\n                    </w:tcPr> "
		+"\n                    <w:p wsp:rsidR=\"00505659\" wsp:rsidRPr=\"00505659\" wsp:rsidRDefault=\"00505659\"> "
		+"\n                        <w:pPr> "
		+"\n                        	<w:jc w:val=\""+ this.align.getValue() +"\"/> "
		+"\n                        </w:pPr> "
		+"\n                        <w:r wsp:rsidRPr=\"00505659\"> "
		+"\n                            <w:rPr> "
		+"\n                            	<w:rFonts w:ascii=\""+ this.fontName +"\" w:h-ansi=\""+ this.fontName +"\" w:cs=\""+ this.fontName +"\"/>"
		+"\n                            	<wx:font wx:val=\""+ this.fontName +"\"/> "
		+"\n                            	<w:sz w:val=\""+ (this.fontSize * 2) +"\"/> "
		+"\n                            	<w:sz-cs w:val=\""+ (this.fontSize * 2) +"\"/> "
		;
		if(this.color != null) {
		td += 
			"\n                            	<w:color w:val=\""+ this.color +"\"/> ";
		}
		td +=
		"\n                            </w:rPr> "
		+"\n                            <w:t>"+ value +"</w:t> "
		+"\n                        </w:r> "
		+"\n                    </w:p> "
		+"\n                </w:tc> "
		;
		return td;
	}

	public TD setFont(String fontName) {
		this.fontName = fontName;
		return this;
	}

	public TD setFontSize(int fontSize) {
		this.fontSize = fontSize;
		return this;
	}

	public TD setWidth(int width) {
		this.width = width;
		return this;
	}

	public TD setCol(int col) {
		this.col = col;
		return this;
	}

	public TD setBackground(String background) {
		this.background = background;
		return this;
	}
	
	public static void setDefaultFont(String fontName) {
		DEFAULT_FONT_NAME = fontName;
	}
	
	public static void setDefaultFontSize(int fontSize) {
		DEFAULT_FONT_SIZE = fontSize;
	}

	public TD setAlign(Align align) {
		this.align = align;
		return this;
	}

	public TD setColor(String color) {
		this.color = color;
		return this;
	}
}
