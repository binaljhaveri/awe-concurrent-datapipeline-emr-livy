val tags_df = spark.read.format("csv").option("header", "true").option("inferschema", "true").load("s3://binal-workspace/data/movielens/tags.csv")

tags_df.write.mode("overwrite").parquet("s3://binal-workspace/data/movielens-parquet/tags/")
