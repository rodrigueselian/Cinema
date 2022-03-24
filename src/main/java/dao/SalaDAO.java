package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Sala;

public class SalaDAO extends BaseDAO{

	public static List<Sala> selectSalas() {
		final String sql = "SELECT * FROM sala ORDER BY id";
		try
			(
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);
				ResultSet res = prepare.executeQuery();
			)
		{
			List<Sala> salas = new ArrayList<>();
			while(res.next()) {
				salas.add(resultsetSala(res));
			}
			return salas;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Sala selectSalaById(int id) {
		final String sql = "SELECT * FROM sala WHERE id=?";
		try (
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);
		)
		{
			prepare.setInt(1, id);	
			ResultSet res = prepare.executeQuery();
			Sala sala = null;
			if(res.next()) {
				sala = resultsetSala(res);
			}
			res.close();
			return sala;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insertSala(Sala sala) {
		final String sql = "INSERT INTO sala VALUES (?, ?)";
		try (
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);	
		)
		{
			prepare.setInt(1, 0);
			prepare.setInt(2, sala.getCapacidade());
			int count = prepare.executeUpdate();
			return count > 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean updateSala(Sala sala) {
		final String sql = "UPDATE sala SET capacidade=? WHERE id=?";
		try	(
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);		
		)
		{
			prepare.setInt(1, sala.getCapacidade());
			prepare.setInt(2, sala.getId());
			int count = prepare.executeUpdate();
			return count > 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static Sala resultsetSala(ResultSet res) throws SQLException {
		Sala s = new Sala();
		s.setId(res.getInt("id"));
		s.setCapacidade(res.getInt("capacidade"));
		return s;
	}

}