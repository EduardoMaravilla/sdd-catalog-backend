package org.eduardomaravill.sdd_catalogo.configs.mailjet;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailJetConfig {
    @Value("${mj.api.public.key}")
    private String apiKey;

    @Value("${mj.api.private.key}")
    private String apiSecret;

    @Bean
    public MailjetClient mailjetClient() {
        ClientOptions options = ClientOptions.builder()
                .apiKey(apiKey)
                .apiSecretKey(apiSecret)
                .build();
        return new MailjetClient(options);
    }
}
