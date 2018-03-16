package us.hyalen.trippmember.core.member;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import us.hyalen.trippmember.core.NotFoundException;
import us.hyalen.trippmember.core.model.MemberModel;

@RestController
@RequestMapping(value = "/api", produces = MemberResource.MEDIA_TYPE)
public class MemberController {
    public final String DEFAULT_USER = "DEFAULT_USER";
    public final String SUPER_USER = "SUPER_USER";
    public final String ADMIN_USER = "ADMIN_USER";

	@Autowired
    @Qualifier("memberResourceAssembler_v1")
    private MemberResourceAssembler memberResourceAssembler;
	
	@RequestMapping(value = "/home") 
	public String hello() {
		return "Hello TRIPP, this is Hyalen ...";
	}
	
	@PreAuthorize("hasAuthority('DEFAULT_USER') or hasAuthority('SUPER_USER') or hasAuthority('ADMIN_USER')")
	@RequestMapping(value = "/members", method = RequestMethod.GET)
    public List<MemberModel> getMembers() {
        return Member.findAllMembers();
    }
	
	@PreAuthorize("hasAuthority('DEFAULT_USER') or hasAuthority('SUPER_USER') or hasAuthority('ADMIN_USER')")
	@RequestMapping(value = "/members/{id:\\d+}", method = RequestMethod.GET)
    public MemberResource getMemberById(@PathVariable(value = "id") long memberId) {
		Member member = Member.findByMemberId(memberId).orElseThrow(NotFoundException::new);
		
        return memberResourceAssembler.toResource(member);
    }
	
	@PreAuthorize("hasAuthority('SUPER_USER') or hasAuthority('ADMIN_USER')")
	@RequestMapping(value = "/members/{id:\\d+}", method = RequestMethod.PUT, consumes = MemberResource.MEDIA_TYPE)
    public void updateMember(@Valid @RequestBody MemberResource resource, @PathVariable(value = "id") long memberId) {
        Member.findByMemberId(memberId).orElseThrow(NotFoundException::new);
        
        Member member = toDomain(resource);
        
        member.update();
    }
	
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	@RequestMapping(value = "/members", method = RequestMethod.POST, consumes = MemberResource.MEDIA_TYPE)
    public void createMember(@Valid @RequestBody MemberResource resource, HttpServletResponse httpServletResponse) {
        Member member = toDomain(resource);
        
        member.save();
    }
	
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	@RequestMapping(value= "/members/{id:\\d+}", method = RequestMethod.DELETE)
    public void remove(@PathVariable("id") Long memberId) {
        Member member = Member.findByMemberId(memberId).orElseThrow(NotFoundException::new);
        
        member.delete(memberId);
    }
	
	private Member toDomain(MemberResource resource) {
        return new Member.Builder()
            .withMemberId(resource.memberId)
            .withName(resource.name)
            .withEmail(resource.email)
            .withPhone(resource.phone)
        		.build();
    }
}
