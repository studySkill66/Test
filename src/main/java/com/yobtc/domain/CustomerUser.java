package com.yobtc.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value = "CustomerUser", description = "服务用户信息")
public class CustomerUser {
    @ApiModelProperty(value = "用户ID")
    private Integer id;
    @ApiModelProperty(value = "登录账号")
    private String accountname;
    @ApiModelProperty(value = "登录密码")
    private String loginpass;
    @ApiModelProperty(value = "真实姓名")
    private String realname;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "注册IP")
    private String regip;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "国家代码")
    private String countrycode;
    @ApiModelProperty(value = "账号是否冻结")
    private Boolean isfrozen;
    @ApiModelProperty(value = "是否允许交易")
    private Boolean istrade;
    @ApiModelProperty(value = "是否允许提现")
    private Boolean iswithdraw;
    @ApiModelProperty(value = "创建时间")
    private Long createtime;
    @ApiModelProperty(value = "注册源")
    private String regsource;
    @ApiModelProperty(value = "邀请码")
    private String reccode;
    @ApiModelProperty(value = "积分等级")
    private Integer accumulateLevel;
    @ApiModelProperty(value = "指定等级")
    private Integer level;
    @ApiModelProperty(value = "用户类型（1：积分等级用户；2：指定等级用户；）")
    private Integer userType;
    @ApiModelProperty(value = "支付密码")
    private String secret;
    @ApiModelProperty(value = "用户积分余额")
    private BigDecimal accumulate;
    @ApiModelProperty(value = "积分冻结余额")
    private BigDecimal accumulateFreeze;
    @ApiModelProperty(value = "Google密钥")
    private String googleSecret;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname == null ? null : accountname.trim();
    }

    public String getLoginpass() {
        return loginpass;
    }

    public void setLoginpass(String loginpass) {
        this.loginpass = loginpass == null ? null : loginpass.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getRegip() {
        return regip;
    }

    public void setRegip(String regip) {
        this.regip = regip == null ? null : regip.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode == null ? null : countrycode.trim();
    }

    public Boolean getIsfrozen() {
        return isfrozen;
    }

    public void setIsfrozen(Boolean isfrozen) {
        this.isfrozen = isfrozen;
    }

    public Boolean getIstrade() {
        return istrade;
    }

    public void setIstrade(Boolean istrade) {
        this.istrade = istrade;
    }

    public Boolean getIswithdraw() {
        return iswithdraw;
    }

    public void setIswithdraw(Boolean iswithdraw) {
        this.iswithdraw = iswithdraw;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getRegsource() {
        return regsource;
    }

    public void setRegsource(String regsource) {
        this.regsource = regsource == null ? null : regsource.trim();
    }

    public String getReccode() {
        return reccode;
    }

    public void setReccode(String reccode) {
        this.reccode = reccode == null ? null : reccode.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getAccumulateLevel() {
        return accumulateLevel;
    }

    public void setAccumulateLevel(Integer accumulateLevel) {
        this.accumulateLevel = accumulateLevel;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret == null ? null : secret.trim();
    }

    public BigDecimal getAccumulate() {
        return accumulate;
    }

    public void setAccumulate(BigDecimal accumulate) {
        this.accumulate = accumulate;
    }

    public BigDecimal getAccumulateFreeze() {
        return accumulateFreeze;
    }

    public void setAccumulateFreeze(BigDecimal accumulateFreeze) {
        this.accumulateFreeze = accumulateFreeze;
    }

    public String getGoogleSecret() {
        return googleSecret;
    }

    public void setGoogleSecret(String googleSecret) {
        this.googleSecret = googleSecret;
    }

    @Override
    public String toString() {
        return "CustomerUser{" +
                "id=" + id +
                ", accountname='" + accountname + '\'' +
                ", loginpass='" + loginpass + '\'' +
                ", realname='" + realname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", regip='" + regip + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", countrycode='" + countrycode + '\'' +
                ", isfrozen=" + isfrozen +
                ", istrade=" + istrade +
                ", iswithdraw=" + iswithdraw +
                ", createtime=" + createtime +
                ", regsource='" + regsource + '\'' +
                ", reccode='" + reccode + '\'' +
                ", accumulateLevel=" + accumulateLevel +
                ", level=" + level +
                ", userType=" + userType +
                ", secret='" + secret + '\'' +
                ", accumulate=" + accumulate +
                ", accumulateFreeze=" + accumulateFreeze +
                ", googleSecret='" + googleSecret + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}