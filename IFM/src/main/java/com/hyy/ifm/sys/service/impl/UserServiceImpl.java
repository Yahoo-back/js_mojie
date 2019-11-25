package com.hyy.ifm.sys.service.impl;

import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.config.dao.ConfigDictDao;
import com.hyy.ifm.config.pojo.DcDict;
import com.hyy.ifm.customer.pojo.QdaoSl;
import com.hyy.ifm.sys.dao.MeunDao;
import com.hyy.ifm.sys.dao.RoleDao;
import com.hyy.ifm.sys.dao.UserDao;
import com.hyy.ifm.sys.pojo.*;
import com.hyy.ifm.sys.service.UserService;
import com.hyy.ifm.util.StringUtil;
import com.hyy.ifm.util.sharekey.SysShareKey;
import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@IocBean
public class UserServiceImpl extends BaseService implements UserService {

    @Inject
    private UserDao userDao;
    @Inject
    private MeunDao meunDao;
    @Inject
    private RoleDao roleDao;

    @Override
    public CallBackBean valiToken(String token, String userId) {
        try {
            IfmSSO sso = userDao.fetchSSO(userId, token);
            if (null == sso) {
                return joinformateJson("", "您的账号在其他的客户端登陆，请重新登录！", "");
            }
            return joinformateJson("", "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return joinformateJson("", "验证token失败", "");
        }
    }

    @Override
    public CallBackBean login(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            String userCode = StringUtil.nvl(cnds.getRows().getUserName());
            String password = StringUtil.nvl(cnds.getRows().getPassword());
            String ip = StringUtil.nvl(cnds.getRows().getAddress());

            IfmLogin user = userDao.qryInfo(userCode, password);
            if (null == user) {
                return this.joinformateJson(json, "用户名或密码不正确！", new IfmUser());
            } else {
                IfmUser user1 = userDao.fetchUserByLngid(user.getLgnId() + "");
                if (!"2".equals(user1.getStatus()) && !"admin".equals(user.getUserCode())) {
                    return this.joinformateJson(json, "当前账户不可用！", user1);
                }

                userDao.deleteSSO(user1.getUserId() + "");
                IfmSSO sso = new IfmSSO();
                sso.setStart_date(new Date());
                String token = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                sso.setToken(token);
                sso.setUser_id(user1.getUserId() + "");
                userDao.insertSSO(sso);

                IfmSysLoginLog is = new IfmSysLoginLog();
                is.setLoginDate(new Date());
                is.setUserCode(userCode);
                //is.setLoginPlace(loginPlace);
                is.setAddress(ip);
                //is.setMac("sssss");
                userDao.insertSYSLogin(is);

                user1.setToken(token);
                return joinformateJson(json, "success", user1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "登录失败", "");
        }
    }

    @Override
    public CallBackBean changePassword(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            String userid = StringUtil.nvl(cnds.getUserCode());
            String lgnid = userDao.fetchUser(userid).getLoginId();
            IfmLogin ifmLogin = userDao.fetchLogin(lgnid);
            if (!StringUtil.MD5(StringUtil.nvl(cnds.getRows().getOldpassword())).equals(ifmLogin.getPassword())) {
                return this.joinformateJson(json, "旧密码输入不对！", "");
            }
            userDao.updateLogin(StringUtil.nvl(ifmLogin.getLgnId()),
                    StringUtil.MD5(StringUtil.nvl(cnds.getRows().getPassword())));

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
            ifmUserOperate.setOperate_date(new Date());
            ifmUserOperate.setOperate("更改密码");
            userDao.insertOperate(ifmUserOperate);
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "重置密码失败", "");
        }
    }


    @Override
    public CallBackBean qryUserList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd(cnds);
        List<Record> res = userDao.qryUserList(cnds);
        int count = userDao.countUserList(cnds);
        return this.joinformateJson(json, "success", count, res);
    }


    @Inject
    private ConfigDictDao configDictDao;
    @Override
    public CallBackBean qryUserListByType(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd6(cnds);
        List<Record> res = userDao.qryUserListByType(cnds);
        for (int i = 0; i < res.size() ; i++) {
            List<DcDict> dcDict = configDictDao.qryDictByDataType("QD_URL");
            res.get(i).set("url",dcDict.get(0).getItemValue()+"?"+res.get(i).get("USER_CODE"));
            res.get(i).set("uvUrl",dcDict.get(0).getItemKey()+"?"+res.get(i).get("USER_CODE"));
        }
        int count = userDao.countUserList(cnds);
        return this.joinformateJson(json, "success", count, res);
    }

    @Override
    public CallBackBean qryUserByLgnId(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        try {
            NutMap re = new NutMap();
            String id = StringUtil.nvl(cnds.getRows().getId());
            IfmLogin login = userDao.fetchLogin(id);
            IfmUser user = userDao.fetchUserByLngid(id);
            re.setv("login", login).setv(SysShareKey.LoginSession, user);
            return joinformateJson(json, "success", Json.toJson(re));
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "查询失败", "");
        }
    }


    @Override
    public CallBackBean qrySourceByLgnId(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        try {
            NutMap re = new NutMap();
            String id = StringUtil.nvl(cnds.getRows().getId());


            QdaoSl qdaoSl = userDao.fetchQdSlByLngid(id);

            IfmLogin login = userDao.fetchLogin(qdaoSl.getLoginId()+"");
            IfmUser user = userDao.fetchUserByLngid(qdaoSl.getLoginId()+"");
            re.setv("login", user).setv(SysShareKey.LoginSession, qdaoSl);
            return joinformateJson(json, "success", Json.toJson(re));
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "查询失败", "");
        }
    }




    @Override
    public CallBackBean updateUserStatus(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            String id = StringUtil.nvl(cnds.getRows().getId());
            String status = StringUtil.nvl(cnds.getRows().getStatus());
            userDao.updateUser(id, status);

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
            ifmUserOperate.setOperate_date(new Date());
            ifmUserOperate.setOperate("更新人员状态");
            userDao.insertOperate(ifmUserOperate);
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "更新人员状态失败", "");
        }
    }

    @Override
    public CallBackBean saveUser(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            boolean f = "".equals(cnds.getRows().getId()) || null == cnds.getRows().getId()? true : false;
            IfmLogin tmp = userDao.fetchLoginByUserCode(StringUtil.nvl(cnds.getRows().getUserCode()), f,
                    StringUtil.parseInt(cnds.getRows().getId()));
            if (null != tmp) {
                return this.joinformateJson(json, "该用户名已经被使用，建议使用姓名拼音，若有重复后面加数字区分", "");
            }

            IfmLogin login = new IfmLogin();
            login.setRegisterDate(new Date());
            login.setUserCode(StringUtil.nvl(cnds.getRows().getUserCode()));

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));

            if (f) {
                login.setPassword(StringUtil.MD5("123456"));
                userDao.saveLogin(login);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("添加用户");
                userDao.insertOperate(ifmUserOperate);
            } else {
                int appr_id = StringUtil.parseInt(cnds.getRows().getId());
                IfmLogin log = userDao.fetchLogin(cnds.getRows().getId());
                login.setPassword(log.getPassword());
                login.setLgnId(appr_id);
                userDao.updateLoginObj(login);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("更新用户");
                userDao.insertOperate(ifmUserOperate);
            }
            IfmUser user = new IfmUser();
            user.setCompanyEmail(StringUtil.nvl(cnds.getRows().getCompanyEmail()));
            user.setCreatDate(new Date());
            user.setIdentityCard(StringUtil.nvl(cnds.getRows().getIdentityCard()));
            user.setLiveAddr(StringUtil.nvl(cnds.getRows().getLiveAddr()));
            user.setLoginId(login.getLgnId() + "");
            user.setOrgId(StringUtil.nvl(cnds.getRows().getOrgId()));
            user.setPersonalEmail(StringUtil.nvl(cnds.getRows().getPersonalEmail()));
            user.setPhone(StringUtil.nvl(cnds.getRows().getPhone()));
            user.setDegree(StringUtil.nvl(cnds.getRows().getDegree()));
            user.setUserName(StringUtil.nvl(cnds.getRows().getUserName()));
            user.setSex(StringUtil.nvl(cnds.getRows().getSex()));
            user.setJobNum(StringUtil.nvl(cnds.getRows().getJobNum()));
            user.setOnjobDate(cnds.getRows().getOnjobDate());
            user.setQq(cnds.getRows().getQq());
            user.setWeixin(cnds.getRows().getWeixin());
            IfmUser iuser = userDao.fetchUserByLngid(login.getLgnId() + "");
            if (null != iuser) {
                user.setStatus(StringUtil.nvl(iuser.getStatus()));
                user.setUserId(StringUtil.parseInt(iuser.getUserId()));
                userDao.updateUserObj(user);
            }else {
                user.setStatus("2");
                userDao.saveUser(user);
            }
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }

    @Override
    public CallBackBean saveUserQudao(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            boolean f = "".equals(cnds.getRows().getId()) || null == cnds.getRows().getId()? true : false;
            IfmLogin tmp = userDao.fetchLoginByUserCode(StringUtil.nvl(cnds.getRows().getUserCode()), f,
                    StringUtil.parseInt(cnds.getRows().getId()));
            if (null != tmp) {
                return this.joinformateJson(json, "该用户名已经被使用，建议使用姓名拼音，若有重复后面加数字区分", "");
            }

            IfmLogin login = new IfmLogin();
            login.setRegisterDate(new Date());
            login.setUserCode(StringUtil.nvl(cnds.getRows().getUserCode()));
            login.setOpenLgnId(StringUtil.nvl(cnds.getUserCode()));

            login.setType(2);
            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));

            if (f) {
                login.setPassword(StringUtil.MD5("123456"));
                userDao.saveLogin(login);

                IfmLogin ifmLogin = userDao.fetchLoginByUserCode(login.getUserCode(), false, 0);
                QdaoSl  qdaoSl = new QdaoSl();
                qdaoSl.setLoginId(ifmLogin.getLgnId());
                qdaoSl.setSource(ifmLogin.getUserCode());
                qdaoSl.setSl(Double.valueOf(1));
                qdaoSl.setUpdateTime(new Date());
                qdaoSl.setCountCount(0);
                qdaoSl.setUv(0);
                qdaoSl.setUvCount(0);
                qdaoSl.setRemark("");
                userDao.saveQdaoSl(qdaoSl);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("添加渠道用户");
                userDao.insertOperate(ifmUserOperate);
            } else {
                int appr_id = StringUtil.parseInt(cnds.getRows().getId());
                IfmLogin log = userDao.fetchLogin(cnds.getRows().getId());
                login.setPassword(log.getPassword());
                login.setLgnId(appr_id);
                userDao.updateLoginObj(login);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("更新用户");
                userDao.insertOperate(ifmUserOperate);
            }
            IfmUser user = new IfmUser();
            user.setCompanyEmail(StringUtil.nvl(cnds.getRows().getCompanyEmail()));
            user.setCreatDate(new Date());
            user.setIdentityCard(StringUtil.nvl(cnds.getRows().getIdentityCard()));
            user.setLiveAddr(StringUtil.nvl(cnds.getRows().getLiveAddr()));
            user.setLoginId(login.getLgnId() + "");
            user.setOrgId(StringUtil.nvl(cnds.getRows().getOrgId()));
            user.setPersonalEmail(StringUtil.nvl(cnds.getRows().getPersonalEmail()));
            user.setPhone(StringUtil.nvl(cnds.getRows().getPhone()));
            user.setDegree(StringUtil.nvl(cnds.getRows().getDegree()));
            user.setUserName(StringUtil.nvl(cnds.getRows().getUserName()));
            user.setSex(StringUtil.nvl(cnds.getRows().getSex()));
            user.setJobNum(StringUtil.nvl(cnds.getRows().getJobNum()));
            user.setOnjobDate(cnds.getRows().getOnjobDate());
            user.setCpa(cnds.getRows().getCpa());
            user.setLoanRate(cnds.getRows().getLoanRate());
            user.setCpaType(cnds.getRows().getCpaType());
            IfmUser iuser = userDao.fetchUserByLngid(login.getLgnId() + "");
            if (null != iuser) {
                user.setStatus(StringUtil.nvl(iuser.getStatus()));
                user.setUserId(StringUtil.parseInt(iuser.getUserId()));
                userDao.updateUserObj(user);
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("更新渠道用户的ifm_user表"
                        + "cpa"+cnds.getRows().getCpa() +"cpaType"+ cnds.getRows().getCpaType()
                        +"loanRate" + cnds.getRows().getLoanRate());
                userDao.insertOperate(ifmUserOperate);
            }else {
                user.setStatus("2");
                userDao.saveUser(user);
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("新增渠道用户的ifm_user表"
                        + "cpa"+cnds.getRows().getCpa() +"cpaType"+ cnds.getRows().getCpaType()
                        +"loanRate" + cnds.getRows().getLoanRate());
                userDao.insertOperate(ifmUserOperate);
            }
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }


    @Override
    public CallBackBean saveQdaoSlList(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            boolean f1 = "".equals(cnds.getRows().getId()) || null == cnds.getRows().getId()? true : false;
            boolean f = "".equals(cnds.getRows().getUser_name()) || null == cnds.getRows().getUser_name()? true : false;


            DateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
            Date updateTime = format.parse(cnds.getRows().getUpdateTime().toString());

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(updateTime);
            cal1.set(Calendar.MINUTE, 0);
            cal1.set(Calendar.SECOND, 0);
            cal1.set(Calendar.MILLISECOND, 0);

            QdaoSl qdaoSl = userDao.fetchLoginByQdSl(cnds.getRows().getUser_code().toString(), f,
                    cal1.getTime().toString(),cnds.getRows().getSl());
            if (null != qdaoSl) {
                return this.joinformateJson(json, "该渠道改时间段已是该百分比", "");
            }

            QdaoSl qdaoSl2 = new QdaoSl();
            qdaoSl2.setSl(cnds.getRows().getSl());
            Calendar cal12 = Calendar.getInstance();
            cal12.setTime(new Date());
            cal12.set(Calendar.MINUTE, 0);
            cal12.set(Calendar.SECOND, 0);
            cal12.set(Calendar.MILLISECOND, 0);
            qdaoSl2.setUpdateTime(updateTime);
            qdaoSl2.setSource(cnds.getRows().getUser_code());
            IfmLogin ifmLogin = userDao.fetchLoginByUserCode(cnds.getRows().getUser_code().toString());
            qdaoSl2.setLoginId(ifmLogin.getLgnId());

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));

            if (f1) {
//                login.setPassword(StringUtil.MD5("123456"));
//                userDao.saveLogin(login);
//
                userDao.saveQdSl(qdaoSl2);
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("添加渠道缩量"+ cnds.getRows().getUser_code()+","+qdaoSl2);
                userDao.insertOperate(ifmUserOperate);
            } else {
                int appr_id = StringUtil.parseInt(cnds.getRows().getId());
                QdaoSl qdaoSl1 = userDao.fetchQdSlByLngid(cnds.getRows().getId());
                qdaoSl1.setSl(cnds.getRows().getSl());
                qdaoSl1.setUpdateTime(updateTime);
                userDao.updateQdSl(qdaoSl1);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("更新渠道缩量" + qdaoSl1.getSource() +"," + cnds.getRows().getSl());
                userDao.insertOperate(ifmUserOperate);
            }
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }


    @Override
    public CallBackBean resetPassword(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            String id = StringUtil.nvl(cnds.getRows().getId());
            IfmUser ifmUser = userDao.fetchUser(id);
            userDao.updateLogin(ifmUser.getLoginId(), StringUtil.MD5("123456"));

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
            ifmUserOperate.setOperate_date(new Date());
            ifmUserOperate.setOperate("重置密码");
            userDao.insertOperate(ifmUserOperate);
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "重置密码失败", "");
        }
    }

    @Override
    public CallBackBean qryRoles(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        QueryResult res = userDao.qryRoles(cnds);
        return this.joinformateJson(json, "success", res.getPager().getRecordCount(), res.getList());
    }

    @Override
    public CallBackBean qryUserRole(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        IfmUser user = userDao.fetchUserByLngid(cnds.getRows().getUserCode());
        List<IfmRole> res = roleDao.qryUserRole(StringUtil.nvl(user.getUserId()));
        return this.joinformateJson(json, "success", 1, res);
    }

    @Override
    public CallBackBean saveRoles(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            boolean f = "".equals(cnds.getRows().getRoleId()) ? true : false;
            IfmRole org = new IfmRole();
            org.setRoleName(StringUtil.nvl(cnds.getRows().getRoleName()));
            org.setRoleCode(StringUtil.nvl(cnds.getRows().getRoleCode()));

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));

            if (f) {
                roleDao.insertRoles(org);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("添加角色");
                userDao.insertOperate(ifmUserOperate);
            } else {
                org.setRoleId(StringUtil.parseInt(cnds.getRows().getRoleId()));
                roleDao.updateRoles(org);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("修改角色");
                userDao.insertOperate(ifmUserOperate);
            }
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }

    @Override
    public CallBackBean deleteRoles(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            roleDao.deleteRoles(StringUtil.nvl(cnds.getRows().getRoleId()));

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
            ifmUserOperate.setOperate_date(new Date());
            ifmUserOperate.setOperate("删除角色");
            userDao.insertOperate(ifmUserOperate);
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }

    @Override
    public CallBackBean qryMeunsByRole(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            String roleId = StringUtil.nvl(cnds.getRows().getRoleId());
            List<IfmRoleMuen> rms = roleDao.qryRoleMuens(roleId);
            List<IfmMuen> meuns = meunDao.qryMeuns();
            List<Map<String, String>> nres = new ArrayList<Map<String, String>>();
            for (IfmMuen m : meuns) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("pId", StringUtil.nvl(m.getParentMuenId()));
                map.put("id", StringUtil.nvl(m.getMuenId()));
                map.put("name", StringUtil.nvl(m.getMuenName()));
                map.put("status", StringUtil.nvl(m.getStatus()));
                if (null != rms && rms.size() > 0) {
                    for (IfmRoleMuen rm : rms) {
                        if (StringUtil.nvl(m.getMuenId()).equals(StringUtil.nvl(rm.getMuenId()))) {
                            map.put("checked", "true");
                            break;
                        }
                    }
                }
                if ("0".equals(StringUtil.nvl(m.getParentMuenId()))) {
                    map.put("icon", "/static/js/ztree/css/zTreeStyle/img/diy/home.png");
                    map.put("open", "true");
                } else {
                    map.put("icon", "/static/js/ztree/css/zTreeStyle/img/diy/dept1.png");
                    map.put("open", "true");
                }
                nres.add(map);
            }
            return this.joinformateJson(json, "success", nres);
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }

    @Override
    public CallBackBean allocationRoles(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            List<IfmUserRole> rms = new ArrayList<IfmUserRole>();
            // 其实是用户ID
            int roleid = StringUtil.parseInt(cnds.getRows().getRoleId());
            IfmUser user = userDao.fetchUserByLngid(roleid + "");
            String meunids = StringUtil.nvl(cnds.getRows().getRoleId_cnd());
            if (!"".equals(meunids)) {
                String[] ms = meunids.split(",");
                for (String m : ms) {
                    if (!"".equals(StringUtil.nvl(m))) {
                        IfmUserRole rm = new IfmUserRole();
                        rm.setUserId(user.getUserId());
                        rm.setRoleId(StringUtil.parseInt(m));
                        rms.add(rm);
                    }
                }
                if (rms.size() > 0) {
                    roleDao.deleteUserRoles(user.getUserId() + "");
                    roleDao.fastInsertUserRole(rms);
                }
            } else {
                return this.joinformateJson(json, "没有选择要分配的菜单项", "");
            }
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }

    @Override
    public CallBackBean allocationMeuns(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            List<IfmRoleMuen> rms = new ArrayList<IfmRoleMuen>();
            String meunids = StringUtil.nvl(cnds.getRows().getMeunId());
            int roleid = StringUtil.parseInt(cnds.getRows().getRoleId());
            if (!"".equals(meunids)) {
                String[] ms = meunids.split(",");
                for (String m : ms) {
                    if (!"".equals(StringUtil.nvl(m))) {
                        IfmRoleMuen rm = new IfmRoleMuen();
                        rm.setMuenId(StringUtil.parseInt(m));
                        rm.setRoleId(roleid);
                        rms.add(rm);
                    }
                }
                if (rms.size() > 0) {
                    roleDao.deleteAllRoles(roleid + "");
                    roleDao.fastInsertRolesMeuns(rms);
                }
            } else {
                return this.joinformateJson(json, "没有选择要分配的菜单项", "");
            }
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }

    @Override
    public CallBackBean qryAllMeuns(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        List<Record> res = userDao.qryAllMuens(cnds);
        List<Map<String, String>> nres = new ArrayList<Map<String, String>>();
        for (Record r : res) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("pId", StringUtil.nvl(r.getString("pid")));
            map.put("id", StringUtil.nvl(r.getString("id")));
            map.put("name", StringUtil.nvl(r.getString("name")));
            map.put("pname", StringUtil.nvl(r.getString("pname")));
            map.put("status", StringUtil.nvl(r.getString("status")));
            map.put("icon", StringUtil.nvl(r.getString("icon")));
            map.put("open", StringUtil.nvl(r.getString("open")));
            map.put("xh", StringUtil.nvl(r.getString("xh")));
            map.put("uri", StringUtil.nvl(r.getString("uri")));
            nres.add(map);
        }
        return this.joinformateJson(json, "success", nres);
    }

    @Override
    public CallBackBean qryMeun(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        String muenId = StringUtil.nvl(cnds.getRows().getMeunId());
        List<Record> res = userDao.qryAllMuens(cnds);
        Record tmp = null;
        for (Record r : res) {
            if (muenId.equals(StringUtil.nvl(r.getString("id")))) {
                tmp = r;
                break;
            }
        }
        return this.joinformateJson(json, "success", tmp);
    }

    @Override
    public CallBackBean saveMuen(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            boolean f = "".equals(cnds.getRows().getMuenId()) ? true : false;
            IfmMuen muen = new IfmMuen();
            muen.setMuenName(StringUtil.nvl(cnds.getRows().getMuenName()));
            muen.setParentMuenId(StringUtil.nvl(cnds.getRows().getParentMuenId()));
            muen.setUri(StringUtil.nvl(cnds.getRows().getUrl()));
            muen.setXh(StringUtil.parseInt(cnds.getRows().getXuhao()));
            muen.setStatus(StringUtil.nvl(cnds.getRows().getStatus()));

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));

            if (f) {
                IfmMuen muen1 = userDao.qryMuenByuri(StringUtil.nvl(cnds.getRows().getUrl()));
                if (muen1 != null && muen1.getMuenId() > 0) {
                    return this.joinformateJson(json, "路径【" + muen1.getUri() + "】出现重复，请重新输入！", "");
                }
                userDao.insertMuen(muen);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("添加菜单");
                userDao.insertOperate(ifmUserOperate);
            } else {
                muen.setMuenId(StringUtil.parseInt(cnds.getRows().getMuenId()));
                IfmMuen muen1 = userDao.qryMuenByuri(StringUtil.nvl(cnds.getRows().getUrl()));
                if (muen1 != null && muen1.getMuenId() > 0) {
                    if (muen1.getMuenId() != StringUtil.parseInt(cnds.getRows().getMuenId())) {
                        return this.joinformateJson(json, "路径【" + muen1.getUri() + "】出现重复，请重新输入！", "");
                    }
                }
                userDao.updateMuen(muen);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("更新菜单");
                userDao.insertOperate(ifmUserOperate);
            }
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }

    @Override
    public CallBackBean deleteMuen(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            String muenId = StringUtil.nvl(cnds.getRows().getMeunId());
            userDao.deleteMuen(muenId);

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
            ifmUserOperate.setOperate_date(new Date());
            ifmUserOperate.setOperate("删除菜单");
            userDao.insertOperate(ifmUserOperate);
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }

}
    
