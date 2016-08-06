<%@include file="includes/header.jsp"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Please Sign up!</h3>
	</div>
	<div class="panel-body">

		<form:form modelAttribute="signupForm" role="form">

			<form:errors />

			<div class="form-group">
				<form:label path="email">Email address</form:label>
				<form:input path="email" type="email" class="form-control"
					placeholder="Email" />
				<form:errors cssClass="error" path="email" />
				<p class="help-block">Enter a Uniqe Email address. It Will also
					be your ...</p>
			</div>

			<div class="form-group">
				<form:label path="name">Name</form:label>
				<form:input path="name" class="form-control"
					placeholder="Enter Name" />
				<form:errors cssClass="error" path="name" />
				<p class="help-block">Enter your display name.</p>
			</div>

			<div class="form-group">
				<form:label path="password">Password</form:label>
				<form:password path="password" class="form-control"
					placeholder="Password" />
				<form:errors cssClass="error" path="password" />
			</div>
			<div class="checkbox">
				<label><input type="checkbox"> Remember me</label>
			</div>

			<button type="submit" class="btn btn-default">Submit</button>

		</form:form>
	</div>
</div>
<%@include file="includes/footer.jsp"%>