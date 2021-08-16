package dev.danuarta.votecounter.dto;

import dev.danuarta.votecounter.model.Candidate;

import java.util.List;

public class VoteResult {
    public List<CandidateStanding> candidateStandings;
    public int totalVotes;

    public VoteResult(List<CandidateStanding> candidateStandings, int totalVotes) {
        this.candidateStandings = candidateStandings;
        this.totalVotes = totalVotes;
    }

    public VoteResult () {}

    public List<CandidateStanding> getCandidateStandings() {
        return candidateStandings;
    }

    public void setCandidateStandings(List<CandidateStanding> candidateStandings) {
        this.candidateStandings = candidateStandings;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }
}
