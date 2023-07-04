package nttdm.bsys.util.word;

import word.w2004.elements.ParagraphPiece;

public class Paragraph extends word.w2004.elements.Paragraph {
	public static final int INDENT_LEFT = 1;
	public static final int INDENT_RIGHT = 2;
	
	public static final int ALIGN_LEFT = 0;
	public static final int ALIGN_RIGHT = 1;
	public static final int ALIGN_CENTER = 2;
	public static final int ALIGN_JUSTIFY = 3;
	
	private static String DEFAULT_FONT_NAME = "Cambria";
	private static int DEFAULT_FONT_SIZE = 11;
	
	private String align = "left";	
	private String indentStyle = "";
	private ParagraphPiece[] pieces = null;
	private word.w2004.style.ParagraphStyle style = new word.w2004.style.ParagraphStyle();
	private String font = DEFAULT_FONT_NAME;
	private int fontSize = DEFAULT_FONT_SIZE;
	
	public Paragraph(String value) {
		if(value == null || "".equals(value)){
			return;
		}
		ParagraphPiece piece = new ParagraphPiece(value);
		pieces = new ParagraphPiece[1];
		pieces[0] = piece;
	}
	
	public Paragraph(ParagraphPiece ... pieces) {
		this.pieces = pieces;
	}
	
	@Override
	public String getContent() {
		if(pieces == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder("");
		for (ParagraphPiece piece : pieces) {
			sb.append(piece.getContent());
		}
		
		String txt = 
			"	<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"00000000\">" 
			+"\n		{styleAlign}"
			+"\n		{styleIndent}"
			+"\n		{value}"
			+"\n	</w:p>";
		
		if("".equals(sb.toString())){ 
			return "";
		}else{
			txt = txt.replace("{styleIndent}", indentStyle);
			txt = style.getNewContentWithStyle(txt);
			String fontStr = "<w:rPr>";
			fontStr += "<w:rFonts w:ascii=\""+this.font+"\" w:h-ansi=\""+this.font+"\" />"
				+ "<wx:font wx:val=\""+this.font+"\" />"
				+ "<w:sz w:val=\""+(this.fontSize*2)+"\" />"
				+ "<w:sz-cs w:val=\""+(this.fontSize*2)+"\" />"
				+ "</w:rPr>";
			String alignStr = "<w:pPr><w:jc	w:val=\""+this.align+"\" /></w:pPr>";
			txt = txt.replace("{value}", sb.toString().replace("<w:r>", alignStr+"<w:r>").replace("<w:t>", fontStr + "<w:t>"));
			return txt;
		}
	}
	
	public word.w2004.style.ParagraphStyle getStyle() {
		return style;
	}
	
	public void setStyle(word.w2004.style.ParagraphStyle style) {
		this.style = style;
	}

	@Override
	public word.w2004.style.ParagraphStyle withStyle() {
		this.style.setElement(this);
		return this.style;
	}
	
	public static Paragraph with(String value) {
		return new Paragraph(value);
	}
	
	public static Paragraph with(Object obj) {
		if(obj == null)
			return new Paragraph("");
		return new Paragraph(obj.toString());
	}
	
	public static Paragraph withPieces(ParagraphPiece ... pieces) {
		return new Paragraph(pieces);
	}
	
	@Override
	public Paragraph create() {
		return this;
	}
	
	public Paragraph setIndent(int type, double indent) {
		if(type == INDENT_LEFT)
			indentStyle += "<w:pPr><w:ind w:left=\""+indent+"\" /></w:pPr>";
		if(type ==  INDENT_RIGHT)
			indentStyle += "<w:pPr><w:ind w:right=\""+indent+"\" /></w:pPr>";
		return this;
	}
	
	public Paragraph setFont(String font) {
		this.font = font;
		return this;
	}
	
	public Paragraph setFontSize(int fontSize) {
		this.fontSize = fontSize;
		return this;
	}
	
	public Paragraph setAlign(int align) {
		if(align == ALIGN_LEFT)
			this.align = "left";
		else if(align == ALIGN_RIGHT)
			this.align = "right";
		else if(align == ALIGN_CENTER)
			this.align = "center";
		else if(align == ALIGN_JUSTIFY)
			this.align = "both";
		return this;
	}
	
	public static void setDefaultFont(String fontName) {
		DEFAULT_FONT_NAME = fontName;
	}
	
	public static void setDefaultFontSize(int fontSize) {
		DEFAULT_FONT_SIZE = fontSize;
	}
}
