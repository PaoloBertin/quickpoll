package eu.opensource.quickpoll.web;

import eu.opensource.quickpoll.domain.Vote;
import eu.opensource.quickpoll.service.VoteService;
import eu.opensource.quickpoll.web.dto.VoteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/polls")
@RestController
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/{pollId}/votes")
    public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {

        log.debug("add vote to poll");
        log.debug("poll=" + pollId);
        log.debug("vote=" + vote);

        vote = voteService.saveVote(vote);

        // Set the headers for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest()
                                                               .path("/{id}")
                                                               .buildAndExpand(vote.getId())
                                                               .toUri());

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/{pollId}/votes")
    public ResponseEntity<List<VoteDto>> getAllVotesByPoll(@PathVariable Long pollId) {

        Iterable<Vote> votes = voteService.getVotesByPoll(pollId);
        List<VoteDto> voteDtos = new ArrayList<>();
        for (Vote vote : votes) {
            voteDtos.add(new VoteDto(vote));
        }

        return ResponseEntity.ok(voteDtos);
    }
}
