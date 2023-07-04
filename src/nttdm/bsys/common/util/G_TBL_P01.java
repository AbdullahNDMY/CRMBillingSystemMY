/**
 * @(#)G_TBL_P01.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.ArrayList;

/**
 * Process to determine Table 
 * 
 * @author lixinj
 */
public class G_TBL_P01 {
	private static final String QCS_SCREEN = "QCS";
	private static final String QCS_TABLE_A = "T_QCS_H";
	private static final String QCS_PAGE1 = "Q_QCS_S01";
	private static final String QCS_PAGE2 = "Q_QCS_S02";

	private static final String BIF_SCREEN = "BIFCN";
	private static final String BIFCN_TABLE_A = "T_BIF_H";
	private static final String BIFCN_PAGE1 = "B_BIF_S01";
	private static final String BIFCN_PAGE2 = "B_BIF_S03";

	private static final String N_BIFCN_TABLE_A = "T_BIF_H";
	private static final String N_BIFCN_PAGE1 = "B_BIF_S01";
	private static final String N_BIFCN_PAGE2 = "B_BIF_S02";

	/**
	 * 
	 * @param idScreen
	 *            screen id
	 * @return ArrayList: 1st element is table name, 2nd element is page1 name,
	 *         3rd element is page2 name
	 */
	public static ArrayList<String> execute(String idScreen) {
		ArrayList<String> arr = new ArrayList<String>();
		if (idScreen != null && idScreen.equals(QCS_SCREEN)) {
			arr.add(QCS_TABLE_A);
			arr.add(QCS_PAGE1);
			arr.add(QCS_PAGE2);
		} else {
			if (idScreen != null && idScreen.equals(BIF_SCREEN)) {
				arr.add(BIFCN_TABLE_A);
				arr.add(BIFCN_PAGE1);
				arr.add(BIFCN_PAGE2);
			} else {
				arr.add(N_BIFCN_TABLE_A);
				arr.add(N_BIFCN_PAGE1);
				arr.add(N_BIFCN_PAGE2);
			}
		}
		return arr;
	}
}
