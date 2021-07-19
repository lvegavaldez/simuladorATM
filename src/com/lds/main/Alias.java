package com.lds.main;

public class Alias {
    String alias;

    public Alias(String alias) {
        this.alias = alias;
    }

    public static Alias crear() {
        return new Alias("como.perro.gato");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alias)) return false;

        Alias alias1 = (Alias) o;

        return alias != null ? alias.equals(alias1.alias) : alias1.alias == null;
    }

    @Override
    public int hashCode() {
        return alias != null ? alias.hashCode() : 0;
    }
}
