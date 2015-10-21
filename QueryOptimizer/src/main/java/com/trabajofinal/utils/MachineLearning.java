package com.trabajofinal.utils;


import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.MemoryDiffStorage;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.slopeone.DiffStorage;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.trabajofinal.model.Configuracion;
import com.trabajofinal.model.Consulta;
import com.trabajofinal.model.Item;
import com.trabajofinal.model.Ranking;


public class MachineLearning{

	private int queryId;
	private String database;
	private String consulta;
	//private Ranking ranking;

	private Analyzer analyzer;
	private char[] termBuffers;
	private int termLen;
	
	public MachineLearning() {
	}

	public MachineLearning(Consulta consulta, Configuracion config) {
		
		//this.queryId = ranking.getItemId();
		this.database = config.getName();
		this.setConsulta(consulta.getQuery());		
	}

	
	public List<RecommendedItem> gestionarRanking(Database database, Consulta consulta, Ranking ranking, Item item) {		
		List<RecommendedItem> recomendaciones = this.slopeOne(ranking.getUserId());		
		return recomendaciones;	
	}
	
	public List<RecommendedItem> recomendar(int userId) {
		RandomUtils.useTestSeed();
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName("localhost");
		dataSource.setUser("root");
		dataSource.setPassword("kaluli32");
		dataSource.setDatabaseName("tesis");
		
		System.out.println("query: " + queryId + "database: " + database);
		JDBCDataModel model = new MySQLJDBCDataModel(dataSource, "rankings", 
				"user_id","item_id", "ranking", null);
		System.out.println(model);
		try {
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(1, similarity, model);
			Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			//UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			List<RecommendedItem> recommendations = recommender.recommend(userId, 3);
			for (RecommendedItem recommendation : recommendations) {
				  System.out.println(recommendation);
			}
			
			return recommendations;	
			
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			return null;
		}				
	}

	public List<RecommendedItem> slopeOne(int userId) {
		//RandomUtils.useTestSeed();
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName("localhost");
		dataSource.setUser("root");
		dataSource.setPassword("kaluli32");
		dataSource.setDatabaseName("tesis");
				
		try {
			JDBCDataModel model = new MySQLJDBCDataModel(dataSource, "rankings", 
					"user_id","item_id", "ranking", null);
			if (model.getNumItems() > 0){ 
				
				DiffStorage diffStorage = new MemoryDiffStorage(model, Weighting.WEIGHTED, false, Long.MAX_VALUE);
				
				Recommender recommender = new SlopeOneRecommender(model,Weighting.WEIGHTED,Weighting.WEIGHTED,diffStorage);
				
				//recommend(long userID,int howManyRecommendations)
				List <RecommendedItem> recommendations = recommender.recommend(userId, 1);
				
				System.out.println("Recomendación: " + recommendations);
				for (RecommendedItem recommendation : recommendations) {
					  System.out.println(recommendation);
				}
				return recommendations;	
			}
			return null;	
			
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			return null;
		}		
		
	}	
	
	@Autowired
	public List<String> parsearQuery(String consulta) {		
		analyzer = new StandardAnalyzer(Version.LUCENE_30);
	    List<String> result = new ArrayList<String>();

		try {					
			TokenStream ts = analyzer.tokenStream("text", new StringReader(consulta.toLowerCase()));
			ts.reset();
			TermAttribute termAtt = ts.addAttribute(TermAttribute.class);

			while (ts.incrementToken()) {
				setTermBuffers(termAtt.termBuffer());
				setTermLen(termAtt.termLength());
				if (termAtt.termLength() != 0){
			        result.add(ts.getAttribute(TermAttribute.class).toString());						
				}					
			}
			ts.end();
			ts.close();
						
		} catch (IOException e) {			
			e.printStackTrace();
			System.out.println(e);
		}		
		System.out.println(consulta);
		return result;	
	}
			
		
	public float crearRankingId(List <Ranking> rankings, Item item) {		
		for(int i = 0; i < rankings.size(); i++) {
		    
			System.out.println(rankings.get(i).getItemId());
		    
			//List<Item> items = itemService.findAll();
			//Item item = itemService.findById(1);
        
            //System.out.println(item.getQuery());
            
        }
		return 1;
		
	}

	public char[] getTermBuffers() {
		return termBuffers;
	}

	public void setTermBuffers(char[] termBuffers) {
		this.termBuffers = termBuffers;
	}

	public int getTermLen() {
		return termLen;
	}

	public void setTermLen(int termLen) {
		this.termLen = termLen;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public String generalizarQuery(List<String> parametrosQuery) {
		StringBuffer queryGeneralizada = new StringBuffer();
		parametrosQuery = this.parsearQuery(consulta);
		String currentStatement = parametrosQuery.get(0);
		int j = 1;
		
		if (currentStatement.contentEquals("term=select")){
			queryGeneralizada.append(currentStatement.substring(5)); //Quita el term			
			if (parametrosQuery.get(1).contentEquals("term=from")){			
				queryGeneralizada.append(" *");
			}
			else{
				queryGeneralizada.append(" %keyfields%");							
			}
			
			for(int i = 1; i < parametrosQuery.size(); i++) {
				switch (parametrosQuery.get(i)){
					case "term=from":{
						queryGeneralizada.append(" " + parametrosQuery.get(i).substring(5));						
						queryGeneralizada.append(" %table%");					
					}
					break;				
					case "term=where":
						queryGeneralizada.append(" " + parametrosQuery.get(i).substring(5));						
						queryGeneralizada.append(" %conditions%");
						break;
					case "term=inner":
						queryGeneralizada.append(" " + parametrosQuery.get(i).substring(5));						
						break;
					case "term=left":
						queryGeneralizada.append(" " + parametrosQuery.get(i).substring(5));						
						break;
					case "term=right":
						queryGeneralizada.append(" " + parametrosQuery.get(i).substring(5));						
						break;
					case "term=join":
						j++;
						queryGeneralizada.append(" " + parametrosQuery.get(i).substring(5));
						queryGeneralizada.append(" %table" + j + "%");
						queryGeneralizada.append(" on %conditions" + j + "%");
						break;
					case "term=group":
						queryGeneralizada.append(" " + parametrosQuery.get(i).substring(5));						
						queryGeneralizada.append(" by" + " %group%");
						break;
					case "term=having":
						queryGeneralizada.append(" " + parametrosQuery.get(i).substring(5));						
						queryGeneralizada.append(" %havingcondition%");
						break;					
					default:
						break;
				}
			}
			//queryGeneralizada.append(";");
			
		}
		return queryGeneralizada.toString();
	}
		

}