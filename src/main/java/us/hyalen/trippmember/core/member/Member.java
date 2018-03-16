package us.hyalen.trippmember.core.member;

import java.util.List;
import java.util.Optional;

import us.hyalen.trippmember.core.Domain;
import us.hyalen.trippmember.core.model.MemberModel;

public class Member extends Domain {
	private static MemberDao memberDao;
	private Long memberId;
	private String name;
	private String email;
	private String phone;
	
	private Member() {}
	
	public Long getMemberId() {
		return memberId;
	}
	
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public static void setMemberDao(MemberDao memberDao) {
		Member.memberDao = memberDao;
	}
	
	public static Optional<Member> findByMemberId(long memberId) {
		return memberDao.findByMemberId(memberId);
    }
	
	public static List<MemberModel> findAllMembers() {
		return memberDao.findAllMembers();
	}

    public void save() {
        validate();
        memberDao.save(this);
    }

    public void update() {
        validate();
        memberDao.update(this);
    }
    
    public void delete(Long memberId) {
    		memberDao.delete(memberId);
    }
	
	public static class Builder {
		private Member member;
		
		public Builder() {
            this.member = new Member();
        }

        public Builder(Member member) {
            this.member = member;
        }

        public Builder withMemberId(Long memberId) {
            member.memberId = memberId;
            
            return this;
        }
        
        public Builder withName(String name) {
            member.name = name;
            
            return this;
        }
        
        public Builder withEmail(String email) {
            member.email = email;
            
            return this;
        }
        
        public Builder withPhone(String phone) {
            member.phone = phone;
            
            return this;
        }
        
        public Member build() {
            return member;
        }
	}
}