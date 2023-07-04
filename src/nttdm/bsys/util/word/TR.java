package nttdm.bsys.util.word;

public class TR {
	private StringBuilder txt = new StringBuilder("");
	private int[] colsWidth = null;
	private int index = 0;
	
	public String getContent() {
		String top = "\n		<w:tr wsp:rsidR=\"00505659\" wsp:rsidRPr=\"00505659\">";
		String bottom = "		</w:tr>";
		
		txt.insert(0, top);
		txt.append(bottom);
		
		return txt.toString();
	}
	
	public TR add(TD td) {
		if(colsWidth != null && colsWidth.length > index) {
			td.setWidth(colsWidth[index]);
			index++;
		}
		txt.append(td.getContent());
		return this;
	}
	
	public TR add(TD... td) {
		for(TD t : td) {
			add(t);
		}
		return this;
	}
	
	public TR setColsWidth(int... colsWidth) {
		this.colsWidth = colsWidth;
		return this;
	}
}
