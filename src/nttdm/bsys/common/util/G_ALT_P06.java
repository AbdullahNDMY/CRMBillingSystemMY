/**
 * @(#)G_ALT_P03.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.g_alt.AlertUserDto;
import nttdm.bsys.g_alt.G_ALT_P06Input;
import nttdm.bsys.g_alt.G_ALT_P06SqlInput;
import nttdm.bsys.g_alt.M_ALT_DDto;
import nttdm.bsys.g_alt.M_ALT_HDto;

/**
 * Send Notification(batch)
 */
public class G_ALT_P06 {
	/**
	 * <div>updateDAO</div>
	 */
	private UpdateDAO updateDAO = null;
	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO;
	private static final String SELECT_USER_TYPE = "SELECT.G_ALT_P06.001";
	private static final String SELECT_ID_USER = "SELECT.G_ALT_P06.002";
	private static final String SELECT_SEQ_ID_MSG = "SELECT.G_ALT_P06.006";
	private static final String SQL_GET_MAX_ID_DOC = "SELECT.G_ALT_P06.007";
	private static final String INSERT_M_ALT_D = "INSERT.G_ALT_P06.004";
	private static final String INSERT_M_ALT_H = "INSERT.G_ALT_P06.005";
    private static final String INSERT_T_DOC = "INSERT.G_ALT_P06.006";
    private static final String INSERT_M_ALT_ATC = "INSERT.G_ALT_P06.007";
    
	private static final String USER_TYPE_ROLE = "ROLE";
	private static final String USER_TYPE_DEP = "DEPARTMENT";
	private static final String MSG_TYPE = "NOTICE";
	private static final String STATUS = "1";
	private static final String REPEAT_MODE = "0";
	private static final String IS_NEW = "1";

	/**
	 * <div>Get UpdateDAO</div>
	 * 
	 * @return UpdateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * <div>Set UpdateDAO</div>
	 * 
	 * @param updateDAO
	 *            UpdateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * <div>Get QueryDAO</div>
	 * 
	 * @return QueryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * <div>Set QueryDAO</div>
	 * 
	 * @param queryDAO
	 *            QueryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * <div>Constructor</div>
	 * 
	 * @param queryDAO
	 *            QueryDAO
	 * @param updateDAO
	 *            UpdateDAO
	 */
	public G_ALT_P06(QueryDAO queryDAO, UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
		this.queryDAO = queryDAO;
	}

	/**
	 * <div>execute</div>
	 * 
	 * @param param
	 *            instance of G_ALT_P06Input
	 * @param uvo
	 *            instance of BillingSystemUserValueObject
	 * @throws Exception
	 */
	public void execute(G_ALT_P06Input param, BillingSystemUserValueObject uvo) {

		// Loop through $LIST_USER_ALERT
		for (int i = 0; i < param.getListAlertUser().size(); i++) {
			Object userAlert = param.getListAlertUser().get(i);
			AlertUserDto dto = null;
			if (userAlert.getClass().getName().equals("java.util.HashMap")) {
				dto = new AlertUserDto();
				// add null check 2011/08/11 for #18 #19 bug
				if (((Map<String, Object>) userAlert).get("ALERT_USER") == null) {
					continue;
				}
				dto.setAlertUser(((Map<String, Object>) userAlert).get("ALERT_USER").toString());
			} else {
				dto = (AlertUserDto) userAlert;
			}
			// 2.0: get $USER_TYPE
			G_ALT_P06SqlInput input = new G_ALT_P06SqlInput();
			input.setAlertUser(dto.getAlertUser());
			input.setBatchId(param.getBatchId());
			String userType = queryDAO.executeForObject(
					G_ALT_P06.SELECT_USER_TYPE, input, String.class);
			if (userType != null
					&& (userType.equals(G_ALT_P06.USER_TYPE_DEP) || userType
							.equals(G_ALT_P06.USER_TYPE_ROLE))) {
				// do 3.0
				// get ID_USER list
				input.setUserType(userType);
				List<String> listUser = new ArrayList<String>();
				listUser = queryDAO.executeForObjectList(
						G_ALT_P06.SELECT_ID_USER, input);
				// loop through LIST_USER
				for (int j = 0; j < listUser.size(); j++) {
					// do 4.0
					String user = (String) listUser.get(j);
					// send mail to user, insert M_ALT_H and M_ALT_D
					nextProcess(user, param, uvo);
				}
			} else {
				// do 7.0
				// send mail to user, insert M_ALT_H and M_ALT_D
				nextProcess(dto.getAlertUser(), param, uvo);
			}
		}
	}

