package eu.opensource.quickpoll.web.dto;

import eu.opensource.quickpoll.domain.Vote;
import lombok.Getter;

@Getter
public class VoteDto {

    private final Long voteId;

    private final Long optionId;

    private final String option;

    public VoteDto(Vote vote) {

        this.voteId = vote.getId();
        this.optionId = vote.getOption().getId();
        this.option = vote.getOption().getValue();
    }
}

//public record VoteDto(Long voteId,
//                      Long optionId,
//                      String option) {
//}
