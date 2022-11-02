package linked_data_structures;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;

public class HangmanIntroFrame extends JFrame implements ActionListener {
	private String name;
	private JPanel contentPane;
	JLabel lblWelcome;
	JLabel lblPick;
	JButton btnNewPlayer;
	JButton btnReturningPlayer;
	String[] names = { "John", "Bill", "Tom", "Ron", "Sam" }; // Hardcoded names for time-being
	static HangmanIntroFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new HangmanIntroFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	} // main

	/**
	 * Create the frame.
	 */
	public HangmanIntroFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 415);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblWelcome = new JLabel("Welcome to Hangman!");
		lblWelcome.setBounds(176, 66, 303, 37);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.add(lblWelcome);

		lblPick = new JLabel("Are you a");
		lblPick.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPick.setBounds(270, 152, 115, 37);
		contentPane.add(lblPick);

		btnNewPlayer = new JButton("New Player");
		btnNewPlayer.setForeground(new Color(0, 0, 139));
		btnNewPlayer.setBackground(new Color(0, 0, 139));
		btnNewPlayer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewPlayer.setBounds(103, 245, 140, 27);
		contentPane.add(btnNewPlayer);
		btnNewPlayer.addActionListener(this);

		btnReturningPlayer = new JButton("Returning Player");
		btnReturningPlayer.setBackground(new Color(0, 0, 128));
		btnReturningPlayer.setForeground(new Color(0, 0, 139));
		btnReturningPlayer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnReturningPlayer.setBounds(406, 245, 140, 27);
		contentPane.add(btnReturningPlayer);
		btnReturningPlayer.addActionListener(this);
	} // HangmanIntroFrame()

	private void enterName() {
		JPanel newPlayerPanel = new JPanel();
		String[] buttonOp = { "Confirm" };
		JLabel lblNameQ = new JLabel("Please enter your name: ");
		JTextField fldName = new JTextField(20);
		newPlayerPanel.add(lblNameQ);
		newPlayerPanel.add(fldName);
		int option = JOptionPane.showOptionDialog(null, newPlayerPanel, "Enter Name to Start", JOptionPane.NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, buttonOp, buttonOp[0]);
		while (fldName.getText().length() == 0 && option == 0) {
			JOptionPane.showMessageDialog(this, "Please enter a name", "Missing name", JOptionPane.ERROR_MESSAGE);
			// Ask for input once more
			option = JOptionPane.showOptionDialog(null, newPlayerPanel, "Enter Name to Start", JOptionPane.NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, buttonOp, buttonOp[0]);

		}
		// if user confirms
		if (option == 0) {
			setName(fldName.getText());
		}
		// If user presses on 'X' to quit at any time, game closes
		if (option != 0) {
			JOptionPane.showMessageDialog(null, "Come back again!");
			System.exit(-1);
		}
	} // enterName()

	private void pickName() {
		JPanel existingPlayerPanel = new JPanel();
		String[] buttonOp = { "Confirm" };
		JLabel lblNameQ = new JLabel("Please choose your name ");
		JScrollBar scroll = new JScrollBar();
		JComboBox cmbxNameList = new JComboBox(names);
		cmbxNameList.add(scroll);

		existingPlayerPanel.add(lblNameQ);
		existingPlayerPanel.add(cmbxNameList);
		int option = JOptionPane.showOptionDialog(null, existingPlayerPanel, "Enter Name to Start",
				JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonOp, buttonOp[0]);

		if (option == 0) {
			setName(cmbxNameList.getSelectedItem().toString());
		}
		// If user presses on 'X' to quit at any time, game closes
		if (option != 0) {
			JOptionPane.showMessageDialog(null, "Come back again!");
			System.exit(-1);
		}
	} // pickName()

	public void setName(String n) {
		name = n;
	} // setName(String)

	public String getName() {
		return name;
	} // getName()

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewPlayer) {
			enterName();
			// hides and disposes start frame
			frame.setVisible(false);
			frame.dispose();
			// starts and displays the game frame
			HangmanFrame gameFrame = new HangmanFrame();
			gameFrame.setVisible(true);
		} else if (e.getSource() == btnReturningPlayer) {
			pickName();
			// hides and disposes start frame
			frame.setVisible(false);
			frame.dispose();
			// starts and displays the game frame
			HangmanFrame gameFrame = new HangmanFrame();
			gameFrame.setVisible(true);
		}
	} // actionPerformed(ActionEvent)
} // HangmanIntroFrame
