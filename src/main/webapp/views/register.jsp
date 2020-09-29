<jsp:include page="../includes/cabecera.jsp">
	<jsp:param name="pagina" value="Registro" />
	<jsp:param name="title" value="Registro" />
</jsp:include>

<div id="register-form" class="container">

	<h2 class="text-center my-3">Crear cuenta</h2>

	<form action="register" method="post" class="form-login"> <!-- MD5 cipher function removed (onsubmit="cifrar()")-> We will cipher pass via SQL command -->

		<div class="form-group">			
			<input type="text" name="userName" placeholder="Nombre de usuario" value="${newUser.nombre}" class="form-control">
		</div>		

		<div class="form-group">
			<input type="password" id="pass" name="pass" placeholder="Contraseña" class="form-control">
		</div>

		<div class="form-group">
			<input type="password" id="passConfirm" name="passConfirm" placeholder="Confirma contraseña" class="form-control">
		</div>
		
		<div class="form-group">
			<label id="dob-label" for="dob">Fecha de nacimiento:</label>
			<input type="date" id="dob" name="dob" min="1901-01-01" max="2018-12-31" value="${newUser.dob}" class="form-control">
		</div>

		<input type="submit" value="Crear cuenta" class="btn btn-primary btn-block">
	
	</form>	
	
</div>

<%@include file="../includes/pie.jsp"%>