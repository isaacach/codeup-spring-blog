package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.Models.Ad;
import com.codeup.codeupspringblog.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdController {

    private final AdRepository adDao;

    public AdController(AdRepository adDao) {
        this.adDao = adDao;
    }

    @GetMapping("/ads")
    public String index(Model model) {
        model.addAttribute("ads", adDao.findAll());
        return "ads/index";
    }

    @GetMapping("ads/create")
    public String createAdForm() {
        return "ads/create";
    }

    @PostMapping("ads/create")
    public String createAd(@RequestParam(name="title") String title, @RequestParam(name="description") String description) {
        Ad ad = new Ad(title, description);
        adDao.save(ad);
        return "redirect:/ads";
    }
}
