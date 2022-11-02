package linked_data_structures;

import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.SoftBevelBorder;

import java.util.Random;

import javax.swing.border.BevelBorder;

public class HangmanFrame extends JFrame implements ActionListener {

	HangmanGame game = new HangmanGame();
	private JPanel contentPane;
	private JTextField fldGuess;
	private JLabel[] letters;
	private JMenuBar menuBar;
	private JMenu mnMenu;
	private JMenuItem mntmNewGame;
	private JMenuItem mntmSaveGame;
	private JMenuItem mntmQuitGame;
	private JMenuItem mntmTuto;
	private JButton btnScoreboard;
	private JButton btnHint;
	private JPanel wordPanel;
	private JLabel lblAttempts;
	private JButton btnGuess;
	private JLabel lblError;
	private JTextArea taAttempts;
	private JLabel lblManImg;
	private int numCorr = 0;
	private int numErr = 0;
	private boolean alreadyShown = false;
//	ArrayList<Character> playerGuessedLetters = new ArrayList<Character>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HangmanFrame frame = new HangmanFrame();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HangmanFrame() {
		setTitle("Hangman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1025, 676);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		mntmNewGame = new JMenuItem("New Game");
		mnMenu.add(mntmNewGame);

		mntmSaveGame = new JMenuItem("Save Game");
		mnMenu.add(mntmSaveGame);

		mntmQuitGame = new JMenuItem("Quit Game");
		mnMenu.add(mntmQuitGame);
		mntmQuitGame.addActionListener(this);

		mntmTuto = new JMenuItem("Tutorial");
		mnMenu.add(mntmTuto);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 139));
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		btnScoreboard = new JButton("Scoreboard");
		btnScoreboard.setBackground(new Color(224, 255, 255));
		btnScoreboard.setForeground(new Color(0, 0, 139));
		btnScoreboard.setBounds(876, 11, 123, 23);

		btnHint = new JButton("Need a hint?");
		btnHint.setBackground(new Color(224, 255, 255));
		btnHint.setForeground(new Color(0, 0, 139));
		btnHint.setBounds(876, 53, 123, 23);
		btnHint.addActionListener(this);

		wordPanel = new JPanel();
		wordPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		wordPanel.setBackground(new Color(230, 230, 250));
		wordPanel.setForeground(new Color(192, 192, 192));
		wordPanel.setBounds(15, 200, 530, 66);

		taAttempts = new JTextArea();
		taAttempts.setForeground(new Color(0, 0, 0));
		taAttempts.setBackground(new Color(255, 255, 255));
		taAttempts.setBounds(15, 444, 192, 160);
		taAttempts.setFont(new Font("Century", Font.PLAIN, 25));
		taAttempts.setEditable(false);

		lblAttempts = new JLabel("Attempts");
		lblAttempts.setForeground(new Color(0, 0, 139));
		lblAttempts.setBounds(15, 418, 55, 14);

		fldGuess = new JTextField();
		fldGuess.setBounds(15, 328, 105, 20);
		fldGuess.setColumns(10);

		btnGuess = new JButton("Guess!");
		btnGuess.setBackground(new Color(224, 255, 255));
		btnGuess.setForeground(new Color(0, 0, 139));
		btnGuess.setBounds(130, 327, 77, 23);
		btnGuess.addActionListener(this);
		contentPane.setLayout(null);
		contentPane.add(wordPanel);
		contentPane.add(fldGuess);
		contentPane.add(btnGuess);
		contentPane.add(btnHint);
		contentPane.add(btnScoreboard);
		contentPane.add(lblAttempts);
		contentPane.add(taAttempts);

		lblError = new JLabel("");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblError.setBounds(395, 328, 257, 29);
		contentPane.add(lblError);

		lblManImg = new JLabel();
		lblManImg.setBounds(321, -271, 1725, 1253);
		lblManImg.setIcon(new ImageIcon("hangman1.png"));
		contentPane.add(lblManImg);

		createWordBox();
	} // HangmanFrame()

	private void createWordBox() {
		if (!game.populateWordList()) {
			JOptionPane.showMessageDialog(this, "Error reading words list file, please quit or try again",
					"Error reading file", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		} else {
			game.populateLettersList();
			int numLet = game.getLettersList().getLength();
			letters = new JLabel[numLet];

			for (int i = 0; i < game.getLettersList().getLength(); ++i) {
				if (Character.toString(game.getLettersList().getElementAt(i)).equals(" ")) {
					++numCorr;
//					letters = game.changelength(letters);
					letters[i] = new JLabel(" ");
//					game.removeEl(i);
				} else {
					letters[i] = new JLabel("_");
				}
				wordPanel.add(letters[i]);
				letters[i].setFont(new Font("Century", Font.PLAIN, 50));
			}

		}
	} // createWordBox()

	public void btnGuess_actionPerformed() {
		boolean beenGuessed = false;
		// confirms input format
		if (fldGuess.getText().length() != 0 && game.checkNum(fldGuess.getText()) == false
				&& !fldGuess.getText().equals(" ")) {
			
			if (fldGuess.getText().length() > 0 && fldGuess.getText().length() < 2) {
				lblError.setText(""); // resets error message
				char guessedLetter = fldGuess.getText().charAt(0); // takes letter from input
				game.guessedLetters.add(guessedLetter); // adds letter to SinglyLinkedList of all letters guessed

				if (game.checkLetter(guessedLetter)) { // if the guessed letter is correct
					ArrayList<Integer> guessedIndices = game.findLetIndex(guessedLetter); // find where it is correct

					for (int i = 0; i < guessedIndices.size(); ++i) {
						if (!(letters[guessedIndices.get(i)].getText().equals("_") && letters[guessedIndices.get(i)]
								.getText().equals(letters[guessedIndices.get(i)].getText()))) {
							lblError.setText("You have already guessed that letter");
						} else
							++numCorr;
						letters[guessedIndices.get(i)].setText(Character.toString(guessedLetter)); // show letter in
																									// according index
					} // for

					fldGuess.setText("");
				} else {
					// updates hangman image
					if (taAttempts.getText().isEmpty()) {
						++numErr;
						lblManImg.setIcon(new ImageIcon("hangman" + (numErr + 1) + ".png"));
						taAttempts.append(fldGuess.getText() + " ");
						fldGuess.setText("");
					} else {
						// verifies letter has not already been guessed
						for (int i = game.guessedLetters.getLength() - 1; i > 0; --i) {
							if (game.guessedLetters.getElementAt(i) == guessedLetter) {
								lblError.setText("You have already guessed that letter");
								fldGuess.setText("");
								beenGuessed = true;
							} // if
						} // for
						if (!beenGuessed) {
							++numErr;
							lblManImg.setIcon(new ImageIcon("hangman" + (numErr + 1) + ".png"));
							taAttempts.append(fldGuess.getText() + " ");
							fldGuess.setText("");
						} // if
					} // else
				} // else
			} // if guess is one letter
			else {
				lblError.setText("Please enter a single letter");
			} // else
		} // if text is entered
		else {
			lblError.setText("Please enter a letter");
		} // else
			// win/loss conditions
		if (numErr == 6) {
			JOptionPane.showMessageDialog(null, "You've lost:(, try again!", "Lost", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		if (numCorr == game.getLettersList().getLength()) {
			JOptionPane.showMessageDialog(null, "Congratulations, you have won!", "Won", JOptionPane.PLAIN_MESSAGE);
			System.exit(-1);
		}
	}

	public void btnHint_actionPerformed() {
		int index = game.selectRanIndex();
		char hintLet = game.lettersList.getElementAt(index);
		for (int l = 0; l < letters.length; ++l) {
			for (int j = 0; j < game.guessedLetters.getLength(); ++j) {
				// if letter is already displayed || it is not an underscore || there is a space
				// find a new index
				if (Character.toString(game.guessedLetters.getElementAt(j)).equals(letters[l].getText())
						|| !(letters[j].getText().equals("_")) || !letters[j].getText().equals(" ")) {

					index = game.selectRanIndex();
					hintLet = game.lettersList.getElementAt(index);
				} // if

			} // for
		} // for

		// display hint at index
		for (int i = 0; i < game.lettersList.getLength(); ++i) {
			if (game.lettersList.getElementAt(i) == hintLet) {
				letters[i].setForeground(new Color(0, 80, 80));
				letters[i].setText(Character.toString(hintLet));
				++numCorr;
			} // if
		}

	} // btnHint_actionPerformed()

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGuess) {
			btnGuess_actionPerformed();
		} else if (e.getSource() == btnHint) {
			btnHint_actionPerformed();
			btnHint.setEnabled(false);
		} else if (e.getSource() == mntmQuitGame) {
			JOptionPane.showMessageDialog(null, "Thanks for playing!");
			System.exit(-1);
		}
	} // actionPerformed(ActionEvent)
} // HangmanFrame
