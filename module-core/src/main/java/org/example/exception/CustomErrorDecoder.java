package org.example.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("[FeignError] Method: {}, Status: {}, Body: {}", methodKey, response.status(), response.body());

        // Response Body를 InputStream에서 읽기
        /**
         * InputStream inputStream = response.body().asInputStream(); // 1. InputStream 가져오기
         * InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8); // 2. InputStream → Reader
         * BufferedReader reader = new BufferedReader(inputStreamReader); // 3. Reader → BufferedReader
         */
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(response.body().asInputStream(), StandardCharsets.UTF_8))) {
            String responseBody = reader.lines().collect(Collectors.joining("\n"));
            extractedJson(responseBody); //cmd + opt + m , 코드의 간결성 가독성을 위해서
        } catch (Exception ex) {
            log.error("[handleFeignException] Failed to process Feign Decode", ex);
        }
        return statusToEnum(response.status()).toException();     // 상태 코드에 따라 TossPyamtn Enum을 매핑하여 예외 반환
    }


    private void extractedJson(String responseBody) throws JsonProcessingException {
        // JSON 응답 파싱
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        // "code"와 "message" 필드 추출
        String code = jsonNode.get("code").asText();
        String message = jsonNode.get("message").asText();
        log.error("[FeignError] Code: {}, Message: {}", code,  message);
    }

    /**
     * 상태 코드 TossPyamtn Enum  매핑
     */
    private TossPyamtn statusToEnum(int statusCode) {
        return switch (statusCode) {
            case 400 -> TossPyamtn.BAD_REQUEST;
            case 404 -> TossPyamtn.NOT_FOUND;
            default -> TossPyamtn.SERVER_ERROR;
        };
    }
}
