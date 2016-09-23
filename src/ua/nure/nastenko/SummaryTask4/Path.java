package ua.nure.nastenko.SummaryTask4;

public class Path {
	//files
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_ERROR = "/WEB-INF/jsp/error.jsp";
	public static final String PAGE_SUCCESS = "/WEB-INF/jsp/success.jsp";
	public static final String PAGE_ADMIN = "/WEB-INF/jsp/admin/login_admin.jsp";
	public static final String PAGE_CLIENT_CHOOSE_OF_STATIONS = "/WEB-INF/jsp/client/choose_of_stations.jsp";
	public static final String PAGE_CLIENT_ROUTES_RESULTS = "/WEB-INF/jsp/client/routes_results.jsp";
	public static final String PAGE_GUEST = "/WEB-INF/jsp/guest/login_guest.jsp";
	public static final String PAGE_NEW_TRAIN = "/WEB-INF/jsp/admin/new_train.jsp";
	
	// commands
	public static final String COMMAND_LIST_STATIONS = "/mainController?command=listStations";
	public static final String COMMAND_LIST_RESULTS_ROUTES = "/mainController?command=listResultsRoutes";
}
