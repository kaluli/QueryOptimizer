package com.trabajofinal.mahout;


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
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
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
import com.trabajofinal.model.Ranking;
import com.trabajofinal.service.RankingService;

public class MachineLearning{
	
	@Autowired
	private RankingService rankingService;
	
	private int queryId;
	private String database;
	private String consulta;
	private Ranking ranking;

	private Analyzer analyzer;
	
	public MachineLearning() {
	}

	public MachineLearning(Consulta consulta, Configuracion config) {
		//this.queryId = ranking.getItemId();
		this.database = config.getName();
		this.consulta = consulta.getQuery();		
	}

	public List<RecommendedItem> recomendar() {
		RandomUtils.useTestSeed();
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName("localhost");
		dataSource.setUser("root");
		dataSource.setPassword("kaluli32");
		dataSource.setDatabaseName("tesis");
		
		System.out.println("query: " + queryId + "database: " + database);
		JDBCDataModel model = new MySQLJDBCDataModel(dataSource, "ranking_queries", 
				"user_id","item_id", "ranking", null);
		System.out.println(model);
		try {
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
			Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			//UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
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

	
	/*private List<Category> getCategory(String keyword) throws IOException {
		  List<String> words=new ArrayList<String>();
		  List<Category> recommendCategories=new ArrayList<Category>();ge
		  Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_30);
		  TokenStream tokenStream=analyzer.reusableTokenStream("title",new StringReader(keyword));
		 
		  while (tokenStream.incrementToken()) {
		    String text= tokenStream.toString();
		    words.add(text);
		  }
		  Iterator<String> categoryIterator= categoryMap.keySet().iterator();
		  double bestAngle=Double.MAX_VALUE;
		  String bestCategory=null;
		  while (categoryIterator.hasNext()) {
		    String category=categoryIterator.next();
		    if (category.equals("DSLR/??/????##????(DSLR/??/??)##??/????##") || category.equals("DSLR/??/????##DSLR???##??##")) {
		      System.out.println("a");
		    }
		    Category c=computeAngle(words,category);
		    recommendCategories.add(c);
		  }
		  Collections.sort(recommendCategories);
		  recommendCategories=recommendCategories.subList(0,10);
		  return recommendCategories;
		}
	*/
	
	
	@Autowired
	public List<String> clasificarQuery(String consulta) {

		//sqlStatements currentStatement = sqlStatements.valueOf(consulta.toUpperCase());
		FeatureVectorEncoder encoder = new StaticWordValueEncoder("SELECT");
		analyzer = new StandardAnalyzer(Version.LUCENE_30);
	    List<String> result = new ArrayList<String>();

		try {
			this.createSequenceFile();
			TokenStream ts = analyzer.tokenStream("text", new StringReader(consulta.toLowerCase()));
			ts.reset();
			TermAttribute termAtt = ts.addAttribute(TermAttribute.class);

			// crear el vector para la nueva consulta
			Vector vector = new RandomAccessSparseVector(10000);
			while (ts.incrementToken()) {
				char[] termBuffer = termAtt.termBuffer();
				int termLen = termAtt.termLength();
				if (termAtt.termLength() != 0){
			        result.add(ts.getAttribute(TermAttribute.class).toString());
				//	Encode into vector size 100
					String word = new String(termBuffer, 0, termLen);
					encoder.addToVector(word, 1, vector);
				//	Add word w to vector "vector"
				}
					
			}
			ts.end();
			ts.close();
			
			/*Configuration configuration = new Configuration();
			configuration.set("select * from table", "bad");
			NaiveBayesModel model = NaiveBayesModel.fromMRTrainerOutput(new Path("/home/kalu/tmp/input"), configuration);
			StandardNaiveBayesClassifier classifier = new StandardNaiveBayesClassifier(model);
			Vector resultVector = classifier.classifyFull(vector);
			System.out.println(result);
			System.out.printf("%s\n", new SequentialAccessSparseVector(vector));
			double bestScore = -Double.MAX_VALUE;
			int bestCategoryId = -1;
			for (Element element : resultVector){
				int categoryId = element.index();
				double score = element.get();
				if (score > bestScore){
					bestScore = score;
					bestCategoryId = categoryId;
				}
				if (categoryId == 1){
					System.out.println("probabilidad de ser positivo: " + score);
				}
				else{
					System.out.println("probabilidad de ser negativo: " + score);
				}
				
				if (bestCategoryId == 1){
					System.out.println("es positivo: ");
				}
				else{
					System.out.println("es negativo: ");
				}
				analyzer.close();
			}*/			
		} catch (IOException e) {			
			e.printStackTrace();
			System.out.println(e);
		}
		
		System.out.println(consulta);
		return result;
	
	}
	
	private void createSequenceFile() throws IOException { 
       
    } 
	
	public void gestionarRanking(Ranking ranking) {

		//rankingService.findByQuery();
	
	}
	
	// Con MachineLearning genero items (queries genéricas)
		public int getRankingId(String consulta){
			List<String> result = this.clasificarQuery(consulta);
			String currentStatement = result.get(0); 
			Boolean where = false; Boolean all = false; Boolean inner = false;
			int item = 0;
			System.out.println(result.get(1));
			if (result.get(1).contentEquals("term=from")){
				all = true;
			}
			
			for(int i = 0; i < result.size(); i++) {
				// Tiene conditions Item 3, 4, 5, 6
				switch (result.get(i)){
					case "term=where":
						where = true;
						break;
					case "term=inner":
						inner = true;
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
				if ((all == false) && (where == true) && (inner == true)){
					item = 6;
				}
				// Select anidados
				/*if ((all == true) && (where == true) && (inner == true)){
					item = 7;
				}*/
				
				System.out.println("Item: " + item);
			
			}	
			break;
			case "term=insert":{
				System.out.println("insert acaaa");
			
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
				break;
			default:
				break;
			}
					
			for(int i = 0; i < result.size(); i++) {
	            System.out.println(result.get(i));
	        }
			return item;
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

	private void actualizarRanking(){
		
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