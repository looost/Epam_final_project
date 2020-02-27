package by.training.entity;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 3311210969816860202L;

    protected int id;

    public AbstractEntity() {
    }

    public AbstractEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity abstractEntity = (AbstractEntity) o;

        return id == abstractEntity.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
