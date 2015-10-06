package com.trabajofinal.utils;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import com.trabajofinal.model.Consulta;

public class Database {
    SingleConnectionDataSource ds = new SingleConnectionDataSource();

	public JdbcTemplate conectarBD(String database){	
	    ds.setDriverClassName("com.mysql.jdbc.Driver");
	    String url = "jdbc:mysql://127.0.0.1:3306/" + database;	  
	    ds.setUrl(url);
	    ds.setUsername("root");
	    ds.setPassword("kaluli32");	    
	    return this.createJDBC(ds);
   	}
	
	private JdbcTemplate createJDBC(SingleConnectionDataSource ds){		
		return new JdbcTemplate(ds);			
	}		   
	
	public void desconectarBD(){
		ds.destroy();		
	}
	
	public Double calculaTiempoQuery(JdbcTemplate jt){
		return jt.queryForObject("SELECT SUM(DURATION) FROM INFORMATION_SCHEMA.PROFILING", Double.class);		
	}
	
	public List<Map<String,Object>> ejecutarQuery(JdbcTemplate jt, Consulta consulta, Database database) {
		this.limpiarCache(jt);		
		jt.execute("SET @@profiling = 1;");
		List<Map<String,Object>> resultados = jt.queryForList(consulta.getQuery());
		consulta.setTime(database.calculaTiempoQuery(jt));
		return resultados;
	}
	
	public JdbcTemplate limpiarCache(JdbcTemplate jt){
		jt.execute("RESET QUERY CACHE;");
		jt.execute("FLUSH QUERY CACHE;");
		jt.execute("SET GLOBAL query_cache_size = 0;");
		jt.execute("SET GLOBAL query_cache_type=0;");
		jt.execute("SET @@profiling = 0;");
		jt.execute("SET @@profiling_history_size = 0;");
		jt.execute("SET @@profiling_history_size = 100;");
		//SHOW STATUS LIKE 'Qcache%'; en workbench, restart mysqlserver
		//sudo /etc/init.d/mysql restart en una terminal
		return jt;		
	}
	
}
