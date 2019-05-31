package com.yobtc.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户安全策略")
public class CustomerSecurityPolicy {
    @ApiModelProperty(value = "用户ID", hidden = true)
    private Integer id;
    @ApiModelProperty(value = "登录启用安全策略", required = true)
    private Boolean uselogin;
    @ApiModelProperty(value = "挂单启用安全策略", required = true)
    private Boolean usetradeare;
    @ApiModelProperty(value = "PUSH交易启用安全策略", required = true)
    private Boolean usepush;
    @ApiModelProperty(value = "修改登录密码启用安全策略", required = true)
    private Boolean useloginpass;
    @ApiModelProperty(value = "设置交易密码启用安全策略", required = true)
    private Boolean usetrade;
    @ApiModelProperty(value = "发起提现启用安全策略", required = true)
    private Boolean usefinancermb;
    @ApiModelProperty(value = "发起提币启用安全策略", required = true)
    private Boolean usefinancecoin;
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

    public Boolean getUselogin() {
        return uselogin;
    }

    public void setUselogin(Boolean uselogin) {
        this.uselogin = uselogin;
    }

    public Boolean getUsetradeare() {
        return usetradeare;
    }

    public void setUsetradeare(Boolean usetradeare) {
        this.usetradeare = usetradeare;
    }

    public Boolean getUsepush() {
        return usepush;
    }

    public void setUsepush(Boolean usepush) {
        this.usepush = usepush;
    }

    public Boolean getUseloginpass() {
        return useloginpass;
    }

    public void setUseloginpass(Boolean useloginpass) {
        this.useloginpass = useloginpass;
    }

    public Boolean getUsetrade() {
        return usetrade;
    }

    public void setUsetrade(Boolean usetrade) {
        this.usetrade = usetrade;
    }

    public Boolean getUsefinancermb() {
        return usefinancermb;
    }

    public void setUsefinancermb(Boolean usefinancermb) {
        this.usefinancermb = usefinancermb;
    }

    public Boolean getUsefinancecoin() {
        return usefinancecoin;
    }

    public void setUsefinancecoin(Boolean usefinancecoin) {
        this.usefinancecoin = usefinancecoin;
    }
}