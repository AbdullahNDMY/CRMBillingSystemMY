/*
 * @(#)A_USR_S01_SearchBLogic.java
 *
 *
 */
package nttdm.bsys.a_usr.blogic;

import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_usr.dto.A_USR_S01IO;
import nttdm.bsys.a_usr.dto.InputSearch;
import nttdm.bsys.a_usr.dto.User;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.dto.SearchBy;

import org.apache.struts.action.ActionMessages;

/**
 * BusinessLogic class.
 * 
 * @author hungtm
 */
public class A_USR_S01_SearchBLogic extends AbstractA_USR_S01_SearchBLogic {

	public BLogicResult execute(A_USR_S01IO input) {
		BLogicResult result = new BLogicResult();
		A_USR_S01IO output = new A_USR_S01IO();
		BillingSystemSettings settings = new BillingSystemSettings(queryDAO);
		InputSearch inputSearch = null;
		String totalCount = "";
		//---get row
		String row = String.valueOf(settings.getResultRow());
		//---get start index
		String startIndex = input.getStartIndex();
		//---Get inputSearch
		//if search by button then input search will be submit data
		String searchBy = input.getSearchBy();
		if(searchBy.equals(SearchBy.BUTTON)){
			inputSearch = input.getInputSearch();
		//if search by page link then input search will be session's data
		} else if(searchBy.equals(SearchBy.PAGELINK)){
			inputSearch = input.getFirstInputSearch();
		}
		//---get listUser (result)
		List<User> listUser = queryDAO.executeForObjectList("SELECT.A_USR.S01.SEARCH", inputSearch,Integer.parseInt(startIndex), Integer.parseInt(row));
		//---get totalCount
		totalCount = queryDAO.executeForObject("SELECT.A_USR.S01.SEARCH_TOTALCOUNT", inputSearch, String.class);
		output.setRow(row);
		output.setStartIndex(startIndex);
		output.setInputSearch(inputSearch);
		output.setFirstInputSearch(inputSearch);
		output.setListUser(listUser);
		output.setTotalCount(totalCount);
		
		if("0".equals(totalCount)){
		    // info.ERR2SC002
            BLogicMessages msgs = new BLogicMessages();
            BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
            result.setMessages(msgs);
		}
		
		result.setResultObject(output);
		result.setResultString("success");
		return result;
	}
}