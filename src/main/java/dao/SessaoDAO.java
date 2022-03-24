package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Filme;
import models.Sessao;

public class SessaoDAO extends BaseDAO{

	public static List<Sessao> selectSessoes() {
		final String sql = "SELECT * FROM sessao where encerrada = 0 ORDER BY id";
		try
			(
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);
				ResultSet res = prepare.executeQuery();
			)
		{
			List<Sessao> sessoes = new ArrayList<>();
			while(res.next()) {
				sessoes.add(resultsetSessao(res));
			}
			return sessoes;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Sessao selectLast() {
		final String sql = "select * from sessao where id = (select max(id) from sessao)";
		try (
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);
		)
		{
			ResultSet res = prepare.executeQuery();
			Sessao sessao = null;
			if(res.next()) {
				sessao = resultsetSessao(res);
			}
			res.close();
			return sessao;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Sessao> selectSessaoByFilme(int id) {
		final String sql = "SELECT * FROM sessao WHERE idfilme = ? and encerrada=0";
		try (
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);
		)
		{
			prepare.setInt(1, id);	
			ResultSet res = prepare.executeQuery();
			List<Sessao> sessoes = new ArrayList<>();
			while(res.next()) {
				sessoes.add(resultsetSessao(res));
			}
			return sessoes;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Sessao selectSessaoById(int id) {
		final String sql = "SELECT * FROM sessao WHERE id=?";
		try (
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);
		)
		{
			prepare.setInt(1, id);	
			ResultSet res = prepare.executeQuery();
			Sessao sessao = null;
			if(res.next()) {
				sessao = resultsetSessao(res);
			}
			res.close();
			return sessao;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insertSessao(Sessao sessao) {
		final String sql = "INSERT INTO sessao VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);	
		)
		{
			prepare.setInt(1, 0);
			prepare.setDate(2, sessao.getDt_sessao());
			prepare.setTime(3, sessao.getHr_sessao());
			prepare.setDouble(4, sessao.getValor_inteira());
			prepare.setDouble(5, sessao.getValor_meia());
			prepare.setBoolean(6, sessao.getEncerrada());
			prepare.setInt(7, sessao.getSala().getId());
			prepare.setInt(8, sessao.getFilme().getId());
			int count = prepare.executeUpdate();
			return count > 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean updateSessao(Sessao sessao) {
		final String sql = "UPDATE sessao SET dt_sessao=?, "
				+ "hor_sessao=?, valor_inteira=?, valor_meia=?, encerrada=?,"
				+ " idsala=?, idfilme=? WHERE id=?";
		try	(
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);		
		)
		{
			prepare.setDate(1, sessao.getDt_sessao());
			prepare.setTime(2, sessao.getHr_sessao());
			prepare.setDouble(3, sessao.getValor_inteira());
			prepare.setDouble(4, sessao.getValor_meia());
			prepare.setBoolean(5, false);
			prepare.setInt(6, sessao.getSala().getId());
			prepare.setInt(7, sessao.getFilme().getId());
			prepare.setInt(8, sessao.getId());
			int count = prepare.executeUpdate();
			return count > 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean encerraSessao(int id) {
		final String sql = "UPDATE sessao SET encerrada=1 where id=?";
		try	(
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);		
		)
		{
			prepare.setInt(1, id);
			int count = prepare.executeUpdate();
			return count > 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static Sessao resultsetSessao(ResultSet res) throws SQLException {
		Sessao s = new Sessao();
		s.setId(res.getInt("id"));
		s.setDt_sessao(res.getDate("dt_sessao"));
		s.setHr_sessao(res.getTime("hor_sessao"));
		s.setValor_inteira(res.getDouble("valor_inteira"));
		s.setValor_meia(res.getDouble("valor_meia"));
		s.setEncerrada(res.getBoolean("encerrada"));
		s.setSala(SalaDAO.selectSalaById(res.getInt("idsala")));
		s.setFilme(FilmeDAO.selectFilmeById(res.getInt("idfilme")));
		
		return s;
	}

}