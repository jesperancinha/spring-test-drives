package org.jesperancinha.sftd.mastery1.french.music.service;

import org.jesperancinha.sftd.mastery1.french.music.api.MemberService;
import org.jesperancinha.sftd.mastery1.french.music.domain.Member;
import org.jesperancinha.sftd.mastery1.french.music.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> getMembersLike(String param) {
        return memberRepository.findAllByNameLike(String.format("%%%s%%", param));
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }


    @Override
    @Transactional(
            propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.SERIALIZABLE,
            rollbackFor = RuntimeException.class,
            rollbackForClassName = "RuntimeException",
            noRollbackForClassName = "Error",
            noRollbackFor = Error.class)
    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public List<Member> getAllDateNonNullMembers() {
        return memberRepository.getAllDateNonNullMembers();
    }
}
