package eu.opensource.quickpoll.service.impl;

import eu.opensource.quickpoll.domain.Option;
import eu.opensource.quickpoll.domain.Poll;
import eu.opensource.quickpoll.repository.OptionRepository;
import eu.opensource.quickpoll.repository.PollRepository;
import eu.opensource.quickpoll.service.PollService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;

    private final OptionRepository optionRepository;

    @Transactional(readOnly = true)
    @Override
    public Poll getPollById(Long pollId) {

        return pollRepository.findById(pollId).orElseThrow();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Poll> getAllPolls() {

        return pollRepository.findAll();
    }

    @Override
    public Poll savePoll(Poll poll) {

        if (poll.getId() != null) {
            // TODO Exception
        }

        return pollRepository.save(poll);
    }

    @Override
    public Poll updatePoll(Poll poll) {

        if (poll.getId() == null) {
            // TODO Exception
        }

        return pollRepository.save(poll);
    }

    @Override
    public void deletePoll(Long pollId) {

        Poll poll = pollRepository.findById(pollId).orElseThrow();
        Set<Option> options = poll.getOptions();
        for (Option option : options) {
            optionRepository.delete(option);
        }
        poll = pollRepository.findById(pollId).orElseThrow();
        pollRepository.delete(poll);
    }
}
