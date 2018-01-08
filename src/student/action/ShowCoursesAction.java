package student.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
import student.dao.DBDAO;
import student.entity.CourseInfo;

public class ShowCoursesAction extends ActionSupport{
	/**
	 * ��ʾ���пγ���Ϣ
	 */
	private static final long serialVersionUID = 1L;
	private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String show(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		DBDAO db = new DBDAO();
		//����ʵ���࣬��DAO�������ݲ���
		//DAO�Ĳ���Ϊʵ���࣬����ֵΪArrayList
		ArrayList<CourseInfo> infos = db.ShowAllCourses();
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
