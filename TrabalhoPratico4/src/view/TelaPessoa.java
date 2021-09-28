package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import principal.*;

public class TelaPessoa implements ActionListener, ListSelectionListener {		
	private JFrame janela;
	private JLabel titulo;
	private JButton cadastroCliente;
	private JButton refreshCliente;
	private JButton cadastroProf;
	private JButton refreshProf;
	private JList<String> listaClientesCadastrados;
	private JList<String> listaFuncionariosCadastrados;
	private String[] listaNomes = new String[1000];

	public void mostrarDados(int op){
		ClienteVIP cliente = new ClienteVIP();
		cliente.dadosPreCadastradosClientes();
		
		Funcionario funcionario = new Funcionario();
		funcionario.dadosPreCadastradosFuncionario();

		switch (op) {
		case 1:// Mostrar dados de alunos cadastrados (JList)
			listaNomes = cliente.visualizarNome();
			listaClientesCadastrados = new JList<String>(listaNomes);
			janela = new JFrame("Clientes");
			titulo = new JLabel("Clientes Cadastrados");
			cadastroCliente = new JButton("Cadastrar");
			refreshCliente = new JButton("Atualizar");

			titulo.setFont(new Font("Arial", Font.BOLD, 30));
			cadastroCliente.setFont(new Font("Arial", Font.BOLD, 18));
			refreshCliente.setFont(new Font("Arial", Font.BOLD, 18));
			listaClientesCadastrados.setFont(new Font("Arial", Font.BOLD, 15));
			titulo.setBounds(90, 15, 350, 30);
			listaClientesCadastrados.setBounds(40, 50, 400, 140);
			listaClientesCadastrados.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listaClientesCadastrados.setVisibleRowCount(10);

			cadastroCliente.setBounds(70, 210, 150, 50);
			refreshCliente.setBounds(250, 210, 150, 50);

			janela.setLayout(null);

			janela.add(titulo);
			janela.add(listaClientesCadastrados);
			janela.add(cadastroCliente);
			janela.add(refreshCliente);

			janela.setSize(500, 325);
			janela.setVisible(true);

			cadastroCliente.addActionListener(this);
			refreshCliente.addActionListener(this);
			listaClientesCadastrados.addListSelectionListener(this);
			janela.setLocationRelativeTo(null);
			janela.setResizable(false);

			break;

		case 2:// Mostrar dados de professores cadastrados (JList)
			listaNomes = funcionario.visualizarNome();
			listaFuncionariosCadastrados = new JList<String>(listaNomes);
			janela = new JFrame("Funcion�rios");
			titulo = new JLabel("Funcion�rios Cadastrados");
			cadastroProf = new JButton("Cadastrar");
			refreshProf = new JButton("Atualizar");

			titulo.setFont(new Font("Arial", Font.BOLD, 30));
			cadastroProf.setFont(new Font("Arial", Font.BOLD, 18));
			refreshProf.setFont(new Font("Arial", Font.BOLD, 18));
			listaFuncionariosCadastrados.setFont(new Font("Arial", Font.BOLD, 15));

			titulo.setBounds(65, 15, 400, 30);
			listaFuncionariosCadastrados.setBounds(40, 50, 400, 140);
			listaFuncionariosCadastrados.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listaFuncionariosCadastrados.setVisibleRowCount(10);


			cadastroProf.setBounds(70, 210, 150, 50);
			refreshProf.setBounds(250, 210, 150, 50);

			janela.setLayout(null);

			janela.add(titulo);
			janela.add(listaFuncionariosCadastrados);
			janela.add(cadastroProf);
			janela.add(refreshProf);

			janela.setSize(500, 325);
			janela.setVisible(true);

			cadastroProf.addActionListener(this);
			refreshProf.addActionListener(this);
			listaFuncionariosCadastrados.addListSelectionListener(this);
			janela.setLocationRelativeTo(null);
			janela.setResizable(false);
			
			break;

		default:
			JOptionPane.showMessageDialog(null,"Op��o n�o encontrada!", null, 
					JOptionPane.ERROR_MESSAGE);
		}

	}

	//Captura eventos relacionados aos bot�es da interface
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		//Cadastro de novo aluno
		if(src == cadastroCliente)
			new TelaDetalhePessoa().inserirEditar(1, this, 0);

		//Cadastro de novo professor
		if(src == cadastroProf)
			new TelaDetalhePessoa().inserirEditar(2, this, 0);

		// Atualiza a lista de nomes de alunos mostrada no JList
		if(src == refreshCliente) {
			listaClientesCadastrados.setListData(new ClienteVIP().visualizarNome());			
			listaClientesCadastrados.updateUI();
		}

		// Atualiza a lista de nomes de professores mostrada no JList
		if(src == refreshProf) {
			listaFuncionariosCadastrados.setListData(new Funcionario().visualizarNome());
			listaFuncionariosCadastrados.updateUI();
		}

	}

	//Captura eventos relacionados ao JList
	public void valueChanged(ListSelectionEvent e) {
		Object src = e.getSource();

		if(e.getValueIsAdjusting() && src == listaClientesCadastrados) {
			new TelaDetalhePessoa().inserirEditar(3, this, 
					listaClientesCadastrados.getSelectedIndex());
		}

		if(e.getValueIsAdjusting() && src == listaFuncionariosCadastrados) {
			new TelaDetalhePessoa().inserirEditar(4, this, 
					listaFuncionariosCadastrados.getSelectedIndex());
		}
	}

}