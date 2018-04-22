package xyz.vegaone.ready.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;


@EnableElasticsearchRepositories(basePackages = "xyz.vegaone.ready.repo")
@Configuration
public class ReadyConfig {

    private  final ElasticSearchProperties elasticSearchProperties;

    @Autowired
    public ReadyConfig(ElasticSearchProperties elasticSearchProperties) {
        this.elasticSearchProperties = elasticSearchProperties;
    }

    @Bean
    public Client client() throws Exception {
        Settings esSettings = Settings.builder()
                .put("cluster.name", elasticSearchProperties.getClusterName())
                .build();

        //https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
        TransportClient transportClient = new PreBuiltTransportClient(esSettings)
                .addTransportAddress(
                        new InetSocketTransportAddress(
                                InetAddress.getByName(elasticSearchProperties.getHost()), elasticSearchProperties.getPort()));

        return transportClient;

    }

    @Bean
    public ElasticsearchOperations elasticsearchOperations(Client client){

        ElasticsearchTemplate elasticsearchTemplate = new ElasticsearchTemplate(client);
        return elasticsearchTemplate;
    }
}
