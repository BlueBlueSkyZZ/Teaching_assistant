package student.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
import student.dao.DBDAO;
import student.entity.Student;
import student.entity.CourseInfo;

public class CourseTableAction extends ActionSupport{
	/**
	 * �鿴ѧ���α��
	 */
	private static final long serialVersionUID = 1L;
	private String studentID;
	private String result;
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String table(){
		/*Map<String, Object> map = new HashMap<String, Object>();
		
		DBDAO db = new DBDAO();
		//����ʵ���࣬��DAO�������ݲ���
		Student studentin = new Student();
		studentin.setStudentID(studentID);
		//DAO�Ĳ���Ϊʵ���࣬����ֵΪArrayList
		ArrayList<CourseInfo> infos = db.CourseTable(studentin);
		ArrayList<CourseInfo> list = new ArrayList<CourseInfo>();
		// ��ȡ�γ���Ϣ
					if (infos != null && infos.size() > 0) {
						map.put("size", infos.size());
						for(int  i = 0 ; i < infos.size() ; i ++){
							CourseInfo info = infos.get(i);
							list.add(info);
						}
						map.put("result", list);
					} else {
						map.put("result", "�γ̴���򲻴���");
					}

					// ��һ��JAVA����������map���󣩣�ת��Ϊһ��JSON������Ҫimport net.sf.json.JSONArray��
					JSONObject json = JSONObject.fromObject(map);
					// ע�⣬�����resultΪString���ͣ�����Ϊ��
					// "{name:"Chris",age:5,position:"tt"}"
					result = json.toString();*/

					return SUCCESS;
		
	}

}
