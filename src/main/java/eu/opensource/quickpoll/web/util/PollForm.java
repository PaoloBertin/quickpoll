package eu.opensource.quickpoll.web.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PollForm {

    private Long pollId;

    private String question;

    private List<Long> options;
}
