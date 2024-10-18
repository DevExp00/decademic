package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.QuestionData;
import kz.djdegens.academic.entities.Question;
import kz.djdegens.academic.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuestionDataImpl implements QuestionData {

    private final QuestionRepository questionRepository;
    @Override
    public Question save(Question question) {
        if(Objects.isNull(question))throw new IllegalArgumentException("Question can not be null");
        return questionRepository.save(question);
    }
}
