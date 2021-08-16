package dev.danuarta.votecounter.service;

import dev.danuarta.votecounter.dto.CandidateRequest;
import dev.danuarta.votecounter.dto.CandidateStanding;
import dev.danuarta.votecounter.dto.VoteRequest;
import dev.danuarta.votecounter.dto.VoteResult;
import dev.danuarta.votecounter.model.Candidate;
import dev.danuarta.votecounter.model.Vote;
import dev.danuarta.votecounter.repository.CandidateRepository;
import dev.danuarta.votecounter.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.*;

@ApplicationScoped
public class VoteCounterServiceBean implements VoteCounterService {
    @Inject
    CandidateRepository candidateRepository;

    @Inject
    VoteRepository voteRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteCounterServiceBean.class);

    public Candidate InsertCandidate (CandidateRequest request) throws PersistenceException {
        Candidate candidateData = new Candidate(request.name);
        candidateRepository.persistAndFlush(candidateData);
        LOGGER.debug("Saving Candidate Data to Database :",candidateData);
        return candidateData;
    }

    public List<Candidate> GetAllCandidate () {
        LOGGER.debug("Getting All Candidate Data from Database");
        return candidateRepository.listAll();
    }

    public Candidate GetCandidateByUUID (UUID uuid) {
        LOGGER.debug("Getting Single Candidate Data from Database :",uuid);
        return candidateRepository.find("uuid",uuid).firstResult();
    }

    public void DeleteCandidateByUUID (UUID uuid) {
        LOGGER.debug("Deleteing Data from Database :",uuid);
        candidateRepository.delete("uuid",uuid);
    }

    public Candidate UpdateCandidateByUUID (UUID uuid, CandidateRequest request) throws PersistenceException {
        Candidate candidateData = candidateRepository.find("uuid",uuid).firstResult();
        candidateData.setName(request.name);
        candidateRepository.persistAndFlush(candidateData);
        LOGGER.debug("Updating Data to Database :",candidateData);
        return candidateData;
    }

    public Vote InsertVote (VoteRequest request) throws PersistenceException {
        Candidate candidateData = candidateRepository.find("uuid",request.candidateUUID).firstResult();
        Vote voteData = new Vote(request.voterPhoneNumber, request.voterName, candidateData);
        voteRepository.persistAndFlush(voteData);
        LOGGER.debug("Saving Vote Data to Database :",voteData);
        return voteData;
    }

    public List<Vote> GetAllVote () {
        LOGGER.debug("Getting All Vote Data from Database");
        return voteRepository.listAll();
    }

    public VoteResult GetVoteResult () {
        List<Vote> voteDataList = voteRepository.listAll();
        int totalVotes = voteDataList.size();
        List<Map> list = new ArrayList();
        VoteResult voteResultData = new VoteResult();
        Map<String,Integer> nMap = new HashMap();
        for (int i = 0;i<totalVotes;i++) {
            String candidate = voteDataList.get(i).getCandidate().getName();
            if (nMap.containsKey(candidate)) {
                nMap.put(candidate,nMap.get(candidate)+1);
            } else {
                nMap.put(candidate,1);
            }
        }
        List<String> l = new ArrayList<String>(nMap.keySet());
        List <CandidateStanding> standingList = new ArrayList<>();
        for (int k = 0; k < l.size(); k++) {
            String name = l.get(k);
            int count = nMap.get(l.get(k));
            double percentage = ((double) count / (double) totalVotes) * 100;
            CandidateStanding standing = new CandidateStanding(name,count,percentage);
            standingList.add(standing);
        }
        voteResultData.setTotalVotes(totalVotes);
        voteResultData.setCandidateStandings(standingList);
        return voteResultData;
    }
}
