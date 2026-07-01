package org.jesperancinha.sftd.mastery1.french.music.controller;

import org.jesperancinha.sftd.mastery1.french.music.api.MemberService;
import org.jesperancinha.sftd.mastery1.french.music.dao.MemberBean;
import org.jesperancinha.sftd.mastery1.french.music.dao.MemberDao;
import org.jesperancinha.sftd.mastery1.french.music.domain.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("member")
public class MemberController {

    private final MemberService memberService;

    private final MemberBean memberBean;

    public MemberController(MemberService memberService, MemberDao memberBean, MemberBean memberDao1) {
        this.memberService = memberService;
        this.memberBean = memberDao1;
    }

    @RequestMapping
    public @ResponseBody
    List<Member> getAllArtists() {
        return memberService.getAllMembers();
    }

    @RequestMapping("/search")
    public @ResponseBody
    List<Member> getAllArtists(
            @RequestParam
            final String param) {
        return memberService.getMembersLike(param);
    }

    @RequestMapping("/nonnull")
    public @ResponseBody
    List<Member> getNonNullArtists() {
        return memberService.getAllDateNonNullMembers();
    }

    @RequestMapping("/delete/{id}")
    public @ResponseBody
    ResponseEntity<Object> deleteMemberById(
            @PathVariable
            final Long id) {
        memberService.deleteMemberById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create/rollback")
    public void createMemberRollback(
            @RequestBody
            final Member member) {
        memberBean.persistMemberRollback(member);
    }

    @PostMapping("/create")
    public void createMember(
            @RequestBody
            final Member member) {
        memberBean.persistMember(member);
    }
}
