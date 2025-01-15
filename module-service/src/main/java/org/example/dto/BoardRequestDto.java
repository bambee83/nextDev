package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * 도메인을 직접적으로 다루는 것은 위험 할 수 있다.
 * Jpa 의 영속성 컨텍스트 때문에 혹시나 같은 트렌젝션 내에서 엔티티 수정시
 * 트렌젝션이 끝날때 그대로 DB 에 커밋을 찍을 수도 있다.
 * 따라서 DB 에서 가져온 후에는 따로 Object 에 넣어야 한다.
 */
import javax.validation.constraints.NotBlank;

@Schema(description = "내용 요청 정보")
@Getter @NoArgsConstructor // 기본 생성자 (Spring 및 Jackson이 필요)
public class BoardRequestDto {

    @Schema(description = "내용", required = true, example = "내용 예시입니다.")
    @NotBlank(message = "내용은 필수값입니다.")
    private String title;

    // testCode 용 추가 생성자 (Service 단)
    public BoardRequestDto(String title) {
        this.title = title;
    }
}
