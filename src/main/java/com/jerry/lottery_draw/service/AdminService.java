package com.jerry.lottery_draw.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jerry.lottery_draw.domain.TAdmin;
import com.jerry.lottery_draw.exception.BusinessException;
import com.jerry.lottery_draw.exception.BusinessExceptionCode;
import com.jerry.lottery_draw.mapper.TAdminMapper;
import com.jerry.lottery_draw.req.AdminCreateReq;
import com.jerry.lottery_draw.req.AdminLoginReq;
import com.jerry.lottery_draw.req.AdminUpdateReq;
import com.jerry.lottery_draw.resp.AdminLoginResp;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class AdminService {

    private static final Logger LOG = LoggerFactory.getLogger(AdminService.class);

    @Resource
    private TAdminMapper tAdminMapper;

    /**
     * 工具方法：根据account字段查询对应的帐号
     *
     * @param account String
     * @return TAdmin
     */
    public TAdmin selectByAccount(String account) {
        LambdaQueryWrapper<TAdmin> sqlWhereWrapper = new LambdaQueryWrapper<TAdmin>()
                .eq(account != null, TAdmin::getAccount, account);
        return tAdminMapper.selectOne(sqlWhereWrapper);
    }

    /**
     * 创建帐号
     *
     * @param req AdminCreateReq
     */
    public void create(AdminCreateReq req) {
        TAdmin tAdmin = selectByAccount(req.getAccount());
        if (ObjectUtils.isEmpty(tAdmin)) {
            // 用户尚不存在
            TAdmin adminDb = new TAdmin();
            BeanUtils.copyProperties(req, adminDb);
            tAdminMapper.insert(adminDb);
        }
        else {
            // 用户名已存在
            LOG.info("用户名已存在, {}", req.getAccount());
            throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
        }
    }

    /**
     * 删除帐号
     *
     * @param account String
     */
    public void delete(String account) {
        TAdmin tAdmin = selectByAccount(account);
        if (ObjectUtils.isEmpty(tAdmin)) {
            // 用户尚不存在
            LOG.info("用户名不存在, {}", account);
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }
        else {
            tAdminMapper.deleteById(tAdmin.getId());
        }
    }

    /**
     * 更新帐号
     *
     * @param account String
     * @param req     AdminUpdateReq
     */
    public void update(String account, AdminUpdateReq req) {
        TAdmin tAdmin = selectByAccount(account);
        if (ObjectUtils.isEmpty(tAdmin)) {
            // 用户尚不存在
            LOG.info("用户名不存在, {}", account);
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }
        else {
            req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
            BeanUtils.copyProperties(req, tAdmin);
            tAdminMapper.updateById(tAdmin);
        }
    }

    /**
     * 登录验证
     *
     * @param req AdminLoginReq
     * @return AdminLoginResp
     */
    public AdminLoginResp login(AdminLoginReq req) {
        TAdmin adminDb = selectByAccount(req.getAccount());
        if (ObjectUtils.isEmpty(adminDb)) {
            // 用户名不存在
            LOG.info("用户名不存在, {}", req.getAccount());
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }
        else {
            if (adminDb.getPassword().equals(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()))) {
                // 登录成功
                AdminLoginResp userLoginResp = new AdminLoginResp();
                BeanUtil.copyProperties(adminDb, userLoginResp);
                return userLoginResp;
            }
            else {
                // 密码不对
                LOG.info("密码不对, 输入密码：{}, 数据库密码：{}", req.getPassword(), adminDb.getPassword());
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            }
        }
    }
}


