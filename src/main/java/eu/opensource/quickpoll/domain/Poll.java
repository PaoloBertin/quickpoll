package eu.opensource.quickpoll.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name = "polls")
@Entity
public class Poll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @JoinTable(name = "poll_option",
            joinColumns = @JoinColumn(name = "poll_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id"))
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Option> options = new HashSet<>();

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getQuestion() {

        return question;
    }

    public void setQuestion(String question) {

        this.question = question;
    }

    public Set<Option> getOptions() {

        return options;
    }

    public void setOptions(Set<Option> options) {

        this.options = options;
    }
}
