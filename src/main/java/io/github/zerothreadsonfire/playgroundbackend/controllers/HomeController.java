package io.github.zerothreadsonfire.playgroundbackend.controllers;

import io.github.zerothreadsonfire.playgroundbackend.models.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Controller is a special type of @Component, which allows us to auto-detect implementation classes through
 * classpath scanning.
 * @ResponseBody tells the controller that the object returned is automatically serialized into JSON and written to the
 * HttpResponse object.
 * @RestController is used to replace the @Controller & @ResponseBody
 * @RequestMapping - If we do not explicitly specify any method then it will map to any HTTP request. The multiple variations
 * of the annotation are written below. @RequestMapping can now be replaced with newer shortcuts like @GetMapping,
 * @PostMapping, @PutMapping, @DeleteMapping.
 * Mandatory data is taken using Path variables whereas data that is not mandatory & non-sensitive is taken
 * using req mapping.
 */
@Controller
@ResponseBody
// @RestController = @Controller + @ResponseBody
public class HomeController {
    @RequestMapping("/api/requestmapping/v1")
    public String home() {
        return "easiest request mapping";
    }

    @RequestMapping(value="/api/requestmapping/v2")
    public String home2() {
        return "req mapping with value param";
    }

    @RequestMapping(value="/api/requestmapping/v3", method = RequestMethod.GET)
    public String home3() {
        return "req mapping with value & method param";
    }

    @RequestMapping(value={"/api/requestmapping/v4", "/api/requestmapping/v4b"}, method = RequestMethod.GET)
    public String home4() {
        return "req param with multiple values /v4 & /v4b";
    }

    @RequestMapping(value="/api/requestmapping/v5", headers = "Accept=application/json", method = RequestMethod.GET)
    public String home5() {
        return "req mapping with headers";
    }
    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public User fetchUser() {
        User user  = new User();
        user.setId("1");
        user.setName("satyam");
        user.setEmailId("sk@gmail.com");
        return user;
    }

//    @RequestMapping(value = "/api/requestmapping/{id}", method = RequestMethod.GET)
//    public String home6(@PathVariable("id") String id) {
//        return "request mapping with path variable - "+id;
//    }

    @RequestMapping(value = "/api/requestmapping/v6", params = "id", method = RequestMethod.GET)
    public String home7(@RequestParam("id") String id) {
        return "request mapping with single request param - "+id;
    }

    @RequestMapping(value = "/api/requestmapping/v7/reqparam", params = {"idFirst", "idSecond"}, method = RequestMethod.GET)
    public String home8(@RequestParam("idFirst") String id, @RequestParam(value = "idSecond", required = false) String idSecond) {
        return "request mapping with multiple request param - "+id +", ";
    }

    @RequestMapping(value = "/api/requestmapping/v8", params = {"idFirst", "idSecond"}, method = RequestMethod.GET)
    public String home10(@RequestParam("idFirst") String id,
                        @RequestParam(value = "idSecond", defaultValue = "default") String idSecond) {
        return "request mapping with multiple request param with default value - "+id +", "+idSecond;
    }

    // Multiple HTTP methods can be routed to same endpoint. Also, '*' indicates a fallback for all requests.
    // Important to not that this wildcard applies to localhost:8080/{anything} & localhost:8080/{anything}/{anything} only
    @RequestMapping(value = "*/*", method = {RequestMethod.GET, RequestMethod.POST})
    public String home11() {
        return "request mapping with for get and post both with * routing";
    }
}
