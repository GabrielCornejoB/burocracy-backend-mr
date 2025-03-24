package co.com.bancolombia.jpa.repositories;

import co.com.bancolombia.jpa.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;
import java.util.UUID;

public interface AuthenticationJpaRepository extends CrudRepository<UserEntity, UUID>,
        QueryByExampleExecutor<UserEntity> {

    Optional<UserEntity> findByCc(String cc);

    Optional<UserEntity> findByCcAndPassword(String cc, String password);

}
