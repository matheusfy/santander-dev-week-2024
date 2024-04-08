package me.dio.sdw2024.adapters.out;

import feign.RequestInterceptor;
import me.dio.sdw2024.domain.ports.GenerativeAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "geminiAiChatApi", url = "${gemini.base-url}", configuration = GeminiAiChatService.Config.class)
public interface GeminiAiChatService extends GenerativeAiService {

    @PostMapping("/v1/chat/completions")
    OpenAiApiCompletionResponse chatCompletion(OpenAiCompletionRequest req);

    @Override
    default String generateContent(String objective, String context){

        String model = "gpt-3.5-turbo";
        List<Message> lstMessages = List.of(
                new Message("system", objective),
                new Message("user", context)
                );
        OpenAiCompletionRequest request = new OpenAiCompletionRequest(model, lstMessages);

        OpenAiApiCompletionResponse response = chatCompletion(request);


        return response.choices().getFirst().message().content();
    }

    record OpenAiCompletionRequest(String model, List<Message> messages){ }
    record Message(String role, String content){ }
    record OpenAiApiCompletionResponse(List<Choice> choices){ }
    record Choice(Message message){ }

    class Config {
        @Bean
         public RequestInterceptor apiKeyRequestInterceptor(@Value("${openapi.apiKey}") String apiKey) {
            return requestTemplate -> requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
        }
    }
}
