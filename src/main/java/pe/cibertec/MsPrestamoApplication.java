package pe.cibertec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MsPrestamoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPrestamoApplication.class, args);
		System.out.println("ms-prestamo iniciado correctamente");
	}

}
