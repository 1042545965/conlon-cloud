package conlon.cloud.common.config;


import conlon.cloud.common.utils.Result;
import java.io.UnsupportedEncodingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Filter 当中的异常无法抛出的问题
 * @author conlon
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