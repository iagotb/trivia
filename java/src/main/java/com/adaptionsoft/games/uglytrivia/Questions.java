package com.adaptionsoft.games.uglytrivia;

import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
public class Questions {

    private List<Question> questions = new LinkedList<>();

    public void createDefaultQuestions() {
        for (int i = 0; i < 50; i++) {
            questions.add(new Question(Category.POP, UUID.randomUUID(), "Pop Question " + i));
            questions.add(new Question(Category.SCIENCE,UUID.randomUUID(), "Sciente Question " + i));
            questions.add(new Question(Category.SPORTS, UUID.randomUUID(),"Sport Question " + i));
            questions.add(new Question(Category.ROCK, UUID.randomUUID(),"Rock Question " + i));
        }
    }

    public Optional<Question> getNextQuestionFromCategory(Category category) {
        return questions.stream().filter(question -> question.getCategory().equals(category)).findFirst();
    }

    public void removeQuestion(UUID id) {
        this.questions = questions.stream().filter(question -> !question.getId().equals(id)).collect(Collectors.toList());
    }
}
