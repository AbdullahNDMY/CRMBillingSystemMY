/**
 * @(#)G_CPM_P01.java
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.exception.SystemException;
import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.common.util.dto.G_CPM_P03Input;
import nttdm.bsys.common.bean.T_CUST_PLAN_LINK;
import nttdm.bsys.common.bean.T_CUST_PLAN_SVC;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;

/**
 * . Process to Approve Customer Plan
 * 
 * @author qinjinh
 * @version 1.0
 */
public class G_CPM_P03 {

    /**
     * . SELECT_SQL_01
     */
    private static final String SELECT_SQL_01 = "SELECT.BSYS.G_CPM_P03.SQL001";

    /**
     * . SELECT_SQL_02
     */
    private static final String SELECT_SQL_02 = "SELECT.BSYS.G_CPM_P03.SQL002";

    /**
     * . SELECT_SQL_03
     */
    private static final String SELECT_SQL_03 = "SELECT.BSYS.G_CPM_P03.SQL003";

    /**
     * . SELECT_SQL_04
     */
    private static final String SELECT_SQL_04 = "SELECT.BSYS.G_CPM_P03.SQL004";

    /**
     * . INSERT_SQL_01
     */
    private static final String INSERT_SQL_01 = "INSERT.BSYS.G_CPM_P03.SQL001";

    /**
     * . INSERT_SQL_02
     */
    private static final String INSERT_SQL_02 = "INSERT.BSYS.G_CPM_P03.SQL002";

    /**
     * . INSERT_SQL_03
     */
    private static final String INSERT_SQL_03 = "INSERT.BSYS.G_CPM_P03.SQL003";

    /**
     * . INSERT_SQL_04
     */
    private static final String INSERT_SQL_04 = "INSERT.BSYS.G_CPM_P03.SQL004";

    /**
     * . queryDAO
     */
    private QueryDAO queryDAO;

    /**
     * . updateDAO
     */
    private UpdateDAO updateDAO;

    /**
     * . updateDAONuked
     */
    private UpdateDAOiBatisNuked updateDAONuked;

    /**
     * . p
     */
    private long p;

    /**
     * <div>get P</div>
     * 
     * @return the p
     */
    public long getP() {

        return p;
    }

    /**
     * <div>set P</div>
     * 
     * @param p
     * the p to set
     */
    public void setP(final long p) {

        this.p = p;
    }

    /**
     * <div>get nDays</div>
     * 
     * @return the nDays
     */
    public long getnDays() {

        return nDays;
    }

    /**
     * <div>set nDays</div>
     * 
     * @param nDays
     * the nDays to set
     */
    public void setnDays(final long nDays) {

        this.nDays = nDays;
    }

    /**
     * <div>get nMonths</div>
     * 
     * @return the nMonths
     */
    public long getnMonths() {

        return nMonths;
    }

    /**
     * <div>set nMonths</div>
     * 
     * @param nMonths
     * the nMonths to set
     */
    public void setnMonths(final long nMonths) {

        this.nMonths = nMonths;
    }

    /**
     * . nDays
     */
    private long nDays;

    /**
     * . nMonths
     */
    private long nMonths;

    /**
     * @return the queryDAO
     */
    public QueryDAO getQueryDAO() {

        return queryDAO;
    }

    /**
     * <div>set QueryDAO</div>
     * 
     * @param queryDAO
     * the queryDAO to set
     */
    public void setQueryDAO(final QueryDAO queryDAO) {

        this.queryDAO = queryDAO;
    }

    /**
     * <div>get UpdateDAO</div>
     * 
     * @return the updateDAO
     */
    public UpdateDAO getUpdateDAO() {

        return updateDAO;
    }

    /**
     * <div>set UpdateDAO</div>
     * 
     * @param updateDAO
     * the updateDAO to set
     */
    public void setUpdateDAO(final UpdateDAO updateDAO) {

        this.updateDAO = updateDAO;
    }

