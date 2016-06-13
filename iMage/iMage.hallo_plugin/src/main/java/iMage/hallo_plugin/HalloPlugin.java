package iMage.hallo_plugin;

import javax.swing.JOptionPane;

import org.iMage.plugins.JmjrstPlugin;
import org.jis.Main;

public class HalloPlugin extends JmjrstPlugin {
	
	private String name = "Hallo-SWT1-Plugin";

	@Override
	public String getMenuText() {
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void init(Main main) {
		System.out.println("Ich initialisiere mich fleissig!");
		
	}

	@Override
	public void run() {		
		System.err.println("iMage - nur echt mit JMJRST!");
		
	}

	@Override
	public boolean isConfigurable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void configure() {
		JOptionPane.showMessageDialog(null, name);
		
	}



}