package com.tuniu.abt.intf.tsp.dto.adt;

/**
 * 运价系统返回的算后价格信息
 * @author tangchuandong
 *
 */
public class FreightCalculateResultVo {
	 // 算前的价格
     private int  price ;
     
     // 算后的价格
     private int  salePrice ;
     
     // 销控平台的id
     private int  saleId;
     
     private int solutionId;

	public int getPrice() {
		return price;
	}

	public void setPrice(int costPrice) {
		this.price = costPrice;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public int getSolutionId() {
		return solutionId;
	}

	public void setSolutionId(int solutionId) {
		this.solutionId = solutionId;
	}
    
     
}
