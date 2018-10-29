FROM payara/server-web:latest

ADD drivers/mysql-connector-java-8.0.12.jar /payara5/javadb/lib/

RUN echo "AS_ADMIN_PASSWORD=glassfish" < /tmp/gfpw.txt && \
    ./payara5/bin/asadmin start-domain domain1 && \
    ./payara5/bin/asadmin/payara5/bin/asadmin --interactive=false --user admin --passwordfile=/tmp/gfpw.txt create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource --property user=root:password=adminpw:DatabaseName=AMTProjectDatabase:ServerName=db:port=3306 pool_AMTProject && \
    ./payara5/bin/asadmin --interactive=false --user admin --passwordfile=/tmp/gfpw.txt create-jdbc-resource --connectionpoolid pool_AMTProject jdbc/AMTProject

RUN ./payara5/bin/asadmin stop-domain domain1