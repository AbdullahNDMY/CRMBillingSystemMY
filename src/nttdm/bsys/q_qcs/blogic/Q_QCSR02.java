package nttdm.bsys.q_qcs.blogic;

import java.io.File;
import java.io.FileNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.terasoluna.fw.dao.QueryDAO;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.q_qcs.bean.Q_QCSFormBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;


public class Q_QCSR02 extends DownloadAction{
	protected static final String CONTENT_DISPOSITION = "Content-disposition";
	protected static final String UTF_8 = "UTF-8";
	protected static final String ISO_8859_1 = "ISO-8859-1";
	protected static final String PATH_FLAG = "/";
	protected static final String CONTENT_TYPE = "";
	
	private String filePath = "";
	protected QueryDAO queryDAO = null;
	
	public QueryDAO getQueryDAO() {
	return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            super.execute(mapping, form, request, response);
            return null;
        } catch (FileNotFoundException e) {           
            response.reset();
            return mapping.findForward(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
        }

    }
	@Override
	protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Get file path
		BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);			
		this.filePath = systemSetting.getFileLocation();		
		Q_QCSFormBean formBean = (Q_QCSFormBean)form;
        String fileName = (String) formBean.getFile_name();       
        response.setHeader(CONTENT_DISPOSITION, "attachment; filename=" + new String(fileName.getBytes(UTF_8), ISO_8859_1));     
        File file = new File(this.filePath + PATH_FLAG + fileName);       
        StreamInfo si = new FileStreamInfo(CONTENT_TYPE, file);
        return si;
	}
	
}
