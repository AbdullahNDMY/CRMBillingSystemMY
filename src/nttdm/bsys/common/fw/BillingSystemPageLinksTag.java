package nttdm.bsys.common.fw;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import jp.terasoluna.fw.util.PropertyUtil;
import jp.terasoluna.fw.web.struts.form.ActionFormUtil;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.taglib.TagUtils;

public class BillingSystemPageLinksTag extends TagSupport {

    /**
     * �V���A���o�[�W����ID
     */
    private static final long serialVersionUID = 9017738370826462823L;

    /**
     * ���O�N���X�B
     */
    private static Log log =
         LogFactory.getLog(BillingSystemPageLinksTag.class);

    /**
     * �o�͐�ύX�pID�B
     */
    protected String id = null;

    /**
     * �y�[�W�����N�������ɋN������A�N�V�������B
     */
    protected String action = null;

    /**
     * �\���J�n�C���f�b�N�X�ƑS������ێ�����Bean���B
     */
    protected String name = null;

    /**
     * �\���s���̃t�B�[���h���B
     */
    protected String rowProperty = null;

    /**
     * �\���J�n�C���f�b�N�X�̃t�B�[���h���B
     */
    protected String indexProperty = null;

    /**
     * �S�����̃t�B�[���h���B
     */
    protected String totalProperty = null;

    /**
     * �擾����Bean�̃X�R�[�v�B
     */
    protected String scope = null;

    /**
     * �T�u�~�b�g�t���O�B
     */
    protected boolean submit = false;

    /**
     * �t�H���[�h�t���O�B
     */
    protected boolean forward = false;

    /**
     * �C�x���g�p�����[�^�B
     */
    protected String event = DEFAULT_EVENT;

    /**
     * �w��͈̓C���f�b�N�X�o�̓t���O�B
     */
    protected boolean resetIndex = false;

    /**
     * ���݃y�[�W�ԍ��ۑ��p�p�����[�^�B
     */
    protected String currentPageIndex = CURRENT_PAGE_INDEX;

    /**
     * ���݃y�[�W�ԍ��ۑ��p�p�����[�^�B
     */
    protected String totalPageCount = TOTAL_PAGE_COUNT;

    /**
     * �ݒ肳��Ă���id�����l��ԋp����B
     * @return �ݒ肳��Ă���l
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * id�����ɒl��ݒ肷��B
     * @param id �ݒ肷��l
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * �ݒ肳��Ă���action�����l��ԋp����B
     * @return �ݒ肳��Ă���l
     */
    public String getAction() {
        return this.action;
    }

    /**
     * action�����ɒl��ݒ肷��B
     * @param action �ݒ肷��l
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * �ݒ肳��Ă���id�����l��ԋp����B
     * @return �ݒ肳��Ă���l
     */
    public String getName() {
        return name;
    }

    /**
     * name�����ɒl��ݒ肷��B
     * @param name �ݒ肷��l
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * �ݒ肳��Ă���rowProperty�����l��ԋp����B
     * @return �ݒ肳��Ă���l
     */
    public String getRowProperty() {
        return rowProperty;
    }

    /**
     * rowProperty�����ɒl��ݒ肷��B
     * @param rowProperty �ݒ肷��l
     */
    public void setRowProperty(String rowProperty) {
        this.rowProperty = rowProperty;
    }

    /**
     * �ݒ肳��Ă���indexProperty�����l��ԋp����B
     * @return �ݒ肳��Ă���l
     */
    public String getIndexProperty() {
        return indexProperty;
    }

    /**
     * indexProperty�����ɒl��ݒ肷��B
     * @param indexProperty �ݒ肷��l
     */
    public void setIndexProperty(String indexProperty) {
        this.indexProperty = indexProperty;
    }

    /**
     * �ݒ肳��Ă���totalProperty�����l��ԋp����B
     * @return �ݒ肳��Ă���l
     */
    public String getTotalProperty() {
        return totalProperty;
    }

    /**
     * totalProperty�����ɒl��ݒ肷��B
     * @param totalProperty �ݒ肷��l
     */
    public void setTotalProperty(String totalProperty) {
        this.totalProperty = totalProperty;
    }

    /**
     * �ݒ肳��Ă���scope�����l��ԋp����B
     * @return �ݒ肳��Ă���l
     */
    public String getScope() {
        return scope;
    }

    /**
     * scope�����ɒl��ݒ肷��B
     * @param scope �ݒ肷��l
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * �ݒ肳��Ă���submit�����l��ԋp����B
     * @return �ݒ肳��Ă���l
     */
    public boolean getSubmit() {
        return submit;
    }

    /**
     * submit�����ɒl��ݒ肷��B
     * @param submit �ݒ肷��l
     */
    public void setSubmit(boolean submit) {
        this.submit = submit;
    }

    /**
     * �ݒ肳��Ă���forward�����l��ԋp����B
     * @return �ݒ肳��Ă���l
     */
    public boolean getForward() {
        return forward;
    }

