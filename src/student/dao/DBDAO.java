package student.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;

import student.entity.Student;
import student.entity.CourseInfo;
import util.DBUtil;
import util.SqlHelper;


public class DBDAO {
	SqlHelper sqlhelper = new SqlHelper();
	
	//��¼����û�������
	public ArrayList<Student> checkStudent(Student student){
		String sql = "select * from T_student where studentID=? and password=?";
		
		String[] parameters={student.getStudentID(),student.getPassword()};
		//System.out.println(parameters);
		ArrayList<Object> list = sqlhelper.executeQuery(sql, parameters);
		
		//���ﷵ�ص�Arraylist��Object��ģ�������Ҫ����ת����Student��
		ArrayList<Student> students = new ArrayList<Student>();
		
		for (int i = 0; i < list.size(); i++) {
			Student stu = new Student();
			Object[] obj = (Object[])list.get(i);
			stu.setStudentID(obj[0].toString());
			stu.setStudentName(obj[1].toString());
			stu.setMajorID(obj[2].toString());
			stu.setPassword(obj[3].toString());
			students.add(stu);
		}
		
		return students;
		
	}
	
	//��ȡ�γ̴�����Ϣ
	public ArrayList<CourseInfo> CourseInfo(CourseInfo infoin){
		String sql = "select courseID,courseName,teacherName from courseinfo where courseName=?";
		
		String[] parameters={infoin.getCourseName()};
		//System.out.println(parameters);
		ArrayList<Object> list = sqlhelper.executeQuery(sql, parameters);
		
		//���ﷵ�ص�Arraylist��Object��ģ�������Ҫ����ת����Student��
		ArrayList<CourseInfo> infos = new ArrayList<CourseInfo>();
		
		for (int i = 0; i < list.size(); i++) {
			CourseInfo infoout = new CourseInfo();
			Object[] obj = (Object[])list.get(i);
			infoout.setCourseID(obj[0].toString());
			infoout.setCourseName(obj[1].toString());
			infoout.setTeacherName(obj[2].toString());
			infos.add(infoout);
		}	
		return infos;
		
	}
	
	//��ȡ�γ���ϸ��Ϣ
	public ArrayList<CourseInfo> CourseDetail (CourseInfo infoin){
		String sql = "select * from courseinfo where courseID=?";
		
		String[] parameters={infoin.getCourseID()};
		//System.out.println(infoin.getCourseID());
		ArrayList<Object> list = sqlhelper.executeQuery(sql, parameters);
		
		//���ﷵ�ص�Arraylist��Object��ģ�������Ҫ����ת����Student��
		ArrayList<CourseInfo> infos = new ArrayList<CourseInfo>();
		
		for (int i = 0; i < list.size(); i++) {
			CourseInfo infoout = new CourseInfo();
			Object[] obj = (Object[])list.get(i);
			infoout.setCourseID(obj[0].toString());
			infoout.setCourseName(obj[1].toString());
			infoout.setStarttime(obj[2].toString());
			infoout.setEndtime(obj[3].toString());
			infoout.setWeekTime(obj[4].toString());
			infoout.setDayTime(obj[5].toString());
			infoout.setTeacherName(obj[6].toString());
			infoout.setClassroomName(obj[7].toString());
			infoout.setMajorName(obj[8].toString());
			infoout.setDepartmentName(obj[9].toString());
			infos.add(infoout);
		}	
		return infos;
		
	}
	
	//��ȡȫ���Ŀγ̵Ĵ�����Ϣ
	public ArrayList<CourseInfo> ShowAllCourses(){
		String sql = "select courseID,courseName,teacherName from courseinfo ";
		
		String[] parameters={};
		//System.out.println(parameters);
		ArrayList<Object> list = sqlhelper.executeQuery(sql,parameters);
		
		//���ﷵ�ص�Arraylist��Object��ģ�������Ҫ����ת����Student��
		ArrayList<CourseInfo> infos = new ArrayList<CourseInfo>();
		
		for (int i = 0; i < list.size(); i++) {
			CourseInfo infoout = new CourseInfo();
			Object[] obj = (Object[])list.get(i);
			infoout.setCourseID(obj[0].toString());
			infoout.setCourseName(obj[1].toString());
			infoout.setTeacherName(obj[2].toString());
			infos.add(infoout);
		}	
		return infos;
		
	}
	
	//��ȡѧ���γ̱�
	/*public ArrayList<CourseInfo> CourseTable(Student studentin) throws Exception{
		//ӦΪ�����з���ֵ�Ĵ洢���̣���������û�����ֵĺ�����������������д��
		
		Connection con = DBUtil.getConnection();
		CallableStatement cstmt = con.prepareCall("{call dbo.stu_coursetable(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
	     cstmt.setString(1, studentin.getStudentID());
	     cstmt.registerOutParameter(2, java.sql.Types.CHAR);
	     cstmt.registerOutParameter(3, java.sql.Types.CHAR);
	     cstmt.registerOutParameter(4, java.sql.Types.CHAR);
	     cstmt.registerOutParameter(5, java.sql.Types.CHAR);
	     cstmt.registerOutParameter(6, java.sql.Types.CHAR);
	     cstmt.registerOutParameter(7, java.sql.Types.CHAR);
	     cstmt.execute();
		
		//ArrayList<Object> list = cstmt.registerOutParameter(2, java.sql.Types.INTEGER);;
		
		//���ﷵ�ص�Arraylist��Object��ģ�������Ҫ����ת����Student��
		ArrayList<CourseInfo> infos = new ArrayList<CourseInfo>();
		
		for (int i = 0; i < list.size(); i++) {
			CourseInfo infoout = new CourseInfo();
			Object[] obj = (Object[])list.get(i);
			infoout.setCourseID(obj[0].toString());
			infoout.setCourseName(obj[1].toString());
			infoout.setTeacherName(obj[2].toString());
			infos.add(infoout);
		}	
		return infos;
		
	}*/
	
}
