package com.yobtc.domain.vo;

import com.yobtc.domain.C2cCoinInfo;
import com.yobtc.domain.C2cSellCoinTradeVO;
import com.yobtc.domain.CustomerUser;

/**
 * Created by Administrator on 2017/11/22 0022.
 */
public class C2cSellCoinTradeCapitalVO {
    private C2cCoinInfo c2cCoinInfo;
    private CustomerUser customerUser;
    private C2cSellCoinTradeVO vo;

    public C2cCoinInfo getC2cCoinInfo() {
        return c2cCoinInfo;
    }

    public void setC2cCoinInfo(C2cCoinInfo c2cCoinInfo) {
        this.c2cCoinInfo = c2cCoinInfo;
    }

    public CustomerUser getCustomerUser() {
        return customerUser;
    }

    public void setCustomerUser(CustomerUser customerUser) {
        this.customerUser = customerUser;
    }

    public C2cSellCoinTradeVO getVo() {
        return vo;
    }

    public void setVo(C2cSellCoinTradeVO vo) {
        this.vo = vo;
    }
}
