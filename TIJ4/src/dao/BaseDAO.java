package dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.activation.FileDataSource;

public class BaseDAO {

	//万能的获取全部内容
	public static ArrayList getList(Class c1){
		ArrayList ar = new ArrayList();
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select * from "+ c1.getSimpleName();
		Field[] fi = c1.getDeclaredFields();
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Object ob = c1.newInstance();//实例化类对象
				for(Field ff :fi){
					ff.setAccessible(true);
					ff.set(ob, rs.getObject(ff.getName()));
				}
				ar.add(ob);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			BaseConnection.closeRes(conn, ps, rs);
		}
		return ar;
	}
	
	//万能的获取ID主键的一条数据。
	public static Object getObById(Class c1,int id){
		Object ob = null;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Field[] fi = c1.getDeclaredFields();
		String sql = "Select * from "+ c1.getSimpleName()+" where "+ fi[0].getName()+" = "+id;
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				ob = c1.newInstance();//实例化类对象
				for(Field ff :fi){
					ff.setAccessible(true);
					ff.set(ob, rs.getObject(ff.getName()));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			BaseConnection.closeRes(conn, ps, rs);
		}
		return ob;
	}

	//万能的获取Where 条件后 的数据。
	public static ArrayList getList(Class c1,String whereSql){
		ArrayList ar = new ArrayList();
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select * from "+ c1.getSimpleName() +" "+ whereSql;
		Field[] fi = c1.getDeclaredFields();
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Object ob = c1.newInstance();//实例化类对象
				for(Field ff :fi){
					ff.setAccessible(true);
					ff.set(ob, rs.getObject(ff.getName()));
				}
				ar.add(ob);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			BaseConnection.closeRes(conn, ps, rs);
		}
		return ar;
	}
	
	//插入的万能方法。
	public static boolean Insert(Object ob){
		boolean f = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		Class c1 = ob.getClass();
		Field[] fi = c1.getDeclaredFields();
		StringBuffer Sql = new StringBuffer(); 
		StringBuffer Sql1 = new StringBuffer();
		Sql.append("insert into ").append(c1.getSimpleName()).append(" (");
		for(int i =1 ;i<fi.length;i++){
			fi[i].setAccessible(true);
			Sql.append(fi[i].getName());
			Sql1.append("?");
			if(i!= fi.length -1){
				Sql.append(",");
				Sql1.append(",");
			}
		}
		Sql.append(") ").append("values (").append(Sql1).append(");");
		try{
			System.out.println(Sql.toString());
			ps = conn.prepareStatement(Sql.toString());
			for(int i =1;i<fi.length;i++){
				fi[i].setAccessible(true);
				ps.setObject(i, fi[i].get(ob));
			}
			int a = ps.executeUpdate();
			if(a>0){
				f = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			BaseConnection.closeRes(conn, ps);
		}
		return f;
	}
   
	//万能更新
	public static boolean update(Object ob){
		boolean b = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		Class c1 = ob.getClass();
		Field[] fi = c1.getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		sb.append("update ").append(c1.getSimpleName()).append(" set ");
		for(int i = 1; i<fi.length;i++){
			sb.append(fi[i].getName());
			sb.append(" = ? ");
			if(i!= fi.length -1){
				sb.append(" , ");
			}
		}
		sb.append(" where ");
		sb.append(fi[0].getName()).append(" =?");
		try{
			System.out.println(sb.toString());
			ps = conn.prepareStatement(sb.toString());
			for(int i=1;i<fi.length ; i++){
				fi[i].setAccessible(true);
				ps.setObject(i, fi[i].get(ob));
			}
			fi[0].setAccessible(true);
			ps.setObject(fi.length, fi[0].get(ob));
			int a = ps.executeUpdate();
			if(a>0){
				b=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
	}
	
	//万能删除
	public static boolean delete(Class c1 ,int id){
		boolean b = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		Field[] fi = c1.getDeclaredFields();
		String Sql = "Delete from "+c1.getSimpleName()+" Where "+fi[0].getName()+" = ?";
		try{
			ps = conn.prepareStatement(Sql);
			ps.setObject(1, id);
			int a = ps.executeUpdate();
			if(a>0){
				b=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			BaseConnection.closeRes(conn, ps);
		}
		return b;
	}
	
	//万能删除
	public static boolean delete(Class c1 ,String WhereSql){
		boolean b = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		Field[] fi = c1.getDeclaredFields();
		String Sql = "Delete from "+c1.getSimpleName()+" "+WhereSql;
		try{
			ps = conn.prepareStatement(Sql);
			int a = ps.executeUpdate();
			if(a>0){
				b=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			BaseConnection.closeRes(conn, ps);
		}
		return b;
	}

}