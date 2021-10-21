package eu.opensource.quickpoll.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "votes")
@Entity
public class Vote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "option_id", foreignKey = @ForeignKey(name = "vote_id_fk"))
    private Option option;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Option getOption() {

        return option;
    }

    public void setOption(Option option) {

        this.option = option;
    }
}
