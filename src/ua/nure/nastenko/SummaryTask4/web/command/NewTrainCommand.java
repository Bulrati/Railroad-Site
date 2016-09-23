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
public class NewTrainCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(NewTrainCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		LOG.debug("Command starts");
		String forward;
		DAORailroad dao = DAORailroad.getDAORailroad();
		String trainNumber = request.getParameter("new_train");
		LOG.trace("Request parameter: loging THIS IS MY CHANGE --> " + trainNumber);

		boolean flag = dao.addTrain(Integer.parseInt(trainNumber));
		if(flag){
			forward = Path.PAGE_SUCCESS;
		}
		else{
			forward = Path.PAGE_ERROR;
		}
		
		
		LOG.debug("Command finished");
		return forward;
	}

}