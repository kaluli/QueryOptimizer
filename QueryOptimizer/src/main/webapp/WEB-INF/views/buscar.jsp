<%@ include file="/WEB-INF/views/includes/header.jsp" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>							

<title>Buscar Usuarios</title>
</head>
<body>

	<%@ include file="/WEB-INF/views/includes/menu.jsp" %>  

	<div class="container">
		<div class="busqueda">
			
			<div class="row">				 
				<div class="col-md-5" id ="seguidores">
						<h2>Busca un usuario</h2>			
						<c:forEach var="usuario" items="${usuarios}">											
						<div>
							<c:if test="${usuario.usuario != yomismo}">
							<a href='perfil.html?id=<c:out value="${usuario.id}"/>'>
							${usuario.nombre} ${usuario.apellido} (@${usuario.usuario}) </a>
							</c:if>											
		                </div>
		              
						</c:forEach>
		  	  	</div>					  
				<div class="col-md-4">
				 
				</div>
			</div>		
			
		</div>		
	</div>
	<div align="center">
		<a class="btn btn-primary" href="<spring:url value="logout.html"/>">Iniciar sesión 
	con un usuario diferente?</a>
	</div>
	
		<div class="panel-body">
		<div class="alert alert-dismissable alert-success">
              <button type="button" class="close" data-dismiss="alert">Ocultar Consejo</button>
              Hacé click en <strong>Seguir</strong> para estar al tanto de todas las noticias de tus usuarios de interés.  
            </div>
		</div>
	<div></div>
	<div></div>


	
  
<%@ include file="/WEB-INF/views/includes/footer.jsp" %>