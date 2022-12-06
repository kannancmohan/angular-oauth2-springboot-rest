package com.kcm.test.dto;

public class FooDto {
    private Long id;
    private String name;

    public FooDto() {
        super();
    }

    public FooDto(final Long id, final String name) {
        super();

        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}