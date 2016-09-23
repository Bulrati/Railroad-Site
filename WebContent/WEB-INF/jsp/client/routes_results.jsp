<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<c:set var="title" value="Routes" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<td class="content">
				<%-- CONTENT --%> <c:choose>
					<c:when test="${fn:length(resultRoutes) == 0}">No such orders</c:when>

					<c:otherwise>
						<table id="list_order_table">
							<thead>
								<tr>
									<td>train №</td>
									<td>departmentDate</td>
									<td>departmentTime</td>
									<td>departmentStation</td>
									<td>arrivalDate</td>
									<td>arrivalTime</td>
									<td>arrivalStation</td>
									<td>totalTimeInRoute</td>
									<td>freePlackart</td>
									<td>pricePlackart</td>
									<td>freeCupe</td>
									<td>priceCupe</td>
									<td>freeCommon</td>
									<td>pricePCommon</td>
									<td>routeLink</td>
								</tr>
							</thead>


							<c:forEach var="bean" items="${resultRoutes}">

								<tr>
									<td>${bean.trainNumber}</td>
									<td>${bean.departmentDate}</td>
									<td>${bean.departmentTime}</td>
									<td>${bean.departmentStation}</td>
									<td>${bean.arrivalDate}</td>
									<td>${bean.arrivalTime}</td>
									<td>${bean.arrivalStation}</td>
									<td>${bean.inRoad}</td>
									<td>${bean.freePlackart}</td>
									<td>${bean.pricePlackart}</td>
									<td>${bean.freeCupe}</td>
									<td>${bean.priceCupe}</td>
									<td>${bean.freeCommon}</td>
									<td>${bean.pricePCommon}</td>
									<td>${bean.routeLink}</td>
								</tr>

							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose> <%-- CONTENT --%>
			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</table>
</body>
</html>