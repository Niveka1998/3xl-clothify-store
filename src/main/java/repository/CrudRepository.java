package repository;

import java.util.List;

public interface CrudRepository<T,ID> {
    Boolean add(T entity);
    Boolean update(T entity);
    Boolean delete(ID id);
    T searchById(ID id);
    List<T> getAll();
}
//this is to prevent crud duplication.