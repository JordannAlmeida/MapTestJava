package org.example.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.decider.DeciderTemplate;

public interface IDeciderTemplateRepository {
    DeciderTemplate getDeciderTemplateById(String journeyId) throws JsonProcessingException;
}
