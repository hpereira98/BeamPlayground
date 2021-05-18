/**
 *
 *
 * @author <a href="mailto:henrique.pereira@xpand-it.com">Henrique Pereira</a>
 * @version \$Revision: 666 $
 */

import org.apache.beam.sdk.Pipeline
import org.apache.beam.sdk.io.TextIO
import org.apache.beam.sdk.options.PipelineOptions
import org.apache.beam.sdk.options.PipelineOptionsFactory
import org.apache.beam.sdk.values.PCollection

/**
 * Playground application to test ApacheBeam pipelines easily.
 *
 * By default, the DirectRunner is used, but you can also use other Runners. Just import them using Maven
 * and configure the pipeline accordingly.
 *
 */
object BeamPlayground extends App{
  println("Welcome to the Apache Beam Playground!")

  // Start by defining the options for the pipeline
  val options: PipelineOptions = PipelineOptionsFactory.create
  // Then create the pipeline.
  val pipeline: Pipeline = Pipeline.create(options)

  // Read data from files (this will create a collection of lines from file)
  val google_apps_collection: PCollection[String] = pipeline.apply(
    "Read googleplaystore.csv", TextIO.read().from("src/main/resources/googleplaystore.csv"))

  val google_reviews_collection: PCollection[String] = pipeline.apply(
    "Read googleplaystore_user_reviews.csv", TextIO.read().from("src/main/resources/googleplaystore_user_reviews.csv"))

  // Apply transformations here!
  // ...

  print(google_apps_collection)

  // Run pipeline (and wait for it to finish)
  pipeline.run.waitUntilFinish

}
