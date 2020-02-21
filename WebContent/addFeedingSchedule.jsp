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
		
		<form action="addFeedingSchedule" method="post" class="form-horizontal">
		
		  <div class="form-group">
		    <label for="id" class="col-sm-4 control-label">ID</label>
		    <div class="col-sm-4">
		      <input type="number" class="form-control" id="id" name="schedule_id" placeholder="ID" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="feeding-time" class="col-sm-4 control-label">Feeding Time</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="feeding-time" name="feeding_time" placeholder="Feeding Time" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="recurrence" class="col-sm-4 control-label">Recurrence</label>
		    <div class="col-sm-4">
					<select id="recurrence" required="required" name="recurrence" class="form-control">
						<option value="Daily">
							Daily
						</option>
						<option value="Monday">
							Monday
						</option>
						<option value="Tuesday">
							Tuesday
						</option>
						<option value="Wednesday">
							Wednesday
						</option>
						<option value="Thursday">
							Thursday
						</option>
						<option value="Friday">
							Friday
						</option>
						<option value="Saturday">
							Saturday
						</option>
						<option value="Sunday">
							Sunday
						</option>
					</select>
				</div>
			</div>	
		  <div class="form-group">
		    <label for="food" class="col-sm-4 control-label">Food</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="food" name="food" placeholder="Food" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="notes" class="col-sm-4 control-label">Notes</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="notes" name="notes" placeholder="Notes" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-primary">Add</button>
		    </div>
		  </div>
		</form>
	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />