package me.dio.sdw2024.adapters.out;

import feign.FeignException;
import feign.RequestInterceptor;
import me.dio.sdw2024.domain.ports.GenerativeAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@ConditionalOnProperty(name =  "generative-ai.provider", havingValue = "GEMINI", matchIfMissing = true)
@FeignClient(name = "geminiAiChatApi", url = "${gemini.base-url}", configuration = GeminiAiChatService.Config.class)
public interface GeminiAiChatService extends GenerativeAiService {

    @PostMapping("/v1beta/models/gemini-pro:generateContent")
    GeminiApiCompletionResponse chatCompletion(GeminiAiCompletionRequest req);

    @Override
    default String generateContent(String objective, String context){

        String inputText = """
                %s
                %s
                """.formatted(objective, context);

        GeminiAiCompletionRequest request = new GeminiAiCompletionRequest(
                List.of(new Contents(List.of(new Parts(inputText))))
        );

        try {
            GeminiApiCompletionResponse response = chatCompletion(request);
            return response.candidates().getFirst().content().parts().getFirst().text();
        } catch (FeignException httpErrors){
            return "httpErrors";
        } catch (Exception unexpectedError){
            return "unexpectedError";
        }

    }

    record GeminiAiCompletionRequest(List<Contents> contents){ }
    record GeminiApiCompletionResponse(List<Candidates> candidates){}
    record Candidates(Contents content){}
    record Contents(List<Parts> parts){}
    record Parts(String text){}

    class Config {
        @Bean
         public RequestInterceptor apiKeyRequestInterceptor(@Value("${gemini.apiKey}") String apiKey) {
            return requestTemplate -> requestTemplate.query("key", apiKey);
        }
    }
}
