package br.aeso.ex01Maur�cio.exerc�cio.controlador;

import java.util.ArrayList;

import br.aeso.ex01Maur�cio.exerc�cio.exceptions.projetoException;
import br.aeso.ex01Maur�cio.exerc�cio.modelo.Endere�o;
import br.aeso.ex01Maur�cio.exerc�cio.modelo.Fornecedor;
import br.aeso.ex01Maur�cio.exerc�cio.repositorio.IRepositorio;
import br.aeso.ex01Maur�cio.exerc�cio.repositorio.RepositorioFornecedorJDBC;
import br.aeso.ex01Maur�cio.exerc�cio.util.ValidarCPF;

public class ControladorFornecedor implements IControlador<Fornecedor> {

	IRepositorio<Fornecedor, String> repositorioFornecedor;
	IControlador<Endere�o> controladorEndereco;

	public ControladorFornecedor() {
		repositorioFornecedor = new RepositorioFornecedorJDBC();
		controladorEndereco = new ControladorEndereco();
	}

	public void cadastrarEntities(Fornecedor fornecedor)
			throws projetoException {

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

	public void atualizarEntities(Fornecedor fornecedor)
			throws projetoException {

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
		controladorEndereco.removerEntities(cpf);
		return repositorioFornecedor.remover(cpf);
	}

	public Fornecedor procurarEntities(String cpf) throws projetoException {
		return repositorioFornecedor.procurar(cpf);
	}

	public ArrayList<Fornecedor> listarEntities() throws projetoException {
		return repositorioFornecedor.listarEntities();
	}

	/*private Integer procurarID(String cpf) throws projetoException {
		ArrayList<Fornecedor> fornecedores = listarEntities();
		if (fornecedores.isEmpty()) {
			return null;
		} else {
			for (Fornecedor f : fornecedores) {
				if (f.getCpf().equals(cpf)) {
					return f.getID();
				}
			}
		}
		return null;
	}*/
}