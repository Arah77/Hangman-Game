package linked_data_structures;

import java.awt.EventQueue;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScoreboardFrame extends JFrame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel sBoard;
	private JButton btnExit;
	private JLabel lblPlayerName;
	private JLabel lblNumGP;
	private JLabel lblNumGW;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreboardFrame frame = new ScoreboardFrame();
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
	public ScoreboardFrame() {
		setUndecorated(true);
		setForeground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 463);
		sBoard = new JPanel();
		sBoard.setBackground(Color.BLACK);
		sBoard.setForeground(Color.GREEN);
		sBoard.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(sBoard);

		btnExit = new JButton("Exit");
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.RED);
		btnExit.setFont(new Font("OCR A Extended", Font.PLAIN, 11));

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		lblPlayerName = new JLabel("Player name");
		lblPlayerName.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		lblPlayerName.setForeground(Color.GREEN);
		sBoard.add(lblPlayerName);

		lblNumGP = new JLabel("Number games played");
		lblNumGP.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		lblNumGP.setForeground(Color.GREEN);
		sBoard.add(lblNumGP);

		lblNumGW = new JLabel("Number of games won");
		lblNumGW.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		lblNumGW.setForeground(Color.GREEN);
		sBoard.add(lblNumGW);

	} // ScoreboardFrame()

	protected void populateBoard(DoublyLinkedList<Player> players) {
		sBoard.setLayout(new GridLayout(players.getLength() + 2, 3));
		sBoard.removeAll();
		sBoard.add(lblPlayerName);
		sBoard.add(lblNumGP);
		sBoard.add(lblNumGP);
		sBoard.add(lblNumGW);

		for (int i = 0; i < players.getLength(); ++i) {
			JLabel pName = new JLabel(players.getElementAt(i).name);
			JLabel pNumGP = new JLabel(Integer.toString(players.getElementAt(i).getNumGP()));
			JLabel pNumGW = new JLabel(Integer.toString(players.getElementAt(i).getNumGW()));
			pName.setForeground(Color.GREEN);
			pNumGP.setForeground(Color.GREEN);
			pNumGW.setForeground(Color.GREEN);
			sBoard.add(pName);
			sBoard.add(pNumGP);
			sBoard.add(pNumGW);
		}
		btnExit.setPreferredSize(new Dimension(10, 10));
		sBoard.add(btnExit);

	} // populateBoard(DoublyLinkedList<Player>)
} // ScoreboardFrame
