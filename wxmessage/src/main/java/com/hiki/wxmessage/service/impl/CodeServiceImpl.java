package com.hiki.wxmessage.service.impl;

import com.hiki.wxmessage.service.CodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：hiki
 * 2019/8/17 14:25
 */
@Service
@Slf4j
public class CodeServiceImpl implements CodeService {

    @Override
    public Boolean checkVerify(HttpServletRequest request) {
        try{
            //从session中获取随机数
            String inputStr = request.getParameter("code");
            String random = (String) request.getSession().getAttribute("RANDOMVALIDATECODEKEY");
            if (random == null) {
                return false;
            }
            if (random.equals(inputStr)) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            log.error("验证码校验失败", e);
            return false;
        }
    }
}
