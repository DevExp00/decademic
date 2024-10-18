package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.AnswerData;
import kz.djdegens.academic.entities.Answer;
import kz.djdegens.academic.repositories.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AnswerDataImpl implements AnswerData {

    private final AnswerRepository answerRepository;

    @Override
    public Answer save(Answer answer) {
        if(Objects.isNull(answer))throw new IllegalArgumentException("Answer can not be null");
        return answerRepository.save(answer);
    }
}
