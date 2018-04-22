package xyz.vegaone.ready.repo;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.ready.domain.FileEsEntity;

@Repository
public class FileEsRepo extends SimpleElasticsearchRepository<FileEsEntity> {
    public FileEsRepo(ElasticsearchOperations elasticsearchOperations) {
        super(elasticsearchOperations);
    }
}
