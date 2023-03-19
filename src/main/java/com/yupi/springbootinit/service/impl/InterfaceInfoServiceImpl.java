package com.yupi.springbootinit.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.mapper.InterfaceInfoMapper;
import com.yupi.springbootinit.model.entity.InterfaceInfo;
import com.yupi.springbootinit.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String url = interfaceInfo.getUrl();
        String method = interfaceInfo.getMethod();
        String userId = interfaceInfo.getUserId();


        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(name, description, url, method, userId), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验

        // sanXun
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
        if (StringUtils.isNotBlank(description) && description.length() > 80) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "描述过长");
        }
        if (StringUtils.isNotBlank(url) && url.length() > 170) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "Url过长");
        }
        if (StringUtils.isNotBlank(method) && method.length() > 80) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求方法类型过长");
        }
        if (StringUtils.isNotBlank(userId) && userId.length() > 80) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "创建人id过长");
        }
    }
}




