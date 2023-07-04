package nttdm.bsys.util.word;

public class Document2004 extends word.w2004.Document2004 {
	
	@Override
	public String getContent() {
		String myWord = super.getContent();
        //format A4 page
        //margin top:0.5inch, bottom:0.5inch, left:0.875inch, right:0.875inch
        String a4 = "<w:pgSz w:w=\"11907\" w:h=\"16839\" w:code=\"9\" />\n";
        a4 += 		"<w:pgMar w:top=\"720\" w:right=\"1260\" w:bottom=\"720\" ";
        a4 += 		"w:left=\"1260\" w:header=\"720\" w:footer=\"720\" ";
        a4 += 		"w:gutter=\"0\"/>\n";
        a4 += "</w:sectPr>";
        myWord = myWord.replace("</w:sectPr>", a4);
        
		return myWord;
	}
	
	
}
