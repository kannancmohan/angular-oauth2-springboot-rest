package com.kcm.test.service.impl;

import com.kcm.test.model.Foo;
import com.kcm.test.service.FooService;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class FooServiceImpl implements FooService {

    private static final HashMap<Long,Foo> TEMP_FOO_MAP = new HashMap<>();

    @Override
    public Optional<Foo> findById(Long id) {
        if(TEMP_FOO_MAP.containsKey(id)){
            return Optional.of(TEMP_FOO_MAP.get(id));
        }else if(id !=null){
            final Foo foo = new Foo(id, "name_" + id);
            TEMP_FOO_MAP.put(id,foo);
            return Optional.of(foo);
        }
        return Optional.empty();
    }

    @Override
    public Foo save(Foo foo) {
        final Long id = foo.getId();
        if(id == null){
            return null;
        }
        TEMP_FOO_MAP.put(id,foo);
        return TEMP_FOO_MAP.get(id);
    }

    @Override
    public Iterable<Foo> findAll() {
        return TEMP_FOO_MAP.values();
    }
}
