package com.screenmatch.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;

public class ChatgptAPI
{
    public static String getTranslate(String text) {
        OpenAiService service = new OpenAiService("sk-proj-ZoZvky3nNhGrvGgr1Du9T3BlbkFJGLvqCJdE335SeYcvmkdX");

        CompletionRequest query = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("traduce a español el siguiente texto: " + text)
                .maxTokens(1000)
                .temperature(0.7)
                .build();


        try {
            CompletionResult result = service.createCompletion(query);
            String translatedText = result.getChoices().get(0).getText();

            return translatedText;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Translate Error: " + e.getMessage());
            return text;
        }
    }
}
