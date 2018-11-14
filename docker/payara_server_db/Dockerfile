FROM payara/server-full:latest

ADD drivers/mysql-connector-java-8.0.12.jar javadb/lib

COPY grizzly-npn-bootstrap.jar /opt/payara5/glassfish/lib/grizzly-npn-bootstrap.jar

RUN echo "AS_ADMIN_PASSWORD=glassfish" < /opt/pwdfile && \
    ./bin/asadmin start-domain domain1 && \
\
    ./bin/asadmin --interactive=false --user admin --passwordfile=/opt/pwdfile \ 
    create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource \ 
    --property user=root:password=root:DatabaseName=AMTProjectDatabase:ServerName=db:port=3306:useSSL=false pool_AMTProject && \
\
    ./bin/asadmin --interactive=false --user admin --passwordfile=/opt/pwdfile create-jdbc-resource --connectionpoolid pool_AMTProject jdbc/AMTProject && \
\
    ./bin/asadmin --interactive=false --user admin --passwordfile=/opt/pwdfile \
    create-javamail-resource --mailhost smtp.gmail.com --mailuser amt.projet --fromaddress amt.projet@gmail.com  \
    --property mail.smtp.user=amt.projet@gmail.com:mail.smtp.password=aamt1234:mail.smtp.auth=true:mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory:mail.smtp.socketFactory.port=465:mail.smtp.port=465 mail/amt && \
\
    ./bin/asadmin stop-domain domain1
