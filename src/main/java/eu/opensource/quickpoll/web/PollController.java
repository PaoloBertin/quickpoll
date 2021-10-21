package eu.opensource.quickpoll.web;

import eu.opensource.quickpoll.domain.Poll;
import eu.opensource.quickpoll.exception.ResourceNotFoundException;
import eu.opensource.quickpoll.service.PollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/polls")
@RequiredArgsConstructor
@RestController
public class PollController {

    private final PollService pollService;

    @GetMapping("/{pollId}")
    public ResponseEntity<Poll> getPollById(@PathVariable Long pollId) {

        Optional<Poll> poll = pollService.getPollById(pollId);
        if(! poll.isPresent()) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }

        log.debug("return poll with id = " + pollId);

        return ResponseEntity.ok(poll.get());
    }

    @GetMapping
    public ResponseEntity<List<Poll>> getAllPolls() {

        List<Poll> polls = pollService.getAllPolls();
        log.debug("returns all polls");

        return ResponseEntity.ok(polls);
    }

    @PostMapping
    public ResponseEntity<?> createPoll(@RequestBody Poll poll) {

        poll = pollService.savePoll(poll);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest()
                                                               .path("/{id}")
                                                               .buildAndExpand(poll.getId()).toUri());

        log.debug("created resource with uri =" + responseHeaders.getLocation());

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{pollId}")
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {

        log.debug("edit record " + pollId);

        pollService.savePoll(poll);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {

        pollService.deletePoll(pollId);

        return ResponseEntity.ok().build();
    }
}
