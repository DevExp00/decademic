package kz.djdegens.academic.services.implementations;


import kz.djdegens.academic.datas.AnswerData;
import kz.djdegens.academic.datas.QuestionData;
import kz.djdegens.academic.dtos.AnswerDto;
import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.entities.Answer;
import kz.djdegens.academic.entities.Question;
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
    public ApplicationDto addAnswer(AnswerDto answerDto) {
        if(Objects.isNull(answerDto))throw new IllegalArgumentException("Answer dto can not be null");
        Question question = questionData.findById(answerDto.getQuestionId());
        Answer answer = answerData.save(answerMapper.dtoToEntity(answerDto,question));
        answerDto = new AnswerDto();
        answerDto.setId(answer.getId());
        return ApplicationDto.builder()
                .answer(answerDto)
                .build();
    }

    @Override
    public void editAnswer(Long answerId, AnswerDto answerDto) {
        Answer answer = answerData.findById(answerId);
        answerData.save(answerMapper.dtoToEntity(answer,answerDto));
    }
}
