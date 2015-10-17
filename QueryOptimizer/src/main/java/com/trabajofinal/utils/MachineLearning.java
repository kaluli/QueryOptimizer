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
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.vectorizer.encoders.FeatureVectorEncoder;
import org.apache.mahout.vectorizer.encoders.StaticWordValueEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.trabajofinal.model.Configuracion;
import com.trabajofinal.model.Consulta;
import com.trabajofinal.model.Item;
import com.trabajofinal.model.Ranking;
import com.trabajofinal.service.ItemService;


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
			
	// Con MachineLearning genero items (queries genéricas)
		public int getItemId(List<String> queryParseada, String consulta){
			queryParseada = this.parsearQuery(consulta);
			String currentStatement = queryParseada.get(0); 
			Boolean where = false; Boolean all = false; Boolean inner = false;
			Boolean right = false; Boolean join = false; Boolean left = false;
			Boolean group = false; Boolean having = false; Boolean cross = false;
			
			int item = 0; 
			System.out.println(queryParseada.get(1));
			if (queryParseada.get(1).contentEquals("term=from")){
				all = true;
			}
			
			for(int i = 0; i < queryParseada.size(); i++) {
				// Tiene conditions Item 3, 4, 5, 6
				switch (queryParseada.get(i)){
					case "term=where":
						where = true;
						break;
					case "term=inner":
						inner = true;
						break;
					case "term=left":
						left = true;
						break;
					case "term=right":
						right = true;
						break;
					case "term=join":
						join = true;
						break;
					default:
						break;
				}
			}
						
			switch (currentStatement){
			case "term=select":{
				if ((all == true) && (where == false)){
					item = 1;
				}
				if ((all == false) && (where == false)){
					item = 2;
				}
				if ((all == true) && (where == true)){
					item = 3;
				}
				if ((all == false) && (where == true)){
					item = 4;
				}
				if ((all == true) && (where == true) && (inner == true)){
					item = 5;
				}
				if ((all == false) && (where == false) && (inner == true)){
					item = 6;
				}
				if ((all == false) && (where == true) && (inner == true)){
					item = 7;
				}
				if ((all == true) && (where == true) && (inner == true)){
					item = 8;
				}
				/*if ((all == true) && (where == false) && (inner == true)){
					item = 9;//anidados
				}*/
				/*if ((all == true) && (where == false) && (group == true) && (having == true)){
					item = 10;
				}
				if ((all == false) && (where == false) && (group == true) && (having == true)){
					item = 11;
				}*/
				if ((all == true) && (where == false) && (group == true) && (having == false)){
					item = 12;
				}
				if ((all == false) && (where == false) && (group == true) && (having == false)){
					item = 13;
				}
				if ((all == true) && (where == false) && (left == true)){
					item = 14;
				}
				if ((all == true) && (where == false) && (left == true) && (cross== true)){
					item = 15;
				}
				if ((all == false) && (where == false) && (left == true) && (cross== false)){
					item = 16;
				}
				if ((all == true) && (where == false) && (right == true)){
					item = 17;
				}
				if ((all == false) && (where == false) && (right == true)){
					item = 18;
				}
				if ((all == false) && (inner == true) && (group == true)){
					item = 19;
				}
				if ((all == true) && (inner == true) && (where == true) && (group == true)){
					item = 20;
				}
				System.out.println("Item: " + item);
			
			}	
			break;
			case "term=insert":{				
			}
			break;
			case "term=update":
				break;
			case "term=delete":
				break;
			case "term=alter":
				break;
			case "term=drop":
				break;
			case "term=create":
				break;
			case "term=use":
				break;
			case "term=show":
				System.out.println("entra");
				break;
			default:
				break;
			}
					
			for(int i = 0; i < queryParseada.size(); i++) {
	            System.out.println(queryParseada.get(i));
	        }
			return item;
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
		

}