package com.spring.mvc.chap04.controller;

import com.spring.mvc.chap04.dto.ScoreListResponseDTO;
import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * # 요청 URL
 * 1. 학생 성적 정보 등록화면을 보여주고 및 성적 정보 목록 조회 처리
 * - /score/list : GET
 * <p>
 * 2. 성적 정보 등록 처리 요청
 * - /score/register : POST
 * <p>
 * 3. 성적 정보 삭제 요청
 * - /score/remove : POST
 * <p>
 * 4. 성적 정보 상세 조회 요청
 * - /score/detail : GET
 */
@Controller
@RequestMapping("/score")
//@AllArgsConstructor : 모든 필드를 초기화하는 생성자
@RequiredArgsConstructor // : final 필드만 초기화하는 생성자
public class ScoreController {

    // 저장소에 의존해야 데이터를 받아서 클라이언트에게 응답할 수 있음
    private final ScoreRepository repository;

    // 만약에 클래스의 생성자가 단 1개라면
    // 자동으로 @Autowired 를 써줌
    //  @Autowired
//    public ScoreController(ScoreRepository repository) {
//        this.repository = repository;
//    }

    // 1. 성적등록화면 띄우기 + 정보목록조회
    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "num") String sort) {
        List<Score> scoreList = repository.findAll(sort);

        // scoreList 에서 원하는 정보만 추출하고 이름을 마스킹해서
        // 다시 DTO 리스트로 변환해줘야 한다.
        List<ScoreListResponseDTO> responseDTOList =
                scoreList.stream()
                        .map(ScoreListResponseDTO::new)
                        .collect(Collectors.toList());

        model.addAttribute("sList", responseDTOList);

        return "chap04/score-list";
    }

    // 2. 성적 정보 등록 처리 요청
    @PostMapping("/register")
    public String register(ScoreRequestDTO dto) {
        // 입력데이터(쿼리스트링) 읽기
        // dto(ScoreDTO) 를 entity(Score) 로 변환해야 함.
        // save 명령
        repository.save(new Score(dto));
        /*
            등록 요청에서 JSP 뷰 포워딩을 하면
            갱신된 목록을 다시 한 번 저장소에서 불러와
            모델에 담는 추가적인 코드가 필요하지만

            리다이렉트를 통해서 위에서 만든 /score/list : GET
            을 자동으로 다시 보낼 수만 있다면 번거로운 코드가
            줄어들 수 있겠다.
         */
//        return "redirect:www.naver.com";
        return "redirect:/score/list";
    }

    // 3. 성적 정보 삭제 요청
    @GetMapping("/remove")
    public String remove(@RequestParam int stuNum) {
        repository.deleteByStuNum(stuNum);

        return "redirect:/score/list";
    }

    // 4. 성적 정보 상세 조회 요청
    @GetMapping("/detail")
    public String detail(int stuNum, Model model) {
        retrieve(stuNum, model);

        return "chap04/score-detail";
    }

    @GetMapping("/inputModify")
    public String inputModify(int stuNum, Model model) {
        retrieve(stuNum, model);

        return "chap04/score-modify";
    }
    // get -> 데이터 베이스에 값이 변경이 되지 않는 행동들
    // post -> 데이터 베이스에 값이 변경이 되는 행동들
    // put -> 전체 데이터 베이스의 한 row 가 전체 바뀔 때
    // patch -> 전체 데이터 베이스의 한 row 의 일부 데이터가 바뀔 때
    // delete -> 전체 데이터 베이스의 한 row 를 전체 삭제할 때

    @PostMapping("/modify")
    public String modify(ScoreRequestDTO dto) {
        repository.modify(dto);

        return "redirect:/score/detail?stuNum=" + dto.getStuNum();
    }

    private void retrieve(int stuNum, Model model) {
        model.addAttribute("student", repository.findByStuNum(stuNum));
    }

}
