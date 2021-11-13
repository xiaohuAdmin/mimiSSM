package com.hu.vo;

public class ProductInfoVo {
    //封装前台往后传递的条件查询

    //商品名称
    private String pName;
    //商品类型
    private Integer typeId;
    //商品最低价格
    private Integer lprice;
    //商品最高价格
    private Integer hprice;

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getLprice() {
        return lprice;
    }

    public void setLprice(Integer lprice) {
        this.lprice = lprice;
    }

    public Integer getHprice() {
        return hprice;
    }

    public void setHprice(Integer hprice) {
        this.hprice = hprice;
    }

    public ProductInfoVo(String pName, Integer typeId, Integer lprice, Integer hprice) {
        this.pName = pName;
        this.typeId = typeId;
        this.lprice = lprice;
        this.hprice = hprice;
    }

    public ProductInfoVo() {
    }

    @Override
    public String toString() {
        return "ProductInfoVo{" +
                "pName='" + pName + '\'' +
                ", typeId=" + typeId +
                ", lprice=" + lprice +
                ", hprice=" + hprice +
                '}';
    }
}
