package com.DaoImple;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.json.JSONArray;
import org.json.JSONObject;

import common.database.DbConnection;

public class ApiController {
	public int AddRegister(JSONArray array, String name, String email, String password,String designation, String gender, String team,String username,String dob,String joindate,String image) {
		// TODO Auto-generated method stub

		 Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			pst=Conn.prepareStatement("insert into registerteamleader (name,email,password,designation,gender,team,username,dob,joindate,image) values(?,?,?,?,?,?,?,?,?,?)");
			 pst.setString(1,name);
			  pst.setString(2, email);
			  pst.setString(3, password);
			  pst.setString(4, designation);
			  pst.setString(5, gender);
			  pst.setString(6, team);
			  pst.setString(7, username);
			  pst.setString(8, dob);
			  pst.setString(9, joindate);
			  pst.setString(10, image);
	    	 i=pst.executeUpdate();
	    	
	    	JSONObject jsonObject=new JSONObject();
			jsonObject.put("name",name );
			jsonObject.put("email", email);
			jsonObject.put("password", password);
			jsonObject.put("designation", designation);
			jsonObject.put("gender", gender);
			jsonObject.put("teamName", team);
			
	        if(i>0) {
	        	System.out.println("sucessfully register");
	        	
	        	jsonObject.put("message", "Successfully register");
	        	jsonObject.put("status", "true");
	        	array.put(jsonObject);
	        }else {
	        	System.out.println("invalid email and password");
	        	
	        	jsonObject.put("message", "Try Again");
	        	jsonObject.put("status", "false");
	        	array.put(jsonObject);
	        }
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				if(Conn!=null) {
					Conn.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			try {
				if(pst!=null) {
					pst.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return i;
	}

}
