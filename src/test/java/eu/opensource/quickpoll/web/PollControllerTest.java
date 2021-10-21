package eu.opensource.quickpoll.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import eu.opensource.quickpoll.domain.Option;
import eu.opensource.quickpoll.domain.Poll;
import eu.opensource.quickpoll.repository.OptionRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PollControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private OptionRepository optionRepository;

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getAllPolls() throws Exception {

        mvc.perform(get("/polls"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", hasSize(1)))
           .andExpect(jsonPath("$[0].question", equalTo("Who will win SuperBowl this year?")))
           .andDo(print())
        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void createPollTest() throws Exception {

        Option option1 = new Option("Inter");
        Option option2 = new Option("Milan");
        Option option3 = new Option("Juventus");

        Set<Option> options = new HashSet<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);

        Poll poll = new Poll();
        poll.setQuestion("Chi vince lo scudetto");
        poll.setOptions(options);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false); // ???
        String requestJson = objectMapper.writeValueAsString(poll);

        mvc.perform(post("/polls").contentType(MediaType.APPLICATION_JSON)
                                  .content(requestJson))

           .andExpect(status().is2xxSuccessful())
//           .andExpect(jsonPath("", equalTo("")))
           .andDo(print())
        ;
    }

    @Disabled
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void createPollWithFormTest() throws Exception {

        Option option1 = optionRepository.save(new Option("Inter"));
        Option option2 = optionRepository.save(new Option("Milan"));
        Option option3 = optionRepository.save(new Option("Juventus"));

        Set<Option> options = new HashSet<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);

        Poll poll = new Poll();
        poll.setQuestion("Chi vince lo scudetto");
        poll.setOptions(options);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false); // ???
        String requestJson = objectMapper.writeValueAsString(poll);

        mvc.perform(post("/polls").contentType(MediaType.APPLICATION_JSON)
                                  .content(requestJson))

           .andExpect(status().is2xxSuccessful())
           .andExpect(jsonPath("", equalTo("")))
           .andDo(print())
        ;
    }
}