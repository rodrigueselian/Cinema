package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Ingresso;
import models.Sessao;

public class IngressoDAO extends BaseDAO{

	public static List<Ingresso> selectIngressos() {
		final String sql = "SELECT * FROM ingresso ORDER BY id";
		try
			(
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);
				ResultSet res = prepare.executeQuery();
			)
		{
			List<Ingresso> ingressos = new ArrayList<>();
			while(res.next()) {
				ingressos.add(resultsetIngresso(res));
			}
			return ingressos;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Ingresso> selectIngressoById(int id) {
		final String sql = "SELECT * FROM ingresso WHERE idsessao=? ORDER BY nr_assento";
		try (
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);
		)
		{
			prepare.setInt(1, id);	
			ResultSet res = prepare.executeQuery();
			List<Ingresso> ingressos = new ArrayList<>();
			while(res.next()) {
				ingressos.add(resultsetIngresso(res));
			}
			return ingressos;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insertIngresso(int assento, Sessao sessao) {
		final String sql = "INSERT INTO ingresso VALUES (?,?,?,?,?)"; 
		try (
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);	
		)
		{
			prepare.setInt(1, 0);
			prepare.setInt(2, SessaoDAO.selectLast().getId());
			prepare.setInt(3, assento);
			prepare.setInt(4, 0);
			prepare.setInt(5, 0);
			int count = prepare.executeUpdate();
			return count > 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean vendaIngresso(int assento, boolean meia, Sessao sessao) {
		final String sql = "UPDATE ingresso SET vendido = 1, meia = ? where idsessao = ? and nr_assento = ?"; 
		try (
				Connection conn = getConnection();
				PreparedStatement prepare = conn.prepareStatement(sql);	
		)
		{
			prepare.setBoolean(1, meia);
			prepare.setInt(2, sessao.getId());
			prepare.setInt(3, assento);
			int count = prepare.executeUpdate();
			return count > 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static Ingresso resultsetIngresso(ResultSet res) throws SQLException {
		Ingresso i = new Ingresso();
		i.setId(res.getInt("id"));
		i.setSessao(SessaoDAO.selectSessaoById(res.getInt("idsessao")));
		i.setAssento(res.getInt("nr_assento"));
		i.setTipo(res.getBoolean("meia"));
		i.setVendido(res.getBoolean("vendido"));
		return i;
	}

}