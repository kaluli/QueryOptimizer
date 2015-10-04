package com.trabajofinal.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

@Entity
@Table(name="rankings")
@IdClass(value=RankingPK.class)
public class Ranking{
	
	@Id
	private int user_id;

	@Id
	private int item_id;
	
	@Column(name = "ranking", nullable = false)
	private Float ranking;
	
	@Column(name = "time", nullable = false)
	private Double time;
	
	private Date created;

	public Ranking(){}
	
	public Ranking(int user_id, int item_id, Float ranking,Double time, Date created) {		
		this.user_id = user_id;
		this.item_id = item_id;
		this.ranking = (float) 1; // Por defecto el ranking más bajo
		this.time = time;
		this.created = created;
	}

	private SingleConnectionDataSource conectarBD(String database){
		SingleConnectionDataSource ds = new SingleConnectionDataSource();
		  // Sería óptimo que leyera estos datos de un archivo 
	    ds.setDriverClassName("com.mysql.jdbc.Driver");
	    String url = "jdbc:mysql://127.0.0.1:3306/" + database;	  
	    ds.setUrl(url);
	    ds.setUsername("root");
	    ds.setPassword("kaluli32");
	    return ds;
   	}
	
	private JdbcTemplate limpiarCache(JdbcTemplate jt){
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
	
	private Double calculaTiempo(JdbcTemplate jt){
		return jt.queryForObject("SELECT SUM(DURATION) FROM INFORMATION_SCHEMA.PROFILING", Double.class);		
	}
		
			
	public List<Map<String, Object>> gestionarConsulta(String db){
		// conectar a la bd seleccionada
		SingleConnectionDataSource dbs = this.conectarBD(db);
		JdbcTemplate jt = new JdbcTemplate(dbs);
	//	List<Map<String, Object>> result = this.ejecutarQuery(jt);
		

		return null;	
	}	


	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Float getRanking() {
		return ranking;
	}

	public void setRanking(Float  ranking) {
		this.ranking = ranking;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public int getItemId() {
		return item_id;
	}

	public void setItemId(int item_id) {
		this.item_id = item_id;
	}
	
	/*@OneToMany(targetEntity=Item.class, mappedBy="id", fetch=FetchType.EAGER)
	private List<Item> Item;
*/
	
}