    /**
     * <div>get UpdateDAONuked</div>
     * 
     * @return the updateDAONuked
     */
    public UpdateDAOiBatisNuked getUpdateDAONuked() {

        return updateDAONuked;
    }

    /**
     * <div>set UpdateDAONuked</div>
     * 
     * @param updateDAONuked
     * the updateDAONuked to set
     */
    public void setUpdateDAONuked(final UpdateDAOiBatisNuked updateDAONuked) {

        this.updateDAONuked = updateDAONuked;
    }

    /**
     * <div>G_CPM_P03 constructor</div>
     * 
     * @param queryDAO
     * QueryDAO
     * @param updateDAONuked
     * UpdateDAOiBatisNuked
     */
    public G_CPM_P03(final QueryDAO queryDAO,
        final UpdateDAOiBatisNuked updateDAONuked) {

        this.queryDAO = queryDAO;
        this.updateDAONuked = updateDAONuked;
    }

    /**
     * . execute
     * 
     * @param input
     * G_CPM_P03Input
     * @throws ParseException
     * ParseException
     * @throws SQLException
     * SQLException
     */
    public final void execute(final G_CPM_P03Input input)
        throws ParseException, SQLException {

        updateDAONuked.getSqlMapClient().endTransaction();
        updateDAONuked.getSqlMapClient().startTransaction();
        
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");

	    input.setSvcStart(dateFormat.format(dateFormat1.parse(input.getSvcStart())));
	    
        // 2.0 Get Credit note plan header and detail
        Map<String, Object> tCustPlanH =
            this.queryDAO.executeForMap(SELECT_SQL_01, input.getIdCustPlan());
        Map<String, Object> tCustPlanD =
            this.queryDAO.executeForMap(
                SELECT_SQL_02, input.getIdCustPlanGrp());
        if (!CollectionUtils.isEmpty(tCustPlanH)) {
            // 3.0 insert record into table(s) for Credit Note Header and Detail
            tCustPlanH.put("REFERENCE_PLAN", input.getIdCustPlan());
            tCustPlanH.put("ID_LOGIN", input.getIdLogin());
            // $ID_CUST_PLAN
            this.updateDAONuked.insert(INSERT_SQL_01, tCustPlanH);
            String idCustPlan = tCustPlanH.get("idCustPlan").toString();
            if (!CollectionUtils.isEmpty(tCustPlanD)) {
                tCustPlanD.put("ID_CUST_PLAN", idCustPlan);
                tCustPlanD.put("SVC_START", input.getSvcStart());
                tCustPlanD.put("SVC_END", input.getSvcEnd());
                tCustPlanD.put("ID_LOGIN", input.getIdLogin());
                this.updateDAONuked.insert(INSERT_SQL_02, tCustPlanD);
                
                G_CDM_P01 gCDMP01 = new G_CDM_P01(this.queryDAO, this.updateDAONuked, input.getIdLogin());
    			String idSubInfo;
    			try {
    				idSubInfo = gCDMP01.getGenerateCode(B_CPM_CONSTANT.SUBSCRIPTION_CODE, input.getIdLogin());
    			} catch (Exception e) {
    				e.printStackTrace();
    				throw new SystemException(e);
    			}
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("idCustPlan", idCustPlan);
                map.put("idSubInfo", idSubInfo);
                map.put("idLogin", input.getIdLogin());
                updateDAONuked.insert("INSERT.BSYS.G_CPM_P03.SQL303", map);
                
                String idCustPlanGrp =
                    tCustPlanD.get("idCustPlanGrp").toString();
                // 4.0 Get p,nDays,nMonths for calculate sub plan amount
                // 4.1
                String proRateBase =
                    tCustPlanD.get("PRO_RATE_BASE").toString();
                String proRateBaseNo =
                	CommonUtils.toString(tCustPlanD.get("PRO_RATE_BASE_NO"));
                input.setbProRateBase(proRateBase);
                input.setbProRateBaseNo(proRateBaseNo);
                getForCalAmount(input);
                // 5.0 Get Credit note plan sub plan information
                List<T_CUST_PLAN_LINK> tCustPlanLinkList =
                    this.queryDAO.executeForObjectList(
                        SELECT_SQL_03, input.getIdCustPlanGrp());
                // Loop result set C
                for (T_CUST_PLAN_LINK tCustPlanLink : tCustPlanLinkList) {
                    // C.RATE_MODE = 6 ?
                    if (!"6".equals(tCustPlanLink.getRate_mode())) {
                        // 5.1 Get mMode(no of months for rate mode)
                        // 5.1.1
                        int mMode =
                            Integer.parseInt(tCustPlanLink.getRate_mode());
                        float l =
                            Float.parseFloat(tCustPlanLink.getUnit_price());
                        float m =
                            Float.parseFloat(tCustPlanLink.getTotal_amount());
                        // 5.2 Calculate inAmt[0] (unit price),inAmt[1](total
                        // amount)
                        // 5.2.1
                        float[] inAmt =
                            getCalculateResult(l, m, getMode(mMode));
                        // 5.4 Get $idcustplanlink
                        // 6.0 Insert records into table(s) for Service
                        tCustPlanLink.setId_cust_plan_grp(idCustPlanGrp);
                        tCustPlanLink.setUnit_price(String.valueOf(inAmt[0]));
                        tCustPlanLink.setTotal_amount(String.valueOf(inAmt[1]));
                        tCustPlanLink.setId_login(input.getIdLogin());
                        this.updateDAONuked.insert(
                            INSERT_SQL_03, tCustPlanLink);
                        // 7.0 Get Credit note plan service item information
                        // $idcustplanlink=C.ID_CUST_PLAN_LINK
                        List<T_CUST_PLAN_SVC> tCustPlanLinkSvcList =
                            this.queryDAO.executeForObjectList(
                                SELECT_SQL_04,
                                tCustPlanLink.getId_cust_plan_link());
                        // Loop result set D
                        for (T_CUST_PLAN_SVC tCustPlanLinkSvc : tCustPlanLinkSvcList) {
                            String idCustPlanLink =
                                tCustPlanLink.getIdCustPlanLink();
                            // 8.0 Insert records into table(s) for Service Item
                            tCustPlanLinkSvc.setId_cust_plan_link(idCustPlanLink);
                            tCustPlanLinkSvc.setId_login(input.getIdLogin());
                            this.updateDAONuked.insert(
                                INSERT_SQL_04, tCustPlanLinkSvc);
                            // Have next record?
                        }
                        // End Loop result set D
                        // Have next record?
                    }
                    // No need generate subplan where rate mode=6(one time)
                }
                // End Loop result set C
            }
        }
        updateDAONuked.getSqlMapClient().commitTransaction();
        updateDAONuked.getSqlMapClient().endTransaction();
        // Commit
    }

