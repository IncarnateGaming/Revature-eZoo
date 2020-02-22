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
	
		<h1>eZoo <small>Edit Animal</small></h1>
		<hr class="paw-primary">
		
		<form action="editAnimal" method="post" class="form-horizontal">
						
		  <div class="form-group">
		    <label for="id" class="col-sm-4 control-label">ID</label>
		    <div class="col-sm-4">
		      <input type="number" class="form-control" id="id" name="id" placeholder="ID" required="required" value="${animal.animalID}" readonly/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="name" class="col-sm-4 control-label">Name</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="name" name="name" placeholder="Name" required="required" value="${animal.name}"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="healthStatus" class="col-sm-4 control-label">Health</label>
		    <div class="col-sm-4">
				<select required="required" name="healthStatus" class="form-control">
					<option value="Healthy"<c:out value="${'Healthy' == animal.healthStatus ? ' selected' : ''}" />>
						Healthy
					</option>
					<option value="Sick"<c:out value="${'Sick' == animal.healthStatus ? ' selected' : ''}" />>
						Sick
					</option>
					<option value="Injured"<c:out value="${'Injured' == animal.healthStatus ? ' selected' : ''}" />>
						Injured
					</option>
					<option value="Dead"<c:out value="${'Dead' == animal.healthStatus ? ' selected' : ''}" />>
						Dead
					</option>
				</select>
			</div>
		  </div>	
		  <div class="form-group">
		    <label for="type" class="col-sm-4 control-label">Type</label>
		    <div class="col-sm-4">
				<select required="required" name="type" class="form-control">
					<option value="Mammal (Terrestrial)"<c:out value="${'Mammal (Terrestrial)' == animal.type ? ' selected' : ''}" />>
						Mammal (Terrestrial)
					</option>
					<option value="Mammal (Aquatic)"<c:out value="${'Mammal (Aquatic)' == animal.type ? ' selected' : ''}" />>
						Mammal (Aquatic)
					</option>
					<option value="Mammal (Aviary)"<c:out value="${'Mammal (Aviary)' == animal.type ? ' selected' : ''}" />>
						Mammal (Aviary)
					</option>
					<option value="Fish"<c:out value="${'Fish' == animal.type ? ' selected' : ''}" />>
						Fish
					</option>
					<option value="Amphibian"<c:out value="${'Amphibian' == animal.type ? ' selected' : ''}" />>
						Amphibian
					</option>
					<option value="Reptile"<c:out value="${'Reptile' == animal.type ? ' selected' : ''}" />>
						Reptile
					</option>
					<option value="Bird"<c:out value="${'Bird' == animal.type ? ' selected' : ''}" />>
						Bird
					</option>
				</select>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="height" class="col-sm-4 control-label">Height (in)</label>
		    <div class="col-sm-4">
		      <input type="number" step="0.01" class="form-control" id="height" name="height" placeholder="Height" required="required" value="${animal.height}"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="weight" class="col-sm-4 control-label">Weight (lb)</label>
		    <div class="col-sm-4">
		      <input type="number" step="0.01" class="form-control" id="weight" name="weight" placeholder="Weight" required="required" value="${animal.weight}"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="kingdom" class="col-sm-4 control-label">Kingdom</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="kingdom" name="kingdom" placeholder="Kingdom" required="required" value="${animal.taxKingdom}"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="phylum" class="col-sm-4 control-label">Phylum</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="phylum" name="phylum" placeholder="Phylum" required="required" value="${animal.taxPhylum}"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="class" class="col-sm-4 control-label">Class</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="class" name="clazz" placeholder="Class" required="required" value="${animal.taxClass}"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="order" class="col-sm-4 control-label">Order</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="order" name="order" placeholder="Order" required="required" value="${animal.taxOrder}"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="family" class="col-sm-4 control-label">Family</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="family" name="family" placeholder="Family" required="required" value="${animal.taxFamily}"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="genus" class="col-sm-4 control-label">Genus</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="genus" name="genus" placeholder="Genus" required="required" value="${animal.taxGenus}"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="species" class="col-sm-4 control-label">Species</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="species" name="species" placeholder="Species" required="required" value="${animal.taxSpecies}"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="feedingSchedule" class="col-sm-4 control-label">Feeding Schedule</label>
		    <div class="col-sm-4">
				<select id="feedingSchedule" required="required" name="feedingSchedule" class="form-control">
				<c:forEach var="feedingSchedule" items="${feedingSchedules}">
					<option value="${feedingSchedule.scheduleID}" <c:out value="${feedingSchedule.scheduleID == animal.feedingScheduleId ? ' selected' : ''}" />>${feedingSchedule.feedingTime}, ${feedingSchedule.recurrence}, ${feedingSchedule.food}</option>
				</c:forEach>
				</select>
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
	<jsp:include page="../../footer.jsp" />