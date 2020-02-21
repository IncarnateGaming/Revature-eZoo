	<!-- Header -->
	<jsp:include page="header.jsp" />
	
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
	
		<h1>eZoo <small>Add Feeding Schedule</small></h1>
		<hr class="paw-primary">
		
		<form action="editFeedingSchedule" method="post" class="form-horizontal">
		  <div class="form-group">
		    <label for="id" class="col-sm-4 control-label">ID</label>
		    <div class="col-sm-4">
		      <input type="number" class="form-control" id="id" name="schedule_id" placeholder="ID" required="required" value="${feedingSchedule.scheduleID}" readonly/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="feeding-time" class="col-sm-4 control-label">Feeding Time</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="feeding-time" name="feeding_time" placeholder="Feeding Time" required="required" value="${feedingSchedule.feedingTime}"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="recurrence" class="col-sm-4 control-label">Recurrence</label>
		    <div class="col-sm-4">
					<select id="recurrence" required="required" name="recurrence" class="form-control">
						<option value="Daily"<c:out value="${'Daily' == feedingSchedule.recurrence ? ' selected' : ''}" />>
							Daily
						</option>
						<option value="Monday"<c:out value="${'Monday' == feedingSchedule.recurrence ? ' selected' : ''}" />>
							Monday
						</option>
						<option value="Tuesday"<c:out value="${'Tuesday' == feedingSchedule.recurrence ? ' selected' : ''}" />>
							Tuesday
						</option>
						<option value="Wednesday"<c:out value="${'Wednesday' == feedingSchedule.recurrence ? ' selected' : ''}" />>
							Wednesday
						</option>
						<option value="Thursday"<c:out value="${'Thursday' == feedingSchedule.recurrence ? ' selected' : ''}" />>
							Thursday
						</option>
						<option value="Friday"<c:out value="${'Friday' == feedingSchedule.recurrence ? ' selected' : ''}" />>
							Friday
						</option>
						<option value="Saturday"<c:out value="${'Saturday' == feedingSchedule.recurrence ? ' selected' : ''}" />>
							Saturday
						</option>
						<option value="Sunday"<c:out value="${'Sunday' == feedingSchedule.recurrence ? ' selected' : ''}" />>
							Sunday
						</option>
					</select>
				</div>
			</div>	
		  <div class="form-group">
		    <label for="food" class="col-sm-4 control-label">Food</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="food" name="food" placeholder="Food" required="required" value="${feedingSchedule.food}"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="notes" class="col-sm-4 control-label">Notes</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="notes" name="notes" placeholder="Notes" required="required" value="${feedingSchedule.notes}"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-primary">Update</button>
		    </div>
		  </div>
		</form>
	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />