package dev.danuarta.votecounter.dto;

public class CandidateStanding {
    public String candidateName;
    public int candidateCount;
    public double percentage;

    public CandidateStanding(String candidateName, int candidateCount, double percentage) {
        this.candidateName = candidateName;
        this.candidateCount = candidateCount;
        this.percentage = percentage;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public int getCandidateCount() {
        return candidateCount;
    }

    public void setCandidateCount(int candidateCount) {
        this.candidateCount = candidateCount;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
