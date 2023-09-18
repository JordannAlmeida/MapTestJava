package org.example.repository;

import org.example.decider.DeciderTemplate;

public interface IDeciderTemplateRepository {
    DeciderTemplate getDeciderTemplateById(String journeyId);
}
