package ua.nure.nastenko.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.nastenko.SummaryTask4.Path;
import ua.nure.nastenko.SummaryTask4.db.DAORailroad;
import ua.nure.nastenko.SummaryTask4.db.info.RoadInfo;

/**
 * List of stations command.
 * 
 * @author S.Nastenko
 * 
 */
public class ListRoutesCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(ListRoutesCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		// obtain login and password from the request
		DAORailroad dao = DAORailroad.getDAORailroad();
		
		String firstStation = request.getParameter("first_station");
		String lastStation = request.getParameter("last_station");
		
		List<RoadInfo> routes = dao.getRoadInfoByStations(firstStation, lastStation);
		
			session.setAttribute("resultRoutes", routes);
			LOG.trace("Set the session attribute: stationsList --> " + routes.toString());

		

		LOG.debug("Command finished");
		return Path.PAGE_CLIENT_ROUTES_RESULTS;
	}

}