package eu.opensource.quickpoll.domain;

import java.util.List;

import javax.persistence.*;

@Table(name = "options")
@Entity
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
    private List<Vote> votes;

    public Option() {

    }

    public Option(String value) {

        this.value = value;
    }

    public Option(Long id, String value) {

        this.id = id;
        this.value = value;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {

        this.value = value;
    }

    public List<Vote> getVotes() {

        return votes;
    }

    public void setVotes(List<Vote> votes) {

        this.votes = votes;
    }
}
