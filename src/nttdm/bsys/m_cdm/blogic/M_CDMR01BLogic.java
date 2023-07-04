/*
 * @(#)Action1BLogic.java
 *
 *
 */
package nttdm.bsys.m_cdm.blogic;

import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.m_cdm.bean.MCodeBean;
import nttdm.bsys.m_cdm.bean.M_CDMFormBean;
import nttdm.bsys.m_cdm.dto.M_CDMR01Output;

/**
 * BusinessLogic class.
 * 
 * @author Duyen.Vo
 */
public class M_CDMR01BLogic extends AbstractM_CDMR01BLogic {
	
	/**
	 * <div>sql to get code list by code id</div>
	 */
	private static final String GET_CODE_LIST="SELECT.M_CDM.SQL001";
	
	/**
	 * <div>sql to get code value by code id</div>
	 */
	private static final String GET_CODE_VALUE="SELECT.M_CDM.SQL002";
	
	/**
	 * <div>
	 * default status is 1
	 * 1 : removed items
	 * 0 : added items
	 * </div> 
	 */
	private static final String STATUS_ADD = "0";
	
	/**
	 * <div>query dao</div>
	 */
	private QueryDAO queryDAO;
	
	/**
	 * 
	 * @param param
	 * @return 
	 */
	public BLogicResult execute(Map<String,Object> param) {
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)param.get("uvoObject");
		String accessType = uvo.getUserAccessInfo("M", "M-CDM").getAccess_type();
		String mode="";
		if("1".equals(accessType)){
			mode = "READONLY";
		}
		//define qcsno list
		MCodeBean[] qcsno = queryDAO.executeForObjectArray(GET_CODE_LIST, M_CDMFormBean.QCSNO, MCodeBean.class);
		//expand array to 4 items
		qcsno = this.expand(M_CDMFormBean.QCSNO, qcsno, M_CDMFormBean.MAX_CONTROLS);
		//get code value 
		String qcsCodeValue = queryDAO.executeForObject(GET_CODE_VALUE, M_CDMFormBean.QCSNO, String.class);
		//define quono list
		MCodeBean[] quono = queryDAO.executeForObjectArray(GET_CODE_LIST, M_CDMFormBean.QUONO, MCodeBean.class);
		//expand array to 4 items
		quono = this.expand(M_CDMFormBean.QUONO, quono, M_CDMFormBean.MAX_CONTROLS);		
		//get code value 
		String quoCodeValue = queryDAO.executeForObject(GET_CODE_VALUE, M_CDMFormBean.QUONO, String.class);
		//define bifno list
		MCodeBean[] bifno = queryDAO.executeForObjectArray(GET_CODE_LIST, M_CDMFormBean.BIFNO, MCodeBean.class);
		//expand array to 4 items
		bifno = this.expand(M_CDMFormBean.BIFNO , bifno, M_CDMFormBean.MAX_CONTROLS);
		//get code value 
		String bifCodeValue = queryDAO.executeForObject(GET_CODE_VALUE, M_CDMFormBean.BIFNO, String.class);
		//define bifdn list
		MCodeBean[] bifdn = queryDAO.executeForObjectArray(GET_CODE_LIST, M_CDMFormBean.BIFDN, MCodeBean.class);
		//expand array to 4 items
		bifdn = this.expand(M_CDMFormBean.BIFDN , bifdn, M_CDMFormBean.MAX_CONTROLS);
		//get code value 
		String bifdnCodeValue = queryDAO.executeForObject(GET_CODE_VALUE, M_CDMFormBean.BIFDN, String.class);
		//define bifcn list
		MCodeBean[] bifcn = queryDAO.executeForObjectArray(GET_CODE_LIST, M_CDMFormBean.BIFCN, MCodeBean.class);
		//expand array to 4 items
		bifcn = this.expand(M_CDMFormBean.BIFCN , bifcn, M_CDMFormBean.MAX_CONTROLS);
		//get code value 
		String bifcnCodeValue = queryDAO.executeForObject(GET_CODE_VALUE, M_CDMFormBean.BIFCN, String.class);
		//define invno list
		MCodeBean[] invno = queryDAO.executeForObjectArray(GET_CODE_LIST, M_CDMFormBean.INVNO, MCodeBean.class);
		//expand array to 4 items
		invno = this.expand(M_CDMFormBean.INVNO , invno, M_CDMFormBean.MAX_CONTROLS);
		//get code value 
		String invCodeValue = queryDAO.executeForObject(GET_CODE_VALUE, M_CDMFormBean.INVNO, String.class);
		//define dbtno list
		MCodeBean[] dbtno = queryDAO.executeForObjectArray(GET_CODE_LIST, M_CDMFormBean.DBTNO, MCodeBean.class);
		//expand array to 4 items
		dbtno = this.expand(M_CDMFormBean.DBTNO, dbtno, M_CDMFormBean.MAX_CONTROLS);			
		//get code value 
		String dbtCodeValue = queryDAO.executeForObject(GET_CODE_VALUE, M_CDMFormBean.DBTNO, String.class);
        // define cdtno list
        MCodeBean[] cdtno = queryDAO.executeForObjectArray(GET_CODE_LIST,
                M_CDMFormBean.CDTNO, MCodeBean.class);
        // expand array to 4 items
        cdtno = this.expand(M_CDMFormBean.CDTNO, cdtno,
                M_CDMFormBean.MAX_CONTROLS);
       // get code value
        String cdtCodeValue = queryDAO.executeForObject(GET_CODE_VALUE,
                M_CDMFormBean.CDTNO, String.class);
        // define cdtno list
        MCodeBean[] ntinv = queryDAO.executeForObjectArray(GET_CODE_LIST,
                M_CDMFormBean.NTINV, MCodeBean.class);
        // expand array to 4 items
        ntinv = this.expand(M_CDMFormBean.NTINV, ntinv,
                M_CDMFormBean.MAX_CONTROLS);
        // get code value
        String ntinvCodeValue = queryDAO.executeForObject(GET_CODE_VALUE,
                M_CDMFormBean.NTINV, String.class);
        // define rcpno list
        MCodeBean[] rcpno = queryDAO.executeForObjectArray(GET_CODE_LIST,
                M_CDMFormBean.RCPNO, MCodeBean.class);
        // expand array to 4 items
        rcpno = this.expand(M_CDMFormBean.RCPNO, rcpno,
                M_CDMFormBean.MAX_CONTROLS);
        // get code value
        String rcpCodeValue = queryDAO.executeForObject(GET_CODE_VALUE,
                M_CDMFormBean.RCPNO, String.class);

