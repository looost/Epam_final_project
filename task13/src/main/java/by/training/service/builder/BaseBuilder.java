package by.training.service.builder;

import by.training.entity.Serial;

import java.util.Set;

public abstract class BaseBuilder {

    protected Set<Serial> serials;

    public Set<Serial> getSerials() {
        return serials;
    }

    public abstract void buildSetSerials(String fileName);
}
