package com.artemis.config;

import org.springframework.context.annotation.Configuration;

// In Spring Boot 3, Envers is auto-configured when the
// hibernate-envers dependency is on the classpath.
// You do NOT need a manual factory bean.
// Simply extend RevisionRepository in your repository interfaces.
@Configuration
public class HibernateEnversConfig {
    // Intentionally empty. Auto-configuration handles everything.
    // If you need custom revision entities, define them here later.
}