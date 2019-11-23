package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.Record;
import Util.DBUtil;
import Util.DateUtil;

public class RecordDAO {

	public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from record";
  
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
  
            System.out.println("total:" + total);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return total;
    }
	
	public void add(Record record) {
		String sql = "insert into Record value(null,?,?,?,?)";
		try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, record.spend);
			ps.setInt(2, record.cid);
			ps.setString(3, record.comment);
			ps.setDate(4, DateUtil.util2sql(record.date));
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				record.id = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void update(Record record) {
		String sql = "update record set spend = ?,cid = ?, comment = ? , date = ? where id = ?";
		
		try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, record.id);
			ps.setInt(2, record.cid);
			ps.setString(3, record.comment);
			ps.setDate(4, DateUtil.util2sql(record.date));
			ps.setInt(5, record.id);
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void delete(int id) {
		
		try(Connection c = DBUtil.getConnection();Statement s = c.createStatement()) {
			String sql = "delete from record where id = " + id;
			s.execute(sql);
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public Record get(int id) {
		Record record = null;
		
		try(Connection c = DBUtil.getConnection();Statement s = c.createStatement()) {
			String sql = "select * from record where id = " + id;
			ResultSet rs = s.executeQuery(sql);
			
			if(rs.next()) {
				record = new Record();
				record.id = id;
				record.spend = rs.getInt("spend");
				record.cid = rs.getInt("cid");
				record.comment = rs.getString("comment");
				record.date = rs.getDate("date");
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return record;
	}
	
	public List<Record> list (){
		return list(0,Short.MAX_VALUE);
	}
	
	public List<Record> list (int start,int count){
		List<Record> records = new ArrayList<Record>();
		
		String sql = "select * from record limit ?,?";
		
		try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, start);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();
			
			records = new RecordDAO().getRecordList(rs);
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return records;
	}
	
	
	public List<Record> list(int cid){
		
		List<Record> records = new ArrayList<Record>();
		
		String sql = "select * from record where cid = ?";
		
		try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, cid);
			ps.execute();
			
			ResultSet rs = ps.executeQuery();
			records = new RecordDAO().getRecordList(rs);
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return records;
		
		
		
		
	}
	
	public List<Record> listToday(){
		return list(DateUtil.today());
	}
	
	public List<Record> list(java.util.Date day){
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record where Date = ?";
		
		try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setDate(1, DateUtil.util2sql(day));
			ps.execute();
			
			ResultSet rs = ps.executeQuery();
			records = new RecordDAO().getRecordList(rs);
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return records;
	}
	
	public List<Record> listThisMonth(){
		return list(DateUtil.monthBegin(),DateUtil.monthEnd());
	}
	
	public List<Record> list(java.util.Date start,java.util.Date end){
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record where date > ? and date <? ";
		
		try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setDate(1, DateUtil.util2sql(start));
			ps.setDate(2, DateUtil.util2sql(end));
			ps.execute();
			
			ResultSet rs = ps.executeQuery();
			records = new RecordDAO().getRecordList(rs);
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return records;
	}
	
	//获取Record集合
	private List<Record> getRecordList(ResultSet rs){
		List<Record> records = new ArrayList<Record>();
		
		try {
			while(rs.next()) {
				Record record = new Record();
				record.id = rs.getInt("id");
				record.spend = rs.getInt("spend");
				record.cid = rs.getInt("cid");
				record.comment = rs.getString("comment");
				record.date = rs.getDate("date");
				
				records.add(record);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return records;
	}
	
}
