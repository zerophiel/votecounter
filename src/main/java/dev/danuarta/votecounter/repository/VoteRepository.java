package dev.danuarta.votecounter.repository;

import dev.danuarta.votecounter.model.Vote;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VoteRepository implements PanacheRepository<Vote> {
}
