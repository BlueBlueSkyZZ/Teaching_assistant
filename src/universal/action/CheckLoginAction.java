package universal.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class CheckLoginAction extends ActionSupport{
	/**
	 * ����Ƿ��Ѿ���½
	 */
	private static final long serialVersionUID = 1L;
	private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String check(){
		Map<String, Object> map = new HashMap<String, Object>();
		// ���session
		// �鿴�Ƿ����û���
		if(ServletActionContext.getRequest().getSession().getAttribute("studentID") !=null){
			String studentName = ServletActionContext.getRequest().getSession().getAttribute("studentName").toString();// ȡ����ȡ������
			map.put("result", studentName);
			map.put("identity", "student");
		}else if(ServletActionContext.getRequest().getSession().getAttribute("teacherID") !=null){
			String teacherName = ServletActionContext.getRequest().getSession().getAttribute("teacherName").toString();// ȡ����ȡ������
			map.put("result", teacherName);
			map.put("identity", "teacher");
		}else if(ServletActionContext.getRequest().getSession().getAttribute("managerID") !=null){
			String managerName = ServletActionContext.getRequest().getSession().getAttribute("managerName").toString();// ȡ����ȡ������
			map.put("result", managerName);
			map.put("identity", "manager");
		}else{
			map.put("result", "δ��¼");
			map.put("identity", "");
		}
		
		
		// ��һ��JAVA����������map���󣩣�ת��Ϊһ��JSON������Ҫimport net.sf.json.JSONArray��
		JSONObject json = JSONObject.fromObject(map);
		// ע�⣬�����resultΪString���ͣ�����Ϊ��
		// "{name:"Chris",age:5,position:"tt"}"
		result = json.toString();
		
		return SUCCESS;
	}
}
