package dev.danuarta.votecounter.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "candidates")
public class Candidate implements Serializable {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "uuid", updatable = false, nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "name",unique = true)
    private String name;

    @OneToMany(mappedBy = "candidate")
    private Set<Vote> votes;

    public Candidate(String name) {
        this.name = name;
    }

    public Candidate() {}

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
