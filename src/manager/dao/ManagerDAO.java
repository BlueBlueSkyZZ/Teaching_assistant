package manager.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import manager.entity.Classroom;
import manager.entity.Department;
import manager.entity.Major;
import manager.entity.Teacher;
import util.SqlHelper;

public class ManagerDAO {
	SqlHelper sqlhelper = new SqlHelper();
	
	/**
	 * ��ȡ���е�ѧԺ
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Department> getAllDepartment() throws Exception{
		String sql = "select * from T_department ORDER BY departmentID";
		
		ResultSet set = sqlhelper.executeQuery(sql);
		ArrayList<Department> departments = new ArrayList<Department>();
		
		while(set.next()) {
			Department department = new Department();
			String id = set.getString("departmentID");
			String name = set.getString("departmentName");
			department.setDepartmentID(id);
			department.setDepartmentName(name);
			departments.add(department);
		}
			
		return departments;
	}
	
	/**
	 * ��ȡ����ѧԺ
	 * @param departmentID ѧԺID
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Department> getSingleDepartment(String departmentID) throws Exception{
		String sql = "select * from T_department WHERE departmentID = " + departmentID;
		
		ResultSet set = sqlhelper.executeQuery(sql);
		ArrayList<Department> departments = new ArrayList<Department>();
		
		while(set.next()) {
			Department department = new Department();
			String id = set.getString("departmentID");
			String name = set.getString("departmentName");
			department.setDepartmentID(id);
			department.setDepartmentName(name);
			departments.add(department);
		}
			
		return departments;
	} 
	
	/**
	 * ���µ���ѧԺ
	 * @param departmentName
	 * @param departmentID
	 * @throws Exception
	 */
	public String updateSingleDepartment(String departmentName, String departmentID) {
		String sql = "UPDATE T_department SET departmentName = '"+ departmentName +"' WHERE departmentID = " + departmentID;
		
		int result = 0;
		try {
			result = sqlhelper.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return "FAILURE";
		}
		if(result > 0) {
			return "SUCCESS";
		} 
		return "FAILURE";
		
	} 
	
	/**
	 * ��ȡ����רҵ
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Major> getAllMajor() throws Exception{
		String sql = "select * from majorView ORDER BY majorID";
		
		ResultSet set = sqlhelper.executeQuery(sql);
		ArrayList<Major> majors = new ArrayList<Major>();
		
		while(set.next()) {
			Major major = new Major();
			String id = set.getString("majorID");
			String majorName = set.getString("majorName");
			String departmentName = set.getString("departmentName");
			String departmentID = set.getString("departmentID");
			major.setMajorID(id);
			major.setMajorName(majorName);
			major.setDepartmentName(departmentName);
			major.setDepartmentID(departmentID);
			majors.add(major);
		}
			
		return majors;
	}
	
	/**
	 * ��ȡ����רҵ��Ϣ
	 * @param majorID
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Major> getSingleMajor(String majorID) throws Exception{
		String sql = "select * from majorView WHERE majorID = " + majorID;
		
		ResultSet set = sqlhelper.executeQuery(sql);
		ArrayList<Major> majors = new ArrayList<Major>();
		
		while(set.next()) {
			Major major = new Major();
			
			String majorName = set.getString("majorName");
			String departmentID = set.getString("departmentID");
			String departmentName = set.getString("departmentName");
			major.setMajorID(majorID);
			major.setDepartmentID(departmentID);
			major.setDepartmentName(departmentName);
			major.setMajorName(majorName);
			
			majors.add(major);
		}
			
		return majors;
	} 

	/**
	 * ���µ���רҵ
	 * @param majorID
	 * @param majorName
	 * @param departmentID
	 * @return
	 */
	public String updateSingleMajor(String majorID, String majorName, String departmentID) {
		String sql = "UPDATE T_major SET majorName = '"+ majorName +"' , departmentID = " + departmentID 
				+ " WHERE majorID = " + majorID;
		
		int result = 0;
		try {
			result = sqlhelper.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return "FAILURE";
		}
		if(result > 0) {
			return "SUCCESS";
		} 
		return "FAILURE";
		
	}
	
	/**
	 * ��ȡ������ʦ
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Teacher> getAllTeacher() throws Exception{
		String sql = "select teacherID, teacherName, majorID from T_teacher";
		
		ResultSet set = sqlhelper.executeQuery(sql);
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		
		while(set.next()) {
			Teacher teacher = new Teacher();
			String teacherID = set.getString("teacherID");
			String teacherName = set.getString("teacherName");
			String majorID = set.getString("majorID");
			teacher.setTeacherID(teacherID);
			teacher.setTeacherName(teacherName);
			teacher.setMajorID(majorID);
			
			teachers.add(teacher);
		}
			
		return teachers;
	}
	
	/**
	 * ��ȡ������ʦ��Ϣ
	 * @param teacherID
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Teacher> getSingleTeacher(String teacherID) throws Exception{
		String sql = "select * from teacherView WHERE teacherID = " + teacherID;
		
		ResultSet set = sqlhelper.executeQuery(sql);
		
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		
		while(set.next()) {
			Teacher teacher = new Teacher();

			String teacherName = set.getString("teacherName");
			String majorID = set.getString("majorID");
			String majorName = set.getString("majorName");
			teacher.setTeacherID(teacherID);
			teacher.setTeacherName(teacherName);
			teacher.setMajorID(majorID);
			teacher.setMajorName(majorName);
			
			teachers.add(teacher);
		}
			
		return teachers;
	}
	
	/**
	 * ���µ�����ʦ
	 * @param teacherID
	 * @param teacherName
	 * @param majorID
	 * @return
	 */
	public String updateSingleTeacher(String teacherID, String teacherName, String majorID) {
		String sql = "UPDATE T_teacher SET teacherName = '"+ teacherName +"' , majorID = " + majorID 
				+ " WHERE teacherID = " + teacherID;
		
		int result = 0;
		try {
			result = sqlhelper.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return "FAILURE";
		}
		if(result > 0) {
			return "SUCCESS";
		} 
		return "FAILURE";
		
	}
	
	/**
	 * ��ȡ���н���
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Classroom> getAllClassroom() throws Exception{
		String sql = "SELECT classroomID, classroomName, status FROM T_classroom";
		
		ResultSet set = sqlhelper.executeQuery(sql);
		ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
		
		while(set.next()) {
			Classroom classroom = new Classroom();
			String classroomID = set.getString("classroomID");
			String classroomName = set.getString("classroomName");
			String status = set.getString("status");
			classroom.setClassroomID(classroomID);
			classroom.setClassroomName(classroomName);
			classroom.setStatus(status);
			
			classrooms.add(classroom);
		}
			
		return classrooms;
	}
	
	/**
	 * ��ȡ����������Ϣ
	 * @param classroomID
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Classroom> getSingleClassroom(String classroomID) throws Exception{
		String sql = "SELECT classroomID, classroomName, status FROM T_classroom WHERE classroomID =" + classroomID;
		
		ResultSet set = sqlhelper.executeQuery(sql);
		
		ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
		
		while(set.next()) {
			Classroom classroom = new Classroom();
			
			String classroomName = set.getString("classroomName");
			String status = set.getString("status");
			classroom.setClassroomID(classroomID);
			classroom.setClassroomName(classroomName);
			classroom.setStatus(status);
			
			classrooms.add(classroom);
		}
			
		return classrooms;
	}
	
}
