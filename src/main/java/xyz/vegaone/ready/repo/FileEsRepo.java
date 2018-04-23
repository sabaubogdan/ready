package xyz.vegaone.ready.repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.ready.domain.FileEsEntity;

@Repository
public interface FileEsRepo extends ElasticsearchCrudRepository<FileEsEntity, String> {
}
