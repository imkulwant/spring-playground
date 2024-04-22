package com.kulsin.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.kulsin.repository")
@ComponentScan(basePackages = {"com.kulsin"})
public class Config {


    @Bean
    RestHighLevelClient client() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("http://localhost:9200")
                .build();

        return RestClients.create(clientConfiguration)
                .rest();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }

    /*
     * Spring Data Elasticsearch generally auto-creates indexes based on the entities in the project.
     * However, we can also create an index programmatically via the client template
     *
     * elasticsearchTemplate.indexOps(Article.class).create();
     *
     */
}
