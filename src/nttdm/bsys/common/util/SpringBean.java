package nttdm.bsys.common.util;

import javax.servlet.ServletContext;

import jp.terasoluna.fw.web.codelist.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringBean {
	private ServletContext context;
	private WebApplicationContext applicationContext;
	public SpringBean(ServletContext context){
		this.context = context;
		this.applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.context);

	}
	public Object get(String beanName){
		return applicationContext.getBean(beanName);
	}
	
	public CodeBean[] getDefinedCodeList(String beanName){
		CodeBean[] result = null;
		CodeListLoader codeList = (CodeListLoader)this.get(beanName);
		if(codeList != null){
			result = codeList.getCodeBeans();
		}
		return result;
	}
}
