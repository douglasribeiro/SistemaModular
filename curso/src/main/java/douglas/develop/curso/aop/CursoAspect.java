package douglas.develop.curso.aop;

import douglas.develop.core.model.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static douglas.develop.utils.Utils.nomeUser;

@Slf4j
@Component
@Aspect
@EnableAspectJAutoProxy
public class CursoAspect {

    @After(value = "execution(* douglas.develop.curso.service.CursoService.list(..))) && args(String)")
    public void lista() throws JSONException {
        log.info("AOP Lista.............");
        log.info("Username "+ nomeUser());
        Calendar brDate = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"),new Locale("pr", "BR"));
        SystemLog systemLog = new SystemLog(null, nomeUser(), brDate.getTime(), "Listagem todos os registros de curso");
        log.info("Username "+ systemLog);

        salvaLog(systemLog);
    }

    @After(value = "execution(* douglas.develop.curso.service.CursoService.findById(..)) && args(Long)")
    public void lista2() throws JSONException {
        log.info("AOP findById.............");
        log.info("Username "+ nomeUser());
        Calendar brDate = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"),new Locale("pr", "BR"));
        SystemLog systemLog = new SystemLog(null, nomeUser(), brDate.getTime(), "Listagem de cursos por id");
        log.info("Username "+ systemLog);

        salvaLog(systemLog);

    }

    private static void salvaLog(SystemLog systemLog) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");

        headers.setAll(map);

        try {
            HttpEntity<?> request = new HttpEntity<>(systemLog, headers);
            String url = "http://localhost:8087/send";

            ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, String.class);

        } catch (Exception e){

        }
    }

}
