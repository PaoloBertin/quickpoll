package eu.opensource.quickpoll.repository;

import eu.opensource.quickpoll.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

//    @Query(value="select v.* from Option o, Vote v where o.POLL_ID = ?1 and v.OPTION_ID = o.OPTION_ID", nativeQuery = true)
    @Query(value="SELECT v.* FROM options o, votes v WHERE o.poll_id = ?1 AND v.option_id = o.option_id", nativeQuery = true)
    public List<Vote> findByPoll(Long pollId);

    public Iterable<Vote> findByOptionId(Long optionId);
}
