package eu.opensource.quickpoll.service;

import eu.opensource.quickpoll.domain.Poll;

import java.util.List;

public interface PollService {

    public Poll getPollById(Long pollId);

    public List<Poll> getAllPolls();

    public Poll savePoll(Poll poll);

    public Poll updatePoll(Poll poll);

    public void deletePoll(Long pollId);
}
