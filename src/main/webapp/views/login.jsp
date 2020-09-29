<jsp:include page="../includes/cabecera.jsp">
	<jsp:param name="pagina" value="Login" />
	<jsp:param name="title" value="Login" />
</jsp:include>


<div id="login-form" class="container">

	<form action="login" method="post" class="form-login" onsubmit="cifrar()">

		<div class="form-group">
			<input type="text" name="nombre" placeholder="Nombre de usuario" class="form-control">
		</div>

		<div class="form-group">
			<input type="password" id="pass" name="pass" placeholder="Contraseña" class="form-control">
		</div>

		<div class="form-group">
			<select name="idioma" class="custom-select">
				<option value="es">Castellano</option>
				<option value="eu">Euskera</option>
				<option value="en">Ingles</option>
			</select>
		</div>

		<input type="submit" value="Iniciar Sesión" class="btn btn-primary btn-block">

	</form>

	<div class="d-flex justify-content-center mt-3">

		<a href="views/register.jsp">Crear cuenta</a>

	</div>



</div>

<%@include file="../includes/pie.jsp"%>

