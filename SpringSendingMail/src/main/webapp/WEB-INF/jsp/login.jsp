<%@include file="includes/header.jsp"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Please Sign in!</h3>
	</div>

	<div class="panel-body">
		<c:if test="${param.error != null}">
			<div class="alert-danger">
			Invalid username and password!
			</div>
		</c:if>
		<c:if test="${param.logout != null}">
			<div class="alert-danger">
				You have been logout!
			</div>
		</c:if>

		<form:form role="form" method="post">
			<div class="form-group">
				<label for="username">Email Adress</label> 
				<input id="username"name="username" type="email" class="form-control" placeholder="Enter Email" >
				<p class="help-block">Enter your mail address!</p>
			</div>

			<div class="form-group">
				<label for="password">Password</label> 
				<input id="password" name="password" type="password" class="form-control" placeholder="Password">
				<form:errors cssClass="error" path="password" />
			</div>

			<button type="submit" class="btn btn-primary"></button>

		</form:form>
	</div>
</div>

<%@include file="includes/footer.jsp"%>