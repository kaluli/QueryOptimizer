package com.trabajofinal.mahout;

import java.util.List;

import org.apache.hadoop.io.FloatWritable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.common.FullRunningAverage;
import org.apache.mahout.cf.taste.impl.common.RunningAverage;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericRecommendedItem;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.common.RandomUtils;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.trabajofinal.model.Configuracion;
import com.trabajofinal.model.Consulta;

public class MachineLearning{

	public MachineLearning() {
	}

	public void analizar(Consulta consulta, Configuracion config) {
		RandomUtils.useTestSeed();
		
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName("localhost");
		dataSource.setUser("root");
		dataSource.setPassword("kaluli32");
		dataSource.setDatabaseName(config.getName());
		JDBCDataModel model = new MySQLJDBCDataModel(dataSource);
		
		RunningAverage averageRating = new FullRunningAverage();
		//PreferenceArray prefs= model.getPreferencesFromUser("time");
		
		
		
		
	}
	
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

}