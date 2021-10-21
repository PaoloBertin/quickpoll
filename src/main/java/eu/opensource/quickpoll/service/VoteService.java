package eu.opensource.quickpoll.service;

import eu.opensource.quickpoll.domain.Vote;

public interface VoteService {

    public Vote saveVote(Vote vote);

    public Iterable<Vote> getVotesByPoll(Long pollId);
}
