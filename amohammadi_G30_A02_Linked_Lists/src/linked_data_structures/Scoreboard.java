package linked_data_structures;

import java.io.*;
//import linked_data_structures.*;

/**
 * @author Arah Mohammadi
 * @Description: This class contains the code for the Hangman game scoreboard
 *               functionality
 *
 */

public class Scoreboard implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DoublyLinkedList<Player> playersList = new DoublyLinkedList<Player>();;
	private int numPlayers;

	public Scoreboard() {
		try {
			Deserialize();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			System.out.print("Hello");
		}
		numPlayers = 0;
	} // Scoreboard()

	protected void addPlayer(Player player) {
		playersList.add(player);
	} // addPlayer(Player)

	protected void gamePlayed(Player player, boolean wOl) {
		player.setNumGP(player.getNumGP() + 1);
		if (wOl) {
			player.setNumGW(player.getNumGW() + 1);
		} // if
	} // gamePlayed(Player, boolean)

	protected void Serialize() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("hangman.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);

		out.writeObject(playersList);
		out.close();
		fileOut.close();
	} // Serialize()

	protected void Deserialize() throws IOException, ClassNotFoundException {
		try {
			FileInputStream inpStream = new FileInputStream("hangman.ser");
			if (inpStream.available() > 0) {
				ObjectInputStream objInp = new ObjectInputStream(inpStream);

				playersList = (DoublyLinkedList<Player>) objInp.readObject();
				objInp.close();
				inpStream.close();
			}
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		} catch (IOException e) {
			e.getStackTrace();
		} catch (ClassNotFoundException e) {
			e.getStackTrace();
		} // try/catch
	} // Deserialize()

	protected Player getNextPlayer(int index) {
		try {
			return playersList.getElementAt(index);
		} catch (IndexOutOfBoundsException e) {
			return null;
		} // try/catch
	} // getNextPlayer(int)
} // Scoreboard
