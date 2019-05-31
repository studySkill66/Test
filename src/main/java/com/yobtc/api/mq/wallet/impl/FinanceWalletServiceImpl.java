package com.yobtc.api.mq.wallet.impl;

import com.yobtc.api.mq.capital.ifac.UserCapitalClientIfac;
import com.yobtc.api.mq.product.ifac.FinanceCoinBaseInfoIfac;
import com.yobtc.api.mq.trade.ifac.TradeClientIfac;
import com.yobtc.api.mq.user.ifac.CustomerUserClientIfac;
import com.yobtc.api.mq.wallet.FinanceWalletService;
import com.yobtc.common.DataModel;
import com.yobtc.common.ResultCode;
import com.yobtc.common.ResultJson;
import com.yobtc.common.TradeCommon;
import com.yobtc.domain.CustomerSecurityPolicy;
import com.yobtc.domain.FinanceWallet;
import com.yobtc.domain.vo.FinanceCoinBaseInfo;
import com.yobtc.domain.vo.FinanceCoinTradeInfo;
import com.yobtc.domain.vo.LevelAndAuthVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service("FinanceWalletService")
public class FinanceWalletServiceImpl implements FinanceWalletService {

    @Autowired
    FinanceCoinBaseInfoIfac financeCoinBaseInfoIfac;

    @Autowired
    UserCapitalClientIfac userCapitalClientIfac;

    @Autowired
    TradeClientIfac tradeClientIfac;

    @Autowired
    CustomerUserClientIfac customerUserClientIfac;

    @Override
    public ResultJson<Map<String, Object>> selectCoinTradeInfoAndWalletInfo(String coinName, String payCoinName, Integer userId) {
        ResultJson<Map<String, Object>> json = new ResultJson<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        TradeCommon tradeCommon = new TradeCommon(coinName, payCoinName, json, financeCoinBaseInfoIfac).invoke();
        if (tradeCommon.is()) return json;
        DataModel<FinanceCoinBaseInfo> coinInfo = tradeCommon.getCoinInfo();
        Integer paycoinId = tradeCommon.getPaycoinId();
        Integer coinId = coinInfo.getModel().getId() == null ? -1 : coinInfo.getModel().getId();
        userId = userId == null ? -1 : userId;

        DataModel<FinanceCoinTradeInfo> tradeInfoDataModel = financeCoinBaseInfoIfac.getFinanceCoinTradeInfo(coinId, paycoinId);
        if (tradeInfoDataModel.getCode() == 0) {
            if (tradeInfoDataModel.getModel() != null) {
                if (this.userCapitalClientIfac.getIsUserCoinFreeCharge(userId, coinId).getModel()) {
                    tradeInfoDataModel.getModel().setTradebuyfee(BigDecimal.ZERO);
                    tradeInfoDataModel.getModel().setTradesellfee(BigDecimal.ZERO);
                    tradeInfoDataModel.getModel().setTradeportbuyfee(BigDecimal.ZERO);
                    tradeInfoDataModel.getModel().setTradeportsellfee(BigDecimal.ZERO);
                }
                map.put("tradeCoinInfo", tradeInfoDataModel.getModel());
            } else {
                map.put("tradeCoinInfo", new FinanceCoinTradeInfo());
            }
        } else {
            map.put("tradeCoinInfo", new FinanceCoinTradeInfo());
        }
        if (userId > 0) {
            DataModel<FinanceWallet> walletDataModel = tradeClientIfac.selectWalletInfo(userId, coinId);
            if (walletDataModel.getCode() == 0) {
                if (walletDataModel.getModel() != null) {
                    map.put("sellWalletCoinInfo", walletDataModel.getModel());
                } else {
                    map.put("sellWalletCoinInfo", new FinanceWallet());
                }
            } else {
                map.put("sellWalletCoinInfo", new FinanceWallet());
            }
            DataModel<FinanceWallet> payWalletDataModel = tradeClientIfac.selectWalletInfo(userId, paycoinId);
            if (payWalletDataModel.getCode() == 0) {
                if (payWalletDataModel.getModel() != null) {
                    map.put("buyWalletCoinInfo", payWalletDataModel.getModel());
                } else {
                    map.put("buyWalletCoinInfo", new FinanceWallet());
                }
            } else {
                map.put("buyWalletCoinInfo", new FinanceWallet());
            }
        } else {
            map.put("buyWalletCoinInfo", new FinanceWallet());
            map.put("sellWalletCoinInfo", new FinanceWallet());

        }
        DataModel<LevelAndAuthVO> userData = customerUserClientIfac.getLevelAndAuthByUserId(userId);
        if (userData.getCode() == 0) {
            LevelAndAuthVO levelAndAuthVO = userData.getModel();
            if (levelAndAuthVO != null) {
                map.put("sellCopper", levelAndAuthVO.getSellCoin());
                map.put("buyCopper", levelAndAuthVO.getPayCoin());
                map.put("tradeCenterDepth", levelAndAuthVO.getTradeCenterDepth());
                map.put("isBatchUndoOrder", levelAndAuthVO.getBatchUndoOrder());
            } else {
                map.put("sellCopper", 1);
                map.put("buyCopper", 1);
                map.put("tradeCenterDepth", 0);
                map.put("isBatchUndoOrder", false);
            }
        } else {
            map.put("sellCopper", 1);
            map.put("buyCopper", 1);
            map.put("tradeCenterDepth", 0);
            map.put("isBatchUndoOrder", false);
        }
        DataModel<CustomerSecurityPolicy> policyDataModel = customerUserClientIfac.getCustomerSecurityPolicyById(userId);
        if (policyDataModel.getCode() == 0) {
            CustomerSecurityPolicy policy = new CustomerSecurityPolicy();
            if (policyDataModel != null) {
                policy = policyDataModel.getModel();
            }
            if (policy != null) {
                map.put("tradeAre", policy.getUsetradeare());
            } else {
                map.put("tradeAre", false);
            }
        } else {
            map.put("tradeAre", false);
        }
        json.setModel(map);
        json.setResultCode(ResultCode.OK);
        return json;
    }
}
