# Building a Concurrent Data Orchestration Pipeline Using Amazon EMR and Apache Livy
This code demonstrates the architecture featured on the AWS Big Data blog (<link>)
which creates a concurrent data pipeline by using Amazon EMR and Apache Livy. This pipeline is orchestrated by Apache Airflow.

### Prerequisites
* You must have an AWS account

### How to get the movielens data?
1. Download the full movielens data (FileName: ml-latest.zip) from https://grouplens.org/datasets/movielens/latest/.
    <img width="887" alt="screen shot 2018-03-28 at 11 52 53 pm" src="https://user-images.githubusercontent.com/36875404/38074345-51e16f3a-32e3-11e8-8317-b6bd60627b9e.png">
2. Unzip it. You should see 6 .csv files (tags.csv, genome-tags.csv, links.csv, movies.csv, ratings.csv, genome-scores.csv).
3. Create an S3 bucket and upload all the files to that bucket. Make a note of the S3 path. It will be needed later in the process.

### How to run the pipeline?
1. Create a keypair which will be used to ssh into the Airflow EC2 instance as well as to spin up the EMR cluster. Keep a note of the name of the EC2 keypair. Go to EC2 --> Network & Security --> Key Pairs
 <img width="177" alt="keypair" src="https://user-images.githubusercontent.com/36875404/38074782-df2a44f6-32e4-11e8-9209-f2e4745ba2e1.png">
2. Run the cloudformation template (airflow.yaml) from this github project to create the Airflow server.
  * It will ask you to choose a keypair. Select the one you created in Step 1.
  * For the other parameters, I have chosen default values but feel free to change them.
3. Once the CloudFormation stack is spun up, login to the EC2 instance.
   ```
   ssh -i <Private key> ec2-user@public-ip
   ```
   
   * Now you need to run some commands as the root user.
   ```
    sudo su
    cd ~/airflow
    # Install git
    yum install -y git
    # Clone the repository
    git clone <Clone-URL>
    # Move all the files to the ~/airflow directory
    mv aws-concurrent-data-orchestration-pipeline-emr-livy/* ~/airflow/
    rm -rf aws-concurrent-data-orchestration-pipeline-emr-livy
    ```
   * Go to the emr_lib.py file which is located in dags/airflowlib folder and change the EMR key pair name (Line 30) to the name of the keypair that you created in Step 1.
   * Change the S3 path to the path of the respective .csv files in each of the .scala files (located in the transform folder)
   * Source the bash_profile
   ```
   source ~/.bash_profile
   ```
   * Start the airflow scheduler
   ```
   airflow scheduler
   ```
4. For the purposes of the blog, I have made the Airflow webserver open to the world so you should be able to access it. Open any browser and go to <EC2-public-ip>:8080. You should see the DAG named as 'transform_movielens' along with some example DAGS.
5. Click on the run button and hopefully you should see your DAG running.