    // Generate Credit Note Plan Sub method(4.1)
    /**
     * . Generate Credit Note Plan Sub method(4.1)
     * 
     * @param input
     * G_CPM_P03Input
     * @throws ParseException
     * ParseException
     */
    private void getForCalAmount(final G_CPM_P03Input input)
        throws ParseException {

        // p=1,nDays=1,nMonth=1
        this.p = 1;
        this.nDays = 1;
        this.nMonths = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date svcStrat = df.parse(input.getSvcStart());
        Date svcEnd = df.parse(input.getSvcEnd());
        // 4.1 Get p
        // B.PRO_RATE_BASE=U?
        if ("U".equals(input.getbProRateBase())) {
            // p=B.PRO_RATEBASE_NO
            this.p = Integer.parseInt(input.getbProRateBaseNo());
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(svcEnd);
            int end = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            this.p = end;
            // p=no. of days in Billto month
        }
        Calendar cal = Calendar.getInstance();
        // 4.2 Get nDays , nMonth
        // nFrom = $svc_start
        Date nFrom = svcStrat;
        Date nTo = svcStrat;
        // nTo<$svc_end?
        while (true) {
            // nTo=nFrom+1month
            cal.clear();
            cal.setTime(nFrom);
            cal.add(Calendar.MONTH, 1);
            nTo = cal.getTime();
            if (svcEnd.compareTo(nTo) <= 0) {
                break;
            }
            // nMonth=nMonth +1,nFrom=nTo+1 day
            this.nMonths = this.nMonths + 1;
            cal.clear();
            cal.setTime(nTo);
            cal.add(Calendar.DATE, 1);
            nFrom = cal.getTime();
        }
        // nDays=$svc_end - (nFrom-1 day)
        cal.clear();
        cal.setTime(nFrom);
        cal.add(Calendar.DATE, -1);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(svcEnd);
        this.nDays = dateSubtraction(cal, calEnd);
    }

