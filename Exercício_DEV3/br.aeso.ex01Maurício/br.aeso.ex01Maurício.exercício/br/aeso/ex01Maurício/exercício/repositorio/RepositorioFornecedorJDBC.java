package br.aeso.ex01Maur�cio.exerc�cio.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.aeso.ex01Maur�cio.exerc�cio.exceptions.projetoException;
import br.aeso.ex01Maur�cio.exerc�cio.modelo.Fornecedor;
import br.aeso.ex01Maur�cio.exerc�cio.util.JDBCConnection;

public class RepositorioFornecedorJDBC implements IRepositorio<Fornecedor, String> {

	private Connection conexaoBD;

	public RepositorioFornecedorJDBC() {
		conexaoBD = JDBCConnection.getConnection();
	}

	@Override
	public void cadastrar(Fornecedor entity) throws projetoException {

		String sqlInsert = "insert into projeto01.fornecedor (nome,cpf,banco) values (?,?,?)";

		try {

			if (this.existe(entity.getCpf())) {
				throw new projetoException("Fornecedor j� existe!");
			}

			PreparedStatement pStatement = conexaoBD.prepareStatement(
					sqlInsert, Statement.RETURN_GENERATED_KEYS);
			pStatement.setString(1, entity.getNome());
			pStatement.setString(2, entity.getCpf());
			pStatement.setString(3, entity.getBanco());
			pStatement.execute();

			ResultSet rSet = pStatement.getGeneratedKeys();
			Integer fornecedor_id = 0;

			while (rSet.next()) {
				fornecedor_id = rSet.getInt(1);
			}

			entity.setID(fornecedor_id);

			pStatement.close();
			rSet.close();
			conexaoBD.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Fornecedor procurar(String cpf) throws projetoException {

		String sqlConsulta = "select * from projeto01.fornecedor where cpf = '"
				+ cpf + "'";

		try {
			PreparedStatement pStatement = conexaoBD
					.prepareStatement(sqlConsulta);
			ResultSet rSet = pStatement.executeQuery();

			if (rSet != null && rSet.next()) {
				String nome = rSet.getString("nome");
				String cpf_fornecedor = rSet.getString("cpf");
				String banco = rSet.getString("banco");

				Fornecedor fornecedorEncontrado = new Fornecedor(null,null,null);
				fornecedorEncontrado.setNome(nome);
				fornecedorEncontrado.setCpf(cpf_fornecedor);
				fornecedorEncontrado.setBanco(banco);

				return fornecedorEncontrado;
			}

			pStatement.close();
			rSet.close();
			conexaoBD.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean remover(String cpf) throws projetoException {

		String sqlRemove = "delete from projeto01.fornecedor where cpf = ?";

		int linhasAfetadas = 0;

		try {
			PreparedStatement pStatement = conexaoBD
					.prepareStatement(sqlRemove);
			pStatement.setString(1, cpf);
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
	public void atualizar(Fornecedor entity) throws projetoException {
		String sqlUpdate = "update projeto01.fornecedor set nome = ?, cpf = ?, banco = ? where cpf = ?";

		try {
			PreparedStatement pStatement = conexaoBD
					.prepareStatement(sqlUpdate);
			pStatement.setString(1, entity.getNome());
			pStatement.setString(2, entity.getCpf());
			pStatement.setString(3, entity.getBanco());
			pStatement.setString(4, entity.getCpf());
			pStatement.executeUpdate();

			pStatement.close();
			conexaoBD.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	@Override
	public ArrayList<Fornecedor> listarEntities() throws projetoException {

		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

		String sqlConsulta = "select * from projeto01.fornecedor";

		try {
			PreparedStatement pStatment = conexaoBD
					.prepareStatement(sqlConsulta);
			ResultSet rSet = pStatment.executeQuery();

			while (rSet != null && rSet.next()) {

				Fornecedor fornecedor = new Fornecedor(null,null,null);
				fornecedor.setNome(rSet.getString("nome"));
				fornecedor.setCpf(rSet.getString("cpf"));
				fornecedor.setBanco(rSet.getString("banco"));

				fornecedores.add(fornecedor);
			}

			pStatment.close();
			rSet.close();
			conexaoBD.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return fornecedores;
	}

	private boolean existe(String cpf) throws SQLException {
		PreparedStatement prepStatment = null;
		ResultSet consultaBD = null;
		String id_fornecedor = null;

		String sql = "select fornecedor_id from projeto01.fornecedor where cpf = '"
				+ cpf + "'";

		prepStatment = conexaoBD.prepareStatement(sql);
		consultaBD = prepStatment.executeQuery();

		while (consultaBD.next()) {
			id_fornecedor = consultaBD.getString(1);
		}

		if (id_fornecedor == null) {
			return false;
		}
		return true;
	}

}
