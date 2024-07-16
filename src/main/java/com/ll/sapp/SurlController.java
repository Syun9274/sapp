package com.ll.sapp;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class SurlController {

    private List<Surl> surls = new ArrayList<>();
    private long surlsLastId;

    @GetMapping("/all")
    @ResponseBody
    public List<Surl> getAll() {
        return surls;
    }

    @GetMapping("/s/{body}/**")
    @ResponseBody
    public Surl add(@PathVariable String body, HttpServletRequest request) {
        String url = request.getRequestURI();

        if(request.getQueryString() != null) {
            url += '?' + request.getQueryString();
        }

        String[] urlBits = url.split("/", 4);
        System.out.println("Arrays.toString(urlBits) : " + Arrays.toString(urlBits));

        url = urlBits[3];

        Surl surl = Surl.builder()
                .id(++surlsLastId)
                .body(body)
                .url(url)
                .build();

        surls.add(surl);

        return surl;
    }

    @GetMapping("/g/{id}")
    @ResponseBody
    public String go(@PathVariable long id) {
        Surl surl = surls.stream()
                .filter(_surl -> _surl.getId() == id)
                .findFirst()
                .orElse(null);

        if(surl == null) throw new RuntimeException("%d번 URL을 찾을 수 없습니다.".formatted(id));

        surl.increaseCount();

        return "redirect:" + surl.getUrl();
    }

}
