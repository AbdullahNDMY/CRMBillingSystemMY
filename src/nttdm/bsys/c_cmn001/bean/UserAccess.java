package nttdm.bsys.c_cmn001.bean;

import java.io.Serializable;

public class UserAccess implements Serializable{
/**
 * 
 */
private static final long serialVersionUID = 7932791309312877258L;

private String id_user;
private String id_module;
private String id_sub_module;
private String access_type;

public String getId_user() {
	return id_user;
}

public void setId_user(String id_user) {
	this.id_user = id_user;
}

public String getId_module() {
	return id_module;
}

public void setId_module(String id_module) {
	this.id_module = id_module;
}

public String getId_sub_module() {
	return id_sub_module;
}

public void setId_sub_module(String id_sub_module) {
	this.id_sub_module = id_sub_module;
}

public String getAccess_type() {
	return access_type;
}

public void setAccess_type(String access_type) {
	this.access_type = access_type;
}

public static UserAccess findAccessFunction(java.util.List<UserAccess> user_access_list, String id_module, String id_sub_module)
{
	if(user_access_list==null || user_access_list.size()==0)
	{
		return null;
	}
	for(int i=0;i<user_access_list.size();i++)
	{
		UserAccess obj=user_access_list.get(i);
		if(obj.getId_module().equals(id_module) && obj.getId_sub_module().equals(id_sub_module))
		{
			return user_access_list.get(i);
		}
	}
	return null;	
}

}
