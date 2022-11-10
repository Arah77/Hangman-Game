package linked_data_structures;

import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
//import linked_data_structures.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.io.*;
import java.util.Random;

import javax.swing.border.BevelBorder;

/**
 * @author Arah Mohammadi
 * @Description: This class contains the code for the Hangman game frame
 *
 */

public class HangmanFrame extends JFrame implements ActionListener, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Scoreboard score = new Scoreboard();;
	HangmanGame game;
	private JPanel contentPane;
	private JTextField fldGuess;
	private JLabel[] letters;
	private JMenuBar menuBar;
	private JMenu mnMenu;
	private JMenuItem mntmNewGame;
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
	private JLabel lblWelcome;
	private boolean alreadyShown = false;
	private ScoreboardFrame scoreFrame = new ScoreboardFrame();
	Player player;

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
	} // main(String[])

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
		mntmNewGame.addActionListener(this);

		mntmQuitGame = new JMenuItem("Quit Game");
		mnMenu.add(mntmQuitGame);
		mntmQuitGame.addActionListener(this);

		mntmTuto = new JMenuItem("Tutorial");
		mnMenu.add(mntmTuto);
		mntmTuto.addActionListener(this);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 139));
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		btnScoreboard = new JButton("Scoreboard");
		btnScoreboard.setBackground(new Color(224, 255, 255));
		btnScoreboard.setForeground(new Color(0, 0, 139));
		btnScoreboard.setBounds(876, 11, 123, 23);
		btnScoreboard.addActionListener(this);

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

		lblWelcome = new JLabel("");
		lblWelcome.setForeground(new Color(0, 0, 0));
		lblWelcome.setFont(new Font("Century", Font.ITALIC, 32));
		lblWelcome.setBounds(15, 64, 636, 77);
		contentPane.add(lblWelcome);
	} // HangmanFrame()

	// creates first ever word box
	protected void createFirstWordBox() {
		if (!game.populateWordList()) {
			JOptionPane.showMessageDialog(this, "Error reading words list file, please quit or try again",
					"Error reading file", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		} else {
			game.populateLettersList();
			showNewWordBoxComponents();
		}
	} // createWordBox()

	// creates word box for next word after first word has been done
	protected void createNextWordBox() {
		game.populateLettersList();
		showNewWordBoxComponents();
	} // createNextWordBox()

	protected void showNewWordBoxComponents() {
		int numLet = game.getLettersList().getLength();
		letters = new JLabel[numLet];

		for (int i = 0; i < game.getLettersList().getLength(); ++i) {
			if (Character.toString(game.getLettersList().getElementAt(i)).equals(" ")) {
				++game.getPlayer().numCorrect;
				letters[i] = new JLabel(" ");
			} else {
				letters[i] = new JLabel("_");
			}
			wordPanel.add(letters[i]);
			letters[i].setFont(new Font("Century", Font.PLAIN, 50));
		} // for
	} // showNewWordBoxComponents()

	protected void showReturningWordBoxComponents() {
		int numLet = game.getLettersList().getLength();
		letters = new JLabel[numLet];

		for (int i = 0; i < game.getLettersList().getLength(); ++i) {

			if (Character.toString(game.getLettersList().getElementAt(i)).equals(" ")) {
				++game.getPlayer().numCorrect;
				letters[i] = new JLabel(" ");
			}
			letters[i] = new JLabel("_");
			for (int j = 0; j < game.guessedLetters.getLength(); ++j) {

				if (game.getLettersList().getElementAt(i) == game.guessedLetters.getElementAt(j)) {
					letters[i] = new JLabel(Character.toString(game.getLettersList().getElementAt(i)));
					continue;
				} // if
			} // for
		} // for

		for (int i = 0; i < game.getLettersList().getLength(); ++i) {
			wordPanel.add(letters[i]);
			letters[i].setFont(new Font("Century", Font.PLAIN, 50));
		} // for
	} // showReturningWordBoxComponents()

	// creates word box for returning player
	protected void createReturningWordBox() {
		lblWelcome.setText("Welcome back " + game.getPlayer().name);
		showReturningWordBoxComponents();
	} // createReturningWordBox()

	// display win/loss
	public void showWinLoss() {
		boolean hasWon = game.winLoss(game.getPlayer().numErrors, game.getPlayer().numCorrect);
		if (hasWon) {
			score.gamePlayed(game.getPlayer(), true);
			JOptionPane.showMessageDialog(null, "Congratulations, you have won!", "Won", JOptionPane.PLAIN_MESSAGE);
		} else {
			score.gamePlayed(game.getPlayer(), false);
			JOptionPane.showMessageDialog(null,
					"You've lost:( The word was " + "\"" + game.getWord() + "\"" + " try again!", "Lost",
					JOptionPane.ERROR_MESSAGE);
		}
	} // showWinLoss()

	// a new player
	protected void registerPlayer(String name) {
		lblWelcome.setText("Welcome " + name);
		game = new HangmanGame();
		game.setPlayer(name);
		score.addPlayer(game.getPlayer());
		game.getPlayer().setGame(game);
	} // registerPlayer(String)

	// a returning player
	protected void returningPlayer(String name) {
		for (int i = 0; i < score.playersList.getLength(); ++i) {
			if (score.playersList.getElementAt(i).name.equals(name)) {
				player = score.playersList.getElementAt(i);

			} // if
		} // for
		if (player != null) {
			game = player.getGame();
		} // if
		numErr = player.getNumErr();
		if (numErr == 0) {
			lblManImg.setIcon(new ImageIcon("hangman1.png"));
		} else
			lblManImg.setIcon(new ImageIcon("hangman" + numErr + ".png"));
		taAttempts.setText(player.getErrors());
	} // returningPlayer(String)

	protected void btnGuess_actionPerformed() {
		boolean beenGuessed = false;
		// confirms input format
		if (fldGuess.getText().length() != 0 && game.checkNum(fldGuess.getText()) == false
				&& !fldGuess.getText().equals(" ")) {

			if (fldGuess.getText().length() > 0 && fldGuess.getText().length() < 2) {
				lblError.setText(""); // resets error message
				String tempGuess = fldGuess.getText().toLowerCase(); // sets guess to lower case
				char guessedLetter = tempGuess.charAt(0); // takes letter from guess
				game.guessedLetters.add(guessedLetter); // adds letter to SinglyLinkedList of all letters guessed

				if (game.checkLetter(guessedLetter)) { // if the guessed letter is correct
					game.guessedIndices = game.findLetIndex(guessedLetter); // find where it is correct

					for (int i = 0; i < game.guessedIndices.size(); ++i) {
						if (!(letters[game.guessedIndices.get(i)].getText().equals("_"))) {
							lblError.setText("You have already guessed that letter");
						} else
							++game.getPlayer().numCorrect;
						letters[game.guessedIndices.get(i)].setText(Character.toString(guessedLetter)); // show letter
																										// in
						// according index
					} // for
					fldGuess.setText("");
				} else {
					// updates hangman image
					if (taAttempts.getText().isEmpty()) {
						++game.getPlayer().numErrors;
						lblManImg.setIcon(new ImageIcon("hangman" + (game.getPlayer().numErrors + 1) + ".png"));
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
							++game.getPlayer().numErrors;
							lblManImg.setIcon(new ImageIcon("hangman" + (game.getPlayer().numErrors + 1) + ".png"));
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
	} // btnGuess_actionPerformed

	public void delWord() {
		game.removeWord();
	} // delWord()

	protected void resetBox() {
		for (int i = 0; i < letters.length; ++i) {
			wordPanel.remove(letters[i]);
		} // for
		game.clearLists();
	} // resetBox()

	protected void btnScoreboard_actionPerformed() {
		scoreFrame.setVisible(true);
		scoreFrame.populateBoard(game.sort(score.playersList));
	} // btnScoreboard_actionPerformed()

	protected void btnHint_actionPerformed() {
		boolean guessed;
		char hintLet;
		int index;
		// checks if index to give hint in word is appropriate according to if it has
		// already been guessed or if it's a space
		do {
			guessed = false;
			index = game.selectRanIndex();
			hintLet = game.lettersList.getElementAt(index);
			for (int l = 0; l < game.guessedLetters.getLength(); ++l) {
				if ((game.guessedLetters.getElementAt(l) == hintLet) || game.guessedLetters.getElementAt(l) == ' ') {
					guessed = true;
					break;
				} // if
			} // for
		} while (guessed);

		// display hint at index
		for (int i = 0; i < game.lettersList.getLength(); ++i) {
			if (game.lettersList.getElementAt(i) == hintLet) {
				letters[i].setForeground(Color.RED);
				letters[i].setText(Character.toString(hintLet));
				++game.getPlayer().numCorrect;
			} // if
		}

	} // btnHint_actionPerformed()

	protected int getPlayerAmount() {
		return score.playersList.getLength();
	} // getSavedPlayers()

	protected Player nextPlayer(int index) {
		return score.getNextPlayer(index);
	} // nextPlayer(index)

	// resets game for next round
	private void resetGame() {
		resetBox();
		delWord();
		createNextWordBox();
		resetIndeces();
		btnHint.setEnabled(true);
		game.getPlayer().setNumErr(0);
		game.getPlayer().setNumCorr(0);
		taAttempts.setText("");
		lblManImg.setIcon(new ImageIcon("hangman1.png"));
	} // resetGame()

	private void resetIndeces() {
		for (int i = 0; i < game.guessedIndices.size(); i++) {
			game.guessedIndices.remove(i);
		}
	} // resetIndeces

	protected void retreiveGame() throws ClassNotFoundException, IOException {
		score.Deserialize();
	} // retreiveGame()

	protected void save() throws IOException {
		game.getPlayer().setErrors(taAttempts.getText());
		score.Serialize();
	} // save()

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGuess) {
			btnGuess_actionPerformed();

		} else if (e.getSource() == btnHint) {
			btnHint_actionPerformed();
			btnHint.setEnabled(false);
		} else if (e.getSource() == mntmQuitGame) {
			try {
				save();
			} catch (IOException q) {
				q.printStackTrace();
			} // try/catch
			JOptionPane.showMessageDialog(null, "Thanks for playing!");
			System.exit(-1);
		} else if (e.getSource() == btnScoreboard) {
			btnScoreboard_actionPerformed();
		} else if (e.getSource() == mntmTuto) {
			JOptionPane.showMessageDialog(this, new tutorialFrame(), "How to play", JOptionPane.PLAIN_MESSAGE);
		} else if (e.getSource() == mntmNewGame) {
			resetGame();
			new HangmanGame();
			new HangmanFrame();
		} // else if

		try {
			if (game.getPlayer().getNumErr() == 6 || game.getPlayer().getNumCorr() == game.lettersList.getLength()) {
				showWinLoss();
				resetGame();
			} // if
		} catch (IndexOutOfBoundsException q) {
			JOptionPane.showMessageDialog(null, "Congratulations you have completed the game!");
			System.exit(-1);
		} // try/catch

	} // actionPerformed(ActionEvent)
} // HangmanFrame
