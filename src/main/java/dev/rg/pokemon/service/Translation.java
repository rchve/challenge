package dev.rg.pokemon.service;

enum Translation {
    SHAKESPEARE("shakespeare"),
    YODA("yoda");

    private final String name;

    Translation(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
