package project.vendingmachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

/**
 * <b>Simple Vending machine simulator.</b>
 *
 *
 * This program is a vending machine simulator and have folowing functionality <br>
 * <ul>
 *     <li>add item type</li>
 *     <li>update item type</li>
 *     <li>get all item types</li>
 *     <li>deposit item</li>
 *     <li>withdraw item</li>
 *     <li>get available item list</li>
 * </ul><br>
 *
 * @author "Narek Chomoyan"
 */
@SpringBootApplication
@EnableSpringConfigured
@ImportResource({"classpath*:applicationContext.xml"})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
