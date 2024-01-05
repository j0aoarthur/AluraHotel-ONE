package views;

import Controllers.HospedeController;
import Controllers.ReservaController;
import Modelos.Hospede;
import Modelos.Reserva;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.List;

import java.sql.Date;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modeloReservas;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	private HospedeController hospedeController;
	private ReservaController reservaController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Buscar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagens/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		this.hospedeController = new HospedeController();
		this.reservaController = new ReservaController();
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(520, 127, 210, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtBuscar.setBackground(new Color(231, 231, 231));
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
				
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloReservas = (DefaultTableModel) tbReservas.getModel();
		modeloReservas.addColumn("Numero de Reserva");
		modeloReservas.addColumn("Data Check In");
		modeloReservas.addColumn("Data Check Out");
		modeloReservas.addColumn("Valor");
		modeloReservas.addColumn("Forma de Pagamento");
		listarReservas();
		JScrollPane scroll_tableReservas = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagens/reservado.png")), scroll_tableReservas, null);
		
		
		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		listarHospedes();
		JScrollPane scroll_tableHospedes = new JScrollPane(tbHospedes);
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/imagens/pessoas.png")), scroll_tableHospedes, null);

		panel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				tbReservas.clearSelection();
				tbHospedes.clearSelection();
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagens/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnBuscar = new JPanel();
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (scroll_tableHospedes.isShowing()) {
					buscarHospedes(txtBuscar.getText());
				} else if (scroll_tableReservas.isShowing()) {
					try {
						Integer convertedId = Integer.parseInt(txtBuscar.getText());
						buscarReservas(convertedId);
					} catch (Exception exception) {
						listarReservas();
					}
				}
			}
		});

		btnBuscar.setLayout(null);
		btnBuscar.setBackground(new Color(12, 138, 199));
		btnBuscar.setBounds(748, 125, 122, 35);
		btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnBuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnBuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int hospedesSelectedRow = tbHospedes.getSelectedRow();
				if (hospedesSelectedRow != -1) {
					try {
						int id = (int) tbHospedes.getValueAt(hospedesSelectedRow, 0);
						String nome = (String) tbHospedes.getValueAt(hospedesSelectedRow,1);
						String sobrenome = (String) tbHospedes.getValueAt(hospedesSelectedRow,2);
						Date data_nascimento = Date.valueOf((String) tbHospedes.getValueAt(hospedesSelectedRow,3));
						String nacionalidade = (String) tbHospedes.getValueAt(hospedesSelectedRow,4);
						String telefone = (String) tbHospedes.getValueAt(hospedesSelectedRow,5);
						int id_reserva = (int) tbHospedes.getValueAt(hospedesSelectedRow,6);

						hospedeController.alterar(new Hospede(id, nome, sobrenome, nacionalidade, telefone, data_nascimento, id_reserva));
						JOptionPane.showMessageDialog(contentPane, "Hóspede editado com sucesso!");
					}
					catch (ClassCastException classCastException) {
						JOptionPane.showMessageDialog(null, "Não é permitido alterar ids", "Erro", JOptionPane.ERROR_MESSAGE);
						listarHospedes();
					}
				}

				int reservasSelectedRow = tbReservas.getSelectedRow();
				if (reservasSelectedRow != -1) {
					try {
						int id = (int) tbReservas.getValueAt(reservasSelectedRow, 0);
						Date data_entrada =  Date.valueOf((String) tbReservas.getValueAt(reservasSelectedRow,1));
						Date data_saida = Date.valueOf((String) tbReservas.getValueAt(reservasSelectedRow,2));
						double valor = Double.valueOf((String) tbReservas.getValueAt(reservasSelectedRow,3));
						String forma_pagamento = (String) tbReservas.getValueAt(reservasSelectedRow,4);

						reservaController.alterar(new Reserva(id, data_entrada, data_saida, valor, forma_pagamento));
						JOptionPane.showMessageDialog(contentPane, "Reserva editada com sucesso!");
					}
					catch (ClassCastException intExeception) {
						JOptionPane.showMessageDialog(null, "Edição não permitida", "Erro", JOptionPane.ERROR_MESSAGE);
						listarReservas();
					}
				}
			}
		});
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);

		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int hospedesSelectedRow = tbHospedes.getSelectedRow();
				if (hospedesSelectedRow != -1) {
					try {
						int id = (int) tbHospedes.getValueAt(hospedesSelectedRow,0);

						hospedeController.deletar(id);
						JOptionPane.showMessageDialog(contentPane, "Hóspede deletado com sucesso!");
						listarHospedes();
					}
					catch (Exception exception) {
						JOptionPane.showMessageDialog(contentPane, "Não foi possível apagar o hóspede selecionado!", "Erro", JOptionPane.ERROR_MESSAGE );
					}
				}

				int reservasSelectedRow = tbReservas.getSelectedRow();
				if (reservasSelectedRow != -1) {
					try {
						int id = (int) tbReservas.getValueAt(reservasSelectedRow, 0);

						reservaController.deletar(id);
						JOptionPane.showMessageDialog(contentPane, "Reserva deletada com sucesso!");
						listarReservas();

					}
					catch (SQLException sqlException) {
						JOptionPane.showMessageDialog(contentPane, "Não é possivel apagar uma reserva de um hóspede existente!", "Erro", JOptionPane.ERROR_MESSAGE );
					}
					catch (Exception exception) {
						JOptionPane.showMessageDialog(contentPane, "Não foi possível apagar o hóspede selecionado!", "Erro", JOptionPane.ERROR_MESSAGE );
					}
				}
			}
		});
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}

	private void listarHospedes() {
		List<Hospede> hospedesList = this.hospedeController.listar();
		preencherTabelaHospedes(hospedesList);
	}

	private void listarReservas() {
		List<Reserva> reservasList = this.reservaController.listar();
		preencherTabelaReservas(reservasList);
	}

	private void buscarHospedes(String text) {
		List<Hospede> hospedesList = this.hospedeController.buscar(text);
		preencherTabelaHospedes(hospedesList);
	}

	private void buscarReservas(int id) {
		List<Reserva> reservaList = this.reservaController.buscar(id);
		preencherTabelaReservas(reservaList);
	}

	private void preencherTabelaHospedes(List<Hospede> hospedesList) {
		limparTabelaHospedes();
		for (Hospede hospede : hospedesList) {
			modeloHospedes.addRow(new Object[] {hospede.getId(), hospede.getNome(), hospede.getSobrenome(), hospede.getData_nascimentoString(), hospede.getNacionalidade(), hospede.getTelefone(), hospede.getId_reserva()} );
		}
	}

	private void preencherTabelaReservas(List<Reserva> reservasList) {
		limparTabelaReservas();
		for (Reserva reserva : reservasList) {
			modeloReservas.addRow(new Object[] {reserva.getId(), reserva.getData_entradaString(), reserva.getData_saidaString(), reserva.getValorString(), reserva.getForma_pagamento()} );
		}
	}

	private void limparTabelaHospedes() {
		modeloHospedes.getDataVector().clear();
	}
	private void limparTabelaReservas() {
		modeloReservas.getDataVector().clear();
	}

	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
