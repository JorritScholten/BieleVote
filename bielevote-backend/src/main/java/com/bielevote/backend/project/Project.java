package com.bielevote.backend.project;

import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserViews;
import com.fasterxml.jackson.annotation.*;
import com.bielevote.backend.votes.Vote;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

@Jacksonized
@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @JsonView({ProjectViews.GetProjectList.class, ProjectViews.Serialize.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @JsonView({ProjectViews.GetProjectList.class, ProjectViews.Serialize.class})
    private String title;

    @NonNull
    @JsonView({ProjectViews.GetProjectList.class, ProjectViews.Serialize.class})
    @Column(columnDefinition = "CLOB")
    private String summary;

    @JsonView({ProjectViews.Serialize.class})
//    @JsonSerialize(using = ContentSerialize.class) //temporary for migrating db to json
    @Column(columnDefinition = "CLOB")
    private String content;

    @NonNull
    @JsonView({ProjectViews.GetProjectList.class, ProjectViews.Serialize.class})
//    @JsonIdentityInfo(property = "username", generator = ObjectIdGenerators.PropertyGenerator.class) //temporary for migrating db to json
//    @JsonIdentityReference(alwaysAsId = true) //temporary for migrating db to json
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonManagedReference
    private User author;

    @NonNull
    @JsonView({ProjectViews.GetProjectList.class, ProjectViews.Serialize.class})
    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime datePublished;

    @NonNull
    @JsonView({ProjectViews.GetProjectList.class, ProjectViews.Serialize.class})
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus status;

    @JsonManagedReference
    @OneToMany(mappedBy = "project")
    private Set<Vote> votes;

//    //temporary for migrating db to json
//    public static class ContentSerialize extends JsonSerializer<String> {
//        @Override
//        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//            var split = value.split("\n");
//            gen.writeStartArray();
//            for (var s : split) {
//                if (s.isBlank()) {
////                    gen.writeString("<p><br></p>");
//                } else {
//                    gen.writeString("<p>" + s + "</p>");
//                }
//            }
//            gen.writeEndArray();
//        }
//    }
}
