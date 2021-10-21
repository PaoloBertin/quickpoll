package eu.opensource.quickpoll.web;

import eu.opensource.quickpoll.domain.Vote;
import eu.opensource.quickpoll.service.VoteService;
import eu.opensource.quickpoll.service.impl.util.OptionCount;
import eu.opensource.quickpoll.service.impl.util.VoteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ComputeResultController {

    private final VoteService voteService;

    @GetMapping("/computeresult")
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {

        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteService.getVotesByPoll(pollId);

        // Algorithm to count votes
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<>();
        for (Vote vote : allVotes) {
            totalVotes++;
            // Get the OptionCount corresponding to this Option
            OptionCount optionCount = tempMap.get(vote.getOption().getId());
            if (optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setOptionId(vote.getOption().getId());
                tempMap.put(vote.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount() + 1);
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());

        return ResponseEntity.ok(voteResult);
    }
}
