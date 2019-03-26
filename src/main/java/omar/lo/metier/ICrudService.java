package omar.lo.metier;

import org.springframework.data.domain.Page;

import java.util.List;

public interface ICrudService<T, ID> {

    List<T> getAll();
    T getOne(ID id);
    T save(T entity);
    T update(T entity);
    void deleteOne(ID id);
    void deleteAll();
    Page<T> chercherParMotCle(String motCle, int page, int size);

}// ICrudService
