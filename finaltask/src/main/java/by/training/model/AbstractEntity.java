package by.training.model;


import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = -7967871648368085337L;
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
