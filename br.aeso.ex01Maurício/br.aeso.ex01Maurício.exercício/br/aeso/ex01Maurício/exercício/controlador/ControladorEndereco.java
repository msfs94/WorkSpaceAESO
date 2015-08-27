package br.aeso.ex01Maur�cio.exerc�cio.controlador;

import java.util.ArrayList;

import br.aeso.ex01Maur�cio.exerc�cio.exceptions.projetoException;
import br.aeso.ex01Maur�cio.exerc�cio.modelo.Endere�o;
import br.aeso.ex01Maur�cio.exerc�cio.repositorio.GenericRepositorioList;

public class ControladorEndereco implements IControlador<Endere�o> {

	GenericRepositorioList<Endere�o> repositorioEndereco;

	public ControladorEndereco() {
		repositorioEndereco = new GenericRepositorioList<>();
	}

	@Override
	public void cadastrarEntities(Endere�o endereco) throws projetoException {
		if (endereco.equals(null) || endereco.getRua().equals("")
				|| endereco.getBairro().equals("")
				|| endereco.getCep().equals("")
				|| endereco.getCidade().equals("")
				|| endereco.getComplemento().equals("")
				|| endereco.getNumero().equals("")) {
			throw new projetoException("Endere�o ou algum atributo inv�lido");
		} else
			repositorioEndereco.cadastrar(endereco);
	}

	@Override
	public void atualizarEntities(Endere�o endereco) throws projetoException {
		if (endereco.equals(null) || endereco.getRua().equals("")
				|| endereco.getBairro().equals("")
				|| endereco.getCep().equals("")
				|| endereco.getCidade().equals("")
				|| endereco.getComplemento().equals("")
				|| endereco.getNumero().equals("")) {
			throw new projetoException("Endere�o ou algum atributo inv�lido");
		} else
			repositorioEndereco.atualizar(endereco);
	}

	@Override
	public boolean removerEntities(String cpf) throws projetoException {
		return false;// repositorioEndereco.remover(id);
	}

	@Override
	public Endere�o procurarEntities(String cpf) throws projetoException {
		//Integer id = procurarID(cpf);
		return null;//repositorioEndereco.procurar(id);
	}

	@Override
	public ArrayList<Endere�o> listarEntities() throws projetoException {
		return repositorioEndereco.listarEntities();
	}

	/*
	private Integer procurarID(String cpf) throws projetoException {
		ArrayList<Endere�o> enderecos = listarEntities();
		if (enderecos.isEmpty()) {
			return null;
		} else {
			for (Endere�o f : enderecos) {
				if (f.getFornecedor().getCpf().equals(cpf)) {
					return f.getID();
				}
			}
		}
		return null;
	}*/

}
