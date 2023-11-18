package basic.example.day1.simplecontrol;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/simple")
public class SimpleDemo implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "mawb");
        modelAndView.addObject("method", "Controller handlerRequest");
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
