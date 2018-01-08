package student.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
import student.dao.DBDAO;
import student.entity.CourseInfo;

public class SearchCourseAction extends ActionSupport{
	/**
	 * ��ѯ�γ���Ϣ
	 */
	private static final long serialVersionUID = 1L;
	private String courseName;
	private String result;
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String search(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		DBDAO db = new DBDAO();
		//����ʵ���࣬��DAO�������ݲ���
		CourseInfo infoin = new CourseInfo();
		infoin.setCourseName(courseName);
		//DAO�Ĳ���Ϊʵ���࣬����ֵΪArrayList
		ArrayList<CourseInfo> infos = db.CourseInfo(infoin);
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
					result = json.toString();

					return SUCCESS;
	}
}
