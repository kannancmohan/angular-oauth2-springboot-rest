package com.kcm.test.service;

import com.kcm.test.model.Foo;
import java.util.Optional;



public interface FooService {
    Optional<Foo> findById(Long id);

    Foo save(Foo foo);
    
    Iterable<Foo> findAll();

}
