package com.geo.openai.service;

import com.geo.openai.model.AuthorQuotes;

public interface IQuoteService {
    AuthorQuotes getAuthorQuotes(String author, int nbQuotes);
}
