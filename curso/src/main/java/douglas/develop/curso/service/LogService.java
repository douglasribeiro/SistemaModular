package douglas.develop.curso.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url= "localhost:8087/send" , name = "log")
public interface LogService {
}
