package nttdm.bsys.r_rev.blogic;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;
import nttdm.bsys.common.bean.G_SET_ReturnValue;
import nttdm.bsys.common.bean.T_SET_BATCH;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_SET_P01;
import nttdm.bsys.r_rev.dto.RP_R_REV_S02RegenerateIO;
import nttdm.bsys.r_rev.blogic.R_REV_R02BLogic;

public class RP_R_REV_S02RegenerateBLogic extends AbstractRP_R_REV_S02RegenerateBLogic{

		public BLogicResult execute (RP_R_REV_S02RegenerateIO input) {
			BLogicResult result = new BLogicResult();
			
			String reportMonth = this.getReportMonth(input.getMonthlyReportYear(),
					input.getMonthlyReportMonth());
			
			// 2.1
			Integer fileCount = 0;
			String moduledID = "E";
			String userId = input.getUvo().getId_user();
			
			// 3.1
			T_SET_BATCH t_batch = new T_SET_BATCH();
			t_batch.setSTATUS("1");
			t_batch.setBATCH_TYPE("RR");
			t_batch.setFILENAME("Regenerate Excel Report " + reportMonth);
			
			// 3.2
			G_SET_P01 gsetp01 = new G_SET_P01(queryDAO, updateDAO);
			G_SET_ReturnValue returnValue = gsetp01.G_SET_P01_GetIdBatch(t_batch);
			
			// 3.3
			int batchId = returnValue.getBatch_id();
			
			// batch_id is -1
			if (batchId == -1) {
				BLogicMessages msgs = new BLogicMessages();
				BLogicMessage msg = new BLogicMessage("errors.ERR1SC112",
						new Object[] { returnValue.getRET_MSG() });
				msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
				result.setErrors(msgs);
			}
			// batch_id is not -1
			else {
				// 4.1 Process Data
				try {					
						R_REV_R02BLogic e = new R_REV_R02BLogic();
						e.setQueryDAO(this.queryDAO);
						e.setUpdateDAO(this.updateDAO);
						
						reportMonth = reportMonth.replace("/", "");
						
						Map<String, Object> m = this.queryDAO.executeForMap("R_REV.GET_REGEN_FISCAL_YEAR", reportMonth);
						
						//Regenerate Excel Report
						fileCount = (Integer)e.doSummaryExcel(m.get("FISCAL_START").toString(), m.get("FISCAL_END").toString(), reportMonth);
						
						//Add by MiffyAn Start
						R_REV_R04BLogic rev04 = new R_REV_R04BLogic();
						rev04.setQueryDAO(this.queryDAO);
						rev04.setUpdateDAO(this.updateDAO);
						Map<String, Object> m_rev04 = this.queryDAO.executeForMap("R_REV.GET_REGEN_FISCAL_YEAR_REV04BL", reportMonth);
						fileCount += (Integer)rev04.doSummaryExcel(m_rev04.get("FISCAL_START").toString(), m_rev04.get("FISCAL_END").toString(), reportMonth);
						
						R_REV_R05BLogic rev05 = new R_REV_R05BLogic();
						rev05.setQueryDAO(this.queryDAO);
						rev05.setUpdateDAO(this.updateDAO);
						Map<String, Object> m_rev05 = this.queryDAO.executeForMap("R_REV.GET_REGEN_FISCAL_YEAR_REV05BL", reportMonth);
						fileCount += (Integer)rev05.doSummaryExcel(m_rev05.get("FISCAL_START").toString(), m_rev05.get("FISCAL_END").toString(), reportMonth);
						
						R_REV_R06BLogic rev06 = new R_REV_R06BLogic();
						rev06.setQueryDAO(this.queryDAO);
						rev06.setUpdateDAO(this.updateDAO);
						Map<String, Object> m_rev06 = this.queryDAO.executeForMap("R_REV.GET_REGEN_FISCAL_YEAR_REV05BL", reportMonth);
						fileCount += (Integer)rev06.doSummaryExcel(m_rev06.get("FISCAL_START").toString(), m_rev06.get("FISCAL_END").toString(), reportMonth);
						
						R_REV_R07BLogic rev07 = new R_REV_R07BLogic();
						rev07.setQueryDAO(this.queryDAO);
						rev07.setUpdateDAO(this.updateDAO);
						fileCount += (Integer)rev07.doAdvanceTxT(reportMonth);
						//Add by MiffyAn End
						if(fileCount.equals(0))
							throw new Exception("Some error has been occured while generating excel file");
					
				} catch (Exception e) {
					e.printStackTrace();
					
					 t_batch.setSTATUS("3");
					 t_batch.setBATCH_TYPE("RR");
					 GlobalMessageResources msgResource = GlobalMessageResources
								.getInstance();
					 t_batch.setMessage(e.getMessage());
					 t_batch.setMessage(msgResource.getMessage("info.ERR2SC058",
								new Object[] {}).replace("{0}", (CommonUtils.isEmpty(e.getMessage())? "Kindly refer logs" : e.getMessage())));
					 t_batch.setID_BATCH(String.valueOf(batchId));
					 gsetp01.G_SET_P01_GetIdBatch(t_batch);
					 
					 result.setResultString("success");
	         		 return result;
				}
				
				// 4.2 ~ 4.3
				t_batch.setSTATUS("2");
				GlobalMessageResources msgResource = GlobalMessageResources
						.getInstance();
				t_batch.setMessage(msgResource.getMessage("info.ERR2SC057",
						new Object[] {}).replace("{0}", fileCount.toString()));
				t_batch.setID_BATCH(String.valueOf(batchId));
				t_batch.setBATCH_TYPE("RR");
				// 5.2
				gsetp01.G_SET_P01_GetIdBatch(t_batch);
			}
	        
			// info.ERR2SC002
            BLogicMessages msgs = new BLogicMessages();
            BLogicMessage msg = new BLogicMessage("screen.r_rev.batchProcessMsg", new Object());
            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
            result.setMessages(msgs);
            
			result.setResultString("success");
			return result;
		}
		
		private String getReportMonth(String year, String month) {
			String rslt = "";
			SimpleDateFormat inSDF = new SimpleDateFormat("yyyyMM");
			SimpleDateFormat outSDF = new SimpleDateFormat("yyyy/MM");
			try {
				rslt = outSDF.format(inSDF.parse(year + month));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return rslt;
		}
}
