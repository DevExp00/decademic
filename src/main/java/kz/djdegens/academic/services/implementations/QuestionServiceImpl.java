package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.interfaces.QuestionData;
import kz.djdegens.academic.datas.interfaces.QuizData;
import kz.djdegens.academic.dtos.QuestionDto;
import kz.djdegens.academic.entities.Question;
import kz.djdegens.academic.entities.Quiz;
import kz.djdegens.academic.mappers.QuestionMapper;
import kz.djdegens.academic.services.interfaces.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuizData quizData;
    private final QuestionData questionData;
    private final QuestionMapper questionMapper;

    @Override
    public void addQuestion(QuestionDto questionDto) {
        if(Objects.isNull(questionDto))throw new IllegalArgumentException("Question dto can not be null");
        Quiz quiz = quizData.findById(questionDto.getQuizId());
        questionData.save(questionMapper.dtoToEntity(questionDto,quiz));
    }
}
