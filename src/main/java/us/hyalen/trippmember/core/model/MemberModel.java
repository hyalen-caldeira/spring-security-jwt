package us.hyalen.trippmember.core.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

import us.hyalen.trippmember.core.member.Member;

@Entity
@Table(name = "MEMBER")
public class MemberModel {
    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Size(max = 255)
    @Column(unique = true, name = "NAME")
    private String name;

    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;
    
    @Size(max = 255)
    @Column(name = "PHONE")
    private String phone;

    public MemberModel() {
    }

    public MemberModel(Member member) {
        this.memberId = member.getMemberId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.phone = member.getPhone();
    }

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
}
