package nttdm.bsys.m_bnk.dto;
import java.io.Serializable;
import java.util.List;
import nttdm.bsys.m_bnk.bean.M_BNK_bankbean;

public class M_BNKS01Output implements Serializable {
	
	
	private static final long serialVersionUID = 8631810323760841623L;
	
	private int row;	
	private int startIndex;	
	private int totalCount;
	private int checkpagetype;
	
	public int getCheckpagetype() {
		return checkpagetype;
	}
	public void setCheckpagetype(int checkpagetype) {
		this.checkpagetype = checkpagetype;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	private List<M_BNK_bankbean> listsearch;

	
	public List<M_BNK_bankbean> getListsearch() {
		return listsearch;
	}
	public void setListsearch(List<M_BNK_bankbean> listsearch) {
		this.listsearch = listsearch;
	}
	
	
	
	
}
