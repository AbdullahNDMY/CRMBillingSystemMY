/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SSM
 * SERVICE NAME   : M_SSM_S02
 * FUNCTION       : M_SSMS02_02BLogic
 * FILE NAME      : M_SSMS02_02BLogic.java
 * 
 * Copyright © 2014 NTTDATA Corporation
 *
 * History
 * 
 * 
 **********************************************************/
package nttdm.bsys.m_gbs.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_gbs.bean.ResultItem;

import org.apache.struts.Globals;

/**
 * M_SSMS02_02BLogic<br>
 * <ul>
 * <li>BusinessLogic class.
 * </ul>
 * <br>
 * 
 * @author NTTData Vietnam
 * @version 1.0
 */
public class M_GBSS02_02BLogic implements BLogic<Map<String, Object>> {

    /**
     * queryDAO
     */
    protected QueryDAO queryDAO;

    /**
     * updateDAO
     */
    protected UpdateDAO updateDAO;

    /**
     * updateDAONuked
     */
    private UpdateDAOiBatisNuked updateDAONuked;
    
    /**
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(Map<String, Object> input) {

        BLogicResult result = new BLogicResult();

        BillingSystemUserValueObject uvo = (BillingSystemUserValueObject) input.get("uvo");

        BLogicMessages messages = new BLogicMessages();
        BLogicMessages errors = new BLogicMessages();

        String resultString = "success";

        Map<String, Object> output = new HashMap<String, Object>();

        // column name
        String precolumnType = CommonUtils.toString(input.get("preSelItem"));

        @SuppressWarnings("unchecked")
        List<ResultItem> listResult = (List<ResultItem>) input.get("listResult");

        Iterator<ResultItem> listIterator = listResult.listIterator();
        List<String> values = new ArrayList<String>();
        List<String> taxcodevalues = new ArrayList<String>();
        Map<String, String> userModeMap = new HashMap<String, String>();
        // check start
        while (listIterator.hasNext()) {
            ResultItem item = listIterator.next();
            if ("TC".equals(precolumnType)) {
                String taxcode=item.getTaxCode();
                String taxrate=item.getTaxRate();
                String accountcode=item.getAccountCode();
                String taxdesr1=item.getTaxDesr1();
                String taxdesr2=item.getTaxDesr2();
                
                // TaxCode Mandantory checking 
                if (CommonUtils.isEmpty(taxcode)) {
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "Tax Code"));
                    break;
                }
                
                // TaxCode maxlength=10 
                if (taxcode.trim().length() > 10) {
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Tax Code", 10 }));
                    break;
                }
                
                // Tax Code validate Duplicate
                if (taxcodevalues.contains(taxcode.trim())) {
                    
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC006", taxcode.trim()));
                    resultString = "faild";
                    break;
                } else {
                    taxcodevalues.add(taxcode.trim());
                }
                
                // TaxRate Mandantory checking 
                if (CommonUtils.isEmpty(taxrate)) {
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "Tax Rate"));
                    break;
                }
                
                // Input integer only
                if (isNotNumberic(taxrate)) {
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC011", "Tax Rate"));
                    break;
                }
                
                // TaxRate maxlength=99 
                if (Integer.parseInt(taxrate, 10) > 99) {
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC041", new Object[] { "Tax Rate", 99 }));
                    break;
                }
                
                //ACC_CODE Mandantory checking 
                if (CommonUtils.isEmpty(accountcode)) {
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "Account Code"));
                    break;
                }
                
                //ACC_CODE maxlength=15
                if (accountcode.trim().length() > 15) {
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Account Code", 15 }));
                    break;
                }
                
                // TaxDescription1 Mandantory checking 
                if (CommonUtils.isEmpty(taxdesr1)) {
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "Description"));
                    break;
                }
                
                // Description maxlength=30 
                if (taxdesr1.trim().length() > 30) {
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Description", 30 }));
                    break;
                }
                
                // Description2 maxlength=300 
                if (taxdesr2.trim().length() > 300) {
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Description2", 300 }));
                    break;
                }
                
            } else if ("CG".equals(precolumnType) || "EU".equals(precolumnType) || "RT".equals(precolumnType) 
            		|| "CC".equals(precolumnType) || "CS".equals(precolumnType) || "CT".equals(precolumnType) || "PR".equals(precolumnType)
            		|| "JN".equals(precolumnType)){
                String code = item.getItemCode();
                String name = item.getItemName();
                
                if (CommonUtils.isEmpty(code)) {
                    // validate is null
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "Code"));
                    break;
                }
                
                if (CommonUtils.isEmpty(name)) {
                    // validate is null
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "Description"));
                    break;
                }
                
                if("RT".equals(precolumnType)){
                	String endUser = item.getEndUser();
                	String modeType = item.getModeType();
                	if (CommonUtils.isEmpty(endUser)) {
                        // validate is null
                        errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "End User"));
                        break;
                    }
                	if (CommonUtils.isEmpty(modeType)) {
                        // validate is null
                        errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "Mode"));
                        break;
                    }
                	if(userModeMap.containsKey(endUser.trim() + modeType.trim())){
                		// validate Duplicate
                        errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC105", 
                        		"Invalid input : " + 
                        		userModeMap.get(endUser.trim() + modeType.trim()) + "," + code.trim() 
                        		+ " same end user and mode."));
                        resultString = "faild";
                        break;
                	}else {
						userModeMap.put(endUser.trim() + modeType.trim(), code.trim());
					}
                }
                
                if("PR".equals(precolumnType)){
                	String category = item.getCategory();
                   if (CommonUtils.isEmpty(category)) {
                        // category is null
                        errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "category"));
                         break;
                       }
                }
                if("CG".equals(precolumnType)){
                	String abbreviation = item.getAbbreviation();
                   if (CommonUtils.isEmpty(abbreviation)) {
                        // category is null
                        errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "abbreviation"));
                         break;
                       }
                }
                if (code.trim().length() > 10) {
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Code", 10 }));
                    break;
                }

                if (values.contains(code.trim())) {
                    // validate Duplicate
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC006", code.trim()));
                    resultString = "faild";
                    break;
                }
                if (name.trim().length() > 30) {
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Description", 30 }));
                    break;
                } else {
                    values.add(code.trim());
                }
            }else {
                String name = item.getItemName();

                if (CommonUtils.isEmpty(name)) {
                    // validate is null
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "Value"));
                    break;
                }

                if (name.trim().length() > 100) {
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Value", 100 }));
                    break;
                }

                if (values.contains(name.trim())) {
                    // validate Duplicate
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC006", name.trim()));
                    resultString = "faild";
                    break;
                } else {
                    values.add(name.trim());
                }
            }
        }
        // check end
        if (errors.isEmpty() && "CA".equals(precolumnType)) {
            // Carrier
            List<ResultItem> dbList = queryDAO.executeForObjectList("M_GBS.getCarriers", null);

            for (int i = 0; i < listResult.size(); i++) {
                ResultItem item = listResult.get(i);
                String itemId = item.getItemId();
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("0");

                if (CommonUtils.isEmpty(itemId)) {
                    // insert
                    Integer iditem= this.updateDAONuked.insert("M_GBS.insertCarriers", item);
                    item.setItemId(iditem.intValue()+"");
                    item.setIsUsed("N");
                } else {
                    // update
                    updateDAO.execute("M_GBS.updateCarriers", item);

                    // remove
                    dbList.remove(item);
                }
            }

            // delete
            for (int i = 0; i < dbList.size(); i++) {
                ResultItem item = dbList.get(i);
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("1");
                updateDAO.execute("M_GBS.updateCarriers", item);
            }

        } 
        //Add by MiffyAn start
        else if (errors.isEmpty() && "CC".equals(precolumnType)) {
            // M_CO_CATEGORY
            List<ResultItem> dbList = queryDAO.executeForObjectList("M_GBS.getComplayCategory", null);
            for (int i = 0; i < listResult.size(); i++) {
                ResultItem item = listResult.get(i);
                String itemId = item.getItemId();
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("0");
                if (CommonUtils.isEmpty(itemId)) {
                    // insert
                    Integer iditem= this.updateDAONuked.insert("M_GBS.insertComplayCategory", item);
                    item.setItemId(iditem.intValue()+"");
                } else {
                    // update
                    updateDAO.execute("M_GBS.updateComplayCategory", item);
                    // remove
                    dbList.remove(item);
                }
                Integer a = queryDAO.executeForObject("M_GBS.getCCUsedCount", item.getItemCode(), Integer.class); 
                if (a== 0) {
                    item.setIsUsed("N");
                }else {
                	item.setIsUsed("");
                }
            }
            // delete
            for (int i = 0; i < dbList.size(); i++) {
                ResultItem item = dbList.get(i);
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("1");
                updateDAO.execute("M_GBS.updateComplayCategory", item);
            }
        }else if (errors.isEmpty() && "CS".equals(precolumnType)) {
            // M_CO_SUBCATEGORY
            List<ResultItem> dbList = queryDAO.executeForObjectList("M_GBS.getComplaySubCategory", null);
            for (int i = 0; i < listResult.size(); i++) {
                ResultItem item = listResult.get(i);
                String itemId = item.getItemId();
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("0");
                if (CommonUtils.isEmpty(itemId)) {
                    // insert
                    Integer iditem= this.updateDAONuked.insert("M_GBS.insertComplaySubCategory", item);
                    item.setItemId(iditem.intValue()+"");
                } else {
                    // update
                    updateDAO.execute("M_GBS.updateComplaySubCategory", item);
                    // remove
                    dbList.remove(item);
                }
                Integer a = queryDAO.executeForObject("M_GBS.getCSUsedCount", item.getItemCode(), Integer.class); 
                if (a== 0) {
                    item.setIsUsed("N");
                }else {
                	item.setIsUsed("");
                }
            }
            // delete
            for (int i = 0; i < dbList.size(); i++) {
                ResultItem item = dbList.get(i);
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("1");
                updateDAO.execute("M_GBS.updateComplaySubCategory", item);
            }
        } else if (errors.isEmpty() && "CT".equals(precolumnType)) {
            // M_CO_TYPE
            List<ResultItem> dbList = queryDAO.executeForObjectList("M_GBS.getComplayType", null);
            for (int i = 0; i < listResult.size(); i++) {
                ResultItem item = listResult.get(i);
                String itemId = item.getItemId();
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("0");

                if (CommonUtils.isEmpty(itemId)) {
                    // insert
                    Integer iditem= this.updateDAONuked.insert("M_GBS.insertComplayType", item);
                    item.setItemId(iditem.intValue()+"");
                } else {
                    // update
                    updateDAO.execute("M_GBS.updateComplayType", item);
                    // remove
                    dbList.remove(item);
                }
                Integer a = queryDAO.executeForObject("M_GBS.getCTUsedCount", item.getItemCode(), Integer.class); 
                if (a== 0) {
                    item.setIsUsed("N");
                }else {
                	item.setIsUsed("");
                }
            }
            // delete
            for (int i = 0; i < dbList.size(); i++) {
                ResultItem item = dbList.get(i);
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("1");
                updateDAO.execute("M_GBS.updateComplayType", item);
            }
        } else if (errors.isEmpty() && "PR".equals(precolumnType)) {
            //M_PRODUCT
            List<ResultItem> dbList = queryDAO.executeForObjectList("M_GBS.getComplayPrpduct", null);
            for (int i = 0; i < listResult.size(); i++) {
                ResultItem item = listResult.get(i);
                String itemId = item.getItemId();
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("0");

                if (CommonUtils.isEmpty(itemId)) {
                    // insert
                    Integer iditem= this.updateDAONuked.insert("M_GBS.insertComplayProduct", item);
                    item.setItemId(iditem.intValue()+"");
                } else {
                    // update
                    updateDAO.execute("M_GBS.updateComplayProduct", item);
                    // remove
                    dbList.remove(item);
                }
                Integer a = queryDAO.executeForObject("M_GBS.getProductUsedCount", item.getItemCode(), Integer.class); 
                if (a== 0) {
                    item.setIsUsed("N");
                }else {
                	item.setIsUsed("");
                }
            }
            // delete
            for (int i = 0; i < dbList.size(); i++) {
                ResultItem item = dbList.get(i);
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("1");
                updateDAO.execute("M_GBS.updateComplayProduct", item);
            }
        }else if (errors.isEmpty() && "JN".equals(precolumnType)) {
            // M_CO_TYPE
            List<ResultItem> dbList = queryDAO.executeForObjectList("M_GBS.getJournalNo", null);
            for (int i = 0; i < listResult.size(); i++) {
                ResultItem item = listResult.get(i);
                String itemId = item.getItemId();
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("0");

                if (CommonUtils.isEmpty(itemId)) {
                    // insert
                    Integer iditem= this.updateDAONuked.insert("M_GBS.insertJournalNo", item);
                    item.setItemId(iditem.intValue()+"");
                } else {
                    // update
                    updateDAO.execute("M_GBS.updateJournalNo", item);
                    // remove
                    dbList.remove(item);
                }
                Integer a = queryDAO.executeForObject("M_GBS.getJNUsedCount", item.getItemCode(), Integer.class); 
                if (a== 0) {
                    item.setIsUsed("N");
                }else {
                	item.setIsUsed("");
                }
            }
            // delete
            for (int i = 0; i < dbList.size(); i++) {
                ResultItem item = dbList.get(i);
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("1");
                updateDAO.execute("M_GBS.updateJournalNo", item);
            }
        }
        //Add by MiffyAn end 
        else if (errors.isEmpty() && "CG".equals(precolumnType)) {
            // Location
            List<ResultItem> dbList = queryDAO.executeForObjectList("M_GBS.getCustomerGroup", null);

            for (int i = 0; i < listResult.size(); i++) {
                ResultItem item = listResult.get(i);
                String itemId = item.getItemId();
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("0");

                if (CommonUtils.isEmpty(itemId)) {
                    // insert
                    Integer iditem= this.updateDAONuked.insert("M_GBS.insertCustomerGroup", item);
                    item.setItemId(iditem.intValue()+"");
                } else {
                    // update
                    updateDAO.execute("M_GBS.updateCustomerGroup", item);
                    
                    // remove
                    dbList.remove(item);
                }
                //
                Integer c = queryDAO.executeForObject("M_GBS.getCust", item.getItemCode(), Integer.class);
                Integer d = queryDAO.executeForObject("M_GBS.getCom", item.getItemCode(), Integer.class);
                if (c + d == 0) {
                    item.setIsUsed("N");
                }else {
                	item.setIsUsed("");
                	}
            }

            // delete
            for (int i = 0; i < dbList.size(); i++) {
                ResultItem item = dbList.get(i);
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("1");
                updateDAO.execute("M_GBS.updateCustomerGroup", item);
            }

        } else if (errors.isEmpty() && "EU".equals(precolumnType)) {
            // End User
            List<ResultItem> dbList = queryDAO.executeForObjectList("M_GBS.getEndUser", null);
            
            for (int i = 0; i < listResult.size(); i++) {
                ResultItem item = listResult.get(i);
                String itemId = item.getItemId();
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("0");

                if (CommonUtils.isEmpty(itemId)) {
                    // insert
                    Integer iditem= this.updateDAONuked.insert("M_GBS.insertEndUser", item);
                    item.setItemId(iditem.intValue()+"");
                } else {
                    // update
                    updateDAO.execute("M_GBS.updateEndUser", item);
                    
                    // remove
                    dbList.remove(item);
                }
                //
                Integer c = queryDAO.executeForObject("M_GBS.getEndUserCount", item.getItemCode(), Integer.class);
                Integer d = queryDAO.executeForObject("M_GBS.getRTEndUserCount", item.getItemCode(), Integer.class);
                if (c + d == 0) {
                    item.setIsUsed("N");
                }else {
                	item.setIsUsed("");
                	}
            }

            // delete
            for (int i = 0; i < dbList.size(); i++) {
                ResultItem item = dbList.get(i);
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("1");
                updateDAO.execute("M_GBS.updateEndUser", item);
            }

        } else if (errors.isEmpty() && "LO".equals(precolumnType)) {
            // Location
            List<ResultItem> dbList = queryDAO.executeForObjectList("M_GBS.getEquipLocations", null);

            for (int i = 0; i < listResult.size(); i++) {
                ResultItem item = listResult.get(i);
                String itemId = item.getItemId();
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("0");

                if (CommonUtils.isEmpty(itemId)) {
                    // insert
                    Integer iditem= this.updateDAONuked.insert("M_GBS.insertEquipLocations", item);
                    item.setItemId(iditem.intValue()+"");
                    item.setIsUsed("N");
                } else {
                    // update
                    updateDAO.execute("M_GBS.updateEquipLocations", item);

                    // remove
                    dbList.remove(item);
                }
            }

            // delete
            for (int i = 0; i < dbList.size(); i++) {
                ResultItem item = dbList.get(i);
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("1");
                updateDAO.execute("M_GBS.updateEquipLocations", item);
            }

        } else if (errors.isEmpty() && "ML".equals(precolumnType)) {
        	// wcbeh@20160920 - Master Location
            // Location
            List<ResultItem> dbList = queryDAO.executeForObjectList("M_GBS.getMasterLocation", null);

            for (int i = 0; i < listResult.size(); i++) {
                ResultItem item = listResult.get(i);
                String itemId = item.getItemId();
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("0");

                if (CommonUtils.isEmpty(itemId)) {
                    // insert
                    Integer iditem= this.updateDAONuked.insert("M_GBS.insertMasterLocation", item);
                    item.setItemId(iditem.intValue()+"");
                    item.setIsUsed("N");
                } else {
                    // update
                    updateDAO.execute("M_GBS.updateMasterLocation", item);

                    // remove
                    dbList.remove(item);
                }
            }

            // delete
            for (int i = 0; i < dbList.size(); i++) {
                ResultItem item = dbList.get(i);
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("1");
                updateDAO.execute("M_GBS.updateMasterLocation", item);
            }

        } else if (errors.isEmpty() && "FT".equals(precolumnType)) {
            // Firewall Type
            List<ResultItem> dbList = queryDAO.executeForObjectList("M_GBS.getFirewallTypes", null);

            for (int i = 0; i < listResult.size(); i++) {
                ResultItem item = listResult.get(i);
                String itemId = item.getItemId();
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("0");

                if (CommonUtils.isEmpty(itemId)) {
                    // insert
                    Integer iditem= this.updateDAONuked.insert("M_GBS.insertFirewallTypes", item);
                    item.setItemId(iditem.intValue()+"");
                    item.setIsUsed("N");
                } else {
                    // update
                    updateDAO.execute("M_GBS.updateFirewallTypes", item);

                    // remove
                    dbList.remove(item);
                }
            }

            // delete
            for (int i = 0; i < dbList.size(); i++) {
                ResultItem item = dbList.get(i);
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("1");
                updateDAO.execute("M_GBS.updateFirewallTypes", item);
            }

        } else if (errors.isEmpty() && "FM".equals(precolumnType)) {
            // Firewall Model
            List<ResultItem> dbList = queryDAO.executeForObjectList("M_GBS.getFirewallModels", null);

            for (int i = 0; i < listResult.size(); i++) {
                ResultItem item = listResult.get(i);
                String itemId = item.getItemId();
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("0");

                if (CommonUtils.isEmpty(itemId)) {
                    // insert
                    Integer iditem= this.updateDAONuked.insert("M_GBS.insertFirewallModels", item);
                    item.setItemId(iditem.intValue()+"");
                    item.setIsUsed("N");
                } else {
                    // update
                    updateDAO.execute("M_GBS.updateFirewallModels", item);

                    // remove
                    dbList.remove(item);
                }
            }

            // delete
            for (int i = 0; i < dbList.size(); i++) {
                ResultItem item = dbList.get(i);
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("1");
                updateDAO.execute("M_GBS.updateFirewallModels", item);
            }
        } else if (errors.isEmpty() && "RT".equals(precolumnType)) {
            // Location
            List<ResultItem> dbList = queryDAO.executeForObjectList("M_GBS.getRateType", null);

            for (int i = 0; i < listResult.size(); i++) {
                ResultItem item = listResult.get(i);
                String itemId = item.getItemId();
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("0");

                if (CommonUtils.isEmpty(itemId)) {
                    // insert
                    Integer iditem= this.updateDAONuked.insert("M_GBS.insertRateType", item);
                    item.setItemId(iditem.intValue()+"");
                } else {
                    // update
                    updateDAO.execute("M_GBS.updateRateType", item);
                    
                    // remove
                    dbList.remove(item);
                }
                //
                Integer c = queryDAO.executeForObject("M_GBS.getCustPlanLink", item.getItemCode(), Integer.class);
                if (c == 0) {
                    item.setIsUsed("N");
                }else {
                	item.setIsUsed("");
                	}
            }

            // delete
            for (int i = 0; i < dbList.size(); i++) {
                ResultItem item = dbList.get(i);
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("1");
                updateDAO.execute("M_GBS.updateRateType", item);
            }

        }else if (errors.isEmpty() && "TC".equals(precolumnType)) {
            //Tax Code
            List<String> dbList = queryDAO.executeForObjectList("M_GBS.selectTaxId", null);
            
            for (int i = 0; i < listResult.size(); i++) {
                ResultItem item = listResult.get(i);
                String itemId = item.getTaxId();
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("0");
                if (CommonUtils.isEmpty(itemId)) {
                    // insert
                    Integer idtax= this.updateDAONuked.insert("M_GBS.insertTaxCode", item);
                    item.setTaxId(idtax.intValue()+"");
                    item.setIsTaxUsed("N");
                } else {
                    // update
                    updateDAO.execute("M_GBS.updateTaxCode", item);

                    // remove
                    dbList.remove(itemId);
                }
            }
            // delete
            for (int i = 0; i < dbList.size(); i++) {
                String taxid= dbList.get(i);
                ResultItem item=new ResultItem();
                item.setTaxId(taxid);
                item.setIdLogin(uvo.getId_user());
                item.setIsDeleted("1");
                updateDAO.execute("M_GBS.deleteTaxCode", item);
            }
        }

        if (errors.isEmpty()) {
            messages.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC003"));
        }

        output.put("columnType", precolumnType);
        output.put("listResult", listResult);
        result.setResultString(resultString);
        result.setErrors(errors);
        result.setMessages(messages);
        result.setResultObject(output);
        return result;
    }

    /**
     * check value is numeric
     * @param mbean
     * @return
     */
    private boolean isNotNumberic(String taxrate){
        try {
            Integer.parseInt(taxrate, 10);
        } catch (Exception ex) {
            return true;
        }
        return false;
    }
    
    /**
     * queryDAO を取得する
     * 
     * @return queryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /**
     * queryDAO を設定する
     * 
     * @param queryDAO
     *            queryDAO
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
     * updateDAO を取得する
     * 
     * @return updateDAO
     */
    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }

    /**
     * updateDAO を設定する
     * 
     * @param updateDAO
     *            updateDAO
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }
    
    /**
     * updateDAONuked を取得する
     * 
     * @return updateDAONuked
     */
    public UpdateDAOiBatisNuked getUpdateDAONuked() {
        return updateDAONuked;
    }

    /**
     * updateDAONuked を設定する
     * 
     * @param updateDAONuked
     *            updateDAONuked
     */
    public void setUpdateDAONuked(UpdateDAOiBatisNuked updateDAONuked) {
        this.updateDAONuked = updateDAONuked;
    }
}
