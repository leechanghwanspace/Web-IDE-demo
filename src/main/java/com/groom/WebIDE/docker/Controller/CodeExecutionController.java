package com.groom.WebIDE.docker.Controller;

import com.groom.WebIDE.docker.service.DockerCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/execute")
@Tag(name = "Code Execution API", description = "코드를 Docker 컨테이너에서 실행하는 API")
public class CodeExecutionController {

    private final DockerCommandService dockerCommandService;

    public CodeExecutionController(DockerCommandService dockerCommandService) {
        this.dockerCommandService = dockerCommandService;
    }

    @PostMapping
    @Operation(
            summary = "코드 실행",
            description = "주어진 파일 경로와 언어를 기반으로 Docker 컨테이너에서 코드를 실행합니다."
    )
    public String executeCode(
            @Parameter(description = "실행할 파일의 이름", example = "testCode.c")
            @RequestParam("fileName") String fileName,
            @Parameter(description = "실행할 파일의 경로", example = "C:/Users/space/OneDrive/...")
            @RequestParam("filePath") String filePath,
            @Parameter(description = "코드 언어 (예: c, cpp, java, python, javascript)", example = "c")
            @RequestParam("language") String language // 언어 선택
    ) {
        try {
            return dockerCommandService.executeDockerCommand(fileName, filePath, language);
        } catch (Exception e) {
            return "Code execution failed! Error: " + e.getMessage();
        }
    }
}
