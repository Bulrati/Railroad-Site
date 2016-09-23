package ua.nure.nastenko.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.nastenko.SummaryTask4.web.command.LogoutCommand;

/**
 * Holder for all commands.<br/>
 * 
 * @author D.Kolesnikov
 * 
 */
public class CommandContainer {
	
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<String, Command>();
	
	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("test", new Test());
		commands.put("logout", new LogoutCommand());
		commands.put("listStations", new ListStationsCommand());
		commands.put("listRoutes", new ListRoutesCommand());
		commands.put("choose_admin_page", new ChooseAdminPageCommand());
		commands.put("admin_menu", new AdminMenuCommand());
		commands.put("new_rain", new NewTrainCommand());
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
	
}