        // define scpid list
        MCodeBean[] scpid = queryDAO.executeForObjectArray(GET_CODE_LIST,
                M_CDMFormBean.SCPID, MCodeBean.class);
        // expand array to 4 items
        scpid = this.expand(M_CDMFormBean.SCPID, scpid,
                M_CDMFormBean.MAX_CONTROLS);
        // get code value
        String scpCodeValue = queryDAO.executeForObject(GET_CODE_VALUE,
                M_CDMFormBean.SCPID, String.class);

        // define ctmid list
        MCodeBean[] ctmid = queryDAO.executeForObjectArray(GET_CODE_LIST,
                M_CDMFormBean.CTMID, MCodeBean.class);
        // expand array to 4 items
        ctmid = this.expand(M_CDMFormBean.CTMID, ctmid,
                M_CDMFormBean.MAX_CONTROLS);
        // get code value
        String ctmCodeValue = queryDAO.executeForObject(GET_CODE_VALUE,
                M_CDMFormBean.CTMID, String.class);
        
        // define bacno list
        MCodeBean[] bacno = queryDAO.executeForObjectArray(GET_CODE_LIST,
                M_CDMFormBean.BACNO, MCodeBean.class);
        // expand array to 4 items
        bacno = this.expand(M_CDMFormBean.BACNO, bacno,
                M_CDMFormBean.MAX_CONTROLS);
        // get code value
        String bacCodeValue = queryDAO.executeForObject(GET_CODE_VALUE,
                M_CDMFormBean.BACNO, String.class);
        
        //define dbtno list
        MCodeBean[] soano = queryDAO.executeForObjectArray(GET_CODE_LIST, M_CDMFormBean.SOANO, MCodeBean.class);
        //expand array to 4 items
        soano = this.expand(M_CDMFormBean.SOANO, soano, M_CDMFormBean.MAX_CONTROLS);            
        //get code value 
        String soaCodeValue = queryDAO.executeForObject(GET_CODE_VALUE, M_CDMFormBean.SOANO, String.class);

		//define output
		M_CDMR01Output output = new M_CDMR01Output();
		//set qcsno
		output.setQcsno(qcsno);
		output.setQcsCodeValue(qcsCodeValue);
		//set quono
		output.setQuono(quono);
		output.setQuoCodeValue(quoCodeValue);
		//set bifno
		output.setBifno(bifno);
		output.setBifCodeValue(bifCodeValue);
		
		//set bifdn
		output.setBifdn(bifdn);
		output.setBifdnCodeValue(bifdnCodeValue);
		
		//set bifcn
		output.setBifcn(bifcn);
		output.setBifcnCodeValue(bifcnCodeValue);
		
		//set invno
		output.setInvno(invno);
		output.setInvCodeValue(invCodeValue);
		//set dbtno
		output.setDbtno(dbtno);
		output.setDbtCodeValue(dbtCodeValue);
        // set cdtno
        output.setCdtno(cdtno);
        output.setCdtCodeValue(cdtCodeValue);
        
        // set ntinv
        output.setNtinv(ntinv);
        output.setNtinvCodeValue(ntinvCodeValue);
        // set rcpno
        output.setRcpno(rcpno);
        output.setRcpCodeValue(rcpCodeValue);
        // set scpid
        output.setScpid(scpid);
        output.setScpCodeValue(scpCodeValue);
        // set ctmid
        output.setCtmid(ctmid);
        output.setCtmCodeValue(ctmCodeValue);
        // set bacno
        output.setBacno(bacno);
        output.setBacCodeValue(bacCodeValue);
        
        // set soano
        output.setSoano(soano);
        output.setSoaCodeValue(soaCodeValue);
        
		//define blogic result 
        output.setMode(mode);
		BLogicResult result = new BLogicResult();
		result.setResultObject(output);
		result.setResultString("success");
		//return
		return result;
	}
	
	/**
	 * Expand array to equals maximum controls length
	 * @param type
	 * @param array
	 * @param size
	 * @return
	 */
	private MCodeBean[] expand(String type, MCodeBean[] array, int size) {
		//declare temp array 
		MCodeBean[] temp = new MCodeBean[size];
		//copy array to temp array
	    System.arraycopy(array, 0, temp, 0, array.length);
	    //fill default values to rest of item in temp array 
	    for(int j = array.length; j < size; j++){
	    	temp[j] = new MCodeBean(type,String.valueOf(j+1));
	    	if(j==0){
	    		//when data is blank, always display 1 combobox
	    		temp[j].setStatus(STATUS_ADD);
	    	}
	    }
	    return temp;
	}
	
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}
	
}