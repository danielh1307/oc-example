package danielh1307.spring.ocexample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class GreetingController {

    @Value("${customer.earlyAdopter:no}")
    private String earlyAdopter;

    @Value("${admin.name:null}")
    private String adminName;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name") String name) throws UnknownHostException {
        String result = "Hello " + name + "<br/><br/>";

        if (name.equals(adminName)) {
            result += "You are admin!<br/><br/>";
        }

        if (earlyAdopter.equals("yes")) {
            result += "YOU ARE VERY WELCOME<br/><br/>";
        }

        result += "IP: " + InetAddress.getLocalHost().getHostAddress();

        return result;
    }
}
