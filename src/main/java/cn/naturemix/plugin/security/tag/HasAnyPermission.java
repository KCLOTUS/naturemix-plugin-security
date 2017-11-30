package cn.naturemix.plugin.security.tag;

import org.apache.shiro.web.tags.PermissionTag;

public class HasAnyPermission extends PermissionTag {
    protected boolean showTagBody(String s) {
        return false;
    }
}
