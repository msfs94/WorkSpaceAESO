package br.aeso.ex01Maur�cio.exerc�cio.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.aeso.ex01Maur�cio.exerc�cio.exceptions.projetoException;
import br.aeso.ex01Maur�cio.exerc�cio.modelo.Endere�o;
import br.aeso.ex01Maur�cio.exerc�cio.modelo.Fornecedor;
import br.aeso.ex01Maur�cio.exerc�cio.util.JDBCConnection;

public class RepositorioEnderecoJDBC implements IRepositorio<Endere�o, Integer> {

	private Connection conexaoBD;

	public RepositorioEnderecoJDBC() {
		conexaoBD = JDBCConnection.getConnection();
	}

	@Override
	public void cadastrar(Endere�o entity) throws projetoException {

		String sqlInsert = "insert into projeto01.endereco (fornecedor_id, rua, numero, complemento, bairro, cidade, cep) "
				+ "values (?,?,?,?,?,?,?)";

		try {

			PreparedStatement pStatement = conexaoBD.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			pStatement.setInt(1, entity.getFornecedor().getID());
			pStatement.setString(2, entity.getRua());
			pStatement.setString(3, entity.getNumero());
			pStatement.setString(4, entity.getComplemento());
			pStatement.setString(5, entity.getBairro());
			pStatement.setString(6, entity.getCidade());
			pStatement.setString(7, entity.getCep());
			pStatement.execute();

			Integer id_endere�o = 0;
			ResultSet rSet = pStatement.getGeneratedKeys();

			while (rSet.next()) {
				id_endere�o = rSet.getInt(1);
			}
			entity.setID(id_endere�o);

			rSet.close();
			pStatement.close();
			conexaoBD.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Endere�o procurar(Integer endereco_id) throws projetoException {

		String sqlConsulta = "select * from projeto01.endereco where endereco_id = ?";
		PreparedStatement pStatement;
		ResultSet rSet;
		Endere�o endereco = new Endere�o(null, null, null, null, null, null, null);

		try {
			pStatement = conexaoBD.prepareStatement(sqlConsulta);
			pStatement.setInt(1, endereco_id);
			rSet = pStatement.executeQuery();

			if (rSet != null && rSet.next()) {
				endereco.setID(rSet.getInt("endereco_id"));
				endereco.setRua(rSet.getString("rua"));
				endereco.setNumero(rSet.getString("numero"));
				endereco.setComplemento(rSet.getString("complemento"));
				endereco.setBairro(rSet.getString("bairro"));
				endereco.setCidade(rSet.getString("cidade"));
				endereco.setCep(rSet.getString("cep"));
				return endereco;
			}

			pStatement.close();
			rSet.close();
			conexaoBD.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return null;
	}

	@Override
	public boolean remover(Integer fornecedor_id) throws projetoException {

		String sqlRemove = "delete from projeto01.endereco where fornecedor_id = ?";

		int linhasAfetadas = 0;

		try {
			PreparedStatement pStatement = conexaoBD.prepareStatement(sqlRemove);
			pStatement.setInt(1, fornecedor_id);
			linhasAfetadas = pStatement.executeUpdate();

			conexaoBD.close();
			pStatement.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		if (linhasAfetadas != 0) {
			return true;
		}
		return false;
	}

	@Override
	public void atualizar(Endere�o entity) throws projetoException {

		String sqlUpdate = "update projeto01.endereco set rua = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, cep = ?";

		try {

			PreparedStatement pStatement = conexaoBD.prepareStatement(sqlUpdate);
			pStatement.setString(1, entity.getRua());
			pStatement.setString(2, entity.getNumero());
			pStatement.setString(3, entity.getComplemento());
			pStatement.setString(4, entity.getBairro());
			pStatement.setString(5, entity.getCidade());
			pStatement.setString(6, entity.getCep());
			pStatement.executeUpdate();

			pStatement.close();
			conexaoBD.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	@Override
	public ArrayList<Endere�o> listarEntities() throws projetoException {
		String sqlConsulta = "select * from projeto01.endereco";
		ArrayList<Endere�o> enderecos = new ArrayList<>();

		try {
			PreparedStatement pStatment = conexaoBD.prepareStatement(sqlConsulta);
			ResultSet rSet = pStatment.executeQuery();

			while (rSet != null && rSet.next()) {
				Endere�o endereco = new Endere�o(null, null, null, null, null, null, null);
				endereco.setBairro(rSet.getString("bairro"));
				endereco.setCep(rSet.getString("cep"));
				endereco.setCidade(rSet.getString("cidade"));
				endereco.setComplemento(rSet.getString("complemento"));
				endereco.setNumero(rSet.getString("numero"));
				endereco.setRua(rSet.getString("rua"));
				endereco.setID(rSet.getInt("endereco_id"));
				endereco.setFornecedor(new Fornecedor(null, null, null));
				endereco.getFornecedor().setID(rSet.getInt("fornecedor_id"));
				enderecos.add(endereco);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return enderecos;
	}

	public Endere�o buscarPorFornecedor(String id_fornecedor) {

		String sqlConsulta = "select * from projeto01.endereco where id_fornecedor = ?";
		PreparedStatement pStatement;
		ResultSet rSet;
		Endere�o endereco = new Endere�o(null, null, null, null, null, null, null);

		try {
			pStatement = conexaoBD.prepareStatement(sqlConsulta);
			pStatement.setString(1, id_fornecedor);
			rSet = pStatement.executeQuery();

			if (rSet != null && rSet.next()) {
				endereco.setID(rSet.getInt("endereco_id"));
				endereco.setRua(rSet.getString("rua"));
				endereco.setNumero(rSet.getString("numero"));
				endereco.setComplemento(rSet.getString("complemento"));
				endereco.setBairro(rSet.getString("bairro"));
				endereco.setCidade(rSet.getString("cidade"));
				endereco.setCep(rSet.getString("cep"));
				return endereco;
			}

			pStatement.close();
			rSet.close();
			conexaoBD.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return null;
	}

	@SuppressWarnings("unused")
	private boolean existe(Integer fornecedor_id) throws SQLException {
		PreparedStatement prepStatment = null;
		ResultSet consultaBD = null;
		String endereco_id = null;

		String sql = "select endereco_id from projeto01.endereco where fornecedor_id = ?";

		prepStatment = conexaoBD.prepareStatement(sql);
		prepStatment.setInt(1, fornecedor_id);
		consultaBD = prepStatment.executeQuery();

		while (consultaBD.next()) {
			endereco_id = consultaBD.getString("endereco_id");
		}

		if (endereco_id == null) {
			return false;
		}
		return true;
	}

}