    /**
     * forward�����ɒl��ݒ肷��B
     * @param forward �ݒ肷��l
     */
    public void setForward(boolean forward) {
        this.forward = forward;
    }

    /**
     * �ݒ肳��Ă���event�����l��ԋp����B
     * @return �ݒ肳��Ă���l
     */
    public String getEvent() {
        return this.event;
    }

    /**
     * event�����ɒl��ݒ肷��B
     * @param event �ݒ肷��l
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * �ݒ肳��Ă���resetIndex�����l��ԋp����B
     * @return �ݒ肳��Ă���l
     */
    public boolean getResetIndex() {
        return resetIndex;
    }

    /**
     * resetIndex�����ɒl��ݒ肷��B
     * @param resetIndex �ݒ肷��l
     */
    public void setResetIndex(boolean resetIndex) {
        this.resetIndex = resetIndex;
    }

    /**
     * �ݒ肳��Ă���currentPageIndex�����l��ԋp����B
     * @return �ݒ肳��Ă���l
     */
    public String getCurrentPageIndex() {
        return this.currentPageIndex;
    }

    /**
     * currentPageIndex�����ɒl��ݒ肷��B
     * @param currentPageIndex �ݒ肷��l
     */
    public void setCurrentPageIndex(String currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }

    /**
     * �ݒ肳��Ă���totalPageCount�����l��ԋp����B
     * @return �ݒ肳��Ă���l
     */
    public String getTotalPageCount() {
        return this.totalPageCount;
    }

