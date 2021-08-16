package dev.danuarta.votecounter.service;

import dev.danuarta.votecounter.dto.CandidateRequest;
import dev.danuarta.votecounter.dto.VoteRequest;
import dev.danuarta.votecounter.dto.VoteResult;
import dev.danuarta.votecounter.model.Candidate;
import dev.danuarta.votecounter.model.Vote;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.UUID;

public interface VoteCounterService {
    Candidate InsertCandidate (CandidateRequest request) throws PersistenceException;
    List<Candidate> GetAllCandidate ();
    Candidate GetCandidateByUUID (UUID uuid);
    void DeleteCandidateByUUID (UUID uuid);
    Candidate UpdateCandidateByUUID (UUID uuid, CandidateRequest request) throws PersistenceException;
    VoteResult GetVoteResult ();
    Vote InsertVote (VoteRequest request) throws PersistenceException;
    List<Vote> GetAllVote ();
}
