package shi.mvc.map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1")
public class V1Rest {

    @GetMapping(path = "show")
    public String show1() {
        return "v1/show 1.0.0";
    }

    @Api("1.1.2")
    @GetMapping(path = "show")
    public String show2() {
        return "v1/show 1.1.2";
    }

    @Api("1.1.0")
    @GetMapping(path = "show")
    public String show3() {
        return "v1/show 1.1.0";
    }
}