	/**
	 * send notification (NOT send mail)
	 * 
	 * @param user
	 *            User ID
	 * @param param
	 *            G_ALT_P06Input
	 * @param uvo
	 *            BillingSystemUserValueObject
	 */
	private void nextProcess(String user, G_ALT_P06Input param,
			BillingSystemUserValueObject uvo) {
		// insert to M_ALT_H
		M_ALT_HDto altHDto = new M_ALT_HDto(G_ALT_P06.MSG_TYPE,
				G_ALT_P06.STATUS, G_ALT_P06.REPEAT_MODE, user,
				param.getSubject(), param.getMsg(), uvo.getId_user());
		String idMsg = insertMAltH(altHDto);
		// insert to M_ALT_D
		M_ALT_DDto altDDto = new M_ALT_DDto(user, idMsg, G_ALT_P06.IS_NEW);
		altDDto.setIdLogin(uvo.getId_user());
		insertMAltD(altDDto);
		
		// Insert alert notification attachment
		insertAttachment(param, uvo, idMsg);
	}

	/**
	 * Insert message to M_ALT_H.
	 * 
	 * @param dto
	 *            M_ALT_HDto
	 * @return message id
	 */
	private String insertMAltH(M_ALT_HDto dto) {
		String idMsg = queryDAO.executeForObject(G_ALT_P06.SELECT_SEQ_ID_MSG,
				null, String.class);
		dto.setIdMsg(idMsg);
		updateDAO.execute(G_ALT_P06.INSERT_M_ALT_H, dto);
		return idMsg;
	}

	/**
	 * INSERT message to M_ALT_D.
	 * 
	 * @param dto
	 *            M_ALT_DDto
	 */
	private void insertMAltD(M_ALT_DDto dto) {
		updateDAO.execute(G_ALT_P06.INSERT_M_ALT_D, dto);
	}
	
	/**
	 * Insert alert notification attachment if any. 
	 * @param input alert information
	 * @param uvo UserValueObject
	 * @param idMsg ID_MSG
	 */
    private void insertAttachment(G_ALT_P06Input input, BillingSystemUserValueObject uvo, String idMsg) {

        if ("".equals(CommonUtils.toString(input.getFilelocation()).trim())) {
            return;
        }

        // T_DOC.ID_DOC
        Long idDoc = queryDAO.executeForObject(SQL_GET_MAX_ID_DOC, null, Long.class);

        // INSERT INTO T_DOC
        Map<String, Object> docParam = new HashMap<String, Object>();
        docParam.put("ID_DOC", idDoc);
        docParam.put("DOC_TYPE", "ALTN");
        docParam.put("FILENAME", input.getFilename());
        docParam.put("FILELOCATION", input.getFilelocation());
        docParam.put("ID_LOGIN", uvo.getId_user());
        updateDAO.execute(INSERT_T_DOC, docParam);

        // INSERT INTO M_ALT_ATC
        Map<String, Object> atcParam = new HashMap<String, Object>();
        atcParam.put("ID_MSG", idMsg);
        atcParam.put("ID_DOC", idDoc);
        atcParam.put("ID_LOGIN", uvo.getId_user());
        updateDAO.execute(INSERT_M_ALT_ATC, atcParam);
    }
}
