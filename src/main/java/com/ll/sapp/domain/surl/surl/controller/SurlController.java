package com.ll.sapp.domain.surl.surl.controller;

import com.ll.sapp.domain.member.member.entity.Member;
import com.ll.sapp.domain.surl.surl.entity.Surl;
import com.ll.sapp.domain.surl.surl.service.SurlService;
import com.ll.sapp.global.exceptions.GlobalException;
import com.ll.sapp.global.rq.Rq;
import com.ll.sapp.global.rsData.RsData;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SurlController {

    private final SurlService surlService;
    private final Rq rq;

    @GetMapping("/all")
    @ResponseBody
    public List<Surl> getAll() {
        return surlService.findAll();
    }

    @GetMapping("/add")
    @ResponseBody
    public RsData<Surl> add(String body, String url) {
        Member member = rq.getMember();

        System.out.println("before get Id");
        member.getId();
        System.out.println("after get Id");

        System.out.println("before get username");
        member.getUsername();
        System.out.println("after get username");

        return surlService.add(member, body, url);
    }

    @GetMapping("/s/{body}/**")
    @ResponseBody
    public RsData<Surl> add(@PathVariable String body, HttpServletRequest request) {
        String url = request.getRequestURI();

        Member member = rq.getMember();

        if(request.getQueryString() != null) {
            url += '?' + request.getQueryString();
        }

        String[] urlBits = url.split("/", 4);

        url = urlBits[3];

        return surlService.add(member, body, url);
    }

    @GetMapping("/g/{id}")
    @ResponseBody
    public String go(@PathVariable long id) {
        Surl surl = surlService.findById(id).orElseThrow(GlobalException.E404::new);

        surlService.increaseCount(surl);

        return "redirect: " + surl.getUrl();
    }

}
