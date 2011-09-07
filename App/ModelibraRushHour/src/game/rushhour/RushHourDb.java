package game.rushhour;

import game.Game;
import game.GameConfig;
import game.PersistentGame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.config.DomainConfig;

/**
 * RushHour db.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-21
 */
public class RushHourDb {

	private static Log log = LogFactory.getLog(RushHourDb.class);

	private Game game;

	private PersistentGame persistentGame;

	private RushHour rushHour;

	/**
	 * Constructs the RushHour db.
	 */
	public RushHourDb() {
		super();
		try {
			GameConfig gameConfig = new GameConfig();
			DomainConfig domainConfig = gameConfig.getDomainConfig();
			game = new Game(domainConfig);
			persistentGame = new PersistentGame(game);
			rushHour = game.getRushHour();
		} catch (Exception e) {
			log.error("Error in RushHourDb.constructor: " + e.getMessage());
		}
	}

	public Game getGame() {
		return game;
	}

	public RushHour getRushHour() {
		return rushHour;
	}

	public void close() {
		if (persistentGame != null) {
			persistentGame.close();
		}
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public static void main(String[] args) {
		RushHourDb rushHourDb = null;
		try {
			rushHourDb = new RushHourDb();

			rushHourDb.close();
		} catch (Exception e) {
			log.error("Error in RushHourDb.main: " + e.getMessage());
			rushHourDb.close();
		}
	}

}