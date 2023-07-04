package nttdm.bsys.util.word;

public enum Align {
	LEFT("left"), RIGHT("right"), CENTER("center"), BOTH("both");
	
	private String value;
	
	Align(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
