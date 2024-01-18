package br.com.customerregistration.enums;

public enum EStateUF {
    RO(11), AC(12), AM(13), RR(14), PA(15), AP(16), TO(17),
    MA(21), PI(22), CE(23), RN(24), PB(25), PE(26), AL(27),
    SE(28), BA(29), MG(31), ES(32), RJ(33), SP(35), PR(41),
    SC(42), RS(43), MS(50), MT(51), GO(52), DF(53);


    private final Integer UF_CODE;
    EStateUF(Integer UF_CODE){
        this.UF_CODE = UF_CODE;
    }

    public Integer getUF() {
        return UF_CODE;
    }
}