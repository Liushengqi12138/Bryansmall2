package lsq.Bryan.domain;

public class CartItem {
	private Good good;
	private int quantity;
	private double price;
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.price = this.good.getPrice() * quantity;//��Ʒ������һ���£���Ʒ���ܼ����ϸ���
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
