package us.hyalen.trippmember.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import us.hyalen.trippmember.core.member.Member;
import us.hyalen.trippmember.core.member.MemberDao;

@Component
public class MemberConfig {
	@Autowired
    @Qualifier("memberDao_v1")
    private MemberDao memberDao;
	
	@PostConstruct
    void injectDependencies() {
        Member.setMemberDao(memberDao);
    }
}