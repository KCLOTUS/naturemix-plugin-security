package cn.naturemix.plugin.security.exception;

import org.apache.shiro.authc.AuthenticationException;

public class AuthcException extends Throwable {
    public AuthcException(AuthenticationException e) {
    }
}
