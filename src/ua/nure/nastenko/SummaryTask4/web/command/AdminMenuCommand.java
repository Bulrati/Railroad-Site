package ua.nure.nastenko.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.nastenko.SummaryTask4.Path;
import ua.nure.nastenko.SummaryTask4.db.DAORailroad;
import ua.nure.nastenko.SummaryTask4.db.entity.User;

import org.apache.log4j.Logger;

/**
 * Login command.
 * 
 * @author S.Nastenko
 * 
 */
public class AdminMenuCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(AdminMenuCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		LOG.debug("Command starts");

		LOG.debug("Command finished");
		return Path.PAGE_ADMIN;
	}

}