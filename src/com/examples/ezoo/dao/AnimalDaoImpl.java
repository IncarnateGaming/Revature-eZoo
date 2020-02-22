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

public class AnimalDaoImpl implements AnimalDAO {

	@Override
	public List<Animal> getAllAnimals() {
		List<Animal> animals = new ArrayList<>();
		Connection connection = null;
		Statement stmt = null;

		try {
			connection = DAOUtilities.getConnection();

			stmt = connection.createStatement();

			String sql = "SELECT * FROM ANIMALS";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Animal a = new Animal(rs);
				
				animals.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return animals;
	}
	public List<Animal> getAllAnimals(FeedingSchedule fs) {
		List<Animal> animals = new ArrayList<>();
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = DAOUtilities.getConnection();

			String sql = "SELECT * FROM ANIMALS WHERE feeding_schedule=?";

			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, fs.getScheduleID());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Animal a = new Animal(rs);
				
				animals.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return animals;
	}

	@Override
	public void saveAnimal(Animal animal) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO ANIMALS VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);

			// Add parameters from animal into PreparedStatement
			stmt.setLong(1, animal.getAnimalID());
			stmt.setString(2, animal.getName());

			stmt.setString(3, animal.getTaxKingdom());
			stmt.setString(4, animal.getTaxPhylum());
			stmt.setString(5, animal.getTaxClass());
			stmt.setString(6, animal.getTaxOrder());
			stmt.setString(7, animal.getTaxFamily());
			stmt.setString(8, animal.getTaxGenus());
			stmt.setString(9, animal.getTaxSpecies());

			stmt.setDouble(10, animal.getHeight());
			stmt.setDouble(11, animal.getWeight());

			stmt.setString(12, animal.getType());
			stmt.setString(13, animal.getHealthStatus());
			stmt.setInt(14, animal.getFeedingScheduleId());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
			throw new Exception("Insert animal failed: " + animal);
		}

	}

	@Override
	public Animal getAnimalById(long id) {
		Animal result = null;
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = DAOUtilities.getConnection();

			String sql = "SELECT * FROM ANIMALS WHERE animalid=?";
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				result = new Animal(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public Integer getHighestId() {
		Integer result = 0;
		Connection connection = null;
		Statement stmt = null;

		try {
			connection = DAOUtilities.getConnection();

			stmt = connection.createStatement();

			String sql = "SELECT MAX(animalid) FROM ANIMALS";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				result = rs.getInt("max");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public void updateAnimal(Animal animal) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE ANIMALS SET name=?, taxkingdom=?, taxphylum=?, taxclass=?, taxorder=?, taxfamily=?, taxgenus=?, taxspecies=?, height=?, weight=?, type=?, healthstatus=?, feeding_schedule=? WHERE animalid=?;";

			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);

			// Add parameters from animal into PreparedStatement
			stmt.setString(1, animal.getName());

			stmt.setString(2, animal.getTaxKingdom());
			stmt.setString(3, animal.getTaxPhylum());
			stmt.setString(4, animal.getTaxClass());
			stmt.setString(5, animal.getTaxOrder());
			stmt.setString(6, animal.getTaxFamily());
			stmt.setString(7, animal.getTaxGenus());
			stmt.setString(8, animal.getTaxSpecies());

			stmt.setDouble(9, animal.getHeight());
			stmt.setDouble(10, animal.getWeight());

			stmt.setString(11, animal.getType());
			stmt.setString(12, animal.getHealthStatus());
			stmt.setInt(13, animal.getFeedingScheduleId());
			
			stmt.setLong(14, animal.getAnimalID());
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
			throw new Exception("Update animal failed: " + animal);
		}
	}

	@Override
	public void deleteAnimal(Integer animalId) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			
			//Delete the schedule
			String sql2 = "DELETE FROM ANIMALS WHERE animalid=?";
			stmt = connection.prepareStatement(sql2);
			stmt.setInt(1, animalId);

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
			throw new Exception("Delete animal failed: " + animalId);
		}
		// TODO Auto-generated method stub
		
	}

}
