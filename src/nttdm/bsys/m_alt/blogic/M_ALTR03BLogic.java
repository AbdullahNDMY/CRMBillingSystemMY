package nttdm.bsys.m_alt.blogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.terasoluna.fw.dao.QueryDAO;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.m_alt.bean.M_ALTFormBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;

public class M_ALTR03BLogic extends DownloadAction{
	protected static final String FILE_NAME = "file_name";

	  
    protected static final String FILE_PATH = "filePath";

   
    protected static final String FAILURE = "failure";

  
    protected static final String CONTENT_DISPOSITION = "Content-disposition";

  
    protected static final String UTF_8 = "UTF-8";

  
    protected static final String ISO_8859_1 = "ISO-8859-1";


    protected static final String PATH_FLAG = "/";

    protected static final String CONTENT_TYPE = "";
        
    protected QueryDAO queryDAO = null;
    
    private String filePath = "";
    
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
            return mapping.findForward(FAILURE);
        }

    }
	@Override
	protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Get file path
		BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);			
		this.filePath = systemSetting.getFileLocation();

		
		M_ALTFormBean formBean = (M_ALTFormBean)form;
        
        Map<String, Object> doc = queryDAO.executeForMap("SELECT.M_ALT.SQL010", formBean.getFile_id());
        String fileName = (String) doc.get("FILENAME");
        String filePath = (String) doc.get("FILELOCATION");
       
        response.setHeader(CONTENT_DISPOSITION, "attachment; filename=" + new String(fileName.getBytes(UTF_8), ISO_8859_1));
       
        File file = new File(filePath);
       
        StreamInfo si = new FileStreamInfo(CONTENT_TYPE, file);
        return si;
	}

}
