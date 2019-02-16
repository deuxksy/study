package todoapp.commons.web.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * ?¤?ë§ë??¸? ę¸°ëł¸ ęľŹíě˛´ě¸ {@link DefaultErrorAttributes}? message ??ą? ?Ž?´?°ę¸? ?  ëŞŠě ?źëĄ? ??ą? ěť´íŹ??¸?´?¤.
 *
 * DefaultErrorAttributes? message ??ą? ??¸ ę°ě˛´? ę°ě ?Ź?Š?ę¸? ?ëŹ¸ě ?Ź?Š?ę°? ?˝ę¸°ě ě˘ě? ëŹ¸ęľŹę°? ???¤.
 * ?´?š ëŠěě§?ëĽ? ëł´ë¤ ?˝ę¸? ě˘ě? ëŹ¸ęľŹëĄ? ę°?ęłľí´? ? ęłľí?¤.
 *
 * @author springrunner.kr@gmail.com
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ReadableErrorAttributes implements ErrorAttributes, HandlerExceptionResolver, Ordered {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final DefaultErrorAttributes delegate;

    public ReadableErrorAttributes() {
        this(false);
    }

    public ReadableErrorAttributes(boolean includeException) {
        this.delegate = new DefaultErrorAttributes(includeException);
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> attributes = delegate.getErrorAttributes(webRequest, includeStackTrace);
        Throwable error = getError(webRequest);

        // TODO attributes, error ? ?Ź?Š?´? message ??ą? ?˝ę¸? ě˘ě? ëŹ¸ęľŹëĄ? ę°?ęłľí?¤.
        // TODO ex) attributes.put("message", "ëŹ¸ęľŹ");

        return attributes;
    }

    @Override
    public Throwable getError(WebRequest webRequest) {
        return delegate.getError(webRequest);
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception error) {
        return delegate.resolveException(request, response, handler, error);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