    // Generate Credit Note Plan Sub method(5.1.1)
    /**
     * <div>get Mode</div>
     * 
     * @param cRateMode
     * int
     * @return mMode int
     */
    private int getMode(final int cRateMode) {

        int mMode = 1;
        switch (cRateMode) {
        // C.RATE_MODE=1?
        case 1:
            // mMode=12
            mMode = 12;
            break;
        // C.RATE_MODE=2?
        case 2:
            // mMode=6
            mMode = 6;
            break;
        // C.RATE_MODE=3?
        case 3:
            // mMode=3
            mMode = 3;
            break;
        // C.RATE_MODE=4?
        case 4:
            // mMode=2
            mMode = 2;
            break;
        default:
            // mMode=1
            mMode = 1;
            break;
        }
        return mMode;
    }

    // Generate Credit Note Plan Sub method(5.2.1)
    /**
     * <div>get Calculate Result</div>
     * 
     * @param l
     * int
     * @param m
     * int
     * @param mMode
     * int
     * @return inAmt int[]
     */
    private float[] getCalculateResult(final float l, final float m,
        final long mMode) {

        // inAmt[0] =C.UNIT_PRICE
        // inAmt[1] =C.TOTAL_AMOUNT
        float[] inAmt = {l , m};
        // nMonth=0?
        if (nMonths == 0) {
            // Pro rate nDays only
            // inAmt[0] = (inAmt[0]/mMode)/p*nDays
            inAmt[0] = (inAmt[0] / mMode) / p * nDays;
            // inAmt[1] = (inAmt[1]/mMode)/p*nDays
            inAmt[1] = (inAmt[1] / mMode) / p * nDays;
        } else {
            // Pro rate nMonth and nDays
            // Amt1=inAmt[0]/mMode
            float amt1 = inAmt[0] / mMode;
            // Amt2=Amt*nMonth
            float amt2 = amt1 * nMonths;
            // Amt3=(Amt/p)*nDays
            float amt3 = (amt1 / p) * nDays;
            // inAmt[0] = Amt2+Amt3
            inAmt[0] = amt2 + amt3;
            // Amt1=inAmt[1]/mMode
            amt1 = inAmt[1] / mMode;
            // Amt2=Amt*nMonth
            amt2 = amt1 * nMonths;
            // Amt3=(Amt/p)*nDays
            amt3 = (amt1 / p) * nDays;
            // inAmt[1] = Amt2+Amt3
            inAmt[1] = amt2 + amt3;
        }
        return inAmt;
    }

    /**
     * <div>get 2 dates result of Subtraction</div>
     * 
     * @param d1
     * Calendar
     * @param d2
     * Calendar
     * @return days
     */
    private int dateSubtraction(Calendar d1, Calendar d2) {

        if (d1.after(d2)) {
            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

}
