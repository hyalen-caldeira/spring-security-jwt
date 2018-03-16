package us.hyalen.trippmember.core.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import us.hyalen.trippmember.core.model.MemberModel;

@Component("memberDao_v1")
@Transactional
public class MemberDao {
	@Autowired
	private MemberRepository repository;
	
	@Cacheable("member")
	public Optional<Member> findByMemberId(Long memberId) {
		MemberModel memberModel = repository.findOne(memberId);
		
		Member member = toDomain(memberModel);
		
		return Optional.ofNullable(member);
	}
	
	@Cacheable("members")
	public List<MemberModel> findAllMembers() {
		List<MemberModel> members = new ArrayList<>();
		
		repository.findAll().forEach(members::add);
		
		return members;
	}
	
	public void save(Member member) {
		repository.save(new MemberModel(member));
	}
	
	public void update(Member member) {
		repository.save(new MemberModel(member));
	}
	
	public void delete(Long memberId) {
		repository.delete(memberId);
	}
	
	private Member toDomain(MemberModel model) {
        Member member = null;
        
        if(model != null) {
            member = new Member.Builder()
            	.withMemberId(model.getMemberId())
            	.withName(model.getName())
            	.withEmail(model.getEmail())
            	.withPhone(model.getPhone())
                .build();
        }
        
        return member;
    }
}
