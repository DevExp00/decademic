package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.interfaces.AnswerData;
import kz.djdegens.academic.datas.interfaces.QuestionData;
import kz.djdegens.academic.datas.interfaces.QuizData;
import kz.djdegens.academic.dtos.AnswerDto;
import kz.djdegens.academic.entities.Question;
import kz.djdegens.academic.entities.Quiz;
import kz.djdegens.academic.mappers.AnswerMapper;
import kz.djdegens.academic.services.interfaces.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerData answerData;
    private final AnswerMapper answerMapper;
    private final QuestionData questionData;

    @Override
    public void addAnswer(AnswerDto answerDto) {
        if(Objects.isNull(answerDto))throw new IllegalArgumentException("Answer dto can not be null");
        Question question = questionData.findById(answerDto.getQuestionId());
        answerData.save(answerMapper.dtoToEntity(answerDto,question));
    }
}
