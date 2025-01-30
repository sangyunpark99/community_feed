package org.sangyunpark99.admin.ui;

import lombok.RequiredArgsConstructor;
import org.sangyunpark99.admin.ui.query.UserStatsQueryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserStatsQueryRepository userStatsQueryRepository;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        modelAndView.addObject("result", userStatsQueryRepository.getDailyRegisterUserStats(100));

        return modelAndView;
    }

}
