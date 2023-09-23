package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.LoginDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserService;
import com.heima.utils.common.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.Map;


@Service
@Transactional
@Slf4j
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {
   @Autowired
    ApUserMapper apUserMapper;
    /**
     * app登陆功能
     * @param dto
     * @return
     */
    public ResponseResult login(LoginDto dto){
        String phone = dto.getPhone();
        String password = dto.getPassword();
        //正常登陆
        if (StringUtils.isNoneBlank(phone)&&StringUtils.isNoneBlank(password)){
            QueryWrapper<ApUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phone", phone);
            //根据手机号查询
            ApUser apUser = apUserMapper.selectOne(queryWrapper);
            //如果手机号不存在
            if (apUser == null){
                return ResponseResult.errorResult(AppHttpCodeEnum.AP_USER_DATA_NOT_EXIST,"用户不存在");
            }
            //存在的话比对数据库的密码
            String pwd = DigestUtils.md5DigestAsHex((password + apUser.getSalt()).getBytes());
            //如果密码错误
            if (!pwd.equals(apUser.getPassword())){
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);

            }
            //正确的话
            //1.3 返回数据  jwt
            Map<String, Object> map = new HashMap<>();
            map.put("token", AppJwtUtil.getToken(apUser.getId().longValue()));
            apUser.setSalt("");
            apUser.setPassword("");
            map.put("user", apUser);
            return ResponseResult.okResult(map);

        }
          //游客登陆
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",AppJwtUtil.getToken(0L));
        return  ResponseResult.okResult(map);
    };
}
