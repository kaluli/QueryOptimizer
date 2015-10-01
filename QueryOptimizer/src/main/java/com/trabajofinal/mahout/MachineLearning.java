package com.trabajofinal.mahout;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.trabajofinal.model.Configuracion;
import com.trabajofinal.model.Consulta;

public class MachineLearning{

	private String query;
	private String database;

	public MachineLearning() {
	}

	public MachineLearning(Consulta consulta, Configuracion config) {
		this.query = consulta.getQuery();
		this.database = config.getName();		
	}

	public List<RecommendedItem> analizar() {
		RandomUtils.useTestSeed();
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName("localhost");
		dataSource.setUser("root");
		dataSource.setPassword("kaluli32");
		dataSource.setDatabaseName("tesis");
		
		System.out.println("query: " + query + "database: " + database);
		JDBCDataModel model = new MySQLJDBCDataModel(dataSource, "ranking_queries", 
				"user_id","item_id", "ranking", null);
		System.out.println(model);
		try {
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
			UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			List<RecommendedItem> recommendations = recommender.recommend(2, 3);
			for (RecommendedItem recommendation : recommendations) {
				  System.out.println(recommendation);
				}
			
			return recommendations;	
			
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		
		
		
		
	}
	/*
	// Comparar la query alternativa con la original
	private String compararVelocidadQueries(Consulta consulta, String alternativa){
		String mas_veloz = alternativa; 		
		return mas_veloz;
	}
	
	// Con MachineLearning genero queries alternativas
	private List<String> generarQueriesAlternativas(){
		List<String> queriesAlternativas = null;
		return queriesAlternativas;
	}

	// Que traiga los mismos resultados que la query original
	private boolean validarResultados(String query, String resultados){
		if (query == resultados)
			return true;
		else
			return false;				
	}
*/
}