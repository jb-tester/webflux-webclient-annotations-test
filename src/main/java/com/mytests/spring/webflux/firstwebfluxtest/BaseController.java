package com.mytests.spring.webflux.firstwebfluxtest;

        import org.springframework.ui.ModelMap;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 10/28/2019.
 * Project: webflux-webclient-annotations-test
 * *******************************
 */
@RestController
public class BaseController {

    @RequestMapping("/{boo:${mymappings.uri-regex}}")
    public String testBaseMappingWithRegex(    @PathVariable String boo) {
        return "just test "+boo;
    }

    @RequestMapping("${mymappings.base-uri}")
    public String testBaseMapping() {
        return "just test foo/bar";
    }
}
