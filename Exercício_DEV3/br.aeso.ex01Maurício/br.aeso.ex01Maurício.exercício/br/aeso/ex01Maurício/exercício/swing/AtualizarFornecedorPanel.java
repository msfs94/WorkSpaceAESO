package br.aeso.ex01Maur�cio.exerc�cio.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;

import br.aeso.ex01Maur�cio.exerc�cio.exceptions.projetoException;
import br.aeso.ex01Maur�cio.exerc�cio.fachada.Fachada;
import br.aeso.ex01Maur�cio.exerc�cio.modelo.Endere�o;
import br.aeso.ex01Maur�cio.exerc�cio.modelo.Fornecedor;

@SuppressWarnings("serial")
public class AtualizarFornecedorPanel extends JPanel {
	private JButton btnAdicionar;
	private JButton btnLimpar;
	private JFormattedTextField cpfFornecedor;
	private JFormattedTextField bancoFornecedor;
	private JFormattedTextField nomeFornecedor;
	private JFormattedTextField ruaFornecedor;
	private JFormattedTextField complementoFornecedor;
	private JFormattedTextField cidadeFornecedor;
	private JFormattedTextField bairroFornecedor;
	private JFormattedTextField numeroFornecedor;
	private JFormattedTextField cepFornecedor;

	/**
	 * Create the panel.
	 * 
	 * @param btnLimpar
	 * @param btnAdicionar
	 * @param cpfCliente
	 */
	public AtualizarFornecedorPanel() {
		setLayout(null);

		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setBounds(226, 59, 46, 23);
		add(lblCPF);

		cpfFornecedor = new JFormattedTextField();
		cpfFornecedor.setBounds(272, 59, 150, 23);
		MaskFormatter mf;
		try {
			mf = new MaskFormatter("###.###.###-##");
			mf.install(cpfFornecedor);
			add(cpfFornecedor);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(26, 25, 46, 23);
		add(lblNome);

		nomeFornecedor = new JFormattedTextField();
		nomeFornecedor.setBounds(72, 25, 447, 23);
		add(nomeFornecedor);

		JLabel lblBanco = new JLabel("Banco:");
		lblBanco.setBounds(26, 59, 46, 23);
		add(lblBanco);

		bancoFornecedor = new JFormattedTextField();
		bancoFornecedor.setBounds(72, 59, 144, 23);
		add(bancoFornecedor);

		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(269, 117, 61, 14);
		add(lblEndereo);

		JLabel lblRua = new JLabel("Rua:");
		lblRua.setBounds(26, 157, 61, 14);
		add(lblRua);

		ruaFornecedor = new JFormattedTextField();
		ruaFornecedor.setBounds(113, 153, 150, 23);
		add(ruaFornecedor);

		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setBounds(26, 215, 89, 14);
		add(lblComplemento);

		complementoFornecedor = new JFormattedTextField();
		complementoFornecedor.setBounds(113, 211, 150, 23);
		add(complementoFornecedor);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(282, 186, 61, 14);
		add(lblBairro);

		bairroFornecedor = new JFormattedTextField();
		bairroFornecedor.setBounds(369, 182, 150, 23);
		add(bairroFornecedor);

		JLabel labelNumero = new JLabel("Numero:");
		labelNumero.setBounds(26, 186, 61, 14);
		add(labelNumero);

		numeroFornecedor = new JFormattedTextField();
		numeroFornecedor.setBounds(113, 182, 150, 23);
		add(numeroFornecedor);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(282, 157, 61, 14);
		add(lblCidade);

		cidadeFornecedor = new JFormattedTextField();
		cidadeFornecedor.setBounds(369, 153, 150, 23);
		add(cidadeFornecedor);

		JLabel labelCep = new JLabel("CEP:");
		labelCep.setBounds(282, 215, 61, 14);
		add(labelCep);

		cepFornecedor = new JFormattedTextField();
		cepFornecedor.setBounds(369, 211, 150, 23);
		add(cepFornecedor);

		btnAdicionar = new JButton("Atualizar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String cpf = cpfFornecedor.getText();
				cpf = cpf.replaceAll(" ", "");
				String nome = nomeFornecedor.getText();
				String banco = bancoFornecedor.getText();
				String rua = ruaFornecedor.getText();
				String numero = numeroFornecedor.getText();
				String complemento = complementoFornecedor.getText();
				String bairro = bairroFornecedor.getText();
				String cidade = cidadeFornecedor.getText();
				String cep = cepFornecedor.getText();

				Endere�o endereco = new Endere�o(null,null,null,null,null,null,null);
				endereco.setRua(rua);
				endereco.setNumero(numero);
				endereco.setComplemento(complemento);
				endereco.setBairro(bairro);
				endereco.setCidade(cidade);
				endereco.setCep(cep);

				Fornecedor fornecedor = new Fornecedor(null,null,null);
				fornecedor.setCpf(cpf);
				fornecedor.setNome(nome);
				fornecedor.setBanco(banco);
				fornecedor.setEndere�o(endereco);
				
				endereco.setFornecedor(fornecedor);

				Fachada fachada = Fachada.getInstance();
				try {
					fachada.atualizarFornecedor(fornecedor);
					limparCampos();
					JOptionPane.showMessageDialog(null,
							"Voc� atualizou o fornecedor com CPF:"+cpf+" com sucesso!",
							"Atualizar Fornecedor",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (projetoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),
							"Adicionar Fornecedor", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdicionar.setBounds(140, 338, 89, 23);
		add(btnAdicionar);

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
			}
		});
		btnLimpar.setBounds(369, 338, 89, 23);
		add(btnLimpar);

		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fachada fachada = Fachada.getInstance();
				String cpf = cpfFornecedor.getText();
				cpf = cpf.replaceAll(" ", "");
				Fornecedor fornecedorEncontrado = null;
				try {
					fornecedorEncontrado = fachada.procurarFornecedor(cpf);
				} catch (projetoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

				if (fornecedorEncontrado != null) {
					limparCampos();
					cpfFornecedor.setText(fornecedorEncontrado.getCpf());
					nomeFornecedor.setText(fornecedorEncontrado.getNome());
					bancoFornecedor.setText(fornecedorEncontrado.getBanco());
					ruaFornecedor.setText(fornecedorEncontrado.getEndere�o().getRua());
					numeroFornecedor.setText(fornecedorEncontrado.getEndere�o().getNumero());
					complementoFornecedor.setText(fornecedorEncontrado.getEndere�o().getComplemento());
					bairroFornecedor.setText(fornecedorEncontrado.getEndere�o().getBairro());
					cidadeFornecedor.setText(fornecedorEncontrado.getEndere�o().getCidade());
					cepFornecedor.setText(fornecedorEncontrado.getEndere�o().getCep());
				} else {
					JOptionPane.showMessageDialog(getPanel(),
							"Fornecedor n�o encontrado!",
							"Procurar fornecedor",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		btnProcurar.setBounds(430, 59, 89, 23);
		add(btnProcurar);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				cpfFornecedor.requestFocus();
			}
		});
	}

	public JPanel getPanel() {
		return this;
	}

	public void limparCampos() {
		cpfFornecedor.setText("");
		bancoFornecedor.setText("");
		nomeFornecedor.setText("");
		complementoFornecedor.setText("");
		ruaFornecedor.setText("");
		cidadeFornecedor.setText("");
		numeroFornecedor.setText("");
		bairroFornecedor.setText("");
		complementoFornecedor.setText("");
		cepFornecedor.setText("");

	}
}
