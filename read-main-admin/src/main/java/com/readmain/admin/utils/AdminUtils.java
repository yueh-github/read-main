package com.readmain.admin.utils;

import com.readmain.common.entity.SysResourceEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminUtils {

    public void putGrantedResourceIntoSession(List<SysResourceEntity> resourceList, HttpSession session) {
        List<String> grantedUrlList = new ArrayList<>();
        for (SysResourceEntity resourceEntity : resourceList) {
            if (StringUtils.isNotEmpty(resourceEntity.getResource())) {
                grantedUrlList.add(resourceEntity.getResource());
            }
        }
        session.setAttribute(Constants.SESSION_GRANTED_URL_KEY, grantedUrlList);
    }

}
