<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>							

<title>Mi cuenta</title>
</head>
<body>

	<%@ include file="/WEB-INF/views/includes/menu.jsp" %> 


	<div class="container">
		<div class="busqueda">
			
			<div class="row">
				<div class="col-md-4">
				 
				</div>				 
				<div class="col-md-6" >
						<h2>Mi cuenta</h2>		
							
						Usuario: ${user.username}<br/>
						Nombre: Karina <br/>
						Apellido: Pangaro<br/>
						Fecha de Nacimiento: 20/05/1987
						<fmt:formatDate value="${user_profile.nacimiento}" pattern="dd/MM/yyyy" /><br/>												
						Email: kaluli@gmail.com<br/>
		                Contrase�a: ********** <a href="cambiarpassword.html">(Modificar Contrase�a)</a><br/>
		                �ltimo acceso: 
		              
		  	  	</div>					  
				<div class="col-md-2">
				 
				</div>
			</div>		
			
		</div>		
	</div>
	<div align="center">
		<a class="btn btn-primary" href="<spring:url value="logout.html"/>">Iniciar sesi�n 
	con un usuario diferente?</a>
	</div>
	
		<div class="panel-body">
		<div class="alert alert-dismissable alert-success">
              <button type="button" class="close" data-dismiss="alert">Ocultar Consejo</button>
              Ponga una contrase�a segura.  
            </div>
		</div>
	<div></div>
	<div></div>


	
  
<%@ include file="/WEB-INF/views/includes/footer.jsp" %>
