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
	 * 显示所有课程信息
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
		//构建实体类，向DAO函数传递参数
		//DAO的参数为实体类，返回值为ArrayList
		ArrayList<CourseInfo> infos = db.ShowAllCourses();
		ArrayList<CourseInfo> list = new ArrayList<CourseInfo>();
		// 获取课程信息
					if (infos != null && infos.size() > 0) {
						map.put("size", infos.size());
						for(int  i = 0 ; i < infos.size() ; i ++){
							CourseInfo info = infos.get(i);
							list.add(info);
						}
						map.put("result", list);
					} else {
						map.put("result", "课程错误或不存在");
					}

					// 将一个JAVA对象（这里是map对象），转化为一个JSON对象。需要import net.sf.json.JSONArray。
					JSONObject json = JSONObject.fromObject(map);
					// 注意，这里的result为String类型，内容为：
					// "{name:"Chris",age:5,position:"tt"}"
					result = json.toString();

					return SUCCESS;
	}
}
