package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	//������ sqlserver
	private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//�������ݿ��URL��ַ
	private static final String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Teaching_assistant";
	private static final String username = "root";
	private static final String password = "root";
	//����������
	//private static Connection conn = null;
	//��̬����鸺���������
	static 
	{
		try
		{
			Class.forName(driver);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//����ģʽ�������ݿ����Ӷ���
	public static Connection getConnection() throws Exception
	{
		//�˴�Ϊ��ֹ�������ѹرա��Ĵ��������ﴴ��Connection��Ϊ��ֹʹ��ȫ�ֵ�Connection�������¶��̲߳������ʴ���
		Connection conn = null;
		if(conn == null)//���û�д���,�򴴽�����
		{
			conn = DriverManager.getConnection(url, username, password);
		}
		return conn;
	}
	
	//�ر���Դ����
	public static void close(ResultSet rs,Statement ps,Connection ct)
	{
		if(rs!=null)
		{	
				try
			{
					rs.close();
			}catch(Exception e)
			{
				
			}
			rs=null;//ʹ����������
		}
		if(ps!=null)
		{
			try
			{
					ps.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			ps=null;
		}
		
		if(ct!=null)
		{
			try
			{
					ct.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			ct=null;
		}
	}
}

