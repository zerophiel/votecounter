package dev.danuarta.votecounter.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "votes")
public class Vote implements Serializable {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "uuid", updatable = false, nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "voter_phone_number",unique = true)
    private String voterPhoneNumber;

    @Column(name = "voter_name")
    private String voterName;

    @ManyToOne
    @JoinColumn(name = "candidate_uuid",nullable = false)
    private Candidate candidate;

    public Vote(String voterPhoneNumber, String voterName, Candidate candidate) {
        this.voterPhoneNumber = voterPhoneNumber;
        this.voterName = voterName;
        this.candidate = candidate;
    }

    public Vote () {

    }

    public UUID getUuid() {
        return uuid;
    }

    public String getVoterPhoneNumber() {
        return voterPhoneNumber;
    }

    public void setVoterPhoneNumber(String voterPhoneNumber) {
        this.voterPhoneNumber = voterPhoneNumber;
    }

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
