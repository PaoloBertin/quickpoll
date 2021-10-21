package eu.opensource.quickpoll.service;

import eu.opensource.quickpoll.domain.Poll;

import java.util.List;
import java.util.Optional;

public interface PollService {

    public Optional<Poll> getPollById(Long pollId);

    public List<Poll> getAllPolls();

    public Poll savePoll(Poll poll);

    public Poll updatePoll(Poll poll);

    public void deletePoll(Long pollId);
}
