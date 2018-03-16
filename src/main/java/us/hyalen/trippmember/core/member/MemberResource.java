package us.hyalen.trippmember.core.member;

import org.springframework.hateoas.ResourceSupport;

public class MemberResource extends ResourceSupport {
	public static final String MEDIA_TYPE = "application/us.hyalen.member.v1+json";
	
	public Long memberId;
	public String name;
	public String email;
	public String phone;

	@Override
    public boolean equals(Object o) {
        if (this == o) 
        		return true;
        
        if (o == null || getClass() != o.getClass()) 
        		return false;

        MemberResource that = (MemberResource) o;

        return memberId != null ? memberId.equals(that.memberId) : that.memberId == null;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (memberId != null ? memberId.hashCode() : 0);
        
        return result;
    }
}
