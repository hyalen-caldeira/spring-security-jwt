package us.hyalen.trippmember.core.member;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component("memberResourceAssembler_v1")
public class MemberResourceAssembler extends ResourceAssemblerSupport <Member, MemberResource> {
	public MemberResourceAssembler() {
		super(MemberController.class, MemberResource.class);
	}

	@Override
	public MemberResource toResource(Member member) {
		MemberResource resource = new MemberResource();
		
		resource.memberId = member.getMemberId();
		resource.name = member.getName();
		resource.email = member.getEmail();
		resource.phone = member.getPhone();
		
		return resource;
	}
}
