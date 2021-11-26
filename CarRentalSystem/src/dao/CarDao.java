package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.CarBean;
import beans.IssueCarBean;
import beans.OwnerBean;

public class CarDao {

	public static int save(CarBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("insert into CAR values(?,?,?,?,?,?)");
			ps.setString(1,bean.getCallno());
			ps.setString(2,bean.getCarname());
			ps.setString(3,bean.getCompany());
			ps.setString(4,bean.getCondition());
			ps.setInt(5,bean.getQuantity());
			ps.setInt(6,0);
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	public static List<CarBean> view(){
		List<CarBean> list=new ArrayList<CarBean>();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from CAR");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				CarBean bean=new CarBean();
				bean.setCallno(rs.getString("callno"));
				bean.setCarname(rs.getString("carname"));
				bean.setCompany(rs.getString("company"));
				bean.setCondition(rs.getString("condtn"));
				bean.setQuantity(rs.getInt("quantity"));
				bean.setIssued(rs.getInt("issued"));
				
				list.add(bean);
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;
	}
	public static int delete(String callno){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("delete from CAR where callno=?");
			ps.setString(1,callno);
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	public static int getIssued(String callno){
		int issued=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from CAR where callno=?");
			ps.setString(1,callno);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				issued=rs.getInt("issued");
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return issued;
	}
	public static boolean checkIssue(String callno){
		boolean status=false;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from CAR where callno=? and quantity>issued");
			ps.setString(1,callno);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				status=true;
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	public static int issueCar(IssueCarBean bean){
		String callno=bean.getCallno();
		boolean checkstatus=checkIssue(callno);
		System.out.println("Check status: "+checkstatus);
		if(checkstatus){
			int status=0;
			try{
				Connection con=DB.getCon();
				PreparedStatement ps=con.prepareStatement("insert into ISSUECAR values(?,?,?,?,?,?)");
				ps.setString(1,bean.getCallno());
				ps.setString(2,bean.getCustomerid());
				ps.setString(3,bean.getCustomername());
				ps.setLong(4,bean.getCustomermobile());
				java.sql.Date currentDate=new java.sql.Date(System.currentTimeMillis());
				ps.setDate(5,currentDate);
				ps.setString(6,"no");
				
				status=ps.executeUpdate();
				if(status>0){
					PreparedStatement ps2=con.prepareStatement("update CAR set issued=? where callno=?");
					ps2.setInt(1,getIssued(callno)+1);
					ps2.setString(2,callno);
					status=ps2.executeUpdate();
				}
				con.close();
				
			}catch(Exception e){System.out.println(e);}
			
			return status;
		}else{
			return 0;
		}
	}
	public static int returnCar(String callno,int carid){
		int status=0;
			try{
				Connection con=DB.getCon();
				PreparedStatement ps=con.prepareStatement("update ISSUECAR set returnstatus='yes' where callno=? and customerid=?");
				ps.setString(1,callno);
				ps.setInt(2,carid);
				
				status=ps.executeUpdate();
				if(status>0){
					PreparedStatement ps2=con.prepareStatement("update CAR set issued=? where callno=?");
					ps2.setInt(1,getIssued(callno)-1);
					ps2.setString(2,callno);
					status=ps2.executeUpdate();
				}
				con.close();
				
			}catch(Exception e){System.out.println(e);}
			
			return status;
	}
	public static List<IssueCarBean> viewIssuedCars(){
		List<IssueCarBean> list=new ArrayList<IssueCarBean>();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from ISSUECAR order by issueddate desc");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				IssueCarBean bean=new IssueCarBean();
				bean.setCallno(rs.getString("callno"));
				bean.setCustomerid(rs.getString("customerid"));
				bean.setCustomername(rs.getString("customername"));
				bean.setCustomermobile(rs.getLong("customermobile"));
				bean.setIssueddate(rs.getDate("issueddate"));
				bean.setReturnstatus(rs.getString("returnstatus"));
				list.add(bean);
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;
	}

}
