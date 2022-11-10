package linked_data_structures;

import java.io.*;

public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected int numGamesPlayed;
	protected int numGamesWon;
	protected HangmanGame game;
	protected int numErrors;
	protected int numCorrect;
	protected String errors;

	public Player() {
		name = "Unknown";
		numGamesPlayed = 0;
		numGamesWon = 0;
	} // Player()

	public Player(String pName) {
		name = pName;
	} // Player(String)

	protected void setName(String n) {
		name = n;
	} // setName(String)

	protected String getName() {
		return name;
	} // getName()

	protected void setNumGP(int numGP) {
		numGamesPlayed = numGP;
	} // setNumGP(int)

	protected int getNumGP() {
		return numGamesPlayed;
	} // getNumGP()

	protected void setNumGW(int numGW) {
		numGamesWon = numGW;
	} // setNumGW(int)

	protected int getNumGW() {
		return numGamesWon;
	} // getNumGW()

	protected void setGame(HangmanGame currGame) {
		game = currGame;
	} // setGame(HangmanGame)

	protected HangmanGame getGame() {
		return game;
	} // getGame()

	protected void setNumErr(int err) {
		numErrors = err;
	} // setNumErr(int)

	protected int getNumErr() {
		return numErrors;
	} // getNumErr()

	protected void setNumCorr(int corr) {
		numCorrect = corr;
	} // setNumCorr(int)

	protected int getNumCorr() {
		return numCorrect;
	} // getNumCorr

	protected void setErrors(String err) {
		errors = err;
	} // setErrors(String)

	protected String getErrors() {
		return errors;
	} // getErrors();
} // Player
