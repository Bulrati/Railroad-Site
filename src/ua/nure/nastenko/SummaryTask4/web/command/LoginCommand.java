package ua.nure.nastenko.SummaryTask4.web.command;

import java.io.IOException;

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
public class LoginCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		// obtain login and password from the request
		DAORailroad dao = DAORailroad.getDAORailroad();
		String login = request.getParameter("login");
		LOG.trace("Request parameter: loging THIS IS MY CHANGE --> " + login);

		String password = request.getParameter("password");

		// error handler
		String errorMessage = null;
		String forward = Path.PAGE_ERROR;

		if (login == null || password == null || login.isEmpty()
				|| password.isEmpty()) {
			errorMessage = "Login/password cannot be empty";
			request.setAttribute("errorMessage", errorMessage);
			LOG.error("errorMessage --> " + errorMessage);
			return forward;
		}

		User user = dao.getUser(login);
		// User user = new User("J", "n", "B", "B", "B");
		if (user != null) {
			LOG.trace("Found in DB: user --> " + user.toString());
		}

		if (user == null || !password.equals(user.getPassword())) {
			errorMessage = "Cannot find user with such login/password";
			request.setAttribute("errorMessage", errorMessage);
			LOG.error("errorMessage --> " + errorMessage);
			return forward;
		} else {
			String userRole = user.getRole();
			LOG.trace("userRole --> " + userRole);

			if (userRole.equals("1")) {
				forward = Path.PAGE_ADMIN;
			}

			if (userRole.equals("2")) {
				forward = Path.COMMAND_LIST_STATIONS;
			}

			if (password.equals("guest")) {
				forward = Path.PAGE_GUEST;
			}

			session.setAttribute("user", user);
			LOG.trace("Set the session attribute: user --> " + user.toString());

			session.setAttribute("userRole", userRole);
			LOG.trace("Set the session attribute: userRole --> " + userRole);

			LOG.info("User " + user + " logged as "
					+ userRole.toString().toLowerCase());
		}

		LOG.debug("Command finished");
		return forward;
	}

}