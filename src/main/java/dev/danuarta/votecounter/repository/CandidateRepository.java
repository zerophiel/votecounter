package dev.danuarta.votecounter.repository;

import dev.danuarta.votecounter.model.Candidate;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CandidateRepository implements PanacheRepository<Candidate> {
}
