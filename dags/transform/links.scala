val links_df = spark.read.format("csv").option("header", "true").option("inferschema", "true").load("s3://binal-workspace/data/movielens/links.csv")

links_df.write.mode("overwrite").parquet("s3://binal-workspace/data/movielens-parquet/links/")
