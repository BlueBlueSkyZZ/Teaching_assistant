package util;

//src=��·��
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//����һ������������Ҫ������ɶ����ݿ��crud����
public class SqlHelper 
{
	//������Ҫ�ı���	
	private static Connection ct=null;//����
	private static ResultSet rs=null;//���
	private static PreparedStatement ps=null;

	public ArrayList executeQuery(String sql,String []paras)
	{
		ArrayList al=new ArrayList();
		try {
			ct=DBUtil.getConnection();
			ps=ct.prepareStatement(sql);
			//��sql�ʺŸ�ֵ
			for (int i = 0; i < paras.length; i++) 
			{
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
			//�ǳ����õ� 
			ResultSetMetaData rsmd=rs.getMetaData();
			//�÷�rs���Եĵ��ж�����
			int columnNum=rsmd.getColumnCount();
			//ѭ����a1��ȡ�����ݷ�װ��ArrayList��
			while(rs.next())
			{
				Object []objects=new Object[columnNum];
				for(int i=0;i<objects.length;i++)
				{
					objects[i]=rs.getObject(i+1); //���ض�������
				}
				al.add(objects);
			}
			return al;
			} catch (Exception e) 
			{
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
		}finally
		{
			DBUtil.close(rs,ps,ct);
		}
		
	}
	public ResultSet executeQuery(String sqlstr) throws Exception 
	{
		Statement stmt = null;
		try
		{
			//�õ�����
			ct=DBUtil.getConnection();
			//ps=ct.prepareStatement(sqlstr);
			stmt = ct.createStatement();
			//���������
			 rs = stmt.executeQuery(sqlstr); 
			//�����������
			return rs;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.print("����");
		}
		return null;
	}
	
	/**
	 * ִ���޸����
	 * @param sqlstr
	 * @return ����ֵΪӰ���˶�����
	 */
	@SuppressWarnings("finally")
	public int executeUpdate(String sqlstr) {
		Statement stmt = null;
		
		int result = 0;
		
		try {
			//�õ�����
			ct=DBUtil.getConnection();
			//ps=ct.prepareStatement(sqlstr);
			stmt = ct.createStatement();
			//���������
			result = stmt.executeUpdate(sqlstr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
		
	}
}