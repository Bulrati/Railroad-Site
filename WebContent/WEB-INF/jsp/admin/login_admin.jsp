<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<c:set var="title" value="Login" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>

	<%--=======================================================s==================== 
Here we use a table layout.
Class page corresponds to the '.page' element in included CSS document.
===========================================================================--%>
	<table id="main-container">

		<%--=========================================================================== 
This is the HEADER, containing a top menu.
header.jspf contains all necessary functionality for it.
Just included it in this JSP document.
===========================================================================--%>

		<%-- HEADER --%>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<%-- HEADER --%>

		<%--=========================================================================== 
This is the CONTENT, containing the main part of the page.
===========================================================================--%>
		<tr>
			<td class="content center">
				<%-- CONTENT --%> <%--=========================================================================== 
Defines the web form.
===========================================================================--%>
				<form id="login_form" action="mainController" method="post">

					<%--=========================================================================== 
Hidden field. In the query it will act as command=login.
The purpose of this to define the command name, which have to be executed 
after you submit current form. 
===========================================================================--%>
					<input type="hidden" name="command" value="choose_admin_page" /> <input
						type="submit" name="new_route_page" value="New route"> <br />
					<input type="submit" name="delete_route_page" value="Delete route">
					<br /> <input type="submit" name="redact_route_page"
						value="Redact route"> <br /> <input type="submit"
						name="new_train_page" value="New train"> <br /> <input
						type="submit" name="delete_train_page" value="Delete train">
					<br /> <input type="submit" name="add_train_to_route_page"
						value="Add train to route"> <br /> <input type="submit"
						name="add_Station_page" value="Add station"> <br /> <input
						type="submit" name="delete_station_page" value="Delete station">

				</form> <%-- CONTENT --%>

			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</table>
</body>
</html>