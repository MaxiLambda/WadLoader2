package lincks.maximilian.wadloader2;

import lincks.maximilian.wadloader2.config.WadExtensionConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@SpringBootApplication
public class WadLoader2Application extends JFrame{
	public WadLoader2Application (WadExtensionConfiguration config){
		initUI();
		this.config = config;
	}

	private final WadExtensionConfiguration config;
	//TODO: build fitting ui
	private void initUI() {

		var quitButton = new JButton("Quit");

		quitButton.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});

		createLayout(quitButton);

		setTitle("Quit button");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createLayout(JComponent... arg) {

		var pane = getContentPane();
		var gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateContainerGaps(true);

		gl.setHorizontalGroup(gl.createSequentialGroup()
				.addComponent(arg[0])
		);

		gl.setVerticalGroup(gl.createSequentialGroup()
				.addComponent(arg[0])
		);
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(WadLoader2Application.class)
				.headless(false).run(args);

		EventQueue.invokeLater(() -> {

			WadLoader2Application ex = ctx.getBean(WadLoader2Application.class);
			ex.setVisible(true);
		});
	}
}