    /**
     * totalPageCount�����ɒl��ݒ肷��B
     * @param totalPageCount �ݒ肷��l
     */
    public void setTotalPageCount(String totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    /**
     * �A�N�V�����������K�{�̃G���[���b�Z�[�W�B
     */
    protected static String ERROR_MESSAGE_ACTION_REQUIRED =
    "Action attribute is required when submit attribute is \"false\".";

    /**
     * �擾�����\���s��(row)��0�ȉ��̏ꍇ�̃G���[���b�Z�[�W�B
     */
    protected static String WARN_MESSAGE_ILLEGAL_ROW =
    "Row param is illegal.";

    /**
     * <code>ApplicationResources</code> �t�@�C���ɂ�����
     * �y�[�W�W�����v�p�����N�̃v���p�e�B���̃v���t�B�b�N�X�B
     */
    protected static String PAGE_LINKS_PREFIX = "pageLinks.";

    /**
     * <code>ApplicationResources</code> �t�@�C���ɂ�����O��ւ�
     * �y�[�W�W�����v�p�����N�̃v���p�e�B���̍\���v�f�B
     */
    protected static String PREV_LINKS = "prev";

    /**
     * <code>ApplicationResources</code> �t�@�C���ɂ�������ւ�
     * �y�[�W�W�����v�p�����N�̃v���p�e�B���̍\���v�f�B
     */
    protected static String NEXT_LINKS = "next";

    /**
     * <code>ApplicationResources</code> �t�@�C���ɂ�����L���\����
     * �y�[�W�W�����v�p�����N�̃v���p�e�B���̍\���v�f�B
     */
    protected static String CHAR_LINKS = ".char";

    /**
     * <code>ApplicationResources</code> �t�@�C���ɂ�����
     * �y�[�W�W�����v�p�����N�̒��ڔԍ��w��̕\���ő吔��
     * �v���p�e�B���̍\���v�f�B
     */
    protected static String MAX_DSP_SIZE = "maxDirectLinkCount";

    /**
     * �y�[�W�����N�@�\�ŏo�͂���JavaScript�̏o�̓t���O
     */
    protected static String PAGELINKS_JAVASCRIPT_KEY
        = "pageLinksJavaScriptKey";

    /**
     * �t�H���[�h���B
     */
    protected static String FORWARD_NAME = "forward_pageLinks";

    /**
     * �f�t�H���g�C�x���g�p�����[�^�B
     */
    protected static String DEFAULT_EVENT = "event";

    /**
     * ���y�[�W�����y�[�W�R���e�L�X�g�ɓo�^����L�[�B
     */
    protected static String TOTAL_PAGE_COUNT = "totalPageCount";

    /**
     * ���݃y�[�W�����y�[�W�R���e�L�X�g�ɓo�^����L�[�B
     */
    protected static String CURRENT_PAGE_INDEX = "currentPageIndex";

    /**
     * �v���p�e�B�t�@�C���̃����N�o�^ID���L�[�Ƃ��ĕ\�������N���i�[����}�b�v�B
     */
    protected Map<String, String> links = new HashMap<String, String>();

    /**
     * �ő�y�[�W�W�����v���B
     */
    protected int maxLinkNo = 1;

    /**
     * �ő咼�ڎw�胊���N�ԍ����B
     */
    protected int maxPageCount = 10;

    /**
     * �^�O�]���J�n���ɌĂ΂�郁�\�b�h�B
     *
     * @return ��������w��
     * @throws JspException JSP��O
     */
    @Override
    public int doStartTag() throws JspException {

        //�����`�F�b�N
        if (!submit && (action == null || "".equals(action))) {
            log.error(ERROR_MESSAGE_ACTION_REQUIRED);
            throw new JspException(ERROR_MESSAGE_ACTION_REQUIRED);
        }

        // �v���p�e�B�t�@�C�����y�[�W�W�����v�p�����N�^�O���擾
        getLinkProperty();

        //�\���s�����擾
        Object objRow = lookup(pageContext, name, rowProperty, scope);
        int row = getInt(objRow);

        //�擾�����\���s����0�ȉ��̏ꍇ�͏������I������B
        if (row <= 0) {
            if (log.isWarnEnabled()) {
                log.warn(WARN_MESSAGE_ILLEGAL_ROW);
            }
            return EVAL_BODY_INCLUDE;
        }

        //�J�n�s�C���f�b�N�X���擾
        Object objIndex = lookup(pageContext, name, indexProperty, scope);
        int startIndex = getInt(objIndex);

        //�S�������擾
        Object objTotal = lookup(pageContext, name, totalProperty, scope);
        int totalCount = getInt(objTotal);

        //StringBuilder�̐���
        StringBuilder sb = new StringBuilder();

        //���݃y�[�W���A���y�[�W����ݒ肷��B
        attributePageCount(
                getPageIndex(row, startIndex), getPageCount(row, totalCount));

        if (submit) {
            //submit������true�̂Ƃ��́A�T�u�~�b�g���s���y�[�W�����N���o�͂���B

            //�\���s���A�J�n�C���f�b�N�X�̃^�O���o��
            defineHtml(row, startIndex, totalCount);

            //�O�y�[�W�����N�̐ݒ�
            addPrevSubmit(sb, row, startIndex, totalCount);

            //�y�[�W�ԍ������N�̐ݒ�
            addDirectSubmit(sb, row, startIndex, totalCount);

            //���y�[�W�����N�̐ݒ�
            addNextSubmit(sb, row, startIndex, totalCount);

        } else {
            //submit������false�̏ꍇ

            //�O�y�[�W�����N�̐ݒ�
            addPrevLink(sb, row, startIndex, totalCount);

            //�y�[�W�ԍ������N�̐ݒ�
            addDirectLink(sb, row, startIndex, totalCount);

            //���y�[�W�����N�̐ݒ�
            addNextLink(sb, row, startIndex, totalCount);
        }

        //StringBuilder�ɂ��߂����e���o�͂���B
        if (id == null || "".equals(id)) {
            try {
                JspWriter writer = pageContext.getOut();
                writer.println(sb.toString());
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new JspTagException(e.toString());
            }
        } else {
            pageContext.setAttribute(id, sb.toString());
        }

        return EVAL_BODY_INCLUDE;
    }

    /**
     * HTML�̒�`���o�͂���B
     * 
     * @param row �\���s��
     * @param startIndex �\���J�n�C���f�b�N�X
     * @param totalCount �S����
     * @throws JspException JSP��O
     */
    protected void defineHtml(int row, int startIndex, int totalCount)
        throws JspException {

        JspWriter writer = pageContext.getOut();
        try {

            //�������O��Hidden�^�O���o�͂��Ȃ����߁A�t���O���m���߂�B
            if (!getPageContextFlg(pageContext, PAGELINKS_JAVASCRIPT_KEY
                    + rowProperty)) {

                //�\��������Hidden�^�O��ǉ�
                writer.println("<input type=\"hidden\" name=\""
                        + rowProperty + "\" value=\"" + row + "\"/>");

                //���o�͂�����t���O�𗧂Ă�B
                setPageContextFlg(pageContext, PAGELINKS_JAVASCRIPT_KEY
                        + rowProperty);
            }

            //�������O��Hidden�^�O���o�͂��Ȃ����߁A�t���O���m���߂�B
            if (!getPageContextFlg(pageContext, PAGELINKS_JAVASCRIPT_KEY
                    + indexProperty)) {

                //�\���J�n�C���f�b�N�X��Hidden�^�O��ǉ�
                writer.println("<input type=\"hidden\" name=\""
                        + indexProperty + "\" value=\"" + startIndex + "\"/>");

                //���o�͂�����t���O�𗧂Ă�B
                setPageContextFlg(pageContext, PAGELINKS_JAVASCRIPT_KEY
                        + indexProperty);
            }

            //�������O��Hidden�^�O���o�͂��Ȃ����߁A�t���O���m���߂�B
            if (!getPageContextFlg(pageContext, PAGELINKS_JAVASCRIPT_KEY
                    + event) && forward) {

                //�\���J�n�C���f�b�N�X��Hidden�^�O��ǉ�
                writer.println("<input type=\"hidden\" name=\"" + event
                        + "\" value=\"\"/>");

                //���o�͂�����t���O�𗧂Ă�B
                setPageContextFlg(pageContext, PAGELINKS_JAVASCRIPT_KEY
                        + event);
            }

            //�������O��Hidden�^�O���o�͂��Ȃ����߁A�t���O���m���߂�B
            if (!getPageContextFlg(pageContext, PAGELINKS_JAVASCRIPT_KEY
                    + "resetIndex") && resetIndex) {

                //startIndex��Hidden�^�O��ǉ�
                if (!"startIndex".equals(indexProperty)) {
                    writer.println("<input type=\"hidden\" name=\"" +
                            "startIndex\" value=\"" + startIndex + "\"/>");
                }

                //endIndex��Hidden�^�O��ǉ�
                int endIndex = startIndex + row - 1;
                if (endIndex > totalCount) {
                    writer.println("<input type=\"hidden\" name=\"" +
                            "endIndex\" value=\"" + (totalCount - 1) + "\"/>");
                } else {
                    writer.println("<input type=\"hidden\" name=\"" +
                            "endIndex\" value=\"" + endIndex + "\"/>");
                }

                //���o�͂�����t���O�𗧂Ă�B
                setPageContextFlg(pageContext, PAGELINKS_JAVASCRIPT_KEY
                        + "resetIndex");
            }

            //�t�H�[�������擾
            String formName = ActionFormUtil
                    .getActionFormName((HttpServletRequest) pageContext
                            .getRequest());

            //�T�u�~�b�g���s��JavaScript��ǉ�����B
            //�������A�y�[�W�����N�^�O�������L�q����Ă���ꍇ�͂P��̂�
            if (!getPageContextFlg(pageContext, PAGELINKS_JAVASCRIPT_KEY)) {
                writer.println("<script type=\"text/javascript\">");
                writer.println("<!--");
                writer.println("  function pageLinkSubmit(rowObj, indexObj,"
                        + " row, startIndex){");
                writer.println("    rowObj.value = row;");
                writer.println("    indexObj.value = startIndex;");

                //forward������true�̏ꍇ�́Aevent������Hidden�^�O��
                //�p�����[�^��ݒ肷��B
                if (forward) {
                    writer.print("    document.");
                    writer.print(formName);
                    writer.print(".");
                    writer.print(event);
                    writer.print(".value = \"");
                    writer.print(FORWARD_NAME);
                    writer.println("\";");
                }
                if(CommonUtils.notEmpty(action)){
               	 writer.print("    document.");
                    writer.print(formName);
                    writer.println(".action=" + "\"" + action + "\";");
               }
                

                writer.print("    document.");
                writer.print(formName);
                writer.println(".submit();");
                writer.println("  }");
                writer.println("// -->");
                writer.println("</script>");

                //���o�͂�����t���O�𗧂Ă�B
                setPageContextFlg(pageContext, PAGELINKS_JAVASCRIPT_KEY);
            }

        } catch (IOException e) {
            log.error(e.getMessage());
            throw new JspTagException(e.toString());
        }
    }

    /**
     * �O�y�[�W�ɑJ�ڂ��郊���N�����StringBuilder�ɒǉ�����B
     * 
     * @param sb �ǉ��Ώۂ�StringBuilder
     * @param row �\���s��
     * @param startIndex �\���J�n�C���f�b�N�X
     * @param totalCount �ꗗ���̑S����
     */
    protected void addPrevSubmit(StringBuilder sb, int row, int startIndex,
            int totalCount) {

        //�t�H�[�������擾
        String formName = ActionFormUtil
                .getActionFormName((HttpServletRequest) pageContext
                        .getRequest());

        //�O�y�[�W�����N�̐���
        for (int i = maxLinkNo; i > 0; i--) {
            String linkKey = PREV_LINKS + i + CHAR_LINKS;
            String linkValue = links.get(linkKey);
            if (linkValue == null
                    || "".equals(linkValue)) {
                continue;
            }
            int index = startIndex - (i * row);
            if (index < 0) {
                sb.append("<span class=\"disabledPageLink\">"); 
                sb.append(linkValue);
            	sb.append("</span>");
            	sb.append("&nbsp;");
            } else {
                sb.append("<a class=\"defaultPreNextPageLink\" href=\"#\" onclick=\"pageLinkSubmit(");
                sb.append("document.");
                sb.append(formName);
                sb.append(".");
                sb.append(rowProperty);
                sb.append(",");
                sb.append("document.");
                sb.append(formName);
                sb.append(".");
                sb.append(indexProperty);
                sb.append(",");
                sb.append(row);
                sb.append(",");
                sb.append(index);
                sb.append(")\">");
                sb.append(linkValue);
                sb.append("</a>&nbsp;");
            }
        }
    }

    /**
     * �y�[�W�ԍ������N�����StringBuilder�ɒǉ�����B
     * 
     * @param sb �ǉ��Ώۂ�StringBuilder
     * @param row �\���s��
     * @param startIndex �\���J�n�C���f�b�N�X
     * @param totalCount �ꗗ���̑S����
     */
    protected void addDirectSubmit(StringBuilder sb, int row, int startIndex,
            int totalCount) {

        //�t�H�[�������擾
        String formName = ActionFormUtil
                .getActionFormName((HttpServletRequest) pageContext
                        .getRequest());

        //�y�[�W�W�����v�p�����N�̒��ڔԍ��w��̕\���ő吔���擾
        String directLinkNo = links.get(MAX_DSP_SIZE);
        if (directLinkNo != null) {
            try {
                maxPageCount = Integer.parseInt(directLinkNo);
            } catch (NumberFormatException e) {
                // NumberFormatException�����������ꍇ�A
                // ���̃v���p�e�B�͖�������maxDirectLinkCount�ɂ�
                // �f�t�H���g�l���g�p�����
            }
        }

        //�S�y�[�W�����擾����B
        int pageCount = getPageCount(row, totalCount);

        //���݂̃y�[�W�C���f�b�N�X���擾����B
        int pageIndex = getPageIndex(row, startIndex);

        //�\���ŏI�y�[�W����ѕ\���J�n�y�[�W
        int startPage = 0;
        int endPage = 0;

        //�S�y�[�W�����A�y�[�W�ԍ������N�̕\�������傫���A
        //���A�\������y�[�W�C���f�b�N�X���A�y�[�W�ԍ������N�̕\�����̔������
        //�傫���ꍇ�́A�\���J�n�y�[�W�C���f�b�N�X���A�\������y�[�W�C���f�b�N�X��
        //���킹�ĕϓ�������B
        //��Ƃ��āA�S�y�[�W���F�P�O�y�[�W�A�y�[�W�ԍ������N�̕\�����F�T�A
        //�\������y�[�W�C���f�b�N�X�F�T�̏ꍇ�AstartPage���Q�ƂȂ�
        //endPage���T�ƂȂ�B���̏ꍇ�A��ʂɕ\�����郊���N�́u3 4 5 6 7�v�ƂȂ�B
        if (pageCount > maxPageCount && pageIndex > (maxPageCount / 2)) {

            //�\���ŏI�y�[�W���ő�y�[�W���Ƃ���B
            endPage = maxPageCount;

            startPage = (pageIndex - (endPage / 2)) - 1;
            if (startPage + endPage > pageCount) {
                startPage = pageCount - endPage;
            }
        } else {
            endPage = pageCount < maxPageCount ? pageCount : maxPageCount;
            startPage = 0;
        }

        //�y�[�W�ԍ������N�̐������[�v
        int size = startPage + endPage;
        for (int i = startPage; i < size; i++) {
            int idx = i + 1;
            if (pageIndex == idx) {
                sb.append("<span class=\"currentPageLink\">");
                sb.append(idx);
                sb.append("</span>&nbsp;");
            } else {
                sb.append("<a class=\"defaultPageLink\" href=\"#\" onclick=\"pageLinkSubmit(");
                sb.append("document.");
                sb.append(formName);
                sb.append(".");
                sb.append(rowProperty);
                sb.append(",");
                sb.append("document.");
                sb.append(formName);
                sb.append(".");
                sb.append(indexProperty);
                sb.append(",");
                sb.append(row);
                sb.append(",");
                sb.append(i * row);
                sb.append(")\">");
                sb.append(idx);
                sb.append("</a>&nbsp;");
            }
        }
    }

    /**
     * ���y�[�W�ɑJ�ڂ��郊���N�����StringBuilder�ɒǉ�����B
     * 
     * @param sb �ǉ��Ώۂ�StringBuilder
     * @param row �\���s��
     * @param startIndex �\���J�n�C���f�b�N�X
     * @param totalCount �ꗗ���̑S����
     */
    protected void addNextSubmit(StringBuilder sb, int row, int startIndex,
            int totalCount) {

        //�t�H�[�������擾
        String formName = ActionFormUtil
                .getActionFormName((HttpServletRequest) pageContext
                        .getRequest());

        //���y�[�W�����N�̐���
        for (int i = 1; i <= maxLinkNo; i++) {
            String linkKey = NEXT_LINKS + i + CHAR_LINKS;
            String linkValue = links.get(linkKey);

            if (linkValue == null
                    || "".equals(linkValue)) {
                continue;
            }

            int index = startIndex + (i * row);
            if (index > (totalCount - 1)) {
                sb.append("<span class=\"disabledPageLink\">"); 
                sb.append(linkValue);
                sb.append("&nbsp;");
            	sb.append("</span>");
            } else {
                sb.append("<a class=\"defaultPreNextPageLink\" href=\"#\" onclick=\"pageLinkSubmit(");
                sb.append("document.");
                sb.append(formName);
                sb.append(".");
                sb.append(rowProperty);
                sb.append(",");
                sb.append("document.");
                sb.append(formName);
                sb.append(".");
                sb.append(indexProperty);
                sb.append(",");
                sb.append(row);
                sb.append(",");
                sb.append(index);
                sb.append(")\">");
                sb.append(linkValue);
                sb.append("</a>&nbsp;");
            }
        }
    }

    /**
     * �O�y�[�W�ɑJ�ڂ��郊���N�����StringBuilder�ɒǉ�����B
     * 
     * @param sb �ǉ��Ώۂ�StringBuilder
     * @param row �\���s��
     * @param startIndex �\���J�n�C���f�b�N�X
     * @param totalCount �ꗗ���̑S����
     */
    protected void addPrevLink(StringBuilder sb, int row, int startIndex,
            int totalCount) {

        //TagUtils�̃C���X�^���X����
        TagUtils tagUtils = TagUtils.getInstance();

        // ���X�|���X�p�����[�^�̎擾
        HttpServletResponse response
            = (HttpServletResponse) pageContext.getResponse();

        //�A�N�V����URL�̎擾
        String url = null;
        url = response.encodeURL(
                tagUtils.getActionMappingURL(action, pageContext));

        //�O�y�[�W�����N�̐���
        for (int i = maxLinkNo; i > 0; i--) {
            String linkKey = PREV_LINKS + i + CHAR_LINKS;
            String linkValue = links.get(linkKey);

            if (linkValue == null
                    || "".equals(linkValue)) {
                continue;
            }

            int index = startIndex - (i * row);
            if (index < 0) {
                sb.append("<span class='disabledPageLink'>"); 
                sb.append(linkValue);
            	sb.append("</span>");
            	sb.append("&nbsp;");
            } else {
                sb.append("<a class=\"defaultPreNextPageLink\" href=\"" + url);
                if (url.indexOf("?") < 0) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(rowProperty);
                sb.append("=");
                sb.append(row);
                sb.append("&");
                sb.append(indexProperty);
                sb.append("=");
                sb.append(index);
                sb.append("\">");
                sb.append(linkValue);
                sb.append("</a>&nbsp;");
            }
        }
    }

    /**
     * �y�[�W�ԍ������N�����StringBuilder�ɒǉ�����B
     * 
     * @param sb �ǉ��Ώۂ�StringBuilder
     * @param row �\���s��
     * @param startIndex �\���J�n�C���f�b�N�X
     * @param totalCount �ꗗ���̑S����
     */
    protected void addDirectLink(StringBuilder sb, int row, int startIndex,
            int totalCount) {

        //TagUtils�̃C���X�^���X����
        TagUtils tagUtils = TagUtils.getInstance();

        // ���X�|���X�p�����[�^�̎擾
        HttpServletResponse response
            = (HttpServletResponse) pageContext.getResponse();

        //�A�N�V����URL�̎擾
        String url = null;
        url = response.encodeURL(
                tagUtils.getActionMappingURL(action, pageContext));

        //�y�[�W�W�����v�p�����N�̒��ڔԍ��w��̕\���ő吔���擾
        String directLinkNo = links.get(MAX_DSP_SIZE);
        if (directLinkNo != null) {
            try {
                maxPageCount = Integer.parseInt(directLinkNo);
            } catch (NumberFormatException e) {
                // NumberFormatException�����������ꍇ�A
                // ���̃v���p�e�B�͖�������maxDirectLinkCount�ɂ�
                // �f�t�H���g�l���g�p�����
            }
        }

        //�S�y�[�W�����擾����B
        int pageCount = getPageCount(row, totalCount);

        //���݂̃y�[�W�C���f�b�N�X���擾����B
        int pageIndex = getPageIndex(row, startIndex);

        //�\���ŏI�y�[�W����ѕ\���J�n�y�[�W
        int startPage = 0;
        int endPage = 0;

        //�S�y�[�W�����A�y�[�W�ԍ������N�̕\�������傫���A
        //���A�\������y�[�W�C���f�b�N�X���A�y�[�W�ԍ������N�̕\�����̔������
        //�傫���ꍇ�́A�\���J�n�y�[�W�C���f�b�N�X���A�\������y�[�W�C���f�b�N�X��
        //���킹�ĕϓ�������B
        //��Ƃ��āA�S�y�[�W���F�P�O�y�[�W�A�y�[�W�ԍ������N�̕\�����F�T�A
        //�\������y�[�W�C���f�b�N�X�F�T�̏ꍇ�AstartPage���Q�ƂȂ�
        //endPage���T�ƂȂ�B���̏ꍇ�A��ʂɕ\�����郊���N�́u3 4 5 6 7�v�ƂȂ�B
        if (pageCount > maxPageCount && pageIndex > (maxPageCount / 2)) {

            //�\���ŏI�y�[�W���ő�y�[�W���Ƃ���B
            endPage = maxPageCount;

            startPage = (pageIndex - (endPage / 2)) - 1;
            if (startPage + endPage > pageCount) {
                startPage = pageCount - endPage;
            }
        } else {
            endPage = pageCount < maxPageCount ? pageCount : maxPageCount;
            startPage = 0;
        }

        //�y�[�W�ԍ������N�̐������[�v
        int size = startPage + endPage;
        for (int i = startPage; i < size; i++) {
            int idx = i + 1;
            if (pageIndex == idx) {
                sb.append("<span class=\"currentPageLink\">");
                sb.append(idx);
                sb.append("</span>&nbsp;");
            } else {
                // �y�[�W�W�����v�p�����N�^�O���Z�b�g
                sb.append("<a class=\"defaultPageLink\" href=\"" + url);
                if (url.indexOf("?") < 0) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(rowProperty);
                sb.append("=");
                sb.append(row);
                sb.append("&");
                sb.append(indexProperty);
                sb.append("=");
                sb.append(i * row);
                sb.append("\">");
                sb.append(idx);
                sb.append("</a>&nbsp;");
            }
        }
    }

    /**
     * ���y�[�W�ɑJ�ڂ��郊���N�����StringBuilder�ɒǉ�����B
     * 
     * @param sb �ǉ��Ώۂ�StringBuilder
     * @param row �\���s��
     * @param startIndex �\���J�n�C���f�b�N�X
     * @param totalCount �ꗗ���̑S����
     */
    protected void addNextLink(StringBuilder sb, int row, int startIndex,
            int totalCount) {

        //TagUtils�̃C���X�^���X����
        TagUtils tagUtils = TagUtils.getInstance();

        // ���X�|���X�p�����[�^�̎擾
        HttpServletResponse response
            = (HttpServletResponse) pageContext.getResponse();

        //�A�N�V����URL�̎擾
        String url = null;
        url = response.encodeURL(
                tagUtils.getActionMappingURL(action, pageContext));

        //���y�[�W�����N�̐���
        for (int i = 1; i <= maxLinkNo; i++) {
            String linkKey = NEXT_LINKS + i + CHAR_LINKS;
            String linkValue = links.get(linkKey);

            if (linkValue == null
                    || "".equals(linkValue)) {
                continue;
            }

            int index = startIndex + (i * row);
            if (index > (totalCount - 1)) {
            	 sb.append("<span class=\"disabledPageLink\">"); 
                 sb.append(linkValue);
                 sb.append("&nbsp;");
             	sb.append("</span>");
            } else {

                sb.append("<a class=\"defaultPreNextPageLink\" href=\"" + url);
                if (url.indexOf("?") < 0) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(rowProperty);
                sb.append("=");
                sb.append(row);
                sb.append("&");
                sb.append(indexProperty);
                sb.append("=");
                sb.append(index);
                sb.append("\">");
                sb.append(linkValue);
                sb.append("</a>&nbsp;");
            }
        }
    }

    /**
     * �v���p�e�B�t�@�C���ɒ�`����Ă���y�[�W�W�����v�p�����N��`���擾����B
     * �擾�����N���X�ϐ��Ɋi�[����B
     */
    private void getLinkProperty() {
        // �v���p�e�B�t�@�C���ɒ�`����Ă��郊���N�\����o�^����
        Enumeration enume
            = PropertyUtil.getPropertyNames(PAGE_LINKS_PREFIX);
        while (enume.hasMoreElements()) {
            String propName = (String) enume.nextElement();
            String id = propName.substring(PAGE_LINKS_PREFIX.length());
            String link = PropertyUtil.getProperty(propName);

            // �ő�y�[�W�W�����v����ݒ�
            if ((id != null)
                    && (id.startsWith(PREV_LINKS)
                    || id.startsWith(NEXT_LINKS))) {
                String strLinkNo = id.substring(4, id.lastIndexOf(CHAR_LINKS));
                int intLinkNo = 0;
                try {
                    intLinkNo = Integer.parseInt(strLinkNo);
                } catch (NumberFormatException e) {
                    // �������Ȃ�
                    continue;
                }
                if (intLinkNo > maxLinkNo) {
                    maxLinkNo = intLinkNo;
                }
                links.put(id, link);
            } else if (MAX_DSP_SIZE.equals(id)) {
                links.put(id, link);
            }
        }
    }

    /**
     * �\���y�[�W�ԍ����Z�o���ĕԋp����B
     * 
     * @param row �\���s��
     * @param startIndex ���ݕ\������Ă���y�[�W�̕\���J�n�C���f�b�N�X
     * @return �Z�o�����\���y�[�W�ԍ�
     */
    protected int getPageIndex(int row, int startIndex) {

        //�\���y�[�W�ԍ��̎Z�o
        int pageIndex = 0;
        if (row > 0) {
            pageIndex = startIndex / row + 1;
        } else {
            pageIndex = 0;
        }
        if (row > 0 && startIndex % row > 0) {
            pageIndex++;
        }

        return pageIndex;

    }

    /**
     * �y�[�W�����Z�o���ĕԋp����B
     * 
     * @param row �\���s��
     * @param totalCount �S����
     * @return �Z�o�����y�[�W��
     */
    protected int getPageCount(int row, int totalCount) {

        //�\���y�[�W�ԍ��̎Z�o
        int pageCount = 0;
        if (row > 0) {
            pageCount = totalCount / row;
            if (totalCount % row > 0) {
                pageCount++;
            }
        } else {
            pageCount = 1;
        }

        return pageCount;

    }

    /**
     * �w�肳�ꂽKEY�ɂĎ擾�����l��^�U�l�ɕϊ����ĕԋp����B
     * �Ȃ��A��key��null�̏ꍇ�́AIllegalArgumentException����������B
     * 
     * @param pageContext �y�[�W�R���e�L�X�g
     * @param key FLG���擾����KEY
     * @return �w�肳�ꂽKEY�ɂĎ擾�����o�͏�ԃt���O
     */
    protected boolean getPageContextFlg(
            PageContext pageContext, String key) {
        //�y�[�W�R���e�L�X�g����t���O���擾����B
        Object obj = pageContext.getAttribute(key);
        Boolean bol = new Boolean(false);
        if (obj != null && obj instanceof Boolean) {
           bol = (Boolean) obj;
        }
        return bol.booleanValue();
    }

    /**
     * �y�[�W�R���e�L�X�g�ɑ΂��āA�w�肳�ꂽKEY�̃t���O��ݒ肷��B
     * �Ȃ��A��key��null�̏ꍇ�́AIllegalArgumentException����������B
     * 
     * @param pageContext �y�[�W�R���e�L�X�g
     * @param key FLG��ݒ肷��KEY
     */
    protected void setPageContextFlg(
            PageContext pageContext, String key) {
        //�y�[�W�R���e�L�X�g�Ƀt���O�𗧂Ă�B
        pageContext.setAttribute(key, Boolean.valueOf(true));
    }

    /**
     * name���w�肳��ĂȂ��ꍇ�́Aproperty�̒l�𒼐ڎ擾����B
     * 
     * @param pageContext �y�[�W�R���e�L�X�g
     * @param name �v���p�e�B��ێ�����Bean��
     * @param property �v���p�e�B
     * @param scope �X�R�[�v
     * @return �擾�����l
     * @throws JspException JSP��O
     */
    protected Object lookup(PageContext pageContext, String name, 
            String property, String scope) throws JspException{
        if (property == null || "".equals(property)) {
            return null;
        }
        Object retObj = null;
        if (name != null && !"".equals(name)) {
            retObj = TagUtils.getInstance().lookup(pageContext, name,
                    property, scope);
        } else {
            retObj = TagUtils.getInstance().lookup(pageContext,
                    property, scope);
        }
        return retObj;
    }

    /**
     * ��̃I�u�W�F�N�g��int�ɕϊ����ĕԋp����B
     * 
     * @param obj int�ɕϊ�����I�u�W�F�N�g 
     * @return �擾�����l
     * @throws JspException JSP��O
     */
    protected int getInt(Object obj) throws JspException{
        int retInt = 0;
        String value = ObjectUtils.toString(obj);
        if (!"".equals(value)) {
            try {
                retInt = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                log.error(e.getMessage());
                throw new JspException(e);
            }
        }
        return retInt;
    }

    /**
     * ���݃y�[�W���A���y�[�W�����y�[�W�R���e�L�X�g�ɕۑ�����B
     * 
     * @param now ���݃y�[�W��
     * @param total ���y�[�W��
     */
    protected void attributePageCount(
            int now, int total) {

        if (total <= 0) {
            now = 0;
        }

        //���݃y�[�W�����y�[�W�R���e�L�X�g�ɕۑ�����B
        if (currentPageIndex != null && !"".equals(currentPageIndex)) {
            pageContext.setAttribute(currentPageIndex, now);
        } else {
            pageContext.setAttribute(CURRENT_PAGE_INDEX , now);
        }

        //���y�[�W�����y�[�W�R���e�L�X�g�ɕۑ�����B
        if (totalPageCount != null && !"".equals(totalPageCount)) {
            pageContext.setAttribute(totalPageCount, total);
        } else {
            pageContext.setAttribute(TOTAL_PAGE_COUNT, total);
        }
    }

    /**
     * ���ׂẴA���P�[�g���ꂽ����������B
     */
    @Override
    public void release() {
        super.release();
        id = null;
        action = null;
        name = null;
        rowProperty = null;
        indexProperty = null;
        totalProperty = null;
        scope = null;
        submit = false;
        forward = false;
        event = DEFAULT_EVENT;
        resetIndex = false;
        currentPageIndex = CURRENT_PAGE_INDEX;
        totalPageCount = TOTAL_PAGE_COUNT;
    }
}
