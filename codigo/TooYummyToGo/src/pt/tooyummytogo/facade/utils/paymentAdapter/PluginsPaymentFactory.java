package pt.tooyummytogo.facade.utils.paymentAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PluginsPaymentFactory {

	public static List<PaymentAdapter> getPaymentPluginsList() {
		List<PaymentAdapter> plugins = new ArrayList<>();

		Properties paymentProp = new Properties();
		try {
			paymentProp.load(new FileInputStream(new File("payment.properties")));

			// add default to first element of the list
			String defaultPaymentPlugin = paymentProp.getProperty("defaultPaymentPlugin");
			addToList(plugins, defaultPaymentPlugin);

			String possiblePaymentPlugins = paymentProp.getProperty("possiblePaymentPlugins");
			for (String paymentMethod : possiblePaymentPlugins.split(",")) {
				addToList(plugins, paymentMethod);
			}

		} catch (IOException e) {
			// caso falhe a leitura de ficheiro o monsterCard serah a predefinicao
			plugins.add(new MonsterCardAdapter());
		}
		return plugins;
	}

	private static void addToList(List<PaymentAdapter> plugins, String paymentMethod) {
		try {
			Class<?> klass = Class.forName(paymentMethod);
			Constructor<?> cons = klass.getConstructor();
			PaymentAdapter payment = (PaymentAdapter) cons.newInstance();
			plugins.add(payment);
		} catch (ClassNotFoundException e) {
			// Do nothing
		} catch (NoSuchMethodException e) {
			// Do nothing
		} catch (SecurityException e) {
			// Do nothing
		} catch (InstantiationException e) {
			// Do nothing
		} catch (IllegalAccessException e) {
			// Do nothing
		} catch (IllegalArgumentException e) {
			// Do nothing
		} catch (InvocationTargetException e) {
			// Do nothing
		}
	}
}
