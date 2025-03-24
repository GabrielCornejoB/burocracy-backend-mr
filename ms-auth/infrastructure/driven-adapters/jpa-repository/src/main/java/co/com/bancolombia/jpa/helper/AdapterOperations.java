package co.com.bancolombia.jpa.helper;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.StreamSupport.stream;

public abstract class AdapterOperations<Model, Entity, Id, Repository extends CrudRepository<Entity, Id>
        & QueryByExampleExecutor<Entity>> {
    private final Class<Entity> dataClass;
    private final Function<Entity, Model> toEntityFn;
    protected Repository repository;
    protected ObjectMapper mapper;

    @SuppressWarnings("unchecked")
    protected AdapterOperations(Repository repository, ObjectMapper mapper, Function<Entity, Model> toEntityFn) {
        this.repository = repository;
        this.mapper = mapper;
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.dataClass = (Class<Entity>) genericSuperclass.getActualTypeArguments()[1];
        this.toEntityFn = toEntityFn;
    }

    protected Entity toEntity(Model model) {
        return mapper.map(model, dataClass);
    }

    protected Model toModel(Entity entity) {
        return entity != null ? toEntityFn.apply(entity) : null;
    }

    public Model saveModel(Model model) {
        Entity data = toEntity(model);
        return toModel(saveEntity(data));
    }

    protected List<Model> saveListOfModels(List<Model> models) {
        List<Entity> list = models.stream().map(this::toEntity).toList();
        return toList(saveEntity(list));
    }

    public List<Model> toList(Iterable<Entity> iterable) {
        return stream(iterable.spliterator(), false).map(this::toModel).toList();
    }

    protected Entity saveEntity(Entity entity) {
        return repository.save(entity);
    }

    protected Iterable<Entity> saveEntity(List<Entity> entities) {
        return repository.saveAll(entities);
    }

    public Model findById(Id id) {
        return toModel(repository.findById(id).orElse(null));
    }

    public List<Model> findByExample(Model model) {
        return toList(repository.findAll(Example.of(toEntity(model))));
    }


    public List<Model> findAll() {
        return toList(repository.findAll());
    }
}
