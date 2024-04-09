package com.geo.openai.service.impl;

import com.geo.openai.model.AuthorQuotes;
import com.geo.openai.service.IQuoteService;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Service
public class QuoteServiceImpl implements IQuoteService {

    private static final BeanOutputParser<AuthorQuotes> authorQuotesBeanOutputParser = new BeanOutputParser<>(AuthorQuotes.class);

    @Value("classpath:/prompts/quote.st")
    private Resource quotePromptResource;

    private ChatClient chatClient;

    public QuoteServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public AuthorQuotes getAuthorQuotes(String author, int nbQuotes) {
        PromptTemplate promptTemplate = new PromptTemplate(quotePromptResource);
        var userMessage = promptTemplate.createMessage(Map.of("author", author, "number", nbQuotes, "format", authorQuotesBeanOutputParser.getFormat()));
        var systemMessage = new SystemMessage("Your primary function is to tell quotes from the same author. if someone ask you another question, tell them you only give quotes from Author");
        Prompt quotePrompt = new Prompt(Arrays.asList(systemMessage, userMessage));
        var generation = chatClient.call(quotePrompt).getResult();

        return authorQuotesBeanOutputParser.parse(generation.getOutput().getContent());
    }
}
