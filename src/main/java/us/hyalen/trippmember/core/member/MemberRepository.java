package us.hyalen.trippmember.core.member;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import us.hyalen.trippmember.core.model.MemberModel;

public interface MemberRepository extends CrudRepository<MemberModel, Long> {}
