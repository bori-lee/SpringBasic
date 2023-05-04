package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    //private final MyLogger myLogger; // myLogger의 스콥이 리퀘스트 스콥.. 근데 리퀘스트가 없다?, 왜냐면 생존범위가 고객이들어와서 나갈떄까지라서. 근데.. 지금 httprequest가 안나왔다. 그러면 provider 사용해서 해결.
    private final ObjectProvider<MyLogger> myLoggerObjectProvider; // 주입시점에서 주입받을 수 있다.

    @RequestMapping("log-demo")
    @ResponseBody //문자 그대로 응답을 보낼 수 있따.
    public String logDemo(HttpServletRequest request) {

        String requestURL = request.getRequestURI().toString();
        MyLogger myLogger = myLoggerObjectProvider.getObject();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }

}
