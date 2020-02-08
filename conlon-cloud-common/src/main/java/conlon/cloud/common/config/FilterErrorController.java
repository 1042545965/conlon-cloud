package conlon.cloud.common.config;


import conlon.cloud.common.utils.Result;
import java.io.UnsupportedEncodingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author conlonx
 * @since 2019-08-28
 */
@RestController
@RequestMapping("/common")
public class FilterErrorController {

    @RequestMapping(path = "/unauthorized/{message}")
    public Result unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return Result.build("401", message);
    }

}