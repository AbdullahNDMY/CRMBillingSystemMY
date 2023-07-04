package nttdm.bsys.r_rev.blogic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Clob;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.io.*;

import nttdm.bsys.common.util.CommonUtils;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import com.sun.xml.internal.bind.v2.model.core.Ref;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

public class R_REV_R07BLogic {
	private QueryDAO queryDAO;
    private UpdateDAO updateDAO;

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}
	private Integer recordCount=0;
	protected Integer doAdvanceTxT(String rMonth) throws Exception{
		//GET filePath
		String filePath = queryDAO.executeForObject("R_REV.GET_FILE_PATH_REV05BL", null, String.class);
		//Get ReportMonth
		String reportMonth=rMonth;
		//Get UploadMonth =ReportMonth+1month
		String uploadMonth=this.getUploadMonth(rMonth);
		Integer fileCount = 0;
		//Get JournalNo and Category Into result set R1 and R2
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("reportMonth", rMonth);
		List<Map<String, Object>> journalList = queryDAO.executeForMapList("R_REV.GET_JOURNALNO_REV07BL", param);
		//Get AdvanceAcc
		String advanceAcc=queryDAO.executeForObject("R_REV.GET_ADVANCEACC_REV07BL", null, String.class);
		//Get RevenueAcc 
		String revenueAcc=queryDAO.executeForObject("R_REV.GET_REVENUEACC_REV07BL", null, String.class);
		//Get busUnit
		String busUnit=queryDAO.executeForObject("R_REV.GET_BUSUNIT_REV07BL", null, String.class);
		//Get defCur 
		String defCur=queryDAO.executeForObject("R_REV.GET_DEFCUR_REV07BL", null, String.class);
		//JournDate=last day of uploadMotth (MMDDYYYY)
		String journalDate=this.getLastDay(uploadMonth);
		String tempJN="";
		for(Map<String, Object> JLlist : journalList){
			//get journalNo
			String journalNo=(String)JLlist.get("JOURNAL_NO");
			//Get journalId=R2.JournalNo+"/"+uploadMonth(MM)+sysDate(YY)
			String journalId=journalNo+"/"+uploadMonth.substring(4)+this.getCurrentYear();
			this.recordCount=0;
		    if(tempJN.equals(journalNo)==false){
				Map<String, Object>tempparam = new HashMap<String, Object>();
				tempparam.put("journalNo", journalNo);
				List<Map<String, Object>>  journalDescList=queryDAO.executeForMapList("R_REV.GET_JOURNALDESC_REV07BL",tempparam);
				String journalDesc=" ";
				for(Map<String, Object> descList : journalDescList){
					journalDesc=(String)descList.get("DESCRIPTION");
				}
				//HeaderDesc
				String headerDesc="Reversal of adv sales-"+journalDesc;
		    	String fileName = "Advance_Sales_" +journalDesc+"_"+ reportMonth + ".txt";
				File filename = new File(filePath + File.separator +fileName); 
				if (filename.exists()) { 
					filename.delete();
				} 
				filename.createNewFile();
			    StringBuffer buf = new StringBuffer();
	        	String  txtStr="H"+this.setLength(busUnit,5)+this.setLength(journalId,10)+this.setLength(journalDate,8)
	        	              +"N"+this.setLength("0",3)+this.setLength("",8)+this.setLength("ACTUALS2",10)+this.setLength("ACTUALS2",10)
	        	              +"N"+this.setLength("",8)+this.setLength("N",3)+"S"+this.setLength("",8)+"ONL"+this.setLength("",8)
	        	              +this.setLength(headerDesc,30)+this.setLength(defCur,3)+"TTM_A"+this.setLength("",8)+this.setLength("1",17)
	        	              +"EXV"+this.setLength("",20)+"N1"+this.setLength("",14);
	        	buf.append(txtStr+"\r\n");
	        	this.recordCount=1;
	        	this.getSub1Str(rMonth,busUnit,advanceAcc,defCur,journalDesc,uploadMonth,buf,journalNo);    
	        	this.getSub2Str(rMonth,revenueAcc,busUnit,defCur,buf,journalNo);  
	            FileOutputStream fos = new FileOutputStream(filename);
	            NumberFormat formatter = NumberFormat.getNumberInstance();     
  		        formatter.setMinimumIntegerDigits(16);     
	   	    	formatter.setGroupingUsed(false);     
   		        String tempLineCount= formatter.format(this.recordCount);   
	            String lastRecord="#"+tempLineCount;
	            buf.append(lastRecord);
			    PrintWriter pw = new PrintWriter(fos);
		        pw.write(buf.toString().toCharArray());
		        pw.flush();
		        fos.close();
		        pw.close();
		        
		        Map<String, Object> fileParam = new HashMap<String, Object>();
				fileParam.put("reportMonth", reportMonth);
				fileParam.put("reportBy", "ADV2");
				fileParam.put("fileName", fileName);
				fileParam.put("fileLocation", filePath+File.separator);
				fileParam.put("idLogin", "sysadmin");
				queryDAO.executeForMap("R_REV.MERGE_FILE_DETAILS", fileParam);
				tempJN=journalNo;
				fileCount += 1;
			}
	    }
		  return fileCount;	
	}
	//get sub1
	public void getSub1Str(String rMonth,String busUnit,String advanceAcc,String defCur,String journalDesc,
                           String uploadMonth,StringBuffer buf,String journalNo) throws Exception{
	 String account=advanceAcc;
	 String dept="";
	 String productCode="";
	 String rateType2="";
	 String affiliateCode="";
	 String project="";
	 String lineDesc="";
	 String amount="";
	 String projBusUnit=" ";
	 String co_Category="";
	 String co_SubCategory="";
	 String budgetLineStatus="";
	 String affiliateAbb="";
	 Map<String, Object> param = new HashMap<String, Object>();
	 param.put("reportMonth", rMonth);
	 param.put("journalNo", journalNo);
	 List<Map<String, Object>> subS1List = queryDAO.executeForMapList("R_REV.GET_SUB1_S1_REV07BL", param);
	 Integer index=0;
	 for(Map<String, Object> s1List : subS1List)
	 {
		 String affCode=(String)s1List.get("AFFILIATE_CODE");
		 String tempJDesc="";
		 if(journalDesc.length()>=5){
			 tempJDesc=journalDesc.substring(0,5);
		 }else 
		 {
			 //tempJDesc=this.setLength(journalDesc,5);
			 tempJDesc = journalDesc; //20170420 - Trim JournalDesc@wcbeh
		 }
		 if(affCode.equals("-"))
		 {
			 affiliateCode="";
		 }else 
		 {
			 affiliateCode=affCode;
			 Map<String, Object>tempparam = new HashMap<String, Object>();
			 tempparam.put("affiliateCode", affCode);
			 List<Map<String, Object>>  journalDescList=queryDAO.executeForMapList("R_REV.GET_SUB1_AK_REV07BL",tempparam);
			 for(Map<String, Object> descList : journalDescList){
				affiliateAbb=(String)descList.get("DESC_ABBR");
			 }
		 }
		//20170420 - Trim AffiliateAbb@wcbeh
		String tempAffiliateAbb = CommonUtils.isEmpty(affiliateAbb) ? "" : this.setLength(affiliateAbb,7)+" ";
		lineDesc="Reversal "+tempJDesc+" "+tempAffiliateAbb+"- "+this.formatDate(uploadMonth); 
		Double sumAmount=Math.abs(Double.parseDouble(s1List.get("SUMAMOUNT0").toString()));
		amount=sumAmount.toString();
		budgetLineStatus="";
		if(index==0){
			budgetLineStatus="N";
		}
		 String record2="L"+this.setLength(busUnit,5)+this.setLength("",9)+this.setLength("ACTUALS2",10)+this.setLength(account,10)
		        +this.setLength("",10)+this.setLength(dept,10)+this.setLength(productCode,8)+this.setLength(rateType2,6)+this.setLength("",5)
		        +this.setLength("",5)+this.setLength("",5)+this.setLength("",8)+this.setLength("",5)+this.setLength("",10)
		        +this.setLength("",10)+this.setLength("",10)+this.setLength("",10)+this.setLength(affiliateCode,10)
		        +this.setLength(project,15)+this.setLength("",4)+this.setLength("",8)+this.setLength("",10)+this.setLength("",3)
		        +this.setLength("",28)+this.setLength("",1)+this.setLength("",17)+this.setLength("",10)+this.setLength(lineDesc,30)
		        +this.setLength(defCur,3)+this.setLength("",5)+this.setLength(amount,28)+this.setLength("",17)+this.setLength(projBusUnit,5)
		        +this.setLength("",15)+this.setLength("",3)+this.setLength("",5)+this.setLength(co_Category,5)+this.setLength(co_SubCategory,5)
		        +this.setLength("",8)+this.setLength(budgetLineStatus,1)+this.setLength("",10)+this.setLength("",4)
		        +this.setLength("",1)+this.setLength("",30);
		 buf.append(record2+"\r\n");
         String record3="VG"+this.setLength("",6)+this.setLength("",1)+this.setLength("",20)+"NY"+this.setLength("",1)+this.setLength("",1)
		        +this.setLength("",3)+this.setLength("",8)+this.setLength("DT",4)+"T"+this.setLength("OS",8)+this.setLength("",4)
		        +this.setLength("",1)+this.setLength("",28)+this.setLength("",28)+this.setLength("",28)+this.setLength("",28)
		        +this.setLength("",9)+this.setLength("",1)+this.setLength("",6)+this.setLength("",7)+this.setLength("",7)
		        +this.setLength("",28)+this.setLength("",28)+this.setLength("",28)+this.setLength("",28)+this.setLength("",1)+this.setLength("",1)
		        +this.setLength("",1)+this.setLength("",1)+this.setLength("",1)+this.setLength("",3)+this.setLength("",28)+this.setLength("",28)
		        +this.setLength("",17)+this.setLength("",1)+this.setLength("",1)+this.setLength("",1)+this.setLength("",9);
         buf.append(record3+"\r\n");
     	 this.recordCount+=2;
		 index++;
	 }
}
	//get sub2
	public void getSub2Str(String rMonth,String revenueAcc,String busUnit,String defCur,
			               StringBuffer buf,String journalNo) throws Exception{
		 Map<String, Object> param = new HashMap<String, Object>();
		 param.put("reportMonth", rMonth);
		 param.put("journalNo", journalNo);
		 List<Map<String, Object>> subS2List = queryDAO.executeForMapList("R_REV.GET_SUB2_S2_REV07BL", param);
		 String account=revenueAcc;
		 String affiliateCode="";
		 String budgetLineStatus="";
		 for(Map<String, Object> s2List : subS2List)
		 {
			 String affCode=(String)s2List.get("AFFILIATE_CODE");
			 String plans=convertClobToString((Clob)s2List.get("PLAN_S"));
		     Double amount0=Double.parseDouble(s2List.get("AMOUNT0").toString());
		     String amount=amount0.toString();
		     if(affCode.equals("-"))
			 {
				 affiliateCode="";
			 }else 
			 {
				 affiliateCode=affCode;
			 }
			 //TODO(rMonth)
			 String lineDesc=this.formatDate2(rMonth)+"/"+plans; 
			 String dept=(String)s2List.get("DEPARTMENT");
			 String productCode=(String)s2List.get("PRODUCT_CODE");
			 String rateType2=(String)s2List.get("RATE_TYPE2");
			 String project=(String)s2List.get("JOB_NO");
			 String projBusUnit=busUnit;
			 String co_category=(String)s2List.get("CO_CATEGORY");
			 String co_sub_category=(String)s2List.get("CO_SUB_CATEGORY");
			 String  record2="L"+this.setLength(busUnit,5)+this.setLength("",9)+this.setLength("ACTUALS2",10)+this.setLength(account,10)
	             +this.setLength("",10)+this.setLength(dept,10)+this.setLength(productCode,8)+this.setLength(rateType2,6)+this.setLength("",5)
	             +this.setLength("",5)+this.setLength("",5)+this.setLength("",8)+this.setLength("",5)+this.setLength("",10)
	             +this.setLength("",10)+this.setLength("",10)+this.setLength("",10)+this.setLength(affiliateCode,10)
	             +this.setLength(project,15)+this.setLength("",4)+this.setLength("",8)+this.setLength("",10)+this.setLength("",3)
	             +this.setLength("",28)+this.setLength("",1)+this.setLength("",17)+this.setLength("",10)+this.setLength(lineDesc,30)
	             +this.setLength(defCur,3)+this.setLength("",5)+this.setLength(amount,28)+this.setLength("",17)+this.setLength(projBusUnit,5)
	             +this.setLength("",15)+this.setLength("",3)+this.setLength("",5)+this.setLength(co_category,5)+this.setLength(co_sub_category,5)
	             +this.setLength("",8)+this.setLength(budgetLineStatus,1)+this.setLength("",10)+this.setLength("",4)
	             +this.setLength("",1)+this.setLength("",30);
			 buf.append(record2+"\r\n");
             String record3="VG"+this.setLength("",6)+this.setLength("",1)+this.setLength("",20)+"NY"+this.setLength("",1)+this.setLength("",1)
	             +this.setLength("",3)+this.setLength("",8)+this.setLength("DT",4)+"T"+this.setLength("OS",8)+this.setLength("",4)
	             +this.setLength("",1)+this.setLength("",28)+this.setLength("",28)+this.setLength("",28)+this.setLength("",28)
	             +this.setLength("",9)+this.setLength("",1)+this.setLength("",6)+this.setLength("",7)+this.setLength("",7)
	             +this.setLength("",28)+this.setLength("",28)+this.setLength("",28)+this.setLength("",28)+this.setLength("",1)+this.setLength("",1)
	             +this.setLength("",1)+this.setLength("",1)+this.setLength("",1)+this.setLength("",3)+this.setLength("",28)+this.setLength("",28)
	             +this.setLength("",17)+this.setLength("",1)+this.setLength("",1)+this.setLength("",1)+this.setLength("",9);
             buf.append(record3+"\r\n");
         	this.recordCount+=2;
		 }
	}
	//get sysdate Year
	public String getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf =  new SimpleDateFormat("yy");
	    String str = sdf.format(cal.getTime()); 
		return  str;       
    }
	//get last day of some date
	public String getLastDay(String yearMonth) throws Exception{
		SimpleDateFormat insdf = new SimpleDateFormat("yyyyMM");
		Date date = insdf.parse(yearMonth);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
	    SimpleDateFormat sdf =   new SimpleDateFormat( "MMddyyyy" );
	    return sdf.format(calendar.getTime());
    }
	//get uploadMonth
	public String getUploadMonth(String yearMonth) throws Exception{
		 SimpleDateFormat insdf = new SimpleDateFormat("yyyyMM");
		Date tempdDate=insdf.parse(yearMonth);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tempdDate);
		calendar.add(Calendar.MONTH, 1);
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyyMM" );
        return sdf.format(calendar.getTime());
    }
	//repair space 
	public String setLength(String str,Integer length) throws Exception{
		if(str==null)
		{
			str="";
		}
		if(str.length()<length)
		{
			Integer tempLength=length-str.length();
			 for(int i=0;i<tempLength;i++){
				 str+=" ";
			 }
		}
       return str;
   }
	//date format
	private String formatDate(String date) throws Exception {
		SimpleDateFormat insdf = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat outsdf = new SimpleDateFormat("MMMyy", Locale.ENGLISH);
		Calendar c = Calendar.getInstance();
		c.setTime(insdf.parse(date));
		return outsdf.format(c.getTime());
	}
	
	//date format 2
	private String formatDate2(String date) throws Exception {
		SimpleDateFormat insdf = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat outsdf = new SimpleDateFormat("yyMM", Locale.ENGLISH);
		Calendar c = Calendar.getInstance();
		c.setTime(insdf.parse(date));
		return outsdf.format(c.getTime());
	}
	/**
	 * 
	 * @param clob
	 * @return
	 */
	public String convertClobToString(Clob clob) {
		String toRet = "";
		if(clob!=null) {
			try {
				long length=clob.length();
				toRet=clob.getSubString(1, (int)length);          
			} catch(Exception ex) {
				ex.printStackTrace();
			}      
		}      
		return toRet; 
	}
}
