<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<title>Logs</title>
</head>
<body>

	<%@ include file="/WEB-INF/views/includes/menu.jsp" %>

	<div class="container">
		<div class="jumbotron">
			<div class="row">																						 	  			
				<div class="col-md-12" id="cuadro">
					Log de: <c:out value="${user.username}"/>
					<c:forEach var="consulta" items="${user.consultas}">
						<div class="logs">
							
							<strong><c:out value="${consulta.query}"/></strong>
							<c:out value="${consulta.created}"/>
							<br/>							
						</div>
					</c:forEach>				
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