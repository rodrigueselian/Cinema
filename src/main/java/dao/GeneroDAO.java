package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Genero;

public class GeneroDAO extends BaseDAO{

	public static List<Genero> selectGeneros() {
		final String sql = "SELECT * FROM genero ORDER BY id";
		try
			(
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);
				ResultSet res = prepare.executeQuery();
			)
		{
			List<Genero> generos = new ArrayList<>();
			while(res.next()) {
				generos.add(resultsetGenero(res));
			}
			return generos;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Genero> selectGeneroByDescricao(String descricao) {
		final String sql = "SELECT * FROM genero WHERE descricao LIKE ? ORDER BY descricao";
		try (
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);
		)
		{
			prepare.setString(1, descricao.toLowerCase() + "%");	
			ResultSet res = prepare.executeQuery();
			List<Genero> generos = new ArrayList<>();
			while(res.next()) {
				generos.add(resultsetGenero(res));
			}
			return generos;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Genero selectGeneroById(int id) {
		final String sql = "SELECT * FROM genero WHERE id=?";
		try (
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);
		)
		{
			prepare.setInt(1, id);	
			ResultSet res = prepare.executeQuery();
			Genero genero = null;
			if(res.next()) {
				genero = resultsetGenero(res);
			}
			res.close();
			return genero;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insertGenero(Genero genero) {
		final String sql = "INSERT INTO genero VALUES (?, ?)";
		try (
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);	
		)
		{
			prepare.setInt(1, 0);
			prepare.setString(2, genero.getDescricao());
			int count = prepare.executeUpdate();
			return count > 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean updateGenero(Genero genero) {
		final String sql = "UPDATE genero SET descricao=? WHERE id=?";
		try	(
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);		
		)
		{
			prepare.setString(1, genero.getDescricao());
			prepare.setInt(2, genero.getId());
			int count = prepare.executeUpdate();
			return count > 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static Genero resultsetGenero(ResultSet res) throws SQLException {
		Genero g = new Genero();
		g.setId(res.getInt("id"));
		g.setDescricao(res.getString("descricao"));
		return g;
	}

}