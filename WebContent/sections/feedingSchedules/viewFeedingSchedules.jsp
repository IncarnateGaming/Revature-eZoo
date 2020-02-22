	<!-- Header -->
	<jsp:include page="../../header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">

	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
 
		<h1>eZoo <small>Feeding Schedules</small></h1>
		<hr class="paw-primary">
		<table class="table table-striped table-hover table-responsive ezoo-datatable">
			<thead>
				<tr>
					<th class="text-center">Edit</th>
					<!-- <th class="text-center">Schedule ID</th> (best practice is to delete not comment out lines like this
					however I wanted to show that I know how to add this even if 90% of customers would not want it) -->
					<th class="text-center">Feeding Time</th>
					<th class="text-center">Recurrence</th>
					<th class="text-center">Food</th>
					<th class="text-center">Notes</th>
					<th class="text-center">Assigned Animals</th>
					<th class="text-center">Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="feedingSchedule" items="${feedingSchedules}">
					<tr>
						<td><a href="/eZoo/editFeedingSchedule?feedingScheduleId=${feedingSchedule.scheduleID}"><button class="btn-primary">Edit</button></a></td>
						<!-- <td><c:out value="${feedingSchedule.scheduleID}" /></td> (best practice is to delete not comment out lines like this
					however I wanted to show that I know how to add this even if 90% of customers would not want it) -->
						<td><c:out value="${feedingSchedule.feedingTime}" /></td>
						<td><c:out value="${feedingSchedule.recurrence}" /></td>
						<td><c:out value="${feedingSchedule.food}" /></td>
						<td><c:out value="${feedingSchedule.notes}" /></td>
						<td><c:out value="${feedingSchedule.animalLinks}" escapeXml="false"/></td>
						<td><button class="btn-danger delete-button" data-label="${feedingSchedule.feedingTime}, ${feedingSchedule.recurrence}, ${feedingSchedule.food}" data-delete="/eZoo/deleteFeedingSchedule?feedingScheduleId=${feedingSchedule.scheduleID}">Delete</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	

	  </div>
	</header>

	<!-- Footer -->
    <script src="resources/scripts/deleteButton.js"></script>
	<jsp:include page="../../footer.jsp" />
