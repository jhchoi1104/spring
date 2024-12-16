package hello.core.member;

public interface MemberService {

    void join(Member memner);

    Member findMember(Long MemberId);
}
