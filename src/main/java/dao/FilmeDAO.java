package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Filme;

public class FilmeDAO extends BaseDAO{

	public static List<Filme> selectFilmes() {
		final String sql = "SELECT * FROM filme ORDER BY id desc";
		try
			(
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);
				ResultSet res = prepare.executeQuery();
			)
		{
			List<Filme> filmes = new ArrayList<>();
			while(res.next()) {
				filmes.add(resultsetFilme(res));
			}
			return filmes;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Filme> selectFilmeByTitulo(String titulo) {
		final String sql = "SELECT * FROM filme WHERE titulo LIKE ? ORDER BY titulo";
		try (
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);
		)
		{
			prepare.setString(1, titulo.toLowerCase() + "%");	
			ResultSet res = prepare.executeQuery();
			List<Filme> filmes = new ArrayList<>();
			while(res.next()) {
				filmes.add(resultsetFilme(res));
			}
			return filmes;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Filme selectFilmeById(int id) {
		final String sql = "SELECT * FROM filme WHERE id=?";
		try (
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);
		)
		{
			prepare.setInt(1, id);	
			ResultSet res = prepare.executeQuery();
			Filme filme = null;
			if(res.next()) {
				filme = resultsetFilme(res);
			}
			res.close();
			return filme;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insertFilme(Filme filme) {
		final String sql = "INSERT INTO filme VALUES (?, ?, ?, ?)";
		try (
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);	
		)
		{
			prepare.setInt(1, 0);
			prepare.setString(2, filme.getTitulo());
			prepare.setTime(3, filme.getDuracao());
			prepare.setInt(4, filme.getGenero().getId());
			int count = prepare.executeUpdate();
			return count > 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean updateFilme(Filme filme) {
		final String sql = "UPDATE filme SET titulo=?, duracao=?, idgenero=? WHERE id=?";
		try	(
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);		
		)
		{
			prepare.setString(1, filme.getTitulo());
			prepare.setTime(2, filme.getDuracao());
			prepare.setInt(3, filme.getGenero().getId());
			prepare.setInt(4, filme.getId());
			int count = prepare.executeUpdate();
			return count > 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static Filme resultsetFilme(ResultSet res) throws SQLException {
		Filme f = new Filme();
		f.setId(res.getInt("id"));
		f.setTitulo(res.getString("titulo"));
		f.setDuracao(res.getTime("duracao"));
		f.setGenero(GeneroDAO.selectGeneroById(res.getInt("idgenero")));
		return f;
	}

}