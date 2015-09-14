package br.aeso.ex01Maur�cio.exerc�cio.controlador;

import java.util.ArrayList;

import br.aeso.ex01Maur�cio.exerc�cio.exceptions.projetoException;
import br.aeso.ex01Maur�cio.exerc�cio.modelo.Endere�o;
import br.aeso.ex01Maur�cio.exerc�cio.modelo.Fornecedor;
import br.aeso.ex01Maur�cio.exerc�cio.repositorio.IRepositorio;
import br.aeso.ex01Maur�cio.exerc�cio.repositorio.RepositorioEnderecoJDBC;
import br.aeso.ex01Maur�cio.exerc�cio.repositorio.RepositorioFornecedorJDBC;

public class ControladorEndereco {

	IRepositorio<Endere�o, Integer> repositorioEndereco;
	IRepositorio<Fornecedor, String> repositorioFornecedor;

	public ControladorEndereco() {
		repositorioEndereco = new RepositorioEnderecoJDBC();
		repositorioFornecedor = new RepositorioFornecedorJDBC();
	}

	public void cadastrarEntities(Endere�o endereco) throws projetoException {
		if (endereco.equals(null) || endereco.getRua().equals("") || endereco.getBairro().equals("")
				|| endereco.getCep().equals("") || endereco.getCidade().equals("")
				|| endereco.getComplemento().equals("") || endereco.getNumero().equals("")) {
			throw new projetoException("Endere�o ou algum atributo inv�lido");
		} else
			repositorioEndereco.cadastrar(endereco);
	}

	public void atualizarEntities(Endere�o endereco) throws projetoException {
		if (endereco.equals(null) || endereco.getRua().equals("") || endereco.getBairro().equals("")
				|| endereco.getCep().equals("") || endereco.getCidade().equals("")
				|| endereco.getComplemento().equals("") || endereco.getNumero().equals("")) {
			throw new projetoException("Endere�o ou algum atributo inv�lido");
		} else
			repositorioEndereco.atualizar(endereco);
	}

	public boolean removerEntities(Integer id_fornecedor) throws projetoException {
		return repositorioEndereco.remover(id_fornecedor);
	}

	public Endere�o procurarEntities(String cpf) throws projetoException {
		Fornecedor f = repositorioFornecedor.procurar(cpf);
		Integer id = null;
		if (f != null) {
			id = f.getID();
		}
		return repositorioEndereco.procurar(id);
	}

	public ArrayList<Endere�o> listarEntities() throws projetoException {
		return repositorioEndereco.listarEntities();
	}

}
