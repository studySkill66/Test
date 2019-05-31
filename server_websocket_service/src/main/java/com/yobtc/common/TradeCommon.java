package com.yobtc.common;

import com.yobtc.api.mq.product.ifac.FinanceCoinBaseInfoIfac;
import com.yobtc.domain.vo.FinanceCoinBaseInfo;
import org.apache.commons.lang3.StringUtils;

public class TradeCommon {
    private FinanceCoinBaseInfoIfac financeCoinBaseInfoIfac;
    private boolean myResult;
    private String coinName;
    private String payCoinName;
    private ResultJson json;
    private DataModel<FinanceCoinBaseInfo> coinInfo;
    private Integer paycoinId;

    public TradeCommon(String coinName, String payCoinName, ResultJson json, FinanceCoinBaseInfoIfac financeCoinBaseInfoIfac) {
        this.coinName = coinName;
        this.payCoinName = payCoinName;
        this.json = json;
        this.financeCoinBaseInfoIfac = financeCoinBaseInfoIfac;
    }

    public boolean is() {
        return myResult;
    }

    public DataModel<FinanceCoinBaseInfo> getCoinInfo() {
        return coinInfo;
    }

    public Integer getPaycoinId() {
        return paycoinId;
    }

    public TradeCommon invoke() {
        if (StringUtils.isBlank(coinName) || StringUtils.isBlank(payCoinName)) {
            json.setResultCode(ResultCode.PARAM_IS_NOT_EMPTY);
            myResult = true;
            return this;
        }
        if (StringUtils.isBlank(coinName) && StringUtils.isBlank(payCoinName)) {
            json.setResultCode( ResultCode.PARAM_IS_NOT_EMPTY);
            myResult = true;
            return this;
        }
        coinInfo = financeCoinBaseInfoIfac.getCoinInfoByCoinName(coinName);
        if (coinInfo.getCode() != 0) {
            json.setResultCode(ResultCode.SERVICE_INVOKE_ERROR);
            myResult = true;
            return this;
        }
        if (coinInfo.getModel() == null) {
            json.setResultCode(ResultCode.COIN_BASE_INFO_NOT_EXISTS);
            myResult = true;
            return this;
        }
        paycoinId = -1;
        if (!payCoinName.equalsIgnoreCase("HKD")) {
            DataModel<FinanceCoinBaseInfo> payCoinInfo = financeCoinBaseInfoIfac.getCoinInfoByCoinName(payCoinName);
            if (payCoinInfo.getCode() != 0) {
                json.setResultCode(ResultCode.SERVICE_INVOKE_ERROR);
                myResult = true;
                return this;
            }
            if (payCoinInfo.getModel() == null) {
                json.setResultCode(ResultCode.COIN_BASE_INFO_NOT_EXISTS);
                myResult = true;
                return this;
            }
            paycoinId = payCoinInfo.getModel().getId();
        } else {
            paycoinId = 0;
        }
        myResult = false;
        return this;
    }

    public TradeCommon invokeSelective() {
        if (StringUtils.isNotBlank(coinName)) {
            coinInfo = financeCoinBaseInfoIfac.getCoinInfoByCoinName(coinName);
            if (coinInfo.getCode() != 0) {
                json.setResultCode( ResultCode.SERVICE_INVOKE_ERROR);
                myResult = true;
                return this;
            }
            if (coinInfo.getModel() == null) {
                json.setResultCode(ResultCode.COIN_BASE_INFO_NOT_EXISTS);
                myResult = true;
                return this;
            }
        }

        if (StringUtils.isNotBlank(payCoinName)) {
            paycoinId = -1;
            if (!payCoinName.equalsIgnoreCase("HKD")) {
                DataModel<FinanceCoinBaseInfo> payCoinInfo = financeCoinBaseInfoIfac.getCoinInfoByCoinName(payCoinName);
                if (payCoinInfo.getCode() != 0) {
                    json.setResultCode(ResultCode.SERVICE_INVOKE_ERROR);
                    myResult = true;
                    return this;
                }
                if (payCoinInfo.getModel() == null) {
                    json.setResultCode( ResultCode.COIN_BASE_INFO_NOT_EXISTS);
                    myResult = true;
                    return this;
                }
                paycoinId = payCoinInfo.getModel().getId();
            } else {
                paycoinId = 0;
            }
        }

        myResult = false;
        return this;
    }
}
