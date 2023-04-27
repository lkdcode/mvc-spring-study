package com.spring.mvc.chap04.service;

import com.spring.mvc.chap04.dto.ScoreListResponseDTO;
import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

// 컨트롤러와 레파지토리 사이 비즈니스 로직 처리
// ex) 트랜잭션 처리, 예외 처리, dto 변환 처리

//@RequiredArgsConstructor
@Service
public class ScoreService {


    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(@Qualifier("spring") ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    // 목록 조회 중간 처리
    /*
        컨트롤러는 데이터 베이스를 통해
        성적 정보 리스트를 가져오길 원한다.
        그런데 데이터 베이스는 성적정보를 전부 모아서 준다.
        컨트롤러는 정보를 일부만 받았으면 좋겠다.
     */
//    public List<ScoreListResponseDTO> getList(String sort) {
//        return scoreRepository.findAll(sort)
//                .stream()
//                .map(ScoreListResponseDTO::new)
//                .collect(toList());
//    }

    public List<ScoreListResponseDTO> getList(String sort) {
        return scoreRepository.findAll(sort)
                .stream()
                .map(ScoreListResponseDTO::new)
                .collect(toList());
    }


    // 등록 중간 처리
    // 컨트롤러는 나에게 DTO 를 줬지만
    // 레파지토리는 ScoreEntity 를 달라고 한다.
    // 내가 변환해야겠네...
    public boolean insertScore(ScoreRequestDTO dto) {
        // dto(ScoreDTO) 를 entity (Score) 로 변환해야 함.
        // save 명령
        return scoreRepository.save(new Score(dto));
    }

    public boolean delete(int stuNum) {
        return scoreRepository.deleteByStuNum(stuNum);
    }

    // 상세 조회, 수정화면 조회 중간처리
    public Score retrieve(int stuNum) {
        // ex) 만약에 스코어 전체 데이터 말고
        //     몇개만 추리고 전후처리해서 줘라
        return scoreRepository.findByStuNum(stuNum);
    }

    public void modify(ScoreRequestDTO dto) {
        scoreRepository.modify(dto);
    }
}
