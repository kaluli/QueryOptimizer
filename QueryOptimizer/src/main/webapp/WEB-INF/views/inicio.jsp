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
				<div class="col-md-12" id="cuadro">
				<div class="form-group">
					<h3>Seleccionar Base de Datos</h3>
					<select id="config" name="configId" class="form-control">
				    <option value=""></option>												  				
					<c:forEach var="config" items="${config}">			            
					<option value="${config.id}" >${config.name}</option>
				    </c:forEach>
					</select>
				</div>				
				<h3>Analizar Consulta SQL</h3>
				<div class="buttons">
					<div class="btn-group">
				  		 <button type="button" class="btn btn-default">SELECT</button>
			  		</div>
				  	<div class="btn-group">			  	
				  		 <button type="button" class="btn btn-default">UPDATE</button>
				  	</div>
				  	<div class="btn-group">			  	
				  		 <button type="button" class="btn btn-default">DELETE</button>
				  	</div>
				  	<div class="btn-group">			  	
				  		 <button type="button" class="btn btn-default">INSERT</button>
					</div>
				  	<div class="btn-group">			  	
				  		 <button type="button" class="btn btn-default">CREATE</button>
					</div>			
				</div>			
				<form>
					<div class="query-box">
						<textarea class="form-control" rows="5"></textarea>																		  	
					</div>					
					<div id="left">
						<button type="submit" class="btn btn-primary">Analizar</button>
					</div>
				</form>
				</div>	
				<div class="col-md-6">
				Resultados:
				
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