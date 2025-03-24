package co.com.bancolombia.jpa.adapters;

import co.com.bancolombia.jpa.entities.UserEntity;
import co.com.bancolombia.jpa.helper.AdapterOperations;
import co.com.bancolombia.jpa.repositories.AuthenticationJpaRepository;
import co.com.bancolombia.model.authentication.UserLogin;
import co.com.bancolombia.model.authentication.UserModel;
import co.com.bancolombia.model.authentication.gateways.AuthenticationRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public class AuthenticationAdapter extends AdapterOperations<UserModel, UserEntity, UUID, AuthenticationJpaRepository>
        implements AuthenticationRepository {

    protected AuthenticationAdapter(AuthenticationJpaRepository repository, ObjectMapper mapper) {
        super(repository, mapper, entity -> mapper.mapBuilder(entity, UserModel.UserModelBuilder.class).build());
    }

    @Transactional
    @Override
    public UserModel createUser(UserModel model) {
        return saveModel(model);
    }

    @Transactional(readOnly = true)
    @Override
    public UserModel findByCc(String cc) {
        return this.repository.findByCc(cc).map(this::toModel).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public UserModel validateCredentials(UserLogin credentials) {
        return this.repository.findByCcAndPassword(credentials.cc(), credentials.password())
                .map(this::toModel).orElse(null);
    }

}
