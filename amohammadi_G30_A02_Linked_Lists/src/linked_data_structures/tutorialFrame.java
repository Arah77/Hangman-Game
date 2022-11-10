package linked_data_structures;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * @author Arah Mohammadi
 * @Description: This class contains the code for the Hangman game tutorial
 *               panel
 *
 */
public class tutorialFrame extends JPanel {

	/**
	 * Create the frame.
	 */
	public tutorialFrame() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblRules = new JLabel(
				"A word is randomly generated. Guess a letter, if it is correct, it will show up in the word box. If it is incorrect, a piece of the hangman will appear. Get 6 incorrect guesses and you loose ");
		GridBagConstraints gbc_lblRules = new GridBagConstraints();
		gbc_lblRules.insets = new Insets(0, 0, 5, 5);
		gbc_lblRules.gridx = 0;
		gbc_lblRules.gridy = 0;
		add(lblRules, gbc_lblRules);

		JLabel lblConfig = new JLabel(
				"Pick new game to start a new game or quit to leave the game which will automatically save your current one");
		GridBagConstraints gbc_lblConfig = new GridBagConstraints();
		gbc_lblConfig.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfig.gridx = 0;
		gbc_lblConfig.gridy = 1;
		add(lblConfig, gbc_lblConfig);

		JLabel lblScore = new JLabel("Click on the Scoreboard button to view the score of all registered players");
		GridBagConstraints gbc_lblScore = new GridBagConstraints();
		gbc_lblScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblScore.gridx = 0;
		gbc_lblScore.gridy = 2;
		add(lblScore, gbc_lblScore);

		JLabel lblHint = new JLabel(
				"Use the hint button to obtain a letter in the word. Use it wisely as you only have one hint per word!");
		GridBagConstraints gbc_lblHint = new GridBagConstraints();
		gbc_lblHint.insets = new Insets(0, 0, 5, 5);
		gbc_lblHint.gridx = 0;
		gbc_lblHint.gridy = 3;
		add(lblHint, gbc_lblHint);
	} // tutorialFrame()

} // tutorialFrame
