package com.sic777.restful.base.interceptor;

import com.sic777.restful.base.response.ResponseManager;
import com.sic777.restful.base.spi.auth.IAuthSPI;
import com.sic777.common.utils.lang.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.sic777.restful.base.permission.Permission;
import com.sic777.restful.base.permission.RestPermission;
import com.sic777.common.utils.container.tuple.Tuple;
import com.sic777.common.utils.container.tuple.TwoTuple;
import com.sic777.common.utils.permission.PermissionUtil;

import static com.sic777.restful.base.exception.ExceptionType.AuthenticationExceptionType.*;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public abstract class SuperAuthInterceptor {

    /**
     * 验证权限
     *
     * @param accessToken
     * @param permissionAnnotation
     * @return accessToken缓存的数据, Rest接口权限值
     * @throws Exception
     */
    protected final TwoTuple<JSONObject, Integer> validate(String accessToken, Permission permissionAnnotation, IAuthSPI authSPI) throws Exception {
        TwoTuple<JSONObject, Integer> tuple = null;
        if (null != permissionAnnotation) {
            int permission = PermissionUtil.permission(permissionAnnotation.value());
            if ((RestPermission.ANYBODY & permission) == 0) {
                if (StringUtil.isEmpty(accessToken)) {
                    ResponseManager.instance().throwRestException(ACCESS_TOKEN_VALUE_EMPTY.getId(), ACCESS_TOKEN_VALUE_EMPTY.getMsg());
                }
                tuple = authSPI.parse(accessToken);
                if (tuple == null || tuple.first == null || (tuple.second & permission) == 0) {
                    ResponseManager.instance().throwRestException(INVALID_ACCESS.getId(), INVALID_ACCESS.getMsg());
                }
            }
        }
        return null != tuple ? tuple : Tuple.tuple(new JSONObject(), RestPermission.ANYBODY);
    }

    /**
     * <p>权限注解优先级：方法注解大于类注解 </p>
     * <p>1. 类无,方法无 无需校验 </p>
     * <p>2. 类无,方法有 以方法为主 </p>
     * <p>3. 类有,方法无 以类为主</p>
     * <p>4. 类有,方法有 以方法为主</p>
     *
     * @param accessToken
     * @param firstPermission 优先级高的权限注解，一般为类权限注解。
     * @param fallPermission  优先级低的权限注解，一般为方法权限注解
     * @param authSPI
     * @return @return accessToken缓存的数据,Rest接口权限值
     * @throws Exception
     */
    protected final TwoTuple<JSONObject, Integer> validate(String accessToken, Permission firstPermission, Permission fallPermission, IAuthSPI authSPI) throws Exception {
        if (null != firstPermission) {
            return this.validate(accessToken, firstPermission, authSPI);
        }
        return this.validate(accessToken, fallPermission, authSPI);
    }
}
