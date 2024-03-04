package com.testyodi.testYodi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.testyodi.testYodi.models.Member;
import com.testyodi.testYodi.models.MemberDto;
import com.testyodi.testYodi.services.MembersRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("members")
public class MemberController {

	@Autowired
	private MembersRepository repo;
	
	@GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable int id) {
        Member member = repo.findById(id).orElse(null);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@GetMapping({"","/"})
	public String showMemberList(Model model) {
		List<Member> member = repo.findAll();
		model.addAttribute("member", member);
		return "members/index";
	}
	
	@GetMapping("/create")
	public String showCreatePage(Model model) {
		MemberDto memberDto = new MemberDto();
		model.addAttribute("memberDto", memberDto);
		return "members/CreateMember";
	}
	
	@PostMapping("/create")
	public String createMember(@Valid @ModelAttribute MemberDto memberDto, BindingResult result) {
		
		if (result.hasErrors()) {
			return "members/CreateMember";
		}
		
		Member member = new Member();
		member.setName(memberDto.getName());
		member.setDateOfBirth(memberDto.getDateOfBirth());
		member.setEducation(memberDto.getEducation());
		member.setEmail(memberDto.getEmail());
		member.setMobileNo(memberDto.getMobileNo());
		
		repo.save(member);
		
		return "redirect:/members";
	}
	
	
	@GetMapping("/edit")
	public String showEditPage(Model model, @RequestParam int id) {
		try {
			Member member = repo.findById(id).get();
			model.addAttribute("member", member);

			MemberDto memberDto = new MemberDto();
			memberDto.setName(member.getName());
			memberDto.setDateOfBirth(member.getDateOfBirth());
			memberDto.setEducation(member.getEducation());
			memberDto.setEmail(member.getEmail());
			memberDto.setMobileNo(member.getMobileNo());

			model.addAttribute("memberDto", memberDto);
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
			return "redirect:/EditMember";
		}

		return "members/EditMember";
	}
	
	
	@PostMapping("/edit")
	public String updateMember(Model model, @RequestParam int id, @Valid @ModelAttribute MemberDto memberDto, BindingResult result) {
		try {
			Member member = repo.findById(id).get();
			model.addAttribute("member", member);

			if (result.hasErrors()) {
				return "members/EditMember";
			}

			// Update member details
			member.setName(memberDto.getName());
			member.setDateOfBirth(memberDto.getDateOfBirth());
			member.setEducation(memberDto.getEducation());
			member.setEmail(memberDto.getEmail());
			member.setMobileNo(memberDto.getMobileNo());

			repo.save(member);
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}

		return "redirect:/members";
	}
	
	@GetMapping("/detail")
	public String showEditPage2(Model model, @RequestParam int id) {
		try {
			Member member = repo.findById(id).get();
			model.addAttribute("member", member);

			MemberDto memberDto = new MemberDto();
			memberDto.setName(member.getName());
			memberDto.setDateOfBirth(member.getDateOfBirth());
			memberDto.setEducation(member.getEducation());
			memberDto.setEmail(member.getEmail());
			memberDto.setMobileNo(member.getMobileNo());

			model.addAttribute("memberDto", memberDto);
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
			return "redirect:/DetailMember";
		}

		return "members/DetailMember";
	}
	
	
	@PostMapping("/detail")
	public String updateMember2(Model model, @RequestParam int id, @Valid @ModelAttribute MemberDto memberDto, BindingResult result) {
		try {
			Member member = repo.findById(id).get();
			model.addAttribute("member", member);

			if (result.hasErrors()) {
				return "members/DetailMember";
			}

			// Update member details
			member.setName(memberDto.getName());
			member.setDateOfBirth(memberDto.getDateOfBirth());
			member.setEducation(memberDto.getEducation());
			member.setEmail(memberDto.getEmail());
			member.setMobileNo(memberDto.getMobileNo());

			repo.save(member);
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}

		return "redirect:/members";
	}
	
	@GetMapping("/delete")
	public String deleteMember(@RequestParam int id) {
		try {
			Member member = repo.findById(id).get();
			
			// delete the member
			repo.delete(member);
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
		
		return "redirect:/members";
	}
}
