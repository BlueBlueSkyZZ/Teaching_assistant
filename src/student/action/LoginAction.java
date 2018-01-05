package student.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
import student.dao.DBDAO;
import student.entity.Student;

public class LoginAction extends ActionSupport{
	/**
	 * ѧ����¼
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String result;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String login(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		DBDAO db = new DBDAO();
		//����ʵ���࣬��DAO�������ݲ���
		Student student = new Student();
		student.setStudentID(username);
		student.setPassword(password);
		//DAO�Ĳ���Ϊʵ���࣬����ֵΪArrayList
		ArrayList<Student> students = db.checkStudent(student);
		// ����Ƿ�������û���
					if (students != null && students.size() > 0) {
						Student stu = students.get(0);
						// �������
						if (password.equals(stu.getPassword())) {
							// ����ͳ��action��ֵһ���������name,gender,position��struts����ǰ�˴��κ��ʼ������action��ֱ��ʹ�þͿ����ˡ�
							map.put("result", "�ɹ�");

							// ����session�����ֵ�¼״̬
							ServletActionContext.getRequest().getSession().setAttribute("studentID", stu.getStudentID());// �����û���
							ServletActionContext.getRequest().getSession().setAttribute("studentName",stu.getStudentName());// ��������
						} else {
							map.put("result", "�����������");
						}
					} else {
						map.put("result", "�˺Ŵ���򲻴���");
					}

					// ��һ��JAVA����������map���󣩣�ת��Ϊһ��JSON������Ҫimport net.sf.json.JSONArray��
					JSONObject json = JSONObject.fromObject(map);
					// ע�⣬�����resultΪString���ͣ�����Ϊ��
					// "{name:"Chris",age:5,position:"tt"}"
					result = json.toString();

					return SUCCESS;
	}
}
