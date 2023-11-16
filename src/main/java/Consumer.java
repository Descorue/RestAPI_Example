import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Consumer {
    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        /*String urlGet = "https://reqres.in/api/users/2";
        String resultGet = restTemplate.getForObject(urlGet, String.class);

        Map<String, String> user = new HashMap<>();
        user.put("name", "Ivan");
        user.put("job", "chlen");

        HttpEntity<Map<String, String>> reques = new HttpEntity<>(user);

        String urlPost = "https://reqres.in/api/users";
        String resultPost = restTemplate.postForObject(urlPost, reques, String.class);*/

        Scanner scanner = new Scanner(System.in);
        String sentenceToTranslate = scanner.nextLine();
        String urlTrans = "https://translate.api.cloud.yandex.net/translate/v2/translate"; //стандантрая ссылка для перевода

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); //тип аппликации ставм
        headers.add("Authorization", "Bearer " + "t1.9euelZqbmo6OzZ6LnZCTno_Pk8edju3rnpWak5DHx4uRzo-Xyo6TyJrOjZDl8_cdUihV-e9HKAdj_d3z910AJlX570coB2P9zef1656Vmo6Ny4yKxs-dnJWbyJWeiYvM7_zN5_XrnpWajoySj87Iic2Vko7PmMiKkJDv_cXrnpWajo3LjIrGz52clZvIlZ6Ji8w.1ESKXJo1pv7UKJfO02nb_rJxuTmLCZxNlGbuIgXPrr7hZSmkQRILzLlFO2fiNBRP6r5JZTzhMqclIw1DHMKHDw"); //токен указываем

        Map<String, String> map = new HashMap<>();
        map.put("folderId", "b1g70p2ul4ei3ulbjjg8"); //фолдер айди, по этой ссылке виден (вверху) https://console.cloud.yandex.ru/folders/b1g70p2ul4ei3ulbjjg8?section=service-accounts
        map.put("targetLanguageCode", "en"); //язык
        map.put("texts", "[" + sentenceToTranslate + "]"); //предложение для перевода

        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);

        YandexResponse yandexResponse = restTemplate.postForObject(urlTrans, request, YandexResponse.class);
        //System.out.println(result);

        //парсим JSON с помощью Jackson

        /*ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(result);*/


        System.out.println("Перевод: " + yandexResponse.getTranslationList().get(0).getText());





        /*System.out.println(resultGet);
        System.out.println(resultPost);*/
    }
}
