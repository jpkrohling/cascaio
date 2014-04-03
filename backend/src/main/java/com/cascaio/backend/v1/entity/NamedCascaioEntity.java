package com.cascaio.backend.v1.entity;

import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@MappedSuperclass
public abstract class NamedCascaioEntity extends CascaioEntity {

    private String name;

    protected NamedCascaioEntity() {
    }

    public NamedCascaioEntity(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public NamedCascaioEntity(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NamedCascaioEntity)) return false;

        NamedCascaioEntity that = (NamedCascaioEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "NamedCascaioEntity{" +
                "name='" + name + '\'' +
                "entity='" + super.toString() + '\'' +
                '}';
    }
}
