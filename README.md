# Building a Concurrent Data Pipeline Using Amazon EMR and Apache Livy
This code demonstrates the architecture featured on the AWS Big Data blog (<link>)
which creates a concurrent data pipeline by using Amazon EMR and Apache Livy. This pipeline is orchestrated by Apache Airflow.

### Prerequisites
* You must have an AWS account

### Steps
1. Run the cloudformation template to create the Airflow server.
2. Login to the EC2 machine
3. sudo
    ``````
    sudo su
    cd ~/airflow
    ```
4. create a 'dags' directory
    ``````
    mkdir dags
    cd dags/
    mkdir lib
    ```
5. Copy emr_lib.py here. TODO: Make EMR key name a parameter.
__init__.py
