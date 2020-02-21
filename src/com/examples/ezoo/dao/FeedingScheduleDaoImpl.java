package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class FeedingScheduleDaoImpl implements FeedingScheduleDAO {

	@Override
	public void saveFeedingSchedule(FeedingSchedule fs) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO FEEDING_SCHEDULES VALUES (?,?,?,?,?)";
			
			//Declare prepared statement
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, fs.getScheduleID());
			stmt.setString(2, fs.getFeedingTime());
			stmt.setString(3, fs.getRecurrence());
			stmt.setString(4, fs.getFood());
			stmt.setString(5, fs.getNotes());
			
			//run statement
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Insert feeding schedule failed: " + fs);
		}
	}

	@Override
	public void editFeedingSchedule(FeedingSchedule fs) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE FEEDING_SCHEDULES SET feeding_time=?, recurrence=?, food=?, notes=? WHERE schedule_id=?";
			
			//Declare prepared statement
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, fs.getFeedingTime());
			stmt.setString(2, fs.getRecurrence());
			stmt.setString(3, fs.getFood());
			stmt.setString(4, fs.getNotes());
			stmt.setInt(5, fs.getScheduleID());
			
			//run statement
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Updating feeding schedule failed: " + fs);
		}
		
	}

	@Override
	public void deleteFeedingSchedule(FeedingSchedule fs) throws Exception {
		Connection connection = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		int success2 = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			
			//Set current Animal references to the not fed schedule
			String sql1 = "UPDATE ANIMALS SET feeding_schedule="+ FeedingSchedule.getNotFedId() + " WHERE feeding_schedule=?";
			stmt1 = connection.prepareStatement(sql1);
			stmt1.setInt(1, fs.getScheduleID());
			
			//Delete the schedule
			String sql2 = "DELETE FROM FEEDING_SCHEDULES WHERE schedule_id=?";
			stmt2 = connection.prepareStatement(sql2);
			stmt2.setInt(1, fs.getScheduleID());

			//run statement
			stmt1.executeUpdate();
			success2 = stmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (stmt1 != null)
					stmt1.close();
				if (stmt2 != null)
					stmt2.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (success2 == 0) {
			throw new Exception("Delete feeding schedule failed: " + fs);
		}
	}

	@Override
	public List<FeedingSchedule> getFeedingSchedules(){
		Connection connection = null;
		Statement stmt = null;
		List<FeedingSchedule> result = new ArrayList<FeedingSchedule>();
		
		try {
			connection = DAOUtilities.getConnection();
			
			//Set current Animal references to the not fed schedule
			String sql = "SELECT * FROM FEEDING_SCHEDULES";
			stmt = connection.createStatement();

			//run statement
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				FeedingSchedule fs = new FeedingSchedule(rs);
				result.add(fs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public FeedingSchedule getFeedingSchedule(Animal animal){
		Connection connection = null;
		PreparedStatement stmt = null;
		FeedingSchedule result = new FeedingSchedule();
		
		try {
			connection = DAOUtilities.getConnection();
			
			String sql = "SELECT * FROM FEEDING_SCHEDULES WHERE schedule_id=?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, animal.getFeedingScheduleId());

			//run statement
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				FeedingSchedule fs = new FeedingSchedule(rs);
				result = fs;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public FeedingSchedule getFeedingSchedule(Integer scheduleId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		FeedingSchedule result = new FeedingSchedule();
		
		try {
			connection = DAOUtilities.getConnection();
			
			String sql = "SELECT * FROM FEEDING_SCHEDULES WHERE schedule_id=?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, scheduleId);

			//run statement
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				FeedingSchedule fs = new FeedingSchedule(rs);
				result = fs;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public void assignFeedingSchedule(Animal animal, FeedingSchedule fs) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			
			//Set current Animal references to the not fed schedule
			String sql = "UPDATE ANIMALS SET feeding_schedule=? WHERE animalid=?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, fs.getScheduleID());
			stmt.setLong(2, animal.getAnimalID());

			//run statement
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Assigned feeding schedule "+ fs.getScheduleID() + " to: "+ animal.getName() + " " + animal);
		}
	}

	@Override
	public void removeFeedingSchedule(Animal animal) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			
			//Set current Animal references to the not fed schedule
			String sql = "UPDATE ANIMALS SET feeding_schedule="+ FeedingSchedule.getNotFedId() + " WHERE animalid=?";
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, animal.getAnimalID());

			//run statement
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Removed feeding schedule from: "+ animal.getName() + " " + animal);
		}
	}



}
