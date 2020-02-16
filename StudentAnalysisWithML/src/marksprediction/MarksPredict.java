package marksprediction;

import org.apache.spark.ml.regression.LinearRegression;
import org.apache.spark.ml.regression.LinearRegressionModel;
import org.apache.spark.ml.regression.LinearRegressionTrainingSummary;
import org.apache.spark.ml.feature.VectorAssembler;

import java.util.List;

import org.apache.spark.ml.evaluation.RegressionEvaluator;
import org.apache.spark.ml.linalg.*;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class MarksPredict {
	//public static void main(String [] args) {
	public LinearRegressionModel predictionModel(String subject_name) {
		//String subject_name = "ComputerNetworks";
		SparkSession session = SparkSession.builder().appName("Marks").master("local[*]").getOrCreate();
		Dataset<Row> training = session.read()
				  .format("jdbc")
				  .option("url", "jdbc:mysql://localhost:3306/college")
				  .option("dbtable", subject_name)
				  .option("driver", "com.mysql.jdbc.Driver")
				  .option("user", "root")
				  .option("password", "ibmlenovo")
				  .load();
		
		training = training.withColumn("visited_resources_", training.col("visited_resources").cast("integer"))
							.withColumn("attendence_", training.col("attendence").cast("float"))
							.withColumn("discipline_score_", training.col("discipline_score").cast("integer"))
							.withColumn("internal_marks_", training.col("internal_marks").cast("integer"))
							.withColumn("external_marks_", training.col("external_marks").cast("integer"))
							.withColumn("interest_level_", training.col("interest_level").cast("integer"))
							.withColumn("doubt_asked_", training.col("doubt_asked").cast("integer"))
							.drop("visited_resources", "attendence", "discipline_score", "internal_marks", "external_marks", "interest_level", "doubt_asked");
		
		String columnNames[] = {"external_marks_", "internal_marks_"};
		training = training.na().fill(0, columnNames);
		
		
		//training.show((int)training.count(), false);
		//training.printSchema();
		
		String featureCols[] = {"visited_resources_","doubt_asked_","attendence_", "discipline_score_","internal_marks_","interest_level_"};
		
		
		VectorAssembler assembler = new VectorAssembler().setInputCols(featureCols).setOutputCol("features");
		
		Dataset<Row> trained = assembler.transform(training);
		trained = trained.select("features", "external_marks_");
		//trained.show(3);
		
			
		LinearRegression lr = new LinearRegression()
				.setMaxIter(1000)
				.setRegParam(0.2)
				.setElasticNetParam(0.8)
				.setFeaturesCol("features")
				.setLabelCol("external_marks_");
		trained.show(10,10,true);
		LinearRegressionModel lrModel = lr.fit(trained);
		
		System.out.println("Coefficients: "
				  + lrModel.coefficients() + " Intercept: " + lrModel.intercept());
		// Summarize the model over the training set and print out some metrics.
		//LinearRegressionTrainingSummary trainingSummary = lrModel.summary();
		//System.out.println("numIterations: " + trainingSummary.totalIterations());
		//System.out.println("objectiveHistory: " + Vectors.dense(trainingSummary.objectiveHistory()));
		//trainingSummary.residuals().show();
		//System.out.println("RMSE: " + trainingSummary.rootMeanSquaredError());
		//System.out.println("r2: " + trainingSummary.r2());
		//trained.drop("external_marks_");
		//Dataset<Row> lr_predictions =  lrModel.transform(trained);
		//lr_predictions.select("prediction","external_marks_","features").show((int)lr_predictions.count(), false);
		
		//double r2 = new RegressionEvaluator().setPredictionCol("prediction").setLabelCol("external_marks_").setMetricName("r2").evaluate(lr_predictions);
		
		//System.out.println("Predicted for "+trained.count()+" Students"+"\nAccuracy R2 "+ r2);

		System.out.println("Model Returned");
		return lrModel;
		}
	
		public Dataset<Row> marksPrediction(String subject_name, String filePath) {
			
			LinearRegressionModel model = predictionModel(subject_name);
			SparkSession session = SparkSession.builder().appName("Marks").master("local[*]").getOrCreate();
			
			
			//String filePath = "E:\\SparkProject\\RandomGenratedData\\Prediction"+subject_name+"CSV.csv";
			
			Dataset<Row> dataForPrediction = session.read().format("csv").option("header", true).load(filePath);
			
			dataForPrediction = dataForPrediction.withColumn("visited_resources_", dataForPrediction.col("visited_resources").cast("integer")).drop("visited_resources").withColumnRenamed("visited_resources_", "visited_resources")
					.withColumn("attendence_", dataForPrediction.col("attendence").cast("float")).drop("attendence").withColumnRenamed("attendence_", "attendence")
					.withColumn("discipline_score_", dataForPrediction.col("discipline_score").cast("integer")).drop("discipline_score").withColumnRenamed("discipline_score_", "discipline_score")
					.withColumn("internal_marks_", dataForPrediction.col("internal_marks").cast("integer")).drop("internal_marks").withColumnRenamed("internal_marks_", "internal_marks")
					.withColumn("interest_level_", dataForPrediction.col("interest_level").cast("integer")).drop("interest_level").withColumnRenamed("interest_level_", "interest_level")
					.withColumn("doubt_asked_", dataForPrediction.col("doubt_asked").cast("integer")).drop("doubt_asked").withColumnRenamed("doubt_asked_", "doubt_asked");
			
				
			String featureCols1[] = {"visited_resources","doubt_asked","attendence", "discipline_score","internal_marks","interest_level"};
			
			String columnNames1[] = {"internal_marks"};
			dataForPrediction = dataForPrediction.na().fill(0, columnNames1);

			//dataForPrediction.show((int)dataForPrediction.count(),false);

			VectorAssembler assembler1 = new VectorAssembler().setInputCols(featureCols1).setOutputCol("features");
			Dataset<Row> dataForPrediction1 = assembler1.transform(dataForPrediction);
			
			Dataset<Row> predictedMarks = model.transform(dataForPrediction1);
			
			predictedMarks = predictedMarks.withColumn("predicted_marks", predictedMarks.col("prediction").cast("integer"));
			Dataset<Row> finalData = predictedMarks.select("student_id","branch_id","visited_resources","attendence","discipline_score","internal_marks" ,"interest_level","doubt_asked", "predicted_marks");
			
			finalData.show((int)finalData.count(),false);
			return finalData;
			
		}
}
