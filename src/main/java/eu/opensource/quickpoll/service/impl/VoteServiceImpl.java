package eu.opensource.quickpoll.service.impl;


import eu.opensource.quickpoll.domain.Option;
import eu.opensource.quickpoll.domain.Poll;
import eu.opensource.quickpoll.domain.Vote;
import eu.opensource.quickpoll.repository.VoteRepository;
import eu.opensource.quickpoll.service.PollService;
import eu.opensource.quickpoll.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service("voteService")
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    private final PollService pollService;

    @Override
    public Vote saveVote(Vote vote) {

        return voteRepository.save(vote);
    }

    @Override
    public Iterable<Vote> getVotesByPoll(Long pollId) {

        Poll poll = pollService.getPollById(pollId);
        Set<Option> options = poll.getOptions();

        List<Vote> votes = new ArrayList<>();
        for (Option option : options) {
            List<Vote> votesOption = option.getVotes();
            for (Vote vote : votesOption) {
                votes.add(vote);
            }
        }
        return votes;
    }
}
