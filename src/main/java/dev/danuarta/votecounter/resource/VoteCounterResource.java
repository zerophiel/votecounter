package dev.danuarta.votecounter.resource;

import dev.danuarta.votecounter.dto.CandidateRequest;
import dev.danuarta.votecounter.dto.VoteRequest;
import dev.danuarta.votecounter.model.Candidate;
import dev.danuarta.votecounter.model.Vote;
import dev.danuarta.votecounter.service.VoteCounterService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@ApplicationScoped
@Path(VoteCounterResourcePath.BASE_PATH)
public class VoteCounterResource {
    @Inject
    VoteCounterService voteCounterService;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(VoteCounterResourcePath.CANDIDATE_PATH)
    public Response InsertCandidate (CandidateRequest request) {
        try {
            Candidate responseData = voteCounterService.InsertCandidate(request);
            return Response
                    .status(201)
                    .entity(responseData)
                    .build();
        } catch (PersistenceException pe) {
            return Response
                    .status(401)
                    .entity(pe)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(VoteCounterResourcePath.CANDIDATE_PATH)
    public Response GetAllCandidate () {
        return Response
                .status(200)
                .entity(voteCounterService.GetAllCandidate())
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(VoteCounterResourcePath.CANDIDATE_PATH+"/{uuid}")
    public Response GetCandidateByUUID (@PathParam("uuid") String uuid) {
        return Response
                .status(200)
                .entity(voteCounterService.GetCandidateByUUID(UUID.fromString(uuid)))
                .build();
    }

    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Path(VoteCounterResourcePath.CANDIDATE_PATH+"/{uuid}")
    public Response DeleteCandidateByUUID (@PathParam("uuid") String uuid) {
        Candidate candidateData = voteCounterService.GetCandidateByUUID(UUID.fromString(uuid));
        if (candidateData != null) {
            voteCounterService.DeleteCandidateByUUID(UUID.fromString(uuid));
            return Response
                    .status(200)
                    .entity(candidateData)
                    .build();
        } else {
            return Response
                    .status(400)
                    .build();
        }
    }

    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(VoteCounterResourcePath.CANDIDATE_PATH+"/{uuid}")
    public Response UpdateCandidateByUUID (@PathParam("uuid") String uuid, CandidateRequest request) {
        Candidate candidateDatacheck = voteCounterService.GetCandidateByUUID(UUID.fromString(uuid));
        if (candidateDatacheck != null) {
            Candidate candidatedata = voteCounterService.UpdateCandidateByUUID(UUID.fromString(uuid), request);
            return Response
                    .status(200)
                    .entity(candidatedata)
                    .build();
        } else {
            return Response
                    .status(400)
                    .build();
        }
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(VoteCounterResourcePath.VOTE_PATH)
    public Response InsertVote (VoteRequest request) {
        if (request.voterPhoneNumber.startsWith("08")) {
            if (request.voterPhoneNumber.length()>5) {
                try {
                    Vote responseData = voteCounterService.InsertVote(request);
                    return Response
                            .status(201)
                            .entity(responseData)
                            .build();
                } catch (PersistenceException pe) {
                    return Response
                            .status(401)
                            .entity(pe)
                            .build();
                }
            } else {
                return Response
                        .status(400)
                        .build();
            }
        } else {
            return Response
                    .status(400)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(VoteCounterResourcePath.VOTE_ADMIN_PATH)
    public Response GetAllVote () {
        return Response
                .status(200)
                .entity(voteCounterService.GetAllVote())
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(VoteCounterResourcePath.VOTE_PATH)
    public Response GetVoteResult () {
        return Response
                .status(200)
                .entity(voteCounterService.GetVoteResult())
                .build();
    }
}
