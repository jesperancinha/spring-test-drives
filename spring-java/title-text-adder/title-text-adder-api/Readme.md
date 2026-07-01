# Title text adder REST API

## Usefull deploy commads
* mvn tomcat7:deploy
* mvn tomcat7:undeploy
* mvn tomcat7:redeploy

## Usefull test commands
* Should not work: curl -H "Content-Type: application/json" -X POST http://localhost:8080/title-text-adder-api/rest/tta/titles/add -d '{ "title":"title"}'
* Should work: curl -H "Content-Type: application/json" -X POST http://localhost:8080/title-text-adder-api/rest/tta/titles/add -d '{ "title":"title", "text":"text"}'
* Test in the browser: http://localhost:8080/title-text-adder-api/rest/tta/titles/list/xt

## Usefull Solr commands
* ./solr create -c mysql-tta-indexing -d basic_configs

## Resources
* http://www.solrtutorial.com/solr-in-5-minutes.html
* http://blog.comperiosearch.com/blog/2014/08/28/indexing-database-using-solr/
* https://examples.javacodegeeks.com/enterprise-java/apache-solr/solr-dataimporthandler-example/

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
