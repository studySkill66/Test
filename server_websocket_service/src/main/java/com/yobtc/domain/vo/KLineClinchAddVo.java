package com.yobtc.domain.vo;

public class KLineClinchAddVo {

    private String ch;
    private Long ts;
    private KLineTickVo tick;

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public KLineTickVo getTick() {
        return tick;
    }

    public void setTick(KLineTickVo tick) {
        this.tick = tick;
    }

}
