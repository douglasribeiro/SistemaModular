package douglas.develop.service;

import org.springframework.boot.configurationprocessor.json.JSONException;

import java.util.List;

public interface GenericService<T> {

    List<T> list() throws JSONException;

    T findById(Long id) throws JSONException;

    T save(T t) throws JSONException;

    void update(Long id, T t) throws JSONException;

    void delete(Long id);

}
