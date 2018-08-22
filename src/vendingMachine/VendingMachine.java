package vendingMachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import exceptions.AllSoldException;
import exceptions.NotEnoughChangeException;
import exceptions.NotEnoughCoinsException;
import model.Coin;
import model.Inventory;
import model.Product;

/**
 * Basic Vending Machine implementation
 */
public class VendingMachine implements VendingMachineAPI {
	
	private Inventory<Coin> coinInventory = new Inventory<Coin>();
	private Inventory<Product> productInventory = new Inventory<Product>();
	private Product product;
	private long coins;

	public VendingMachine() {
		for (Product p : Product.values()) {
			productInventory.put(p, 10);
		}
		for (Coin c : Coin.values()) {
			coinInventory.put(c, 5);
		}
	}

	@Override
	public void reset() {
		coinInventory.clear();
		productInventory.clear();
		product = null;
		coins = 0;
	}

	@Override
	public void insertCoin(Coin coin) {
		this.coins = coins + coin.getValue();
		coinInventory.add(coin);
	}

	@Override
    public long getProductWithPrice(Product product) {
        if (productInventory.hasProduct(product)) {
            this.product = product;
            return product.getPrice();
        }
        throw new AllSoldException(AllSoldException.MESSAGE + product.name());
    }

	@Override
	public Pair<Product, List<Coin>> getProductAndChange() {
		Product product = collectProduct();
		List<Coin> change = collectChange(); 
		return Pair.of(product, change);
	}

	@Override
	public List<Coin> refund() {
		List<Coin> refund = getChange(coins);
		updateCoinsInventory(refund);
		coins = 0;
		product = null;
		return refund;
	}

	
	private boolean isFullPaid() {
		return (coins >= product.getPrice()) ;
	}

	private boolean hasEnoughChange() {
		try {
			getChange(coins - product.getPrice());
		} catch (NotEnoughChangeException nsce) {
			return false;
		}
		return true;
	}

	private List<Coin> getChange(long amount) throws NotEnoughChangeException {
		List<Coin> changes = Collections.emptyList();
		if (amount > 0) {
			changes = new ArrayList<Coin>();
			long balance = amount;
			while (balance > 0) {
				if (balance >= Coin.COIN_2.getValue() && coinInventory.hasProduct(Coin.COIN_2)) {
					changes.add(Coin.COIN_2);
					balance = balance - Coin.COIN_2.getValue();
					continue;
				} else if (balance >= Coin.COIN_1.getValue() && coinInventory.hasProduct(Coin.COIN_1)) {
					changes.add(Coin.COIN_1);
					balance = balance - Coin.COIN_1.getValue();
					continue;
				} else if (balance >= Coin.COIN_0_5.getValue() && coinInventory.hasProduct(Coin.COIN_0_5)) {
					changes.add(Coin.COIN_0_5);
					balance = balance - Coin.COIN_0_5.getValue();
					continue;
				} else if (balance >= Coin.COIN_0_2.getValue() && coinInventory.hasProduct(Coin.COIN_0_2)) {
					changes.add(Coin.COIN_0_2);
					balance = balance - Coin.COIN_0_2.getValue();
					continue;
				} else if (balance >= Coin.COIN_0_1.getValue() && coinInventory.hasProduct(Coin.COIN_0_1)) {
					changes.add(Coin.COIN_0_1);
					balance = balance - Coin.COIN_0_1.getValue();
					continue;
				} else if (balance >= Coin.COIN_0_05.getValue() && coinInventory.hasProduct(Coin.COIN_0_05)) {
					changes.add(Coin.COIN_0_05);
					balance = balance - Coin.COIN_0_05.getValue();
					continue;
				} else {
					throw new NotEnoughChangeException(NotEnoughChangeException.MESSAGE);
				}
			}
		}
		return changes;
	}

	private List<Coin> collectChange() {
		long changeAmount = coins - product.getPrice();
		List<Coin> change = getChange(changeAmount);
		updateCoinsInventory(change);
		coins = 0;
		product = null;
		return change;
	}

	private Product collectProduct() throws NotEnoughChangeException, NotEnoughCoinsException {
		if (isFullPaid()) {
			if (hasEnoughChange()) {
				productInventory.remove(product);
				return product;
			}
			throw new NotEnoughChangeException(NotEnoughChangeException.MESSAGE);
		}
		long remainingCoins = product.getPrice() - coins;
		throw new NotEnoughCoinsException(NotEnoughCoinsException.MESSAGE_REMAING_COINS, remainingCoins);
	}

	private void updateCoinsInventory(List<Coin> change) {
		for (Coin c : change) {
			coinInventory.remove(c);
		}
	}

}