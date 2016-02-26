package regist.utils;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by wangyiran on 26/2/2016.
 */
public class ServletsMockitoUtils {
    public static HttpServletRequest createHttpRequest(HttpSession httpSession) {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(httpSession);
        return request;
    }

    public static HttpSession createSession() {
        HttpSession httpSession = mock(HttpSession.class);
        Map<String,Object> map = new HashMap<String, Object>();
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String key = (String) args[0];
                Object value = args[1];
                map.put(key,value);
                return null;
            }
        }).when(httpSession).setAttribute(anyString(),anyObject());
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String key = (String) args[0];
                return map.get(key);
            }
        }).when(httpSession).getAttribute(anyString());
        return httpSession;
    }

    public static ServletRequestEvent createServletRequestEvent(HttpServletRequest httpRequest) {
        ServletRequestEvent event = mock(ServletRequestEvent.class);
        when(event.getServletRequest()).thenReturn(httpRequest);
        return event;
    }
}
