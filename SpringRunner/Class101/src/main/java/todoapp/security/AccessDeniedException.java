package todoapp.security;

import todoapp.commons.SystemException;

/**
 * κΆν?΄ ??΄ ? κ·? λΆκ? ??©? λ°μ κ°??₯? ??Έ ?΄??€
 *
 * @author springrunner.kr@gmail.com
 */
public class AccessDeniedException extends SystemException {

    public AccessDeniedException() {
        super("? κ·Όμ κ±°λ??©??€.");
    }

}
