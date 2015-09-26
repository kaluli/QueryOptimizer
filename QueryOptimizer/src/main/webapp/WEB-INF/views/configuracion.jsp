<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<title>Configuración</title>
</head>
<body>

	<%@ include file="/WEB-INF/views/includes/menu.jsp" %>

	<div class="container">
		<div class="jumbotron">
			<div class="row"  id="cuadro">																						 	  			
				<div class="col-md-10" >
					<form:form method="post"  enctype="multipart/form-data" class="bs-example form-horizontal" action="upload.html">				
						<div class="form-group">
							<label for="file">Importar Archivo sql</label>
							<input type="file" name="file" accept=".sql" path="file" id="fileInput">
							<p class="help-block"></p>
		   						<button type="submit" class="btn btn-primary">Importar Archivo</button>					 
							</div>		
					</form:form>	
					<div class="form-group">
						<h3>Archivos cargados</h3>
						<select id="config" name="configId" class="form-control">
					    <option value=""></option>												  				
						<c:forEach var="config" items="${config}">			            
						<option value="${config.id}" >${config.name}</option>
					    </c:forEach>
						</select>
					</div>							
				</div>
					    											
				</div>				
			</div>	
		</div>					            				     
  	  
	<div class="panel panel-success">		
		<div class="panel-body">
		<div class="alert alert-dismissable alert-success">
              <button type="button" class="close" data-dismiss="alert">SOS</button>
              <strong>Ayuda!</strong> Deberá cargar la BD antes de comenzar a analizar las consultas.            
		</div>
		</div>
	</div>

	
	<script type="text/javascript">
		$(function() {
			var yesButton = $("#yesbutton");
			var progress = $("#doitprogress");		
			
			yesButton.click(function() {		
				yesButton.button("loading");

				var counter = 0;
				var countDown = function() {
					counter++;
					if (counter == 11) {
						yesButton.button("complete");
					} else {
						progress.width(counter * 10 + "%");
						setTimeout(countDown, 100);
					}
				};
				
				setTimeout(countDown, 100);
			});
			
		});
	</script>
	

</body>
</html>