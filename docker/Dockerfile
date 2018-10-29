FROM payara/server-web:latest

ADD drivers/mysql-connector-java-8.0.12.jar /payara5/javadb/lib/

RUN echo "AS_ADMIN_PASSWORD=glassfish" < /opt/pwdfile && \
    ./bin/asadmin start-domain domain1 && \
    ./bin/asadmin --interactive=false --user admin --passwordfile=/opt/pwdfile create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource --property user=root:password=adminpw:DatabaseName=AMTProjectDatabase:ServerName=db:port=3306 pool_AMTProject && \
    ./bin/asadmin --interactive=false --user admin --passwordfile=/opt/pwdfile create-jdbc-resource --connectionpoolid pool_AMTProject jdbc/AMTProject &&\
    ./bin/asadmin stop-domain domain1
