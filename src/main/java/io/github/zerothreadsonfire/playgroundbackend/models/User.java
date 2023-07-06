package io.github.zerothreadsonfire.playgroundbackend.models;

import lombok.*;

/**
 * @NoArgsConstructor will generate a constructor with no params.
 * @AllArgsConstructor will generate a constructor with 1 param for each field in our class.
 * @Getter & @Setter generates the getters and setters for the fields.
 * @Data combines @Getter, @Setter, @ToString, @EqualsAndHashCode & @RequiredArgsConstructor
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private String id;
    private String name;
    private String emailId;
}
