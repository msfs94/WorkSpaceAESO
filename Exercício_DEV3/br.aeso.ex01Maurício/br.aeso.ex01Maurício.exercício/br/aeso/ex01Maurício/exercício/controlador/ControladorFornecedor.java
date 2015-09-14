package br.aeso.ex01Maur�cio.exerc�cio.controlador;

import java.util.ArrayList;

import br.aeso.ex01Maur�cio.exerc�cio.exceptions.projetoException;
import br.aeso.ex01Maur�cio.exerc�cio.modelo.Fornecedor;
import br.aeso.ex01Maur�cio.exerc�cio.repositorio.IRepositorio;
import br.aeso.ex01Maur�cio.exerc�cio.repositorio.RepositorioFornecedorJDBC;
import br.aeso.ex01Maur�cio.exerc�cio.util.ValidarCPF;

public class ControladorFornecedor implements IControlador<Fornecedor> {

	IRepositorio<Fornecedor, String> repositorioFornecedor;
	ControladorEndereco controladorEndereco;

	public ControladorFornecedor() {
		repositorioFornecedor = new RepositorioFornecedorJDBC();
		controladorEndereco = new ControladorEndereco();
	}

	public void cadastrarEntities(Fornecedor fornecedor) throws projetoException {

		if ((fornecedor != null) && (ValidarCPF.validaCPF(fornecedor.getCpf()))) {
			if (fornecedor.getNome().equals("")) {
				throw new projetoException("Nome inv�lido!");
			} else {
				repositorioFornecedor.cadastrar(fornecedor);
				fornecedor.getEndere�o().setFornecedor(fornecedor);
				controladorEndereco.cadastrarEntities(fornecedor.getEndere�o());
			}
		} else {
			throw new projetoException("Fornecedor ou CPF inv�lido!");
		}
	}

	public void atualizarEntities(Fornecedor fornecedor) throws projetoException {

		if ((fornecedor != null) && (ValidarCPF.validaCPF(fornecedor.getCpf()))) {
			if (fornecedor.getNome() == "" || fornecedor.getCpf() == "") {
				throw new projetoException("Fornecedor inv�lido!");
			} else {
				repositorioFornecedor.atualizar(fornecedor);
				fornecedor.getEndere�o().setFornecedor(fornecedor);
				controladorEndereco.atualizarEntities(fornecedor.getEndere�o());
			}
		} else {
			throw new projetoException("Fornecedor ou CPF inv�lido!");
		}

	}

	public boolean removerEntities(String cpf) throws projetoException {
		Fornecedor f = procurarEntities(cpf);
		if (f != null) {
			controladorEndereco.removerEntities(f.getID());
		} else {
			throw new projetoException("Fornecedor n�o encontrado!");
		}
		return repositorioFornecedor.remover(cpf);
	}

	public Fornecedor procurarEntities(String cpf) throws projetoException {
		Fornecedor fornecedor = repositorioFornecedor.procurar(cpf);
		fornecedor.setEndere�o(controladorEndereco.procurarEntities(cpf));
		return fornecedor;
	}

	public ArrayList<Fornecedor> listarEntities() throws projetoException {
		ArrayList<Fornecedor> fornecedores = repositorioFornecedor.listarEntities();
		if (fornecedores.isEmpty()) {
			return null;
		} else {
			for (Fornecedor f : fornecedores) {
				f.setEndere�o(controladorEndereco.procurarEntities(f.getCpf()));
			}
		}
		return fornecedores;
	}

	/*
	 * private Integer procurarID(String cpf) throws projetoException {
	 * ArrayList<Fornecedor> fornecedores = listarEntities(); if
	 * (fornecedores.isEmpty()) { return null; } else { for (Fornecedor f :
	 * fornecedores) { if (f.getCpf().equals(cpf)) { return f.getID(); } } }
	 * return null; }
	 */
}