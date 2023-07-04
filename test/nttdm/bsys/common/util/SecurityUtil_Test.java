/**
 * @(#)SecurityUtil_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/10/09
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

/**
 * @author gplai
 *
 */
public class SecurityUtil_Test extends AbstractUtilTest {
    
    @Override
    protected void setUpData() throws Exception {
    }

    /**
     * MD5 Encryt
     * 
     * @throws Exception
     */
    public void testMd5() throws Exception{
        String result = SecurityUtil.md5("abcdef");
        assertEquals("e80b5017098950fc58aad83c8c14978e", result);
    }
    
    /**
     * AES Encrypt non-empty string.
     * 
     * security.key=billingsystemntts
     * 
     * @throws Exception
     */
    public void testAesEncrypt01() throws Exception{
        String result = SecurityUtil.aesEncrypt("abcdef");
        assertEquals("MTJDNEJFNEVDQTkyMDFGQkM2RTAxODdDMjk4ODVFODk=", result);
    }

    /**
     * AES Encrypt Empty String.
     * 
     * security.key=billingsystemntts
     *  
     * @throws Exception
     */
    public void testAesEncrypt02() throws Exception{
        String result = SecurityUtil.aesEncrypt("");
        assertEquals("Q0ZCRkJBQkQzRUQxQjgzNkYyMjAyODdGMjE0OUFGNUM=", result);
    }
    
    /**
     * AES Decrypt non-empty string.
     * 
     * security.key=billingsystemntts
     * 
     * @throws Exception
     */
    public void testAesDecrypt01() throws Exception{
        String result = SecurityUtil.aesDecrypt("MTJDNEJFNEVDQTkyMDFGQkM2RTAxODdDMjk4ODVFODk=");
        assertEquals("abcdef", result);
    }
    
    /**
     * AES Decrypt Empty String.
     * 
     * security.key=billingsystemntts
     *  
     * @throws Exception
     */
    public void testAesDecrypt02() throws Exception{
        String result = SecurityUtil.aesDecrypt("");
        assertEquals("", result);
    }
}
