<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<title>Inicio</title>
<script src="jquery-1.8.3.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>	 
</head>
<body>

	<%@ include file="/WEB-INF/views/includes/menu.jsp" %> 

	<div class="container">
		<div class="jumbotron">
			<div class="row">						
				<form:form id="enviarQuery" method="POST" commandName="query" > 											 	  																					
					<div class="col-md-12" id="cuadro">
						<div class="form-group">
							<h3>Seleccionar Base de Datos</h3>
							<select id="config" name="configId" class="form-control" required>
						    <option value=""></option>												  				
							<c:forEach var="config" items="${user.configuraciones}">			            
							<option value="${config.id}" >${config.name}</option>
						    </c:forEach>
							</select>
						</div>				
						<h3>Analizar Consulta SQL</h3>
						<div class="buttons">
							<div class="btn-group">
						  		 <button type="button" id="button" class="btn btn-default">SELECT</button>
					  		</div>
						  	<div class="btn-group">			  	
						  		 <button type="button" id="button" class="btn btn-default">UPDATE</button>
						  	</div>
						  	<div class="btn-group">			  	
						  		 <button type="button" id="button" class="btn btn-default">DELETE</button>
						  	</div>
						  	<div class="btn-group">			  	
						  		 <button type="button" id="button" class="btn btn-default">INSERT</button>
							</div>
						  	<div class="btn-group">			  	
						  		 <button type="button" id="button" class="btn btn-default">CREATE</button>
							</div>
							<div class="btn-group">			  	
						  		 <button type="button" id="button" class="btn btn-default">VER TABLAS</button>
							</div>			
						</div>			
						<div class="query-box">
							<textarea name="query" id="query" class="form-control" rows="5" required></textarea>																		  	
						</div>					
						<div id="right">
							<button type="submit" class="btn btn-primary">Analizar</button>
						</div>
					</div>
				</form:form>	
							
				<div class="col-md-12">
					Consulta: ${consulta.query}<br/>
					${consulta.time}<br/>
					Tiempo: <fmt:formatNumber type="number" maxFractionDigits="5" value="${consulta.time}" /> segundos<br/>
					Resultados:	<br/>
					<c:forEach var="resultado" items="${resultados}">
						${resultado} <br/>
					</c:forEach>
				</div>
				
			</div>			    		
							            				     
  	  	</div>					  
	
	</div>		
	
	<div class="panel panel-success">		
		<div class="panel-body">
		<div class="alert alert-dismissable alert-success">
              <button type="button" class="close" data-dismiss="alert">SOS</button>
              <strong>Ayuda!</strong> Ingrese la consulta SQL o analice un archivo.            
		</div>
		</div>
	</div>

	
	<script type="text/javascript">
		$(function() {
			$("button#button").on( "click", function() {
				var query = $(this).text();
				switch($(this).text()){
					case "SELECT":
						query = query + " * FROM table_name WHERE condition;";
						break;
					
					case "UPDATE":
						query = query + " table_name SET values WHERE condition;";
						break;
						
					case "CREATE":
						query = query + " DATABASE database_name;";
						break;
						
					case "ALTER":
						query = query + " TABLE table_name WHERE condition;";
						break;
					
					case "DELETE":
						query = query + " FROM table_name WHERE condition;";
						break;			
						
					case "INSERT":
						query = query + " INTO table_name;";
						break;
						
					case "VER TABLAS":
						query = "SHOW TABLES; ";
						break;
				}
				$("#query").val(query);
				$("#query").focus();
			});
		});
	</script>
	

</body>
</html>