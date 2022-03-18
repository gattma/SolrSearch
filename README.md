# SolrSearch

````helm repo add gp-helm-charts https://gepaplexx.github.io/gp-helm-charts/```  
```helm install solr-search-service gepaplexx/gp-bke-runtime-minimal-application -n contest --set image.name=ghcr.io/gattma/solrsearchservice-develop --set image.tag=sha-b750193 --set route.enabled=true```  
oder   
```helm install solr-search-service gepaplexx/gp-bke-runtime-minimal-application -n contest -f SolrSearchService/src/main/k8s/values.yaml```
``````
``````